package com.example.blurdemo;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;

/**
 * Created by zhouxiang on 15/12/12.
 */
public class DispatchLayout extends FrameLayout {

    private WeakReference<PeekAndPopHelper.TouchEventHandler> mTouchEventHandler;

    public DispatchLayout(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        PeekAndPopHelper.TouchEventHandler handler = mTouchEventHandler == null ? null :
                mTouchEventHandler.get();
        if (handler != null) {
            handler.onTouch(e);
            return true;
        }
        return super.dispatchTouchEvent(e);
    }

    public void setTouchEventHandler(PeekAndPopHelper.TouchEventHandler handler) {
        if (handler == null) {
            mTouchEventHandler = null;
        } else {
            mTouchEventHandler = new WeakReference<PeekAndPopHelper.TouchEventHandler>(handler);
        }
    }
}
