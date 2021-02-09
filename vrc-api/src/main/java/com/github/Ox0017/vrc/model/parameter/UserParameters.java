package com.github.Ox0017.vrc.model.parameter;

import com.github.Ox0017.vrc.model.dto.user.DeveloperTypeDto;
import com.github.Ox0017.vrc.util.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class UserParameters implements RequestParameter {

	private final boolean active; // path parameter

	private Integer amount;

	private Integer offset;

	private DeveloperTypeDto developerType;

	private String search;

	public boolean isActive() {
		return this.active;
	}

	private UserParameters(final boolean active, final Integer amount, final Integer offset, final DeveloperTypeDto developerType, final String search) {
		this.active = active;
		this.amount = amount;
		this.offset = offset;
		this.developerType = developerType;
		this.search = search;
	}

	@Override
	public boolean isEmpty() {
		return !this.active && this.amount == null && this.offset == null && this.developerType == null && this.search == null;
	}

	@Override
	public NameValuePair[] getParameters() {
		if (this.isEmpty()) {
			return new NameValuePair[0];
		}

		final List<NameValuePair> parameters = new ArrayList<>(4);
		if (this.amount != null) {
			parameters.add(new BasicNameValuePair("n", this.amount.toString()));
		}
		if (this.offset != null) {
			parameters.add(new BasicNameValuePair("offset", this.offset.toString()));
		}
		if (this.developerType != null) {
			parameters.add(new BasicNameValuePair("developerType", this.developerType.getValue()));
		}
		if (this.search != null) {
			parameters.add(new BasicNameValuePair("search", this.search));
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

		private boolean active = false;

		private Integer amount = 10;

		private Integer offset = 0;

		private String search;

		private DeveloperTypeDto developerType;

		/**
		 * Adds the active parameter, set with true to get only active users
		 *
		 * @param active true or false, default: false
		 * @return the builder
		 */
		public Builder active(final Boolean active) {
			this.active = Boolean.TRUE.equals(active);
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
		 * Adds the type parameter to filter results by their type
		 *
		 * @param developerType DeveloperTypeDto.UNKNOWN will be ignored
		 * @return the builder
		 */
		public Builder developerType(final DeveloperTypeDto developerType) {
			if (developerType != DeveloperTypeDto.UNKNOWN) {
				this.developerType = developerType;
			}
			return this;
		}

		/**
		 * Adds the type parameter to filter results by their type
		 *
		 * @param search a string to search for in user names
		 * @return the builder
		 */
		public Builder search(final String search) {
			this.search = StringUtils.trimToNull(search);
			return this;
		}

		/**
		 * Completes the build process
		 *
		 * @return the UserParameters with all previously set properties
		 */
		public UserParameters build() {
			return new UserParameters(this.active, this.amount, this.offset, this.developerType, this.search);
		}

		public static Builder create() {
			return new Builder();
		}
	}

}
