package com.example.blurdemo;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.dpidemo.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlurDemo extends ListActivity {

    private BlurDrawable mBlurDrawable;
    private View mProgressBar;
    private int[] mLocation = new int[2];

    public static void setFullWindow(Activity activity, boolean full) {
        try {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            int trans_status = 0;
            Field flags;
            if (android.os.Build.VERSION.SDK_INT < 19) {
                // API level 19 以下时，Meizu自己加了Flag
                trans_status = 1 << 6;
                flags = lp.getClass().getDeclaredField("meizuFlags");
            } else {
                // 这里其实是使用API Level 19的一个flag，不是一定需要用反射
                flags = lp.getClass().getDeclaredField("flags");
                trans_status = 0x04000000;
            }
            flags.setAccessible(true);
            int value = flags.getInt(lp);
            if (full) {
                value = value | trans_status;
            } else {
                value = value & ~trans_status;
            }
            flags.setInt(lp, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void onBackPressed() {
        if (mImage != null) {
            ((ViewGroup)mImage.getParent()).removeView(mImage);
            mImage = null;
            getListView().setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }*/

    private static void setStatusBarDarkIcon(Activity activity, boolean dark) {
        try {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            Field meizuFlags = lp.getClass().getDeclaredField("meizuFlags");
            meizuFlags.setAccessible(true);
            int value = meizuFlags.getInt(lp);
            if (dark) {
                value |= 1 << 9;
            } else {
                value &= ~(1 << 9);
            }
            meizuFlags.setInt(lp, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置带状态栏透明全屏显示
        //setFullWindow(this, true);

        setContentView(R.layout.list_layout);

        // 填充ListView数据
        List<Map<String, Object>> contents = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 12; ) {
            Map<String, Object> map = new HashMap<String, Object>();
            if (i % 2 == 0) {
                //map.put("ICON", R.drawable.jpeg6);
                map.put("TITLE", ++i + "  Test Title one");
                map.put("CONTENT", "Test Content one");
            } else {
                //map.put("ICON", R.drawable.jpeg7);
                map.put("TITLE", ++i + "  Test Title two Title two");
                map.put("CONTENT", "Test Content two Test Content two");
            }
            contents.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, contents, R.layout.list_item_layout,
                new String[]{ /*"ICON", */"TITLE", "CONTENT"},
                new int[]{ /*android.R.id.icon, */android.R.id.text1, android.R.id.text2});
        setListAdapter(adapter);

        getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.jay));

        // 修改ListView的padding值
        // 请留意styles.xml中对AppThmeme设置了android:windowActionBarOverlay为true
        // 这样ListView的内容才会显示到ActionBar和SmartBar下方
        final ListView v = (ListView) findViewById(android.R.id.list);
//        int actionBarHeight = getActionBarHeight();
//        int statusBarHeight = getStatusBarHeight();
//        v.setPadding(v.getPaddingLeft(), actionBarHeight + statusBarHeight,
//                v.getPaddingRight(), actionBarHeight);
//        v.setClipToPadding(false);

        // 设置ActionBar背景为毛玻璃背景，同时考虑了顶部ActionBar下面的横条
//        ActionBar ab = getActionBar();
//        ColorDrawable cd = new ColorDrawable(0xFFF56313);
//        LayerDrawable ld = new LayerDrawable(new Drawable[] { new BlurDrawable(), cd });
//        ab.setBackgroundDrawable(ld);
//        ab.setSplitBackgroundDrawable(new BlurDrawable());
//        int lineHeight = getResources().getDimensionPixelSize(R.dimen.activity_color_line_height);
//        ld.setLayerInset(1, 0, actionBarHeight + statusBarHeight - lineHeight, 0, 0);

        // 设置状态栏图标为深色
        //setStatusBarDarkIcon(this, true);

        // 中间部分的View设置一个毛玻璃背景，用于演示BlurDrawable的各种参数设置
//        mBlurDrawable = new BlurDrawable();
//        View blur = findViewById(R.id.blur_rect_view);
//        blur.setBackground(mBlurDrawable);

//        PeekAndPopHelper.enablePeekAndPop(v, new PeekAndPopHelper.PeekAndPopListener() {
//            @Override
//            public View peek(float x, float y, PeekAndPopHelper.PeekAndPopConfig config) {
//                if (v.getVisibility() != View.VISIBLE) {
//                    return null;
//                }
//                int pos = v.pointToPosition((int) x, (int) y);
//                pos -= v.getFirstVisiblePosition();
//                View child = v.getChildAt(pos);
//                child.getLocationInWindow(mLocation);
//                config.mSourceRect = new Rect(mLocation[0], mLocation[1], mLocation[0] + child
// .getWidth(), mLocation[1] + child.getHeight());
//
//                /*ImageView image = new ImageView(BlurDemo.this);
//                image.setImageDrawable(((ImageView)child.findViewById(android.R.id.icon))
// .getDrawable().getConstantState().newDrawable());
//                return image;*/
//
//                config.mPeekActivity = true;
//                Intent i = new Intent(BlurDemo.this, SecondActivity.class);
//                startActivity(i);
//                PeekAndPopHelper.prepareForActivityPeek(BlurDemo.this);
//                return null;
//            }
//
//            @Override
//            public void cancel() {
//                PeekAndPopHelper.cancelForActivityPeek();
//            }
//
//            @Override
//            public boolean pop(View peekView) {
//                /*((ViewGroup)v.getParent()).addView(peekView);
//                v.setVisibility(View.INVISIBLE);*/
//
//                /*PeekAndPopHelper.prepareForActivityPop(BlurDemo.this, peekView);
//                startActivity(new Intent(BlurDemo.this, SecondActivity.class));
//                overridePendingTransition(0, 0);
//                return true;*/
//
//                PeekAndPopHelper.popForActivityPeek();
//
//                return true;
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ab_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu1: {
                // 用动画的方式演示：可以通过setBlurLevel设置模糊的等级
                ObjectAnimator anim = ObjectAnimator.ofFloat(mBlurDrawable,
                        "blurLevel", BlurDrawable.DEFAULT_BLUR_LEVEL, 0.0f, BlurDrawable
                                .DEFAULT_BLUR_LEVEL);
                anim.setDuration(5000);
                anim.start();
            }
            break;
            case R.id.action_menu2: {
                // 用动画的方式演示：可以通过setAlpha设置模糊效果在原有图像上叠加时采用的透明度
                /*ObjectAnimator anim = ObjectAnimator.ofInt(mBlurDrawable, "alpha", 255, 0, 255);
                anim.setDuration(1000);*/
                ObjectAnimator anim = ObjectAnimator.ofFloat(mProgressBar,
                        "translationY", 0, 1000, 0);
                anim.setDuration(5000);
                anim.start();
            }
            break;
            case R.id.action_menu3: {
                // 可以通过setColorFilter设置在模糊效果基础上叠加的颜色效果
                // 默认颜色效果是BlurDrawable.DEFAULT_BLUR_COLOR和BlurDrawable.DEFAULT_BLUR_COLOR_MODE
                int r = (int) (Math.random() * 255);
                int g = (int) (Math.random() * 255);
                int b = (int) (Math.random() * 255);
                mBlurDrawable.setColorFilter(Color.argb(0x88, r, g, b), BlurDrawable
                        .DEFAULT_BLUR_COLOR_MODE);
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private int getActionBarHeight() {
        // 推荐基于SDK开发者使用此方法获得ActionBar高度，但要避免频繁调用
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(
                    tv.data, getResources().getDisplayMetrics());
        }
        return 120;
    }

    private int getStatusBarHeight() {
        // 推荐基于SDK开发者使用此方法获得StatusBar高度，但要避免频繁调用
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int height = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            Log.e("BlurDemo", "get status bar height fail", e);
        }
        return 62;
    }
}
