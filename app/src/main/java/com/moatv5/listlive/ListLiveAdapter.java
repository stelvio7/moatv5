package com.moatv5.listlive;

import java.util.ArrayList;

import com.moatv5.model.LiveChannel;
import com.moatv5.settop.R;

import android.content.Context;
import android.util.Log;
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
        if (channelList != null)
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
            viewHolder.vodTime1 = (TextView) convertView.findViewById(R.id.vodTime1);
            viewHolder.vodTitle1 = (TextView) convertView.findViewById(R.id.vodTitle1);
            viewHolder.hasVod2 = (ImageView) convertView.findViewById(R.id.hasVod2);
            viewHolder.vodTime2 = (TextView) convertView.findViewById(R.id.vodTime2);
            viewHolder.vodTitle2 = (TextView) convertView.findViewById(R.id.vodTitle2);
            viewHolder.hasVod3 = (ImageView) convertView.findViewById(R.id.hasVod3);
            viewHolder.vodTime3 = (TextView) convertView.findViewById(R.id.vodTime3);
            viewHolder.vodTitle3 = (TextView) convertView.findViewById(R.id.vodTitle3);
            viewHolder.hasVod4 = (ImageView) convertView.findViewById(R.id.hasVod4);
            viewHolder.vodTime4 = (TextView) convertView.findViewById(R.id.vodTime4);
            viewHolder.vodTitle4 = (TextView) convertView.findViewById(R.id.vodTitle4);
            viewHolder.hasVod5 = (ImageView) convertView.findViewById(R.id.hasVod5);
            viewHolder.vodTime5 = (TextView) convertView.findViewById(R.id.vodTime5);
            viewHolder.vodTitle5 = (TextView) convertView.findViewById(R.id.vodTitle5);
            viewHolder.hasVod6 = (ImageView) convertView.findViewById(R.id.hasVod6);
            viewHolder.vodTime6 = (TextView) convertView.findViewById(R.id.vodTime6);
            viewHolder.vodTitle6 = (TextView) convertView.findViewById(R.id.vodTitle6);
            viewHolder.hasVod7 = (ImageView) convertView.findViewById(R.id.hasVod7);
            viewHolder.vodTime7 = (TextView) convertView.findViewById(R.id.vodTime7);
            viewHolder.vodTitle7 = (TextView) convertView.findViewById(R.id.vodTitle7);
            viewHolder.hasVod8 = (ImageView) convertView.findViewById(R.id.hasVod8);
            viewHolder.vodTime8 = (TextView) convertView.findViewById(R.id.vodTime8);
            viewHolder.vodTitle8 = (TextView) convertView.findViewById(R.id.vodTitle8);
            viewHolder.hasVod9 = (ImageView) convertView.findViewById(R.id.hasVod9);
            viewHolder.vodTime9 = (TextView) convertView.findViewById(R.id.vodTime9);
            viewHolder.vodTitle9 = (TextView) convertView.findViewById(R.id.vodTitle9);
            viewHolder.hasVod10 = (ImageView) convertView.findViewById(R.id.hasVod10);
            viewHolder.vodTime10 = (TextView) convertView.findViewById(R.id.vodTime10);
            viewHolder.vodTitle10 = (TextView) convertView.findViewById(R.id.vodTitle10);
            viewHolder.hasVod11 = (ImageView) convertView.findViewById(R.id.hasVod11);
            viewHolder.vodTime11 = (TextView) convertView.findViewById(R.id.vodTime11);
            viewHolder.vodTitle11 = (TextView) convertView.findViewById(R.id.vodTitle11);
            viewHolder.hasVod12 = (ImageView) convertView.findViewById(R.id.hasVod12);
            viewHolder.vodTime12 = (TextView) convertView.findViewById(R.id.vodTime12);
            viewHolder.vodTitle12 = (TextView) convertView.findViewById(R.id.vodTitle12);
            //viewHolder.btn1 = (Button) convertView.findViewById(R.id.btn1);
            //viewHolder.btn2 = (Button) convertView.findViewById(R.id.btn2);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (LiveListViewHolder) convertView.getTag();
        }
        viewHolder.broadTitle.setText(channelList.get(position).getName());
        if (channelList.get(position).getListLiveDataList()[0] != null)
            if (channelList.get(position).getListLiveDataList()[0].size() > 0)
                if (channelList.get(position).getListLiveDataList()[0].get(0).getTitle() != null) {
                    viewHolder.vodTitle1.setText(channelList.get(position).getListLiveDataList()[0].get(0).getTitle());
                    viewHolder.vodTime1.setText(channelList.get(position).getListLiveDataList()[0].get(0).getTime());
                }
        if (channelList.get(position).getListLiveDataList()[0] != null)
            if (channelList.get(position).getListLiveDataList()[0].size() > 1)
                if (channelList.get(position).getListLiveDataList()[0].get(1).getTitle() != null) {
                    viewHolder.vodTitle2.setText(channelList.get(position).getListLiveDataList()[0].get(1).getTitle());
                    viewHolder.vodTime2.setText(channelList.get(position).getListLiveDataList()[0].get(1).getTime());
                }
        if (channelList.get(position).getListLiveDataList()[0] != null)
            if (channelList.get(position).getListLiveDataList()[0].size() > 2)
                if (channelList.get(position).getListLiveDataList()[0].get(2).getTitle() != null) {
                    viewHolder.vodTitle3.setText(channelList.get(position).getListLiveDataList()[0].get(2).getTitle());
                    viewHolder.vodTime3.setText(channelList.get(position).getListLiveDataList()[0].get(2).getTime());
                }
        if (channelList.get(position).getListLiveDataList()[1] != null)
            if (channelList.get(position).getListLiveDataList()[1].size() > 0)
                if (channelList.get(position).getListLiveDataList()[1].get(0).getTitle() != null) {
                    viewHolder.vodTitle4.setText(channelList.get(position).getListLiveDataList()[1].get(0).getTitle());
                    viewHolder.vodTime4.setText(channelList.get(position).getListLiveDataList()[1].get(0).getTime());
                }
        if (channelList.get(position).getListLiveDataList()[1] != null)
            if (channelList.get(position).getListLiveDataList()[1].size() > 1)
                if (channelList.get(position).getListLiveDataList()[1].get(1).getTitle() != null) {
                    viewHolder.vodTitle5.setText(channelList.get(position).getListLiveDataList()[1].get(1).getTitle());
                    viewHolder.vodTime5.setText(channelList.get(position).getListLiveDataList()[1].get(1).getTime());
                }
        if (channelList.get(position).getListLiveDataList()[1] != null)
            if (channelList.get(position).getListLiveDataList()[1].size() > 2)
                if (channelList.get(position).getListLiveDataList()[1].get(2).getTitle() != null) {
                    viewHolder.vodTitle6.setText(channelList.get(position).getListLiveDataList()[1].get(2).getTitle());
                    viewHolder.vodTime6.setText(channelList.get(position).getListLiveDataList()[1].get(2).getTime());
                }
        if (channelList.get(position).getListLiveDataList()[2] != null)
            if (channelList.get(position).getListLiveDataList()[2].size() > 0)
                if (channelList.get(position).getListLiveDataList()[2].get(0).getTitle() != null) {
                    viewHolder.vodTitle7.setText(channelList.get(position).getListLiveDataList()[2].get(0).getTitle());
                    viewHolder.vodTime7.setText(channelList.get(position).getListLiveDataList()[2].get(0).getTime());
                }
        if (channelList.get(position).getListLiveDataList()[2] != null)
            if (channelList.get(position).getListLiveDataList()[2].size() > 1)
                if (channelList.get(position).getListLiveDataList()[2].get(1).getTitle() != null) {
                    viewHolder.vodTitle8.setText(channelList.get(position).getListLiveDataList()[2].get(1).getTitle());
                    viewHolder.vodTime8.setText(channelList.get(position).getListLiveDataList()[2].get(1).getTime());
                }
        if (channelList.get(position).getListLiveDataList()[2] != null)
            if (channelList.get(position).getListLiveDataList()[2].size() > 2)
                if (channelList.get(position).getListLiveDataList()[2].get(2).getTitle() != null) {
                    viewHolder.vodTitle9.setText(channelList.get(position).getListLiveDataList()[2].get(2).getTitle());
                    viewHolder.vodTime9.setText(channelList.get(position).getListLiveDataList()[2].get(2).getTime());
                }
        if (channelList.get(position).getListLiveDataList()[3] != null)
            if (channelList.get(position).getListLiveDataList()[3].size() > 0)
                if (channelList.get(position).getListLiveDataList()[3].get(0).getTitle() != null) {
                    viewHolder.vodTitle10.setText(channelList.get(position).getListLiveDataList()[3].get(0).getTitle());
                    viewHolder.vodTime10.setText(channelList.get(position).getListLiveDataList()[3].get(0).getTime());
                }
        if (channelList.get(position).getListLiveDataList()[3] != null)
            if (channelList.get(position).getListLiveDataList()[3].size() > 1)
                if (channelList.get(position).getListLiveDataList()[3].get(1).getTitle() != null) {
                    viewHolder.vodTitle11.setText(channelList.get(position).getListLiveDataList()[3].get(1).getTitle());
                    viewHolder.vodTime11.setText(channelList.get(position).getListLiveDataList()[3].get(1).getTime());
                }
        if (channelList.get(position).getListLiveDataList()[3] != null)
            if (channelList.get(position).getListLiveDataList()[3].size() > 2)
                if (channelList.get(position).getListLiveDataList()[3].get(2).getTitle() != null) {
                    viewHolder.vodTitle12.setText(channelList.get(position).getListLiveDataList()[3].get(2).getTitle());
                    viewHolder.vodTime12.setText(channelList.get(position).getListLiveDataList()[3].get(2).getTime());
                }

        //viewHolder.vodTitle2.setText(channelList.get(0).getListLiveDataList().get(0).getTitle());
        //viewHolder.vodTitle3.setText(channelList.get(0).getListLiveDataList().get(0).getTitle());

        return convertView;
    }
}
