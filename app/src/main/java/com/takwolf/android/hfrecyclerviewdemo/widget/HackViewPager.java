package com.takwolf.android.hfrecyclerviewdemo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

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
