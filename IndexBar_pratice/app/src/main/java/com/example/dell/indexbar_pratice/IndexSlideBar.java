package com.example.dell.indexbar_pratice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by DELL on 2017/7/13.
 */

public class IndexSlideBar extends View {
    //Paint i dont know what is that
    private Paint textPaint = new Paint();
    private Canvas canvas;
    private int width, height;
    private int item_height;
    //选中
    private int choose = -1;
    private TextView TextDialog;
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;


    private String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    public IndexSlideBar(Context context){
        super(context);
    }
    public IndexSlideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public IndexSlideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //set textView
    public void setTextView(TextView textDialog) {
        this.TextDialog = textDialog;
    }
    //rewrite onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height = getHeight();
        width = getWidth();
        this.item_height = (height/26)-2;
        for (int i = 0; i < 26; i ++) {
            textPaint.setColor(Color.rgb(61,61,61));
            textPaint.setTypeface(Typeface.DEFAULT_BOLD);  //设置字体
            textPaint.setAntiAlias(true);  //设置抗锯齿
            textPaint.setTextSize(30);
            if (i == choose) {
                textPaint.setColor(Color.rgb(00,00,00));
                textPaint.setTextSize(50);
                textPaint.setFakeBoldText(true);
            }
            float xPos = width/2 -textPaint.measureText(letters[i]);
            float yPos = i * item_height + item_height;
            canvas.drawText(letters[i], xPos, yPos, textPaint);  //绘制所有的字母
            textPaint.reset();// 重置画笔
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float pos_y = event.getY();
        final int old_choose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        //
        final int c = (int) (pos_y / getHeight() * letters.length);
        // c:   点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
        switch (action) {
            case MotionEvent.ACTION_UP:
                //setBackgroundDrawable(new ColorDrawable(0x00000000));
                choose = -1;//
                invalidate();
                if (TextDialog != null) {
                    TextDialog.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                //setBackgroundResource(R.drawable.);
                if (old_choose != c) {  //判断选中字母是否发生改变
                    if (c >= 0 && c < letters.length) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(letters[c]);
                        }
                        if (TextDialog != null) {
                            TextDialog.setText(letters[c]);
                            TextDialog.setVisibility(View.VISIBLE);
                        }

                        choose = c;
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }

    /**
     * 向外公开的方法
     */
    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }
    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s);
    }
}
