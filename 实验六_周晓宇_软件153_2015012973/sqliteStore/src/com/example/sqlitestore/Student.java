package com.example.sqlitestore;

public class Student {
	public String Name;
	public String Classnum;
	public int Num;
	
	@Override
	public String toString(){
		String result = "";
		result += "������" + this.Name + "��";
		result += "�༶��" + this.Classnum + "�� ";
		result += "ѧ�ţ�" + this.Num + "��";
		return result;
	}
}
