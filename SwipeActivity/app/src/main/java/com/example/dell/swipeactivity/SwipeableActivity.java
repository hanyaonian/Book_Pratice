package com.example.dell.swipeactivity;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by DELL on 2017/7/26.
 * 学习自 http://blog.csdn.net/goodlixueyong/article/details/51142797
 */

public class SwipeableActivity extends AppCompatActivity {
    private int screen_Width; //屏幕宽度
    private int minimumSwipe; //最小滑动距离
    private boolean is_moving; // 有无滑动

    private float initX, initY;

    ValueAnimator mAnimator; //属性动画

    private ViewGroup decorView; //整个页面根布局
    private ViewGroup contentView; //页面内容布局
    private ViewGroup userView; //页面用户内容布局

    private VelocityTracker mVelTracker;//计算速度

    @Override
    protected void onStart() {
        super.onStart();
        initSwipeableActivity();
    }

    public void initSwipeableActivity() {
        screen_Width = getResources().getDisplayMetrics().widthPixels;
        minimumSwipe = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();

        decorView = (ViewGroup) getWindow().getDecorView();
        decorView.setBackgroundColor(Color.parseColor("#ffffff"));

        contentView = (ViewGroup) findViewById(android.R.id.content);
        userView = (ViewGroup)contentView.getChildAt(0); //用户内容布局

        mAnimator = new ValueAnimator();
        mAnimator.setDuration(300);
        mAnimator.setInterpolator(new DecelerateInterpolator());//http://blog.csdn.net/jason0539/article/details/16370405
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int x = (int) mAnimator.getAnimatedValue();
                if (x >= screen_Width) {
                    //滑完，结束
                    SwipeableActivity.this.finish();
                }
                handleView(x);
            }
        });
    }
    public void handleView(int x) {
        decorView.setTranslationX(x);
    }
    public boolean processEvent(MotionEvent event) {
        getVelocityTracker(event);

        if (mAnimator.isRunning() == true) return true;
        int PointId = -1;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initX = event.getRawX(); //落点x y
                initY = event.getRawY();
                PointId = event.getPointerId(0);
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                int moved_distance = (int)(event.getRawX() - initX);
                mVelTracker.computeCurrentVelocity(1000);
                //获取x方向上的速度
                float velocityX = mVelTracker.getXVelocity(PointId);
                if (is_moving && Math.abs(userView.getTranslationX()) >= 0) {
                    if (velocityX > 1000f || moved_distance >=  screen_Width / 4) {
                        mAnimator.setIntValues((int) event.getRawX(), screen_Width);
                    } else {
                        mAnimator.setIntValues((int) event.getRawX(), 0);
                    }
                    mAnimator.start();
                    is_moving = false;
                }
                cleanVelocityTracker();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!is_moving) {
                    float dx = Math.abs(event.getRawX() - initX);
                    float dy = Math.abs(event.getRawY() - initY);
                    if (dx > minimumSwipe && dx > dy && initX < 60) {  //默认开始滑动的位置距离左边缘的距离
                        is_moving = true;
                    }
                } else {
                    handleView((int)event.getRawX());
                }
                break;

        }
        return true;
    }

    //速度追踪器
    private VelocityTracker getVelocityTracker(MotionEvent event) {
        if (mVelTracker == null) {
            mVelTracker = VelocityTracker.obtain();
        }
        mVelTracker.addMovement(event);
        return mVelTracker;
    }
    //clean velocitytracker
    private void cleanVelocityTracker() {
        if (mVelTracker != null) {
            mVelTracker.clear();
            mVelTracker.recycle();
            mVelTracker = null;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (processEvent(event)) {
            return true;
        } else {
            return super.onTouchEvent(event);
        }
    }
}
