package com.example.get_together;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.MotionEvent; 
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;
import android.widget.Toast;


public class NewEventActivity extends Activity implements View.OnTouchListener {
	
	private Button searchAddrBtn;
	private Button makeEventBtn;
	private Button clearBtn;
	private EditText nameInput;
	private EditText dateTimeSelect;
	private EditText addressInput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_event);
		
		//get contact  list
		ListView lv = (ListView) findViewById(R.id.contactList);
		SimpleAdapter adapter = new SimpleAdapter(this,getContact(),R.layout.contact_list,
				new String[]{"friendHead","friendName"},
				new int[]{R.id.friendHead,R.id.friendName});
		lv.setAdapter(adapter);
		
		findViews();
		setLIsteners();
	}
	
	//find view
	private void findViews() {
		searchAddrBtn = (Button) findViewById(R.id.searchAddrBtn);
		makeEventBtn = (Button) findViewById(R.id.makeEventBtn);
		clearBtn = (Button) findViewById(R.id.clearBtn);
		nameInput = (EditText) this.findViewById(R.id.nameInput);
		dateTimeSelect = (EditText) this.findViewById(R.id.dateTimeSelect); 
		addressInput = (EditText) this.findViewById(R.id.addressInput);
	}
	//set listener
	private void setLIsteners() {
		searchAddrBtn.setOnClickListener(searchAddrBtnListener);
		makeEventBtn.setOnClickListener(makeEventBtnListener);
		clearBtn.setOnClickListener(clearBtnListener);
		dateTimeSelect.setOnTouchListener(this); 
	}
	
	//search the map
	private View.OnClickListener searchAddrBtnListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			//test
			intent.setClass(getApplicationContext(), MainActivity.class);
			//intent.setClass(getApplicationContext(), EventActivity.class);
			startActivity(intent);
		Toast.makeText(getApplicationContext(), "search the map", Toast.LENGTH_SHORT).show();
		}
	};
	
	//make new event
	private View.OnClickListener makeEventBtnListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Toast.makeText(getApplicationContext(), "make new event", Toast.LENGTH_SHORT).show();
		}
	};
	
	//clear form
	private View.OnClickListener clearBtnListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			nameInput.setText("");
			dateTimeSelect.setText("");
			addressInput.setText("");
		}
	};
	
	//get date&time
	@Override 
    public boolean onTouch(View v, MotionEvent event) { 
        if (event.getAction() == MotionEvent.ACTION_DOWN) { 
   
            AlertDialog.Builder builder = new AlertDialog.Builder(this); 
            View view = View.inflate(this, R.layout.date_time_dialog, null); 
            final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker); 
            final TimePicker timePicker = (android.widget.TimePicker) view.findViewById(R.id.time_picker); 
            builder.setView(view); 
   
            Calendar cal = Calendar.getInstance(); 
            cal.setTimeInMillis(System.currentTimeMillis()); 
            datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null); 
           
            timePicker.setIs24HourView(true); 
            timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY)); 
            timePicker.setCurrentMinute(Calendar.MINUTE); 
 
                final int inType = dateTimeSelect.getInputType(); 
                dateTimeSelect.setInputType(InputType.TYPE_NULL); 
                dateTimeSelect.onTouchEvent(event); 
                dateTimeSelect.setInputType(inType); 
                dateTimeSelect.setSelection(dateTimeSelect.getText().length()); 
               
                builder.setTitle("イベントの�r�g"); 
                builder.setPositiveButton("�_�J", new DialogInterface.OnClickListener() { 
   
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
   
                        StringBuffer sb = new StringBuffer(); 
                        sb.append(String.format("%d-%02d-%02d",  
                                datePicker.getYear(),  
                                datePicker.getMonth() + 1, 
                                datePicker.getDayOfMonth())); 
                        sb.append("  "); 
                        sb.append(timePicker.getCurrentHour()) 
                        .append(":").append(timePicker.getCurrentMinute()); 
   
                        dateTimeSelect.setText(sb); 
                           
                        dialog.cancel(); 
                    } 
                }); 

            Dialog dialog = builder.create(); 
            dialog.show(); 
        } 
        return true; 
    } 
	
	//get contact  list
	private List<Map<String, Object>> getContact() {
				
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("friendHead", R.drawable.header01);
		map.put("friendName", "Aさん");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("friendHead", R.drawable.header02);
		map.put("friendName", "Bさん");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("friendHead", R.drawable.header03);
		map.put("friendName", "Cさん");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("friendHead", R.drawable.header04);
		map.put("friendName", "Dさん");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("friendHead", R.drawable.header01);
		map.put("friendName", "Eさん");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("friendHead", R.drawable.header02);
		map.put("friendName", "Fさん");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("friendHead", R.drawable.header03);
		map.put("friendName", "Gさん");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("friendHead", R.drawable.header04);
		map.put("friendName", "Hさん");
		list.add(map);
				
		return list;
	}
}
