package com.github.Ox0017.vrc;

import com.github.Ox0017.vrc.model.VrcRequestContext;
import com.github.Ox0017.vrc.model.dto.avatar.AvatarDto;
import com.github.Ox0017.vrc.model.dto.config.RemoteConfigDto;
import com.github.Ox0017.vrc.model.dto.favorite.FavoriteDto;
import com.github.Ox0017.vrc.model.dto.favorite.FavoriteTypeDto;
import com.github.Ox0017.vrc.model.dto.friend.FriendStatusDto;
import com.github.Ox0017.vrc.model.dto.notification.NotificationDto;
import com.github.Ox0017.vrc.model.dto.user.CurrentUserDto;
import com.github.Ox0017.vrc.model.dto.user.LimitedUserDto;
import com.github.Ox0017.vrc.model.dto.user.UserDto;
import com.github.Ox0017.vrc.model.parameter.FavoriteParameters;
import com.github.Ox0017.vrc.model.parameter.FriendParameters;
import com.github.Ox0017.vrc.model.parameter.NotificationParameters;
import com.github.Ox0017.vrc.model.parameter.UserParameters;

import java.util.List;

public interface VRChatApiClient {

	/**
	 * <p>Remote config contains configuration that the game clients needs to work properly.</p>
	 *
	 * @param vrcRequestContext No user credentials or session is needed, the context is enriched with apiKey from header
	 * @return the config with clientApiKey
	 */
	RemoteConfigDto getRemoteConfig(final VrcRequestContext vrcRequestContext);

	/**
	 * <p>Get user details for the given credentials or the current session and used for login.</p>
	 * <p>Creates a new session if only username and password is provided.</p>
	 *
	 * @param vrcRequestContext for login or to continue the current session if auth value is present.
	 *                          Automatically updates auth and apiKey values if a session was returned.
	 * @return current user object
	 */
	CurrentUserDto getCurrentUser(final VrcRequestContext vrcRequestContext);

	/**
	 * <p>Verifies whether the current session is valid</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @return whether or not the current auth state is valid
	 */
	boolean verifyAuth(final VrcRequestContext vrcRequestContext);

	/**
	 * <p>Gets the favorite data for a single favoriteId</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param favoriteId        example: fvrt_11111111-2222-aaaa-bbbb-333333333333
	 * @return favorite data including the id of the favorite item
	 */
	FavoriteDto getFavorite(final VrcRequestContext vrcRequestContext, final String favoriteId);

	/**
	 * <p>Get favorites including all types</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @return first 10 favorites including all types
	 */
	List<FavoriteDto> getFavorites(final VrcRequestContext vrcRequestContext);

	/**
	 * <p>Get favorites by given parameters</p>
	 *
	 * @param vrcRequestContext  session with auth and apiKey should already exist
	 * @param favoriteParameters to filter the result, for example only avatars.
	 *                           Use the builder methods to add the parameters: FavoriteParameters.Builder.create()
	 * @return list of selected favorites
	 */
	List<FavoriteDto> getFavorites(final VrcRequestContext vrcRequestContext, final FavoriteParameters favoriteParameters);

	/**
	 * <p>Add a new favorite</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param favoriteTypeDto   type of the favorite (UNKNOWN type is not supported)
	 * @param favoriteId        example: avtr_11111111-2222-aaaa-bbbb-333333333333
	 * @return favorite with id (fvrt_11111111-2222-aaaa-bbbb-333333333333)
	 */
	FavoriteDto addFavorite(final VrcRequestContext vrcRequestContext, final FavoriteTypeDto favoriteTypeDto, final String favoriteId);

	/**
	 * <p>Removes favorite by id</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param favoriteId        example: fvrt_11111111-2222-aaaa-bbbb-333333333333
	 * @return true if the request was successful
	 */
	boolean deleteFavorite(final VrcRequestContext vrcRequestContext, final String favoriteId);

	/**
	 * <p>Get avatar by id</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param avatarId          example: avtr_11111111-2222-aaaa-bbbb-333333333333
	 * @return details about the avatar
	 */
	AvatarDto getAvatar(final VrcRequestContext vrcRequestContext, final String avatarId);

	/**
	 * <p>Choose an avatar. This will work on any avatar (doesn't update in-game instantly)</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param avatarId          example: avtr_11111111-2222-aaaa-bbbb-333333333333
	 * @return the current user with the selected avatar
	 */
	CurrentUserDto selectAvatar(final VrcRequestContext vrcRequestContext, final String avatarId);

	/**
	 * <p>Get user by id</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param userId            usr_xxxx
	 * @return the public info about the user
	 */
	UserDto getUserById(final VrcRequestContext vrcRequestContext, final String userId);

	/**
	 * <p>Get user by name</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param userName          the lowercase in-game user name
	 * @return the public info about the user
	 */
	UserDto getUserByName(final VrcRequestContext vrcRequestContext, final String userName);

	/**
	 * <p>Get users by given parameters</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param userParameters    to filter the result, for example by user name or type.
	 *                          Use the builder methods to add the parameters: UserParameters.Builder.create()
	 * @return list of selected users
	 */
	List<LimitedUserDto> getUsers(final VrcRequestContext vrcRequestContext, final UserParameters userParameters);

	/**
	 * <p>Get friends by given parameters</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param friendParameters  to filter the result, for example include offline friends.
	 *                          Use the builder methods to add the parameters: FriendParameters.Builder.create()
	 * @return list of selected friends
	 */
	List<LimitedUserDto> getFriends(final VrcRequestContext vrcRequestContext, final FriendParameters friendParameters);

	/**
	 * <p>Get the friend status of a given user</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param userId            the user that should be checked
	 * @return the friend status
	 */
	FriendStatusDto getFriendStatus(final VrcRequestContext vrcRequestContext, final String userId);

	/**
	 * <p>Send a friend request to a given user</p>
	 * <p><b>Warning</b> from https://vrchatapi.github.io/#/NotificationAPI/GetAll<br>
	 * 'We tried to test these APIs and we are not sure how they work in-game,
	 * even friend request wasn't received unless we sent a real friend request
	 * and not a notification. Will have to do some further testing.'</p>
	 * <p>'It seems that the response will always be a notification response even if the receiver ID does not exists'
	 * (https://vrchatapi.github.io/#/UserAPI/FriendRequest)</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param userId            should be no friend, example: usr_11111111-2222-aaaa-bbbb-333333333333
	 * @return the created notification
	 */
	NotificationDto sendFriendRequest(final VrcRequestContext vrcRequestContext, final String userId);

	/**
	 * <p>Accept a friend request notification</p>
	 * <p><b>Warning</b> from https://vrchatapi.github.io/#/NotificationAPI/GetAll<br>
	 * 'We tried to test these APIs and we are not sure how they work in-game,
	 * even friend request wasn't received unless we sent a real friend request
	 * and not a notification. Will have to do some further testing.'</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param notificationId    should be the id of a notification of type FRIEND_REQUEST
	 *                          example: not_11111111-2222-aaaa-bbbb-333333333333
	 * @return true if the request was successful
	 */
	boolean acceptFriendRequest(final VrcRequestContext vrcRequestContext, final String notificationId);

	/**
	 * <p>Ignore a friend request, this deletes the notification</p>
	 * <p><b>Warning</b> from https://vrchatapi.github.io/#/NotificationAPI/GetAll<br>
	 * 'We tried to test these APIs and we are not sure how they work in-game,
	 * even friend request wasn't received unless we sent a real friend request
	 * and not a notification. Will have to do some further testing.'</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param notificationId    example: not_11111111-2222-aaaa-bbbb-333333333333
	 * @return the deleted friend request notification
	 */
	NotificationDto ignoreFriendRequest(final VrcRequestContext vrcRequestContext, final String notificationId);

	/**
	 * <p>Remove an existing friend</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param userId            should be a friend, example: usr_11111111-2222-aaaa-bbbb-333333333333
	 * @return true if the request was successful
	 */
	boolean removeFriend(final VrcRequestContext vrcRequestContext, final String userId);

	/**
	 * <p>Get notifications by given parameters</p>
	 * <p><b>Warning</b> from https://vrchatapi.github.io/#/NotificationAPI/GetAll<br>
	 * 'We tried to test these APIs and we are not sure how they work in-game,
	 * even friend request wasn't received unless we sent a real friend request
	 * and not a notification. Will have to do some further testing.'</p>
	 *
	 * @param vrcRequestContext      session with auth and apiKey should already exist
	 * @param notificationParameters to filter the result, for example only sent notifications.
	 *                               Use the builder methods to add the parameters: NotificationParameters.Builder.create()
	 * @return list of selected notifications
	 */
	List<NotificationDto> getNotifications(final VrcRequestContext vrcRequestContext, final NotificationParameters notificationParameters);

	/**
	 * <p>Mark a notification as read (seen) by id</p>
	 * <p><b>Warning</b> from https://vrchatapi.github.io/#/NotificationAPI/MarkAsSeen<br>
	 * 'We tried to test these APIs and we are not sure how they work in-game,
	 * even friend request wasn't received unless we sent a real friend request
	 * and not a notification. Will have to do some further testing.'</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param notificationId    example: not_11111111-2222-aaaa-bbbb-333333333333
	 * @return the modified notification
	 */
	NotificationDto markNotificationAsRead(final VrcRequestContext vrcRequestContext, final String notificationId);

	/**
	 * <p>Delete (hide) a notification by id</p>
	 * <p><b>Warning</b> from https://vrchatapi.github.io/#/NotificationAPI/Delete<br>
	 * We tried to test these APIs and we are not sure how they work in-game,
	 * even friend request wasn't received unless we sent a real friend request
	 * and not a notification. Will have to do some further testing.'</p>
	 *
	 * @param vrcRequestContext session with auth and apiKey should already exist
	 * @param notificationId    example: not_11111111-2222-aaaa-bbbb-333333333333
	 * @return the deleted notification
	 */
	NotificationDto deleteNotification(final VrcRequestContext vrcRequestContext, final String notificationId);

	/**
	 * <p>Invalidates the current session.</p>
	 *
	 * @param vrcRequestContext with existing auth value. Username and password are not required,
	 *                          removes the auth from the context
	 * @return true if the request was successful
	 */
	boolean logout(final VrcRequestContext vrcRequestContext);

}
