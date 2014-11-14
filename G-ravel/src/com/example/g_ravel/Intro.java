package com.example.g_ravel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class Intro extends Activity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        
        (new Thread(this)).start();
        
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			Thread.sleep(3000);
			Intent go_login = new Intent(Intro.this,Login.class);
			startActivity(go_login);
			finish();
		}catch(Exception e){
			Log.d("¿À·ù", e.toString());
		}
	}
}
