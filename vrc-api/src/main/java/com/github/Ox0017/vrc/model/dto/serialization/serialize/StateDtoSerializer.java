package com.github.Ox0017.vrc.model.dto.serialization.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.Ox0017.vrc.model.dto.user.StateDto;

import java.io.IOException;

public class StateDtoSerializer extends JsonSerializer<StateDto> {

	@Override
	public void serialize(final StateDto stateDto, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(stateDto != null ? stateDto.getValue() : "null");
	}

}
