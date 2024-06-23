package com.example.pintu.activity;

import com.example.pintu.adapter.MyExpandableAdapter;
import com.example.pintulogo.R;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ExpandableListView;

public class GameHelpActivity extends ExpandableListActivity {
	MyExpandableAdapter myAdapter;
	ExpandableListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		lv = getExpandableListView();
		myAdapter = new MyExpandableAdapter(this);
		lv.setAdapter(myAdapter);
		DisplayMetrics metrics = new DisplayMetrics();// ��ȡ��Ļ���
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		// ������չͼ��ľ�����߽�λ��
		lv.setIndicatorBounds(width - 80, width - 60);
		lv.setBackgroundResource(R.drawable.helpback);
	}
}
