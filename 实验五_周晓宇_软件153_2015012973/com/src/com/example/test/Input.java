package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Input extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input);
		
		final EditText userin = (EditText)findViewById(R.id.userin);
		final EditText pwdin = (EditText)findViewById(R.id.pwdin);
        Button end = (Button)findViewById(R.id.end);
        
        end.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		String user = userin.getText().toString();		
        		String pwd = pwdin.getText().toString();
        		String uri = user+"\n"+pwd;
        		Uri data = Uri.parse(uri);
        		
        		Intent result = new Intent(null, data);
        		
        		setResult(RESULT_OK, result);
        		finish();
        	}
        });
        
    
	}

}

