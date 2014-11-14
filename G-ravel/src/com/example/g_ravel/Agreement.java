package com.example.g_ravel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Agreement extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.join_agreement);

		// 버튼선언
		Button AgreeNext = (Button) findViewById(R.id.btn_Agreement_agreeNext);
		Button NoneAgree = (Button) findViewById(R.id.btn_Agreement_noneAgree);

		// 버튼 기능주기
		AgreeNext.setOnClickListener(this);
		NoneAgree.setOnClickListener(this);
	}

	// 버튼에 기능달아주기
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btn_Agreement_agreeNext) {
			// 체크박스 체크 됬는지 안됬는지 확인
			CheckBox box1 = (CheckBox) findViewById(R.id.cb_Agreement_firstagree);
			CheckBox box2 = (CheckBox) findViewById(R.id.cb_Agreement_secondagree);

			if (box1.isChecked() == true && box2.isChecked() == true) {
				Intent go_writer = new Intent(Agreement.this, JoinWrite.class);
				startActivity(go_writer);
				finish();
			} else {
				Toast.makeText(this, "동의하지 않은 약관이 있습니다.", Toast.LENGTH_SHORT)
						.show();
			}
		} else if (v.getId() == R.id.btn_Agreement_noneAgree) {
			Toast.makeText(this, "동의하지않아 가입이 취소되셨습니다.", Toast.LENGTH_SHORT).show();
			Intent go_main = new Intent(Agreement.this,Login.class);
			startActivity(go_main);
			finish();
		}
	}
}
