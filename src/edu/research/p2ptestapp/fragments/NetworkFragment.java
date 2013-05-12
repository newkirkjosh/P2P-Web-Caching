package edu.research.p2ptestapp.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import edu.research.p2ptestapp.R;

public class NetworkFragment extends Fragment {

	private FragmentManager mFragManager;
	private FragmentTransaction mFragTrans;
	private WifiManager mWifiManager;
	private LruCache<String, String> mCache;
	private int maxCacheSize = 1024;
	boolean mTwoPane;
	int mCurCheckPosition = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the view to be replaced in the main fragment
		View main = inflater.inflate(R.layout.fragment_networks, container,
				false);

		mWifiManager = (WifiManager) getActivity().getSystemService(
				Context.WIFI_SERVICE);
		ListView listNetworks = (ListView) main
				.findViewById(R.id.list_networks);
		ArrayAdapter<String> networkAdapter;
		String[] nulls = { "No networks to be displayed" };

		// Get the list of available networks for the phone
		List<ScanResult> networks = new ArrayList<ScanResult>();

		if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
			if (!mWifiManager.getScanResults().isEmpty()) {
				mWifiManager.getScanResults().addAll(networks);
				Log.d("networks", networks.toString());
				List<String> networkNames = new ArrayList<String>();

				for (ScanResult s : networks) {
					Log.d("ScanResult: ", s.toString());
					networkNames.add(s.SSID);
				}

				networkAdapter = new ArrayAdapter<String>(getActivity(),
						R.layout.networks_item, R.id.radio_network,
						networkNames);
				listNetworks.setAdapter(networkAdapter);
				listNetworks.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Toast.makeText(getActivity(),
								"Position " + position + " was selected.",
								Toast.LENGTH_SHORT).show();
					}
				});
			} else {
				networkAdapter = new ArrayAdapter<String>(getActivity(),
						R.layout.networks_item, R.id.radio_network, nulls);
				listNetworks.setAdapter(networkAdapter);
			}
		}

		if (savedInstanceState != null) {
			mCurCheckPosition = savedInstanceState.getInt("ourChoice", 0);
		}

		return main;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("ourChoice", mCurCheckPosition);
	}
}
