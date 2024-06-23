package com.example.pintu.activity;

import com.example.pintu.util.DataManager;
import com.example.pintu.view.LogoView;
import com.example.pintulogo.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	LogoView lv;
	DataManager dataManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DisplayMetrics metrics = new DisplayMetrics();
		// 获取屏幕的长度和宽度
		getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
		// System.out.println(metrics.heightPixels+"  "+metrics.widthPixels);
		// 设置无信号栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 设置无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	
		lv = new LogoView(this, metrics);
		
		//加载布局
		setContentView(lv);
		dataManager=new DataManager(this);
		System.out.println("======"+dataManager.queryData().get(0).size());
		if (dataManager.queryData().get(0).size()==0) {
			dataManager.initData();
		}
		
	}

	public void gotoMenu() {
		//跳转到菜单页面
		Intent intent = new Intent(MainActivity.this, MenuActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
