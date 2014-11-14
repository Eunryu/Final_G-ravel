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

public class TravelNote_Read extends Activity {
	
	private String ss;
	private int i;
	private JSONObject object;
	//����Ʈ�� ������
	private String send_title,send_writer;
	//����� ���� �����..
	@SuppressWarnings("unused")
	private String Title,Writer,Date,Message,TravelList,BestNum;
	//����� ���� ����..
	private String Name,P_Message;
	
	private TextView title,writer,date,message,bestnum;
	private TextView name,p_message;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.travelnote_read);
		
		thread_A A = new thread_A();
		A.start();
	}
	
	public class thread_A extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			//���� ���� ���� �ҷ����ô�.
			try{
				Intent send = getIntent();
				send_title = send.getStringExtra("Title");
				send_writer = send.getStringExtra("Writer");
				
				Log.d("�� ����", send_title);
				Log.d("�۾����", send_writer);
				
				URL url = new URL("http://www.g-ravel.com/TravelNote_read.php");
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				HttpURLConnection connection = http;

				connection.setRequestMethod("POST");
				connection.setDoInput(true);
				connection.setDoOutput(true);

				List<BasicNameValuePair> nameList = new ArrayList<BasicNameValuePair>(
						2);
				nameList.add(new BasicNameValuePair("title", send_title));
				nameList.add(new BasicNameValuePair("writer", send_writer));
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
			}catch(Exception e){
				Log.d("���ͳ� ����", e.toString());
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

					Log.d("Index", insideObject.getString("note_roadlist"));

					Title = insideObject.getString("note_title");
					Writer = insideObject.getString("member_id");
					Date = insideObject.getString("note_date");
					Message = insideObject.getString("note_message");
					TravelList = insideObject.getString("note_roadlist");
					BestNum = insideObject.getString("note_goodnum");
					
				}
			} catch (JSONException e) {
				Log.d("���̽� ����", e.toString());
			}
			
			title = (TextView)findViewById(R.id.tv_TravelNoteRead_title);
			writer = (TextView)findViewById(R.id.tv_TravelNoteRead_writer);
			date = (TextView)findViewById(R.id.tv_TravelNoteRead_Date);
			bestnum = (TextView)findViewById(R.id.tv_TravelNoteRead_Likenum);
			message = (TextView)findViewById(R.id.tv_TravelNoteRead_Message);
			
			title.setText(Title);
			writer.setText(Writer);
			date.setText(Date);
			bestnum.setText(BestNum);
			message.setText(Message);
			
			
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
