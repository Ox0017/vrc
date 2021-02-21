package com.github.Ox0017.vrc.model.dto.world;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Ox0017.vrc.model.dto.avatar.UnityPackageDto;
import com.github.Ox0017.vrc.model.dto.general.ReleaseStatusDto;
import com.github.Ox0017.vrc.model.dto.serialization.deserialize.OffsetDateTimeDeserializer;
import com.github.Ox0017.vrc.model.dto.serialization.serialize.OffsetDateTimeSerializer;

import java.time.OffsetDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LimitedWorldDto {

	@JsonProperty("id")
	private String id;

	@JsonProperty("authorId")
	private String authorId;

	@JsonProperty("authorName")
	private String authorName;

	@JsonProperty("capacity")
	private Long capacity;

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

	@JsonProperty("favorites")
	private Long favorites;

	@JsonProperty("heat")
	private Long heat;

	@JsonProperty("imageUrl")
	private String imageUrl;

	@JsonProperty("name")
	private String name;

	@JsonProperty("occupants")
	private Long occupants;

	@JsonProperty("organization")
	private String organization;

	@JsonProperty("popularity")
	private Long popularity;

	@JsonProperty("releaseStatus")
	private ReleaseStatusDto releaseStatus;

	@JsonProperty("tags")
	private List<String> tags;

	@JsonProperty("thumbnailImageUrl")
	private String thumbnailImageUrl;

	@JsonProperty("unityPackages")
	private List<UnityPackageDto> unityPackages;

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
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

	public Long getCapacity() {
		return this.capacity;
	}

	public void setCapacity(final Long capacity) {
		this.capacity = capacity;
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

	public Long getFavorites() {
		return this.favorites;
	}

	public void setFavorites(final Long favorites) {
		this.favorites = favorites;
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

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
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

	public Long getPopularity() {
		return this.popularity;
	}

	public void setPopularity(final Long popularity) {
		this.popularity = popularity;
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

	public List<UnityPackageDto> getUnityPackages() {
		return this.unityPackages;
	}

	public void setUnityPackages(final List<UnityPackageDto> unityPackages) {
		this.unityPackages = unityPackages;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("LimitedWorldDto [");
		sb.append("id='").append(this.id).append('\'');
		sb.append(", authorId='").append(this.authorId).append('\'');
		sb.append(", authorName='").append(this.authorName).append('\'');
		sb.append(", capacity=").append(this.capacity);
		sb.append(", createdAt=").append(this.createdAt);
		sb.append(", updatedAt=").append(this.updatedAt);
		sb.append(", publicationDate=").append(this.publicationDate);
		sb.append(", labsPublicationDate=").append(this.labsPublicationDate);
		sb.append(", favorites=").append(this.favorites);
		sb.append(", heat=").append(this.heat);
		sb.append(", imageUrl='").append(this.imageUrl).append('\'');
		sb.append(", name='").append(this.name).append('\'');
		sb.append(", occupants=").append(this.occupants);
		sb.append(", organization='").append(this.organization).append('\'');
		sb.append(", popularity=").append(this.popularity);
		sb.append(", releaseStatus=").append(this.releaseStatus);
		sb.append(", tags=").append(this.tags);
		sb.append(", thumbnailImageUrl='").append(this.thumbnailImageUrl).append('\'');
		sb.append(", unityPackages=").append(this.unityPackages);
		sb.append(']');
		return sb.toString();
	}

}
