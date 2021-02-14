package com.github.Ox0017.vrc.model.dto.serialization.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.Ox0017.vrc.model.dto.notification.NotificationTypeDto;

import java.io.IOException;

public class NotificationTypeDtoSerializer extends JsonSerializer<NotificationTypeDto> {

	@Override
	public void serialize(final NotificationTypeDto notificationTypeDto, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(notificationTypeDto != null ? notificationTypeDto.getValue() : "null");
	}

}
