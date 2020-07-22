package com.takwolf.android.hfrecyclerviewdemo.holder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.adapter.NumberPager2Adapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPager2Header {

    @BindView(R.id.view_pager)
    ViewPager2 viewPager;

    public ViewPager2Header(@NonNull Activity activity, @NonNull HeaderAndFooterRecyclerView recyclerView, @NonNull ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        View headerView = LayoutInflater.from(activity).inflate(R.layout.header_view_pager_2, recyclerView.getHeaderContainer(), false);
        recyclerView.addHeaderView(headerView);
        ButterKnife.bind(this, headerView);

        NumberPager2Adapter adapter = new NumberPager2Adapter(activity, 10);
        viewPager.setAdapter(adapter);
        viewPager.registerOnPageChangeCallback(onPageChangeCallback);
    }

}
