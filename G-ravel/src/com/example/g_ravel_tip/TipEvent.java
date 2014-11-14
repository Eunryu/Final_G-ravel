package com.example.g_ravel_tip;

import java.util.ArrayList;

import com.example.g_ravel.R;
import com.example.g_ravel_etcProgramming.Event_ListData;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class TipEvent extends Activity {

	// ����.��� �Ѵ� Ŀ���� ���ΰ� ������...:) �׾���..��;
	private String[] Canival_title = { "��������", "��������", "��������", "��������", "��������" };
	private String[] Event_title = { "�����̺�Ʈ", "�����̺�Ʈ", "�����̺�Ʈ", "�����̺�Ʈ",
			"�����̺�Ʈ" };
	private String[] Canival_startDate = { "14.09.09", "14.09.09", "14.09.09",
			"14.09.09", "14.09.09" };
	private String[] Canival_EndDate = { "14.10.10", "14.10.10", "14.10.10",
			"14.10.10", "14.10.10" };
	private String[] Event_startDate = { "14.09.09", "14.09.09", "14.09.09",
			"14.09.09", "14.09.09" };
	private String[] Event_EndDate = { "14.10.10", "14.10.10", "14.10.10",
			"14.10.10", "14.10.10" };

	private ListViewAdapter mAdapter;
	private ListView TipEventList;
	private int i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tip_event);

		Log.d("1", "1");
		TipEventList = (ListView) findViewById(R.id.lv_TipEvent_Eventlist);
		// TipCanivalList = (ListView)
		// findViewById(R.id.lv_TipEvent_Carnivallist);
		Log.d("2", "2");
		// ����,��縦 ���� �迭�� ���� �� �ƴ��Ϳ� �ֱ�
		String[] spinner = { "���", "����" };
		Log.d("3", "3");
		ArrayAdapter<String> SpinnerList = new ArrayAdapter<String>(
				TipEvent.this, android.R.layout.simple_dropdown_item_1line,
				spinner);
		Log.d("4", "4");
		mAdapter = new ListViewAdapter(TipEvent.this);
		// eAdapter = new ListViewAdapter(TipEvent.this);
		Log.d("5", "5");
		// ���ǳ�,����Ʈ�� ����
		Spinner choiceSpinner = (Spinner) findViewById(R.id.sp_TipEvent_list);
		choiceSpinner.setAdapter(SpinnerList);

		TipEventList.setAdapter(mAdapter);

//		for (i = 0; i < Event_title.length; i++) {
//			mAdapter.addItem(Event_title[i], Event_startDate[i],
//					Event_EndDate[i]);
//		}
		// for (i = 0; i < Canival_title.length; i++) {
		// eAdapter.addItem(Canival_title[i], Canival_startDate[i],
		// Canival_EndDate[i]);
		// }
		Log.d("6", "6");
		// TipEventList.setAdapter(mAdapter);
		// TipCanivalList.setAdapter(eAdapter);
		Log.d("7", "7");

		// ���ǳʸ� ���� ����Ʈ ����
		// choiceSpinner.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		// // TODO Auto-generated method stub
		// if (position == 0) {
		// Log.d("1","1");
		// TipEventList.setVisibility(View.VISIBLE);
		// TipCanivalList.setVisibility(View.INVISIBLE);
		// } else if (position == 1) {
		// Log.d("1","1");
		// TipEventList.setVisibility(View.INVISIBLE);
		// TipCanivalList.setVisibility(View.VISIBLE);
		// }
		// }
		// });
	}

	private class ViewHolder {
		public TextView Title;
		public TextView StartDate;
		public TextView EndDate;
	}

	private class ListViewAdapter extends BaseAdapter {

		private Context mContext = null;
		private ArrayList<Event_ListData> mListData = new ArrayList<Event_ListData>();

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

		public void addItem(String title, String startDate ,String endDate) {
			
			Event_ListData addInfo = null;
			addInfo = new Event_ListData();
			
			addInfo.ListTitle = title;
			addInfo.ListStartDate = startDate;
			addInfo.ListEndDate = endDate;
			
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
				convertView = inflater.inflate(
						R.layout.event_canival_customlist, null);

				holder.Title = (TextView) findViewById(R.id.tv_TipEvent_customListTitle);
				holder.StartDate = (TextView) findViewById(R.id.tv_TipEvent_customListStartDate);
				holder.EndDate = (TextView) findViewById(R.id.tv_TipEvent_customListEndDate);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Event_ListData mData = mListData.get(position);
			holder.Title.setText(mData.ListTitle);
			holder.StartDate.setText(mData.ListStartDate);
			holder.EndDate.setText(mData.ListEndDate);

			return convertView;
		}
	}
}
