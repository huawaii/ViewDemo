package com.huawaii.viewDraw;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotoCanvasActivity(null);
    }

    public void gotoRoundImageActivity(View view) {
        Intent intent = new Intent(this, RoundImageActivity.class);
        startActivity(intent);
    }

    public void gotoXfermodeActivity(View view) {
        Intent intent = new Intent(this, XfermodeActivity.class);
        startActivity(intent);
    }

    public void gotoCanvasActivity(View view) {
        Intent intent = new Intent(this, CanvasActivity.class);
        startActivity(intent);
    }
}
