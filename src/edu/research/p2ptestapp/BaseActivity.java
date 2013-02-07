package edu.research.p2ptestapp;

import android.app.Activity;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MenuItem;

public class BaseActivity extends Activity {

	public final static String TITLE = "title";
	public Channel mChannel;
	public WifiP2pManager mWifiP2pManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		if( getIntent().getStringExtra(TITLE) != null ){
			getActionBar().setTitle(getIntent().getStringExtra(TITLE));
		}
		
		mWifiP2pManager.initialize(this, getMainLooper() , null);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
		
	}
	
	class LooperThread extends Thread{
		public Handler mHandler;
		
		public void run(){
			Looper.prepare();
			
			mHandler = new Handler(){
				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					super.handleMessage(msg);
				}
			};
		}
	}
}
