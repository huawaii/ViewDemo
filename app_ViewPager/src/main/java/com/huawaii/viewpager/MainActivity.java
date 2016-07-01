package com.huawaii.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends Activity {
    private ViewPager viewPager;
    private TextView tv_intro;
    private LinearLayout dot_layout;

    private ArrayList<com.huawaii.viewpager.Ad> list = new ArrayList<com.huawaii.viewpager.Ad>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
        initData();
    }


    private void initView() {
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tv_intro = (TextView) findViewById(R.id.tv_intro);
        dot_layout = (LinearLayout) findViewById(R.id.dot_layout);
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Log.e("Activity", "position: " + position);
                if(position == 0)
                    viewPager.setCurrentItem(position + 1);
                else if(position == (list.size() -1))
                    viewPager.setCurrentItem(position - 1);
                updateIntroAndDot();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initData() {
        list.add(new com.huawaii.viewpager.Ad(R.drawable.a, "111"));
        list.add(new com.huawaii.viewpager.Ad(R.drawable.b, "222"));
        list.add(new com.huawaii.viewpager.Ad(R.drawable.c, "333"));
        list.add(new com.huawaii.viewpager.Ad(R.drawable.d, "444"));
        list.add(new com.huawaii.viewpager.Ad(R.drawable.e, "555"));

        initDots();

        viewPager.setAdapter(new MyPagerAdapter());

        updateIntroAndDot();
    }

    /**
     * 初始化dot
     */
    private void initDots() {
        for (int i = 0; i < list.size(); i++) {
            View view = new View(this);
            LayoutParams params = new LayoutParams(8, 8);
            if (i != 0) {
                params.leftMargin = 5;
            }
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.selector_dot);
            dot_layout.addView(view);
        }
    }

    /**
     * 更新文本
     */
    private void updateIntroAndDot() {
        int currentPage = viewPager.getCurrentItem() % list.size();
        tv_intro.setText(list.get(currentPage).getIntro());

        for (int i = 0; i < dot_layout.getChildCount(); i++) {
            dot_layout.getChildAt(i).setEnabled(i == currentPage);
        }
    }

    class MyPagerAdapter extends PagerAdapter {

        /**
         * 返回多少page
         */
        @Override
        public int getCount() {
            return list.size();
        }

        /**
         * true: 表示不去创建，使用缓存  false:去重新创建
         * view： 当前滑动的view
         * object：将要进入的新创建的view，由instantiateItem方法创建
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 类似于BaseAdapger的getView方法
         * 用了将数据设置给view
         * 由于它最多就3个界面，不需要viewHolder
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(MainActivity.this, R.layout.adapter_ad, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);

            com.huawaii.viewpager.Ad ad = list.get(position % list.size());
            imageView.setImageResource(ad.getIconResId());

            container.addView(view);//一定不能少，将view加入到viewPager中

            return view;
        }

        /**
         * 销毁page
         * position： 当前需要消耗第几个page
         * object:当前需要消耗的page
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }


    }

}
