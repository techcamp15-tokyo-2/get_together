package com.example.get_together;

	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;

	import android.os.Bundle;
	import android.app.Activity;
	import android.view.Menu;
	import android.widget.ArrayAdapter;
	import android.widget.ListView;
	import android.widget.SimpleAdapter;

	public class ContactActivity extends Activity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_contact);

			//get contact  list
			ListView lv = (ListView) findViewById(R.id.contactList);
			SimpleAdapter adapter = new SimpleAdapter(this,getContact(),R.layout.contact_list,
					new String[]{"friendHead","friendName"},
					new int[]{R.id.friendHead,R.id.friendName});
			lv.setAdapter(adapter);
				
				
		}
		
		//get contact  list
		private List<Map<String, Object>> getContact() {
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("friendHead", R.drawable.header01);
			map.put("friendName", "A����");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("friendHead", R.drawable.header02);
			map.put("friendName", "B����");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("friendHead", R.drawable.header03);
			map.put("friendName", "C����");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("friendHead", R.drawable.header01);
			map.put("friendName", "A����");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("friendHead", R.drawable.header02);
			map.put("friendName", "B����");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("friendHead", R.drawable.header03);
			map.put("friendName", "C����");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("friendHead", R.drawable.header01);
			map.put("friendName", "A����");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("friendHead", R.drawable.header02);
			map.put("friendName", "B����");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("friendHead", R.drawable.header03);
			map.put("friendName", "C����");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("friendHead", R.drawable.header01);
			map.put("friendName", "A����");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("friendHead", R.drawable.header02);
			map.put("friendName", "B����");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("friendHead", R.drawable.header03);
			map.put("friendName", "C����");
			list.add(map);
			
			return list;
		}
	}
