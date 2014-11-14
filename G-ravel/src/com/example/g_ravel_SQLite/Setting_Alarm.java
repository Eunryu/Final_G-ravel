package com.example.g_ravel_SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Setting_Alarm extends SQLiteOpenHelper {

	public Setting_Alarm(Context context) {
		super(context, "SettingAlarm_DB", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE SettingAlarm (EventAlarm_set VARCHAR2(20),PlanAlarm_set VARCHAR2(20));";
		db.execSQL(sql);
		String sql2 = "INSERT INTO SettingAlarm VALUES('"+ "ON" +"','"+ "OFF" +"');";
		db.execSQL(sql2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String sql = "DROP TABLE IF EXISTS SettingAlarm";
		db.execSQL(sql);
		onCreate(db);
	}

}
