package com.example.g_ravel_goTravel;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.g_ravel.R;
import com.example.g_ravel_etcProgramming.Gravel_static;

public class BestRoadLevel2 extends Activity {

	// 했다 !!!!!
	private String ss;
	private int i;
	private ListView AreaList;
	private JSONObject object;
	
	//전역변수에 값 저장
	//private Gravel_static gs;

	// 예제 보니까 HashMap 이용해서 집어넣을 예정인듯... 좋은 방법인거 같다.
	ArrayList<HashMap<String, String>> hashList = new ArrayList<HashMap<String, String>>();
	private String TAG_INDEX = "index";
	private String TAG_AREA_NAME = "Area_Name";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bestroad_level2);

		hashList = new ArrayList<HashMap<String, String>>();
		new JsonParsor().execute(ss);

	}

	private class JsonParsor extends AsyncTask<String, Void, SimpleAdapter> {
		private ProgressDialog pDialog;
		private Gravel_static gs = (Gravel_static)getApplicationContext();
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(BestRoadLevel2.this);
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
						"http://instru93.hubweb.net/Find_smallArea.php");
				HttpURLConnection http = (HttpURLConnection) url
						.openConnection();
				HttpURLConnection connection = http;

				connection.setRequestMethod("POST");
				connection.setDoInput(true);
				connection.setDoOutput(true);

				StringBuilder response = new StringBuilder();
				if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader b_reader = new BufferedReader(
							new InputStreamReader(connection.getInputStream()));
					for (;;) {
						String sLine = b_reader.readLine();
						// Log.d("잘감 ?", sLine);
						if (sLine == null)
							break;
						response.append(sLine + '\n');
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
				Log.d("제이슨 오류", e.toString());
			}

			// 제이슨 써보기
			try {
				// JSONObject object = new JSONObject(ss);
				JSONArray Array = object.getJSONArray("results");

				for (i = 0; i < Array.length(); i++) {
					JSONObject insideObject = Array.getJSONObject(i);

					Log.d("Index", insideObject.getString("index"));
					Log.d("Small_Area", insideObject.getString("Area_Name"));

					// 해쉬에 값 집어넣기
					String index_tx = insideObject.getString("index");
					String area_tx = insideObject.getString("Area_Name");

					Log.d("내려옴", "1");
					HashMap<String, String> map = new HashMap<String, String>();
					map.put(TAG_INDEX, index_tx);
					map.put(TAG_AREA_NAME, area_tx);

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

			SimpleAdapter adapter = new SimpleAdapter(BestRoadLevel2.this,
					hashList, R.layout.custom_smallarea, new String[] {
							TAG_INDEX, TAG_AREA_NAME }, new int[] {
							R.id.tv_customSmallArea_index,
							R.id.tv_customSmallArea_Area });
			return adapter;
		}

		@Override
		protected void onPostExecute(SimpleAdapter adapter) {
			// TODO Auto-generated method stub
			pDialog.dismiss();
			Log.d("내려옴", "2");
			AreaList = (ListView) findViewById(R.id.lv_BestRoadLevel2_AreaList);
			AreaList.setAdapter(adapter);
			Log.d("내려옴", "5");

			try {
				AreaList.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						gs.set_SELECT_AREA(hashList.get(+position).get(TAG_AREA_NAME));
						Intent go_next = new Intent(BestRoadLevel2.this,BestRoadLevel3.class);
						startActivity(go_next);
						finish();

					}
				});
			} catch (Exception e) {
				Log.d("오류", e.toString());
			}
			Intent data = getIntent();
			String test = data.getStringExtra("ButtonName");

			TextView NameTitle = (TextView) findViewById(R.id.tv_BestRoadLevel2_prevChoice);
			NameTitle.setText(test);
		}
	}
}
