package com.example.g_ravel_goTravel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
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
import com.example.g_ravel_etcProgramming.Gravel_static;

public class MakeRoadLevel3 extends Activity {
	
	private String ss;
	private int i;
	private ListView AreaList;
	private JSONObject object;
	
	ArrayList<HashMap<String, String>> hashList = new ArrayList<HashMap<String, String>>();
	private String TAG_LIST_NAME = "list_name";
	//private String TAG_LIST_SELECT = "list_select";
	private String TAG_SMALL_AREA = "small_area";
	private String TAG_LIST_NUM = "list_num";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.makeroad_level3);
		
		hashList = new ArrayList<HashMap<String, String>>();
		new JsonParsor().execute(ss);
	}
	private class JsonParsor extends AsyncTask<String, Void, SimpleAdapter> {
		private ProgressDialog pDialog;
		private Gravel_static gs = (Gravel_static) getApplicationContext();
		private String search_select = gs.get_SELECT_SHOP_NAME();
		private String search_area = gs.get_SELECT_AREA();
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MakeRoadLevel3.this);
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
						"http://www.g-ravel.com/Choice_select_Area.php");
				HttpURLConnection http = (HttpURLConnection) url
						.openConnection();
				HttpURLConnection connection = http;

				connection.setRequestMethod("POST");
				connection.setDoInput(true);
				connection.setDoOutput(true);

				List<BasicNameValuePair> nameList = new ArrayList<BasicNameValuePair>(
						2);
				nameList.add(new BasicNameValuePair("Select", search_select));
				nameList.add(new BasicNameValuePair("AreaName", search_area));
				// nameList.add(new BasicNameValuePair("test",
				// "test"));

				OutputStream output = connection.getOutputStream();
				BufferedWriter b_writer = new BufferedWriter(
						new OutputStreamWriter(output, "UTF-8"));
				b_writer.write(getURLQuery(nameList));
				b_writer.flush();
				b_writer.close();
				output.close();

				connection.connect();

				StringBuilder response = new StringBuilder();
				if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader b_reader = new BufferedReader(
							new InputStreamReader(connection.getInputStream()));
					for (;;) {
						String sLine = b_reader.readLine();
						// Log.d("잘감 ?", sLine);
						if (sLine == null)
							break;
						response.append(sLine);
					}
					b_reader.close();
				}

				ss = response.toString();
				Log.d("찍기", ss);
				connection.disconnect();
			} catch (Exception e) {
				Log.d("인터넷 연결 오류", e.toString());
			}
			try {
				object = new JSONObject(ss);
			} catch (JSONException e) {
				Log.d("제이슨 오류1", e.toString());
			}

			// 제이슨 써보기
			try {
				// JSONObject object = new JSONObject(ss);
				JSONArray Array = object.getJSONArray("results");

				for (i = 0; i < Array.length(); i++) {
					JSONObject insideObject = Array.getJSONObject(i);

					Log.d("Index", insideObject.getString("index"));

					// 해쉬에 값 집어넣기
					String area_tx = insideObject.getString("small_area");
					String list_nm = insideObject.getString("info_name");
					String num = insideObject.getString("num");

					HashMap<String, String> map = new HashMap<String, String>();
					map.put(TAG_LIST_NAME, list_nm);
					map.put(TAG_SMALL_AREA, area_tx);
					map.put(TAG_LIST_NUM, num);

					hashList.add(map);
				}
			} catch (JSONException e) {
				Log.d("제이슨 오류", e.toString());
			}

			// Object check = map.get(TAG_INDEX);
			// String TestCheck = check.toString();
			int aa = hashList.size();
			String test = Integer.toString(aa);
			Log.d("해쉬값 테스트", test);

			SimpleAdapter adapter = new SimpleAdapter(MakeRoadLevel3.this,
					hashList, R.layout.custom_makeplanlevel3, new String[] {
							TAG_LIST_NAME,TAG_SMALL_AREA,TAG_LIST_NUM }, new int[] {
							R.id.tv_customMakePlanLevel3_Name,
							R.id.tv_customMakePlanLevel3_smallArea,
							R.id.tv_customMakePlanLevel3_Num});
			return adapter;
		}

		@Override
		protected void onPostExecute(SimpleAdapter adapter) {
			// TODO Auto-generated method stub
			pDialog.dismiss();
			Log.d("내려옴", "2");
			AreaList = (ListView) findViewById(R.id.lv_MakeRoadLevel3_list);
			AreaList.setAdapter(adapter);
			Log.d("내려옴", "5");

			try {
				AreaList.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						Intent go_next = new Intent(MakeRoadLevel3.this,
								MakeRoadLevel4.class);
						go_next.putExtra("Name_Shop", hashList.get(+position).get(TAG_LIST_NAME));
						startActivity(go_next);
						finish();

					}
				});
			} catch (Exception e) {
				Log.d("오류", e.toString());
			}
		}
	}

	private String getURLQuery(List<BasicNameValuePair> params) {
		StringBuilder stringBuilder = new StringBuilder();
		boolean first = true;

		for (BasicNameValuePair pair : params) {
			if (first)
				first = false;
			else
				stringBuilder.append("&");

			try {
				stringBuilder
						.append(URLEncoder.encode(pair.getName(), "UTF-8"));
				stringBuilder.append("=");
				stringBuilder
						.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return stringBuilder.toString();
	}
	
}
