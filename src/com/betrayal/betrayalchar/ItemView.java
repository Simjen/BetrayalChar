package com.betrayal.betrayalchar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Baljenurface on 20-01-2018.
 */

public class ItemView extends LinearLayout {

    private ImageView imageView;
    private TextView textView;

    private void initialize(Context context){
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setGravity(Gravity.CENTER);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int densityDpi = displayMetrics.densityDpi;
        imageView.setMaxWidth(100 * (densityDpi / 160));
        imageView.setMaxHeight(100 * (densityDpi / 160));
        imageView.setAdjustViewBounds(true);
        addView(imageView);
        textView = new TextView(context);
        textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setSingleLine(false);
        textView.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
    }
    public ItemView(Context context) {
        super(context);
        initialize(context);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    public void setImage(Drawable drawable){
        imageView.setImageDrawable(drawable);
    }

    public void setText(@StringRes int resId){
        textView.setText(resId);
    }
}
