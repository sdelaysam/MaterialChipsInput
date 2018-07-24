package com.pchmn.materialchips.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;


public abstract class DetailedChipView extends RelativeLayout {

    private static final String TAG = DetailedChipView.class.toString();

    public DetailedChipView(Context context) {
        super(context);
    }

    public DetailedChipView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract ViewGroup getContentLayout();

    public abstract void setOnDeleteClicked(OnClickListener onClickListener);

    /**
     * Inflate the view according to attributes
     *
     * @param attrs the attributes
     */
    protected void init(AttributeSet attrs) {
        // hide on first
        setVisibility(GONE);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setClickable(true);
        setBackgroundColor(0x01000000);
    }

    /**
     * Fade in
     */
    public void fadeIn() {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(200);
        startAnimation(anim);
        setVisibility(VISIBLE);
        // focus on the view
        requestFocus();
    }

    /**
     * Fade out
     */
    public void fadeOut() {
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(200);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (getParent() instanceof ViewGroup) {
                    ((ViewGroup) getParent()).removeView(DetailedChipView.this);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        startAnimation(anim);
        setVisibility(GONE);
        // fix onclick issue
        clearFocus();
    }

    public void alignLeft() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getContentLayout().getLayoutParams();
        params.leftMargin = 0;
        getContentLayout().setLayoutParams(params);
    }

    public void alignRight() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getContentLayout().getLayoutParams();
        params.rightMargin = 0;
        getContentLayout().setLayoutParams(params);
    }

}
