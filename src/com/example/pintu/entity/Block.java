package com.example.pintu.entity;

import android.graphics.Bitmap;
import android.graphics.RectF;

public class Block {
	int tempW;// 格子宽度
	int tempH;// 格子高度
	int xLine;// 行位置
	int yLine;// 列位置
	int blockId;// 格子id
	RectF blockRect;

	int x;// x轴坐标
	int y;// y轴坐标

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
	 * 是否点击格子
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean onClick(float x, float y) {
		return blockRect.contains(x, y);
	}
}
