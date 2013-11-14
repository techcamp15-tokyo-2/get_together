package com.example.domain;

/*
 * 		db.execSQL("CREATE TABLE friends" +
				"(friend_id integer primary key autoincrement, " +
				"mail varchar(32)"+
				"name varchar(20), " +
				"sex varchar(5))");
 * */
public class Friends {
	
	private Integer friend_id;
	private String mail;
	private String name;
	private String sex;
	
	public Friends(Integer friend_id, String mail, String name,String  sex) {
		this.friend_id = friend_id;
		this.mail = mail;
		this.name = name;
		this.sex = sex;
	}
	public Friends(String mail, String name,String  sex) {
		this.mail = mail;
		this.name = name;
		this.sex = sex;
	}
	
	public Integer getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(Integer friend_id) {
		this.friend_id = friend_id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
