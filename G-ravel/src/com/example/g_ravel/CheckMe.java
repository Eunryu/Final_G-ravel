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

		// ����ȭ��ȣ �ҷ�����
		myPhone = (TextView) findViewById(R.id.tv_Checkme_myphone);
		telephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		myPhone.setText(telephony.getLine1Number());
		Log.d("�׽�Ʈ", telephony.getLine1Number());

		// ��ư����
		Button Ok = (Button) findViewById(R.id.btn_Checkme_check);
		Button Cancle = (Button) findViewById(R.id.btn_Checkme_cancle);
		Button SendOK = (Button) findViewById(R.id.btn_Checkme_sendOK);

		// ��ư ����ֱ�
		Ok.setOnClickListener(this);
		Cancle.setOnClickListener(this);
		SendOK.setOnClickListener(this);
	}

	// ��Ŭ�� ������
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btn_Checkme_check) {
			String testNumber = gs.getrandomNum();
			String myInput = inputNumber.getText().toString();

			if (testNumber.equals(myInput)) {
				Toast.makeText(getApplicationContext(), "�����Ǿ����ϴ�.",
						Toast.LENGTH_SHORT).show();
				MainThread main = new MainThread();
				main.start();
				Intent goNext = new Intent(CheckMe.this,Login.class);
				startActivity(goNext);
				finish();
			} else {
				Toast.makeText(this, "��ġ ���Ѵ��.. �ٽþ�����.", Toast.LENGTH_SHORT)
						.show();
			}
		} else if (v.getId() == R.id.btn_Checkme_cancle) {
			Intent go_back = new Intent(CheckMe.this,JoinWrite.class);
			startActivity(go_back);
			finish();
		} else if (v.getId() == R.id.btn_Checkme_sendOK) {
			// ������ȣ ���� �ڵ�
			// step1. �����߻�
			Random random = new Random();
			// Random random = new Random(System.currentTimeMillis());
			int randomNumber = Math.abs(random.nextInt(9000) + 1000);
			String testNum = Integer.toString(randomNumber);
			Log.d("����", testNum);

			// step2. �޽���������
			SmsManager smsManager = SmsManager.getDefault();
			String myP = telephony.getLine1Number();
			smsManager.sendTextMessage(myP, null, testNum, null, null);
			Toast.makeText(CheckMe.this, "���ۿϷ�", Toast.LENGTH_SHORT).show();
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
