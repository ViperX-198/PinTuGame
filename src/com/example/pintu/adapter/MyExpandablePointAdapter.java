package com.example.pintu.adapter;

import java.util.ArrayList;

import com.example.pintu.dao.PointRanDao;
import com.example.pintulogo.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyExpandablePointAdapter extends BaseExpandableListAdapter {
	Context context;
	LayoutInflater lf;
	ArrayList<ArrayList<PointRanDao>> array;
	// HolderView holdrview = null;
	String[] textGroup = { "简单", "普通", "困难" };

	public MyExpandablePointAdapter(Context context,
			ArrayList<ArrayList<PointRanDao>> array) {
		this.array = array;
		this.context = context;
		lf = LayoutInflater.from(context);// 传入当前类对象
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return textGroup.length;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return array.get(groupPosition).size();
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
		// if (convertView == null) {
		// holdrview = new HolderView();
		// // 调用布局过滤器的inflate()方法将list_help布局传入
		// convertView = lf.inflate(R.layout.list_help, null);
		// holdrview.parent = (TextView) convertView
		// .findViewById(R.id.list_help_tv_helping);
		// convertView.setTag(holdrview);
		// } else {
		// holdrview = (HolderView) convertView.getTag();
		// }
		// holdrview.parent.setText(textGroup[groupPosition]);
		// holdrview.parent.setTextSize(50);
		// holdrview.parent.setTextColor(Color.BLACK);

		convertView = lf.inflate(R.layout.list_help, null);
		TextView tv = (TextView) convertView
				.findViewById(R.id.list_help_tv_helping);
		tv.setText(textGroup[groupPosition]);
		tv.setTextSize(50);
		tv.setTextColor(Color.BLACK);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// if (convertView == null) {
		// TextView tv_name = new TextView(context);
		// // TextView tv_paiming = new TextView(context);
		// convertView = tv_name;
		// holdrview.children = tv_name;
		// convertView.setTag(holdrview);
		// } else {
		// holdrview = (HolderView) convertView.getTag();
		// }
		// holdrview.children.setText((childPosition+1)+"   "+array.get(groupPosition).get(childPosition).getName()+"   "+array.get(groupPosition).get(childPosition).getScore()+"   "+array.get(groupPosition).get(childPosition).getDiff());
		// holdrview.children.setTextSize(20);
		// holdrview.children.setTextColor(Color.BLACK);
		TextView tv_name = new TextView(context);
		convertView = tv_name;
		TextView children=tv_name;
		children.setText((childPosition+1)+"   "+array.get(groupPosition).get(childPosition).getName()+"   "+array.get(groupPosition).get(childPosition).getScore()+"   "+array.get(groupPosition).get(childPosition).getDiff());
		 children.setTextSize(20);
		children.setTextColor(Color.BLACK);
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	// class HolderView {
	// TextView parent;
	// TextView children;
	// }
}
