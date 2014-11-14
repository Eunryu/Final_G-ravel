package com.example.g_ravel;

import com.example.g_ravel_G_train.G_Train;
import com.example.g_ravel_G_train.G_worldMap;
import com.example.g_ravel_goTravel.ChooseBigArea;
import com.example.g_ravel_setting.Setting_main;
import com.example.g_ravel_travelNote_event.TravelNote_main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NonMemberMain extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_nonmember);

		// 버튼선언
		Button Nonmake_travel = (Button) findViewById(R.id.btn_NonMember_Gotravel);
		Button Nonwrite_note = (Button) findViewById(R.id.btn_NonMember_TravelNote);
		Button Nontrain_map = (Button) findViewById(R.id.btn_NonMember_G_train);
		Button Nonworld_map = (Button) findViewById(R.id.btn_NonMember_G_map);
		Button Nonsetting = (Button) findViewById(R.id.btn_NonMember_setting);

		Nonmake_travel.setOnClickListener(this);
		Nonwrite_note.setOnClickListener(this);
		Nontrain_map.setOnClickListener(this);
		Nonworld_map.setOnClickListener(this);
		Nonsetting.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.btn_NonMember_Gotravel){
			Intent go_travel = new Intent(NonMemberMain.this,ChooseBigArea.class);
			startActivity(go_travel);
		}else if(v.getId() == R.id.btn_NonMember_TravelNote){
			Intent go_note = new Intent(NonMemberMain.this,TravelNote_main.class);
			startActivity(go_note);
		}else if(v.getId() == R.id.btn_NonMember_G_train){
			Intent go_train = new Intent(NonMemberMain.this,G_Train.class);
			startActivity(go_train);
		}else if(v.getId() == R.id.btn_NonMember_G_map){
			Intent go_map = new Intent(NonMemberMain.this,G_worldMap.class);
			startActivity(go_map);
		}else if(v.getId() == R.id.btn_NonMember_setting){
			Intent go_setting = new Intent(NonMemberMain.this,Setting_main.class);
			startActivity(go_setting);
		}
	}
}
