package com.github.Ox0017.vrc.model.parameter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class FriendParameters extends PagingParameter implements RequestParameter {

	private final boolean offline;

	private FriendParameters(final Integer amount, final Integer offset, final boolean offline) {
		super(amount, offset);
		this.offline = offline;
	}

	@Override
	public NameValuePair[] getParameters() {
		if (this.isEmpty()) {
			return new NameValuePair[0];
		}

		final List<NameValuePair> parameters = new ArrayList<>(3);
		if (this.amount != null) {
			parameters.add(new BasicNameValuePair("n", this.amount.toString()));
		}
		if (this.offset != null) {
			parameters.add(new BasicNameValuePair("offset", this.offset.toString()));
		}
		if (this.offline) {
			parameters.add(new BasicNameValuePair("offline", "true"));
		}

		final NameValuePair[] result = new NameValuePair[parameters.size()];
		for (int i = 0; i < parameters.size(); i++) {
			result[i] = parameters.get(i);
		}

		return result;
	}

	@Override
	public boolean isEmpty() {
		return this.amount == null && this.offset == null && !this.offline;
	}

	public static class Builder {

		private Builder() {
		}

		private boolean offline = false;

		private Integer amount = 10;

		private Integer offset = 0;

		/**
		 * Adds the offline parameter, set with true includes offline friends
		 *
		 * @param offline true or false, default: false
		 * @return the builder
		 */
		public Builder offline(final Boolean offline) {
			this.offline = Boolean.TRUE.equals(offline);
			return this;
		}

		/**
		 * Adds the amount parameter, how many results should be returned
		 *
		 * @param amount a value greater than 0 and less or equal 100, default: 10
		 * @return the builder
		 */
		public Builder amount(final Integer amount) {
			if (amount != null && amount < 1) {
				throw new IllegalArgumentException("Amount must be a positive value");
			}
			if (amount != null && amount > 100) {
				throw new IllegalArgumentException("Amount must be <= 100");
			}
			this.amount = amount;
			return this;
		}

		/**
		 * Adds the offset parameter, used for paging
		 *
		 * @param offset a value greater or equal than 0, default: 0
		 * @return the builder
		 */
		public Builder offset(final Integer offset) {
			if (offset != null && offset < 0) {
				throw new IllegalArgumentException("Offset must be a positive value");
			}
			this.offset = offset;
			return this;
		}

		/**
		 * Completes the build process
		 *
		 * @return the UserParameters with all previously set properties
		 */
		public FriendParameters build() {
			return new FriendParameters(this.amount, this.offset, this.offline);
		}

		public static Builder create() {
			return new Builder();
		}
	}

}
