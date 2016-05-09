package com.huawaii.objectanimator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

//Animator -> ValueAnimator -> ObjectAnimator
public class MainActivity extends Activity {

    private ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点不到我", 0).show();

            }
        });
    }


    public void translate(View v) {
//        TranslateAnimation ta = new TranslateAnimation(0, 150, 0, 0);
//        ta.setDuration(2000);
//        ta.setFillAfter(true);
//        iv.startAnimation(ta);

        //target:动画作用于哪个组件
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "translationX", 10, 70, 20, 100);
        oa.setDuration(2000);
        oa.setRepeatCount(1);
        oa.setRepeatMode(ValueAnimator.REVERSE);
        oa.start();
    }

    public void scale(View v) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "scaleX", 1, 1.6f, 1.2f, 2);
        oa.setDuration(2000);
        oa.start();
    }

    public void alpha(View v) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "alpha", 0, 0.6f, 0.2f, 1);
        oa.setDuration(2000);
        oa.start();
    }

    public void rotate(View v) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "rotationY", 0, 180, 90, 360);
        oa.setDuration(2000);
        oa.setRepeatCount(1);
        oa.setRepeatMode(ValueAnimator.REVERSE);
        oa.start();
    }

    public void fly(View v) {
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator oa1 = ObjectAnimator.ofFloat(iv, "translationX", 10, 70, 20, 100);
        oa1.setDuration(2000);
        oa1.setRepeatCount(1);
        oa1.setRepeatMode(ValueAnimator.REVERSE);

        ObjectAnimator oa2 = ObjectAnimator.ofFloat(iv, "translationY", 10, 70, 20, 100);
        oa2.setDuration(2000);
        oa2.setRepeatCount(1);
        oa2.setRepeatMode(ValueAnimator.REVERSE);

        ObjectAnimator oa3 = ObjectAnimator.ofFloat(iv, "scaleX", 1, 1.6f, 1.2f, 2);
        oa3.setDuration(2000);
        oa3.setRepeatCount(1);
        oa3.setRepeatMode(ValueAnimator.REVERSE);

        ObjectAnimator oa4 = ObjectAnimator.ofFloat(iv, "rotation", 0, 180, 90, 360);
        oa4.setDuration(2000);
        oa4.setRepeatCount(1);
        oa4.setRepeatMode(ValueAnimator.REVERSE);

//        PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX", 10, 70, 20, 100);
//        PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationY", 10, 70, 20, 100);
//        PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("scaleX", 1, 1.6f, 1.2f, 2);
//        PropertyValuesHolder p4 = PropertyValuesHolder.ofFloat("rotation", 0, 180, 90, 360);
//        ObjectAnimator.ofPropertyValuesHolder(iv, p1, p2, p3, p4).setDuration(2000).start();
// 多动画时效率更高

        //set.playSequentially(oa1, oa2, oa3, oa4); //设置挨个飞
        set.playTogether(oa1, oa2, oa3, oa4); //设置一起飞
        set.start();
    }

    public void xml(View v) {
        Animator at = AnimatorInflater.loadAnimator(this, R.animator.objanimator);
        at.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Toast.makeText(MainActivity.this, "xml Object Animator", Toast.LENGTH_SHORT).show();
            }
        });
        at.setInterpolator(new LinearInterpolator());
        //设置作用于哪个组件
        at.setTarget(iv);
        at.start();
    }

    public void valueAnimatorDemo(final View v) {
        final Button button = (Button) v;

//        ValueAnimator valueAnimator = ValueAnimator.ofObject(new TypeEvaluator<PointF>() {
//            @Override
//            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
//                return null;
//            }
//        });   //返回值更加灵活
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setText("" + value);
            }
        });
        valueAnimator.start();
    }

}
