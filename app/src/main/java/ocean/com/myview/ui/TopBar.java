package ocean.com.myview.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import ocean.com.myview.R;

/**
 * Created by lenovo on 2018/5/15.
 */

public class TopBar extends RelativeLayout {


    Button mLeftButton;
    Button mRightButton;
    MyTextView2 mTitleView;

    int mLeftTextColor;
    Drawable mLeftBackground;
    String mLeftText;
    int mRightTextColor;
    Drawable mRightBackground;
    String mRightText;
    int mTitleTextColor;
    Float mTitleTextSize;
    String mTitleText;
    //设置布局元素
    LayoutParams mLeftParams;
    LayoutParams mRightParams;
    LayoutParams mTitleParams;

    topbarClickListener listener;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        //从TypedArray中取出相应的值来为将要设置的的属性赋值
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor,0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);

        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor,0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);

        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor,0);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize,0);
        mTitleText = ta.getString(R.styleable.TopBar_title);

        ta.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new MyTextView2(context);

        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setText(mTitleText);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(mLeftButton,mLeftParams);

        mRightParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(mRightButton,mRightParams);

        mTitleParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mTitleView,mTitleParams);

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }









    //暴露一个方法给调用者来注册接口回调
    //通过接口来获取回调者对接口方法的实现
    public void setOnTobarClickListener(topbarClickListener listener){
        this.listener = listener;
    }


    public interface topbarClickListener{
        void leftClick();
        Void rightClick();
    }

    /*设置按钮的的显示与是否通过ID区分按钮，flag区分是否显示*/

    public void setButtonVisable(int id,boolean flag){
        if(flag){
            if(id == 0){
                mLeftButton.setVisibility(View.VISIBLE);
            }else{
                mRightButton.setVisibility(View.VISIBLE);
            }
        }else{
            if(id == 0){
                mLeftButton.setVisibility(View.GONE);
            }else{
                mRightButton.setVisibility(View.GONE);
            }
        }
    }
}
