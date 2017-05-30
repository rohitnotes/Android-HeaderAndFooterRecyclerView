package com.takwolf.android.hfrecyclerviewdemo.viewholder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;

import butterknife.ButterKnife;

public class VerticalHeader {

    private final Activity activity;

    public VerticalHeader(@NonNull Activity activity, @NonNull HeaderAndFooterRecyclerView recyclerView) {
        this.activity = activity;
        View headerView = LayoutInflater.from(activity).inflate(R.layout.header_vertical, recyclerView.getHeaderParent(), false);
        recyclerView.addHeaderView(headerView);
        ButterKnife.bind(this, headerView);
    }

}
