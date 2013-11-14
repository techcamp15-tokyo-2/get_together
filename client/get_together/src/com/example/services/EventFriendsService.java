package com.example.services;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.domain.EventFriends;

/*
 * 		db.execSQL("CREATE TABLE event_friends" +
				"event_id integer"+
				"friend_id integer");
 * 
 * */
public class EventFriendsService {
	private DBOpenHelper dbOpenHelper;

	public EventFriendsService(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}
	
	public void save(EventFriends ef){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("insert into event_friends(event_id, friend_id) values(?,?)",
				new Object[]{ef.getEvent_id(), ef.getFriend_id()});
	}
	/**
	 * delete id 
	 * @param id
	 */
	public void delete(Integer id){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("delete from event_friends where event_id=?", new Object[]{id});
	}
	/**
	 * update
	 * @param event
	 */
	public void update(EventFriends ef){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update event_friends set event_id=?, friend_id=? where event_id=?",
				new Object[]{ef.getEvent_id(), ef.getFriend_id()});
	}
	/**
	 * query
	 * @param id 
	 * @return
	 */
	public EventFriends find(Integer id){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from event_friends where event_id=?", new String[]{id.toString()});
		if(cursor.moveToFirst()){
			int event_id = cursor.getInt(cursor.getColumnIndex("event_id"));
			int friend_id = cursor.getInt(cursor.getColumnIndex("friend_id"));
			return new EventFriends(event_id, friend_id);
		}
		cursor.close();
		return null;
	}
	/**
	 * getScrollData
	 * @param offset query data from no. offset record.
	 * @param maxResult each page's biggest records' amount.
	 * @return
	 */
	public List<EventFriends> getScrollData(int offset, int maxResult){
		List<EventFriends> efs = new ArrayList<EventFriends>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from event_friends order by event_id asc limit ?,?",
				new String[]{String.valueOf(offset), String.valueOf(maxResult)});
		while(cursor.moveToNext()){
			int event_id = cursor.getInt(cursor.getColumnIndex("event_id"));
			int friend_id = cursor.getInt(cursor.getColumnIndex("friend_id"));
			efs.add(new EventFriends(event_id, friend_id));
		}
		cursor.close();
		return efs;
	}
	/**
	 * the same as the function before. different with the return cursor.
	 * @param offset 
	 * @param maxResult 
	 * @return
	
	public Cursor getCursorScrollData(int offset, int maxResult){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select event_id as _id,status, time, hostess, address, p_latitude, p_longtitude from event order by event_id asc limit ?,?",
				new String[]{String.valueOf(offset), String.valueOf(maxResult)});
		return cursor;
	}
	 */
	/**
	 * 
	 * @return
	 */
	public long getCount(){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from event_friends", null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		cursor.close();
		return result;
	}
}
