package com.github.Ox0017.vrc.model.dto.serialization.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.Ox0017.vrc.model.dto.favorite.FavoriteTypeDto;

import java.io.IOException;

public class FavoriteTypeDtoDeserializer extends JsonDeserializer<FavoriteTypeDto> {

	@Override
	public FavoriteTypeDto deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
		return FavoriteTypeDto.fromString(jsonParser.getValueAsString());
	}

}
