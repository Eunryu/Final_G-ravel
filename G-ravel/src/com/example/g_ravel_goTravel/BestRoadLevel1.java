package com.example.g_ravel_goTravel;

import com.example.g_ravel.R;
import com.example.g_ravel_etcProgramming.Gravel_static;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BestRoadLevel1 extends Activity implements OnClickListener{
	
	private Button Healing;
	private Button Play;
	private Button family;
	private Button Date;
	private Button See;
	
	private Gravel_static gs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bestroad_level1);
		
		gs = (Gravel_static)getApplicationContext();
				
		//버튼선언
		Healing = (Button)findViewById(R.id.btn_BestRoadLevel1_Healing);
		Play = (Button)findViewById(R.id.btn_BestRoadLevel1_work);
		family = (Button)findViewById(R.id.btn_BestRoadLevel1_Family);
		Date = (Button)findViewById(R.id.btn_BestRoadLevel1_Date);
		See = (Button)findViewById(R.id.btn_BestRoadLevel1_SeeArea);
		
		Healing.setOnClickListener(this);
		Play.setOnClickListener(this);
		family.setOnClickListener(this);
		Date.setOnClickListener(this);
		See.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.btn_BestRoadLevel1_Healing){
			String Name = Healing.getText().toString();
			gs.set_SELECT_PACKAGE(Name);
			Intent go_Healing = new Intent(BestRoadLevel1.this, BestRoadLevel2.class);
			go_Healing.putExtra("ButtonName", Name);
			startActivity(go_Healing);
		}else if(v.getId() == R.id.btn_BestRoadLevel1_Family){
			String Name = family.getText().toString();
			gs.set_SELECT_PACKAGE(Name);
			Intent go_Healing = new Intent(BestRoadLevel1.this, BestRoadLevel2.class);
			go_Healing.putExtra("ButtonName", Name);
			startActivity(go_Healing);
		}else if(v.getId() == R.id.btn_BestRoadLevel1_Date){
			String Name = Date.getText().toString();
			gs.set_SELECT_PACKAGE(Name);
			Intent go_Healing = new Intent(BestRoadLevel1.this, BestRoadLevel2.class);
			go_Healing.putExtra("ButtonName", Name);
			startActivity(go_Healing);
		}else if(v.getId() == R.id.btn_BestRoadLevel1_work){
			String Name = Play.getText().toString();
			gs.set_SELECT_PACKAGE(Name);
			Intent go_Healing = new Intent(BestRoadLevel1.this, BestRoadLevel2.class);
			go_Healing.putExtra("ButtonName", Name);
			startActivity(go_Healing);
		}else if(v.getId() == R.id.btn_BestRoadLevel1_SeeArea){
			String Name = See.getText().toString();
			gs.set_SELECT_PACKAGE(Name);
			Intent go_Healing = new Intent(BestRoadLevel1.this, BestRoadLevel2.class);
			go_Healing.putExtra("ButtonName", Name);
			startActivity(go_Healing);
		}
	}
}
