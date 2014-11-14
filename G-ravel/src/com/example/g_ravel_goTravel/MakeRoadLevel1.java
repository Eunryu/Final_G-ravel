package com.example.g_ravel_goTravel;

import com.example.g_ravel.R;
import com.example.g_ravel_etcProgramming.Gravel_static;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MakeRoadLevel1 extends Activity implements OnClickListener{

	private Button bed;
	private Button eat;
	private Button play;
	private Button see;
	private Button car;
	private Gravel_static gs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.makeroad_level1);
		
		gs = (Gravel_static)getApplicationContext();
		
		//버튼선언
		bed = (Button)findViewById(R.id.btn_MakeRoadLevel1_bed);
		eat = (Button)findViewById(R.id.btn_MakeRoadLevel1_eat);
		play = (Button)findViewById(R.id.btn_MakeRoadLevel1_play);
		see = (Button)findViewById(R.id.btn_MakeRoadLevel1_see);
		car = (Button)findViewById(R.id.btn_MakeRoadLevel1_car);
		
		bed.setOnClickListener(this);
		eat.setOnClickListener(this);
		play.setOnClickListener(this);
		see.setOnClickListener(this);
		car.setOnClickListener(this);
		
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.btn_MakeRoadLevel1_bed){
			Intent go_next = new Intent(MakeRoadLevel1.this,MakeRoadLevel2.class);
			gs.set_SELECT_SHOP_NAME(bed.getText().toString());
			go_next.putExtra("SET_AREA", bed.getText().toString());
			startActivity(go_next);
		}else if(v.getId() == R.id.btn_MakeRoadLevel1_eat){
			//Toast.makeText(getApplicationContext(), "없음", Toast.LENGTH_SHORT).show();
			Intent go_next = new Intent(MakeRoadLevel1.this,MakeRoadLevel2.class);
			gs.set_SELECT_SHOP_NAME(eat.getText().toString());
			go_next.putExtra("SET_AREA", eat.getText().toString());
			startActivity(go_next);
		}else if(v.getId() == R.id.btn_MakeRoadLevel1_see){
			//Toast.makeText(getApplicationContext(), "없음", Toast.LENGTH_SHORT).show();
			Intent go_next = new Intent(MakeRoadLevel1.this,MakeRoadLevel2.class);
			gs.set_SELECT_SHOP_NAME(see.getText().toString());
			go_next.putExtra("SET_AREA", see.getText().toString());
			startActivity(go_next);
		}else if(v.getId() == R.id.btn_MakeRoadLevel1_play){
			//Toast.makeText(getApplicationContext(), "없음", Toast.LENGTH_SHORT).show();
			Intent go_next = new Intent(MakeRoadLevel1.this,MakeRoadLevel2.class);
			gs.set_SELECT_SHOP_NAME(play.getText().toString());
			go_next.putExtra("SET_AREA", play.getText().toString());
			startActivity(go_next);
		}else if(v.getId() == R.id.btn_MakeRoadLevel1_car){
			//Toast.makeText(getApplicationContext(), "없음", Toast.LENGTH_SHORT).show();
			Intent go_next = new Intent(MakeRoadLevel1.this,MakeRoadLevel2.class);
			gs.set_SELECT_SHOP_NAME(car.getText().toString());
			go_next.putExtra("SET_AREA", car.getText().toString());
			startActivity(go_next);
		}
	}
}
