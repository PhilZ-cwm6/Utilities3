package com.sentaroh.android.Utilities3.Widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;


public class ExtendImageViewTouch extends ImageViewTouch{

//	
//http://stackoverflow.com/questions/19562312/disable-swipe-in-pageradapter-if-imageviewtouch-is-in-zoom-mode
//	
	
	static final float SCROLL_DELTA_THRESHOLD = 1.0f;

	private final static String DEBUG_TAG = "ExtendImageViewTouch";

	public ExtendImageViewTouch(Context context, AttributeSet attrs,
                                int defStyle) {
	    super(context, attrs, defStyle);
	    init();
	}

	public ExtendImageViewTouch(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    init();
	}

	public ExtendImageViewTouch(Context context) {
	    super(context);
	    init();
	}

	private void init() {
//		LOG_ENABLED=false;
		View.OnTouchListener listener = new View.OnTouchListener() {
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	        	if (LOG_ENABLED) 
	        		Log.v(DEBUG_TAG,"OnTouchListener onTouch scale="+getScale());
	            if (getScale() > 1f) {
	                getParent().requestDisallowInterceptTouchEvent(true);
	            } else {
	                getParent().requestDisallowInterceptTouchEvent(false);
	            }
	            return false;
	        }
	    };
	    setOnTouchListener(listener);
	    setDisplayType(DisplayType.FIT_TO_SCREEN);
	}

	@Override
	public boolean onTouchEvent( MotionEvent event ) {
    	if (LOG_ENABLED) 
    		Log.v(DEBUG_TAG,"onTouchEvent scale="+getScale());
    	
		mScaleDetector.onTouchEvent( event );

		if ( !mScaleDetector.isInProgress() ) {
			mGestureDetector.onTouchEvent( event );
		}

		int action = event.getAction();
		switch ( action & MotionEvent.ACTION_MASK ) {
			case MotionEvent.ACTION_UP:
				return onUp( event );
		}
		return true;
	}


	@Override
	protected float onDoubleTapPost(float scale, float maxZoom) {
	    if (scale!=1f) {
	        mDoubleTapDirection = 1;
	        return 1f;
	    }
	    if (mDoubleTapDirection == 1) {
	        mDoubleTapDirection = -1;
	        if ( ( scale + ( mScaleFactor * 2 ) ) <= maxZoom ) {
	            return scale + mScaleFactor;
	        } else {
	                mDoubleTapDirection = -1;
	                return maxZoom;
	        }
	    } else {
	        mDoubleTapDirection = 1;
	        return 1f;
	    }
	}

	@Override
	final public boolean canScroll( int direction ) {
        RectF bitmapRect = getBitmapRect();
        updateRect( bitmapRect, mScrollRect );
        Rect imageViewRect = new Rect();
        getGlobalVisibleRect(imageViewRect);
        
        if( null == bitmapRect ) return false;

//      Landscape対応のために変更
//        if ( Math.abs(bitmapRect.right-imageViewRect.right) < (SCROLL_DELTA_THRESHOLD) ) {
        if ( Math.abs(bitmapRect.right-(imageViewRect.right-imageViewRect.left)) < (SCROLL_DELTA_THRESHOLD) ) {
            if ( direction < 0 ) {
            	if (LOG_ENABLED) Log.v(DEBUG_TAG,"canScroll right=false");
            	return false;
            }
        }
        if (Math.abs(bitmapRect.left-mScrollRect.left) < SCROLL_DELTA_THRESHOLD) {
            if ( direction > 0 ) {
            	if (LOG_ENABLED) Log.v(DEBUG_TAG,"canScroll left=false");
            	return false;
            }
        }
        return true;
	}

	@Override
	final public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY ) {
	    if ( getScale() == 1f ) return false;
	    if (distanceX!=0 && !canScroll((int)-distanceX)) {
	    	if (LOG_ENABLED) 
	    		Log.v(DEBUG_TAG,"onScroll requestDisallowInterceptTouchEvent(false)");
	        getParent().requestDisallowInterceptTouchEvent(false);
	        return false;
	    } else {
	    	if (LOG_ENABLED) 
	    		Log.v(DEBUG_TAG,"onScroll requestDisallowInterceptTouchEvent(true)");
	        getParent().requestDisallowInterceptTouchEvent(true);
	        mUserScaled = true;
	        scrollBy( -distanceX, -distanceY );
	        invalidate();
	        return true;
	   }
	}
	
}
