package com.github.Ox0017.vrc.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.Ox0017.vrc.VRChatApiClient;
import com.github.Ox0017.vrc.model.VrcRequestContext;
import com.github.Ox0017.vrc.model.client.Response;
import com.github.Ox0017.vrc.model.dto.avatar.AvatarDto;
import com.github.Ox0017.vrc.model.dto.config.RemoteConfigDto;
import com.github.Ox0017.vrc.model.dto.favorite.FavoriteDto;
import com.github.Ox0017.vrc.model.dto.favorite.FavoriteTypeDto;
import com.github.Ox0017.vrc.model.dto.friend.FriendStatusDto;
import com.github.Ox0017.vrc.model.dto.general.VrcErrorDto;
import com.github.Ox0017.vrc.model.dto.general.VrcSuccessDto;
import com.github.Ox0017.vrc.model.dto.notification.NotificationDto;
import com.github.Ox0017.vrc.model.dto.user.AuthDto;
import com.github.Ox0017.vrc.model.dto.user.CurrentUserDto;
import com.github.Ox0017.vrc.model.dto.user.LimitedUserDto;
import com.github.Ox0017.vrc.model.dto.user.UserDto;
import com.github.Ox0017.vrc.model.dto.world.LimitedWorldDto;
import com.github.Ox0017.vrc.model.dto.world.WorldDto;
import com.github.Ox0017.vrc.model.parameter.*;
import com.github.Ox0017.vrc.util.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VRChatApiClientImpl implements VRChatApiClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(VRChatApiClientImpl.class);

	// headers
	private static final String API_KEY_HEADER = "apiKey";
	private static final String AUTH_HEADER = "auth";
	private static final String CF_HEADER = "__cfduid";

	// endpoints
	private static final String AUTH = "auth";
	private static final String AUTH_USER = AUTH + "/user";
	private static final String AVATARS = "avatars";
	private static final String CONFIG = "config";
	private static final String FAVORITES = "favorites";
	private static final String FRIENDS = AUTH_USER + "/friends";
	private static final String LOGOUT = "logout";
	private static final String NOTIFICATIONS = AUTH_USER + "/notifications";
	private static final String USER = "user";
	private static final String USERS = "users";
	private static final String WORLDS = "worlds";

	private final String baseUrl;

	private final HttpClient httpClient;

	private final ObjectMapper objectMapper;

	/**
	 * Initializes the VRChatApiClient with a default client and base url
	 */
	public VRChatApiClientImpl() {
		this(defaultHttpClientBuilder()
				.disableAuthCaching()
				.disableAutomaticRetries()
				.disableRedirectHandling()
				.build());
	}

	private static HttpClientBuilder defaultHttpClientBuilder() {
		final int timeoutMillis = 2000;
		final RequestConfig.Builder requestBuilder = RequestConfig.custom();
		requestBuilder.setConnectTimeout(timeoutMillis);
		requestBuilder.setConnectionRequestTimeout(timeoutMillis);
		requestBuilder.setSocketTimeout(timeoutMillis);

		final HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setDefaultRequestConfig(requestBuilder.build());
		return httpClientBuilder;
	}

	/**
	 * Initializes the VRChatApiClient with a provided httpClient and the default VRChat base url
	 *
	 * @param httpClient client to be used for all requests
	 */
	public VRChatApiClientImpl(final HttpClient httpClient) {
		this(httpClient, "https://api.vrchat.cloud/api/1/");
	}

	/**
	 * Initialize the client with the provided httpClient and with a custom base url
	 *
	 * @param httpClient client to be used for all requests
	 * @param baseUrl    base url for all requests
	 */
	public VRChatApiClientImpl(final HttpClient httpClient, final String baseUrl) {
		if (baseUrl == null) {
			throw new IllegalArgumentException("BaseUrl is null");
		}
		if (httpClient == null) {
			throw new IllegalArgumentException("HttpClient is null");
		}

		this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
		this.httpClient = httpClient;
		this.objectMapper = new ObjectMapper();
		this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	@Override
	public RemoteConfigDto getRemoteConfig(final VrcRequestContext vrcRequestContext) {
		if (vrcRequestContext == null) {
			throw new IllegalArgumentException("VrcRequestContext is null");
		}

		LOGGER.info("Get remote config");

		final HttpUriRequest request = RequestBuilder.get(this.baseUrl + CONFIG).build();

		final Response response = this.executeRequest(request, vrcRequestContext);

		return this.deserializeResponse(response, RemoteConfigDto.class);
	}

	@Override
	public CurrentUserDto getCurrentUser(final VrcRequestContext vrcRequestContext) {
		verifyRequestContext(vrcRequestContext);

		LOGGER.info("Get current user");

		final HttpUriRequest request = RequestBuilder.get(this.baseUrl + AUTH_USER).build();

		final Response response = this.executeRequest(request, vrcRequestContext);

		return this.deserializeResponse(response, CurrentUserDto.class);
	}

	@Override
	public boolean verifyAuth(final VrcRequestContext vrcRequestContext) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return false;
		}

		LOGGER.debug("Verify auth");

		final HttpUriRequest request = RequestBuilder.get(this.baseUrl + AUTH).build();

		final VrcRequestContext previousContext = vrcRequestContext.copy();
		final Response response = this.executeRequest(request, vrcRequestContext);

		final AuthDto authDto = this.deserializeResponse(response, AuthDto.class);
		if (authDto == null) {
			return false;
		}

		if (!Boolean.TRUE.equals(authDto.getValid())) {
			LOGGER.info("Auth is not valid, removing from vrcRequestContext");
			vrcRequestContext.setAuth(null);
			return false;
		}

		final String authResponse = authDto.getToken();
		if (authResponse == null || authResponse.isEmpty()) {
			LOGGER.warn("Auth response was ok but did not contain the authToken");
			vrcRequestContext.restoreBy(previousContext);
			return true;
		}

		if (!Objects.equals(vrcRequestContext.getAuth(), authResponse)) {
			vrcRequestContext.setAuth(authResponse);
			LOGGER.info("Updated valid auth value: {}", authResponse);
		}

		return true;
	}

	@Override
	public FavoriteDto getFavorite(final VrcRequestContext vrcRequestContext, final String favoriteId) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		if (favoriteId == null) {
			throw new IllegalArgumentException("FavoriteId is null");
		}

		LOGGER.info("Get favorite {}", favoriteId);

		final HttpUriRequest request = RequestBuilder.get(this.baseUrl + FAVORITES + "/" + favoriteId).build();

		final Response response = this.executeRequest(request, vrcRequestContext);

		return this.deserializeResponse(response, FavoriteDto.class);
	}

	@Override
	public List<FavoriteDto> getFavorites(final VrcRequestContext vrcRequestContext) {
		return this.getFavorites(vrcRequestContext, null);
	}

	@Override
	public List<FavoriteDto> getFavorites(final VrcRequestContext vrcRequestContext, final FavoriteParameters favoriteParameters) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		LOGGER.info("Get favorites {} params", (favoriteParameters == null ? "without" : "with"));

		final HttpUriRequest request = getWithParams(this.baseUrl + FAVORITES, favoriteParameters);

		final Response response = this.executeRequest(request, vrcRequestContext);

		final FavoriteDto[] favoriteDtoArray = this.deserializeResponse(response, FavoriteDto[].class);
		if (favoriteDtoArray == null) {
			return null;
		}
		return Stream.of(favoriteDtoArray).collect(Collectors.toList());
	}

	@Override
	public FavoriteDto addFavorite(final VrcRequestContext vrcRequestContext, final FavoriteTypeDto favoriteTypeDto, final String favoriteId, final String tag) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		if (favoriteTypeDto == null) {
			throw new IllegalArgumentException("FavoriteTypeDto is null");
		}
		if (favoriteTypeDto == FavoriteTypeDto.UNKNOWN) {
			throw new IllegalArgumentException("FavoriteTypeDto UNKNOWN is not supported");
		}
		if (favoriteId == null) {
			throw new IllegalArgumentException("FavoriteId is null");
		}
		if (tag == null) {
			throw new IllegalArgumentException("Tag is null");
		}

		LOGGER.info("Add favorite {} {}", favoriteTypeDto, favoriteId);

		final HttpPost request = new HttpPost(this.baseUrl + FAVORITES);

		final FavoriteDto favoriteDto = new FavoriteDto();
		favoriteDto.setFavoriteId(favoriteId);
		favoriteDto.setType(favoriteTypeDto);
		favoriteDto.setTags(Collections.singletonList(tag));

		final Response response = this.executeRequestWithBody(request, vrcRequestContext, favoriteDto);

		return this.deserializeResponse(response, FavoriteDto.class);
	}

	@Override
	public boolean deleteFavorite(final VrcRequestContext vrcRequestContext, final String favoriteId) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return false;
		}

		if (favoriteId == null) {
			throw new IllegalArgumentException("FavoriteId is null");
		}

		LOGGER.debug("Delete favorite {}", favoriteId);

		final HttpUriRequest request = RequestBuilder.delete(this.baseUrl + FAVORITES + "/" + favoriteId).build();

		final Response response = this.executeRequest(request, vrcRequestContext);
		if (response.is2xxSuccessful()) {
			LOGGER.info("Delete favorite {} was successful", favoriteId);
			return true;
		}
		else {
			final String errorMessage = this.deserializeError(response.getResponseBody());
			LOGGER.info("Delete favorite {} failed: {}", favoriteId, errorMessage);
			return false;
		}
	}

	@Override
	public AvatarDto getAvatar(final VrcRequestContext vrcRequestContext, final String avatarId) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		if (avatarId == null) {
			throw new IllegalArgumentException("AvatarId is null");
		}

		LOGGER.info("Get avatar {}", avatarId);

		final HttpUriRequest request = RequestBuilder.get(this.baseUrl + AVATARS + "/" + avatarId).build();

		final Response response = this.executeRequest(request, vrcRequestContext);

		return this.deserializeResponse(response, AvatarDto.class);
	}

	@Override
	public CurrentUserDto selectAvatar(final VrcRequestContext vrcRequestContext, final String avatarId) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		if (avatarId == null) {
			throw new IllegalArgumentException("AvatarId is null");
		}

		LOGGER.info("Select avatar {}", avatarId);

		final HttpUriRequest request = RequestBuilder.put(this.baseUrl + AVATARS + "/" + avatarId + "/select").build();

		final Response response = this.executeRequest(request, vrcRequestContext);

		return this.deserializeResponse(response, CurrentUserDto.class);
	}

	@Override
	public UserDto getUserById(final VrcRequestContext vrcRequestContext, final String userId) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		if (userId == null) {
			throw new IllegalArgumentException("UserId is null");
		}

		LOGGER.info("Get user by id {}", userId);

		final HttpUriRequest request = RequestBuilder.get(this.baseUrl + USERS + "/" + userId).build();

		final Response response = this.executeRequest(request, vrcRequestContext);

		return this.deserializeResponse(response, UserDto.class);
	}

	@Override
	public UserDto getUserByName(final VrcRequestContext vrcRequestContext, final String userName) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		if (userName == null) {
			throw new IllegalArgumentException("UserName is null");
		}

		LOGGER.info("Get user by name {}", userName);

		final HttpUriRequest request = RequestBuilder.get(this.baseUrl + USERS + "/" + userName + "/name").build();

		final Response response = this.executeRequest(request, vrcRequestContext);

		return this.deserializeResponse(response, UserDto.class);
	}

	@Override
	public List<LimitedUserDto> getUsers(final VrcRequestContext vrcRequestContext, final UserParameters userParameters) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		if (userParameters == null) {
			throw new IllegalArgumentException("UserParameters is null");
		}

		LOGGER.info("Get users by parameters");

		final String path = userParameters.isActive() ? USERS + "/active" : USERS;
		final HttpUriRequest request = getWithParams(this.baseUrl + path, userParameters);

		final Response response = this.executeRequest(request, vrcRequestContext);

		final LimitedUserDto[] limitedUserDtoArray = this.deserializeResponse(response, LimitedUserDto[].class);
		if (limitedUserDtoArray == null) {
			return null;
		}
		return Stream.of(limitedUserDtoArray).collect(Collectors.toList());
	}

	@Override
	public List<LimitedUserDto> getFriends(final VrcRequestContext vrcRequestContext, final FriendParameters friendParameters) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		if (friendParameters == null) {
			throw new IllegalArgumentException("FriendParameters is null");
		}

		LOGGER.info("Get friends by parameters");

		final HttpUriRequest request = getWithParams(this.baseUrl + FRIENDS, friendParameters);

		final Response response = this.executeRequest(request, vrcRequestContext);

		final LimitedUserDto[] limitedUserDtoArray = this.deserializeResponse(response, LimitedUserDto[].class);
		if (limitedUserDtoArray == null) {
			return null;
		}
		return Stream.of(limitedUserDtoArray).collect(Collectors.toList());
	}

	@Override
	public FriendStatusDto getFriendStatus(final VrcRequestContext vrcRequestContext, final String userId) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		if (userId == null) {
			throw new IllegalArgumentException("UserId is null");
		}

		LOGGER.info("Get friend status for userId {}", userId);

		final HttpUriRequest request = RequestBuilder.get(this.baseUrl + USER + "/" + userId + "/friendStatus").build();

		final Response response = this.executeRequest(request, vrcRequestContext);

		return this.deserializeResponse(response, FriendStatusDto.class);
	}

	@Override
	public NotificationDto sendFriendRequest(final VrcRequestContext vrcRequestContext, final String userId) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		if (userId == null) {
			throw new IllegalArgumentException("UserId is null");
		}

		LOGGER.info("Send friend request for userId {}", userId);

		final HttpUriRequest request = RequestBuilder.post(this.baseUrl + USER + "/" + userId + "/friendRequest").build();

		final Response response = this.executeRequest(request, vrcRequestContext);

		return this.deserializeResponse(response, NotificationDto.class);
	}

	@Override
	public boolean acceptFriendRequest(final VrcRequestContext vrcRequestContext, final String notificationId) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return false;
		}

		if (notificationId == null) {
			throw new IllegalArgumentException("NotificationId is null");
		}

		LOGGER.debug("Accept friend request for notificationId {}", notificationId);

		final HttpUriRequest request = RequestBuilder.put(this.baseUrl + NOTIFICATIONS + "/" + notificationId + "/accept").build();

		final Response response = this.executeRequest(request, vrcRequestContext);
		if (response.is2xxSuccessful()) {
			final String message = this.deserializeSuccess(response.getResponseBody());
			LOGGER.info("Accept friend request for notificationId {} was successful: {}", notificationId, message);
			return true;
		}
		else {
			final String errorMessage = this.deserializeError(response.getResponseBody());
			LOGGER.info("Accept friend request for notificationId {} failed: {}", notificationId, errorMessage);
			return false;
		}
	}

	@Override
	public NotificationDto ignoreFriendRequest(final VrcRequestContext vrcRequestContext, final String notificationId) {
		return this.deleteNotification(vrcRequestContext, notificationId);
	}

	@Override
	public boolean removeFriend(final VrcRequestContext vrcRequestContext, final String userId) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return false;
		}

		if (userId == null) {
			throw new IllegalArgumentException("UserId is null");
		}

		LOGGER.debug("Remove friend for userId {}", userId);

		final HttpUriRequest request = RequestBuilder.delete(this.baseUrl + FRIENDS + "/" + userId).build();

		final Response response = this.executeRequest(request, vrcRequestContext);
		if (response.is2xxSuccessful()) {
			final String message = this.deserializeSuccess(response.getResponseBody());
			LOGGER.info("Remove friend for userId {} was successful: {}", userId, message);
			return true;
		}
		else {
			final String errorMessage = this.deserializeError(response.getResponseBody());
			LOGGER.info("Remove friend for userId {} failed: {}", userId, errorMessage);
			return false;
		}
	}

	@Override
	public List<NotificationDto> getNotifications(final VrcRequestContext vrcRequestContext, final NotificationParameters notificationParameters) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		if (notificationParameters == null) {
			throw new IllegalArgumentException("NotificationParameters is null");
		}

		LOGGER.info("Get notification by parameters");

		final HttpUriRequest request = getWithParams(this.baseUrl + NOTIFICATIONS, notificationParameters);

		final Response response = this.executeRequest(request, vrcRequestContext);

		final NotificationDto[] notificationDtoArray = this.deserializeResponse(response, NotificationDto[].class);
		if (notificationDtoArray == null) {
			return null;
		}
		return Stream.of(notificationDtoArray).collect(Collectors.toList());
	}

	@Override
	public NotificationDto markNotificationAsRead(final VrcRequestContext vrcRequestContext, final String notificationId) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		if (notificationId == null) {
			throw new IllegalArgumentException("NotificationId is null");
		}

		LOGGER.debug("Mark notification {} as read", notificationId);

		final HttpUriRequest request = RequestBuilder.put(this.baseUrl + NOTIFICATIONS + "/" + notificationId + "/see").build();

		final Response response = this.executeRequest(request, vrcRequestContext);

		return this.deserializeResponse(response, NotificationDto.class);
	}

	@Override
	public NotificationDto deleteNotification(final VrcRequestContext vrcRequestContext, final String notificationId) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		if (notificationId == null) {
			throw new IllegalArgumentException("NotificationId is null");
		}

		LOGGER.debug("Mark notification {} as read", notificationId);

		final HttpUriRequest request = RequestBuilder.put(this.baseUrl + NOTIFICATIONS + "/" + notificationId + "/hide").build();

		final Response response = this.executeRequest(request, vrcRequestContext);

		return this.deserializeResponse(response, NotificationDto.class);
	}

	@Override
	public WorldDto getWorldById(final VrcRequestContext vrcRequestContext, final String worldId) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		if (worldId == null) {
			throw new IllegalArgumentException("WorldId is null");
		}

		LOGGER.info("Get world for id {}", worldId);

		final HttpUriRequest request = RequestBuilder.get(this.baseUrl + WORLDS + "/" + worldId).build();

		final Response response = this.executeRequest(request, vrcRequestContext);

		return this.deserializeResponse(response, WorldDto.class);
	}

	@Override
	public List<LimitedWorldDto> getWorlds(final VrcRequestContext vrcRequestContext, final WorldParameters worldParameters) {
		if (isSessionMissing(vrcRequestContext)) {
			LOGGER.warn("Session is missing");
			return null;
		}

		if (worldParameters == null) {
			throw new IllegalArgumentException("WorldParameters is null");
		}

		LOGGER.info("Get worlds by parameters");

		final HttpUriRequest request = getWithParams(this.baseUrl + WORLDS + worldParameters.getEndpoint(), worldParameters);

		final Response response = this.executeRequest(request, vrcRequestContext);

		final LimitedWorldDto[] limitedWorldDtoArray = this.deserializeResponse(response, LimitedWorldDto[].class);
		if (limitedWorldDtoArray == null) {
			return null;
		}
		return Stream.of(limitedWorldDtoArray).collect(Collectors.toList());
	}

	@Override
	public boolean logout(final VrcRequestContext vrcRequestContext) {
		if (isSessionMissing(vrcRequestContext)) {
			return true;
		}

		final HttpUriRequest request = RequestBuilder.put(this.baseUrl + LOGOUT).build();

		LOGGER.debug("Logout");

		final Response response = this.executeRequest(request, vrcRequestContext);
		if (response.is2xxSuccessful()) {
			LOGGER.info("Logout was successful");
			if (vrcRequestContext.getAuth() != null) {
				// is normally done by empty auth header from response
				LOGGER.debug("Removed auth");
				vrcRequestContext.setAuth(null);
			}
			return true;
		}
		else {
			LOGGER.warn("Logout failed with statusCode {}: {}", response.getStatusCode(), response.getStatusPhrase());
			return false;
		}
	}

	private Response executeRequest(final HttpUriRequest request, final VrcRequestContext vrcRequestContext) {
		setHeaders(request, vrcRequestContext);
		LOGGER.trace("Executing {} request for {}", request.getMethod(), request.getURI().toString());

		final Response response = new Response();
		try {
			final long timestamp = System.currentTimeMillis();
			final HttpResponse httpResponse = this.httpClient.execute(request);
			LOGGER.debug("Request execution {} {} took {} ms", request.getMethod(), request.getURI().toString(), System.currentTimeMillis() - timestamp);

			final StatusLine statusLine = httpResponse.getStatusLine();
			if (statusLine != null) {
				response.setStatusCode(statusLine.getStatusCode());
				response.setStatusPhrase(statusLine.getReasonPhrase());
			}

			processHeaders(httpResponse, response, vrcRequestContext);

			if (httpResponse.getEntity() != null && httpResponse.getEntity().getContent() != null) {
				response.setResponseBody(IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8));
				LOGGER.trace("Server Response {} {} with body: {}", response.getStatusCode(), response.getStatusPhrase(), response.getResponseBody());
			}
			else {
				LOGGER.trace("Server Response {} {} without body", response.getStatusCode(), response.getStatusPhrase());
			}
			if (httpResponse instanceof CloseableHttpResponse) {
				((CloseableHttpResponse) httpResponse).close();
			}
		}
		catch (final Exception ex) {
			LOGGER.error("Request failed", ex);
		}

		return response;
	}

	private Response executeRequestWithBody(final HttpEntityEnclosingRequestBase request, final VrcRequestContext vrcRequestContext, final Object body) {
		final String serializedBody = this.serialize(body);
		if (serializedBody == null) {
			LOGGER.warn("Did not execute request {} {}: body is missing", request.getMethod(), request.getURI().toString());
			return new Response();
		}

		LOGGER.debug("Preparing {} request for {} with body {}", request.getMethod(), request.getURI().toString(), serializedBody);

		try {
			request.addHeader("content-type", "application/json; charset=utf-8");
			request.setEntity(new StringEntity(serializedBody));
			return this.executeRequest(request, vrcRequestContext);
		}
		catch (final UnsupportedEncodingException ex) {
			LOGGER.error("Could not set request body {}", body, ex);
			return new Response();
		}
	}

	private String serialize(final Object body) {
		if (body == null) {
			return null;
		}

		if (body instanceof String) {
			return (String) body;
		}

		try {
			return this.objectMapper.writeValueAsString(body);
		}
		catch (final JsonProcessingException ex) {
			LOGGER.warn("Failed to serialize {}", body.toString(), ex);
			return null;
		}
	}

	private <T> T deserializeResponse(final Response response, final Class<T> clazz) {
		if (response == null) {
			return null;
		}

		final String body = response.getResponseBody();
		if (response.is2xxSuccessful()) {
			if (body != null && !body.isEmpty()) {
				return this.deserializeResponse(body, clazz);
			}
			else {
				LOGGER.debug("No response body to deserialize for {}", clazz.getSimpleName());
			}
		}
		else if (response.getStatusCode() != null) {
			final String errorMessage = this.deserializeError(body);
			if (errorMessage != null) {
				LOGGER.info("Server returned: {}", errorMessage);
			}
			else {
				LOGGER.warn("Server returned statusCode {}: {}", response.getStatusCode(), response.getStatusPhrase());
			}
		}
		return null;
	}

	private String deserializeSuccess(final String body) {
		final VrcSuccessDto vrcSuccessDto = this.deserializeResponse(body, VrcSuccessDto.class);
		if (vrcSuccessDto == null || vrcSuccessDto.getSuccess() == null) {
			return null;
		}
		return vrcSuccessDto.getSuccess().getMessage();
	}

	private String deserializeError(final String body) {
		final VrcErrorDto vrcErrorDto = this.deserializeResponse(body, VrcErrorDto.class);
		if (vrcErrorDto == null || vrcErrorDto.getError() == null) {
			return null;
		}
		return vrcErrorDto.getError().getMessage();
	}

	private <T> T deserializeResponse(final String value, final Class<T> clazz) {
		if (value == null || clazz == null) {
			return null;
		}

		try {
			return this.objectMapper.readValue(value, clazz);
		}
		catch (final JsonProcessingException ex) {
			LOGGER.warn("Failed to deserialize {}", clazz.getSimpleName(), ex);
			return null;
		}
	}

	private static void verifyRequestContext(final VrcRequestContext vrcRequestContext) {
		if (vrcRequestContext == null) {
			throw new IllegalArgumentException("VrcRequestContext is null");
		}

		if (!vrcRequestContext.hasCredentials() && !vrcRequestContext.hasAuth()) {
			throw new IllegalArgumentException("VrcRequestContext is missing credentials");
		}
	}

	private static boolean isSessionMissing(final VrcRequestContext vrcRequestContext) {
		return vrcRequestContext == null || !vrcRequestContext.hasAuth();
	}

	private static String getBasicAuth(final VrcRequestContext vrcRequestContext) {
		return "Basic " + Base64.getEncoder().encodeToString((vrcRequestContext.getUsername() + ":" + vrcRequestContext.getPassword()).getBytes());
	}

	private static HttpUriRequest getWithParams(final String url, final RequestParameter parameters) {
		if (parameters == null || parameters.isEmpty()) {
			return RequestBuilder.get(url).build();
		}
		else {
			return RequestBuilder.get(url).addParameters(parameters.getParameters()).build();
		}
	}

	private static void setHeaders(final HttpUriRequest request, final VrcRequestContext vrcRequestContext) {
		final List<String> cookies = new ArrayList<>();

		if (vrcRequestContext.getApiKey() != null) {
			cookies.add(API_KEY_HEADER + "=" + vrcRequestContext.getApiKey());
			LOGGER.trace("Add {}", API_KEY_HEADER);
		}

		if (vrcRequestContext.hasAuth()) {
			cookies.add(AUTH_HEADER + "=" + vrcRequestContext.getAuth());
			LOGGER.trace("Add {}", AUTH_HEADER);
		}
		else if (vrcRequestContext.hasCredentials()) {
			request.addHeader(HttpHeaders.AUTHORIZATION, getBasicAuth(vrcRequestContext));
			LOGGER.trace("Add header {}", HttpHeaders.AUTHORIZATION);
		}

		if (!cookies.isEmpty()) {
			request.addHeader("cookie", String.join("; ", cookies));
		}
	}

	private static void processHeaders(final HttpResponse httpResponse, final Response response, final VrcRequestContext vrcRequestContext) {
		if (!isProcessHeaders(response)) {
			LOGGER.trace("Did not process headers");
			return;
		}

		if (response.is2xxSuccessful()) {
			final Header[] headers = httpResponse.getAllHeaders();
			if (headers != null) {
				final List<HeaderElement> elements = Stream.of(headers)
						.map(Header::getElements)
						.flatMap(Arrays::stream)
						.filter(Objects::nonNull)
						.collect(Collectors.toList());

				for (final HeaderElement element : elements) {
					final String name = element.getName();
					final String value = element.getValue();
					LOGGER.trace("Processing response header {}: {}", name, value);

					if (AUTH_HEADER.equals(name) && !Objects.equals(vrcRequestContext.getAuth(), value)) {
						final String newValue = StringUtils.trimToNull(value);
						vrcRequestContext.setAuth(newValue);
						LOGGER.debug("Updated auth value: {}", newValue);
					}
					else if (API_KEY_HEADER.equals(name) && !Objects.equals(vrcRequestContext.getApiKey(), value)) {
						final String newValue = StringUtils.trimToNull(value);
						vrcRequestContext.setApiKey(newValue);
						LOGGER.debug("Updated apiKey value: {}", newValue);
					}
					else if (CF_HEADER.equals(name) && !Objects.equals(vrcRequestContext.getCfduid(), value)) {
						final String newValue = StringUtils.trimToNull(value);
						vrcRequestContext.setCfduid(newValue);
						LOGGER.debug("Updated cfduid value: {}", newValue);
					}
				}
			}
		}
		else if (response.isUnauthorized()) {
			LOGGER.debug("Removed auth value: {}", vrcRequestContext.getAuth());
			vrcRequestContext.setAuth(null);
		}
	}

	private static boolean isProcessHeaders(final Response response) {
		// do not update/remove headers for bad request etc
		return response != null && (response.is2xxSuccessful() || response.isUnauthorized());
	}

}
