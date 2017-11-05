package com.huawaii.viewDraw;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Copyright (C) 2017 huawaii. All rights reserved.
 *
 * @author huawaii on 2017/11/5.
 */

public class RoundImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roundimage);

        ImageView imageView1 = (ImageView) findViewById(R.id.big_picture);
        imageView1.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.panda1));

        ImageView imageView2 = (ImageView) findViewById(R.id.mz_big_picture);
        imageView2.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.panda1));
    }
}
