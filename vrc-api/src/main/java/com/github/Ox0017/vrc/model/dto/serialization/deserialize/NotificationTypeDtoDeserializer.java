package com.github.Ox0017.vrc.model.dto.serialization.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.Ox0017.vrc.model.dto.notification.NotificationTypeDto;

import java.io.IOException;

public class NotificationTypeDtoDeserializer extends JsonDeserializer<NotificationTypeDto> {

	@Override
	public NotificationTypeDto deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
		return NotificationTypeDto.fromString(jsonParser.getValueAsString());
	}

}
