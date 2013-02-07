package edu.research.p2ptestapp;

import java.util.ArrayList;

import net.simonvt.widget.MenuDrawer;
import net.simonvt.widget.MenuDrawerManager;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import edu.research.p2ptestapp.fragments.NetworkFragment;
import edu.research.p2ptestapp.fragments.WebFragment;
import edu.research.p2ptestapp.slideinmenu.MenuListView;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends BaseDrawerActivity {

	private MenuAdapter mAdapter;
	private MenuListView mList;
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getIntent().getStringExtra(TITLE) != null) {
			getActionBar().setTitle(getIntent().getStringExtra(TITLE));
		}

		mMenuDrawer = new MenuDrawerManager(this, MenuDrawer.MENU_DRAG_CONTENT);
		mMenuDrawer.setContentView(R.layout.fragment_layout);

		// Adds the browser as the first fragment as we would like the user to
		// start out in this screen
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment startFrag = new WebFragment();
		ft.replace(R.id.fragment_main_layout, startFrag).commit();

		if (savedInstanceState != null) {
			mActivePosition = savedInstanceState.getInt(STATE_ACTIVE_POSITION);
			mContentText = savedInstanceState.getString(STATE_CONTENT_TEXT);
		}

		// Adding items to the MenuDrawer
		// Should be static and can be changed later if need be
		items = new ArrayList<Object>();
		items.add(new Item("Browser", R.drawable.home));
		items.add(new Item("Choose a network", R.drawable.wifi));
		items.add(new Item("Show Cache", 0));
		items.add(new Item("Browse Cloud", 0));
		items.add(new Item("Show Peers", 0));

		mList = new MenuListView(this);
		mAdapter = new MenuAdapter(items);
		mList.setAdapter(mAdapter);
		mList.setOnItemClickListener(mItemClickListener);
		mList.setOnScrollChangedListener(new MenuListView.OnScrollChangedListener() {

			@Override
			public void onScrollChanged() {
				mMenuDrawer.getMenuDrawer().invalidate();
			}
		});

		mMenuDrawer.setMenuView(mList);
	}

	private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			FragmentTransaction ft = getFragmentManager().beginTransaction();
			Fragment temp;
			mMenuDrawer.setActiveView(webView);

			switch (position) {
			case 0:
				// Browser
				if (view != findViewById(R.layout.web_browser)) {
					temp = new WebFragment();
					ft.replace(R.id.fragment_main_layout, temp).commit();
				}
				break;
			case 1:
				// Choose a network
				if (view != findViewById(R.layout.fragment_networks)) {
					temp = new NetworkFragment();
					ft.replace(R.id.fragment_main_layout, temp).commit();
				}
				break;
			case 2:
				// Show Cache
				if (view != findViewById(R.layout.cloud_browser)) {
					
				}
				break;
			case 3:
				// Browse Cloud
				if (view != findViewById(R.layout.cloud_browser)) {

				}
				break;
			case 4:
				// Show Peers
				if (view != findViewById(R.layout.cloud_browser)) {

				}
				break;
			default:
				temp = new WebFragment();
				ft.replace(R.layout.fragment_layout, temp);
				break;
			}

			Log.d("position", position + "");
			mMenuDrawer.closeMenu();
			// ft.commit();
		}
	};
}
