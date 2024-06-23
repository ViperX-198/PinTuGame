package com.example.pintu.activity;

import java.util.ArrayList;

import com.example.pintu.adapter.MyExpandablePointAdapter;
import com.example.pintu.dao.PointRanDao;
import com.example.pintu.util.DataManager;
import com.example.pintulogo.R;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ExpandableListView;

public class PointsRanActivity extends ExpandableListActivity {
	MyExpandablePointAdapter myPointAdapter;
	ExpandableListView lv;
	DataManager dataManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		dataManager = new DataManager(this);
		ArrayList<ArrayList<PointRanDao>> array=	dataManager.queryData();
		lv = getExpandableListView();
		myPointAdapter = new MyExpandablePointAdapter(this,array);
		lv.setAdapter(myPointAdapter);
		DisplayMetrics metrics = new DisplayMetrics();// 获取屏幕宽高
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		// 设置扩展图标的距离左边界位置
		lv.setIndicatorBounds(width - 80, width - 60);
		lv.setBackgroundResource(R.drawable.helpback);
	}
}
