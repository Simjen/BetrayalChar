package com.betrayal.betrayalchar;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;

import java.io.Console;

/**
 *
 * The mCurrentStat wiev is for taking care of mCurrentStat things and showing the stats
 */
public class StatView extends View {
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
    private Stat mPlayerStat;
	private Stat mCurrentStat;
    private Stat mStatBelow;
    private Stat mStatAbove;


	@Override
	public boolean isContextClickable() {
		return true;
	}

	public class StatContextInfo implements ContextMenu.ContextMenuInfo{
    	public int id;
    	StatContextInfo(View StatContext){
			this.id = StatContext.getId();
    	}
	}

	@Override
	protected ContextMenu.ContextMenuInfo getContextMenuInfo() {
		return new StatContextInfo(this);
	}

	public StatView(Context context) {
		super(context);
		initialize();
	}

	public StatView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	public StatView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
	}
	
	public void setCurrentStat(Stat s){
		mPlayerStat = s;
        mCurrentStat = s.nextStat().prevStat();
	}

	private void initialize()
	{
		mBGGrad = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {0xFF000000, 0xFFAAAAAA, 0xFF000000});

		mDigitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mDigitPaint.setColor(Color.WHITE);
		mDigitPaint.setTextAlign(Align.CENTER);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		mBGGrad.draw(canvas);
		if(mDigitString != null) {
			canvas.drawText(mDigitString, mDigitX, mDigitY, mDigitPaint);

			canvas.drawText(mDigitAboveString, mDigitX, mDigitAboveY, mDigitPaint);
			canvas.drawText(mDigitBelowString, mDigitX, mDigitBelowY, mDigitPaint);
		}
		else{
			canvas.drawText("0",mDigitX,mDigitY,mDigitPaint);
		}
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

		return (float) (mHeight - ((mHeight - textHeight) / 2));
	}


	public void setCurrentDigit(Stat stat)
	{
		// Basic range limiting

		//animate change
        mCurrentStat = stat;
        mCurrentDigit = stat.getStatValue();

		// Digit above - greater
        mStatAbove = stat.nextStat();
		mDigitAbove = mStatAbove.getStatValue();


		// digit below - lower
        mStatBelow = stat.prevStat();
		mDigitBelow = mStatBelow.getStatValue();

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

					setCurrentDigit(mStatBelow);
					mTouchStartY -= mHeight;

					mDigitY -= postDelta;
					mDigitBelowY -= postDelta;
					mDigitAboveY -= postDelta;
				}
				else
				{
					// go UP a number

					setCurrentDigit(mStatAbove);
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
			

			if(Math.abs(deltaY) > (mHeight / 3) )
			{
				// higher numbers are 'above' the current, so a scroll down 
				// _increases_ the value
				if(deltaY < 0)
				{
                    mCurrentStat = mStatAbove;
					mPlayerStat.setStatIndex(mCurrentStat.getStatIndex());
				}
				else
				{
                    mCurrentStat = mStatBelow;
					mPlayerStat.setStatIndex(mCurrentStat.getStatIndex());
				}
			}
			mPlayerStat.setStatIndex(mCurrentStat.getStatIndex());
			setCurrentDigit(mCurrentStat);

			return true;
		}
		return true;
	}

}
