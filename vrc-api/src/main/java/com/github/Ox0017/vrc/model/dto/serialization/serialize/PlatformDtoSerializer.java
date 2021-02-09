package com.github.Ox0017.vrc.model.dto.serialization.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.Ox0017.vrc.model.dto.PlatformDto;

import java.io.IOException;

public class PlatformDtoSerializer extends JsonSerializer<PlatformDto> {

	@Override
	public void serialize(final PlatformDto platformDto, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(platformDto != null ? platformDto.getValue() : "null");
	}

}
