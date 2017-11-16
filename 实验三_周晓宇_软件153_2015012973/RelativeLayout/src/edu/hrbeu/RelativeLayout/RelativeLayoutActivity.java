package edu.hrbeu.RelativeLayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.util.Log;

public class RelativeLayoutActivity extends Activity {
	/** Called when the activity is first created. */
	private Button one;
	private Button two;
	private Button three;
	private Button four;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		one = (Button) findViewById(R.id.one);
		one.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Log.d("Z", "one");
			}
		});
		
		two = (Button) findViewById(R.id.two);
		two.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Log.i("Z", "two");
			}
		});
		
		three = (Button) findViewById(R.id.three);
		three.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Log.w("Z", "three");
			}
		});
		
		four = (Button) findViewById(R.id.four);
		four.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Log.v("Z", "four");
			}
		});
	}
}