package com.example.g_ravel_Animation;

import android.app.ActionBar.LayoutParams;
import android.view.Gravity;
import android.view.animation.*;
import android.widget.LinearLayout;

public class OpenAnimation extends TranslateAnimation implements
		Animation.AnimationListener {

	private LinearLayout mainLayout;
	int panelWidth;

	public OpenAnimation(LinearLayout layout, int width, int fromXType,
			float fromXValue, int toXType, float toXValue, int fromYType,
			float fromYValue, int toYType, float toYValue) {
		super(fromXType, fromXValue, toXType, toXValue, fromYType, fromYValue,
				toYType, toYValue);
		// TODO Auto-generated constructor stub

		// init
		mainLayout = layout;
		panelWidth = width;
		setDuration(250);
		setFillAfter(false);
		setInterpolator(new AccelerateDecelerateInterpolator());
		setAnimationListener(this);
		mainLayout.startAnimation(this);
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		
		LayoutParams params = (LayoutParams)mainLayout.getLayoutParams();
		params.leftMargin = panelWidth;
		params.gravity = Gravity.LEFT;
		mainLayout.clearAnimation();
		mainLayout.setLayoutParams(params);
		mainLayout.requestLayout();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

}
