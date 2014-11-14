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
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.g_ravel.MemberMain;
import com.example.g_ravel.R;
import com.example.g_ravel_SQLite.None_joiner_SQLite;
import com.example.g_ravel_SQLite.Train_mapSqlite;
import com.example.g_ravel_etcProgramming.Gravel_static;

public class MakeRoadLevel4 extends Activity implements Runnable {

	private String ss;
	private int i;
	private JSONObject object;
	private Gravel_static gs;
	private String name;
	private String address;
	private String phone;
	private String URL_address;
	private String message;
	private String search_select;
	private String search_area;

	// �ؽ�Ʈ��
	private TextView Name;
	private TextView Address;
	private TextView Phone;
	private TextView URL_Address;
	private TextView Message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.makeroad_level4);

		(new Thread(this)).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			gs = (Gravel_static) getApplicationContext();
			search_select = gs.get_SELECT_SHOP_NAME();
			search_area = gs.get_SELECT_AREA();

			Intent getData = getIntent();
			String getSelect = getData.getStringExtra("Name_Shop");

			URL url = new URL("http://www.g-ravel.com/Find_DetailInfo.php");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			HttpURLConnection connection = http;

			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);

			List<BasicNameValuePair> nameList = new ArrayList<BasicNameValuePair>(
					2);
			nameList.add(new BasicNameValuePair("Select", search_select));
			nameList.add(new BasicNameValuePair("Name", getSelect));
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

				name = insideObject.getString("info_name");
				address = insideObject.getString("info_address");
				phone = "0" + insideObject.getString("info_phone");
				URL_address = insideObject.getString("info_URL");
				message = insideObject.getString("info_message");

			}
		} catch (JSONException e) {
			Log.d("���̽� ����", e.toString());
		}

		Name = (TextView) findViewById(R.id.tv_Areainfo_name);
		Address = (TextView) findViewById(R.id.tv_Areainfo_address);
		Phone = (TextView) findViewById(R.id.tv_Areainfo_phone);
		URL_Address = (TextView) findViewById(R.id.tv_Areainfo_URL);
		Message = (TextView) findViewById(R.id.tv_MakePlanLevel4_detail);

		Name.setText(name);
		Address.setText(address);
		Phone.setText(phone);
		URL_Address.setText(URL_address);
		Message.setText(message);
		
		try{
			//SQLite ����
			Train_mapSqlite t_sqlite = new Train_mapSqlite(this, "Project_travel.db", null, 1);
			final SQLiteDatabase db = t_sqlite.getWritableDatabase();
			
			None_joiner_SQLite n_sqlite = new None_joiner_SQLite(this, "None_Project_travel.db", null, 1);
			final SQLiteDatabase ndb = n_sqlite.getWritableDatabase();
			
			//ȸ������ �ƴ��� �Ǵ�.
			final String mem = gs.get_KIMI_MEMBER();
			
			// ��ư����
			Button add = (Button) findViewById(R.id.btn_MakeRoadLevel5_add);
			add.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(mem.equals("MEMBER")){
						db.execSQL("INSERT INTO travel_now VALUES('"+search_area+"','"+search_select+"','"+name+"','"+address+"','"+URL_address+"','"+phone+"');");
					}else if(mem.equals("NON_MEMBER")){
						ndb.execSQL("INSERT INTO none_travel_now VALUES('"+search_area+"','"+search_select+"','"+name+"','"+address+"','"+URL_address+"','"+phone+"');");
					}
					
					Log.d("��", "DB�Է¿Ϸ�");
					AlertDialog.Builder dlg = new AlertDialog.Builder(MakeRoadLevel4.this);
					dlg.setTitle("��Ҽ��� �Ϸ�");
					dlg.setMessage("�ٸ� ��Ҹ� �� �����Ͻðڽ��ϱ� ?");
					dlg.setPositiveButton("�� ����", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO �ڵ� ������ �޼ҵ� ����
							Intent intent3 = new Intent(getApplicationContext(),MakeRoadLevel1.class);
							startActivity(intent3);
							finish();
						}
					});
					dlg.setNegativeButton("�ƴϿ�", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO �ڵ� ������ �޼ҵ� ����
							Toast.makeText(getApplicationContext(), "���� ���� �̵��մϴ�.", Toast.LENGTH_SHORT).show();
							Intent intent4 = new Intent(getApplicationContext(),MemberMain.class);
							startActivity(intent4);
							finish();
						}
					});
					dlg.show();
				}
			});

			final String phoneNum = "tel:" + Phone.getText().toString();
			final String InternetURL = URL_Address.getText().toString();

			// ��ȭ�ɱ� �� ���ͳ�
			Button Call = (Button) findViewById(R.id.btn_AreaInfo_bookPhone);
			Button Internet = (Button) findViewById(R.id.btn_AreaInfo_bookURL);

			Call.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent CallPhone = new Intent(Intent.ACTION_VIEW, Uri
							.parse(phoneNum));
					startActivity(CallPhone);
				}
			});

			Internet.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent OpenInternet = new Intent(Intent.ACTION_VIEW, Uri
							.parse(InternetURL));
					startActivity(OpenInternet);
				}
			});
		}catch(Exception e){
			e.printStackTrace();
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
