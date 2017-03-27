package com.moatv5.setting;

import java.io.BufferedInputStream; 
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.moatv5.model.Constant;
import com.moatv5.settop.R;
import com.noh.util.ImageDownloader;
import com.noh.util.PostHttp;
import com.noh.util.Util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class SpeedActivity extends Activity {
    /** Called when the activity is first created. */
	
	//private TextView txtNowDay = null;
	//private TextView txtNowTime = null;
	
	private ImageView imgArrow;
	private Button btnSpeedCheck;
	private Button btnSelectServer;
	
	private DownloadTask downloadTask= null;
	private ProgressDialog mProgress;
	private Button expireText;
	private ImageView ivTel = null;
	private TextView txtSpeed = null;
	
	private ImageView imgLock = null;
	private ImageView imgNet = null;
	private Context mContext = null;
	private Spinner txtServer = null;
	
	private TimerTask timerTask;
	
	public String videoUrl = "";
	public int idxServer = 0;
	public ArrayList<String> serverList = new ArrayList<String>();
	public ArrayList<String> videoList = new ArrayList<String>();
	private ProgressBar progressBar1;
	private int backWidth;
	public ImageView speedback;
	 
	public SpeedActivity(){
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speedcheck);
        
        mContext = this.getBaseContext();
        
        ImageDownloader imageDownloader = new ImageDownloader();
		ivTel = (ImageView)findViewById(R.id.tel);
		imageDownloader.download(Constant.mainUrl + "/image/info_tel.png", (ImageView)ivTel);
        
		imgLock = (ImageView)findViewById(R.id.imgLock);
		if(!Util.getChildset(mContext))
			imgLock.setBackgroundResource(R.drawable.image_main_lock_off);
		else
        	imgLock.setBackgroundResource(R.drawable.image_main_lock_on);
        imgNet = (ImageView)findViewById(R.id.imgNet);
        if(!Util.checkNetwordState(mContext))
        	imgNet.setBackgroundResource(R.drawable.image_main_net_off);
        
        expireText = (Button) findViewById(R.id.imgExpireDate);
        Button expireText2 = (Button) findViewById(R.id.imgExpireDate2);
        if(!getExpireDate().equals("")){
        	expireText.setText(getExpireDate().substring(0, 2));
        	expireText2.setText(getExpireDate().substring(2, 4));
        }
        
        //txtNowDay = (TextView) findViewById(R.id.txtNowDay);
        //txtNowTime = (TextView) findViewById(R.id.txtNowTime);

        
        
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        txtServer = (Spinner) findViewById(R.id.txtServer);
        
        speedback = (ImageView) findViewById(R.id.speedback);

        imgArrow = (ImageView) findViewById(R.id.imgArrow);
        btnSpeedCheck = (Button) findViewById(R.id.btnSpeedCheck);
        btnSelectServer = (Button) findViewById(R.id.btnSelectServer);
        txtSpeed = (TextView) findViewById(R.id.txtSpeed);
        btnSpeedCheck.requestFocus();
        VideoTask videoTask=  new VideoTask(getBaseContext());
        videoTask.execute();
        
        speedback.post(new Runnable(){
            @Override
            public void run() {
            	backWidth = speedback.getWidth();
                  Log.e("DEBUG", "h2: " + speedback.getHeight());
                  Log.e("DEBUG", "w2: " + speedback.getWidth());    }
        });
    }
    
    
	@Override
	protected void onDestroy() {
		if(timerTask != null){
			timerTask.cancel();
		}
		if(downloadTask != null){
			downloadTask = null;
		}
		if(mProgress != null){
			mProgress = null;
		}
		
		super.onDestroy();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
			
		} else if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {

		}else if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {

		}else if(keyCode == KeyEvent.KEYCODE_DPAD_UP) {

		}else if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
			//clickOkButton();
		}
  	  return super.onKeyDown(keyCode, event); 
	}
	
	 class DownloadTask extends AsyncTask<String, Integer, Long> {
    	private Context mContext;
    	private String result = "";
    	float speed = 0;
    	
    	public DownloadTask(Context context) {
    		mContext = context;
    	}
    
    	@Override
    	protected void onPreExecute() {
    		// TODO Auto-generated method stub
    		super.onPreExecute();
    		mProgress = ProgressDialog.show(SpeedActivity.this, "", getResources().getString(R.string.wait), true, true);
    		//mProgress.setCancelable(false);
    	}
    
    	@Override
    	protected Long doInBackground(String... params) {
    		// TODO Auto-generated method stub
    		int count = 0;
    		float result = 0;
    		
    	    try {
    	    	Thread.sleep(100);
    	    	
    	        URL url = new URL(videoList.get(idxServer));
    	        URLConnection conexion = url.openConnection();
    	        conexion.connect();
    	 
    	        int lenghtOfFile = conexion.getContentLength();
    	 
    	        
    	        InputStream input = new BufferedInputStream(url.openStream());
    	        //OutputStream output = new FileOutputStream("/sdcard/Downloadvideo.mp4");
    	 
    	        byte data[] = new byte[1024];
    	 
    	        long total = 0;
    	 
    	        long startTime = System.currentTimeMillis();
    	        while ((count = input.read(data)) != -1) {
    	            total += count;
    	            //publishProgress("" + (int) ((total * 100) / lenghtOfFile));
    	           // output.write(data, 0, count);
    	            //Log.e(null, "total:" + total + "count:" +count);
    	            if(total >= 500000)
    	            	break;
    	        }
    	        long endTime = System.currentTimeMillis();
    	        //output.flush();
    	        //output.close();
    	        input.close();
    	        
    	        long diffTime = endTime - startTime;
    	        result = (500000/diffTime);
    	        speed = result/(float)1000;
    	        Log.e(null, "time:" + (500000/diffTime));
    	        if(result <= 0){
    	        	return 2L;
    	        }else if(result <= 1000){
    	        	result = result * ((float)453/(float)1000);
    	        	//Log.e(null, "result:" + result);
    	        	return (long)result+2L;
    	        }else if(result <= 5000){
    	        	result -= 1000;
    	        	result = result * ((float)910/(float)4000);
    	        	//Log.e(null, "result:" + result);
    	        	return (long)result+453L;
    	        }else if(result <= 10000){
    	        	result = result * ((float)227/(float)5000);
    	        	//Log.e(null, "result:" + result);
    	        	return (long)result+1137L;
    	        }else if(result <= 20000){
    	        	result = result * ((float)227/(float)10000);
    	        	//Log.e(null, "result:" + result);
    	        	return (long)result+1364L;
    	        }else{
    	        	return (long)1820L;
    	        }
    	       
    	        // 작업이 진행되면서 호출하며 화면의 업그레이드를 담당하게 된다
    	        //publishProgress("progress", 1, "Task " + 1 + " number");
    	         
    	    } catch (InterruptedException e) {
    	        e.printStackTrace();
    	        return 35L;
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	        return 35L;
    	    }
    	 
    	    // 수행이 끝나고 리턴하는 값은 다음에 수행될 onProgressUpdate 의 파라미터가 된다
    	   // return result;

    	}
    
    	@SuppressLint("NewApi")
		@Override
    	protected void onPostExecute(Long lresult) {
    		// TODO Auto-generated method stub
    		imgArrow.setX((float)lresult * backWidth/1872);
    		if(mProgress != null)
    			mProgress.dismiss();
    		txtSpeed.setText(String.valueOf(speed));
    	}
    
    	@Override
    	protected void onProgressUpdate(Integer... values) {
    		// TODO Auto-generated method stub
    	}
    
    	@Override
    	protected void onCancelled() {
    		// TODO Auto-generated method stub
    		super.onCancelled();
    	}
    }
	 
	 class VideoTask extends AsyncTask<String, Integer, Boolean> {
			private Context mContext;
			boolean success = false;
			
	    	public VideoTask(Context context) {
	    		mContext = context;
	    	}
	    
	    	@Override
	    	protected void onPreExecute() {
	    		// TODO Auto-generated method stub
	    		super.onPreExecute();
	    	}
	    
	    	@Override
	    	protected Boolean doInBackground(String... params) {
	    		// TODO Auto-generated method stub
	    		boolean loginSuccess = false;
		    	String strJson = "";
	    		PostHttp postmake = new PostHttp();
	    		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

	    		strJson = postmake.httpConnect( 
	    				/*Constant.mainUrl + */Constant.mainUrl + "/module/tv/speed_check.php", nameValuePairs);
	    		try{
	    			JSONObject json_data = new JSONObject(strJson); 
	    			String items = json_data.getString("item");
	    			JSONObject items_data = new JSONObject(items);

		    		if(items_data.getString("resultCode").equals("0")){
		    			String strItemArray = json_data.getString("itemArray");
		    			JSONArray jArray = new JSONArray(strItemArray);
		    			
		    			for(int i = 0; i < jArray.length(); i++){
		    				JSONObject item_data = jArray.getJSONObject(i);
							serverList.add(item_data.getString("serverUrl"));
							videoList.add(item_data.getString("videoUrl"));
		    			}
		    			loginSuccess = true;
		    		}else if(items_data.getString("resultCode").equals("1")){
		    			loginSuccess = false;
		    		}
	    		}catch(JSONException e){
	    		}
	    		if(loginSuccess)
	    			return true;
	    		else
	    			return false;
	    	}
		    
	    	@Override
	    	protected void onPostExecute(Boolean result) {
	    		// TODO Auto-generated method stub
	    		if (result) {
	    			btnSpeedCheck.setVisibility(View.VISIBLE);
	    			txtServer.setAdapter(new ArrayAdapter<String>(SpeedActivity.this, R.layout.spinner_item, serverList));
	    			btnSelectServer.setOnClickListener(new OnClickListener(){
	        			@Override
	        			public void onClick(View arg0) {
	        				// TODO Auto-generated method stub
	        				txtServer.performClick();
	        			}
	                });
	    			btnSpeedCheck.setOnClickListener(new OnClickListener(){
	        			@Override
	        			public void onClick(View arg0) {
	        				// TODO Auto-generated method stub
	        				DownloadTask downloadTask = new DownloadTask(getBaseContext());
	    	    	        downloadTask.execute();
	        			}
	                });
	    			
	    			//txtServer.setText(serverList.get(idxServer));
	    			
		        }
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
	    		
	    	}	
	   }

	private String getExpireDate(){
		SharedPreferences sp = getSharedPreferences(Util.getApplicationName(getApplicationContext()), MODE_PRIVATE);
		return sp.getString("expiredate", "");
	}
		
	//맥어드레스 가져오기
	private String getMacaddress() {
		SharedPreferences sp = getSharedPreferences(Util.getApplicationName(getApplicationContext()), MODE_PRIVATE);
		return sp.getString("macaddress", "");
	}
	
}

