package com.example.g_ravel_goTravel;

import com.example.g_ravel.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ChooseBigArea extends Activity{

	//리스트뷰를 위한 스트링 더미값
	private String[] list = {"경기도"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_bigarea);
		
		ArrayAdapter<String> aList = new ArrayAdapter<String>(ChooseBigArea.this, android.R.layout.simple_dropdown_item_1line, list);
		ListView lv_area = (ListView)findViewById(R.id.lv_CBigArea_AreaList);
		lv_area.setAdapter(aList);
		lv_area.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position == 0){
					String posi = Integer.toString(position);
					Toast.makeText(getApplicationContext(), posi, Toast.LENGTH_SHORT).show();
					Intent goNext = new Intent(ChooseBigArea.this,ChoosePlanhow.class);
					startActivity(goNext);
					finish();
				}
			}
		});
	}
}
