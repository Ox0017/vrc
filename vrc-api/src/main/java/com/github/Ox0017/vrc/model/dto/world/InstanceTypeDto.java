package com.github.Ox0017.vrc.model.dto.world;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Ox0017.vrc.model.dto.serialization.deserialize.InstanceTypeDtoDeserializer;
import com.github.Ox0017.vrc.model.dto.serialization.serialize.InstanceTypeDtoSerializer;

import java.util.Locale;

@JsonSerialize(using = InstanceTypeDtoSerializer.class)
@JsonDeserialize(using = InstanceTypeDtoDeserializer.class)
public enum InstanceTypeDto {

	UNKNOWN,
	HIDDEN,
	FRIENDS,
	PUBLIC;

	private final String value;

	InstanceTypeDto() {
		this.value = this.name().toLowerCase(Locale.ENGLISH);
	}

	public String getValue() {
		return this.value;
	}

	public static InstanceTypeDto fromString(final String value) {
		if (value == null) {
			return null;
		}

		for (final InstanceTypeDto instanceTypeDto : InstanceTypeDto.values()) {
			if (instanceTypeDto.name().equals(value) || instanceTypeDto.getValue().equals(value)) {
				return instanceTypeDto;
			}
		}
		return UNKNOWN;
	}

}
