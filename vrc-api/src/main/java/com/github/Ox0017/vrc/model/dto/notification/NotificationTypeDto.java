package com.github.Ox0017.vrc.model.dto.notification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Ox0017.vrc.model.dto.serialization.deserialize.NotificationTypeDtoDeserializer;
import com.github.Ox0017.vrc.model.dto.serialization.serialize.NotificationTypeDtoSerializer;

import java.util.Locale;

@JsonSerialize(using = NotificationTypeDtoSerializer.class)
@JsonDeserialize(using = NotificationTypeDtoDeserializer.class)
public enum NotificationTypeDto {

	UNKNOWN(Type.NONE),
	ALL(Type.RECEIVE), // Only used as a parameter to return all notification types,
	FRIEND_REQUEST("friendRequest", Type.RECEIVE),
	VOTE_KICK("votetokick", Type.RECEIVE),
	HALP(Type.RECEIVE), // Unknown, might not exist anymore
	HIDDEN(Type.RECEIVE), // Unknown, might not exist anymore
	BROADCAST(Type.RECEIVE_SEND),
	MESSAGE(Type.RECEIVE_SEND),
	INVITE(Type.RECEIVE_SEND),
	REQUEST_INVITE("requestInvite", Type.RECEIVE_SEND),
	REQUEST_INVITE_RESPONSE("requestInviteResponse", Type.RECEIVE_SEND);

	private final String value;

	private final Type type;

	NotificationTypeDto(final Type type) {
		this.type = type;
		this.value = this.name().toLowerCase(Locale.ENGLISH);
	}

	NotificationTypeDto(final String value, final Type type) {
		this.value = value;
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public boolean isValid() {
		return this.type != Type.NONE;
	}

	public boolean isSendable() {
		return this.type == Type.RECEIVE_SEND;
	}

	public static NotificationTypeDto fromString(final String value) {
		if (value == null) {
			return null;
		}

		for (final NotificationTypeDto notificationTypeDto : NotificationTypeDto.values()) {
			if (notificationTypeDto.name().equals(value) || notificationTypeDto.getValue().equals(value)) {
				return notificationTypeDto;
			}
		}
		return UNKNOWN;
	}

	private enum Type {
		NONE,
		RECEIVE,
		RECEIVE_SEND
	}

}
