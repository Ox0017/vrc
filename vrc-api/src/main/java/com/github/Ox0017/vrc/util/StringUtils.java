package com.github.Ox0017.vrc.util;

public class StringUtils {

	public static String trimToNull(final String value) {
		if (value == null) {
			return null;
		}

		final String trimmed = value.trim();
		if (trimmed.isEmpty()) {
			return null;
		}

		return value;
	}

}
