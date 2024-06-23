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

	String[] levelStr = { "简单", "普通", "困难" };

	/**
	 * //积分排名初始化操作
	 */
	public void initData() {

		for (int i = 0; i < levelStr.length; i++) {
			for (int j = 0; j < 10; j++) {
				PointRanDao pointRan = new PointRanDao();
				pointRan.setName("无名氏");
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
		 * 1、String类型--〉表名
		 * 
		 * 2、String[]类型--〉每个元素为查询列名，如果查询所有列则滞空
		 * 
		 * 3、String类型--〉查询条件，如果没有筛选条件则滞空
		 * 
		 * 4、String[]类型--〉查询条件值，如果没有筛选条件值则滞空
		 * 
		 * 5、String类型--〉分组，如果没有分组条件则滞空
		 * 
		 * 6、String类型--〉筛选，如果没有筛选条件则滞空
		 * 
		 * 7、String类型--〉排序，如果没有对哪个列进行排序则滞空
		 */
		String selection = "diff=?";
		String[] selectionArgs = { "简单" };
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
		String[] selectionArgs1 = { "普通" };
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
		String[] selectionArgs2 = { "困难" };
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
//		System.out.println(" 传过来的姓名" + name);
//		System.out.println("传过来的时间" + fenshu);
		System.out.println("传过来的难度等级" + (level + 3));
//		array.get(level).get(9).setName(name);
//		array.get(level).get(9).setScore((int) fenshu);
//		System.out.println("获得的姓名是————>" + array.get(level).get(9).getName());
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
