package com.example.g_ravel_travelNote_event;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.g_ravel.R;

public class Event_main extends Activity {
	private String ss;
	private int i;
	private ListView AreaList;
	private JSONObject object;

	ArrayList<HashMap<String, String>> hashList = new ArrayList<HashMap<String, String>>();
	//private String TAG_INDEX = "index";
	private String TAG_LIST_NAME = "list_name";
	private String TAG_EVENT_START = "event_start";
	private String TAG_EVENT_END = "event_end";
	private String TAG_EVENT_MESSAGE = "event_message";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event);
		
		hashList = new ArrayList<HashMap<String, String>>();
		new JsonParsor().execute(ss);

	}
	private class JsonParsor extends AsyncTask<String, Void, SimpleAdapter> {
		private ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Event_main.this);
			pDialog.setMessage("Getting Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected SimpleAdapter doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				URL url = new URL(
						"http://www.g-ravel.com/event.php");
				HttpURLConnection http = (HttpURLConnection) url
						.openConnection();
				HttpURLConnection connection = http;

				connection.setRequestMethod("POST");
				connection.setDoInput(true);
				connection.setDoOutput(true);

				connection.connect();

				StringBuilder response = new StringBuilder();
				if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader b_reader = new BufferedReader(
							new InputStreamReader(connection.getInputStream()));
					for (;;) {
						String sLine = b_reader.readLine();
						// Log.d("�߰� ?", sLine);
						if (sLine == null)
							break;
						response.append(sLine + '\n');
					}
					b_reader.close();
				}

				ss = response.toString();
				Log.d("���", ss);
				connection.disconnect();
			} catch (Exception e) {
				Log.d("���ͳ� ���� ����", e.toString());
			}
			try {
				object = new JSONObject(ss);
			} catch (JSONException e) {
				Log.d("���̽� ����", e.toString());
			}

			// ���̽� �Ẹ��
			try {
				// JSONObject object = new JSONObject(ss);
				JSONArray Array = object.getJSONArray("results");

				for (i = 0; i < Array.length(); i++) {
					JSONObject insideObject = Array.getJSONObject(i);

					Log.d("Index", insideObject.getString("index"));
					//Log.d("Small_Area", insideObject.getString("Area_Name"));

					// �ؽ��� �� ����ֱ�
					//String index_tx = insideObject.getString("index");
					String list_nm = insideObject.getString("event_name");
					String ev_start = insideObject.getString("event_start");
					String ev_end = insideObject.getString("event_end");
					String ev_message = insideObject.getString("event_message");

					HashMap<String, String> map = new HashMap<String, String>();
					//map.put(TAG_INDEX, index_tx);
					map.put(TAG_LIST_NAME, list_nm);
					map.put(TAG_EVENT_START, ev_start);
					map.put(TAG_EVENT_END, ev_end);
					map.put(TAG_EVENT_MESSAGE, ev_message);

					hashList.add(map);
				}
			} catch (JSONException e) {
				Log.d("���̽� ����", e.toString());
			}

			// Object check = map.get(TAG_INDEX);
			// String TestCheck = check.toString();
			int aa = hashList.size();
			String test = Integer.toString(aa);
			Log.d("�ؽ��� �׽�Ʈ", test);

			SimpleAdapter adapter = new SimpleAdapter(Event_main.this,
					hashList, R.layout.custom_eventlist, new String[] {
							TAG_LIST_NAME,TAG_EVENT_START,TAG_EVENT_END,TAG_EVENT_MESSAGE}, new int[] {
							R.id.tv_customEvent_name,
							R.id.tv_customEvent_StartDate,
							R.id.tv_customEvent_EndDate,
							R.id.tv_customEvent_message});
			return adapter;
		}

		@Override
		protected void onPostExecute(SimpleAdapter adapter) {
			// TODO Auto-generated method stub
			pDialog.dismiss();
			Log.d("������", "2");
			AreaList = (ListView) findViewById(R.id.lv_Event_eventInfo);
			AreaList.setAdapter(adapter);
			Log.d("������", "5");

			try {
				AreaList.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						Intent go_next = new Intent(Event_main.this,
								Event_info.class);
						go_next.putExtra("EVENT_NAME", hashList.get(+position).get(TAG_LIST_NAME));
						startActivity(go_next);
						
					}
				});
			} catch (Exception e) {
				Log.d("����", e.toString());
			}
		}
	}

}
