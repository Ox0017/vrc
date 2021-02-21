package com.github.Ox0017.vrc.model.dto.serialization.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.Ox0017.vrc.model.dto.world.InstanceTypeDto;

import java.io.IOException;

public class InstanceTypeDtoSerializer extends JsonSerializer<InstanceTypeDto> {

	@Override
	public void serialize(final InstanceTypeDto instanceTypeDto, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(instanceTypeDto != null ? instanceTypeDto.getValue() : "null");
	}

}
