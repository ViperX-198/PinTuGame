package com.example.pintu.dialog;

import com.example.pintulogo.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageButton;

public class MyDialog extends Dialog {
	ImageButton btn;
	int imgeIds;


	public MyDialog(Context context, int imgeIds) {
		super(context);
		this.imgeIds = imgeIds;
	
	
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datuxianshi);
		btn = (ImageButton) findViewById(R.id.edittext_ImageButton_xianshi);
		btn.setImageResource(imgeIds);
		
	}

}
