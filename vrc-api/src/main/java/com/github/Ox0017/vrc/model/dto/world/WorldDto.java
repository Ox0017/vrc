package com.github.Ox0017.vrc.model.dto.world;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Ox0017.vrc.model.dto.avatar.UnityPackageDto;
import com.github.Ox0017.vrc.model.dto.general.ReleaseStatusDto;
import com.github.Ox0017.vrc.model.dto.general.UnknownDto;
import com.github.Ox0017.vrc.model.dto.serialization.deserialize.OffsetDateTimeDeserializer;
import com.github.Ox0017.vrc.model.dto.serialization.serialize.OffsetDateTimeSerializer;

import java.time.OffsetDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorldDto {

	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("assetUrl")
	private String assetUrl;

	@JsonProperty("assetUrlObject")
	private UnknownDto assetUrlObject;

	@JsonProperty("authorId")
	private String authorId;

	@JsonProperty("authorName")
	private String authorName;

	@JsonSerialize(using = OffsetDateTimeSerializer.class)
	@JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	@JsonProperty("created_at")
	private OffsetDateTime createdAt;

	@JsonSerialize(using = OffsetDateTimeSerializer.class)
	@JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	@JsonProperty("updated_at")
	private OffsetDateTime updatedAt;

	@JsonSerialize(using = OffsetDateTimeSerializer.class)
	@JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	@JsonProperty("publicationDate")
	private OffsetDateTime publicationDate;

	@JsonSerialize(using = OffsetDateTimeSerializer.class)
	@JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	@JsonProperty("labsPublicationDate")
	private OffsetDateTime labsPublicationDate;

	@JsonProperty("description")
	private String description;

	@JsonProperty("capacity")
	private String capacity;

	@JsonProperty("favorites")
	private Long favorites;

	@JsonProperty("featured")
	private Boolean featured;

	@JsonProperty("heat")
	private Long heat;

	@JsonProperty("imageUrl")
	private String imageUrl;

	@JsonProperty("instances")
	private List<List<String>> instances;

	@JsonProperty("namespace")
	private String namespace;

	@JsonProperty("occupants")
	private Long occupants;

	@JsonProperty("organization")
	private String organization;

	@JsonProperty("pluginUrl")
	private String pluginUrl;

	@JsonProperty("pluginUrlObject")
	private UnknownDto pluginUrlObject;

	@JsonProperty("popularity")
	private Long popularity;

	@JsonProperty("publicOccupants")
	private Long publicOccupants;

	@JsonProperty("releaseStatus")
	private ReleaseStatusDto releaseStatus;

	@JsonProperty("tags")
	private List<String> tags;

	@JsonProperty("thumbnailImageUrl")
	private String thumbnailImageUrl;

	@JsonProperty("unityPackageUrl")
	private String unityPackageUrl;

	@JsonProperty("unityPackageUrlObject")
	private UnknownDto unityPackageUrlObject;

	@JsonProperty("unityPackages")
	private List<UnityPackageDto> unityPackages;

	@JsonProperty("version")
	private Long version;

	@JsonProperty("visits")
	private Long visits;

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getAssetUrl() {
		return this.assetUrl;
	}

	public void setAssetUrl(final String assetUrl) {
		this.assetUrl = assetUrl;
	}

	public UnknownDto getAssetUrlObject() {
		return this.assetUrlObject;
	}

	public void setAssetUrlObject(final UnknownDto assetUrlObject) {
		this.assetUrlObject = assetUrlObject;
	}

	public String getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(final String authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return this.authorName;
	}

	public void setAuthorName(final String authorName) {
		this.authorName = authorName;
	}

	public OffsetDateTime getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(final OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public OffsetDateTime getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(final OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public OffsetDateTime getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(final OffsetDateTime publicationDate) {
		this.publicationDate = publicationDate;
	}

	public OffsetDateTime getLabsPublicationDate() {
		return this.labsPublicationDate;
	}

	public void setLabsPublicationDate(final OffsetDateTime labsPublicationDate) {
		this.labsPublicationDate = labsPublicationDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getCapacity() {
		return this.capacity;
	}

	public void setCapacity(final String capacity) {
		this.capacity = capacity;
	}

	public Long getFavorites() {
		return this.favorites;
	}

	public void setFavorites(final Long favorites) {
		this.favorites = favorites;
	}

	public Boolean getFeatured() {
		return this.featured;
	}

	public void setFeatured(final Boolean featured) {
		this.featured = featured;
	}

	public Long getHeat() {
		return this.heat;
	}

	public void setHeat(final Long heat) {
		this.heat = heat;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(final String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<List<String>> getInstances() {
		return this.instances;
	}

	public void setInstances(final List<List<String>> instances) {
		this.instances = instances;
	}

	public String getNamespace() {
		return this.namespace;
	}

	public void setNamespace(final String namespace) {
		this.namespace = namespace;
	}

	public Long getOccupants() {
		return this.occupants;
	}

	public void setOccupants(final Long occupants) {
		this.occupants = occupants;
	}

	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(final String organization) {
		this.organization = organization;
	}

	public String getPluginUrl() {
		return this.pluginUrl;
	}

	public void setPluginUrl(final String pluginUrl) {
		this.pluginUrl = pluginUrl;
	}

	public UnknownDto getPluginUrlObject() {
		return this.pluginUrlObject;
	}

	public void setPluginUrlObject(final UnknownDto pluginUrlObject) {
		this.pluginUrlObject = pluginUrlObject;
	}

	public Long getPopularity() {
		return this.popularity;
	}

	public void setPopularity(final Long popularity) {
		this.popularity = popularity;
	}

	public Long getPublicOccupants() {
		return this.publicOccupants;
	}

	public void setPublicOccupants(final Long publicOccupants) {
		this.publicOccupants = publicOccupants;
	}

	public ReleaseStatusDto getReleaseStatus() {
		return this.releaseStatus;
	}

	public void setReleaseStatus(final ReleaseStatusDto releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public void setTags(final List<String> tags) {
		this.tags = tags;
	}

	public String getThumbnailImageUrl() {
		return this.thumbnailImageUrl;
	}

	public void setThumbnailImageUrl(final String thumbnailImageUrl) {
		this.thumbnailImageUrl = thumbnailImageUrl;
	}

	public String getUnityPackageUrl() {
		return this.unityPackageUrl;
	}

	public void setUnityPackageUrl(final String unityPackageUrl) {
		this.unityPackageUrl = unityPackageUrl;
	}

	public UnknownDto getUnityPackageUrlObject() {
		return this.unityPackageUrlObject;
	}

	public void setUnityPackageUrlObject(final UnknownDto unityPackageUrlObject) {
		this.unityPackageUrlObject = unityPackageUrlObject;
	}

	public List<UnityPackageDto> getUnityPackages() {
		return this.unityPackages;
	}

	public void setUnityPackages(final List<UnityPackageDto> unityPackages) {
		this.unityPackages = unityPackages;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(final Long version) {
		this.version = version;
	}

	public Long getVisits() {
		return this.visits;
	}

	public void setVisits(final Long visits) {
		this.visits = visits;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("WorldDto [");
		sb.append("id='").append(this.id).append('\'');
		sb.append(", name='").append(this.name).append('\'');
		sb.append(", assetUrl='").append(this.assetUrl).append('\'');
		sb.append(", assetUrlObject=").append(this.assetUrlObject);
		sb.append(", authorId='").append(this.authorId).append('\'');
		sb.append(", authorName='").append(this.authorName).append('\'');
		sb.append(", createdAt=").append(this.createdAt);
		sb.append(", updatedAt=").append(this.updatedAt);
		sb.append(", publicationDate=").append(this.publicationDate);
		sb.append(", labsPublicationDate=").append(this.labsPublicationDate);
		sb.append(", description='").append(this.description).append('\'');
		sb.append(", capacity='").append(this.capacity).append('\'');
		sb.append(", favorites=").append(this.favorites);
		sb.append(", featured=").append(this.featured);
		sb.append(", heat=").append(this.heat);
		sb.append(", imageUrl='").append(this.imageUrl).append('\'');
		sb.append(", instances=").append(this.instances);
		sb.append(", namespace='").append(this.namespace).append('\'');
		sb.append(", occupants=").append(this.occupants);
		sb.append(", organization='").append(this.organization).append('\'');
		sb.append(", pluginUrl='").append(this.pluginUrl).append('\'');
		sb.append(", pluginUrlObject=").append(this.pluginUrlObject);
		sb.append(", popularity=").append(this.popularity);
		sb.append(", publicOccupants=").append(this.publicOccupants);
		sb.append(", releaseStatus=").append(this.releaseStatus);
		sb.append(", tags=").append(this.tags);
		sb.append(", thumbnailImageUrl='").append(this.thumbnailImageUrl).append('\'');
		sb.append(", unityPackageUrl='").append(this.unityPackageUrl).append('\'');
		sb.append(", unityPackageUrlObject=").append(this.unityPackageUrlObject);
		sb.append(", unityPackages=").append(this.unityPackages);
		sb.append(", version=").append(this.version);
		sb.append(", visits=").append(this.visits);
		sb.append(']');
		return sb.toString();
	}

}
