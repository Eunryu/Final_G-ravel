package com.example.g_ravel_G_train;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.g_ravel.R;
import com.example.g_ravel_SQLite.None_joiner_SQLite;
import com.example.g_ravel_SQLite.Train_mapSqlite;
import com.example.g_ravel_etcProgramming.Gravel_static;

public class G_Train extends Activity {

	private List<String> list = new ArrayList<String>();
	private String final_text;
	private Button prev, next;
	private String first_text;

	private int size = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.g_train);

		Gravel_static gs = (Gravel_static) getApplicationContext();
		String user_form = gs.get_KIMI_MEMBER();

		if (user_form.equals("MEMBER")) {
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
						list.add(c.getString(0));
						data = data.concat(c.getString(0));
						Log.d("불러옴", data);
					}
					c.moveToNext();
				}
			} catch (Exception e) {
				Log.d("오류 : ", e.toString());
			}
		} else if (user_form.equals("NON_MEMBER")) {
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
					c.moveToNext();
				}
			} catch (Exception e) {
				Log.d("오류 : ", e.toString());
			}
		}

		size = list.size() - 1;

		prev = (Button) findViewById(R.id.btn_GTrain_prev);
		next = (Button) findViewById(R.id.btn_GTrain_next);

		// 일단 텍뷰 선언.
		final TextView text = (TextView) findViewById(R.id.tv_list);
		text.setText(list.get(0).toString());

		// 스트링값 미치 받아놓는데 제일 마지막에 있는 놈하고 처음거 받아요.
		final_text = list.get(size).toString();
		first_text = list.get(1).toString();

		// 초기설정.
		prev.setEnabled(false);
		if (list.size() == 1) {
			next.setEnabled(false);
		}

		prev.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (text.getText().toString().equals(first_text)) {
					prev.setEnabled(false);
				}

				size --;
				text.setText(list.get(size).toString());
			}
		});

		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("이 밑에부터 오류 1-1", "ㅇㅇ");
				if (text.getText().toString().equals(final_text)) {
					next.setEnabled(false);
				}
				Log.d("이 밑에부터 오류 1", "ㅇㅇ");
				size = size + 1;
				text.setText(list.get(size).toString());
			}
		});
	}
}
