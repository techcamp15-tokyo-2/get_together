package com.example.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	
	public DBOpenHelper(Context context) {
		//the last param is version. when you need to update your database, 
		//just gain the version param. ex. 1 -> 2
		super(context, "get_together.db", null, 1);//<°ü>/databases/
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE friends" +
				"(friend_id integer primary key autoincrement, " +
				"mail varchar(32)"+
				"name varchar(20), " +
				"sex varchar(5))");
		db.execSQL("CREATE TABLE event"+
				"(event_id integer primary key autoincrement,"+
				"status varchar(5)"+
				"time varchar(32)"+
				"hostess varchar(32)"+
				"address varchar(50)"+
				"p_Latitude double"+
				"p_longtitude double");
		db.execSQL("CREATE TABLE event_friends" +
				"event_id integer"+
				"friend_id integer");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		// TODO Auto-generated method stub
		//db.execSQL("ALTER TABLE event_friends ADD ef_id integer");
	}

}
