package com.github.Ox0017.vrc.model.dto.serialization.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.Ox0017.vrc.model.dto.world.InstanceTypeDto;

import java.io.IOException;

public class InstanceTypeDtoDeserializer extends JsonDeserializer<InstanceTypeDto> {

	@Override
	public InstanceTypeDto deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
		return InstanceTypeDto.fromString(jsonParser.getValueAsString());
	}

}
