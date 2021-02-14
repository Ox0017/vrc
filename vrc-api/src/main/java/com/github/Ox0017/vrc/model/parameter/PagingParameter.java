package com.github.Ox0017.vrc.model.parameter;

abstract class PagingParameter {

	final Integer amount;

	final Integer offset;

	PagingParameter(final Integer amount, final Integer offset) {
		this.amount = amount;
		this.offset = offset;
	}

}
