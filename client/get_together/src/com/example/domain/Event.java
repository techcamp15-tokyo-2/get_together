package com.example.domain;
/*
db.execSQL("CREATE TABLE event"+
		"(event_id integer primary key autoincrement,"+
		"status varchar(5)"+
		"time varchar(32)"+
		"hostess varchar(10)"+
		"address varchar(50)"+
		"p_Latitude double"+
		"p_longtitude double");
		*/

		
public class Event {
	
	public Event(Integer event_id, String status, String time,String  hostess,String  address,String  p_latitude,String  p_longtitude) {
		this.event_id = event_id;
		this.status = status;
		this.time = time;
		this.hostess = hostess;
		this.address = address;
		this.p_latitude = p_latitude;
		this.p_longtitude = p_longtitude;
	}
		public Integer getEvent_id() {
			return event_id;
	}
	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getHostess() {
		return hostess;
	}
	public void setHostess(String hostess) {
		this.hostess = hostess;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getP_latitude() {
		return p_latitude;
	}
	public void setP_latitude(String p_latitude) {
		this.p_latitude = p_latitude;
	}
	public String getP_longtitude() {
		return p_longtitude;
	}
	public void setP_longtitude(String p_longtitude) {
		this.p_longtitude = p_longtitude;
	}
	private Integer event_id;
	private String status;
	private String time;
	private String hostess;
	private String address;
	private String p_latitude;
	private String p_longtitude;
	
}
