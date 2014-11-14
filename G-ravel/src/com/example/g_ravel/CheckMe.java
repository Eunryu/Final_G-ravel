package com.example.g_ravel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.g_ravel_etcProgramming.Gravel_static;

public class CheckMe extends Activity implements OnClickListener {

	private TextView myPhone;
	private TelephonyManager telephony;
	private Gravel_static gs;
	private EditText inputNumber;

	private String MyId, MyPhone, MyEmail, MyNickname, MyAddress, MyPassword,
			ss;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkme);

		inputNumber = (EditText) findViewById(R.id.et_Checkme_Number);
		gs = (Gravel_static) getApplicationContext();

		// 내전화번호 불러오기
		myPhone = (TextView) findViewById(R.id.tv_Checkme_myphone);
		telephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		myPhone.setText(telephony.getLine1Number());
		Log.d("테스트", telephony.getLine1Number());

		// 버튼선언
		Button Ok = (Button) findViewById(R.id.btn_Checkme_check);
		Button Cancle = (Button) findViewById(R.id.btn_Checkme_cancle);
		Button SendOK = (Button) findViewById(R.id.btn_Checkme_sendOK);

		// 버튼 기능주기
		Ok.setOnClickListener(this);
		Cancle.setOnClickListener(this);
		SendOK.setOnClickListener(this);
	}

	// 온클릭 리스너
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btn_Checkme_check) {
			String testNumber = gs.getrandomNum();
			String myInput = inputNumber.getText().toString();

			if (testNumber.equals(myInput)) {
				Toast.makeText(getApplicationContext(), "인증되었습니다.",
						Toast.LENGTH_SHORT).show();
				MainThread main = new MainThread();
				main.start();
				Intent goNext = new Intent(CheckMe.this,Login.class);
				startActivity(goNext);
				finish();
			} else {
				Toast.makeText(this, "일치 안한대요.. 다시쓰세요.", Toast.LENGTH_SHORT)
						.show();
			}
		} else if (v.getId() == R.id.btn_Checkme_cancle) {
			Intent go_back = new Intent(CheckMe.this,JoinWrite.class);
			startActivity(go_back);
			finish();
		} else if (v.getId() == R.id.btn_Checkme_sendOK) {
			// 인증번호 보낼 코드
			// step1. 난수발생
			Random random = new Random();
			// Random random = new Random(System.currentTimeMillis());
			int randomNumber = Math.abs(random.nextInt(9000) + 1000);
			String testNum = Integer.toString(randomNumber);
			Log.d("난수", testNum);

			// step2. 메시지보내기
			SmsManager smsManager = SmsManager.getDefault();
			String myP = telephony.getLine1Number();
			smsManager.sendTextMessage(myP, null, testNum, null, null);
			Toast.makeText(CheckMe.this, "전송완료", Toast.LENGTH_SHORT).show();
			gs.setrandomNum(testNum);
		}
	}

	private class MainThread extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Intent GetData = getIntent();
			MyId = GetData.getStringExtra("NextId");
			MyNickname = GetData.getStringExtra("NextNickname");
			MyAddress = GetData.getStringExtra("NextAddress");
			MyEmail = GetData.getStringExtra("NextEmail");
			MyPhone = GetData.getStringExtra("NextPhone");
			MyPassword = GetData.getStringExtra("NextPassword");

			try {
				URL url = new URL("http://www.g-ravel.com/Join.php");
				HttpURLConnection http = (HttpURLConnection) url
						.openConnection();
				HttpURLConnection connection = http;

				connection.setRequestMethod("POST");
				connection.setDoInput(true);
				connection.setDoOutput(true);

				Log.d("1", MyId);
				Log.d("2", MyNickname);
				Log.d("3", MyPhone);
				Log.d("4", MyAddress);
				Log.d("5", MyEmail);
				Log.d("6", MyPassword);

				List<BasicNameValuePair> nameList = new ArrayList<BasicNameValuePair>(
						6);
				nameList.add(new BasicNameValuePair("Id", MyId));
				nameList.add(new BasicNameValuePair("NickName", MyNickname));
				nameList.add(new BasicNameValuePair("Phone", MyPhone));
				nameList.add(new BasicNameValuePair("Address", MyAddress));
				nameList.add(new BasicNameValuePair("Email", MyEmail));
				nameList.add(new BasicNameValuePair("Password", MyPassword));

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

			} catch (IOException e) {
				e.printStackTrace();
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
