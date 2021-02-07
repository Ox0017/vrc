package com.github.Ox0017.vrc.model.dto.avatar;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Ox0017.vrc.model.dto.serialization.deserialize.PlatformDtoDeserializer;
import com.github.Ox0017.vrc.model.dto.serialization.serialize.PlatformDtoSerializer;

import java.util.Locale;

@JsonSerialize(using = PlatformDtoSerializer.class)
@JsonDeserialize(using = PlatformDtoDeserializer.class)
public enum PlatformDto {

	UNKNOWN,
	WINDOWS("standalonewindows"),
	ANDROID;

	private final String value;

	PlatformDto() {
		this.value = this.name().toLowerCase(Locale.ENGLISH);
	}

	PlatformDto(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static PlatformDto fromString(final String value) {
		if (value == null) {
			return null;
		}

		for (final PlatformDto platformDto : PlatformDto.values()) {
			if (platformDto.name().equals(value) || platformDto.getValue().equals(value)) {
				return platformDto;
			}
		}
		return UNKNOWN;
	}

}
