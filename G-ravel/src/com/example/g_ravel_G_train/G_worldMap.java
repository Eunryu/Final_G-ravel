package com.example.g_ravel_G_train;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.g_ravel.R;
import com.example.g_ravel_SQLite.None_joiner_SQLite;
import com.example.g_ravel_SQLite.Train_mapSqlite;
import com.example.g_ravel_etcProgramming.Gravel_static;

public class G_worldMap extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.g_worldmap);

		ArrayList<String> list = new ArrayList<String>();

		Gravel_static gs = (Gravel_static)getApplicationContext();
		String user = gs.get_KIMI_MEMBER();

		if (user.equals("MEMBER")) {
			try {
				Train_mapSqlite t_sqlite = new Train_mapSqlite(this,
						"Project_travel.db", null, 1);
				// Log.d("db 불러오기", "Access DataBase1");
				SQLiteDatabase db = t_sqlite.getWritableDatabase();
				// Log.d("db 불러오기2", "Access DataBase2");
				Cursor c = db.rawQuery("select T_name from travel_now", null);
				// Log.d("쿼리", "Access Query");
				c.moveToFirst();
				while (!(c.isAfterLast())) {
					String data = "";
					for (int i = 0; i < c.getColumnCount(); i++) {
						data = data.concat(c.getString(0));
						Log.d("불러옴", data);
					}
					list.add(data);
					c.moveToNext();
				}
			} catch (Exception e) {
				Log.d("오류 : ", e.toString());
			}
		} else if (user.equals("NON_MEMBER")) {
			try {
				None_joiner_SQLite n_sqlite = new None_joiner_SQLite(this,
						"None_Project_travel.db", null, 1);
				// Log.d("db 불러오기", "Access DataBase1");
				SQLiteDatabase ndb = n_sqlite.getWritableDatabase();
				// Log.d("db 불러오기2", "Access DataBase2");
				Cursor c = ndb.rawQuery("select T_name from none_travel_now",
						null);
				// Log.d("쿼리", "Access Query");
				c.moveToFirst();
				while (!(c.isAfterLast())) {
					String data = "";
					for (int i = 0; i < c.getColumnCount(); i++) {
						data = data.concat(c.getString(0));
						Log.d("불러옴", data);
					}
					list.add(data);
					c.moveToNext();
				}
			} catch (Exception e) {
				Log.d("오류 : ", e.toString());
			}
		}

		ListView trainmap_ly = (ListView) findViewById(R.id.lv_GMap_listview);
		Log.d("실행완료", "리스트뷰 생성완료");
		trainmap_ly.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, list));
		Log.d("실행완료", "어댑터 생성완료");
	}

}
