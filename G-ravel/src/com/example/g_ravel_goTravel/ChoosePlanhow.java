package com.example.g_ravel_goTravel;

import com.example.g_ravel.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChoosePlanhow extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_planhow);
		
		//버튼선언
		Button BestRoad = (Button)findViewById(R.id.btn_ChoosePlanhow_Best);
		Button MakeRoad = (Button)findViewById(R.id.btn_ChoosePlanhow_Makeplan);
		
		BestRoad.setOnClickListener(this);
		MakeRoad.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.btn_ChoosePlanhow_Best){
			Intent go_next = new Intent(ChoosePlanhow.this,BestRoadLevel1.class);
			startActivity(go_next);
			finish();
		}else if(v.getId() == R.id.btn_ChoosePlanhow_Makeplan){
			Intent go_next2 = new Intent(ChoosePlanhow.this,MakeRoadLevel1.class);
			startActivity(go_next2);
			finish();
		}
	}
}
