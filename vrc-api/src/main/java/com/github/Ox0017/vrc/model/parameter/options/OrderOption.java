package com.github.Ox0017.vrc.model.parameter.options;

import java.util.Locale;

public enum OrderOption {

	ASCENDING,
	DESCENDING;

	private final String value;

	OrderOption() {
		this.value = this.name().toLowerCase(Locale.ENGLISH);
	}

	public String getValue() {
		return this.value;
	}

}
