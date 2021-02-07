package com.github.Ox0017.vrc.model.dto.serialization.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.Ox0017.vrc.model.dto.user.DeveloperTypeDto;

import java.io.IOException;

public class DeveloperTypeDtoSerializer extends JsonSerializer<DeveloperTypeDto> {

	@Override
	public void serialize(final DeveloperTypeDto developerTypeDto, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(developerTypeDto != null ? developerTypeDto.getValue() : "null");
	}

}
