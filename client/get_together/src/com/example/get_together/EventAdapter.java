
package com.example.get_together;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.anim;
import android.R.integer;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.style.SuperscriptSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class EventAdapter extends SimpleAdapter {
	private LayoutInflater inflater;
	private List<? extends Map<String, ?>> listData;
	
	public class ViewHolder {
		TextView eventName;
		TextView eventHostess;
		TextView eventTime;
		TextView eventAddress;
	}
	
	public EventAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
		  super(context, data, resource, from, to);

		  this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  this.listData = data;
	}
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.event_list, parent, false);
			holder = new ViewHolder();
			holder.eventName = (TextView) view.findViewById(R.id.eventName);
			holder.eventHostess = (TextView) view.findViewById(R.id.eventHostess);
			holder.eventTime = (TextView) view.findViewById(R.id.eventTime);
			holder.eventAddress = (TextView) view.findViewById(R.id.eventAddress);
			view.setTag(holder);
		}
		else {
			   holder = (ViewHolder) view.getTag();
		}
		
		String eventName = ((HashMap<?, ?>) listData.get(position)).get("eventName").toString();
		String eventHostess = ((HashMap<?, ?>) listData.get(position)).get("eventHostess").toString();
		String eventTime = ((HashMap<?, ?>) listData.get(position)).get("eventTime").toString();
		String eventAddress = ((HashMap<?, ?>) listData.get(position)).get("eventAddress").toString();
		holder.eventName.setText(eventName);
		holder.eventHostess.setText(eventHostess);
		holder.eventTime.setText(eventTime);
		holder.eventAddress.setText(eventAddress);
		
		Button btn = (Button) view.findViewById(R.id.eventButton);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Toast.makeText(v.getContext(), "position:"+position, Toast.LENGTH_SHORT).show();
				
				LayoutInflater mInflater = LayoutInflater.from(v.getContext());
				View view = mInflater.inflate(R.layout.event_more, null);
				LinearLayout layout = (LinearLayout) view.findViewById(R.id.eventMore);
				
				TextView tempEventName = (TextView) view.findViewById(R.id.eventName);
				TextView tempEventHostess = (TextView) view.findViewById(R.id.eventHostess);
				TextView tempEventTime = (TextView) view.findViewById(R.id.eventTime);
				TextView tempEventAddress = (TextView) view.findViewById(R.id.eventAddress);

				switch(position){
					case 0:
						tempEventName.setText("飲み会");
						tempEventHostess.setText("組織者："+"Aさん");
						tempEventTime.setText("時　間："+"11月12日 ");
						tempEventAddress.setText("場　所："+"渋谷アークシティ");
						break;
					case 1:
						tempEventName.setText("カラオケ");
						tempEventHostess.setText("組織者："+"Bさん");
						tempEventTime.setText("時　間："+"11月13日 ");
						tempEventAddress.setText("場　所："+"渋谷アークシティ");
						break;
					case 2:
						tempEventName.setText("誕生日パーティ");
						tempEventHostess.setText("組織者："+"Dさん");
						tempEventTime.setText("時　間："+"11月14日 ");
						tempEventAddress.setText("場　所："+"渋谷アークシティ");
						break;
					default:
				}
				
				AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext()); 
//			    View view = View.inflate(v.getContext(), R.layout.event_more, null); 
			    builder.setView(view); 
			    builder.setTitle("イベントの情報"); 
			    builder.setPositiveButton("確認", new DialogInterface.OnClickListener() { 
			    	@Override 
			    	public void onClick(DialogInterface dialog, int which) { 
			    		dialog.cancel(); 
			        } 
			    }); 
			    Dialog dialog = builder.create(); 
			    dialog.show(); 
				
			}
		});
		return view;
}
}

