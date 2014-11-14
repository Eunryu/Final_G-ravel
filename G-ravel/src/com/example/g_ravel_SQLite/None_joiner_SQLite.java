package com.example.g_ravel_SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class None_joiner_SQLite extends SQLiteOpenHelper{

	public None_joiner_SQLite(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO 자동 생성된 생성자 스텁
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 자동 생성된 메소드 스텁
		String sql = "CREATE TABLE none_travel_now" +
				"(T_area varchar2(15),T_zone varchar2(25),T_name varchar2(30),T_address varchar2(200),T_url varchar2(40)" +
				",T_tel varchar2(15));";
	
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 자동 생성된 메소드 스텁
		db.execSQL("drop table if exists card");
	}

}