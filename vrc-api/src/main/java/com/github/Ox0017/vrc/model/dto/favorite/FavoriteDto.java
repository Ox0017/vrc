package com.github.Ox0017.vrc.model.dto.favorite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FavoriteDto {

	@JsonProperty("id")
	private String id;

	@JsonProperty("type")
	private FavoriteTypeDto type;

	@JsonProperty("favoriteId")
	private String favoriteId;

	@JsonProperty("tags")
	private List<String> tags;

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public FavoriteTypeDto getType() {
		return this.type;
	}

	public void setType(final FavoriteTypeDto type) {
		this.type = type;
	}

	public String getFavoriteId() {
		return this.favoriteId;
	}

	public void setFavoriteId(final String favoriteId) {
		this.favoriteId = favoriteId;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public void setTags(final List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("FavoriteDto [");
		sb.append("id='").append(this.id).append('\'');
		sb.append(", type=").append(this.type);
		sb.append(", favoriteId='").append(this.favoriteId).append('\'');
		sb.append(", tags=").append(this.tags);
		sb.append(']');
		return sb.toString();
	}

}
