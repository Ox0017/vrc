package com.github.Ox0017.vrc.model.dto.avatar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Ox0017.vrc.model.dto.general.ReleaseStatusDto;
import com.github.Ox0017.vrc.model.dto.general.UnknownDto;
import com.github.Ox0017.vrc.model.dto.serialization.deserialize.OffsetDateTimeDeserializer;
import com.github.Ox0017.vrc.model.dto.serialization.serialize.OffsetDateTimeSerializer;

import java.time.OffsetDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AvatarDto {

	@JsonProperty("id")
	private String id;

	@JsonSerialize(using = OffsetDateTimeSerializer.class)
	@JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	@JsonProperty("created_at")
	private OffsetDateTime createdAt;

	@JsonSerialize(using = OffsetDateTimeSerializer.class)
	@JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	@JsonProperty("updated_at")
	private OffsetDateTime updatedAt;

	@JsonProperty("version")
	private Long version;

	@JsonProperty("name")
	private String name;

	@JsonProperty("releaseStatus")
	private ReleaseStatusDto releaseStatus;

	@JsonProperty("assetUrl")
	private String assetUrl;

	@JsonProperty("assetUrlObject")
	private UnknownDto assetUrlObject;

	@JsonProperty("authorId")
	private String authorId;

	@JsonProperty("authorName")
	private String authorName;

	@JsonProperty("description")
	private String description;

	@JsonProperty("featured")
	private Boolean featured;

	@JsonProperty("imageUrl")
	private String imageUrl;

	@JsonProperty("thumbnailImageUrl")
	private String thumbnailImageUrl;

	@JsonProperty("tags")
	private List<String> tags;

	@JsonProperty("unityPackageUrl")
	private String unityPackageUrl;

	@JsonProperty("unityPackageUrlObject")
	private UnknownDto unityPackageUrlObject;

	@JsonProperty("unityPackages")
	private List<UnityPackageDto> unityPackages;

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
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

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(final Long version) {
		this.version = version;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public ReleaseStatusDto getReleaseStatus() {
		return this.releaseStatus;
	}

	public void setReleaseStatus(final ReleaseStatusDto releaseStatus) {
		this.releaseStatus = releaseStatus;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Boolean getFeatured() {
		return this.featured;
	}

	public void setFeatured(final Boolean featured) {
		this.featured = featured;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(final String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getThumbnailImageUrl() {
		return this.thumbnailImageUrl;
	}

	public void setThumbnailImageUrl(final String thumbnailImageUrl) {
		this.thumbnailImageUrl = thumbnailImageUrl;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public void setTags(final List<String> tags) {
		this.tags = tags;
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

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("AvatarDto [");
		sb.append("id='").append(this.id).append('\'');
		sb.append(", createdAt=").append(this.createdAt);
		sb.append(", updatedAt=").append(this.updatedAt);
		sb.append(", version=").append(this.version);
		sb.append(", name='").append(this.name).append('\'');
		sb.append(", releaseStatus=").append(this.releaseStatus);
		sb.append(", assetUrl='").append(this.assetUrl).append('\'');
		sb.append(", assetUrlObject=").append(this.assetUrlObject);
		sb.append(", authorId='").append(this.authorId).append('\'');
		sb.append(", authorName='").append(this.authorName).append('\'');
		sb.append(", description='").append(this.description).append('\'');
		sb.append(", featured=").append(this.featured);
		sb.append(", imageUrl='").append(this.imageUrl).append('\'');
		sb.append(", thumbnailImageUrl='").append(this.thumbnailImageUrl).append('\'');
		sb.append(", tags=").append(this.tags);
		sb.append(", unityPackageUrl='").append(this.unityPackageUrl).append('\'');
		sb.append(", unityPackageUrlObject=").append(this.unityPackageUrlObject);
		sb.append(", unityPackages=").append(this.unityPackages);
		sb.append(']');
		return sb.toString();
	}

}
