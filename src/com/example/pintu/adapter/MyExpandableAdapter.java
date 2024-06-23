package com.example.pintu.adapter;

import com.example.pintulogo.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyExpandableAdapter extends BaseExpandableListAdapter {
	Context context;
	LayoutInflater lf;
	HolderView holdrview=null;
	String[] textGroup = { "游戏操作", "游戏系统" };
	int[][] textChildren = { { R.string.gamehelpactivity_world1 },
			{ R.string.gamehelpactivity_world2 } };

	public MyExpandableAdapter(Context context) {
	
		lf=LayoutInflater.from(context);// 传入当前类对象
		this.context = context;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return textGroup.length;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return textChildren[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			holdrview = new HolderView();
			// 调用布局过滤器的inflate()方法将list_help布局传入
			convertView=lf.inflate(R.layout.list_help, null);
			holdrview.parent = (TextView) convertView
					.findViewById(R.id.list_help_tv_helping);
			convertView.setTag(holdrview);
		} else {
			holdrview = (HolderView) convertView.getTag();
		}
		holdrview.parent.setText(textGroup[groupPosition]);
		holdrview.parent.setTextSize(50);
		holdrview.parent.setTextColor(Color.BLACK);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			TextView tv = new TextView(context);
			convertView = tv;
			holdrview.children = tv;
			convertView.setTag(holdrview);
		} else {
			holdrview = (HolderView) convertView.getTag();
		}
		holdrview.children.setText(context.getResources().getString(
				textChildren[groupPosition][childPosition]));
		holdrview.children.setTextSize(30);
		holdrview.children.setTextColor(Color.BLACK);
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	class HolderView {
		TextView parent;
		TextView children;
	}
}
