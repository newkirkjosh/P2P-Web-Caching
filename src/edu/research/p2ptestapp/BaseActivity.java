package edu.research.p2ptestapp;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MenuItem;
import edu.research.p2ptestapp.receivers.P2PBroadcastReceiver;

public class BaseActivity extends Activity {

	public final static String TITLE = "title";
	public Channel mChannel;
	public WifiP2pManager mWifiP2pManager;
	public P2PBroadcastReceiver mReceiver;
	public final IntentFilter mIntentFilter = new IntentFilter();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		if( getIntent().getStringExtra(TITLE) != null ){
			getActionBar().setTitle(getIntent().getStringExtra(TITLE));
		}
		
		//  Indicates a change in the Wi-Fi Peer-to-Peer status.
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);

	    // Indicates a change in the list of available peers.
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);

	    // Indicates the state of Wi-Fi P2P connectivity has changed.
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

	    // Indicates this device's details have changed.
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

		
		// Connect the app with the Wi-Fi Direct Framework
		mWifiP2pManager = (WifiP2pManager)getSystemService(Context.WIFI_P2P_SERVICE);
		mChannel = mWifiP2pManager.initialize(this, getMainLooper() , null);
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
					super.handleMessage(msg);
				}
			};
		}
	}
}
