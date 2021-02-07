package com.github.Ox0017.vrc.model.dto.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Ox0017.vrc.model.dto.serialization.deserialize.DeveloperTypeDtoDeserializer;
import com.github.Ox0017.vrc.model.dto.serialization.serialize.DeveloperTypeDtoSerializer;

import java.util.Locale;

@JsonSerialize(using = DeveloperTypeDtoSerializer.class)
@JsonDeserialize(using = DeveloperTypeDtoDeserializer.class)
public enum DeveloperTypeDto {

	UNKNOWN,
	NONE,
	TRUSTED,
	INTERNAL,
	MODERATOR;

	private final String value;

	DeveloperTypeDto() {
		this.value = this.name().toLowerCase(Locale.ENGLISH);
	}

	public String getValue() {
		return this.value;
	}

	public static DeveloperTypeDto fromString(final String value) {
		if (value == null) {
			return null;
		}

		for (final DeveloperTypeDto developerTypeDto : DeveloperTypeDto.values()) {
			if (developerTypeDto.name().equals(value) || developerTypeDto.getValue().equals(value)) {
				return developerTypeDto;
			}
		}
		return UNKNOWN;
	}

}
