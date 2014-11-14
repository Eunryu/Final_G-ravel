package com.example.g_ravel_setting;

import com.example.g_ravel.R;
import com.example.g_ravel_SQLite.Setting_Alarm;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

public class Setting_main extends Activity implements OnCheckedChangeListener {

	private String E_State, P_State;
	private String ON = "ON";
	private String OFF = "OFF";

	// 스위치
	private Switch EventSwitch, PlanSwitch;
	private Setting_Alarm SetAlarm;

	// 버전확인용
	private String NowVersion, NewVersion;
	private TextView Now, New;
	private Button Update;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_main);

		SetAlarm = new Setting_Alarm(this);
		try {
			SQLiteDatabase readsqlDB = SetAlarm.getReadableDatabase();
			Cursor cursor = readsqlDB.rawQuery(
					"SELECT EventAlarm_set,PlanAlarm_set FROM SettingAlarm",
					null);
			while (cursor.moveToNext()) {
				E_State = cursor.getString(0);
				Log.d("있음", E_State);
				P_State = cursor.getString(1);
				Log.d("있음", P_State);
			}
			cursor.close();
			readsqlDB.close();
			Toast.makeText(getApplicationContext(), "불러오기 완료",
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Log.d("오류", e.toString());
		}

		// 둘다 불렸는지 로그로 테스트
		Log.d("공지알림", E_State);
		Log.d("일정알림", P_State);

		// 스위치 선언
		EventSwitch = (Switch) findViewById(R.id.sw_SettingMain_EventAlam);
		PlanSwitch = (Switch) findViewById(R.id.sw_SettingMain_TravelAlam);
		EventSwitch.setOnCheckedChangeListener(this);
		PlanSwitch.setOnCheckedChangeListener(this);

		// SQLite 에서 가져온 값으로 일단 스위치에 적용
		// 이벤트알림
		if (E_State.equals(ON)) {
			EventSwitch.setChecked(true);
		} else if (E_State.equals(OFF)) {
			EventSwitch.setChecked(false);
		}

		// 여행알림
		if (P_State.equals(ON)) {
			PlanSwitch.setChecked(true);
		} else if (P_State.equals(OFF)) {
			PlanSwitch.setChecked(false);
		}

		// 버전확인
		Update = (Button) findViewById(R.id.btn_SettingMain_update);
		Now = (TextView) findViewById(R.id.tv_SettingMain_NowVersion);
		New = (TextView) findViewById(R.id.tv_SettingMain_NewVersion);

		NowVersion = Now.getText().toString();
		NewVersion = New.getText().toString();

		if (NowVersion.equals(NewVersion)) {
			Update.setEnabled(false);
		} else {
			Update.setEnabled(true);
		}

		// 업데이트가 있을시
		Update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent google = new Intent(Intent.ACTION_VIEW, Uri
						.parse("market://details?id=com.nhn.android.search"));
				startActivity(google);
			}
		});

		// 버튼선언
		Button appNotice = (Button) findViewById(R.id.btn_SettingMain_appNotice);

		appNotice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent goNotice = new Intent(Setting_main.this, AppNotice.class);
				startActivity(goNotice);
			}
		});

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub

		// 디비 읽어오기
		SQLiteDatabase writeDB;
		writeDB = SetAlarm.getWritableDatabase();

		// 이 아래부터는 디비값 변경.
		if (buttonView.getId() == R.id.sw_SettingMain_EventAlam) {
			if (isChecked == false) {
				String sql = "UPDATE SettingAlarm SET EventAlarm_set = '"
						+ "OFF" + "';";
				writeDB.execSQL(sql);
			} else if (isChecked == true) {
				String sql = "UPDATE SettingAlarm SET EventAlarm_set = '"
						+ "ON" + "';";
				writeDB.execSQL(sql);
			}
		} else if (buttonView.getId() == R.id.sw_SettingMain_TravelAlam) {
			if (isChecked == false) {
				String sql = "UPDATE SettingAlarm SET PlanAlarm_set = '"
						+ "OFF" + "';";
				writeDB.execSQL(sql);
			} else if (isChecked == true) {
				String sql = "UPDATE SettingAlarm SET PlanAlarm_set = '" + "ON"
						+ "';";
				writeDB.execSQL(sql);
			}
		}
	}
}
