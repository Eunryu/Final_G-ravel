package com.example.g_ravel_myinfo;

import com.example.g_ravel.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Myinfo_main extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myinfo_main);
		
		//버튼선언
		Button see = (Button)findViewById(R.id.btn_MyInfo_seeMyinfo);
		Button oftenarea = (Button)findViewById(R.id.btn_MyInfo_oftenArea);
		Button Mystamp = (Button)findViewById(R.id.btn_MyInfo_Mystamp);
		Button TravelRecord = (Button)findViewById(R.id.btn_MyInfo_travelRecord);
		
		see.setOnClickListener(this);
		oftenarea.setOnClickListener(this);
		Mystamp.setOnClickListener(this);
		TravelRecord.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.btn_MyInfo_seeMyinfo){
			Intent go_see = new Intent(Myinfo_main.this,Myinfo_see.class);
			startActivity(go_see);
		}else if(v.getId() == R.id.btn_MyInfo_oftenArea){
			Intent go_oftenArea = new Intent(Myinfo_main.this,Myinfo_oftenArea.class);
			startActivity(go_oftenArea);
		}else if(v.getId() == R.id.btn_MyInfo_travelRecord){
			Intent go_travelRecord = new Intent(Myinfo_main.this,Myinfo_travelRecord.class);
			startActivity(go_travelRecord);
		}else if(v.getId() == R.id.btn_MyInfo_Mystamp){
			Intent go_stamp = new Intent(Myinfo_main.this,Myinfo_myStamp.class);
			startActivity(go_stamp);
		}
	}
}
