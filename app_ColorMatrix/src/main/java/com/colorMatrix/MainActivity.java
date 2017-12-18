package com.colorMatrix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotoStyleImageActivity(null);
        //gotoImageActivity(null);
    }

    public void gotoStyleImageActivity(View view) {
        Intent intent = new Intent(this, StyleImageActivity.class);
        startActivity(intent);
    }

    public void gotoImageActivity(View view) {
        Intent intent = new Intent(this, ImageActivity.class);
        startActivity(intent);
    }
}
