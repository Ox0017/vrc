package com.github.Ox0017.vrc.model.dto.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicWorldRowDto {

	@JsonProperty("index")
	private Long index;

	@JsonProperty("name")
	private String name;

	@JsonProperty("sortHeading")
	private String sortHeading;

	@JsonProperty("sortOwnership")
	private String sortOwnership;

	@JsonProperty("sortOrder")
	private String sortOrder;

	@JsonProperty("platform")
	private String platform;

	@JsonProperty("tag")
	private String tag;

	public Long getIndex() {
		return this.index;
	}

	public void setIndex(final Long index) {
		this.index = index;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSortHeading() {
		return this.sortHeading;
	}

	public void setSortHeading(final String sortHeading) {
		this.sortHeading = sortHeading;
	}

	public String getSortOwnership() {
		return this.sortOwnership;
	}

	public void setSortOwnership(final String sortOwnership) {
		this.sortOwnership = sortOwnership;
	}

	public String getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(final String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(final String platform) {
		this.platform = platform;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(final String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("DynamicWorldRowDto [");
		sb.append("index=").append(this.index);
		sb.append(", name='").append(this.name).append('\'');
		sb.append(", sortHeading='").append(this.sortHeading).append('\'');
		sb.append(", sortOwnership='").append(this.sortOwnership).append('\'');
		sb.append(", sortOrder='").append(this.sortOrder).append('\'');
		sb.append(", platform='").append(this.platform).append('\'');
		sb.append(", tag='").append(this.tag).append('\'');
		sb.append(']');
		return sb.toString();
	}

}
