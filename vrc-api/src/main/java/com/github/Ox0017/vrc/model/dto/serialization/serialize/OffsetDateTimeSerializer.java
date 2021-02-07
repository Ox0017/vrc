package com.github.Ox0017.vrc.model.dto.serialization.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {

	@Override
	public void serialize(final OffsetDateTime offsetDateTime, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(offsetDateTime != null ? DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC)) : "null");
	}

}
