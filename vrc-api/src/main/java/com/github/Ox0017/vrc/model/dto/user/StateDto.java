package com.github.Ox0017.vrc.model.dto.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Ox0017.vrc.model.dto.serialization.deserialize.StateDtoDeserializer;
import com.github.Ox0017.vrc.model.dto.serialization.serialize.StateDtoSerializer;

import java.util.Locale;

@JsonSerialize(using = StateDtoSerializer.class)
@JsonDeserialize(using = StateDtoDeserializer.class)
public enum StateDto {

	UNKNOWN,
	ONLINE,
	ACTIVE,
	OFFLINE;

	private final String value;

	StateDto() {
		this.value = this.name().toLowerCase(Locale.ENGLISH);
	}

	public String getValue() {
		return this.value;
	}

	public static StateDto fromString(final String value) {
		if (value == null) {
			return null;
		}

		for (final StateDto stateDto : StateDto.values()) {
			if (stateDto.name().equals(value) || stateDto.getValue().equals(value)) {
				return stateDto;
			}
		}
		return UNKNOWN;
	}

}
