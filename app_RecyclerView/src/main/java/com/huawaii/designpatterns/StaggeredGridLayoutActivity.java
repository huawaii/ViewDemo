package com.huawaii.designpatterns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StaggeredGridLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private StaggeredSimpleAdapter mStaggeredSimpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initViews();
        initEvent();
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private void initViews() {
        mStaggeredSimpleAdapter = new StaggeredSimpleAdapter(this, mDatas);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerView);

        mRecyclerView.setAdapter(mStaggeredSimpleAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());   // 设置item动画

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
    }

    private void initEvent() {
        mStaggeredSimpleAdapter.setOnItemClickLitener(new StaggeredSimpleAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(StaggeredGridLayoutActivity.this,
                        position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(StaggeredGridLayoutActivity.this,
                        position + " long click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_staggered, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_add:
                mStaggeredSimpleAdapter.addData(1);
                break;
            case R.id.id_action_delete:
                mStaggeredSimpleAdapter.removeData(1);
                break;
        }
        return true;
    }

}
