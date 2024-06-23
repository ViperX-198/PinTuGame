package com.example.pintu.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.pintu.activity.StartGameActivity;
import com.example.pintu.entity.BlockGroup;
import com.example.pintu.util.DataManager;
import com.example.pintulogo.R;

@SuppressLint({ "ViewConstructor", "SimpleDateFormat" })
public class GameView extends SurfaceView implements SurfaceHolder.Callback,
		Runnable {
	SurfaceHolder sHolder;
	Bitmap bitmap;
	BlockGroup blockGroup;
	StartGameActivity sGame;
	DataManager dataManager;
	int readyCount;
	int readyTime = 3;

	public boolean finishGame;
	Thread t;
	Paint myPaint;

	int GameMapX = 10;
	int GameMapY = 120;
	String readyStr, rfinalStr;

	/**
	 * 游戏状态机
	 */
	int gemeState;
	public static final int GAMESTATE_READY = 0;
	public static final int GAMESTATE_RUN = 1;
	public static final int GAMESTATE_WIN = 2;
	public static final int GAMESTATE_SHOW = 3;
	public static final int GAMESTATE_REPLAY = 4;

	int eventType;
	public static final int EVENT_NON = 0;
	public static final int EVENT_TOUCHDOWN = 1;
	public static final int EVENT_TOUCHUP = 2;

	float touchX, touchY;

	/**
	 * 游戏界面操作：界面设置
	 */
	Bitmap smallImg;
	Bitmap replayImg;
	Bitmap clockImg;
	/**
	 * 游戏界面操作：时间设置
	 */
	long startTime;
	long secends;
	/**
	 * 
	 * 游戏界面操作：查看原图
	 */
	RectF smallRect;// 缩略图矩形框

	/**
	 * 胜利文字
	 */
	String winStr = "恭喜你，胜利了！";
	int winCount;
	/**
	 * 胜利时使用秒数
	 */
	int score;// 游戏完成所用时间
	Context context;
	int imgeIds;
	int level;
	public GameView(Context context, int imgeIds, int level) {
		super(context);
		// TODO Auto-generated constructor stub

		bitmap = BitmapFactory.decodeResource(getResources(), imgeIds);
		this.level = level;
		System.out.println("我要的难度是..................."+level);
		this.context = context;
		this.imgeIds = imgeIds;
		myPaint = new Paint();
		readyStr = "图片打乱中";

		sGame = (StartGameActivity) context;

		blockGroup = new BlockGroup(level, bitmap);

		/*
		 * 游戏界面操作：界面设置
		 */
		clockImg = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.k);

		smallImg = Bitmap.createScaledBitmap(bitmap, 100, 100, true);

		replayImg = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.up);

		/*
		 * 游戏界面操作：缩略图矩形框
		 */
		smallRect = new RectF(10, 10, 110, 110);

		sHolder = getHolder();// 2、获取SurfaceHolder对象

		sHolder.addCallback(this);// 添加CallBack回调

	}

	@Override
	/**
	 * surface创建时调用：主要执行初始化工作  通常用于启动线程
	 */
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		t = new Thread(this);
		t.start();// 开启线程
	}

	@Override
	/**
	 * surface改变时调用：只有surface发生变化（大小改变、横竖屏切换）
	 */
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	/**
	 * surface销毁时调用：清理资源   通常必须进行停止线程
	 */
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

		finishGame = true;
	}

	public void update() {

		switch (gemeState) {
		case GAMESTATE_READY:
			if (readyCount < readyTime) {
				blockGroup.flushBlocks();
				rfinalStr = readyStr;
				for (int i = 0; i <= readyCount % 3; i++) {
					rfinalStr += ".";
				}
				readyCount++;
			} else {
				// 任务11 游戏过程操作逻辑 移动图块:自动进入运行状态
				readyCount = 0;
				gemeState = GAMESTATE_RUN;
				// 任务13 游戏界面操作：时间设置
				startTime = System.currentTimeMillis();
			}
			break;

		case GAMESTATE_WIN:
			// 胜利文字设置
			winCount++;
			if (winCount > 90) {

				finishGame = true;// 停止线程，关闭线程开关
				DataManager dataManager=new DataManager(context);
				if(dataManager.breakRecoed(level, score)){
					sGame.gotoBreakRecord(score);
				}else{
					sGame.gotoSelectActivity();
				}
			}

			break;

		case GAMESTATE_REPLAY:
			break;
		}
	}

	public void render() {

		SurfaceHolder holder = getHolder();
		Canvas canvas = holder.lockCanvas();
		if (canvas == null) {
			return;
		}

		canvas.drawColor(Color.BLACK);
		switch (gemeState) {
		case GAMESTATE_READY:
			renderREADY(canvas);
			break;
		case GAMESTATE_RUN:
			renderRUN(canvas);
			break;
		case GAMESTATE_SHOW:

			renderSHOW(canvas);
			break;
		case GAMESTATE_REPLAY:
			renderREPLAY(canvas);
			break;
		case GAMESTATE_WIN:
			renderWIN(canvas);
			break;
		}

		holder.unlockCanvasAndPost(canvas);
	}

	/**
	 * 任务11 游戏过程操作逻辑 移动图块:绘制界面
	 * 
	 * @param canvas
	 */
	private void renderRUN(Canvas canvas) {
		/*
		 * 游戏操作界面：界面设置
		 */
		canvas.drawBitmap(smallImg, 10, 10, myPaint);
		canvas.drawBitmap(clockImg, 220, 10, myPaint);
		canvas.drawBitmap(replayImg, 220, 60, myPaint);
		/*
		 * 游戏操作界面：时间绘制
		 */
		myPaint.setColor(Color.YELLOW);
		myPaint.setTextSize(40);

		canvas.drawText(getCurrentTime(), 270, 50, myPaint);

		blockGroup.paint(canvas, GameMapX, GameMapY);
	}

	private String getCurrentTime() {
		// int secends = (int) ((System.currentTimeMillis() - startTime) /
		// 1000);
		// int secend = secends % 60;
		// int min = ((secends) / 60) % 60;
		// int hour = secends / 3600;
		//
		// return (hour < 10 ? "0" + hour : hour) + ":"
		// + (min < 10 ? "0" + min : min) + ":"
		// + (secend < 10 ? "0" + secend : secend);

		secends = System.currentTimeMillis() - startTime + 16 * 60 * 60 * 1000;
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
		return sf.format(new Date(secends));
	}

	private void renderSHOW(Canvas canvas) {
		canvas.drawBitmap(bitmap, 30, 30, myPaint);

	}

	private void renderREPLAY(Canvas canvas) {
		// TODO Auto-generated method stub

	}

	private void renderWIN(Canvas canvas) {
		// TODO Auto-generated method stub
	
		myPaint.setColor(Color.YELLOW);
		myPaint.setTextSize(40);
		canvas.drawText(winStr, 480 - (winCount << 3),
				bitmap.getHeight() + 140, myPaint);

		canvas.drawBitmap(bitmap, 50, 100, null);

	}

	private void renderREADY(Canvas canvas) {
		// TODO Auto-generated method stub
		myPaint.setColor(Color.YELLOW);
		myPaint.setTextSize(32);
		canvas.drawText(rfinalStr, 50, 110, myPaint);
		System.out.println("canvas   " + canvas);
		blockGroup.paint(canvas, GameMapX, GameMapY);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!finishGame) {
			try {
				event();
				update();
				render();
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void event() {

		if (eventType == EVENT_NON)
			return;
		switch (eventType) {
		case EVENT_TOUCHDOWN:
			switch (gemeState) {
			case GAMESTATE_RUN:
				// 任务12 游戏过程操作逻辑 移动图块，并判断是否胜利
				if (blockGroup.onClick(touchX - GameMapX, touchY - GameMapY)) {
					score = (int) (((System.currentTimeMillis() - startTime)) / 1000);
					gemeState = GAMESTATE_WIN;

				}
				if (smallRect.contains(touchX, touchY)) {
					gemeState = GAMESTATE_SHOW;
				}
				break;
			case GAMESTATE_SHOW:
				gemeState = GAMESTATE_RUN;
				break;
			}

			eventType = EVENT_NON;
			break;
		case EVENT_TOUCHUP:
			switch (gemeState) {
			case GAMESTATE_RUN:
				break;
			case GAMESTATE_SHOW:
				break;
			}
			eventType = EVENT_NON;
			break;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (eventType != EVENT_NON)
			return true;
		if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
			eventType = EVENT_TOUCHDOWN;
			touchX = event.getX();
			touchY = event.getY();
			// if (smallRect.contains(touchX, touchY)) {
			// //gemeState = GAMESTATE_SHOW;
			// md=new MyDialog(context, imgeIds);
			// md.show();
			// new DialogInterface.OnClickListener(){
			// @Override
			// public void onClick(DialogInterface dialog, int which) {
			// // TODO Auto-generated method stub
			// md.dismiss();
			// }
			// };
			// }
			return true;
		}

		if (event.getActionMasked() == MotionEvent.ACTION_UP) {
			eventType = EVENT_TOUCHUP;
			touchX = event.getX();
			touchY = event.getY();
			return true;
		}
		return false;
	}

	
}
