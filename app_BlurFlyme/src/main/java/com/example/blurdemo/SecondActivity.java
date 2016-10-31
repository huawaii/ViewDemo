package com.example.blurdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dpidemo.R;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        BlurDemo.setFullWindow(this, true);

        /*int testCase = 3;

        if (testCase == 0) {
            setContentView(R.layout.activity_second);
            ViewGroup v = (ViewGroup) findViewById(android.R.id.content);
            PeekAndPopHelper.addViewForActivityPop(v, true);
        } else if (testCase == 1) {
            setContentView(R.layout.activity_second);
            ViewGroup v = (ViewGroup) findViewById(R.id.second);
            PeekAndPopHelper.addViewForActivityPop(v, true);
        } else if (testCase == 2) {
            PeekAndPopHelper.addViewForActivityPop((ViewGroup)getWindow().getDecorView(), true);
        } else if (testCase == 3) {
            PeekAndPopHelper.addViewForActivityPop(this, true);
        }*/

        //PeekAndPopHelper.notifyActivityPeekReady(this, android.R.id.content);

        setContentView(R.layout.activity_second);

        PeekAndPopHelper.notifyActivityPeekReady(this, R.id.imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
