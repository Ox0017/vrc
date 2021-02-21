package com.github.Ox0017.vrc.impl;

import com.github.Ox0017.vrc.TestSupport;
import com.github.Ox0017.vrc.VRChatApiClient;
import com.github.Ox0017.vrc.model.VrcRequestContext;
import com.github.Ox0017.vrc.model.dto.avatar.AvatarDto;
import com.github.Ox0017.vrc.model.dto.config.RemoteConfigDto;
import com.github.Ox0017.vrc.model.dto.favorite.FavoriteDto;
import com.github.Ox0017.vrc.model.dto.favorite.FavoriteTypeDto;
import com.github.Ox0017.vrc.model.dto.friend.FriendStatusDto;
import com.github.Ox0017.vrc.model.dto.general.PlatformDto;
import com.github.Ox0017.vrc.model.dto.general.ReleaseStatusDto;
import com.github.Ox0017.vrc.model.dto.notification.NotificationDto;
import com.github.Ox0017.vrc.model.dto.notification.NotificationTypeDto;
import com.github.Ox0017.vrc.model.dto.user.CurrentUserDto;
import com.github.Ox0017.vrc.model.dto.user.DeveloperTypeDto;
import com.github.Ox0017.vrc.model.dto.user.LimitedUserDto;
import com.github.Ox0017.vrc.model.dto.user.UserDto;
import com.github.Ox0017.vrc.model.dto.world.LimitedWorldDto;
import com.github.Ox0017.vrc.model.dto.world.WorldDto;
import com.github.Ox0017.vrc.model.parameter.*;
import com.github.Ox0017.vrc.model.parameter.options.OrderOption;
import com.github.Ox0017.vrc.model.parameter.options.SortOption;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VRChatApiClientImplTest extends TestSupport {

	private static final String AUTH_VALUE = "authcookie_xxxx";
	private static final String AUTH_COOKIE = "Set-Cookie: auth=" + AUTH_VALUE;

	private static final String API_KEY_VALUE = "apiKey_xxxx";
	private static final String API_KEY_COOKIE = "Set-Cookie: apiKey=" + API_KEY_VALUE;

	@Mock
	private HttpClient httpClient;

	private VRChatApiClient client;

	@Captor
	private ArgumentCaptor<HttpUriRequest> requestCaptor;

	@Captor
	private ArgumentCaptor<HttpEntityEnclosingRequestBase> bodyRequestCaptor;

	@Before
	public void before() {
		this.client = new VRChatApiClientImpl(this.httpClient);
	}

	@Ignore
	@Test
	public void integrationTest() {
		// given
		final VRChatApiClient realClient = new VRChatApiClientImpl();
		final VrcRequestContext vrcRequestContext = vrcRequestContext();
		vrcRequestContext.setUsername("");
		vrcRequestContext.setPassword("");

		// when
		final CurrentUserDto currentUserDto = realClient.getCurrentUser(vrcRequestContext);
		final List<FavoriteDto> favoriteAvatars = realClient.getFavorites(vrcRequestContext, FavoriteParameters.Builder.create()
				.offset(1)
				.amount(50)
				.type(FavoriteTypeDto.AVATAR)
				.build());
		final boolean logoutSuccess = realClient.logout(vrcRequestContext);

		// then
		assertNotNull(currentUserDto);
		assertNotNull(favoriteAvatars);
		assertTrue(logoutSuccess);
	}

	@Test
	public void testGetRemoteConfig() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContext();

		final String json = this.readJson("remoteConfig.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final RemoteConfigDto remoteConfigDto = this.client.getRemoteConfig(vrcRequestContext);

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/config", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNotNull(remoteConfigDto);
		assertEquals("apiKey", remoteConfigDto.getApiKey());
		assertEquals("clientApiKey", remoteConfigDto.getClientApiKey());
	}

	@Test
	public void testGetCurrentUser() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContext();

		final String json = this.readJson("currentUser.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final CurrentUserDto currentUserDto = this.client.getCurrentUser(vrcRequestContext);

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/auth/user", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNotNull(currentUserDto);
		assertEquals("usr_id", currentUserDto.getId());
	}

	@Test
	public void testGetCurrentUser_Unauthorized() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseUnauthorized());

		// when
		final CurrentUserDto currentUserDto = this.client.getCurrentUser(vrcRequestContext);

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/auth/user", this.requestCaptor.getValue().getURI().toString());

		assertFalse(vrcRequestContext.hasAuth());
		assertNull(currentUserDto);
	}

	@Test
	public void testVerifyAuth() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();
		vrcRequestContext.setAuth("another");

		final String json = this.readJson("auth.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true)); // 401 if invalid

		// when
		final boolean valid = this.client.verifyAuth(vrcRequestContext);

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/auth", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertTrue(valid);
	}

	@Test
	public void testGetFavorite() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("favorite.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final FavoriteDto favoriteDto = this.client.getFavorite(vrcRequestContext, "fvrt_xxx");

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/favorites/fvrt_xxx", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNotNull(favoriteDto);
		assertEquals("fvrt_xxxx", favoriteDto.getId());
		assertEquals("avtr_xxxx", favoriteDto.getFavoriteId());
	}

	@Test
	public void testGetFavorite_BadRequest() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseBadRequest());

		// when
		final FavoriteDto favoriteDto = this.client.getFavorite(vrcRequestContext, "fvrt_xxxx");

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/favorites/fvrt_xxxx", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNull(favoriteDto);
	}

	@Test
	public void testGetFavorites() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("favorites.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final List<FavoriteDto> favoriteDtos = this.client.getFavorites(vrcRequestContext);

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/favorites", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertThat(favoriteDtos).isNotNull().hasSize(3)
				.extracting(FavoriteDto::getId, FavoriteDto::getType, FavoriteDto::getFavoriteId)
				.containsExactly(
						tuple("fvrt_1", FavoriteTypeDto.AVATAR, "avtr_xxxx"),
						tuple("fvrt_2", FavoriteTypeDto.FRIEND, "usr_xxxx"),
						tuple("fvrt_3", FavoriteTypeDto.WORLD, "wrld_xxxx")
				);
	}

	@Test
	public void testGetFavorites_WithParameters() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("favorites.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final List<FavoriteDto> favoriteDtos = this.client.getFavorites(vrcRequestContext,
				FavoriteParameters.Builder.create()
						.amount(25)
						.type(FavoriteTypeDto.FRIEND)
						.offset(5)
						.build()
		);

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/favorites?type=friend&n=25&offset=5", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNotNull(favoriteDtos);
	}

	@Test
	public void testAddFavorite() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("favorite.json");
		when(this.httpClient.execute(this.bodyRequestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final FavoriteDto favoriteDto = this.client.addFavorite(vrcRequestContext, FavoriteTypeDto.AVATAR, "avtr_xxxx", "avatars1");

		// then
		assertEquals("POST", this.bodyRequestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/favorites", this.bodyRequestCaptor.getValue().getURI().toString());
		final String body = inputStreamToString(this.bodyRequestCaptor.getValue().getEntity().getContent());
		assertEquals("{\"type\":\"avatar\",\"favoriteId\":\"avtr_xxxx\",\"tags\":[\"avatars1\"]}", body); // FavoriteDto without id

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNotNull(favoriteDto);
		assertEquals("fvrt_xxxx", favoriteDto.getId());
		assertEquals("avtr_xxxx", favoriteDto.getFavoriteId());
	}

	@Test
	public void testDeleteFavorite() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk("", true));

		// when
		final boolean isDeleted = this.client.deleteFavorite(vrcRequestContext, "fvrt_xxxx"); // 404 if not favorited

		// then
		assertEquals("DELETE", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/favorites/fvrt_xxxx", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertTrue(isDeleted);
	}

	@Test
	public void testGetAvatar() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("avatar.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final AvatarDto avatarDto = this.client.getAvatar(vrcRequestContext, "avtr_xxxx");

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/avatars/avtr_xxxx", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNotNull(avatarDto);
		assertEquals("avtr_xxxx", avatarDto.getId());
	}

	@Test
	public void testSelectAvatar() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("currentUser.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final CurrentUserDto currentUserDto = this.client.selectAvatar(vrcRequestContext, "avtr_xxxx");

		// then
		assertEquals("PUT", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/avatars/avtr_xxxx/select", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNotNull(currentUserDto);
		assertEquals("usr_id", currentUserDto.getId());
		assertEquals("avtr_current", currentUserDto.getCurrentAvatar());
	}

	@Test
	public void testGetUserById() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("user.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final UserDto userDto = this.client.getUserById(vrcRequestContext, "usr_xxxx");

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/users/usr_xxxx", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNotNull(userDto);
		assertEquals("usr_id", userDto.getId());
		assertEquals("username", userDto.getUsername());
	}

	@Test
	public void testGetUserByName() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("user.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final UserDto userDto = this.client.getUserByName(vrcRequestContext, "username");

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/users/username/name", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNotNull(userDto);
		assertEquals("usr_id", userDto.getId());
		assertEquals("username", userDto.getUsername());
	}

	@Test
	public void testGetUsers() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("limitedUsers.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final List<LimitedUserDto> limitedUserDtos = this.client.getUsers(vrcRequestContext, UserParameters.Builder.create()
				.search("some name")
				.active(true)
				.developerType(DeveloperTypeDto.NONE)
				.amount(5)
				.offset(1)
				.build());

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/users/active?n=5&offset=1&developerType=none&search=some+name", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertThat(limitedUserDtos).isNotNull().hasSize(2)
				.extracting(LimitedUserDto::getId, LimitedUserDto::getUsername)
				.containsExactly(
						tuple("usr_xxxx", "username"),
						tuple("usr_yyyy", "username2")
				);
	}

	@Test
	public void testGetUsers_Minimal() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("limitedUsers.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final List<LimitedUserDto> limitedUserDtos = this.client.getUsers(vrcRequestContext, UserParameters.Builder.create().build());

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/users?n=10&offset=0", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertThat(limitedUserDtos).isNotNull().hasSize(2)
				.extracting(LimitedUserDto::getId, LimitedUserDto::getUsername)
				.containsExactly(
						tuple("usr_xxxx", "username"),
						tuple("usr_yyyy", "username2")
				);
	}

	@Test
	public void testGetFriends() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("limitedUsers.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final List<LimitedUserDto> limitedUserDtos = this.client.getFriends(vrcRequestContext, FriendParameters.Builder.create()
				.amount(5)
				.offset(1)
				.offline(true)
				.build());

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/auth/user/friends?n=5&offset=1&offline=true", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertThat(limitedUserDtos).isNotNull().hasSize(2)
				.extracting(LimitedUserDto::getId, LimitedUserDto::getUsername)
				.containsExactly(
						tuple("usr_xxxx", "username"),
						tuple("usr_yyyy", "username2")
				);
	}

	@Test
	public void testGetFriends_Minimal() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("limitedUsers.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final List<LimitedUserDto> limitedUserDtos = this.client.getFriends(vrcRequestContext, FriendParameters.Builder.create().build());

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/auth/user/friends?n=10&offset=0", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertThat(limitedUserDtos).isNotNull().hasSize(2)
				.extracting(LimitedUserDto::getId, LimitedUserDto::getUsername)
				.containsExactly(
						tuple("usr_xxxx", "username"),
						tuple("usr_yyyy", "username2")
				);
	}

	@Test
	public void testGetFriendStatus() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("friendStatus.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final FriendStatusDto friendStatusDto = this.client.getFriendStatus(vrcRequestContext, "usr_xxxx");

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/user/usr_xxxx/friendStatus", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNotNull(friendStatusDto);
		assertTrue(friendStatusDto.getFriend());
		assertFalse(friendStatusDto.getIncomingRequest());
		assertFalse(friendStatusDto.getOutgoingRequest());
	}

	@Test
	public void testSendFriendRequest() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("notification.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final NotificationDto notificationDto = this.client.sendFriendRequest(vrcRequestContext, "usr_xxxx");

		// then
		assertEquals("POST", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/user/usr_xxxx/friendRequest", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNotNull(notificationDto);
		assertEquals("not_xxxx", notificationDto.getId());
	}

	@Test
	public void testAcceptFriendRequest() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("notification.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final boolean success = this.client.acceptFriendRequest(vrcRequestContext, "not_xxxx");

		// then
		assertEquals("PUT", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/auth/user/notifications/not_xxxx/accept", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertTrue(success);
	}

	@Test
	public void testIgnoreFriendRequest() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("notification.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final NotificationDto notificationDto = this.client.ignoreFriendRequest(vrcRequestContext, "not_xxxx");

		// then
		assertEquals("PUT", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/auth/user/notifications/not_xxxx/hide", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNotNull(notificationDto);
		assertEquals("not_xxxx", notificationDto.getId());
	}

	@Test
	public void testRemoveFriend() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("success.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final boolean removed = this.client.removeFriend(vrcRequestContext, "usr_xxxx");

		// then
		assertEquals("DELETE", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/auth/user/friends/usr_xxxx", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertTrue(removed);
	}

	@Test
	public void testGetNotifications() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("notifications.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final List<NotificationDto> notificationDtos = this.client.getNotifications(vrcRequestContext, NotificationParameters.Builder.create()
				.type(NotificationTypeDto.FRIEND_REQUEST)
				.sent(true)
				.dateAfter(OffsetDateTime.parse("2021-02-13T12:00:00+01"))
				.build());

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/auth/user/notifications?type=friendRequest&sent=true&date=2021-02-13T11%3A00%3A00Z", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertThat(notificationDtos).isNotNull();
	}

	@Test
	public void testGetNotifications_Minimal() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("notifications.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final List<NotificationDto> notificationDtos = this.client.getNotifications(vrcRequestContext, NotificationParameters.Builder.create().build());

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/auth/user/notifications", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertThat(notificationDtos).isNotNull().hasSize(6)
				.extracting(NotificationDto::getId, NotificationDto::getType)
				.containsExactly(
						tuple("not_1111", NotificationTypeDto.REQUEST_INVITE),
						tuple("not_2222", NotificationTypeDto.INVITE),
						tuple("not_3333", NotificationTypeDto.FRIEND_REQUEST),
						tuple("not_4444", NotificationTypeDto.FRIEND_REQUEST),
						tuple("not_5555", NotificationTypeDto.MESSAGE),
						tuple("not_6666", NotificationTypeDto.REQUEST_INVITE_RESPONSE)
				);
	}

	@Test
	public void testMarkNotificationAsRead() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("notification.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final NotificationDto notificationDto = this.client.markNotificationAsRead(vrcRequestContext, "not_xxxx");

		// then
		assertEquals("PUT", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/auth/user/notifications/not_xxxx/see", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNotNull(notificationDto);
		assertEquals("not_xxxx", notificationDto.getId());
	}

	@Test
	public void testGetWorldById() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("world.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final WorldDto worldDto = this.client.getWorldById(vrcRequestContext, "wrld_xxxx");

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/worlds/wrld_xxxx", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNotNull(worldDto);
		assertEquals("wrld_xxxx", worldDto.getId());
	}

	@Test
	public void testGetWorlds() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("limitedWorlds.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final List<LimitedWorldDto> limitedWorldDtos = this.client.getWorlds(vrcRequestContext, WorldParameters.Builder.create()
				.amount(5)
				.offset(1)
				.featured(false)
				.sort(SortOption.UPDATED)
				.user("user")
				.userId("usr_xxxx")
				.order(OrderOption.DESCENDING)
				.search("text")
				.tagInclude("include")
				.tagExclude("exclude")
				.releaseStatus(ReleaseStatusDto.PUBLIC)
				.platformDto(PlatformDto.WINDOWS)
				.build());

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/worlds?n=5&offset=1&featured=false&sort=updated&user=user&userId=usr_xxxx&order=descending&search=text&tag=include&notag=exclude&releaseStatus=public&platform=standalonewindows", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertThat(limitedWorldDtos).isNotNull().hasSize(2)
				.extracting(LimitedWorldDto::getId, LimitedWorldDto::getName)
				.containsExactly(
						tuple("wrld_xxxx", "World Name"),
						tuple("wrld_yyyy", "World Name 2")
				);
	}

	@Test
	public void testGetWorlds_Minimal() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("limitedWorlds.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final List<LimitedWorldDto> limitedWorldDtos = this.client.getWorlds(vrcRequestContext, WorldParameters.Builder.create().build());

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/worlds?n=10&offset=0", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertThat(limitedWorldDtos).isNotNull().hasSize(2)
				.extracting(LimitedWorldDto::getId, LimitedWorldDto::getName)
				.containsExactly(
						tuple("wrld_xxxx", "World Name"),
						tuple("wrld_yyyy", "World Name 2")
				);
	}

	@Test
	public void testGetWorlds_Active() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("limitedWorlds.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final List<LimitedWorldDto> limitedWorldDtos = this.client.getWorlds(vrcRequestContext, WorldParameters.Builder.create().inActiveWorlds().build());

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/worlds/active?n=10&offset=0", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertThat(limitedWorldDtos).isNotNull().hasSize(2)
				.extracting(LimitedWorldDto::getId, LimitedWorldDto::getName)
				.containsExactly(
						tuple("wrld_xxxx", "World Name"),
						tuple("wrld_yyyy", "World Name 2")
				);
	}

	@Test
	public void testGetWorlds_Recent() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("limitedWorlds.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final List<LimitedWorldDto> limitedWorldDtos = this.client.getWorlds(vrcRequestContext, WorldParameters.Builder.create().inRecentlyVisitedWorlds().build());

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/worlds/recent?n=10&offset=0", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertThat(limitedWorldDtos).isNotNull().hasSize(2)
				.extracting(LimitedWorldDto::getId, LimitedWorldDto::getName)
				.containsExactly(
						tuple("wrld_xxxx", "World Name"),
						tuple("wrld_yyyy", "World Name 2")
				);
	}

	@Test
	public void testGetWorlds_Favorite() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		final String json = this.readJson("limitedWorlds.json");
		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk(json, true));

		// when
		final List<LimitedWorldDto> limitedWorldDtos = this.client.getWorlds(vrcRequestContext, WorldParameters.Builder.create().inFavoriteWorlds().build());

		// then
		assertEquals("GET", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/worlds/favorites?n=10&offset=0", this.requestCaptor.getValue().getURI().toString());

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertThat(limitedWorldDtos).isNotNull().hasSize(2)
				.extracting(LimitedWorldDto::getId, LimitedWorldDto::getName)
				.containsExactly(
						tuple("wrld_xxxx", "World Name"),
						tuple("wrld_yyyy", "World Name 2")
				);
	}

	@Test
	public void testLogout() throws IOException {
		// given
		final VrcRequestContext vrcRequestContext = vrcRequestContextWithSession();

		when(this.httpClient.execute(this.requestCaptor.capture())).thenReturn(httpResponseOk("success", false));

		// when
		final boolean success = this.client.logout(vrcRequestContext);

		// then
		assertEquals("PUT", this.requestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/logout", this.requestCaptor.getValue().getURI().toString());

		assertTrue(success);
		assertFalse(vrcRequestContext.hasAuth());
	}

	private static VrcRequestContext vrcRequestContext() {
		final VrcRequestContext vrcRequestContext = new VrcRequestContext();
		vrcRequestContext.setUsername("username");
		vrcRequestContext.setPassword("password");
		return vrcRequestContext;
	}

	private static VrcRequestContext vrcRequestContextWithSession() {
		final VrcRequestContext vrcRequestContext = vrcRequestContext();
		vrcRequestContext.setAuth(AUTH_VALUE);
		vrcRequestContext.setApiKey(API_KEY_VALUE);
		return vrcRequestContext;
	}

	private static HttpResponse httpResponseOk(final String body, final boolean provideAuthHeaders) {
		final HttpResponse response = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK"));
		if (provideAuthHeaders) {
			final Header[] headers = new BufferedHeader[]{
					new BufferedHeader(stringToCharBuffer(AUTH_COOKIE)),
					new BufferedHeader(stringToCharBuffer(API_KEY_COOKIE))
			};
			response.setHeaders(headers);
		}
		final BasicHttpEntity entity = new BasicHttpEntity();
		if (body != null) {
			entity.setContent(IOUtils.toInputStream(body, StandardCharsets.UTF_8));
		}
		response.setEntity(entity);
		return response;
	}

	private static CharArrayBuffer stringToCharBuffer(final String s) {
		final CharArrayBuffer buffer = new CharArrayBuffer(s.length());
		buffer.append(s);
		return buffer;
	}

	private static String inputStreamToString(final InputStream inputStream) throws IOException {
		return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
	}

	private static HttpResponse httpResponseUnauthorized() {
		return new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 401, "Unauthorized"));
	}

	private static HttpResponse httpResponseBadRequest() {
		return new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 400, "Bad Request"));
	}

}
