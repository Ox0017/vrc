package com.github.Ox0017.vrc.model.dto.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VrcSuccessDto {

	@JsonProperty("success")
	private MessageDto success;

	public MessageDto getSuccess() {
		return this.success;
	}

	public void setSuccess(final MessageDto success) {
		this.success = success;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("VrcSuccessDto [");
		sb.append("success=").append(this.success);
		sb.append(']');
		return sb.toString();
	}

}
