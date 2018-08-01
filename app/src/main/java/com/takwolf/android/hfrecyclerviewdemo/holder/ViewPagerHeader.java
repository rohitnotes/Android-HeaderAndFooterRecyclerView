package com.takwolf.android.hfrecyclerviewdemo.holder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.adapter.NumberPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerHeader {

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    public ViewPagerHeader(@NonNull Activity activity, @NonNull HeaderAndFooterRecyclerView recyclerView, @NonNull ViewPager.OnPageChangeListener onPageChangeListener) {
        View headerView = LayoutInflater.from(activity).inflate(R.layout.header_view_pager, recyclerView.getHeaderContainer(), false);
        recyclerView.addHeaderView(headerView);
        ButterKnife.bind(this, headerView);

        NumberPagerAdapter adapter = new NumberPagerAdapter(activity, 10);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

}
