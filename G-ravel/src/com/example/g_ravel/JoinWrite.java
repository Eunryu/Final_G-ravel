package com.example.g_ravel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinWrite extends Activity implements OnClickListener{
	
	private String Id,Nickname,Phone,Address,Email,Password;
	private EditText myId,myNickName,myEmail1,myEmail2,myPhone,myAddress,myPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.join_write);
		
		//���� �ؽ�Ʈ�� �� ��ȣ ����.
		myPhone = (EditText)findViewById(R.id.et_JoinWrite_phone);
		TelephonyManager telephony = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		myPhone.setText(telephony.getLine1Number());
		myPhone.setEnabled(false);
		
		//�����ؽ�Ʈ ����
		myId = (EditText)findViewById(R.id.et_JoinWrite_id);
		myNickName = (EditText)findViewById(R.id.et_JoinWrite_nickname);
		myEmail1 = (EditText)findViewById(R.id.edt_JoinWriter_email1);
		myEmail2 = (EditText)findViewById(R.id.edt_JoinWriter_email2);
		myAddress = (EditText)findViewById(R.id.edt_JoinWriter_address);
		myPassword = (EditText)findViewById(R.id.et_JoinWrite_pw);
		
		//��ư����
		Button Next = (Button)findViewById(R.id.btn_JoinWriter_Next);
		Button Cancle = (Button)findViewById(R.id.btn_JoinWriter_cancle);
		
		//��ư ����ֱ�
		Next.setOnClickListener(this);
		Cancle.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.btn_JoinWriter_Next){
			Id = myId.getText().toString();
			Phone = myPhone.getText().toString();
			Address = myAddress.getText().toString();
			Email = myEmail1.getText().toString() + "@" + myEmail2.getText().toString();
			Nickname = myNickName.getText().toString();
			Password = myPassword.getText().toString();
			
			Intent go_next = new Intent(JoinWrite.this,CheckMe.class);
			go_next.putExtra("NextId", Id);
			go_next.putExtra("NextPhone", Phone);
			go_next.putExtra("NextAddress", Address);
			go_next.putExtra("NextEmail", Email);
			go_next.putExtra("NextNickname", Nickname);
			go_next.putExtra("NextPassword", Password);
			startActivity(go_next);
			finish();
		}else if(v.getId() == R.id.btn_JoinWriter_cancle){
			Toast.makeText(this, "������Ҹ� �����ż� ��ҵǼ̽��ϴ�. ó������ �ٽ��ϼ���.", Toast.LENGTH_SHORT).show();
			Intent go_main = new Intent(JoinWrite.this,Login.class);
			startActivity(go_main);
			finish();
		}
	}
}
