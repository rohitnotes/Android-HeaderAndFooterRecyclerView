package com.takwolf.android.hfrecyclerviewdemo.viewholder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;

import butterknife.ButterKnife;
import butterknife.OnLongClick;

public class HorizontalHeader {

    private final Activity activity;
    private final HeaderAndFooterRecyclerView recyclerView;
    private final View headerView;

    public HorizontalHeader(@NonNull Activity activity, @NonNull HeaderAndFooterRecyclerView recyclerView) {
        this.activity = activity;
        this.recyclerView = recyclerView;
        headerView = LayoutInflater.from(activity).inflate(R.layout.header_horizontal, recyclerView.getHeaderContainer(), false);
        recyclerView.addHeaderView(headerView);
        ButterKnife.bind(this, headerView);
    }

    @OnLongClick(R.id.btn_item)
    boolean onBtnItemLongClick() {
        recyclerView.removeHeaderView(headerView);
        return true;
    }

}
