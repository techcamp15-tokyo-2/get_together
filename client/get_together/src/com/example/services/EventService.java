package com.example.services;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.domain.Event;
import com.example.services.DBOpenHelper;

public class EventService {
	private DBOpenHelper dbOpenHelper;

	public EventService(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}
	
	/**
	 * add a event
	 * @param event
	 * 
	 * 		db.execSQL("CREATE TABLE event"+
				"(event_id integer primary key autoincrement,"+
				"status varchar(5)"+
				"time varchar(32)"+
				"hostess varchar(10)"+
				"address varchar(50)"+
				"p_latitude double"+
				"p_longtitude double");
	 */
	public void save(Event event){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("insert into event(status, time, hostess, address, p_latitude, p_longtitude) values(?,?,?,?,?,?)",
				new Object[]{event.getStatus(),event.getTime(),
				event.getHostess(),event.getAddress(), event.getP_latitude(), event.getP_longtitude()});
	}
	/**
	 * delete id 
	 * @param id
	 */
	public void delete(Integer id){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("delete from event where event_id=?", new Object[]{id});
	}
	/**
	 * update
	 * @param event
	 */
	public void update(Event event){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update event set status=?,time=?,hostess=?, address=?, p_latitude=?,p_longtitude=? where event_id=?",
				new Object[]{event.getStatus(),event.getTime(),
				event.getHostess(),event.getAddress(), event.getP_latitude(), event.getP_longtitude()});
	}
	/**
	 * query
	 * @param id 
	 * @return
	 */
	public Event find(Integer id){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from event where event_id=?", new String[]{id.toString()});
		if(cursor.moveToFirst()){
			int event_id = cursor.getInt(cursor.getColumnIndex("event_id"));
			String status = cursor.getString(cursor.getColumnIndex("status"));
			String time = cursor.getString(cursor.getColumnIndex("time"));
			String hostess = cursor.getString(cursor.getColumnIndex("hostess"));
			String address = cursor.getString(cursor.getColumnIndex("address"));
			String p_latitude = cursor.getString(cursor.getColumnIndex("p_latitude"));
			String p_longtitude = cursor.getString(cursor.getColumnIndex("p_longtitude"));
			return new Event(event_id, status, time, hostess, address, p_latitude, p_longtitude);
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
	public List<Event> getScrollData(int offset, int maxResult){
		List<Event> events = new ArrayList<Event>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from event order by event_id asc limit ?,?",
				new String[]{String.valueOf(offset), String.valueOf(maxResult)});
		while(cursor.moveToNext()){
			int event_id = cursor.getInt(cursor.getColumnIndex("event_id"));
			String status = cursor.getString(cursor.getColumnIndex("status"));
			String time = cursor.getString(cursor.getColumnIndex("time"));
			String hostess = cursor.getString(cursor.getColumnIndex("hostess"));
			String address = cursor.getString(cursor.getColumnIndex("address"));
			String p_latitude = cursor.getString(cursor.getColumnIndex("p_latitude"));
			String p_longtitude = cursor.getString(cursor.getColumnIndex("p_longtitude"));
			events.add(new Event(event_id, status, time, hostess, address, p_latitude, p_longtitude));
		}
		cursor.close();
		return events;
	}
	/**
	 * the same as the function before. different with the return cursor.
	 * @param offset 
	 * @param maxResult 
	 * @return
	 */
	public Cursor getCursorScrollData(int offset, int maxResult){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select event_id as _id,status, time, hostess, address, p_latitude, p_longtitude from event order by event_id asc limit ?,?",
				new String[]{String.valueOf(offset), String.valueOf(maxResult)});
		return cursor;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getCount(){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from person", null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		cursor.close();
		return result;
	}
}
