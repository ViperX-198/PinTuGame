package com.example.pintu.activity;


import com.example.pintu.adapter.ImageAdapter;
import com.example.pintu.util.DataManager;
import com.example.pintulogo.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;


@SuppressWarnings("deprecation")
public class SelectActivity  extends Activity{
	Gallery gly;
	ImageAdapter iAdapter;
	Intent intent;
	DataManager dataManager;
	// public static int imgId;
	 int [] imageId={R.drawable.b0,R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4,R.drawable.b5,R.drawable.b6,R.drawable.b7,R.drawable.b8,R.drawable.b9};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select);
//		dataManager = new DataManager(this);
//		dataManager.queryData();
		gly=	(Gallery) findViewById(R.id.select_gallery_huantu);
		iAdapter=new ImageAdapter(SelectActivity.this,imageId);
		gly.setAdapter(iAdapter);
		gly.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				showDialog1(position);
	//		imgId	=(int) id;
			}
		});
		gly.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				iAdapter.getPosition(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public void showDialog1(final int p){
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.icon).setTitle("选择难度").setPositiveButton("简单", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				 intent=new Intent(SelectActivity.this,StartGameActivity.class);
				 intent.putExtra("PicId", imageId[p]);
				 intent.putExtra("nandu", 3);
				 startActivity(intent);
			}
		}).setNegativeButton("困难", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				 intent=new Intent(SelectActivity.this,StartGameActivity.class);
				 intent.putExtra("nandu", 5);
				 intent.putExtra("PicId", imageId[p]);
				 startActivity(intent);
			}
		}).setNeutralButton("普通", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				 intent=new Intent(SelectActivity.this,StartGameActivity.class);
				 intent.putExtra("nandu", 4);
				 intent.putExtra("PicId", imageId[p]);
				 startActivity(intent);
			}
		});
		builder.show();
	}
}
