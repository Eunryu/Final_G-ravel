package com.example.g_ravel_tip;

import java.util.ArrayList;

import com.example.g_ravel.R;
import com.example.g_ravel_etcProgramming.UsefulArea_ListData;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class UsefulArea extends Activity {

	private String[] SpinnerList = { "병원", "편의점", "주유소" };
	private String[] SpinnerArea = { "성남", "용인", "수원", "안산" };

	// 테스트용 배열
	private String[] Select = { "주유소", "편의점", "병원", "편의점", "병원", "병원", "편의점",
			"병원", "주유소", "주유소" };
	private String[] Name = { "S-Oil", "세븐일레븐", "서울대병원", "미니스톱", "건국대학병원",
			"서울대병원", "세븐일레븐", "아주대병원", "Sk-주유소", "Self주유소" };
	private String[] Area = { "성남", "용인", "안산", "수원", "안산", "용인", "성남", "용인",
			"성남", "성남" };
	private String[] phone = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
	private String[] address = { "none", "none", "none", "none", "none",
			"none", "none", "none", "none", "none" };
	
	private ListViewAdapter listadapter;
	private int i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tip_usefularea);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				UsefulArea.this, android.R.layout.simple_dropdown_item_1line,
				SpinnerList);
		Spinner spinner = (Spinner) findViewById(R.id.sp_UsefulArea_spinner);
		spinner.setAdapter(adapter);

		ArrayAdapter<String> Area_adapter = new ArrayAdapter<String>(
				UsefulArea.this, android.R.layout.simple_dropdown_item_1line,
				SpinnerArea);
		Spinner AreaSpinner = (Spinner) findViewById(R.id.sp_UsefulArea_AreaSpinner);
		AreaSpinner.setAdapter(Area_adapter);

		// 이 아래부터 커스텀 리스트 뷰.
		ListView UsefulAreaList = (ListView)findViewById(R.id.lv_UserfulArea_list);
		listadapter = new ListViewAdapter(UsefulArea.this);
		UsefulAreaList.setAdapter(listadapter);
		
		for(i=0;i<Select.length;i++){
			listadapter.addItem(Select[i], Area[i], Name[i], address[i], phone[i]);
		}
		
	}
	public class ViewHolder {
		public TextView UsefulArea_select;
		public TextView UsefulArea_name;
		public TextView UsefulArea_phone;
		public TextView UsefulArea_address;
		public TextView UsefulArea_area;
	}
	private class ListViewAdapter extends BaseAdapter {

		private Context mContext = null;
		private ArrayList<UsefulArea_ListData> mListData = new ArrayList<UsefulArea_ListData>();

		public ListViewAdapter(Context mContext) {
			super();
			this.mContext = mContext;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mListData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mListData.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public void addItem(String Select, String Area,String Name,String Address,String Phone) {
			UsefulArea_ListData addInfo = null;
			addInfo = new UsefulArea_ListData();
			addInfo.Select = Select;
			addInfo.Area = Area;
			addInfo.address = Address;
			addInfo.phone = Phone;
			addInfo.Name = Name;
			mListData.add(addInfo);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();

				LayoutInflater inflater = (LayoutInflater) mContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.custom_usefularea,
						null);

				holder.UsefulArea_select = (TextView) convertView
						.findViewById(R.id.tv_CustomUsefulArea_select);
				holder.UsefulArea_name = (TextView) convertView
						.findViewById(R.id.tv_CustomUsefulArea_Name);
				holder.UsefulArea_address = (TextView) convertView
						.findViewById(R.id.tv_CustomUsefulArea_address);
				holder.UsefulArea_phone = (TextView) convertView
						.findViewById(R.id.tv_CustomUsefulArea_Phone);
				holder.UsefulArea_area = (TextView) convertView
						.findViewById(R.id.tv_CustomUsefulArea_Area);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			UsefulArea_ListData mData = mListData.get(position);
			holder.UsefulArea_select.setText(mData.Select);
			holder.UsefulArea_address.setText(mData.address);
			holder.UsefulArea_name.setText(mData.Name);
			holder.UsefulArea_area.setText(mData.Area);
			holder.UsefulArea_phone.setText(mData.phone);
			

			return convertView;
		}

	}
}
