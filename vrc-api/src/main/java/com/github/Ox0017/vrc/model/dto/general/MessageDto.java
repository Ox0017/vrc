package com.github.Ox0017.vrc.model.dto.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {

	@JsonProperty("message")
	private String message;

	@JsonProperty("status_code")
	private Integer statusCode;

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public Integer getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(final Integer statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ErrorDto [");
		sb.append("message='").append(this.message).append('\'');
		sb.append(", statusCode=").append(this.statusCode);
		sb.append(']');
		return sb.toString();
	}

}
