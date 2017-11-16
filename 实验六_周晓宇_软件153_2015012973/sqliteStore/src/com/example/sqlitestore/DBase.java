package com.example.sqlitestore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.example.sqlitestore.Student;

public class DBase {

	private static final String DB_NAME = "student.db";
	private static final String DB_TABLE = "studentinfo";
	private static final int DB_VERSION = 1;
	
	public static final String KEY_NAME = "name"; 
	public static final String KEY_CLASSNUM = "classnum";
	public static final String KEY_NUM = "num";
	
	private SQLiteDatabase db;
	private final Context context;
	private DBOpenHelper dbOpenHelper;
	
	public DBase(Context _context) {
	    context = _context;
	  }

	  /** Close the database */
	  public void close() {
		  if (db != null){
			  db.close();
			  db = null;
		  }
		}

	  /** Open the database */
	  public void open() throws SQLiteException {  
		  dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
		  try {
			  db = dbOpenHelper.getWritableDatabase();
		  }
		  catch (SQLiteException ex) {
			  db = dbOpenHelper.getReadableDatabase();
		  }	  
		}
	  
	
	  public long insert(Student student) {
	    ContentValues newValues = new ContentValues();
	  
	    newValues.put(KEY_NAME, student.Name);
	    newValues.put(KEY_CLASSNUM, student.Classnum);
	    newValues.put(KEY_NUM, student.Num);
	    
	    return db.insert(DB_TABLE, null, newValues);
	  }


	  public Student[] queryAllData() {  
		  Cursor results =  db.query(DB_TABLE, new String[] { KEY_NAME, KEY_CLASSNUM, KEY_NUM}, 
				  null, null, null, null, null);
		  return ConvertToPeople(results);   
	  }
	  
	 public Student[] queryOneData(long id) {  
		  Cursor results =  db.query(DB_TABLE, new String[] { KEY_NAME, KEY_CLASSNUM, KEY_NUM}, 
				   null,null, null, null, null);
		  return ConvertToPeople(results);   
	  }
	  
	  private Student[] ConvertToPeople(Cursor cursor){
		  int resultCounts = cursor.getCount();
		  if (resultCounts == 0 || !cursor.moveToFirst()){
			  return null;
		  }
		  Student[] students = new Student[resultCounts];
		  for (int i = 0 ; i<resultCounts; i++){
			  students[i] = new Student();
			  students[i].Name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
			  students[i].Classnum = cursor.getString(cursor.getColumnIndex(KEY_CLASSNUM));
			  students[i].Num = cursor.getInt(cursor.getColumnIndex(KEY_NUM));
			  
			  cursor.moveToNext();
		  }	  
		  return students; 
	  }
	  
	  public long deleteAllData() {
		  return db.delete(DB_TABLE, null, null);
	  }

	  public long deleteOneData(long num) {
		  return db.delete(DB_TABLE,  KEY_NUM + "=" + num, null);
	  }

	  public long updateOneData(long num , Student student){
		  ContentValues updateValues = new ContentValues();	  
		  updateValues.put(KEY_NAME, student.Name);
		  updateValues.put(KEY_CLASSNUM, student.Classnum);
		  updateValues.put(KEY_NUM, student.Num);
		  
		  return db.update(DB_TABLE, updateValues,  KEY_NUM + "=" + num, null);
	  }
	  
		/** 静态Helper类，用于建立、更新和打开数据库*/
	  private static class DBOpenHelper extends SQLiteOpenHelper {

		  public DBOpenHelper(Context context, String name, CursorFactory factory, int version) {
		    super(context, name, factory, version);
		  }

		  private static final String DB_CREATE = "create table " + 
		    DB_TABLE + " (" + KEY_NUM + " integer primary key autoincrement, " +
		    KEY_NAME+ " text not null, " + KEY_CLASSNUM+ " text not null);";

		  @Override
		  public void onCreate(SQLiteDatabase _db) {
		    _db.execSQL(DB_CREATE);
		  }

		  @Override
		  public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {		    
		    _db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
		    onCreate(_db);
		  }
		}
	}
