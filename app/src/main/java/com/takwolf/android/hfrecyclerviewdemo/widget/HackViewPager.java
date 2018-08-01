package com.takwolf.android.hfrecyclerviewdemo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class HackViewPager extends ViewPager {

    public HackViewPager(@NonNull Context context) {
        super(context);
    }

    public HackViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onAttachedToWindow() {}

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onDetachedFromWindow() {}

}
