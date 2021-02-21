package com.github.Ox0017.vrc.model.parameter;

import com.github.Ox0017.vrc.model.dto.general.PlatformDto;
import com.github.Ox0017.vrc.model.dto.general.ReleaseStatusDto;
import com.github.Ox0017.vrc.model.parameter.options.OrderOption;
import com.github.Ox0017.vrc.model.parameter.options.SortOption;
import com.github.Ox0017.vrc.util.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class WorldParameters extends PagingParameter implements RequestParameter {

	private final Endpoint endpoint; // path parameter option

	private final Boolean featured;

	private final SortOption sortOption;

	private final String user;

	private final String userId;

	private final OrderOption orderOption;

	private final String search;

	private final String tagInclude;

	private final String tagExclude;

	private final ReleaseStatusDto releaseStatus;

	private final PlatformDto platform;

	// maxUnityVersion
	// minUnityVersion
	// maxAssetVersion
	// minAssetVersion

	private WorldParameters(final Integer amount, final Integer offset, final Endpoint endpoint, final Boolean featured, final SortOption sortOption, final String user, final String userId,
			final OrderOption orderOption, final String search, final String tagInclude, final String tagExclude, final ReleaseStatusDto releaseStatus, final PlatformDto platform) {
		super(amount, offset);
		this.endpoint = endpoint;
		this.featured = featured;
		this.sortOption = sortOption;
		this.user = user;
		this.userId = userId;
		this.orderOption = orderOption;
		this.search = search;
		this.tagInclude = tagInclude;
		this.tagExclude = tagExclude;
		this.releaseStatus = releaseStatus;
		this.platform = platform;
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
		if (this.featured != null) {
			parameters.add(new BasicNameValuePair("featured", this.featured.toString()));
		}
		if (this.sortOption != null) {
			parameters.add(new BasicNameValuePair("sort", this.sortOption.getValue()));
		}
		if (this.user != null) {
			parameters.add(new BasicNameValuePair("user", this.user));
		}
		if (this.userId != null) {
			parameters.add(new BasicNameValuePair("userId", this.userId));
		}
		if (this.orderOption != null) {
			parameters.add(new BasicNameValuePair("order", this.orderOption.getValue()));
		}
		if (this.search != null) {
			parameters.add(new BasicNameValuePair("search", this.search));
		}
		if (this.tagInclude != null) {
			parameters.add(new BasicNameValuePair("tag", this.tagInclude));
		}
		if (this.tagExclude != null) {
			parameters.add(new BasicNameValuePair("notag", this.tagExclude));
		}
		if (this.releaseStatus != null) {
			parameters.add(new BasicNameValuePair("releaseStatus", this.releaseStatus.getValue()));
		}
		if (this.platform != null) {
			parameters.add(new BasicNameValuePair("platform", this.platform.getValue()));
		}

		final NameValuePair[] result = new NameValuePair[parameters.size()];
		for (int i = 0; i < parameters.size(); i++) {
			result[i] = parameters.get(i);
		}

		return result;
	}

	public String getEndpoint() {
		if (this.endpoint != null) {
			switch (this.endpoint) {
				case ACTIVE:
					return "/active";
				case FAVORITE:
					return "/favorites";
				case RECENTLY_VISITED:
					return "/recent";
				default:
					return "";
			}
		}
		return "";
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	public static class Builder {

		private Builder() {
		}

		private Endpoint endpoint = Endpoint.ANY;

		private Integer amount = 10;

		private Integer offset = 0;

		private Boolean featured;

		private SortOption sortOption;

		private String user;

		private String userId;

		private OrderOption orderOption;

		private String search;

		private String tagInclude;

		private String tagExclude;

		private ReleaseStatusDto releaseStatus;

		private PlatformDto platform;

		/**
		 * Search in any worlds (set by default)
		 *
		 * @return the builder
		 */
		public Builder inAnyWorlds() {
			this.endpoint = Endpoint.ANY;
			return this;
		}

		/**
		 * Search in active worlds
		 *
		 * @return the builder
		 */
		public Builder inActiveWorlds() {
			this.endpoint = Endpoint.ACTIVE;
			return this;
		}

		/**
		 * Search in recently visited worlds
		 *
		 * @return the builder
		 */
		public Builder inRecentlyVisitedWorlds() {
			this.endpoint = Endpoint.RECENTLY_VISITED;
			return this;
		}

		/**
		 * Search in favorite worlds
		 *
		 * @return the builder
		 */
		public Builder inFavoriteWorlds() {
			this.endpoint = Endpoint.FAVORITE;
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
		 * Adds the type parameter to filter featured worlds
		 *
		 * @param featured is the world featured
		 * @return the builder
		 */
		public Builder featured(final Boolean featured) {
			this.featured = featured;
			return this;
		}

		/**
		 * Adds the sort parameter
		 *
		 * @param sortOption how to sort the worlds
		 * @return the builder
		 */
		public Builder sort(final SortOption sortOption) {
			this.sortOption = sortOption;
			return this;
		}

		/**
		 * Adds the user parameter
		 *
		 * @param user set to 'me' for searching own worlds
		 * @return the builder
		 */
		public Builder user(final String user) {
			this.user = StringUtils.trimToNull(user);
			return this;
		}

		/**
		 * Adds the userId parameter
		 *
		 * @param userId filter by creator id, use 'me' for only worlds owned by current user
		 * @return the builder
		 */
		public Builder userId(final String userId) {
			this.userId = StringUtils.trimToNull(userId);
			return this;
		}

		/**
		 * Adds the order parameter
		 *
		 * @param orderOption the order of the returned worlds
		 * @return the builder
		 */
		public Builder order(final OrderOption orderOption) {
			this.orderOption = orderOption;
			return this;
		}

		/**
		 * Adds the search parameter to filter results by a given text
		 *
		 * @param search a string to search for in world names
		 * @return the builder
		 */
		public Builder search(final String search) {
			this.search = StringUtils.trimToNull(search);
			return this;
		}

		/**
		 * Adds the tagInclude parameter to filter results by given tags (comma separated)
		 *
		 * @param tagInclude tags the worlds must contain
		 * @return the builder
		 */
		public Builder tagInclude(final String tagInclude) {
			this.tagInclude = StringUtils.trimToNull(tagInclude);
			return this;
		}

		/**
		 * Adds the tagExclude parameter to filter results by given tags (comma separated)
		 *
		 * @param tagExclude tags the worlds must not contain
		 * @return the builder
		 */
		public Builder tagExclude(final String tagExclude) {
			this.tagExclude = StringUtils.trimToNull(tagExclude);
			return this;
		}

		/**
		 * Adds the releaseStatus parameter to filter results by release status
		 *
		 * @param releaseStatus UNKNOWN is not supported
		 * @return the builder
		 */
		public Builder releaseStatus(final ReleaseStatusDto releaseStatus) {
			if (releaseStatus != ReleaseStatusDto.UNKNOWN) {
				this.releaseStatus = releaseStatus;
			}
			return this;
		}

		/**
		 * Adds the platform parameter to filter results by platform
		 *
		 * @param platform UNKNOWN is not supported
		 * @return the builder
		 */
		public Builder platformDto(final PlatformDto platform) {
			if (platform != PlatformDto.UNKNOWN) {
				this.platform = platform;
			}
			return this;
		}

		/**
		 * Completes the build process
		 *
		 * @return the WorldParameters with all previously set properties
		 */
		public WorldParameters build() {
			return new WorldParameters(this.amount, this.offset, this.endpoint, this.featured, this.sortOption, this.user, this.userId,
					this.orderOption, this.search, this.tagInclude, this.tagExclude, this.releaseStatus, this.platform);
		}

		public static Builder create() {
			return new Builder();
		}
	}

	private enum Endpoint {
		ANY,
		ACTIVE,
		RECENTLY_VISITED,
		FAVORITE
	}

}
