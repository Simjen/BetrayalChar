package com.betrayal.betrayalchar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class ScrollingCounterView extends View {
	private float mTouchStartY;
	private float mTouchLastY;
	private float mDigitY;
	private float mDigitAboveY;
	private float mDigitBelowY;
	private int mCurrentDigit;
	private int mHeight;
	private int mDigitAbove;
	private int mDigitBelow;
	private GradientDrawable mBGGrad;
	private String mDigitString;
	private float mDigitX;
	private Paint mDigitPaint;
	private String mDigitAboveString;
	private String mDigitBelowString;
	private int mWidth;
	private MainPlayer mainPlayer;
	private String statName = "";

	public ScrollingCounterView(Context context) {
		super(context);
		initialize();
	}

	public ScrollingCounterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	public ScrollingCounterView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
	}
	
	public void setStat(String s){
		statName = s;
	}

	private void initialize()
	{
		mainPlayer = (MainPlayer) getContext();
		mBGGrad = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {0xFF000000, 0xFFAAAAAA, 0xFF000000});

		mDigitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mDigitPaint.setColor(Color.WHITE);
		mDigitPaint.setTextAlign(Align.CENTER);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		mBGGrad.draw(canvas);

		canvas.drawText(mDigitString, mDigitX, mDigitY, mDigitPaint);

		canvas.drawText(mDigitAboveString, mDigitX, mDigitAboveY, mDigitPaint);
		canvas.drawText(mDigitBelowString, mDigitX, mDigitBelowY, mDigitPaint);
	}


	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);

		mWidth = w;
		mHeight = h;

		mBGGrad.setBounds(0, 0, w, h);

		mDigitPaint.setTextSize(h);

		mDigitX = mWidth / 2;

		setDigitYValues();
	}


	private void setDigitYValues()
	{
		mDigitY = findCenterY(mCurrentDigit);
		mDigitAboveY = findCenterY(mDigitAbove) - mHeight;
		mDigitBelowY = mHeight + findCenterY(mDigitBelow);
	}

	private float findCenterY(int digit)
	{
		String text = String.valueOf(digit);
		Rect bounds = new Rect();
		mDigitPaint.getTextBounds(text, 0, text.length(), bounds);

		int textHeight = Math.abs(bounds.height());

		float result = mHeight - ((mHeight - textHeight) / 2);

		return result;
	}

	public void setCurrentDigit(int digit)
	{
		// Basic range limiting
		int newVal = digit;

		mCurrentDigit = newVal;

		// Digit above - greater
		mDigitAbove = digit + 1;

		if(mDigitAbove > 8)
			mDigitAbove = 8;

		// digit below - lower
		mDigitBelow = digit -1;

		if(mDigitBelow < 0)
			mDigitBelow = 0;

		mDigitString = String.valueOf(mCurrentDigit);
		mDigitAboveString = String.valueOf(mDigitAbove);
		mDigitBelowString = String.valueOf(mDigitBelow);

		setDigitYValues();
		invalidate();
	}



	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// Pull out the Action value from the event for processing
		int action = event.getAction();
		
		if(action == MotionEvent.ACTION_DOWN)
		{
			mTouchStartY = event.getY();
			mTouchLastY = mTouchStartY;
			
			return true;
		}
		else if(action == MotionEvent.ACTION_MOVE)
		{                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
			float currentY = event.getY();
			
			float delta = mTouchLastY - currentY;
			mTouchLastY = currentY;
			
			mDigitY -= delta;
			mDigitAboveY -= delta;
			mDigitBelowY -= delta;
			
			// calculate the overall delta (beginning to now)
			float totalDelta = mTouchStartY - currentY;
			
			// If we have scrolled an entire number, change numbers while 
			// keeping the scroll
			if(Math.abs(totalDelta) > mHeight )
			{
				// need to either increase or decrease value
				float postDelta = Math.abs(totalDelta) - mHeight;

				if(totalDelta > 0)
				{
					// go DOWN a number
					setCurrentDigit(mDigitBelow);
					mTouchStartY -= mHeight;

					mDigitY -= postDelta;
					mDigitBelowY -= postDelta;
					mDigitAboveY -= postDelta;
				}
				else
				{
					// go UP a number
					setCurrentDigit(mDigitAbove);
					mTouchStartY += mHeight;

					mDigitY += postDelta;
					mDigitBelowY += postDelta;
					mDigitAboveY += postDelta;
				}
			}
			
			invalidate();
			
			return true;
		}
		else if(action == MotionEvent.ACTION_UP)
		{
			float currentY = event.getY();
			
			// delta: negative means a down 'scroll'
			float deltaY = mTouchStartY - currentY;
			
			int newValue = mCurrentDigit;
			
			if(Math.abs(deltaY) > (mHeight / 3) )
			{
				// higher numbers are 'above' the current, so a scroll down 
				// _increases_ the value
				if(deltaY < 0)
				{
					newValue ++;
				}
				else
				{
					newValue --;
				}
			}
			
			setCurrentDigit(newValue);
			
			return true;
		}
		return false;
	}

}
