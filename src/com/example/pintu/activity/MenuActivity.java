package com.example.pintu.activity;

import com.example.pintulogo.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

import android.widget.Button;

public class MenuActivity extends Activity implements OnClickListener {
	Button btuStart;
	Button btuPoint;
	Button btuSet;
	Button btuHelp;
	Button btuAbout;
	Button btuExit;
	AlertDialog.Builder builder;
	AlertDialog.Builder builder1;
	Intent intent;
	SetGameActivity startMusic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置无信号栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 设置无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.menuactivity);
	//	MusicUtil.musicStart(this, R.raw.play);
		btuStart = (Button) findViewById(R.id.menuactivity_button1);// 游戏开始
		btuPoint = (Button) findViewById(R.id.menuactivity_button2);// 积分排名
		btuSet = (Button) findViewById(R.id.menuactivity_button3);// 游戏设置
		btuHelp = (Button) findViewById(R.id.menuactivity_button4);// 游戏帮助
		btuAbout = (Button) findViewById(R.id.menuactivity_button5);// 游戏关于
		btuExit = (Button) findViewById(R.id.menuactivity_button6);// 游戏退出
		btuStart.setOnClickListener(this);
		btuPoint.setOnClickListener(this);
		btuSet.setOnClickListener(this);
		btuHelp.setOnClickListener(this);
		btuAbout.setOnClickListener(this);
		btuExit.setOnClickListener(this);
	
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.menuactivity_button1:
			gotoStartGame();
			break;

		case R.id.menuactivity_button2:
			gotoPointRan();
			break;
		case R.id.menuactivity_button3:
			gotoSetGame();
			break;
		case R.id.menuactivity_button4:
			gotoGameHelp();
			break;
		case R.id.menuactivity_button5:
			aboutAlertDialog();
			break;
		case R.id.menuactivity_button6:
			exitAlertDialog();
			break;
		}
	}

	private void gotoGameHelp() {
		// TODO Auto-generated method stub
		 intent = new Intent(MenuActivity.this, GameHelpActivity.class);
		startActivity(intent);
	}

	private void gotoSetGame() {
		// TODO Auto-generated method stub
	 intent = new Intent(MenuActivity.this, SetGameActivity.class);
		startActivity(intent);
	}

	private void gotoPointRan() {
		// TODO Auto-generated method stub
		 intent = new Intent(MenuActivity.this, PointsRanActivity.class);
		startActivity(intent);
	
	}

	private void gotoStartGame() {
		// TODO Auto-generated method stub
		 intent = new Intent(MenuActivity.this, SelectActivity.class);
		startActivity(intent);
	}

	private void aboutAlertDialog() {
		// TODO Auto-generated method stub
		// 创建个builder设置背景,标题,消息,返回按钮
		builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.icon)
				.setTitle("关于")
				.setMessage("版权所有,感谢试玩!")
				.setNegativeButton("返回",
						new android.content.DialogInterface.OnClickListener() {
							// 确定是在android.content.DialogInterface下的监听方法
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								// 调用消除方法,返回前一页面
								dialog.dismiss();
							}
						}).show();
	}

	private void exitAlertDialog() {
		// TODO Auto-generated method stub
		builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.icon)
				.setTitle("退出游戏")
				.setMessage("是否退出游戏?")
				.setPositiveButton("是",
						new android.content.DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// 结束当前的activity
								finish();

							}
						})
				.setNegativeButton("否",
						new android.content.DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								dialog.dismiss();
							}
						}).show();
	}
	 @Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			 if(keyCode==KeyEvent.KEYCODE_BACK){
					exitAlertDialog();
			 }
			return super.onKeyDown(keyCode, event);
		}
	
}