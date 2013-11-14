package com.example.get_together;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TextView;

public class MenuActivity extends TabActivity {
	private TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menu);
		
		tabHost=this.getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent=new Intent().setClass(this, NewEventActivity.class);
        spec=tabHost.newTabSpec("m_new").setIndicator("m_new").setContent(intent);
        tabHost.addTab(spec);
        
        intent=new Intent().setClass(this,EventActivity.class);
        spec=tabHost.newTabSpec("m_event").setIndicator("m_event").setContent(intent);
        tabHost.addTab(spec);
        
        intent=new Intent().setClass(this, MainActivity.class);
        spec=tabHost.newTabSpec("m_map").setIndicator("m_map").setContent(intent);
        tabHost.addTab(spec);
        
        intent=new Intent().setClass(this, ContactActivity.class);
        spec=tabHost.newTabSpec("m_contact").setIndicator("m_contact").setContent(intent);
        tabHost.addTab(spec);
        
        tabHost.setCurrentTab(1);
        
        RadioGroup radioGroup=(RadioGroup) this.findViewById(R.id.main_tab_group);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.m_new://new event page
					tabHost.setCurrentTabByTag("m_new");
					break;
				case R.id.m_event://event page
					tabHost.setCurrentTabByTag("m_event");
					break;
				case R.id.m_map://map page
					tabHost.setCurrentTabByTag("m_map");
					break;
				case R.id.m_contact://contact page
					tabHost.setCurrentTabByTag("m_contact");
					break;

				default:
					break;
				}
			}
		});
	}

	

}
