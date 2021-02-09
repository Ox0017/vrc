package com.github.Ox0017.vrc.model.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Ox0017.vrc.model.dto.UnknownDto;
import com.github.Ox0017.vrc.model.dto.PlatformDto;
import com.github.Ox0017.vrc.model.dto.serialization.deserialize.OffsetDateTimeDeserializer;
import com.github.Ox0017.vrc.model.dto.serialization.serialize.OffsetDateTimeSerializer;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentUserDto {

	@JsonProperty("id")
	private String id;

	@JsonProperty("username")
	private String username;

	@JsonProperty("displayName")
	private String displayName;

	@JsonProperty("pastDisplayNames")
	private List<String> pastDisplayNames;

	@JsonProperty("bio")
	private String bio;

	@JsonProperty("bioLinks")
	private List<String> bioLinks;

	@JsonProperty("email")
	private String email;

	@JsonProperty("emailVerified")
	private Boolean emailVerified;

	@JsonProperty("hasEmail")
	private Boolean hasEmail;

	@JsonProperty("hasPendingEmail")
	private Boolean hasPendingEmail;

	@JsonProperty("obfuscatedEmail")
	private String obfuscatedEmail;

	@JsonProperty("obfuscatedPendingEmail")
	private String obfuscatedPendingEmail;

	@JsonProperty("steamId")
	private String steamId;

	@JsonProperty("steamDetails")
	private UnknownDto steamDetails;

	@JsonProperty("oculusId")
	private String oculusId;

	@JsonProperty("acceptedTOSVersion")
	private Long acceptedTOSVersion;

	@JsonProperty("hasBirthday")
	private Boolean hasBirthday;

	@JsonProperty("friends")
	private List<String> friends;

	@JsonProperty("onlineFriends")
	private List<String> onlineFriends;

	@JsonProperty("activeFriends")
	private List<String> activeFriends;

	@JsonProperty("offlineFriends")
	private List<String> offlineFriends;

	@JsonProperty("friendGroupNames")
	private List<String> friendGroupNames;

	@JsonProperty("state")
	private StateDto state;

	@JsonProperty("status")
	private StatusDto status;

	@JsonProperty("statusDescription")
	private String statusDescription;

	@JsonProperty("currentAvatar")
	private String currentAvatar;

	@JsonProperty("currentAvatarAssetUrl")
	private String currentAvatarAssetUrl;

	@JsonProperty("currentAvatarImageUrl")
	private String currentAvatarImageUrl;

	@JsonProperty("currentAvatarThumbnailImageUrl")
	private String currentAvatarThumbnailImageUrl;

	@JsonProperty("homeLocation")
	private String homeLocation;

	@JsonSerialize(using = OffsetDateTimeSerializer.class)
	@JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	@JsonProperty("last_login")
	private OffsetDateTime lastLogin;

	@JsonProperty("last_platform")
	private PlatformDto lastPlatform;

	@JsonProperty("hasLoggedInFromClient")
	private Boolean hasLoggedInFromClient;

	@JsonProperty("twoFactorAuthEnabled")
	private Boolean twoFactorAuthEnabled;

	@JsonProperty("allowAvatarCopying")
	private Boolean allowAvatarCopying;

	@JsonProperty("accountDeletionDate")
	private String accountDeletionDate;

	@JsonProperty("unsubscribe")
	private Boolean unsubscribe;

	@JsonProperty("tags")
	private List<String> tags;

	@JsonProperty("feature")
	private Map<String, String> feature;

	@JsonProperty("developerType")
	private DeveloperTypeDto developerType;

	@JsonProperty("isFriend")
	private Boolean isFriend;

	@JsonProperty("friendKey")
	private String friendKey;

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	public List<String> getPastDisplayNames() {
		return this.pastDisplayNames;
	}

	public void setPastDisplayNames(final List<String> pastDisplayNames) {
		this.pastDisplayNames = pastDisplayNames;
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(final String bio) {
		this.bio = bio;
	}

	public List<String> getBioLinks() {
		return this.bioLinks;
	}

	public void setBioLinks(final List<String> bioLinks) {
		this.bioLinks = bioLinks;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Boolean getEmailVerified() {
		return this.emailVerified;
	}

	public void setEmailVerified(final Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public Boolean getHasEmail() {
		return this.hasEmail;
	}

	public void setHasEmail(final Boolean hasEmail) {
		this.hasEmail = hasEmail;
	}

	public Boolean getHasPendingEmail() {
		return this.hasPendingEmail;
	}

	public void setHasPendingEmail(final Boolean hasPendingEmail) {
		this.hasPendingEmail = hasPendingEmail;
	}

	public String getObfuscatedEmail() {
		return this.obfuscatedEmail;
	}

	public void setObfuscatedEmail(final String obfuscatedEmail) {
		this.obfuscatedEmail = obfuscatedEmail;
	}

	public String getObfuscatedPendingEmail() {
		return this.obfuscatedPendingEmail;
	}

	public void setObfuscatedPendingEmail(final String obfuscatedPendingEmail) {
		this.obfuscatedPendingEmail = obfuscatedPendingEmail;
	}

	public String getSteamId() {
		return this.steamId;
	}

	public void setSteamId(final String steamId) {
		this.steamId = steamId;
	}

	public UnknownDto getSteamDetails() {
		return this.steamDetails;
	}

	public void setSteamDetails(final UnknownDto steamDetails) {
		this.steamDetails = steamDetails;
	}

	public String getOculusId() {
		return this.oculusId;
	}

	public void setOculusId(final String oculusId) {
		this.oculusId = oculusId;
	}

	public Long getAcceptedTOSVersion() {
		return this.acceptedTOSVersion;
	}

	public void setAcceptedTOSVersion(final Long acceptedTOSVersion) {
		this.acceptedTOSVersion = acceptedTOSVersion;
	}

	public Boolean getHasBirthday() {
		return this.hasBirthday;
	}

	public void setHasBirthday(final Boolean hasBirthday) {
		this.hasBirthday = hasBirthday;
	}

	public List<String> getFriends() {
		return this.friends;
	}

	public void setFriends(final List<String> friends) {
		this.friends = friends;
	}

	public List<String> getOnlineFriends() {
		return this.onlineFriends;
	}

	public void setOnlineFriends(final List<String> onlineFriends) {
		this.onlineFriends = onlineFriends;
	}

	public List<String> getActiveFriends() {
		return this.activeFriends;
	}

	public void setActiveFriends(final List<String> activeFriends) {
		this.activeFriends = activeFriends;
	}

	public List<String> getOfflineFriends() {
		return this.offlineFriends;
	}

	public void setOfflineFriends(final List<String> offlineFriends) {
		this.offlineFriends = offlineFriends;
	}

	public List<String> getFriendGroupNames() {
		return this.friendGroupNames;
	}

	public void setFriendGroupNames(final List<String> friendGroupNames) {
		this.friendGroupNames = friendGroupNames;
	}

	public StateDto getState() {
		return this.state;
	}

	public void setState(final StateDto state) {
		this.state = state;
	}

	public StatusDto getStatus() {
		return this.status;
	}

	public void setStatus(final StatusDto status) {
		this.status = status;
	}

	public String getStatusDescription() {
		return this.statusDescription;
	}

	public void setStatusDescription(final String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getCurrentAvatar() {
		return this.currentAvatar;
	}

	public void setCurrentAvatar(final String currentAvatar) {
		this.currentAvatar = currentAvatar;
	}

	public String getCurrentAvatarAssetUrl() {
		return this.currentAvatarAssetUrl;
	}

	public void setCurrentAvatarAssetUrl(final String currentAvatarAssetUrl) {
		this.currentAvatarAssetUrl = currentAvatarAssetUrl;
	}

	public String getCurrentAvatarImageUrl() {
		return this.currentAvatarImageUrl;
	}

	public void setCurrentAvatarImageUrl(final String currentAvatarImageUrl) {
		this.currentAvatarImageUrl = currentAvatarImageUrl;
	}

	public String getCurrentAvatarThumbnailImageUrl() {
		return this.currentAvatarThumbnailImageUrl;
	}

	public void setCurrentAvatarThumbnailImageUrl(final String currentAvatarThumbnailImageUrl) {
		this.currentAvatarThumbnailImageUrl = currentAvatarThumbnailImageUrl;
	}

	public String getHomeLocation() {
		return this.homeLocation;
	}

	public void setHomeLocation(final String homeLocation) {
		this.homeLocation = homeLocation;
	}

	public OffsetDateTime getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(final OffsetDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public PlatformDto getLastPlatform() {
		return this.lastPlatform;
	}

	public void setLastPlatform(final PlatformDto lastPlatform) {
		this.lastPlatform = lastPlatform;
	}

	public Boolean getHasLoggedInFromClient() {
		return this.hasLoggedInFromClient;
	}

	public void setHasLoggedInFromClient(final Boolean hasLoggedInFromClient) {
		this.hasLoggedInFromClient = hasLoggedInFromClient;
	}

	public Boolean getTwoFactorAuthEnabled() {
		return this.twoFactorAuthEnabled;
	}

	public void setTwoFactorAuthEnabled(final Boolean twoFactorAuthEnabled) {
		this.twoFactorAuthEnabled = twoFactorAuthEnabled;
	}

	public Boolean getAllowAvatarCopying() {
		return this.allowAvatarCopying;
	}

	public void setAllowAvatarCopying(final Boolean allowAvatarCopying) {
		this.allowAvatarCopying = allowAvatarCopying;
	}

	public String getAccountDeletionDate() {
		return this.accountDeletionDate;
	}

	public void setAccountDeletionDate(final String accountDeletionDate) {
		this.accountDeletionDate = accountDeletionDate;
	}

	public Boolean getUnsubscribe() {
		return this.unsubscribe;
	}

	public void setUnsubscribe(final Boolean unsubscribe) {
		this.unsubscribe = unsubscribe;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public void setTags(final List<String> tags) {
		this.tags = tags;
	}

	public Map<String, String> getFeature() {
		return this.feature;
	}

	public void setFeature(final Map<String, String> feature) {
		this.feature = feature;
	}

	public DeveloperTypeDto getDeveloperType() {
		return this.developerType;
	}

	public void setDeveloperType(final DeveloperTypeDto developerType) {
		this.developerType = developerType;
	}

	public Boolean getFriend() {
		return this.isFriend;
	}

	public void setFriend(final Boolean friend) {
		this.isFriend = friend;
	}

	public String getFriendKey() {
		return this.friendKey;
	}

	public void setFriendKey(final String friendKey) {
		this.friendKey = friendKey;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("CurrentUserDto [");
		sb.append("id='").append(this.id).append('\'');
		sb.append(", username='").append(this.username).append('\'');
		sb.append(", displayName='").append(this.displayName).append('\'');
		sb.append(", pastDisplayNames=").append(this.pastDisplayNames);
		sb.append(", bio='").append(this.bio).append('\'');
		sb.append(", bioLinks=").append(this.bioLinks);
		sb.append(", email='").append(this.email).append('\'');
		sb.append(", emailVerified=").append(this.emailVerified);
		sb.append(", hasEmail=").append(this.hasEmail);
		sb.append(", hasPendingEmail=").append(this.hasPendingEmail);
		sb.append(", obfuscatedEmail='").append(this.obfuscatedEmail).append('\'');
		sb.append(", obfuscatedPendingEmail='").append(this.obfuscatedPendingEmail).append('\'');
		sb.append(", steamId='").append(this.steamId).append('\'');
		sb.append(", steamDetails=").append(this.steamDetails);
		sb.append(", oculusId='").append(this.oculusId).append('\'');
		sb.append(", acceptedTOSVersion=").append(this.acceptedTOSVersion);
		sb.append(", hasBirthday=").append(this.hasBirthday);
		sb.append(", friends=").append(this.friends);
		sb.append(", onlineFriends=").append(this.onlineFriends);
		sb.append(", activeFriends=").append(this.activeFriends);
		sb.append(", offlineFriends=").append(this.offlineFriends);
		sb.append(", friendGroupNames=").append(this.friendGroupNames);
		sb.append(", state=").append(this.state);
		sb.append(", status=").append(this.status);
		sb.append(", statusDescription='").append(this.statusDescription).append('\'');
		sb.append(", currentAvatar='").append(this.currentAvatar).append('\'');
		sb.append(", currentAvatarAssetUrl='").append(this.currentAvatarAssetUrl).append('\'');
		sb.append(", currentAvatarImageUrl='").append(this.currentAvatarImageUrl).append('\'');
		sb.append(", currentAvatarThumbnailImageUrl='").append(this.currentAvatarThumbnailImageUrl).append('\'');
		sb.append(", homeLocation='").append(this.homeLocation).append('\'');
		sb.append(", lastLogin=").append(this.lastLogin);
		sb.append(", lastPlatform=").append(this.lastPlatform);
		sb.append(", hasLoggedInFromClient=").append(this.hasLoggedInFromClient);
		sb.append(", twoFactorAuthEnabled=").append(this.twoFactorAuthEnabled);
		sb.append(", allowAvatarCopying=").append(this.allowAvatarCopying);
		sb.append(", accountDeletionDate='").append(this.accountDeletionDate).append('\'');
		sb.append(", unsubscribe=").append(this.unsubscribe);
		sb.append(", tags=").append(this.tags);
		sb.append(", feature=").append(this.feature);
		sb.append(", developerType=").append(this.developerType);
		sb.append(", isFriend=").append(this.isFriend);
		sb.append(", friendKey='").append(this.friendKey).append('\'');
		sb.append(']');
		return sb.toString();
	}

}
