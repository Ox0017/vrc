package com.github.Ox0017.vrc.model.dto.favorite;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Ox0017.vrc.model.dto.serialization.deserialize.FavoriteTypeDtoDeserializer;
import com.github.Ox0017.vrc.model.dto.serialization.serialize.FavoriteTypeDtoSerializer;

import java.util.Locale;

@JsonSerialize(using = FavoriteTypeDtoSerializer.class)
@JsonDeserialize(using = FavoriteTypeDtoDeserializer.class)
public enum FavoriteTypeDto {

	UNKNOWN,
	WORLD,
	FRIEND,
	AVATAR;

	private final String value;

	FavoriteTypeDto() {
		this.value = this.name().toLowerCase(Locale.ENGLISH);
	}

	public String getValue() {
		return this.value;
	}

	public static FavoriteTypeDto fromString(final String value) {
		if (value == null) {
			return null;
		}

		for (final FavoriteTypeDto favoriteTypeDto : FavoriteTypeDto.values()) {
			if (favoriteTypeDto.name().equals(value) || favoriteTypeDto.getValue().equals(value)) {
				return favoriteTypeDto;
			}
		}
		return UNKNOWN;
	}

}
