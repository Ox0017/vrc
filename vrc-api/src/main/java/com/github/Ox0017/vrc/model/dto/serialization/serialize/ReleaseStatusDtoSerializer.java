package com.github.Ox0017.vrc.model.dto.serialization.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.Ox0017.vrc.model.dto.general.ReleaseStatusDto;

import java.io.IOException;

public class ReleaseStatusDtoSerializer extends JsonSerializer<ReleaseStatusDto> {

	@Override
	public void serialize(final ReleaseStatusDto releaseStatusDto, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(releaseStatusDto != null ? releaseStatusDto.getValue() : "null");
	}

}
