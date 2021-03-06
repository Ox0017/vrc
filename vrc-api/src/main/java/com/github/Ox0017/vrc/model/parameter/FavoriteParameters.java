package com.github.Ox0017.vrc.model.parameter;

import com.github.Ox0017.vrc.model.dto.favorite.FavoriteTypeDto;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class FavoriteParameters extends PagingParameter implements RequestParameter {

	private final FavoriteTypeDto type;

	private FavoriteParameters(final Integer amount, final Integer offset, final FavoriteTypeDto type) {
		super(amount, offset);
		this.type = type;
	}

	@Override
	public boolean isEmpty() {
		return this.type == null && this.amount == null && this.offset == null;
	}

	@Override
	public NameValuePair[] getParameters() {
		if (this.isEmpty()) {
			return new NameValuePair[0];
		}

		final List<NameValuePair> parameters = new ArrayList<>(3);
		if (this.type != null) {
			parameters.add(new BasicNameValuePair("type", this.type.getValue()));
		}
		if (this.amount != null) {
			parameters.add(new BasicNameValuePair("n", this.amount.toString()));
		}
		if (this.offset != null) {
			parameters.add(new BasicNameValuePair("offset", this.offset.toString()));
		}

		final NameValuePair[] result = new NameValuePair[parameters.size()];
		for (int i = 0; i < parameters.size(); i++) {
			result[i] = parameters.get(i);
		}

		return result;
	}

	public static class Builder {

		private Builder() {
		}

		private FavoriteTypeDto type;

		private Integer amount = 10;

		private Integer offset = 0;

		/**
		 * Adds the type parameter to filter results by their type
		 *
		 * @param type FavoriteTypeDto.UNKNOWN will be ignored
		 * @return the builder
		 */
		public Builder type(final FavoriteTypeDto type) {
			if (type != FavoriteTypeDto.UNKNOWN) {
				this.type = type;
			}
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
		 * @return the FavoriteParameters with all previously set properties
		 */
		public FavoriteParameters build() {
			return new FavoriteParameters(this.amount, this.offset, this.type);
		}

		public static Builder create() {
			return new Builder();
		}
	}

}
