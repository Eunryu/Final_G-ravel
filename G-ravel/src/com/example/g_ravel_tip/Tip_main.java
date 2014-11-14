package com.example.g_ravel_tip;

import com.example.g_ravel.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Tip_main extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tip_main);
		
		//버튼선언
		Button TravelTip = (Button)findViewById(R.id.btn_Tip_traveltip);
		Button UsefulArea = (Button)findViewById(R.id.btn_Tip_usefullArea);
		Button event = (Button)findViewById(R.id.btn_Tip_event);
		
		TravelTip.setOnClickListener(this);
		UsefulArea.setOnClickListener(this);
		event.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//버튼 기능주기
		if(v.getId() == R.id.btn_Tip_traveltip){
			Intent go_traveltip = new Intent(Tip_main.this,TravelTip.class);
			startActivity(go_traveltip);
		}else if(v.getId() == R.id.btn_Tip_usefullArea){
			Intent go_useful = new Intent(Tip_main.this,UsefulArea.class);
			startActivity(go_useful);
		}else if(v.getId() == R.id.btn_Tip_event){
			Intent go_event = new Intent(Tip_main.this,TipEvent.class);
			startActivity(go_event);
		}
	}
}
