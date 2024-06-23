package com.example.pintu.entity;

import android.graphics.Bitmap;
import android.graphics.RectF;

public class Block {
	int tempW;// ���ӿ��
	int tempH;// ���Ӹ߶�
	int xLine;// ��λ��
	int yLine;// ��λ��
	int blockId;// ����id
	RectF blockRect;

	int x;// x������
	int y;// y������

	public Block(Bitmap currentBitmap, int id, int level) {
		// TODO Auto-generated constructor stub
		tempW = currentBitmap.getWidth() / level;
		tempH = currentBitmap.getHeight() / level;
		xLine = id / level;// 000111222
		yLine = id % level;// 012012012

		blockId = id;

		x = (tempW + 2) * yLine;
		y = (tempH + 2) * xLine;

	}

	public void initBlock() {
		blockRect = new RectF(x, y, x + tempW, y + tempH);
	}
	/**
	 * �Ƿ�������
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean onClick(float x, float y) {
		return blockRect.contains(x, y);
	}
}
