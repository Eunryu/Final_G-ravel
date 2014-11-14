package com.example.g_ravel_Animation;

import android.app.ActionBar.LayoutParams;
import android.view.animation.*;
import android.widget.LinearLayout;

public class CloseAnimation extends TranslateAnimation implements Animation.AnimationListener{

	private LinearLayout mainLayout;
	int panelWidth;
	
	public CloseAnimation(LinearLayout layout, int width, int fromXType, float fromXValue, int toXType,
			float toXValue, int fromYType, float fromYValue, int toYType,
			float toYValue) {
		super(fromXType, fromXValue, toXType, toXValue, fromYType, fromYValue, toYType,
				toYValue);
		// TODO Auto-generated constructor stub
		
		//Initialize
		mainLayout = layout;
		panelWidth = width;
		setDuration(250);
		setFillAfter(false);
		setInterpolator(new AccelerateDecelerateInterpolator());
		setAnimationListener(this);
		
		//Clear left and right margins
		LayoutParams params = (LayoutParams)mainLayout.getLayoutParams();
		params.rightMargin = 0;
		params.leftMargin = 0;
		mainLayout.setLayoutParams(params);
		mainLayout.requestLayout();
		mainLayout.startAnimation(this);
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

}
