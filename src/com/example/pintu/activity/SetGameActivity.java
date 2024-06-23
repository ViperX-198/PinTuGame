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
	MediaPlayer mp; // MediaPlayer����
	AudioManager am; // AudioManager����
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
		// ��ȡAudioManager����
		am = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);// ��ȡ��������
		// ��ȡϵͳ�������ֵ
		maxSound = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		soundseekBar.setMax(maxSound);// ��������Bar�����ֵ����Ϊϵͳ�������ֵ
		index = soundseekBar.getProgress();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.setgameactivity_Button_music_on:
			// ����ģʽ
			// am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			musicsState.setImageResource(R.drawable.audio_on);
			MusicUtil.musicStart(SetGameActivity.this, R.raw.play);
			break;
		case R.id.setgameactivity_Button_music_off:
			// ����ģʽ
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
		Toast.makeText(this, "��������������", Toast.LENGTH_LONG).show();
	}

	@Override
	// ��ֵ�ĸı�,�õ���ǰ��ֵ�Ĵ�С
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		// ��ȡ��������ֵ���óɵ�ǰ��������С.
		am.setStreamVolume(AudioManager.STREAM_MUSIC,
				soundseekBar.getProgress(),
				AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		if (soundseekBar.getProgress() == 0) {
			stopMusic();
		}
	}

	@Override
	// ��ʼ�϶�����
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	// ֹͣ�϶�����
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	public void upMusic() {

		// streamType����������,direction���������Ĵ�С,flags,��־λ����������Ч��
		am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
				AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
		index = am.getStreamVolume(AudioManager.STREAM_MUSIC);

		// ��������Bar�ĵ�ǰֵ����Ϊϵͳ������ǰֵ
		soundseekBar.setProgress(index);
		startMusic();

	}

	public void downMusic() {

		index = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		// streamType����������?,direction���������Ĵ�С,flags,��־λ����������Ч��
		am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
				AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
		if (index == 0) {
			// ����Ϊ0,��ر�����
			stopMusic();
		} else {
			index = am.getStreamVolume(AudioManager.STREAM_MUSIC);
			startMusic();
		}
		soundseekBar.setProgress(index);
	}

	public void startMusic() {
		MusicUtil.musicStart(SetGameActivity.this, R.raw.play); // ��̬���ÿ�������
		musicsState.setImageResource(R.drawable.audio_on); // ������ʾ��������ͼ��
	}

	public void stopMusic() {
		MusicUtil.musicStop(); // ��̬���ÿ�������
		musicsState.setImageResource(R.drawable.audio_off); // ������ʾ��������ͼ��
	}

}
