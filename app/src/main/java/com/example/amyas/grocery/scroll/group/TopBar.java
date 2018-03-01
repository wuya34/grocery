package com.example.amyas.grocery.scroll.group;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.amyas.grocery.R;


/**
 * author: Amyas
 * date: 2017/12/7
 */

public class TopBar extends RelativeLayout {
    //widget attrs
    private int mLeftTextColor;
    private int mRightTextColor;
    private int mTitleTextColor;
    private String title;
    private String leftText;
    private String rightText;
    private float mTitleTextSize;
    private Drawable mLeftTextBackground;
    private Drawable mRightTextBackground;
    //widget
    private Button leftButton;
    private Button rightButton;
    private TextView mTitleView;
    //widget layoutParams
    private LayoutParams mLeftButtonLayoutParams;
    private LayoutParams mRightButtonLayoutParams;
    private LayoutParams mTitleLayoutParams;
    //listener 监听
    private topBarClickListener mListener;


    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        leftButton = new Button(context);
        rightButton = new Button(context);
        mTitleView = new Button(context);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor,0);
        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor,0);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor,0);
        title = ta.getString(R.styleable.TopBar_title);
        leftText = ta.getString(R.styleable.TopBar_leftText);
        rightText = ta.getString(R.styleable.TopBar_rightText);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 10);
        mLeftTextBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mRightTextBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        ta.recycle();

        configWidget();
    }

    private void configWidget() {
        // set attrs
        leftButton.setTextColor(mLeftTextColor);
        leftButton.setText(leftText);
        leftButton.setBackground(mLeftTextBackground);

        rightButton.setTextColor(mRightTextColor);
        rightButton.setText(rightText);
        rightButton.setBackground(mRightTextBackground);

        mTitleView.setText(title);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        //set layoutParams
        mLeftButtonLayoutParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mLeftButtonLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(leftButton, mLeftButtonLayoutParams);

        mRightButtonLayoutParams= new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mRightButtonLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(rightButton, mRightButtonLayoutParams);

        mTitleLayoutParams= new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mTitleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mTitleView, mTitleLayoutParams);
        // button listener config
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });
        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });

    }

    public TopBar(Context context) {
        super(context);
    }

    public interface topBarClickListener {
        void leftClick();
        void rightClick();
    }
    public void setOnTopBarClickLIstener(topBarClickListener listener){
        mListener = listener;
    }
}
