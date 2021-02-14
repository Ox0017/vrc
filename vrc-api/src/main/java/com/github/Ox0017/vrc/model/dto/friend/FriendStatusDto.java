package com.github.Ox0017.vrc.model.dto.friend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FriendStatusDto {

	@JsonProperty("isFriend")
	private Boolean friend;

	@JsonProperty("outgoingRequest")
	private Boolean outgoingRequest;

	@JsonProperty("incomingRequest")
	private Boolean incomingRequest;

	public Boolean getFriend() {
		return this.friend;
	}

	public void setFriend(final Boolean friend) {
		this.friend = friend;
	}

	public Boolean getOutgoingRequest() {
		return this.outgoingRequest;
	}

	public void setOutgoingRequest(final Boolean outgoingRequest) {
		this.outgoingRequest = outgoingRequest;
	}

	public Boolean getIncomingRequest() {
		return this.incomingRequest;
	}

	public void setIncomingRequest(final Boolean incomingRequest) {
		this.incomingRequest = incomingRequest;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("FriendStatusDto [");
		sb.append("friend=").append(this.friend);
		sb.append(", outgoingRequest=").append(this.outgoingRequest);
		sb.append(", incomingRequest=").append(this.incomingRequest);
		sb.append(']');
		return sb.toString();
	}

}
