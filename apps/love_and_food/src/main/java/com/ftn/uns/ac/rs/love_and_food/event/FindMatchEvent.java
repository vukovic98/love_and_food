package com.ftn.uns.ac.rs.love_and_food.event;

import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.ftn.uns.ac.rs.love_and_food.model.User;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("168h")
// 1 week = 7 * 24h = 168h
public class FindMatchEvent {
	
	private Date timestamp;
	private User initiator;
	
	public FindMatchEvent(Date timestamp, User initiator) {
		this.timestamp = timestamp;
		this.initiator = initiator;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public User getInitiator() {
		return initiator;
	}
	public void setInitiator(User initiator) {
		this.initiator = initiator;
	}
}
