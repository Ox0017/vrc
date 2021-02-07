package com.github.Ox0017.vrc.model.dto.serialization.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.Ox0017.vrc.model.dto.user.StatusDto;

import java.io.IOException;

public class StatusDtoSerializer extends JsonSerializer<StatusDto> {

	@Override
	public void serialize(final StatusDto statusDto, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(statusDto != null ? statusDto.getValue() : "null");
	}

}
