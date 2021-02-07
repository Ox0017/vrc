package com.github.Ox0017.vrc;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static junit.framework.TestCase.fail;

public abstract class TestSupport {

	protected String readJson(final String name) {
		final URL url = this.getClass().getClassLoader().getResource("json/" + name);
		if (url == null) {
			fail("Could not load json" + name);
		}
		try {
			return IOUtils.toString(url, StandardCharsets.UTF_8);
		}
		catch (final IOException ex) {
			fail("Could not load json" + name + ": " + ex.getMessage());
		}
		return null;
	}

}
