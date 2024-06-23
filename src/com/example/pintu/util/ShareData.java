package com.example.pintu.util;



import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ShareData {
	SharedPreferences sf;
	Context context;
	Editor er;

	public ShareData(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		sf = context.getSharedPreferences("name", Context.MODE_PRIVATE);
	}

	public String read() {
		String name2 = sf.getString("name", "Œﬁ√˚ œ");
		return name2;
	}

	public void write(String name) {
		er = sf.edit();
		er.putString("name", name);
		er.commit();
	}


}
