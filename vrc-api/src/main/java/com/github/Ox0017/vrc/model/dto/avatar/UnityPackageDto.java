package com.github.Ox0017.vrc.model.dto.avatar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Ox0017.vrc.model.dto.general.PlatformDto;
import com.github.Ox0017.vrc.model.dto.general.UnknownDto;
import com.github.Ox0017.vrc.model.dto.serialization.deserialize.OffsetDateTimeDeserializer;
import com.github.Ox0017.vrc.model.dto.serialization.serialize.OffsetDateTimeSerializer;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnityPackageDto {

	@JsonProperty("id")
	private String id;

	@JsonSerialize(using = OffsetDateTimeSerializer.class)
	@JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	@JsonProperty("created_at")
	private OffsetDateTime createdAt;

	@JsonProperty("assetVersion")
	private Long assetVersion;

	@JsonProperty("assetUrl")
	private String assetUrl;

	@JsonProperty("assetUrlObject")
	private UnknownDto assetUrlObject;

	@JsonProperty("pluginUrl")
	private String pluginUrl;

	@JsonProperty("pluginUrlObject")
	private UnknownDto pluginUrlObject;

	@JsonProperty("platform")
	private PlatformDto platform;

	@JsonProperty("unitySortNumber")
	private Long unitySortNumber;

	@JsonProperty("unityVersion")
	private String unityVersion;

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

	public Long getAssetVersion() {
		return this.assetVersion;
	}

	public void setAssetVersion(final Long assetVersion) {
		this.assetVersion = assetVersion;
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

	public PlatformDto getPlatform() {
		return this.platform;
	}

	public void setPlatform(final PlatformDto platform) {
		this.platform = platform;
	}

	public Long getUnitySortNumber() {
		return this.unitySortNumber;
	}

	public void setUnitySortNumber(final Long unitySortNumber) {
		this.unitySortNumber = unitySortNumber;
	}

	public String getUnityVersion() {
		return this.unityVersion;
	}

	public void setUnityVersion(final String unityVersion) {
		this.unityVersion = unityVersion;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UnityPackageDto [");
		sb.append("id='").append(this.id).append('\'');
		sb.append(", createdAt=").append(this.createdAt);
		sb.append(", assetVersion=").append(this.assetVersion);
		sb.append(", assetUrl='").append(this.assetUrl).append('\'');
		sb.append(", assetUrlObject=").append(this.assetUrlObject);
		sb.append(", pluginUrl='").append(this.pluginUrl).append('\'');
		sb.append(", pluginUrlObject=").append(this.pluginUrlObject);
		sb.append(", platform=").append(this.platform);
		sb.append(", unitySortNumber=").append(this.unitySortNumber);
		sb.append(", unityVersion='").append(this.unityVersion).append('\'');
		sb.append(']');
		return sb.toString();
	}

}
