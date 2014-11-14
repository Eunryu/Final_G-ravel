package com.example.g_ravel_travelNote_event;

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
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.g_ravel.R;

public class Event_info extends Activity implements Runnable{
	
	private String EventName,EventStart,EventEnd,EventMessage;
	private TextView eventName,eventStart,eventEnd,eventMessage;
	
	private int i;
	private JSONObject object;
	private String ss;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_info);
		
		(new Thread(this)).start();
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			Intent getData = getIntent();
			String getName = getData.getStringExtra("EVENT_NAME");

			URL url = new URL("http://www.g-ravel.com/read_event.php");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			HttpURLConnection connection = http;

			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);

			List<BasicNameValuePair> nameList = new ArrayList<BasicNameValuePair>(
					2);
			nameList.add(new BasicNameValuePair("event_name", getName));
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

				EventName = insideObject.getString("event_name");
				EventStart = insideObject.getString("event_start");
				EventEnd = insideObject.getString("event_end");
				EventMessage = insideObject.getString("event_message");
				
			}
		} catch (JSONException e) {
			Log.d("제이슨 오류", e.toString());
		}

		eventName = (TextView) findViewById(R.id.tv_EventView_title);
		eventStart = (TextView) findViewById(R.id.tv_EventView_DateStart);
		eventEnd = (TextView) findViewById(R.id.tv_EventView_DateEnd);
		eventMessage = (TextView) findViewById(R.id.tv_EventView_Message);

		eventName.setText(EventName);
		eventStart.setText(EventStart);
		eventEnd.setText(EventEnd);
		eventMessage.setText(EventMessage);
		
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
