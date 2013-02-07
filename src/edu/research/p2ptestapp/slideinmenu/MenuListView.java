package edu.research.p2ptestapp.slideinmenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ListView;
import edu.research.p2ptestapp.R;

public class MenuListView extends ListView {
	
	private Bitmap logo = ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_launcher)).getBitmap();
	private Paint p = new Paint();
	
	public interface OnScrollChangedListener{
		void onScrollChanged();
	}
	
	private OnScrollChangedListener mOnScrollChangedListener;

	public MenuListView(Context context) {
		super(context);
	}

	public MenuListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public MenuListView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		
		if(mOnScrollChangedListener != null){
			mOnScrollChangedListener.onScrollChanged();
		}
	}
	
	public void setOnScrollChangedListener(OnScrollChangedListener listener){
		mOnScrollChangedListener = listener;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		p.setAlpha(150);
		canvas.drawBitmap(logo, canvas.getWidth()/2 - logo.getWidth()/2, canvas.getHeight()/2 - logo.getHeight()/2, p);
	}
}
