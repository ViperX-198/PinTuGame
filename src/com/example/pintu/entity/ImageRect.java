package com.example.pintu.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;

public class ImageRect {
	int tempW;// ͼ����
	int tempH;// ͼ��߶�
	int xLine;// ��λ��
	int yLine;// ��λ��
	Bitmap tempBitmap;// ͼ��
	public ImageRect(Bitmap currentBitmap, int id, int level) {
		// TODO Auto-generated constructor stub
		tempW = currentBitmap.getWidth() / level;
		tempH = currentBitmap.getHeight() / level;
		xLine = id / level;// 000111222
		yLine = id % level;// 012012012
		if (level * level - 1 == id) {
			tempBitmap = Bitmap.createBitmap(tempW, tempH, Config.ARGB_8888);
			Canvas canvas = new Canvas(tempBitmap);
			canvas.drawColor(Color.WHITE);
		} else {
			tempBitmap = Bitmap.createBitmap(currentBitmap, tempW * yLine,
					tempH * xLine, tempW, tempH);
		}

	}

	public void paint(Canvas canvas, int left, int top, Paint paint) {
		canvas.drawBitmap(tempBitmap, left, top, paint);
	}
}
