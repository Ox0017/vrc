package com.github.Ox0017.vrc.model.client;

public class Response {

	private Integer statusCode;

	private String statusPhrase;

	private String responseBody;

	public Integer getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(final Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusPhrase() {
		return this.statusPhrase;
	}

	public void setStatusPhrase(final String statusPhrase) {
		this.statusPhrase = statusPhrase;
	}

	public String getResponseBody() {
		return this.responseBody;
	}

	public void setResponseBody(final String responseBody) {
		this.responseBody = responseBody;
	}

	public boolean is2xxSuccessful() {
		return this.statusCode != null && this.statusCode >= 200 && this.statusCode < 300;
	}

	public boolean isUnauthorized() {
		return this.statusCode != null && this.statusCode == 401;
	}

}
