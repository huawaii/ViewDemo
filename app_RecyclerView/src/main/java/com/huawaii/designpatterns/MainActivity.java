package com.huawaii.designpatterns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huawaii on 2016/5/13.
 * http://blog.csdn.net/lmj623565791/article/details/45059587
 * http://www.imooc.com/learn/424
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private SimpleAdapter mSimpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        initViews();
        initEvent();
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private void initViews() {
        mSimpleAdapter = new SimpleAdapter(this, mDatas);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerView);

        mRecyclerView.setAdapter(mSimpleAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
    }

    private void initEvent() {
        mSimpleAdapter.setOnItemClickLitener(new SimpleAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_add:
                mSimpleAdapter.addData(1);
                break;
            case R.id.id_action_delete:
                mSimpleAdapter.removeData(1);
                break;
            case R.id.id_action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.id_action_gridview:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
                break;
            case R.id.id_action_horizontalGridView:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.id_action_staggeredgridview:
                Intent intent = new Intent(this, StaggeredGridLayoutActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

}
