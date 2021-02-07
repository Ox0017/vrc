package com.github.Ox0017.vrc.model.dto.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VrcErrorDto {

	@JsonProperty("error")
	private ErrorDto error;

	public ErrorDto getError() {
		return this.error;
	}

	public void setError(final ErrorDto error) {
		this.error = error;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ErrorWrapperDto [");
		sb.append("error=").append(this.error);
		sb.append(']');
		return sb.toString();
	}

}
