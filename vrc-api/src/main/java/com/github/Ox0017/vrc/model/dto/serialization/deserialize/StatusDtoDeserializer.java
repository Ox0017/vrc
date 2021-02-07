package com.github.Ox0017.vrc.model.dto.serialization.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.Ox0017.vrc.model.dto.user.StatusDto;

import java.io.IOException;

public class StatusDtoDeserializer extends JsonDeserializer<StatusDto> {

	@Override
	public StatusDto deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
		return StatusDto.fromString(jsonParser.getValueAsString());
	}

}
