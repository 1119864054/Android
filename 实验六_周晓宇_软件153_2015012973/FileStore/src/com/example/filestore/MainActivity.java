package com.example.filestore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final String FILE_NAME = "fileDemo.txt";
	private TextView top;
	private TextView display;
	private EditText name;
	private EditText classname;
	private EditText num;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		top = (TextView) findViewById(R.id.top);
		display = (TextView) findViewById(R.id.display);
		name = (EditText) findViewById(R.id.name);
		classname = (EditText) findViewById(R.id.classname);
		num = (EditText) findViewById(R.id.num);
		Button input = (Button) findViewById(R.id.input);
		Button output = (Button) findViewById(R.id.output);
		Button delete = (Button) findViewById(R.id.delete);
		input.setOnClickListener(inputListener);
		output.setOnClickListener(outputListener);
		delete.setOnClickListener(deleteListener);
		// entryText.selectAll();
		// entryText.findFocus();
	}

	OnClickListener inputListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			FileOutputStream fos = null;
			try {
				fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
				String Name = name.getText().toString();
				String Classname = classname.getText().toString();
				String Num = num.getText().toString();
				String Data = "姓名：" + Name + "\n" + "班级：" + Classname + "\n" + "学号：" + Num + "\n";
				fos.write(Data.getBytes());
				top.setText("文件写入成功，写入长度：" + Data.length());
				name.setText("");
				classname.setText("");
				num.setText("");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.flush();
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	};

	OnClickListener outputListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			display.setText("");
			FileInputStream fis = null;
			try {
				fis = openFileInput(FILE_NAME);
				if (fis.available() == 0) {
					return;
				}
				byte[] readBytes = new byte[fis.available()];
				while (fis.read(readBytes) != -1) {
				}
				String text = new String(readBytes);
				display.setText(text);
				top.setText("文件读取成功，文件长度：" + text.length());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	};
	
	OnClickListener deleteListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			FileOutputStream fos = null;
			try {
				fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
				String Data="";
				fos.write(Data.getBytes());
				top.setText("成功清除文件内容");
				display.setText("");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.flush();
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	};

}
