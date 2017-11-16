package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static final int INPUT = 1;
	
	TextView user;
	TextView pwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		user = (TextView)findViewById(R.id.user);
		pwd = (TextView)findViewById(R.id.pwd);
        final Button load = (Button)findViewById(R.id.load);
        
        load.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		Intent intent = new Intent(MainActivity.this, Input.class);
        		startActivityForResult(intent,INPUT);
        	}
        });
        
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
			if (resultCode == RESULT_OK){
				Uri uriData = data.getData();
				String[] DATA = uriData.toString().split("\n");
				user.setText("”√ªß√˚:"+DATA[0]);
				pwd.setText("√‹¬Î:"+DATA[1]);
			}
	}

}
