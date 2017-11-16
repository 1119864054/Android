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
				top.setText("��ӹ��̴���");
			} else {
				top.setText("�ɹ�������ݣ�ѧ�ţ�"+String.valueOf(student.Num));	
				
			}
		}	
    };
    
    OnClickListener deleteButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			long num = Integer.parseInt(Num.getText().toString());
			long result = dbase.deleteOneData(num); 
			String msg = "ɾ��ѧ��Ϊ"+Num.getText().toString()+"������" + (result>0?"�ɹ�":"ʧ��");
			top.setText(msg);
		}	
    };
    
    OnClickListener appearButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Student[] students = dbase.queryAllData();
			if (students == null){
				top.setText("���ݿ���û������");
				display.setText("���ݿ���û������");
				return;
			}
			top.setText("���ݿ⣺");
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
			String msg = "����ȫ��ɾ��";
			top.setText(msg);
		}	
    };
    
    OnClickListener queryButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			int num = Integer.parseInt(Num.getText().toString());
			Student[] students = dbase.queryOneData(num);
			
			if (students == null){
				top.setText("���ݿ���û��IDΪ"+String.valueOf(num)+"������");
				return;
			}
			top.setText("���ݿ⣺");
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
				top.setText("���´���");
			} else {
				top.setText("���³ɹ�����������"+String.valueOf(count)+"��");	
				
			}
		}
    };
}
