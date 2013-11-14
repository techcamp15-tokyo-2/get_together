package com.example.services;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.domain.Friends;

public class FriendsService {
	private DBOpenHelper dbOpenHelper;

	public FriendsService(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}
	
	/*
	 * 		db.execSQL("CREATE TABLE friends" +
					"(friend_id integer primary key autoincrement, " +
					"mail varchar(32)"+
					"name varchar(20), " +
					"sex varchar(5))");
	 * */
	
	public void save(Friends friends){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("insert into event(mail, name, sex) values(?,?,?)",
				new Object[]{friends.getMail(), friends.getName(), friends.getSex()});
	}
	/**
	 * delete id 
	 * @param id
	 */
	public void delete(Integer id){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("delete from friends where friends_id=?", new Object[]{id});
	}
	/**
	 * update
	 * @param event
	 */
	public void update(Friends friends){
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update friends set mail=?,name=?,sex=? where friends_id=?",
				new Object[]{friends.getMail(), friends.getName(), friends.getSex()});
	}
	/**
	 * query
	 * @param id 
	 * @return
	 */
	public Friends find(Integer id){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from event where friend_id=?", new String[]{id.toString()});
		if(cursor.moveToFirst()){
			int friend_id = cursor.getInt(cursor.getColumnIndex("friend_id"));
			String mail = cursor.getString(cursor.getColumnIndex("mail"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String sex = cursor.getString(cursor.getColumnIndex("sex"));
			return new Friends(friend_id, mail, name,sex);
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
	public List<Friends> getScrollData(int offset, int maxResult){
		List<Friends> friends = new ArrayList<Friends>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from friends order by friend_id asc limit ?,?",
				new String[]{String.valueOf(offset), String.valueOf(maxResult)});
		while(cursor.moveToNext()){
			int friend_id = cursor.getInt(cursor.getColumnIndex("friend_id"));
			String mail = cursor.getString(cursor.getColumnIndex("mail"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String sex = cursor.getString(cursor.getColumnIndex("sex"));
			friends.add(new Friends(friend_id, mail, name, sex));
		}
		cursor.close();
		return friends;
	}
	/**
	 * the same as the function before. different with the return cursor.
	 * @param offset 
	 * @param maxResult 
	 * @return
	 */
	public Cursor getCursorScrollData(int offset, int maxResult){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select friend_id as _id,mail, name, sex from event order by friend_id asc limit ?,?",
				new String[]{String.valueOf(offset), String.valueOf(maxResult)});
		return cursor;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getCount(){
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from friends", null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		cursor.close();
		return result;
	}
}
