package com.github.Ox0017.vrc.impl;

import com.github.Ox0017.vrc.TestSupport;
import com.github.Ox0017.vrc.VRChatApiClient;
import com.github.Ox0017.vrc.model.FavoriteParameters;
import com.github.Ox0017.vrc.model.VrcRequestContext;
import com.github.Ox0017.vrc.model.dto.avatar.AvatarDto;
import com.github.Ox0017.vrc.model.dto.favorite.FavoriteDto;
import com.github.Ox0017.vrc.model.dto.favorite.FavoriteTypeDto;
import com.github.Ox0017.vrc.model.dto.user.CurrentUserDto;
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
import java.util.List;

import static junit.framework.TestCase.*;
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

		assertNotNull(favoriteDtos);
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
		final FavoriteDto favoriteDto = this.client.addFavorite(vrcRequestContext, FavoriteTypeDto.AVATAR, "avtr_xxxx");

		// then
		assertEquals("POST", this.bodyRequestCaptor.getValue().getMethod());
		assertEquals("https://api.vrchat.cloud/api/1/favorites", this.bodyRequestCaptor.getValue().getURI().toString());
		final String body = inputStreamToString(this.bodyRequestCaptor.getValue().getEntity().getContent());
		assertEquals("{\"type\":\"avatar\",\"favoriteId\":\"avtr_xxxx\",\"tags\":[\"\"]}", body); // FavoriteDto without id

		assertTrue(vrcRequestContext.hasAuth());
		assertEquals(AUTH_VALUE, vrcRequestContext.getAuth());
		assertEquals(API_KEY_VALUE, vrcRequestContext.getApiKey());

		assertNotNull(favoriteDto);
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
