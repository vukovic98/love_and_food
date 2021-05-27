package com.ftn.uns.ac.rs.love_and_food.event;

import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("2m")
public class FailedLoginEvent{
	
	private Date timestamp;
	private RegisteredUser user;
	
	public FailedLoginEvent() {}
	
	public FailedLoginEvent(Date timestamp, RegisteredUser user) {
		this.timestamp = timestamp;
		this.user = user;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public RegisteredUser getUser() {
		return user;
	}
	public void setUser(RegisteredUser user) {
		this.user = user;
	}
}
