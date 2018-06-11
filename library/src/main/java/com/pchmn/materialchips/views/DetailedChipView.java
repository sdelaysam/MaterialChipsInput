package com.pchmn.materialchips.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;


public abstract class DetailedChipView extends RelativeLayout {

    private static final String TAG = DetailedChipView.class.toString();

    public DetailedChipView(Context context) {
        super(context);
    }

    public DetailedChipView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract RelativeLayout getContentLayout();

    public abstract void setOnDeleteClicked(OnClickListener onClickListener);

    /**
     * Inflate the view according to attributes
     *
     * @param attrs the attributes
     */
    protected void init(AttributeSet attrs) {
        // hide on first
        setVisibility(GONE);
        // hide on touch outside
        hideOnTouchOutside();
    }

    /**
     * Hide the view on touch outside of it
     */
    protected void hideOnTouchOutside() {
        // set focusable
        setFocusable(true);
        setFocusableInTouchMode(true);
        setClickable(true);
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
        startAnimation(anim);
        setVisibility(GONE);
        // fix onclick issue
        clearFocus();
        setClickable(false);
    }

    public void alignLeft() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getContentLayout().getLayoutParams();
        params.leftMargin = 0;
        getContentLayout().setLayoutParams(params);
    }

    public void alignRight() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getContentLayout().getLayoutParams();
        params.rightMargin = 0;
        getContentLayout().setLayoutParams(params);
    }

}
