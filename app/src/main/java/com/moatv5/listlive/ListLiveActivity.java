package com.moatv5.listlive;

import java.util.ArrayList;

import com.moatv5.model.Constant;
import com.moatv5.model.LiveChannel;
import com.moatv5.settop.R;
import com.noh.util.PostHttp;
import com.noh.util.Util;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.provider.Contacts.SettingsColumns.KEY;

public class ListLiveActivity extends Activity {
    private ArrayList<LiveChannel> channelList = new ArrayList<LiveChannel>();
    private ListLiveAdapter liveListAdapter;
    private ListView listView;
    private Context mContext;
    private TextView listTime1;
    private TextView listTime2;
    private TextView listTime3;
    private TextView listTime4;
    private int selectedTime;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listlive);

        mContext = this.getBaseContext();
        ChannelTask channelTask = new ChannelTask(mContext);
        channelTask.execute();

        liveListAdapter = new ListLiveAdapter(ListLiveActivity.this,
                channelList);
        listTime1 = (TextView) findViewById(R.id.listTime1);
        listTime2 = (TextView) findViewById(R.id.listTime2);
        listTime3 = (TextView) findViewById(R.id.listTime3);
        listTime4 = (TextView) findViewById(R.id.listTime4);

        setNowSelectedTime();
        setTimeHead();

        if (liveListAdapter != null) {
            liveListAdapter.notifyDataSetChanged();
            listView = (ListView) findViewById(R.id.listGrid);
            listView.setAdapter(liveListAdapter);
            listView.setFocusable(false);
            //listView.setOnItemClickListener(this);
            listView.setItemsCanFocus(true);
            listView.setDividerHeight(0);
            //listView.setOnScrollListener(this);
        }
    }

    private void setNowSelectedTime() {
        if (Integer.parseInt(Util.getNowTime().substring(0, 2)) >= 0 && Integer.parseInt(Util.getNowTime().substring(0, 2)) < 4) {
            selectedTime = 0;
        } else if (Integer.parseInt(Util.getNowTime().substring(0, 2)) >= 4 && Integer.parseInt(Util.getNowTime().substring(0, 2)) < 8) {
            selectedTime = 4;
        } else if (Integer.parseInt(Util.getNowTime().substring(0, 2)) >= 8 && Integer.parseInt(Util.getNowTime().substring(0, 2)) < 12) {
            selectedTime = 8;
        } else if (Integer.parseInt(Util.getNowTime().substring(0, 2)) >= 12 && Integer.parseInt(Util.getNowTime().substring(0, 2)) < 16) {
            selectedTime = 12;
        } else if (Integer.parseInt(Util.getNowTime().substring(0, 2)) >= 16 && Integer.parseInt(Util.getNowTime().substring(0, 2)) < 20) {
            selectedTime = 16;
        } else if (Integer.parseInt(Util.getNowTime().substring(0, 2)) >= 20 && Integer.parseInt(Util.getNowTime().substring(0, 2)) < 24) {
            selectedTime = 20;
        }
    }

    private void setTimeHead() {
        listTime1.setText("" + selectedTime + ":00 ~ " + (selectedTime + 1) + ":00");
        listTime2.setText("" + (selectedTime + 1) + ":00 ~ " + (selectedTime + 2) + ":00");
        listTime3.setText("" + (selectedTime + 2) + ":00 ~ " + (selectedTime + 3) + ":00");
        listTime4.setText("" + (selectedTime + 3) + ":00 ~ " + (selectedTime + 4) + ":00");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class ChannelTask extends AsyncTask<String, Integer, Long> {
        private int position;
        private Context mContext;

        public ChannelTask(Context context) {
            mContext = context;
        }

        @Override
        protected final void onPreExecute() {
        }

        @Override
        protected Long doInBackground(String... params) {
            // TODO Auto-generated method stub
            String strJson = "";
            PostHttp postmake = new PostHttp();
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            //channelList = new ArrayList<LiveChannel>();
            strJson = postmake.httpConnect(
                    Constant.mainUrl + "/module/tv/broadcast_category.php", nameValuePairs);
            JSONArray jArray = null;
            try {
                jArray = new JSONArray(strJson);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    String code = json_data.getString("code");
                    String name = json_data.getString("name");
                    if (code != null && name != null) {
                        LiveChannel liveChannelList = new LiveChannel(code, name);
                        channelList.add(liveChannelList);
                    }
                }
            } catch (JSONException e) {
                Log.e(null, e.toString());
            }
            return 0L;
        }

        protected void showCancelMessage() {
            //Toast.makeText(context, mTaskCancelledMessage, Toast.LENGTH_SHORT).show();
        }

        protected void showError(Context context, Throwable t) {
            //TODO exception
            String errorMessage = context.getString(R.string.str_network_error);
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Long result) {
            // TODO Auto-generated method stub
            ListTask listTask = new ListTask(mContext);
            listTask.execute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            //	mImageView.scrollTo(mScrollStep++, 0);
        }

        @Override
        protected void onCancelled() {
            // TODO Auto-generated method stub
            super.onCancelled();
            showCancelMessage();
        }
    }

    class ListTask extends AsyncTask<String, Integer, Long> {
        private int position;
        private Context mContext;

        public ListTask(Context context) {
            mContext = context;
        }

        @Override
        protected final void onPreExecute() {
        }

        @Override
        protected Long doInBackground(String... params) {
            // TODO Auto-generated method stub
            String strJson = "";
            PostHttp postmake = new PostHttp();
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("date", Util.getNowDay()));
            Log.d("text", Util.getNowDay());
            strJson = postmake.httpConnect(
                    Constant.mainUrl + "/module/tv/broadcasttable.php", nameValuePairs);
            JSONArray jArray = null;
            try {
                jArray = new JSONArray(strJson);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);

                    String code = json_data.getString("chenal");
                    String time = json_data.getString("time");
                    String title = json_data.getString("title");
                    String state = json_data.getString("state");


                    if (code != null && time != null) {
                        ListLiveItemData listItem = new ListLiveItemData(title, state, time, "link");
                        if (channelList.size() > 0)
                            for (LiveChannel liveChannel : channelList) {
                                if (liveChannel.getCode().equals(code)) {
                                    ArrayList<ListLiveItemData> list1 = new ArrayList<ListLiveItemData>();
                                    ArrayList<ListLiveItemData> list2 = new ArrayList<ListLiveItemData>();
                                    ArrayList<ListLiveItemData> list3 = new ArrayList<ListLiveItemData>();
                                    ArrayList<ListLiveItemData> list4 = new ArrayList<ListLiveItemData>();
                                    if(Integer.parseInt(listItem.getTime().substring(0, 2)) >= selectedTime && Integer.parseInt(listItem.getTime().substring(0, 2)) < selectedTime+1)
                                        list1.add(listItem);
                                    if(Integer.parseInt(listItem.getTime().substring(0, 2)) >= selectedTime+1 && Integer.parseInt(listItem.getTime().substring(0, 2)) < selectedTime+2)
                                        list2.add(listItem);
                                    if(Integer.parseInt(listItem.getTime().substring(0, 2)) >= selectedTime+2 && Integer.parseInt(listItem.getTime().substring(0, 2)) < selectedTime+3)
                                        list3.add(listItem);
                                    if(Integer.parseInt(listItem.getTime().substring(0, 2)) >= selectedTime+3 && Integer.parseInt(listItem.getTime().substring(0, 2)) < selectedTime+4)
                                        list4.add(listItem);
                                   // channelList.get().getListLiveDataList()[0] = list1;
                                    //channelList.getListLiveDataList()[1] = list2;
                                    //channelList.getListLiveDataList()[2] = list3;
                                    //channelList.getListLiveDataList()[3] = list4;
                                }
                                //Log.d("sdfsdf", "" + channelList.get(0).getListLiveDataList().size());
                            }
                    }
                }
            } catch (Exception e) {
                Log.e(null, e.toString());
            }
            return 0L;
        }

        protected void showCancelMessage() {
            //Toast.makeText(context, mTaskCancelledMessage, Toast.LENGTH_SHORT).show();
        }

        protected void showError(Context context, Throwable t) {
            //TODO exception
            String errorMessage = context.getString(R.string.str_network_error);
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Long result) {
            // TODO Auto-generated method stub
            liveListAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            //	mImageView.scrollTo(mScrollStep++, 0);
        }

        @Override
        protected void onCancelled() {
            // TODO Auto-generated method stub
            super.onCancelled();
            showCancelMessage();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            finish();
        return super.onKeyDown(keyCode, event);
    }
}
