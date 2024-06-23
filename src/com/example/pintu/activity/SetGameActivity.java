package com.example.pintu.activity;

import com.example.pintu.util.DataManager;
import com.example.pintu.util.MusicUtil;
import com.example.pintulogo.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class SetGameActivity extends Activity implements OnClickListener,
		OnSeekBarChangeListener {
	Button butdown;
	Button butup;
	Button butMoff;
	Button butMon;
	ImageButton musicsState;
	Button fhui;
	Button qingkon;
	MediaPlayer mp; // MediaPlayer引用
	AudioManager am; // AudioManager引用
	SeekBar soundseekBar;
	int index;
	int maxSound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setgameactivity);
		musicsState = (ImageButton) findViewById(R.id.setgameactivity_ImageButton_MusicState);
		butMoff = (Button) findViewById(R.id.setgameactivity_Button_music_off);
		butMon = (Button) findViewById(R.id.setgameactivity_Button_music_on);
		butup = (Button) findViewById(R.id.setgameactivity_Button_trunUp);
		butdown = (Button) findViewById(R.id.setgameactivity_Button_trunDomn);
		fhui = (Button) findViewById(R.id.setgameactivity_Button_fhui);
		qingkon = (Button) findViewById(R.id.setgameactivity_Button_qingkon);
		soundseekBar = (SeekBar) findViewById(R.id.setgameactivity_SeekBar_seekBar1);
		butMoff.setOnClickListener(this);
		butMon.setOnClickListener(this);
		butdown.setOnClickListener(this);
		butup.setOnClickListener(this);
		fhui.setOnClickListener(this);
		qingkon.setOnClickListener(this);
		soundseekBar.setOnSeekBarChangeListener(this);
		mp = new MediaPlayer();
		// 获取AudioManager对象
		am = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);// 获取音量服务
		// 获取系统音量最大值
		maxSound = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		soundseekBar.setMax(maxSound);// 音量控制Bar的最大值设置为系统音量最大值
		index = soundseekBar.getProgress();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.setgameactivity_Button_music_on:
			// 声音模式
			// am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			musicsState.setImageResource(R.drawable.audio_on);
			MusicUtil.musicStart(SetGameActivity.this, R.raw.play);
			break;
		case R.id.setgameactivity_Button_music_off:
			// 静音模式
			// am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			musicsState.setImageResource(R.drawable.audio_off);
			MusicUtil.musicStop();
			break;
		case R.id.setgameactivity_Button_trunDomn:
			downMusic();
			break;
		case R.id.setgameactivity_Button_trunUp:
			upMusic();
			break;
		case R.id.setgameactivity_Button_qingkon:
			qingKonMessage();
			break;
		case R.id.setgameactivity_Button_fhui:
			Intent intent = new Intent(SetGameActivity.this, MenuActivity.class);
			startActivity(intent);
			finish();
			break;

		}
	}

	private void qingKonMessage() {
		// TODO Auto-generated method stub
		DataManager dataManager=new DataManager(this);
		dataManager.deleteData();
		dataManager.initData();
		Toast.makeText(this, "积分排名已重置", Toast.LENGTH_LONG).show();
	}

	@Override
	// 数值的改变,得到当前数值的大小
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		// 获取进度条的值设置成当前的音量大小.
		am.setStreamVolume(AudioManager.STREAM_MUSIC,
				soundseekBar.getProgress(),
				AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		if (soundseekBar.getProgress() == 0) {
			stopMusic();
		}
	}

	@Override
	// 开始拖动方法
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	// 停止拖动方法
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	public void upMusic() {

		// streamType声音的类型,direction调整音量的大小,flags,标志位调整声音的效果
		am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
				AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
		index = am.getStreamVolume(AudioManager.STREAM_MUSIC);

		// 音量控制Bar的当前值设置为系统音量当前值
		soundseekBar.setProgress(index);
		startMusic();

	}

	public void downMusic() {

		index = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		// streamType声音的类型?,direction调整音量的大小,flags,标志位调整声音的效果
		am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
				AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
		if (index == 0) {
			// 音量为0,则关闭音乐
			stopMusic();
		} else {
			index = am.getStreamVolume(AudioManager.STREAM_MUSIC);
			startMusic();
		}
		soundseekBar.setProgress(index);
	}

	public void startMusic() {
		MusicUtil.musicStart(SetGameActivity.this, R.raw.play); // 静态调用开启音乐
		musicsState.setImageResource(R.drawable.audio_on); // 设置显示开启音乐图标
	}

	public void stopMusic() {
		MusicUtil.musicStop(); // 静态调用开启音乐
		musicsState.setImageResource(R.drawable.audio_off); // 设置显示开启音乐图标
	}

}
