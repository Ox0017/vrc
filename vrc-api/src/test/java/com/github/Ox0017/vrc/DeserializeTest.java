package com.github.Ox0017.vrc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.Ox0017.vrc.model.dto.avatar.AvatarDto;
import com.github.Ox0017.vrc.model.dto.config.RemoteConfigDto;
import com.github.Ox0017.vrc.model.dto.favorite.FavoriteDto;
import com.github.Ox0017.vrc.model.dto.friend.FriendStatusDto;
import com.github.Ox0017.vrc.model.dto.notification.NotificationDto;
import com.github.Ox0017.vrc.model.dto.user.AuthDto;
import com.github.Ox0017.vrc.model.dto.user.CurrentUserDto;
import com.github.Ox0017.vrc.model.dto.user.LimitedUserDto;
import com.github.Ox0017.vrc.model.dto.user.UserDto;
import com.github.Ox0017.vrc.model.dto.world.LimitedWorldDto;
import com.github.Ox0017.vrc.model.dto.world.WorldDto;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertNotNull;

public class DeserializeTest extends TestSupport {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void testDeserialize_RemoteConfigDto() throws JsonProcessingException {
		// given
		final String json = this.readJson("remoteConfig.json");

		// when
		final RemoteConfigDto remoteConfigDto = this.objectMapper.readValue(json, RemoteConfigDto.class);

		// then
		assertNotNull(remoteConfigDto);
	}

	@Test
	public void testDeserialize_CurrentUserDto() throws JsonProcessingException {
		// given
		final String json = this.readJson("currentUser.json");

		// when
		final CurrentUserDto currentUserDto = this.objectMapper.readValue(json, CurrentUserDto.class);

		// then
		assertNotNull(currentUserDto);
	}

	@Test
	public void testDeserialize_AuthDto() throws JsonProcessingException {
		// given
		final String json = this.readJson("auth.json");

		// when
		final AuthDto authDto = this.objectMapper.readValue(json, AuthDto.class);

		// then
		assertNotNull(authDto);
	}

	@Test
	public void testDeserialize_FavoriteDto() throws JsonProcessingException {
		// given
		final String json = this.readJson("favorite.json");

		// when
		final FavoriteDto favoriteDto = this.objectMapper.readValue(json, FavoriteDto.class);

		// then
		assertNotNull(favoriteDto);
	}

	@Test
	public void testDeserialize_FavoriteDtos() throws JsonProcessingException {
		// given
		final String json = this.readJson("favorites.json");

		// when
		final List<FavoriteDto> favoriteDtos = Stream.of(this.objectMapper.readValue(json, FavoriteDto[].class)).collect(Collectors.toList());

		// then
		assertNotNull(favoriteDtos);
	}

	@Test
	public void testDeserialize_AvatarDto() throws JsonProcessingException {
		// given
		final String json = this.readJson("avatar.json");

		// when
		final AvatarDto avatarDto = this.objectMapper.readValue(json, AvatarDto.class);

		// then
		assertNotNull(avatarDto);
	}

	@Test
	public void testDeserialize_UserDto() throws JsonProcessingException {
		// given
		final String json = this.readJson("user.json");

		// when
		final UserDto userDto = this.objectMapper.readValue(json, UserDto.class);

		// then
		assertNotNull(userDto);
	}

	@Test
	public void testDeserialize_LimitedUserDtos() throws JsonProcessingException {
		// given
		final String json = this.readJson("limitedUsers.json");

		// when
		final List<LimitedUserDto> limitedUserDtos = Stream.of(this.objectMapper.readValue(json, LimitedUserDto[].class)).collect(Collectors.toList());

		// then
		assertNotNull(limitedUserDtos);
	}

	@Test
	public void testDeserialize_FriendStatusDto() throws JsonProcessingException {
		// given
		final String json = this.readJson("friendStatus.json");

		// when
		final FriendStatusDto friendStatusDto = this.objectMapper.readValue(json, FriendStatusDto.class);

		// then
		assertNotNull(friendStatusDto);
	}

	@Test
	public void testDeserialize_NotificationDtos() throws JsonProcessingException {
		// given
		final String json = this.readJson("notifications.json");

		// when
		final List<NotificationDto> notificationDtos = Stream.of(this.objectMapper.readValue(json, NotificationDto[].class)).collect(Collectors.toList());

		// then
		assertNotNull(notificationDtos);
	}

	@Test
	public void testDeserialize_NotificationDto() throws JsonProcessingException {
		// given
		final String json = this.readJson("notification.json");

		// when
		final NotificationDto notificationDto = this.objectMapper.readValue(json, NotificationDto.class);

		// then
		assertNotNull(notificationDto);
	}

	@Test
	public void testDeserialize_WorldDto() throws JsonProcessingException {
		// given
		final String json = this.readJson("world.json");

		// when
		final WorldDto worldDto = this.objectMapper.readValue(json, WorldDto.class);

		// then
		assertNotNull(worldDto);
	}

	@Test
	public void testDeserialize_LimitedWorldDtos() throws JsonProcessingException {
		// given
		final String json = this.readJson("limitedWorlds.json");

		// when
		final List<LimitedWorldDto> limitedWorldDtos = Stream.of(this.objectMapper.readValue(json, LimitedWorldDto[].class)).collect(Collectors.toList());

		// then
		assertNotNull(limitedWorldDtos);
	}

}
