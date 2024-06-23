package com.example.pintu.view;

import com.example.pintu.activity.MainActivity;
import com.example.pintulogo.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.View;

@SuppressLint("ViewConstructor")
public class LogoView  extends View  implements Runnable{
	Bitmap[] bitmap;
	int i = 0;
	boolean flag = true;//�����ź����������ж���ת�����˵�
	int[] image = { R.drawable.mmlogo, R.drawable.and1, R.drawable.logo };//
	MainActivity mainActivity;
	int width;//��Ļ���
	int height;

	public LogoView(Context context, DisplayMetrics metrics) {
		super(context);
		mainActivity = (MainActivity) context;
		bitmap = new Bitmap[3];
		width = metrics.widthPixels;
		height = metrics.heightPixels;
		// bitmap[0] = BitmapFactory.decodeResource(getResources(),
		// R.drawable.mmlogo);
		// bitmap[1] = BitmapFactory.decodeResource(getResources(),
		// R.drawable.and1);
		// bitmap[2] = BitmapFactory.decodeResource(getResources(),
		// R.drawable.bm1);
		//bitmap��ʼ��
		for (int i = 0; i < bitmap.length; i++) {
			bitmap[i] = BitmapFactory.decodeResource(getResources(), image[i]);
			bitmap[i] = Bitmap.createScaledBitmap(bitmap[i], width, height,true);
		}
		new Thread(this).start();//�����߳�
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawBitmap(bitmap[i], 0, 0, null);
	}

	@Override
	public void run() {
		while (flag) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (i < bitmap.length - 1) {
				i++;
				postInvalidate();//����ִ��onDraw����
			} else{
				flag = false;
			mainActivity.gotoMenu();//��ת�����˵�ҳ��
			}
		}
	}

}
