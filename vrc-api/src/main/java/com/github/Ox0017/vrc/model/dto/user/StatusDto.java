package com.github.Ox0017.vrc.model.dto.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Ox0017.vrc.model.dto.serialization.deserialize.StatusDtoDeserializer;
import com.github.Ox0017.vrc.model.dto.serialization.serialize.StatusDtoSerializer;

import java.util.Locale;

@JsonSerialize(using = StatusDtoSerializer.class)
@JsonDeserialize(using = StatusDtoDeserializer.class)
public enum StatusDto {

	UNKNOWN,
	ACTIVE,
	JOIN_ME("join me"),
	ASK_ME("ask me"),
	BUSY,
	OFFLINE;

	private final String value;

	StatusDto() {
		this.value = this.name().toLowerCase(Locale.ENGLISH);
	}

	StatusDto(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static StatusDto fromString(final String value) {
		if (value == null) {
			return null;
		}

		for (final StatusDto statusDto : StatusDto.values()) {
			if (statusDto.name().equals(value) || statusDto.getValue().equals(value)) {
				return statusDto;
			}
		}
		return UNKNOWN;
	}

}
