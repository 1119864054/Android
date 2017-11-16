package com.example.sqlitestore;

public class Student {
	public String Name;
	public String Classnum;
	public int Num;
	
	@Override
	public String toString(){
		String result = "";
		result += "ÐÕÃû£º" + this.Name + "£¬";
		result += "°à¼¶£º" + this.Classnum + "£¬ ";
		result += "Ñ§ºÅ£º" + this.Num + "£¬";
		return result;
	}
}
