package com.example.domain;

/*
 * 		db.execSQL("CREATE TABLE event_friends" +
				"event_id integer"+
				"friend_id integer");
 * 
 * */
public class EventFriends {
	private Integer event_id;
	private Integer friend_id;
	
	public EventFriends(Integer event_id, Integer friend_id) {
		this.event_id = event_id;
		this.friend_id = friend_id;
	}
	
	public Integer getEvent_id() {
		return event_id;
	}
	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}
	public Integer getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(Integer friend_id) {
		this.friend_id = friend_id;
	}
}
