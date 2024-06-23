package com.example.pintu.activity;

import com.example.pintu.view.GameView;
import com.example.pintulogo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class StartGameActivity extends Activity {
	Intent intent;
	Intent intent1;
	GameView gv;
	 int level;
     int imgeIds;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.startgameactivity);
		intent = this.getIntent();
		level = intent.getIntExtra("nandu", 2);
		imgeIds=intent.getIntExtra("PicId", R.drawable.b0);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		gv = new GameView(this,imgeIds,level);
		setContentView(gv);
	//	int id = intent.getIntExtra("PicId", 4);
		//System.out.println("难度值为："+diffcult);
	//	System.out.println(id);
	
	}
	public void gotoBreakRecord(long second){
		intent1=new Intent(com.example.pintu.activity.StartGameActivity.this,com.example.pintu.activity.BreakRecordActivity.class);
	long fenshu=second;
		intent1.putExtra("fenshu", fenshu);
		intent1.putExtra("level", level);
		startActivity(intent1);
	}
	public void gotoSelectActivity(){
		intent1=new Intent(com.example.pintu.activity.StartGameActivity.this,com.example.pintu.activity.SelectActivity.class);
		startActivity(intent1);
	}
}
