package com.example.blurdemo;

import android.app.Activity;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.lang.ref.WeakReference;

/**
 * Created by zhouxiang on 15/12/5.
 */
public class PeekAndPopHelper {

    private static PeekAndPopTransitionInfo sTransitionInfo;

    public static void enablePeekAndPop(ViewGroup source, PeekAndPopListener listener) {
        ViewGroup v = (ViewGroup) source.getRootView();
        int childCnt = v.getChildCount();
        if (childCnt == 1) {
            View child = v.getChildAt(0);
            if (!(child instanceof PeekAndPopLayout)) {
                v.removeView(child);
                PeekAndPopLayout peekAndPopLayout = new PeekAndPopLayout(source.getContext());
                peekAndPopLayout.addView(child);
                v.addView(peekAndPopLayout);
                child = peekAndPopLayout;
            }
            ((PeekAndPopLayout) child).setPeekAndPopListener(source, listener);
        }
    }

    public static void prepareForActivityPop(Activity from, View peekView) {
        sTransitionInfo = new PeekAndPopTransitionInfo(from, peekView);
    }

    public static View addViewForActivityPop(ViewGroup to, boolean autoNotify) {
        PeekAndPopTransitionInfo p = sTransitionInfo;
        if (p != null) {
            final View v = p.mView == null ? null : p.mView.get();
            if (v != null) {
                to.addView(v);
                if (autoNotify) {
                    v.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver
                            .OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            v.getViewTreeObserver().removeOnPreDrawListener(this);
                            notifyActivityPopReady();
                            return true;
                        }
                    });
                }
            }
            return v;
        }
        return null;
    }

    public static View addViewForActivityPop(Activity to, boolean autoNotify) {
        PeekAndPopTransitionInfo p = sTransitionInfo;
        if (p != null) {
            final View v = p.mView == null ? null : p.mView.get();
            if (v != null) {
                to.setContentView(v);
                if (autoNotify) {
                    v.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver
                            .OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            v.getViewTreeObserver().removeOnPreDrawListener(this);
                            notifyActivityPopReady();
                            return true;
                        }
                    });
                }
            }
            return v;
        }
        return null;
    }

    public static void notifyActivityPopReady() {
        PeekAndPopTransitionInfo p = sTransitionInfo;
        sTransitionInfo = null;
        if (p != null) {
            Activity a = p.mActivity == null ? null : p.mActivity.get();
            if (a != null) {
                ViewGroup v = (ViewGroup) a.getWindow().getDecorView();
                int childCnt = v.getChildCount();
                if (childCnt > 0) {
                    View child = v.getChildAt(0);
                    if (child instanceof PeekAndPopLayout) {
                        PeekAndPopLayout l = (PeekAndPopLayout) child;
                        l.onActivityPopReady();
                    }
                }
            }
        }
    }

    public static void prepareForActivityPeek(Activity a) {
        sTransitionInfo = new PeekAndPopTransitionInfo(a, null);
    }

    public static void cancelForActivityPeek() {
        PeekAndPopTransitionInfo p = sTransitionInfo;
        sTransitionInfo = null;
        if (p != null) {
            Activity a = p.mActivity == null ? null : p.mActivity.get();
            if (a != null) {
                a.finish();
            }
        }
    }

    public static void notifyActivityPeekReady(Activity b, int id) {
        PeekAndPopTransitionInfo p = sTransitionInfo;
        if (p != null) {
            Activity a = p.mActivity == null ? null : p.mActivity.get();
            if (a != null) {
                ViewGroup v = (ViewGroup) a.getWindow().getDecorView();
                int childCnt = v.getChildCount();
                if (childCnt > 0) {
                    View child = v.getChildAt(0);
                    if (child instanceof PeekAndPopLayout) {
                        PeekAndPopLayout l = (PeekAndPopLayout) child;
                        View peekView = b.findViewById(id);

                        p.mActivity = new WeakReference<Activity>(b);
                        p.mView = new WeakReference<View>(peekView);
                        l.onActivityPeekReady(peekView);

                        v = (ViewGroup) b.getWindow().getDecorView();
                        v.setAlpha(0.0f);

                        childCnt = v.getChildCount();
                        if (childCnt == 1) {
                            child = v.getChildAt(0);
                            if (!(child instanceof DispatchLayout)) {
                                v.removeView(child);
                                DispatchLayout dispatchLayout = new DispatchLayout(b);
                                dispatchLayout.addView(child);
                                v.addView(dispatchLayout);
                                child = dispatchLayout;
                            }

                            DispatchLayout dl = (DispatchLayout) child;
                            dl.setTouchEventHandler(l.getTouchEventHandler());
                        }
                    }
                }
            }
        }
    }

    public static void popForActivityPeek() {
        PeekAndPopTransitionInfo p = sTransitionInfo;
        sTransitionInfo = null;
        if (p != null) {
            Activity a = p.mActivity == null ? null : p.mActivity.get();
            if (a != null) {
                ViewGroup v = (ViewGroup) a.getWindow().getDecorView();
                v.setAlpha(1.0f);

                int childCnt = v.getChildCount();
                if (childCnt > 0) {
                    View child = v.getChildAt(0);
                    if (child instanceof DispatchLayout) {
                        DispatchLayout l = (DispatchLayout) child;
                        l.setTouchEventHandler(null);
                        child = l.getChildAt(0);
                        l.removeView(child);
                        v.removeView(l);
                        v.addView(child);
                    }
                }
            }
        }
    }

    public interface PeekAndPopListener {
        View peek(float x, float y, PeekAndPopConfig config);

        void cancel();

        boolean pop(View peekView);
    }

    interface TouchEventHandler {
        public boolean onTouch(MotionEvent e);
    }

    public static class PeekActionItem {

    }

    public static class PeekAndPopConfig {
        Rect mSourceRect;
        int mPeekWidth;
        int mPeekHeight;
        boolean mQuickMenu;
        boolean mPeekActivity;

        public void reset() {
            mSourceRect = null;
            int mPeekWidth = 0;
            int mPeekHeight = 0;
            boolean mQuickMenu = false;
            boolean mPeekActivity = false;
        }
    }

    private static class PeekAndPopTransitionInfo {
        public WeakReference<Activity> mActivity;
        public WeakReference<View> mView;

        public PeekAndPopTransitionInfo(Activity a, View v) {
            mActivity = a == null ? null : new WeakReference<Activity>(a);
            mView = v == null ? null : new WeakReference<View>(v);
        }
    }
}
