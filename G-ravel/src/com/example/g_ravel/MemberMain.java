package com.example.g_ravel;

import com.example.g_ravel_G_train.G_Train;
import com.example.g_ravel_G_train.G_worldMap;
import com.example.g_ravel_goTravel.ChooseBigArea;
import com.example.g_ravel_myinfo.Myinfo_main;
import com.example.g_ravel_setting.Setting_main;
import com.example.g_ravel_tip.Tip_main;
import com.example.g_ravel_travelNote_event.Event_main;
import com.example.g_ravel_travelNote_event.TravelNote_main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MemberMain extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_member);
		
		//버튼선언
		Button make_travel = (Button)findViewById(R.id.btn_Member_Gotravel);
		Button write_note = (Button)findViewById(R.id.btn_Member_TravelNote);
		Button train_map = (Button)findViewById(R.id.btn_Member_G_train);
		Button world_map = (Button)findViewById(R.id.btn_Member_G_map);
		Button tip = (Button)findViewById(R.id.btn_Member_Tips);
		Button event = (Button)findViewById(R.id.btn_Member_event);
		Button setting = (Button)findViewById(R.id.btn_Member_setting);
		Button myInfo = (Button)findViewById(R.id.btn_Member_myinfo);
		
		make_travel.setOnClickListener(this);
		write_note.setOnClickListener(this);
		train_map.setOnClickListener(this);
		world_map.setOnClickListener(this);
		tip.setOnClickListener(this);
		event.setOnClickListener(this);
		setting.setOnClickListener(this);
		myInfo.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.btn_Member_Gotravel){
			Intent go_travel = new Intent(MemberMain.this,ChooseBigArea.class);
			startActivity(go_travel);
		}else if(v.getId() == R.id.btn_Member_TravelNote){
			Intent go_note = new Intent(MemberMain.this,TravelNote_main.class);
			startActivity(go_note);
		}else if(v.getId() == R.id.btn_Member_G_train){
			Intent go_train = new Intent(MemberMain.this,G_Train.class);
			startActivity(go_train);
		}else if(v.getId() == R.id.btn_Member_G_map){
			Intent go_map = new Intent(MemberMain.this,G_worldMap.class);
			startActivity(go_map);
		}else if(v.getId() == R.id.btn_Member_Tips){
			Intent go_tip = new Intent(MemberMain.this,Tip_main.class);
			startActivity(go_tip);
		}else if(v.getId() == R.id.btn_Member_event){
			Intent go_event = new Intent(MemberMain.this,Event_main.class);
			startActivity(go_event);
		}else if(v.getId() == R.id.btn_Member_setting){
			Intent go_setting = new Intent(MemberMain.this,Setting_main.class);
			startActivity(go_setting);
		}else if(v.getId() == R.id.btn_Member_myinfo){
			Intent go_myinfo = new Intent(MemberMain.this,Myinfo_main.class);
			startActivity(go_myinfo);
		}
	}
}
