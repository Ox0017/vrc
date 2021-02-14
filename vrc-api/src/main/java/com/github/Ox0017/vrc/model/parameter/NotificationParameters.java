package com.github.Ox0017.vrc.model.parameter;

import com.github.Ox0017.vrc.model.dto.notification.NotificationTypeDto;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NotificationParameters implements RequestParameter {

	private final NotificationTypeDto type;

	private final boolean sent;

	private final OffsetDateTime dateAfter;

	private NotificationParameters(final NotificationTypeDto type, final boolean sent, final OffsetDateTime dateAfter) {
		this.type = type;
		this.sent = sent;
		this.dateAfter = dateAfter;
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
		if (this.sent) {
			parameters.add(new BasicNameValuePair("sent", "true"));
		}
		if (this.dateAfter != null) {
			parameters.add(new BasicNameValuePair("date", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.dateAfter.atZoneSameInstant(ZoneOffset.UTC))));
		}

		final NameValuePair[] result = new NameValuePair[parameters.size()];
		for (int i = 0; i < parameters.size(); i++) {
			result[i] = parameters.get(i);
		}

		return result;
	}

	@Override
	public boolean isEmpty() {
		return this.type == null && !this.sent && this.dateAfter == null;
	}

	public static class Builder {

		private Builder() {
		}

		private NotificationTypeDto type;

		private boolean sent = false;

		private OffsetDateTime dateAfter;

		/**
		 * Adds the type parameter, to filter notifications by type
		 *
		 * @param type UNKNOWN is not supported
		 * @return the builder
		 */
		public Builder type(final NotificationTypeDto type) {
			if (type != null && type.isValid()) {
				this.type = type;
			}
			return this;
		}

		/**
		 * Adds the sent parameter, to get only sent notifications
		 *
		 * @param sent true or false, default: false (received notifications)
		 * @return the builder
		 */
		public Builder sent(final Boolean sent) {
			this.sent = Boolean.TRUE.equals(sent);
			return this;
		}

		/**
		 * Adds the date parameter, to get only notification newer than the given date
		 *
		 * @param dateAfter true or false, default: false (received notifications)
		 * @return the builder
		 */
		public Builder dateAfter(final OffsetDateTime dateAfter) {
			this.dateAfter = dateAfter;
			return this;
		}

		/**
		 * Completes the build process
		 *
		 * @return the UserParameters with all previously set properties
		 */
		public NotificationParameters build() {
			return new NotificationParameters(this.type, this.sent, this.dateAfter);
		}

		public static Builder create() {
			return new Builder();
		}
	}

}
