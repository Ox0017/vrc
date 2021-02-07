package com.github.Ox0017.vrc.model.dto.serialization.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.Ox0017.vrc.model.dto.user.StateDto;

import java.io.IOException;

public class StateDtoDeserializer extends JsonDeserializer<StateDto> {

	@Override
	public StateDto deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
		return StateDto.fromString(jsonParser.getValueAsString());
	}

}
