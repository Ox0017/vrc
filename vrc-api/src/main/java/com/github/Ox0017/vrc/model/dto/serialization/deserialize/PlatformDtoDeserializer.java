package com.github.Ox0017.vrc.model.dto.serialization.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.Ox0017.vrc.model.dto.general.PlatformDto;

import java.io.IOException;

public class PlatformDtoDeserializer extends JsonDeserializer<PlatformDto> {

	@Override
	public PlatformDto deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
		return PlatformDto.fromString(jsonParser.getValueAsString());
	}

}
