package com.github.Ox0017.vrc.model.dto.serialization.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.Ox0017.vrc.model.dto.general.ReleaseStatusDto;

import java.io.IOException;

public class ReleaseStatusDtoDeserializer extends JsonDeserializer<ReleaseStatusDto> {

	@Override
	public ReleaseStatusDto deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
		return ReleaseStatusDto.fromString(jsonParser.getValueAsString());
	}

}
