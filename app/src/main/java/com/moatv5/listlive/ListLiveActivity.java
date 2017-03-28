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
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.provider.Contacts.SettingsColumns.KEY;

public class ListLiveActivity  extends Activity {
	private ArrayList<LiveChannel> channelList = new ArrayList<LiveChannel>();
	private ListLiveAdapter liveListAdapter;
	private ListView listView;
	private Context mContext;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.listlive);

		mContext = this.getBaseContext();
		ChannelTask channelTask = new ChannelTask(mContext);
		channelTask.execute();
	    
	    liveListAdapter = new ListLiveAdapter(ListLiveActivity.this, 
	            channelList);
	 		
 		if(liveListAdapter != null) {
 			liveListAdapter.notifyDataSetChanged();
 			listView = (ListView)findViewById(R.id.listGrid);
 			listView.setAdapter(liveListAdapter);
 			listView.setFocusable(false);
 			//listView.setOnItemClickListener(this);
			listView.setItemsCanFocus(true);
			listView.setDividerHeight(0);
			//listView.setOnScrollListener(this);
        }
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
				for(int i = 0; i < jArray.length(); i++) {
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
				for(int i = 0; i < jArray.length(); i++) {
					JSONObject json_data = jArray.getJSONObject(i);

					String code = json_data.getString("chenal");
					String time = json_data.getString("time");
					String title = json_data.getString("title");
					String state = json_data.getString("state");

					if (code != null && time != null) {
						ListLiveItemData listItem = new ListLiveItemData(title, state, time, "link");
						if (channelList.size() > 0)
							for(LiveChannel liveChannel : channelList) {
								if(liveChannel.getCode().equals(code)) {
									liveChannel.getListLiveDataList().add(listItem);

							}
							//Log.d("sdfsdf", "" + channelList.get(0).getListLiveDataList().size());
						}
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
		if(keyCode == KeyEvent.KEYCODE_BACK)
			finish();
		return super.onKeyDown(keyCode, event);
	}
}
