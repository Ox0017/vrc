package com.github.Ox0017.vrc.model.dto.serialization.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.Ox0017.vrc.model.dto.favorite.FavoriteTypeDto;

import java.io.IOException;

public class FavoriteTypeDtoSerializer extends JsonSerializer<FavoriteTypeDto> {

	@Override
	public void serialize(final FavoriteTypeDto favoriteTypeDto, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(favoriteTypeDto != null ? favoriteTypeDto.getValue() : "null");
	}

}
