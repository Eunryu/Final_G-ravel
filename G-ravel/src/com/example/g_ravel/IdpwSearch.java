package com.example.g_ravel;

import com.example.g_ravel_etcProgramming.Gravel_static;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IdpwSearch extends Activity implements OnClickListener {

	private View v[] = new View[2];
	private View ChangePassword;

	// ���̵�,��й�ȣ �˻��� ���� ������ ��.
	private String[] email = { "instru93@naver.com", "e_groupid@naver.com",
			"cutely93@gmail.com" };
	private String[] id = { "instru93", "e_groupid", "cutely93" };

	// EditText.
	private EditText SearchID_email;
	private EditText searchPW_email;
	private EditText ChangePw_text1;
	private EditText ChangePw_text2;

	private int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_idpw);

		// �����ؽ�Ʈ ����
		SearchID_email = (EditText) findViewById(R.id.et_IdpwSearch_idEm);
		searchPW_email = (EditText) findViewById(R.id.et_IdpwSearch_pwEm);
		ChangePw_text1 = (EditText) findViewById(R.id.et_Idpwsearch_changePassword);
		ChangePw_text2 = (EditText) findViewById(R.id.et_Idpwsearch_changePassword2);

		// ��ư����
		Button idSearch = (Button) findViewById(R.id.btn_IdpwSearch_idSearchTitle);
		Button pwSearch = (Button) findViewById(R.id.btn_IdpwSearch_pwSearchTitle);
		Button idSearch_Result = (Button) findViewById(R.id.btn_IdpwSearch_idsearch);
		Button pwSearch_Result = (Button) findViewById(R.id.btn_IdpwSearch_pwsearch);
		Button ChangePW = (Button) findViewById(R.id.btn_Idpwsearch_PwChange);

		v[0] = findViewById(R.id.ic_IdpwSearch_idSearch);
		v[1] = findViewById(R.id.ic_IdpwSearch_pwSearch);
		ChangePassword = findViewById(R.id.include_ChangePassword);

		// �ʱ����
		v[0].setVisibility(View.VISIBLE);
		v[1].setVisibility(View.INVISIBLE);
		ChangePassword.setVisibility(View.INVISIBLE);

		idSearch.setOnClickListener(this);
		pwSearch.setOnClickListener(this);
		idSearch_Result.setOnClickListener(this);
		pwSearch_Result.setOnClickListener(this);
		ChangePW.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btn_IdpwSearch_idSearchTitle) {
			this.v[0].setVisibility(View.VISIBLE);
			this.v[1].setVisibility(View.INVISIBLE);
		} else if (v.getId() == R.id.btn_IdpwSearch_pwSearchTitle) {
			this.v[0].setVisibility(View.INVISIBLE);
			this.v[1].setVisibility(View.VISIBLE);
		} else if (v.getId() == R.id.btn_IdpwSearch_idsearch) {
			// �̸����� �ִ��� �˻�.
			Gravel_static gs = (Gravel_static) getApplicationContext();
			String ID_email = SearchID_email.getText().toString();
			i = 0;
			while (i != email.length) {
				if (ID_email.equals(email[i])) {
					gs.set_SEARCH_ID_COMMEND("SUCCESS");
					AlertDialog.Builder dlg = new AlertDialog.Builder(
							IdpwSearch.this);
					dlg.setTitle("���̵� ���");
					dlg.setMessage("����� ���̵�� " + id[i] + "�Դϴ�.");
					dlg.setPositiveButton("�α�������",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									Intent go_login = new Intent(
											IdpwSearch.this, Login.class);
									startActivity(go_login);
									finish();
								}
							});
					dlg.show();
					break;
				}
				i++;
				gs.set_SEARCH_ID_COMMEND("FAIL");
			}
			String commend = gs.get_SEARCH_ID_COMMEND();
			if (commend.equals("FAIL")) {
				Toast.makeText(getApplicationContext(), "����� �����ϴ�.",
						Toast.LENGTH_SHORT).show();
			}
			// Toast.makeText(getApplicationContext(), "����� �����ϴ�.",
			// Toast.LENGTH_SHORT).show();
		} else if (v.getId() == R.id.btn_IdpwSearch_pwsearch) {
			Gravel_static gs = (Gravel_static) getApplicationContext();
			String PW_Email = searchPW_email.getText().toString();
			i = 0;
			while (i != email.length) {
				if (PW_Email.equals(email[i])) {
					gs.set_SEARCH_ID_COMMEND("SUCCESS");

					break;
				}
				i++;
				gs.set_SEARCH_ID_COMMEND("FAIL");
			}
			String commend = gs.get_SEARCH_ID_COMMEND();
			if (commend.equals("FAIL")) {
				Toast.makeText(getApplicationContext(), "����� �����ϴ�.",
						Toast.LENGTH_SHORT).show();
			} else if (commend.equals("SUCCESS")) {
				Toast.makeText(getApplicationContext(), "��й�ȣ�� �������ּ���.",
						Toast.LENGTH_SHORT).show();
				ChangePassword.setVisibility(View.VISIBLE);
			}
		} else if (v.getId() == R.id.btn_Idpwsearch_PwChange) {
			String Change_pw1 = ChangePw_text1.getText().toString();
			String Change_pw2 = ChangePw_text2.getText().toString();

			if (Change_pw1.equals(Change_pw2)) {
				Toast.makeText(getApplicationContext(), "������ �Ϸ�Ǿ����ϴ�.",
						Toast.LENGTH_LONG).show();
				Intent go_login = new Intent(IdpwSearch.this, Login.class);
				startActivity(go_login);
				finish();
			} else if(Change_pw1 != Change_pw2) {
				Toast.makeText(getApplicationContext(),
				"�Էµ� ��й�ȣ�� ���� �ٸ��ϴ�. �ٽ� Ȯ���� �ּ���.", Toast.LENGTH_LONG)
				.show();
			}
		}
	}
}
