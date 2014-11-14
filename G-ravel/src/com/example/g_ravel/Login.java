package com.example.g_ravel;

import com.example.g_ravel_etcProgramming.Gravel_static;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Login extends Activity implements OnClickListener{
	
	private Gravel_static gs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		gs = (Gravel_static)getApplicationContext();
		
		//��ư����
		Button join = (Button)findViewById(R.id.btn_Login_join);
		Button searchIdpw = (Button)findViewById(R.id.btn_Login_idpwSearch);
		Button MemberLogin = (Button)findViewById(R.id.btn_Login_login);
		Button GuestLogin = (Button)findViewById(R.id.btn_Login_nonlogin);
		
		//��ư ����ֱ�
		join.setOnClickListener(this);
		searchIdpw.setOnClickListener(this);
		MemberLogin.setOnClickListener(this);
		GuestLogin.setOnClickListener(this);
	}

	//��ư ��Ŭ�� ������
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.btn_Login_join){
			Intent go_join = new Intent(Login.this,Agreement.class);
			startActivity(go_join);
		}else if(v.getId() == R.id.btn_Login_idpwSearch){
			Intent go_idpwSearch = new Intent(Login.this,IdpwSearch.class);
			startActivity(go_idpwSearch);
		}else if(v.getId() == R.id.btn_Login_login){
			Intent go_main = new Intent(Login.this,MemberMain.class);
			gs.set_KIMI_MEMBER("MEMBER");
			startActivity(go_main);
			finish();
		}else if(v.getId() == R.id.btn_Login_nonlogin){
			AlertDialog.Builder NonLogin = new AlertDialog.Builder(Login.this);
			NonLogin.setTitle("��ȸ�� �α��� �ȳ�");
			NonLogin.setMessage("G-ravel �� ȸ���� �ǽø� ��ȸ���� ��캸�� �� ���� ������ ��ٸ��� �ֽ��ϴ�. ȸ�� ���� �ϽǷ���?");
			NonLogin.setPositiveButton("��,�ҰԿ�", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent go_join = new Intent(Login.this,Agreement.class);
					startActivity(go_join);
				}
			});
			NonLogin.setNegativeButton("�ƴϿ�,���ҷ���.", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent go_MemberMainIntent = new Intent(Login.this,NonMemberMain.class);
					gs.set_KIMI_MEMBER("NON_MEMBER");
					startActivity(go_MemberMainIntent);
					finish();
				}
			} );
			NonLogin.show();
		}
	}
	
}
