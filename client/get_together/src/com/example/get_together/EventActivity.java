package com.example.get_together;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

public class EventActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_event);
	}

}
