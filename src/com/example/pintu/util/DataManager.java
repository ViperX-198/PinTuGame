package com.example.pintu.util;

import java.util.ArrayList;

import com.example.pintu.dao.PointRanDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataManager {
	MyDbHelper myDbHelper;
	SQLiteDatabase sd;
	String name;
	Context context;
	ArrayList<ArrayList<PointRanDao>> array;

	public DataManager(Context context) {
		// TODO Auto-generated constructor stub
		array = new ArrayList<ArrayList<PointRanDao>>();
		this.context = context;
		name = "Mydb";
		myDbHelper = new MyDbHelper(context, name, null, 1);

	}

	String[] levelStr = { "��", "��ͨ", "����" };

	/**
	 * //����������ʼ������
	 */
	public void initData() {

		for (int i = 0; i < levelStr.length; i++) {
			for (int j = 0; j < 10; j++) {
				PointRanDao pointRan = new PointRanDao();
				pointRan.setName("������");
				pointRan.setDiff(levelStr[i]);
				pointRan.setPaiming(j + 1);
				pointRan.setScore(60 + j * 100);
				insertData(pointRan);

			}
		}

	}

	public void insertData(PointRanDao pointRan) {
		sd = myDbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", pointRan.getName());
		values.put("scoer", pointRan.getScore());
		values.put("paiming", pointRan.getPaiming());
		values.put("diff", pointRan.getDiff());
		sd.insert("ranks", null, values);
		sd.close();
	}

	public ArrayList<ArrayList<PointRanDao>> queryData() {
		sd = myDbHelper.getWritableDatabase();
		/*
		 * 1��String����--������
		 * 
		 * 2��String[]����--��ÿ��Ԫ��Ϊ��ѯ�����������ѯ���������Ϳ�
		 * 
		 * 3��String����--����ѯ���������û��ɸѡ�������Ϳ�
		 * 
		 * 4��String[]����--����ѯ����ֵ�����û��ɸѡ����ֵ���Ϳ�
		 * 
		 * 5��String����--�����飬���û�з����������Ϳ�
		 * 
		 * 6��String����--��ɸѡ�����û��ɸѡ�������Ϳ�
		 * 
		 * 7��String����--���������û�ж��ĸ��н����������Ϳ�
		 */
		String selection = "diff=?";
		String[] selectionArgs = { "��" };
		Cursor cursor = sd.query("ranks", null, selection, selectionArgs, null,
				null, "scoer");
		// Cursor cursor = sd.rawQuery("select *from ranks ", null);
		ArrayList<PointRanDao> arr = new ArrayList<PointRanDao>();
		while (cursor.moveToNext()) {
			PointRanDao pointRan = new PointRanDao();
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int scoer = cursor.getInt(cursor.getColumnIndex("scoer"));
			int paiming = cursor.getInt(cursor.getColumnIndex("paiming"));
			String diff = cursor.getString(cursor.getColumnIndex("diff"));

			pointRan.setName(name);
			pointRan.setScore(scoer);
			pointRan.setPaiming(paiming);
			pointRan.setDiff(diff);
			arr.add(pointRan);
		}

		String selection1 = "diff=?";
		String[] selectionArgs1 = { "��ͨ" };
		Cursor cursor1 = sd.query("ranks", null, selection1, selectionArgs1,
				null, null, "scoer");
		// Cursor cursor = sd.rawQuery("select *from ranks ", null);
		ArrayList<PointRanDao> arr1 = new ArrayList<PointRanDao>();
		while (cursor1.moveToNext()) {
			PointRanDao pointRan = new PointRanDao();
			String name = cursor1.getString(cursor1.getColumnIndex("name"));
			int scoer = cursor1.getInt(cursor1.getColumnIndex("scoer"));
			int paiming = cursor1.getInt(cursor1.getColumnIndex("paiming"));
			String diff = cursor1.getString(cursor1.getColumnIndex("diff"));

			pointRan.setName(name);
			pointRan.setScore(scoer);
			pointRan.setPaiming(paiming);
			pointRan.setDiff(diff);
			arr1.add(pointRan);
		}
		String selection2 = "diff=?";
		String[] selectionArgs2 = { "����" };
		Cursor cursor2 = sd.query("ranks", null, selection2, selectionArgs2,
				null, null, "scoer");
		// Cursor cursor = sd.rawQuery("select *from ranks ", null);
		ArrayList<PointRanDao> arr2 = new ArrayList<PointRanDao>();
		while (cursor2.moveToNext()) {
			PointRanDao pointRan = new PointRanDao();
			String name = cursor2.getString(cursor2.getColumnIndex("name"));
			int scoer = cursor2.getInt(cursor2.getColumnIndex("scoer"));
			int paiming = cursor2.getInt(cursor2.getColumnIndex("paiming"));
			String diff = cursor2.getString(cursor2.getColumnIndex("diff"));

			pointRan.setName(name);
			pointRan.setScore(scoer);
			pointRan.setPaiming(paiming);
			pointRan.setDiff(diff);

			arr2.add(pointRan);
		}
		array.add(arr);
		array.add(arr1);
		array.add(arr2);
		sd.close();
		return array;
	}

	public void updateData(int level, long fenshu, String name) {
		// DataManager dataManager=new DataManager(context);

		array = queryData();
		sd = myDbHelper.getWritableDatabase();
//		System.out.println(array.get(level).get(9).getScore());
//		System.out.println(array.get(level).get(9).getName());
		int score = array.get(level).get(9).getScore();
		String nName = array.get(level).get(9).getName();
		String diff=array.get(level).get(9).getDiff();
//		System.out.println(" ������������" + name);
//		System.out.println("��������ʱ��" + fenshu);
		System.out.println("���������Ѷȵȼ�" + (level + 3));
//		array.get(level).get(9).setName(name);
//		array.get(level).get(9).setScore((int) fenshu);
//		System.out.println("��õ������ǡ�������>" + array.get(level).get(9).getName());
		ContentValues values1 = new ContentValues();
		values1.put("name", name);
		values1.put("scoer", fenshu);
		String whereClause = "name=? and scoer=? and diff=?";
		String[] whereArgs = { nName, score + "" ,diff};
		sd.update("ranks", values1, whereClause, whereArgs);
		array = queryData();
		sd.close();
	}

	public void deleteData() {
		sd = myDbHelper.getWritableDatabase();
		sd.delete("ranks", null, null);
		sd.close();
	}


	public  boolean breakRecoed(int level,int s){
		array = queryData();
		int score = array.get(level-3).get(9).getScore();
		return score>s;
	}
}
