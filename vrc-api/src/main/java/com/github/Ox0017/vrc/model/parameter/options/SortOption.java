package com.github.Ox0017.vrc.model.parameter.options;

import java.util.Locale;

public enum SortOption {

	POPULARITY,
	CREATED,
	UPDATED,
	ORDER,
	CREATED_AT("_created_at"),
	UPDATED_AT("_updated_at");

	private final String value;

	SortOption() {
		this.value = this.name().toLowerCase(Locale.ENGLISH);
	}

	SortOption(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
