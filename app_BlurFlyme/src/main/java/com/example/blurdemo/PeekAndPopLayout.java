package com.example.blurdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by zhouxiang on 15/12/5.
 */
class PeekAndPopLayout extends FrameLayout {

    private float mForce = 0;
    private Paint mPaint = null;
    private Rect mRect = null;

    private float FORCE_HINT = 0.5f;
    private float FORCE_PEEK = 0.8f;
    private float FORCE_POP = 1.0f;
    private float FORCE_MIN_CHANGE = 0.001f;

    // 0:暗示之前，1；正在暗示，2:Peek弹出动画；3、Peek展示，4:Peek取消动画，5:Pop动画，6:无需暗示，7、Menu展示，8、Pop到Activity中
    private int mPeekAndPopState = 0;
    private WeakReference<View> mPeekView = null;

    private float mAnimProgress = 0;
    private float ANIM_STEP = 0.1f;

    private PeekAndPopHelper.PeekAndPopConfig mConfig = new PeekAndPopHelper.PeekAndPopConfig();
    private ViewGroup mSource;
    private PeekAndPopHelper.PeekAndPopListener mListener;

    private TextView mText;

    private int[] mLocation = new int[2];
    private PeekAndPopHelper.TouchEventHandler mTouchEventHandler = new PeekAndPopHelper
            .TouchEventHandler() {

        @Override
        public boolean onTouch(MotionEvent e) {
            if (mListener != null) {
                float force = e.getSize();

                switch (mPeekAndPopState) {
                    case 0: {
                        mForce = force;
                        if (mForce >= FORCE_HINT) {
                            mConfig.reset();
                            View peekView = mListener.peek(e.getX(), e.getY(), mConfig);
                            if (peekView != null) {
                                mPeekView = new WeakReference<View>(peekView);
                                mPeekAndPopState = 1;
                                addPeekView();
                                invalidate();
                            } else {
                                if (mConfig.mPeekActivity) {
                                    mPeekAndPopState = 1;
                                    invalidate();
                                } else {
                                    mPeekAndPopState = 6;
                                }
                            }
                        }
                    }
                    break;
                    case 1: {
                        if (Math.abs(mForce - force) >= FORCE_MIN_CHANGE) {
                            mForce = force;
                            invalidate();
                            if (mForce >= FORCE_PEEK) {
                                mPeekAndPopState = 2;
                                mAnimProgress = 0;
                            }
                        }
                    }
                    break;
                    case 3: {
                        if (Math.abs(mForce - force) >= FORCE_MIN_CHANGE) {
                            mForce = force;
                            invalidate();
                            if (mForce > FORCE_POP) {
                                //mPeekAndPopState = 5;
                                View peekView = mPeekView == null ? null : mPeekView.get();
                                removePeekView();
                                if (mListener.pop(peekView)) {
                                    mPeekAndPopState = 8;
                                } else {
                                    mPeekAndPopState = 6;
                                    mPeekView = null;
                                }
                            }
                        }
                    }
                    break;
                    default:
                }

                if (e.getAction() == MotionEvent.ACTION_UP) {
                    if (mPeekAndPopState == 1 || mPeekAndPopState == 6 || mPeekAndPopState == 8) {
                        mPeekAndPopState = 0;
                        mListener.cancel();
                        mForce = 0;
                        mAnimProgress = 0;
                        invalidate();
                        removePeekView();
                        mPeekView = null;
                    }
                    if (mPeekAndPopState == 2 || mPeekAndPopState == 3) {
                        mPeekAndPopState = 4;
                        invalidate();
                    }
                }

                updateDebugInfo();

                if (mPeekAndPopState != 0 && mPeekAndPopState != 6) {
                    return true;
                }
            }
            return false;
        }
    };

    public PeekAndPopLayout(Context context) {
        super(context);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (mPeekAndPopState > 0) {
            switch (mPeekAndPopState) {
                case 1: {
                    drawForHint(canvas, mForce - FORCE_HINT);
                }
                break;
                case 2:
                case 3:
                case 8: {
                    drawForPeek(canvas, FORCE_PEEK - FORCE_HINT);
                }
                break;
                case 4: {
                    drawForPeek(canvas, (FORCE_PEEK - FORCE_HINT) * mAnimProgress);
                }
                break;
                case 5:
                case 6:
                case 7: {
                    super.dispatchDraw(canvas);
                }
                break;
            }
        } else {
            super.dispatchDraw(canvas);
        }
    }

    private void drawForHint(Canvas canvas, float progress) {
        drawBlurBackground(canvas, progress);
        drawSource(canvas, progress);
    }

    private void drawForPeek(Canvas canvas, float progress) {
        drawBlurBackground(canvas, progress);
        if (mPeekAndPopState != 4) {
            drawSource(canvas, progress);
        }
        drawPeekView(canvas);
    }

    private void drawBlurBackground(Canvas canvas, float progress) {
        canvas.save();
        float scale = 1.0f - 0.2f * progress;
        canvas.scale(scale, scale, getWidth() / 2, getHeight() / 2);
        super.dispatchDraw(canvas);
        canvas.restore();

        if (mPaint == null) {
            mPaint = new Paint();
        }
        if (mRect == null) {
            mRect = new Rect();
        }
        getDrawingRect(mRect);
        BlurDrawable.drawBlurRect(canvas, mRect, BlurDrawable.DEFAULT_BLUR_LEVEL * progress * 2,
                mPaint);
    }

    private void drawSource(Canvas canvas, float progress) {
        canvas.save();

        float scale = 1.0f + 0.2f * progress;
        Rect rect = mConfig.mSourceRect;
        canvas.scale(scale, scale, rect.centerX(), rect.centerY());
        if (mAnimProgress > 0) {
            canvas.saveLayerAlpha(rect.left, rect.top, rect.right, rect.bottom, (int) ((1.0f -
                    mAnimProgress) * 255), Canvas.ALL_SAVE_FLAG);
        } else {
            canvas.clipRect(rect);
        }

        View p = (View) getParent();
        Drawable bg = p == null ? null : p.getBackground();
        if (bg != null) {
            bg.draw(canvas);
        }

        mSource.draw(canvas);

        if (mAnimProgress > 0) {
            canvas.restore();
        }

        canvas.restore();
    }

    private void drawPeekView(Canvas canvas) {
        boolean needInvalidate = true;

        if (mPeekAndPopState == 2) {
            mAnimProgress += ANIM_STEP;
            if (mAnimProgress >= 1) {
                mAnimProgress = 1;
                mPeekAndPopState = 3;
            }
        } else if (mPeekAndPopState == 4) {
            mAnimProgress -= ANIM_STEP;
            if (mAnimProgress <= 0) {
                mAnimProgress = 0;
                mPeekAndPopState = 0;
                mListener.cancel();
            }
        } else {
            needInvalidate = false;
        }

        View peekView = mPeekView == null ? null : mPeekView.get();

        if (peekView != null) {
            canvas.save();

            float scale = mAnimProgress * 0.9f;
            Rect rect = mConfig.mSourceRect;
            canvas.scale(scale, scale, rect.centerX(), rect.centerY());

            if (mAnimProgress < 1.0f) {
                peekView.getLocationInWindow(mLocation);
                canvas.saveLayerAlpha(mLocation[0], mLocation[1], mLocation[0] + peekView
                        .getWidth(), mLocation[1] + peekView.getHeight(),
                        (int) (mAnimProgress * 255), Canvas.ALL_SAVE_FLAG);
            }

            peekView.draw(canvas);

            if (mAnimProgress < 1.0f) {
                canvas.restore();
            }
            canvas.restore();
        }

        updateDebugInfo();

        if (mPeekAndPopState == 0) {
            removePeekView();
            mPeekView = null;
        }

        if (needInvalidate) {
            invalidate();
        }
    }

    public void onActivityPopReady() {
        mPeekView = null;
        Log.d("XXX", "XXXXXXXXX@!!!!!!!!");
        Log.d("XXX", "XXXXXXXXX@!!!!!!!!");
        Log.d("XXX", "XXXXXXXXX@!!!!!!!!");
        updateDebugInfo();
    }

    public void onActivityPeekReady(View v) {
        if (v != null) {
            mPeekView = new WeakReference<View>(v);
            Log.d("XXX", "XXXXXXXXX@!!!!!!!!");
            Log.d("XXX", "XXXXXXXXX@!!!!!!!!");
            Log.d("XXX", "XXXXXXXXX@!!!!!!!!");
            updateDebugInfo();
        }
    }

    public PeekAndPopHelper.TouchEventHandler getTouchEventHandler() {
        return mTouchEventHandler;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        if (mTouchEventHandler.onTouch(e)) {
            return true;
        }
        return super.dispatchTouchEvent(e);
    }

    private void addPeekView() {
        View peekView = mPeekView == null ? null : mPeekView.get();
        if (peekView != null) {
            addView(peekView);
            peekView.setVisibility(View.INVISIBLE);
        }
    }

    private void removePeekView() {
        if (!mConfig.mPeekActivity) {
            View peekView = mPeekView == null ? null : mPeekView.get();
            if (peekView != null) {
                removeView(peekView);
                peekView.setVisibility(View.VISIBLE);
            }
        }
    }

    void updateDebugInfo() {
        String t = String.format("mForce: %.2f, mPeekAndPopState: %d, mAnimProgress %.2f",
                mForce, mPeekAndPopState, mAnimProgress);
        mText.setText(t);
        Log.d("XXX", t);
    }

    void setPeekAndPopListener(ViewGroup source, PeekAndPopHelper.PeekAndPopListener listener) {
        mSource = source;
        mListener = listener;
        mText = new TextView(source.getContext());
        mText.setText("测试");
        mText.setTextColor(0xFFFF0000);
        ((ViewGroup) mSource.getRootView()).addView(mText);
    }
}
