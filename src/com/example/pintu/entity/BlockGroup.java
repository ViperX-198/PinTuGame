package com.example.pintu.entity;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BlockGroup {
	
	ImageRect[] imageRects;
	int myLevel2;
	Block[][] blocks;
	int myLevel;
	int whiteB;// 空白格子
	Random r;
	public BlockGroup(int level, Bitmap currentBitmap) {
		// TODO Auto-generated constructor stub
		myLevel2 = level * level;
		myLevel = level;

		imageRects = new ImageRect[myLevel2];
		for (int i = 0; i < imageRects.length; i++) {
			imageRects[i] = new ImageRect(currentBitmap, i, level);
		}

		blocks = new Block[level][level];
		for (int i = 0; i < myLevel2; i++) {
			blocks[i / level][i % level] = new Block(currentBitmap, i, level);
			blocks[i / level][i % level].initBlock();
		}
		r=new Random();
		whiteB=myLevel2-1;
	}

	/**
	 * 先获取每个格子的id，根据格子id进行绘制图块
	 */
	public void paint(Canvas canvas, int left, int top) {
		for (int i = 0; i < myLevel2; i++) {
			Block tempBlock = blocks[i / myLevel][i % myLevel];
			imageRects[tempBlock.blockId].paint(canvas, left + tempBlock.x, top
					+ tempBlock.y, null);
		}
	}
	
	/**
	 * 刷新格子
	 */
	public void flushBlocks() {
		for (int i = 0; i < myLevel; i++) {
			autoChange();
		}
	}

	/**
	 * 随机打乱格子
	 */
	public void autoChange() {

		for (int i = 0; i < myLevel2; i++) {
			Block tempB = blocks[i / myLevel][i % myLevel];
			if (tempB.blockId == whiteB) {
				 while (true) {
				int index = r.nextInt(4);// 随机设置方向
				switch (index) {
				case 0:
					// 向上
					if (tempB.xLine > 0) {
						int tempNum = tempB.blockId;// 8

						tempB.blockId = blocks[tempB.xLine - 1][tempB.yLine].blockId;// tid=5
						blocks[tempB.xLine - 1][tempB.yLine].blockId = tempNum;// sid=8
						return;
					}
					break;
				case 1:
					// 向下
					if (tempB.xLine < 2) {
						int tempNum = tempB.blockId;
						tempB.blockId = blocks[tempB.xLine + 1][tempB.yLine].blockId;
						blocks[tempB.xLine + 1][tempB.yLine].blockId = tempNum;
						return;
					}
					break;
				case 2:
					// 向左
					if (tempB.yLine > 0) {
						int tempNum = tempB.blockId;
						tempB.blockId = blocks[tempB.xLine][tempB.yLine - 1].blockId;
						blocks[tempB.xLine][tempB.yLine - 1].blockId = tempNum;
						return;
					}
					break;
				case 3:
					// 向右
					if (tempB.yLine < 2) {
						int tempNum = tempB.blockId;
						tempB.blockId = blocks[tempB.xLine][tempB.yLine + 1].blockId;
						blocks[tempB.xLine][tempB.yLine + 1].blockId = tempNum;
						return;
					}
					break;
				}
				 }
			}
		}

	}
	/**
	 * 任务11 游戏过程操作逻辑 移动图块：点击格子
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean onClick(float x, float y) {
		for (int i = 0; i < myLevel2; i++) {
			Block tempB = blocks[i / myLevel][i % myLevel];
			if (tempB.onClick(x, y)) {
				if (checkBlock(tempB)) {
					/*
					 * 任务12 确定是否胜利
					 */
					return checkWin();
				} else
					return false;
			}
		}
		return false;
	}

	/**
	 * 任务11 游戏过程操作逻辑 移动图块：确定是否能够移动
	 * 
	 * @param blog
	 * @return
	 */
	private boolean checkBlock(Block blog) {
		// 向上
		if (blog.xLine > 0
				&& (blocks[blog.xLine - 1][blog.yLine].blockId == whiteB)) {
			int maopao = blog.blockId;
			blog.blockId = whiteB;
			blocks[blog.xLine - 1][blog.yLine].blockId = maopao;
			return true;
		}
		// 向下
		if (blog.xLine < myLevel - 1
				&& (blocks[blog.xLine + 1][blog.yLine].blockId == whiteB)) {
			int maopao = blog.blockId;
			blog.blockId = whiteB;
			blocks[blog.xLine + 1][blog.yLine].blockId = maopao;
			return true;
		}
		// 向左
		if (blog.yLine > 0
				&& (blocks[blog.xLine][blog.yLine - 1].blockId == whiteB)) {
			int maopao = blog.blockId;
			blog.blockId = whiteB;
			blocks[blog.xLine][blog.yLine - 1].blockId = maopao;
			return true;
		}
		// 向右
		if (blog.yLine < myLevel - 1
				&& (blocks[blog.xLine][blog.yLine + 1].blockId == whiteB)) {
			int maopao = blog.blockId;
			blog.blockId = whiteB;
			blocks[blog.xLine][blog.yLine + 1].blockId = maopao;
			return true;
		}
		return false;
	}
	/**
	 * 任务12 确定是否胜利
	 * 
	 * @return
	 */
	
	private boolean checkWin() {
		for (int i = 0; i < myLevel2; i++) {
			Block tempB = blocks[i / myLevel][i % myLevel];
			if (tempB.blockId != i) {
				return false;
			}
		}
		return true;
	}
	
}
