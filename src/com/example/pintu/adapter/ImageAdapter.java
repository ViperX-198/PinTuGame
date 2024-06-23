package com.example.pintu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class ImageAdapter extends BaseAdapter{
	Context context;
	int  position;
  	 int [] imageId;
	
	public ImageAdapter(Context context, int [] imageId) {
		this.context = context;
		this.imageId=imageId;
	}
public void getPosition(int position){
	this.position=position;
	//该方法能够重新执行getView方法,用来刷新页面内容.
	notifyDataSetChanged();
}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageId.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return imageId[position];
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView iv=new ImageView(context);
		iv.setImageResource(imageId[position]);
		if(this.position!=position){
			iv.setLayoutParams(new Gallery.LayoutParams(400,400));
		}
		return iv;
	}

}
