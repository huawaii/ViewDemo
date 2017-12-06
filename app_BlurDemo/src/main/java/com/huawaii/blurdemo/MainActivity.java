package com.huawaii.blurdemo;

import android.app.Activity;
import android.os.Bundle;

import com.meizu.common.renderer.effect.GLRenderer;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GLRenderer.initialize(this);

        setContentView(R.layout.activity_main);
    }
}
