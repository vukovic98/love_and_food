package com.ftn.uns.ac.rs.love_and_food.event;

import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.ftn.uns.ac.rs.love_and_food.model.Match;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("2h")
public class MateRatingEvent {
	
	private Date timestamp;
	private Match match;
	
	public MateRatingEvent(Date timestamp, Match match) {
		this.timestamp = timestamp;
		this.match = match;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	
}
