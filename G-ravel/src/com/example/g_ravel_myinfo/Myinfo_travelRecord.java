package com.example.g_ravel_myinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.g_ravel.R;
import com.example.g_ravel_etcProgramming.MyTravelRecord_ListData;

public class Myinfo_travelRecord extends Activity {

	// 테스트 배열
	private String[] Area = { "성남", "용인", "안산", "안산", "용인", "용인" };
	private String[] Date = { "9월 8일", "9월 8일", "9월 8일", "9월 9일", "9월 9일",
			"9월10일" };

	// 리스트 아답터
	private ListViewAdapter mAdapter;
	private int i;
	private String ss;
	private ListView MyRecord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myinfo_travelrecord);

		ListView MyRecord = (ListView) findViewById(R.id.lv_TravelRecord_list);
		mAdapter = new ListViewAdapter(Myinfo_travelRecord.this);
		MyRecord.setAdapter(mAdapter);

		Log.d("내려옴 ?", "ㅇㅇ");
		for (i = 0; i < Area.length; i++) {
			mAdapter.addItem(Area[i], Date[i]);
		}

		MyRecord.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				MyTravelRecord_ListData mData = mAdapter.mListData
						.get(position);
				String Put = mData.MyDate.toString();
				Toast.makeText(getApplicationContext(), Put, Toast.LENGTH_LONG)
						.show();

				// 인텐트 값넘기기
				Intent Next = new Intent(Myinfo_travelRecord.this,
						TravelRecordDetail.class);
				Next.putExtra("PutData", Put);
				startActivity(Next);
			}
		});
	}

	// 이 아래부터 커스텀 리스트뷰.
	private class ViewHolder {
		public TextView TravelArea;
		public TextView TravelDate;
	}

	private class ListViewAdapter extends BaseAdapter {

		private Context mContext = null;
		private ArrayList<MyTravelRecord_ListData> mListData = new ArrayList<MyTravelRecord_ListData>();

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

		public void addItem(String MyBigArea, String MyDate) {
			MyTravelRecord_ListData addInfo = null;
			addInfo = new MyTravelRecord_ListData();
			addInfo.MyBigArea = MyBigArea;
			addInfo.MyDate = MyDate;

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
				convertView = inflater.inflate(R.layout.custom_travelrecord,
						null);

				holder.TravelArea = (TextView) convertView
						.findViewById(R.id.tv_TravelRecord_BigArea);
				holder.TravelDate = (TextView) convertView
						.findViewById(R.id.tv_TravelRecord_Date);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			MyTravelRecord_ListData mData = mListData.get(position);
			holder.TravelArea.setText(mData.MyBigArea);
			holder.TravelDate.setText(mData.MyDate);

			return convertView;
		}

	}
}
