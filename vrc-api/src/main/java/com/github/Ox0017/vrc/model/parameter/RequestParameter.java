package com.github.Ox0017.vrc.model.parameter;

import org.apache.http.NameValuePair;

public interface RequestParameter {

	NameValuePair[] getParameters();

	boolean isEmpty();

}
