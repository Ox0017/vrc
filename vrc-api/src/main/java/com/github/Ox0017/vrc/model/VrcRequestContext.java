package com.github.Ox0017.vrc.model;

import com.github.Ox0017.vrc.util.StringUtils;

public class VrcRequestContext {

	private String username;

	private char[] password;

	private String auth;

	private String apiKey;

	private String cfduid;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		if (this.password == null) {
			return null;
		}
		else {
			return String.valueOf(this.password);
		}
	}

	public void setPassword(final String password) {
		if (password == null) {
			this.password = null;
		}
		else {
			this.password = password.toCharArray();
		}
	}

	public String getAuth() {
		return this.auth;
	}

	public void setAuth(final String auth) {
		this.auth = auth;
	}

	public String getApiKey() {
		return this.apiKey;
	}

	public void setApiKey(final String apiKey) {
		this.apiKey = apiKey;
	}

	public boolean hasAuth() {
		return this.auth != null;
	}

	public String getCfduid() {
		return this.cfduid;
	}

	public void setCfduid(final String cfduid) {
		this.cfduid = cfduid;
	}

	public boolean hasCredentials() {
		return StringUtils.trimToNull(this.username) != null && this.password != null;
	}

	public VrcRequestContext copy() {
		final VrcRequestContext copy = new VrcRequestContext();
		copy.username = this.username;
		if (copy.password != null) {
			this.password = copy.password.clone();
		}
		copy.auth = this.auth;
		copy.apiKey = this.apiKey;
		copy.cfduid = this.cfduid;
		return copy;
	}

	public void restoreBy(final VrcRequestContext vrcRequestContext) {
		if (vrcRequestContext == null) {
			return;
		}

		this.username = vrcRequestContext.username;
		this.password = vrcRequestContext.password != null ? vrcRequestContext.password.clone() : null;
		this.auth = vrcRequestContext.auth;
		this.apiKey = vrcRequestContext.apiKey;
		this.cfduid = vrcRequestContext.cfduid;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("VrcRequestContext [");
		sb.append("username='").append(this.username).append('\'');
		sb.append(", password='").append(this.password != null ? "_isPresent_" : "null").append('\'');
		sb.append(", auth='").append(this.auth).append('\'');
		sb.append(", apiKey='").append(this.apiKey).append('\'');
		sb.append(", cfduid='").append(this.cfduid).append('\'');
		sb.append(']');
		return sb.toString();
	}

}
