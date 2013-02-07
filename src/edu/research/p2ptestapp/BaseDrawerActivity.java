package edu.research.p2ptestapp;

import java.util.List;

import net.simonvt.widget.MenuDrawer;
import net.simonvt.widget.MenuDrawerManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseDrawerActivity extends BaseActivity{

	protected static final String STATE_MENUDRAWER = "net.simonvt.menudrawer.samples.ContentSample.menuDrawer";
    protected static final String STATE_ACTIVE_POSITION = "net.simonvt.menudrawer.samples.ContentSample.activePosition";
    protected static final String STATE_CONTENT_TEXT = "net.simonvt.menudrawer.samples.ContentSample.contentText";
    protected static final String KEY_TITLE = "TITLE";
    protected static final String KEY_ICON = "ICON";
    protected static final String KEY_URL = "URL";
    
    protected MenuDrawerManager mMenuDrawer;
    public List<Object> items;
    protected int mActivePosition = -1;
    protected String mContentText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	getActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);
    	mMenuDrawer.onRestoreDrawerState(savedInstanceState.getParcelable(STATE_MENUDRAWER));
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	outState.putParcelable(STATE_MENUDRAWER, mMenuDrawer.onSaveDrawerState());
    	outState.putInt(STATE_ACTIVE_POSITION, mActivePosition);
    	outState.putString(STATE_CONTENT_TEXT, mContentText);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch(item.getItemId()){
    		case android.R.id.home:
    			mMenuDrawer.toggleMenu();
    			return true;
    	}
    	
    	return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onBackPressed() {
    	final int drawerState = mMenuDrawer.getDrawerState();
        if (drawerState == MenuDrawer.STATE_OPEN || drawerState == MenuDrawer.STATE_OPENING) {
            mMenuDrawer.closeMenu();
            return;
        }
        
    	super.onBackPressed();
    }
    
    protected class Item {
    	public String mTitle;
    	public int mIconResId;
    	
    	public Item(String title, int iconResId){
    		mTitle = title;
    		mIconResId = iconResId;
    	}
    }
    
    protected class MenuAdapter extends BaseAdapter {

        public List<Object> mItems;

        public MenuAdapter(List<Object> items) {
            mItems = items;
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            return getItem(position) instanceof Item ? 0 : 1;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public boolean isEnabled(int position) {
            return getItem(position) instanceof Item;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null)
                v = getLayoutInflater().inflate(R.layout.menu_row_item, parent, false);

            TextView itemTitle = (TextView) v.findViewById(R.id.itemTitle);
            ImageView icon = (ImageView) v.findViewById(R.id.icon);
            itemTitle.setText(((Item) items.get(position)).mTitle);
            icon.setImageResource(((Item)items.get(position)).mIconResId);
            

            v.setTag(R.id.mdActiveViewPosition, position);

            if (position == mActivePosition) {
                mMenuDrawer.setActiveView(v, position);
            }

            return v;
        }
    }
}
