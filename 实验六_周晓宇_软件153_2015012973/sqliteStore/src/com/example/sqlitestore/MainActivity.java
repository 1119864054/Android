package com.example.sqlitestore;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sqlitestore.DBase;
import com.example.sqlitestore.Student;
import com.example.sqlitestore.R;

public class MainActivity extends Activity {

	private DBase dbase;
	
	private EditText Name;
	private EditText Classnum;
	private EditText Num;

	private TextView top;
	private TextView display;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Name = (EditText)findViewById(R.id.Name);
        Classnum = (EditText)findViewById(R.id.Classnum);
        Num = (EditText)findViewById(R.id.Num);
        
        top = (TextView)findViewById(R.id.top);
        display = (TextView)findViewById(R.id.display);
        
        
        
        Button addButton = (Button)findViewById(R.id.add);
        Button appearButton = (Button)findViewById(R.id.appear);      
//        Button clearButton = (Button)findViewById(R.id.clear);
        Button deleteAllButton = (Button)findViewById(R.id.delete_all);
        
        Button queryButton = (Button)findViewById(R.id.query);
        Button deleteButton = (Button)findViewById(R.id.delete);
        Button updateButton = (Button)findViewById(R.id.update);
        
        
        addButton.setOnClickListener(addButtonListener); 
        appearButton.setOnClickListener(appearButtonListener);     
//        clearButton.setOnClickListener(clearButtonListener);
        deleteAllButton.setOnClickListener(deleteAllButtonListener);      
        
        queryButton.setOnClickListener(queryButtonListener);
        deleteButton.setOnClickListener(deleteButtonListener);
        updateButton.setOnClickListener(updateButtonListener);
        
        dbase = new DBase(this);
        dbase.open();
    }
    
    OnClickListener addButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Student student = new Student();
			student.Name = Name.getText().toString();
			student.Classnum = Classnum.getText().toString();
			student.Num = Integer.parseInt(Num.getText().toString());
			long colunm = dbase.insert(student);
			if (colunm == -1 ){
				top.setText("添加过程错误！");
			} else {
				top.setText("成功添加数据，学号："+String.valueOf(student.Num));	
				
			}
		}	
    };
    
    OnClickListener deleteButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			long num = Integer.parseInt(Num.getText().toString());
			long result = dbase.deleteOneData(num); 
			String msg = "删除学号为"+Num.getText().toString()+"的数据" + (result>0?"成功":"失败");
			top.setText(msg);
		}	
    };
    
    OnClickListener appearButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Student[] students = dbase.queryAllData();
			if (students == null){
				top.setText("数据库中没有数据");
				display.setText("数据库中没有数据");
				return;
			}
			top.setText("数据库：");
			String msg = "";
			for (int i = 0 ; i<students.length; i++){
				msg += students[i].toString()+"\n";
			}
			display.setText(msg);
		}
    };
    
    OnClickListener deleteAllButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			dbase.deleteAllData();
			String msg = "数据全部删除";
			top.setText(msg);
		}	
    };
    
    OnClickListener queryButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			int num = Integer.parseInt(Num.getText().toString());
			Student[] students = dbase.queryOneData(num);
			
			if (students == null){
				top.setText("数据库中没有ID为"+String.valueOf(num)+"的数据");
				return;
			}
			top.setText("数据库：");
			Name.setText(students[0].Name.toString());
			Classnum.setText(students[0].Classnum.toString());
		}
    };
    
    OnClickListener updateButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Student student = new Student();
			student.Name = Name.getText().toString();
			student.Classnum = Classnum.getText().toString();
			student.Num = Integer.parseInt(Num.getText().toString());
			long num = Integer.parseInt(Num.getText().toString());
			long count = dbase.updateOneData(num, student);
			if (count == -1 ){
				top.setText("更新错误！");
			} else {
				top.setText("更新成功，更新数据"+String.valueOf(count)+"条");	
				
			}
		}
    };
}
