package com.github.Ox0017.vrc.model.dto.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Ox0017.vrc.model.dto.serialization.deserialize.OffsetDateTimeDeserializer;
import com.github.Ox0017.vrc.model.dto.serialization.serialize.OffsetDateTimeSerializer;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationDto {

	@JsonProperty("id")
	private String id;

	@JsonProperty("senderUserId")
	private String senderUserId;

	@JsonProperty("senderUsername")
	private String senderUserName;

	@JsonProperty("type")
	private NotificationTypeDto type;

	@JsonProperty("message")
	private String message;

	@JsonProperty("details")
	private String details;

	@JsonProperty("seen")
	private Boolean seen;

	@JsonSerialize(using = OffsetDateTimeSerializer.class)
	@JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	@JsonProperty("created_at")
	private OffsetDateTime createdAt;

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getSenderUserId() {
		return this.senderUserId;
	}

	public void setSenderUserId(final String senderUserId) {
		this.senderUserId = senderUserId;
	}

	public String getSenderUserName() {
		return this.senderUserName;
	}

	public void setSenderUserName(final String senderUserName) {
		this.senderUserName = senderUserName;
	}

	public NotificationTypeDto getType() {
		return this.type;
	}

	public void setType(final NotificationTypeDto type) {
		this.type = type;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(final String details) {
		this.details = details;
	}

	public Boolean getSeen() {
		return this.seen;
	}

	public void setSeen(final Boolean seen) {
		this.seen = seen;
	}

	public OffsetDateTime getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(final OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("NotificationDto [");
		sb.append("id='").append(this.id).append('\'');
		sb.append(", senderUserId='").append(this.senderUserId).append('\'');
		sb.append(", senderUserName='").append(this.senderUserName).append('\'');
		sb.append(", type=").append(this.type);
		sb.append(", message='").append(this.message).append('\'');
		sb.append(", details=").append(this.details != null && !"{}".equals(this.details) ? "_present_" : "null");
		sb.append(", seen=").append(this.seen);
		sb.append(", createdAt=").append(this.createdAt);
		sb.append(']');
		return sb.toString();
	}

}
