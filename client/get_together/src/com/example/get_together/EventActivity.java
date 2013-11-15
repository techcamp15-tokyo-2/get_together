package com.example.get_together;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class EventActivity extends Activity {
	
	private ImageButton refreshBtn;
	private ImageButton logoutBtn;
	private ImageButton hiddenBtn;
	private TextView yourName;
	private Button getTogether;
	private FrameLayout nowEvent;
	private String userMail;
	
	private boolean hiddenFlag=true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_event);
		SharedPreferences sharedata = this.getSharedPreferences("TabHost", 0);
		userMail= sharedata.getString("userMail",null);
//		System.out.println(userMail);
		yourName = (TextView)findViewById(R.id.yourName);
		yourName.setText(userMail);
			
		//get event list
		ListView lv = (ListView) findViewById(R.id.eventList);
		SimpleAdapter adapter = new EventAdapter(this,getEventList(),R.layout.event_list,
				new String[]{"eventName","eventHostess","eventTime","eventAddress"},
				new int[]{R.id.eventName,R.id.eventHostess,R.id.eventTime,R.id.eventAddress});
		lv.setAdapter(adapter);
		
		//listener start
		findViews();
		setLIsteners();
	}
	
	//find view
	private void findViews() {
		refreshBtn = (ImageButton) findViewById(R.id.refreshBtn);
		logoutBtn = (ImageButton) findViewById(R.id.logoutBtn);
		hiddenBtn = (ImageButton) findViewById(R.id.hiddenBtn);
		getTogether = (Button) findViewById(R.id.getTogether);
		nowEvent = (FrameLayout) findViewById(R.id.nowEvent);
	}
	//set listener
	private void setLIsteners() {
		refreshBtn.setOnClickListener(refreshBtnListener);
		logoutBtn.setOnClickListener(logoutBtnListener);
		hiddenBtn.setOnClickListener(hiddenBtnListener);
		getTogether.setOnClickListener(getTogetherListener);
	}
	
	//refreshBtn
	private View.OnClickListener refreshBtnListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Toast.makeText(getApplicationContext(), "refresh page", Toast.LENGTH_SHORT).show();
			
		}
	};
	
	//logout
	private View.OnClickListener logoutBtnListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), LoginActivity.class);
			startActivity(intent);
		}
	};
	
	//hiddenBtn
		private View.OnClickListener hiddenBtnListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(hiddenFlag==true){
					nowEvent.setVisibility(View.GONE);
					hiddenFlag = false;
				}
				else{
					nowEvent.setVisibility(View.VISIBLE);
					hiddenFlag = true;
				}
			}
		};
	
	//getTogether
	private View.OnClickListener getTogetherListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Toast.makeText(getApplicationContext(), "get together!", Toast.LENGTH_SHORT).show();
		}
	};
	
	//get event list
	private List<Map<String, Object>> getEventList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("eventName", "飲み会");
		map.put("eventHostess", "組織者："+"Aさん");
		map.put("eventTime", "時　間："+"11月12日 ");
		map.put("eventAddress", "場　所："+"渋谷アークシティ");
		list.add(map);
			
		map = new HashMap<String, Object>();
		map.put("eventName", "カラオケ");
		map.put("eventHostess", "組織者："+"Bさん");
		map.put("eventTime", "時　間："+"11月13日 ");
		map.put("eventAddress", "場　所："+"渋谷アークシティ");
		list.add(map);
				
		map = new HashMap<String, Object>();
		map.put("eventName", "誕生日パーティ");
		map.put("eventHostess", "組織者："+"Dさん");	
		map.put("eventTime", "時　間："+"11月14日 ");
		map.put("eventAddress", "場　所："+"渋谷アークシティ");
		list.add(map);


		return list;
	}
}
