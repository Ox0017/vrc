package com.github.Ox0017.vrc.model.dto.world;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.Ox0017.vrc.model.dto.general.PlatformDto;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstanceDto {

	@JsonProperty("id")
	private String id;

	@JsonProperty("instanceId")
	private String instanceId;

	@JsonProperty("location")
	private String location;

	@JsonProperty("name")
	private String name;

	@JsonProperty("shortName")
	private String shortName;

	@JsonProperty("worldId")
	private String worldId;

	@JsonProperty("type")
	private InstanceTypeDto type;

	@JsonProperty("active")
	private Boolean active;

	@JsonProperty("canRequestInvite")
	private Boolean canRequestInvite;

	@JsonProperty("capacity")
	private Long capacity;

	@JsonProperty("clientNumber")
	private String clientNumber;

	@JsonProperty("full")
	private Boolean full;

	@JsonProperty("hidden")
	private String hidden;

	@JsonProperty("n_users")
	private Long usersCount;

	@JsonProperty("ownerId")
	private String ownerId;

	@JsonProperty("photonRegion")
	private String photonRegion;

	@JsonProperty("permanent")
	private Boolean permanent;

	@JsonProperty("platforms")
	private List<PlatformDto> platforms;

	@JsonProperty("tags")
	private List<String> tags;

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getInstanceId() {
		return this.instanceId;
	}

	public void setInstanceId(final String instanceId) {
		this.instanceId = instanceId;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(final String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(final String shortName) {
		this.shortName = shortName;
	}

	public String getWorldId() {
		return this.worldId;
	}

	public void setWorldId(final String worldId) {
		this.worldId = worldId;
	}

	public InstanceTypeDto getType() {
		return this.type;
	}

	public void setType(final InstanceTypeDto type) {
		this.type = type;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(final Boolean active) {
		this.active = active;
	}

	public Boolean getCanRequestInvite() {
		return this.canRequestInvite;
	}

	public void setCanRequestInvite(final Boolean canRequestInvite) {
		this.canRequestInvite = canRequestInvite;
	}

	public Long getCapacity() {
		return this.capacity;
	}

	public void setCapacity(final Long capacity) {
		this.capacity = capacity;
	}

	public String getClientNumber() {
		return this.clientNumber;
	}

	public void setClientNumber(final String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public Boolean getFull() {
		return this.full;
	}

	public void setFull(final Boolean full) {
		this.full = full;
	}

	public String getHidden() {
		return this.hidden;
	}

	public void setHidden(final String hidden) {
		this.hidden = hidden;
	}

	public Long getUsersCount() {
		return this.usersCount;
	}

	public void setUsersCount(final Long usersCount) {
		this.usersCount = usersCount;
	}

	public String getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(final String ownerId) {
		this.ownerId = ownerId;
	}

	public String getPhotonRegion() {
		return this.photonRegion;
	}

	public void setPhotonRegion(final String photonRegion) {
		this.photonRegion = photonRegion;
	}

	public Boolean getPermanent() {
		return this.permanent;
	}

	public void setPermanent(final Boolean permanent) {
		this.permanent = permanent;
	}

	public List<PlatformDto> getPlatforms() {
		return this.platforms;
	}

	public void setPlatforms(final List<PlatformDto> platforms) {
		this.platforms = platforms;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public void setTags(final List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("InstanceDto [");
		sb.append("id='").append(this.id).append('\'');
		sb.append(", instanceId='").append(this.instanceId).append('\'');
		sb.append(", location='").append(this.location).append('\'');
		sb.append(", name='").append(this.name).append('\'');
		sb.append(", shortName='").append(this.shortName).append('\'');
		sb.append(", worldId='").append(this.worldId).append('\'');
		sb.append(", type=").append(this.type);
		sb.append(", active=").append(this.active);
		sb.append(", canRequestInvite=").append(this.canRequestInvite);
		sb.append(", capacity=").append(this.capacity);
		sb.append(", clientNumber='").append(this.clientNumber).append('\'');
		sb.append(", full=").append(this.full);
		sb.append(", hidden='").append(this.hidden).append('\'');
		sb.append(", usersCount=").append(this.usersCount);
		sb.append(", ownerId='").append(this.ownerId).append('\'');
		sb.append(", photonRegion='").append(this.photonRegion).append('\'');
		sb.append(", permanent=").append(this.permanent);
		sb.append(", platforms=").append(this.platforms);
		sb.append(", tags=").append(this.tags);
		sb.append(']');
		return sb.toString();
	}

}
