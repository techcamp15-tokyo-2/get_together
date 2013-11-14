package com.example.test;

import android.test.AndroidTestCase;

import com.example.services.FriendsService;
import com.example.services.DBOpenHelper;
import com.example.domain.Friends;

public class FriendsTest extends AndroidTestCase {
	private static final String TAG = "PersonServiceTest";
	
	public void testCreateDB() throws Exception{
		DBOpenHelper dbOpenHelper = new DBOpenHelper(getContext());
		dbOpenHelper.getWritableDatabase();
	}
	
	public void testSave() throws Exception{
		FriendsService service = new FriendsService(this.getContext());
		Friends friend = new Friends("1@1.com","Roy","male");
			service.save(friend);
	}

}
