package com.github.Ox0017.vrc.model.dto.serialization.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {

	@Override
	public OffsetDateTime deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
		try {
			return OffsetDateTime.parse(jsonParser.getValueAsString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		}
		catch (final DateTimeParseException ex) {
			// no-op
		}
		try {
			return OffsetDateTime.of(LocalDate.parse(jsonParser.getValueAsString()), LocalTime.MIN, ZoneOffset.UTC);
		}
		catch (final DateTimeParseException ex) {
			// no-op
		}
		return null;
	}

}
