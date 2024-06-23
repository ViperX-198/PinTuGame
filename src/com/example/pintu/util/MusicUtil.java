package com.example.pintu.util;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicUtil {
	static MediaPlayer mp;

	public static void musicStart(Context context, int musicId) {
		// TODO Auto-generated method stub
		if(mp!=null){
			if(mp.isPlaying()){
				mp.stop();
			}
			mp.reset();
			mp=null;
		}
		mp = MediaPlayer.create(context, musicId);
		mp.start();
	}

	public static void musicStop() {
		if (mp != null && mp.isPlaying()) {
			mp.stop();
		}
	}
}
