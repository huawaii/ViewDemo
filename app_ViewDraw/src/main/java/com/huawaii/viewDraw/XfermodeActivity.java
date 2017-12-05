package com.huawaii.viewDraw;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Copyright (C) 2017 huawaii. All rights reserved.
 *
 * @author huawaii on 2017/11/5.
 */

public class XfermodeActivity extends Activity {

    private View mFrameLayout;
    private View mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfermode);

        mFrameLayout = findViewById(R.id.colorfulFrameLayout);
        mTextView = findViewById(R.id.colorfulTextView);
    }

    public void startAnimation(View view) {
        mTextView.setLayerType(View.LAYER_TYPE_NONE, null);
        mTextView.animate()
                .alpha(0)
                .setDuration(4000)
                .start();
    }
    public void overAnimation(View view) {
        mTextView.setLayerType(View.LAYER_TYPE_NONE, null);
        mTextView.animate()
                .alpha(1)
                .setDuration(4000)
                .start();
    }
}
