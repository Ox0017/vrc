package com.github.Ox0017.vrc.model.dto.serialization.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.Ox0017.vrc.model.dto.user.DeveloperTypeDto;

import java.io.IOException;

public class DeveloperTypeDtoDeserializer extends JsonDeserializer<DeveloperTypeDto> {

	@Override
	public DeveloperTypeDto deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
		return DeveloperTypeDto.fromString(jsonParser.getValueAsString());
	}

}
