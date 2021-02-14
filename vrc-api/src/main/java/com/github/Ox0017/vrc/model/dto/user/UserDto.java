package com.github.Ox0017.vrc.model.dto.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Ox0017.vrc.model.dto.general.PlatformDto;
import com.github.Ox0017.vrc.model.dto.serialization.deserialize.OffsetDateTimeDeserializer;
import com.github.Ox0017.vrc.model.dto.serialization.serialize.OffsetDateTimeSerializer;

import java.time.OffsetDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

	@JsonProperty("id")
	private String id;

	@JsonProperty("allowAvatarCopying")
	private Boolean allowAvatarCopying;

	@JsonProperty("bio")
	private String bio;

	@JsonProperty("bioLinks")
	private List<String> bioLinks;

	@JsonProperty("currentAvatarImageUrl")
	private String currentAvatarImageUrl;

	@JsonProperty("currentAvatarThumbnailImageUrl")
	private String currentAvatarThumbnailImageUrl;

	@JsonSerialize(using = OffsetDateTimeSerializer.class)
	@JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	@JsonProperty("date_joined")
	private OffsetDateTime dateJoined;

	@JsonProperty("developerType")
	private DeveloperTypeDto developerType;

	@JsonProperty("displayName")
	private String displayName;

	@JsonProperty("fallbackAvatar")
	private String fallbackAvatar;

	@JsonProperty("friendKey")
	private String friendKey;

	@JsonProperty("instanceId")
	private String instanceId;

	@JsonProperty("isFriend")
	private Boolean isFriend;

	@JsonSerialize(using = OffsetDateTimeSerializer.class)
	@JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	@JsonProperty("last_login")
	private OffsetDateTime lastLogin;

	@JsonProperty("last_platform")
	private PlatformDto lastPlatform;

	@JsonProperty("location")
	private String location;

	@JsonProperty("state")
	private String state;

	@JsonProperty("status")
	private String status;

	@JsonProperty("statusDescription")
	private String statusDescription;

	@JsonProperty("tags")
	private List<String> tags;

	@JsonProperty("userIcon")
	private String userIcon;

	@JsonProperty("username")
	private String username;

	@JsonProperty("worldId")
	private String worldId;

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Boolean getAllowAvatarCopying() {
		return this.allowAvatarCopying;
	}

	public void setAllowAvatarCopying(final Boolean allowAvatarCopying) {
		this.allowAvatarCopying = allowAvatarCopying;
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

	public OffsetDateTime getDateJoined() {
		return this.dateJoined;
	}

	public void setDateJoined(final OffsetDateTime dateJoined) {
		this.dateJoined = dateJoined;
	}

	public DeveloperTypeDto getDeveloperType() {
		return this.developerType;
	}

	public void setDeveloperType(final DeveloperTypeDto developerType) {
		this.developerType = developerType;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	public String getFallbackAvatar() {
		return this.fallbackAvatar;
	}

	public void setFallbackAvatar(final String fallbackAvatar) {
		this.fallbackAvatar = fallbackAvatar;
	}

	public String getFriendKey() {
		return this.friendKey;
	}

	public void setFriendKey(final String friendKey) {
		this.friendKey = friendKey;
	}

	public String getInstanceId() {
		return this.instanceId;
	}

	public void setInstanceId(final String instanceId) {
		this.instanceId = instanceId;
	}

	public Boolean getFriend() {
		return this.isFriend;
	}

	public void setFriend(final Boolean friend) {
		this.isFriend = friend;
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

	public String getLocation() {
		return this.location;
	}

	public void setLocation(final String location) {
		this.location = location;
	}

	public String getState() {
		return this.state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public String getStatusDescription() {
		return this.statusDescription;
	}

	public void setStatusDescription(final String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public void setTags(final List<String> tags) {
		this.tags = tags;
	}

	public String getUserIcon() {
		return this.userIcon;
	}

	public void setUserIcon(final String userIcon) {
		this.userIcon = userIcon;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getWorldId() {
		return this.worldId;
	}

	public void setWorldId(final String worldId) {
		this.worldId = worldId;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UserDto [");
		sb.append("id='").append(this.id).append('\'');
		sb.append(", allowAvatarCopying=").append(this.allowAvatarCopying);
		sb.append(", bio='").append(this.bio).append('\'');
		sb.append(", bioLinks=").append(this.bioLinks);
		sb.append(", currentAvatarImageUrl='").append(this.currentAvatarImageUrl).append('\'');
		sb.append(", currentAvatarThumbnailImageUrl='").append(this.currentAvatarThumbnailImageUrl).append('\'');
		sb.append(", dateJoined=").append(this.dateJoined);
		sb.append(", developerType=").append(this.developerType);
		sb.append(", displayName='").append(this.displayName).append('\'');
		sb.append(", fallbackAvatar='").append(this.fallbackAvatar).append('\'');
		sb.append(", friendKey='").append(this.friendKey).append('\'');
		sb.append(", instanceId='").append(this.instanceId).append('\'');
		sb.append(", isFriend=").append(this.isFriend);
		sb.append(", lastLogin=").append(this.lastLogin);
		sb.append(", lastPlatform=").append(this.lastPlatform);
		sb.append(", location='").append(this.location).append('\'');
		sb.append(", state='").append(this.state).append('\'');
		sb.append(", status='").append(this.status).append('\'');
		sb.append(", statusDescription='").append(this.statusDescription).append('\'');
		sb.append(", tags=").append(this.tags);
		sb.append(", userIcon='").append(this.userIcon).append('\'');
		sb.append(", username='").append(this.username).append('\'');
		sb.append(", worldId='").append(this.worldId).append('\'');
		sb.append(']');
		return sb.toString();
	}

}
