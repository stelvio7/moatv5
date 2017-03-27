package com.moatv5.listlive;

import java.util.ArrayList;

import com.moatv5.model.LiveChannel;
import com.moatv5.settop.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ListLiveAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<LiveChannel> channelList;
	private int mLayout;
	
	
	public ListLiveAdapter(Context context, 
			 ArrayList<LiveChannel> channelList) {
		this.mContext = context;
		this.channelList = channelList;
		this.mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		if(channelList != null)
			return channelList.size();
		else 
			return 0;
	}

	@Override
	public Object getItem(int position) {
		return channelList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LiveListViewHolder viewHolder;
       // ImageView iv = (ImageView)convertView.findViewById(R.id.imageView1);
       // iv.setImageResource(img[position]);
		if (convertView == null) {
			
			convertView = mInflater.inflate(R.layout.livelistrow, parent, false);
			
			viewHolder = new LiveListViewHolder();
			viewHolder.broadTitle = (TextView) convertView.findViewById(R.id.broadTitle);
			viewHolder.hasVod1 = (ImageView) convertView.findViewById(R.id.hasVod1);
			viewHolder.vodTitle1 = (TextView) convertView.findViewById(R.id.vodTitle1);
			viewHolder.hasVod2 = (ImageView) convertView.findViewById(R.id.hasVod2);
			viewHolder.vodTitle2 = (TextView) convertView.findViewById(R.id.vodTitle2);
			viewHolder.hasVod3 = (ImageView) convertView.findViewById(R.id.hasVod3);
			viewHolder.vodTitle3 = (TextView) convertView.findViewById(R.id.vodTitle3);
			viewHolder.hasVod4 = (ImageView) convertView.findViewById(R.id.hasVod4);
			viewHolder.vodTitle4 = (TextView) convertView.findViewById(R.id.vodTitle4);
			viewHolder.hasVod5 = (ImageView) convertView.findViewById(R.id.hasVod5);
			viewHolder.vodTitle5 = (TextView) convertView.findViewById(R.id.vodTitle5);
			viewHolder.hasVod6 = (ImageView) convertView.findViewById(R.id.hasVod6);
			viewHolder.vodTitle6 = (TextView) convertView.findViewById(R.id.vodTitle6);
			viewHolder.hasVod7 = (ImageView) convertView.findViewById(R.id.hasVod7);
			viewHolder.vodTitle7 = (TextView) convertView.findViewById(R.id.vodTitle7);
			viewHolder.hasVod8 = (ImageView) convertView.findViewById(R.id.hasVod8);
			viewHolder.vodTitle8 = (TextView) convertView.findViewById(R.id.vodTitle8);
			viewHolder.hasVod9 = (ImageView) convertView.findViewById(R.id.hasVod9);
			viewHolder.vodTitle9 = (TextView) convertView.findViewById(R.id.vodTitle9);
			viewHolder.hasVod10 = (ImageView) convertView.findViewById(R.id.hasVod10);
			viewHolder.vodTitle10 = (TextView) convertView.findViewById(R.id.vodTitle10);
			viewHolder.hasVod11 = (ImageView) convertView.findViewById(R.id.hasVod11);
			viewHolder.vodTitle11 = (TextView) convertView.findViewById(R.id.vodTitle11);
			viewHolder.hasVod12 = (ImageView) convertView.findViewById(R.id.hasVod12);
			viewHolder.vodTitle12 = (TextView) convertView.findViewById(R.id.vodTitle12);
			//viewHolder.btn1 = (Button) convertView.findViewById(R.id.btn1);
			//viewHolder.btn2 = (Button) convertView.findViewById(R.id.btn2);
			
			convertView.setTag(viewHolder);
		}
		else {
			viewHolder = (LiveListViewHolder) convertView.getTag();
		}
		viewHolder.broadTitle.setText(channelList.get(position).getName());
		if(channelList.get(position).getListLiveDataList().size() > 0)
			if (channelList.get(position).getListLiveDataList().get(0).getTitle() != null)
				viewHolder.vodTitle1.setText(channelList.get(position).getListLiveDataList().get(0).getTitle());
		if(channelList.get(position).getListLiveDataList().size() > 1)
			if (channelList.get(position).getListLiveDataList().get(1).getTitle() != null)
				viewHolder.vodTitle2.setText(channelList.get(position).getListLiveDataList().get(1).getTitle());
		if(channelList.get(position).getListLiveDataList().size() > 2)
			if (channelList.get(position).getListLiveDataList().get(2).getTitle() != null)
				viewHolder.vodTitle3.setText(channelList.get(position).getListLiveDataList().get(2).getTitle());
		if(channelList.get(position).getListLiveDataList().size() > 3)
			if (channelList.get(position).getListLiveDataList().get(3).getTitle() != null)
				viewHolder.vodTitle4.setText(channelList.get(position).getListLiveDataList().get(3).getTitle());
		if(channelList.get(position).getListLiveDataList().size() > 4)
			if (channelList.get(position).getListLiveDataList().get(4).getTitle() != null)
				viewHolder.vodTitle5.setText(channelList.get(position).getListLiveDataList().get(4).getTitle());
		if(channelList.get(position).getListLiveDataList().size() > 5)
			if (channelList.get(position).getListLiveDataList().get(5).getTitle() != null)
				viewHolder.vodTitle6.setText(channelList.get(position).getListLiveDataList().get(5).getTitle());
		if(channelList.get(position).getListLiveDataList().size() > 6)
			if (channelList.get(position).getListLiveDataList().get(6).getTitle() != null)
				viewHolder.vodTitle7.setText(channelList.get(position).getListLiveDataList().get(6).getTitle());
		if(channelList.get(position).getListLiveDataList().size() > 7)
			if (channelList.get(position).getListLiveDataList().get(7).getTitle() != null)
				viewHolder.vodTitle8.setText(channelList.get(position).getListLiveDataList().get(7).getTitle());
		if(channelList.get(position).getListLiveDataList().size() > 8)
			if (channelList.get(position).getListLiveDataList().get(8).getTitle() != null)
				viewHolder.vodTitle9.setText(channelList.get(position).getListLiveDataList().get(8).getTitle());
		if(channelList.get(position).getListLiveDataList().size() > 9)
			if (channelList.get(position).getListLiveDataList().get(9).getTitle() != null)
				viewHolder.vodTitle10.setText(channelList.get(position).getListLiveDataList().get(9).getTitle());
		if(channelList.get(position).getListLiveDataList().size() > 10)
			if (channelList.get(position).getListLiveDataList().get(10).getTitle() != null)
				viewHolder.vodTitle11.setText(channelList.get(position).getListLiveDataList().get(10).getTitle());
		if(channelList.get(position).getListLiveDataList().size() > 11)
			if (channelList.get(position).getListLiveDataList().get(11).getTitle() != null)
				viewHolder.vodTitle12.setText(channelList.get(position).getListLiveDataList().get(11).getTitle());

		//viewHolder.vodTitle2.setText(channelList.get(0).getListLiveDataList().get(0).getTitle());
		//viewHolder.vodTitle3.setText(channelList.get(0).getListLiveDataList().get(0).getTitle());

		return convertView;
	}
}
