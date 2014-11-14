package com.example.g_ravel_myinfo;

import com.example.g_ravel.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class TravelRecordDetail extends Activity{

	private TextView DetailArea;
	private ListView DetailList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.travelrecord_detail);
		
		Intent intent = getIntent();
		
		DetailArea = (TextView)findViewById(R.id.tv_TravelRecordDetail_AreaDate);
		DetailArea.setText(intent.getStringExtra("PutData"));
	}
}
