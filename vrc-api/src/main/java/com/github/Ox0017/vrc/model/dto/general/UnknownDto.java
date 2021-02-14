package com.github.Ox0017.vrc.model.dto.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnknownDto {

	// unknown properties

	@Override
	public String toString() {
		return "UnknownDto [parameters unknown]";
	}

}
