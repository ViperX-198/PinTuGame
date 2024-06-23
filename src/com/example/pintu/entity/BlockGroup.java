package com.example.pintu.entity;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BlockGroup {
	
	ImageRect[] imageRects;
	int myLevel2;
	Block[][] blocks;
	int myLevel;
	int whiteB;// �հ׸���
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
	 * �Ȼ�ȡÿ�����ӵ�id�����ݸ���id���л���ͼ��
	 */
	public void paint(Canvas canvas, int left, int top) {
		for (int i = 0; i < myLevel2; i++) {
			Block tempBlock = blocks[i / myLevel][i % myLevel];
			imageRects[tempBlock.blockId].paint(canvas, left + tempBlock.x, top
					+ tempBlock.y, null);
		}
	}
	
	/**
	 * ˢ�¸���
	 */
	public void flushBlocks() {
		for (int i = 0; i < myLevel; i++) {
			autoChange();
		}
	}

	/**
	 * ������Ҹ���
	 */
	public void autoChange() {

		for (int i = 0; i < myLevel2; i++) {
			Block tempB = blocks[i / myLevel][i % myLevel];
			if (tempB.blockId == whiteB) {
				 while (true) {
				int index = r.nextInt(4);// ������÷���
				switch (index) {
				case 0:
					// ����
					if (tempB.xLine > 0) {
						int tempNum = tempB.blockId;// 8

						tempB.blockId = blocks[tempB.xLine - 1][tempB.yLine].blockId;// tid=5
						blocks[tempB.xLine - 1][tempB.yLine].blockId = tempNum;// sid=8
						return;
					}
					break;
				case 1:
					// ����
					if (tempB.xLine < 2) {
						int tempNum = tempB.blockId;
						tempB.blockId = blocks[tempB.xLine + 1][tempB.yLine].blockId;
						blocks[tempB.xLine + 1][tempB.yLine].blockId = tempNum;
						return;
					}
					break;
				case 2:
					// ����
					if (tempB.yLine > 0) {
						int tempNum = tempB.blockId;
						tempB.blockId = blocks[tempB.xLine][tempB.yLine - 1].blockId;
						blocks[tempB.xLine][tempB.yLine - 1].blockId = tempNum;
						return;
					}
					break;
				case 3:
					// ����
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
	 * ����11 ��Ϸ���̲����߼� �ƶ�ͼ�飺�������
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
					 * ����12 ȷ���Ƿ�ʤ��
					 */
					return checkWin();
				} else
					return false;
			}
		}
		return false;
	}

	/**
	 * ����11 ��Ϸ���̲����߼� �ƶ�ͼ�飺ȷ���Ƿ��ܹ��ƶ�
	 * 
	 * @param blog
	 * @return
	 */
	private boolean checkBlock(Block blog) {
		// ����
		if (blog.xLine > 0
				&& (blocks[blog.xLine - 1][blog.yLine].blockId == whiteB)) {
			int maopao = blog.blockId;
			blog.blockId = whiteB;
			blocks[blog.xLine - 1][blog.yLine].blockId = maopao;
			return true;
		}
		// ����
		if (blog.xLine < myLevel - 1
				&& (blocks[blog.xLine + 1][blog.yLine].blockId == whiteB)) {
			int maopao = blog.blockId;
			blog.blockId = whiteB;
			blocks[blog.xLine + 1][blog.yLine].blockId = maopao;
			return true;
		}
		// ����
		if (blog.yLine > 0
				&& (blocks[blog.xLine][blog.yLine - 1].blockId == whiteB)) {
			int maopao = blog.blockId;
			blog.blockId = whiteB;
			blocks[blog.xLine][blog.yLine - 1].blockId = maopao;
			return true;
		}
		// ����
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
	 * ����12 ȷ���Ƿ�ʤ��
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
