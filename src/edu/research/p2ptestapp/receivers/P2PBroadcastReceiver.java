package edu.research.p2ptestapp.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class P2PBroadcastReceiver extends BroadcastReceiver{
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
	}

}
