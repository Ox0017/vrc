package com.github.Ox0017.vrc.model.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthDto {

	@JsonProperty("ok")
	private Boolean valid;

	@JsonProperty("token")
	private String token;

	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(final Boolean valid) {
		this.valid = valid;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("AuthDto [");
		sb.append("valid=").append(this.valid);
		sb.append(", token='").append(this.token).append('\'');
		sb.append(']');
		return sb.toString();
	}

}
