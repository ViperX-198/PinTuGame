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
		// �������ź���
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// �����ޱ�����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.menuactivity);
	//	MusicUtil.musicStart(this, R.raw.play);
		btuStart = (Button) findViewById(R.id.menuactivity_button1);// ��Ϸ��ʼ
		btuPoint = (Button) findViewById(R.id.menuactivity_button2);// ��������
		btuSet = (Button) findViewById(R.id.menuactivity_button3);// ��Ϸ����
		btuHelp = (Button) findViewById(R.id.menuactivity_button4);// ��Ϸ����
		btuAbout = (Button) findViewById(R.id.menuactivity_button5);// ��Ϸ����
		btuExit = (Button) findViewById(R.id.menuactivity_button6);// ��Ϸ�˳�
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
		// ������builder���ñ���,����,��Ϣ,���ذ�ť
		builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.icon)
				.setTitle("����")
				.setMessage("��Ȩ����,��л����!")
				.setNegativeButton("����",
						new android.content.DialogInterface.OnClickListener() {
							// ȷ������android.content.DialogInterface�µļ�������
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								// ������������,����ǰһҳ��
								dialog.dismiss();
							}
						}).show();
	}

	private void exitAlertDialog() {
		// TODO Auto-generated method stub
		builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.icon)
				.setTitle("�˳���Ϸ")
				.setMessage("�Ƿ��˳���Ϸ?")
				.setPositiveButton("��",
						new android.content.DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// ������ǰ��activity
								finish();

							}
						})
				.setNegativeButton("��",
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