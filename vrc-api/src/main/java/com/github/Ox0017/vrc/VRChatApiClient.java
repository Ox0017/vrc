package com.github.Ox0017.vrc;

import com.github.Ox0017.vrc.model.VrcRequestContext;
import com.github.Ox0017.vrc.model.dto.avatar.AvatarDto;
import com.github.Ox0017.vrc.model.dto.config.RemoteConfigDto;
import com.github.Ox0017.vrc.model.dto.favorite.FavoriteDto;
import com.github.Ox0017.vrc.model.dto.favorite.FavoriteTypeDto;
import com.github.Ox0017.vrc.model.dto.user.CurrentUserDto;
import com.github.Ox0017.vrc.model.dto.user.LimitedUserDto;
import com.github.Ox0017.vrc.model.dto.user.UserDto;
import com.github.Ox0017.vrc.model.parameter.FavoriteParameters;
import com.github.Ox0017.vrc.model.parameter.UserParameters;

import java.util.List;

public interface VRChatApiClient {

	/**
	 * Remote config contains configuration that the game clients needs to work properly.
	 *
	 * @param vrcRequestContext No user credentials or session is needed, the context is enriched with apiKey from header
	 * @return the config with clientApiKey
	 */
	RemoteConfigDto getRemoteConfig(final VrcRequestContext vrcRequestContext);

	/**
	 * Get user details for the given credentials or the current session and used for login.
	 * Creates a new session if only username and password is provided.
	 *
	 * @param vrcRequestContext for login or to continue the current session if auth value is present.
	 *                          Automatically updates auth and apiKey values if a session was returned.
	 * @return current user object
	 */
	CurrentUserDto getCurrentUser(final VrcRequestContext vrcRequestContext);

	/**
	 * Verifies whether the current session is valid
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @return whether or not the current auth state is valid
	 */
	boolean verifyAuth(final VrcRequestContext vrcRequestContext);

	/**
	 * Gets the favorite data for a single favoriteId
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param favoriteId        example: fvrt_11111111-2222-aaaa-bbbb-333333333333
	 * @return favorite data including the id of the favorite item
	 */
	FavoriteDto getFavorite(final VrcRequestContext vrcRequestContext, final String favoriteId);

	/**
	 * Get favorites including all types
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @return first 10 favorites including all types
	 */
	List<FavoriteDto> getFavorites(final VrcRequestContext vrcRequestContext);

	/**
	 * Get favorites by given parameters
	 *
	 * @param vrcRequestContext  session with auth and apiKey should already exist
	 * @param favoriteParameters to filter the result, for example only avatars
	 *                           use the builder methods to add the parameters: FavoriteParameters.Builder.create()
	 * @return list of selected favorites
	 */
	List<FavoriteDto> getFavorites(final VrcRequestContext vrcRequestContext, final FavoriteParameters favoriteParameters);

	/**
	 * Add a new favorite
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param favoriteTypeDto   type of the favorite (UNKNOWN type is not supported)
	 * @param favoriteId        example: avtr_11111111-2222-aaaa-bbbb-333333333333
	 * @return favorite with id (fvrt_11111111-2222-aaaa-bbbb-333333333333)
	 */
	FavoriteDto addFavorite(final VrcRequestContext vrcRequestContext, final FavoriteTypeDto favoriteTypeDto, final String favoriteId);

	/**
	 * Removes favorite by id
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param favoriteId        example: fvrt_11111111-2222-aaaa-bbbb-333333333333
	 * @return true if the request was successful
	 */
	boolean deleteFavorite(final VrcRequestContext vrcRequestContext, final String favoriteId);

	/**
	 * Get avatar by id
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param avatarId          example: avtr_11111111-2222-aaaa-bbbb-333333333333
	 * @return details about the avatar
	 */
	AvatarDto getAvatar(final VrcRequestContext vrcRequestContext, final String avatarId);

	/**
	 * Choose an avatar. This will work on any avatar (doesn't update in-game instantly)
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param avatarId          example: avtr_11111111-2222-aaaa-bbbb-333333333333
	 * @return the current user with the selected avatar
	 */
	CurrentUserDto selectAvatar(final VrcRequestContext vrcRequestContext, final String avatarId);

	/**
	 * Get user by id
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param userId            usr_xxxx
	 * @return the public info about the user
	 */
	UserDto getUserById(final VrcRequestContext vrcRequestContext, final String userId);

	/**
	 * Get user by name
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param userName          the lowercase in-game user name
	 * @return the public info about the user
	 */
	UserDto getUserByName(final VrcRequestContext vrcRequestContext, final String userName);

	/**
	 * Get users by given parameters
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param userParameters    to filter the result, for example by user name or type
	 *                          use the builder methods to add the parameters: UserParameters.Builder.create()
	 * @return list of selected users
	 */
	List<LimitedUserDto> getUsers(final VrcRequestContext vrcRequestContext, final UserParameters userParameters);

	/**
	 * Invalidates the current session.
	 *
	 * @param vrcRequestContext with existing auth value. Username and password are not required,
	 *                          removes the auth from the context
	 * @return true if the request was successful
	 */
	boolean logout(final VrcRequestContext vrcRequestContext);

}
