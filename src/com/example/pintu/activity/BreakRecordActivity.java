package com.example.pintu.activity;

import com.example.pintu.util.DataManager;
import com.example.pintu.util.ShareData;
import com.example.pintulogo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class BreakRecordActivity extends Activity{
	ShareData share;
	EditText it;
	Button btn;
	String name;
	Intent intent;
	long  fenshu;
	int level;
	DataManager dataManager;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	 intent=this.getIntent();
	 fenshu=	intent.getLongExtra("fenshu", 1000);
	 level=intent.getIntExtra("level",3)-3;
	
	 dataManager=new DataManager(this);
	setContentView(R.layout.breakrecordactivity);
	share = new ShareData(this);
	 name = share.read();
	it = (EditText) findViewById(R.id.breakrecordactivity_et_write);
	it.setText(name);
	btn=(Button) findViewById(R.id.breakrecordactivity_btn_queren);
	btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			name=it.getText().toString();
			share.write(name);
			dataManager.updateData(level, fenshu, name);
			Intent intent1=new Intent(BreakRecordActivity.this, PointsRanActivity.class);
			startActivity(intent1);
		}
	});
}
}
