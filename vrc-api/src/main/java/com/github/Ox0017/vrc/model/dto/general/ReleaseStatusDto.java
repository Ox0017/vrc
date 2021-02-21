package com.github.Ox0017.vrc.model.dto.general;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Ox0017.vrc.model.dto.serialization.deserialize.ReleaseStatusDtoDeserializer;
import com.github.Ox0017.vrc.model.dto.serialization.serialize.ReleaseStatusDtoSerializer;

import java.util.Locale;

@JsonSerialize(using = ReleaseStatusDtoSerializer.class)
@JsonDeserialize(using = ReleaseStatusDtoDeserializer.class)
public enum ReleaseStatusDto {

	UNKNOWN,
	PUBLIC,
	PRIVATE,
	HIDDEN;

	private final String value;

	ReleaseStatusDto() {
		this.value = this.name().toLowerCase(Locale.ENGLISH);
	}

	public String getValue() {
		return this.value;
	}

	public static ReleaseStatusDto fromString(final String value) {
		if (value == null) {
			return null;
		}

		for (final ReleaseStatusDto releaseStatusDto : ReleaseStatusDto.values()) {
			if (releaseStatusDto.name().equals(value) || releaseStatusDto.getValue().equals(value)) {
				return releaseStatusDto;
			}
		}
		return UNKNOWN;
	}

}
