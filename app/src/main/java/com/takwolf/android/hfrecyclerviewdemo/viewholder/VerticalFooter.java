package com.takwolf.android.hfrecyclerviewdemo.viewholder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;

import butterknife.ButterKnife;

public class VerticalFooter {

    private final Activity activity;

    public VerticalFooter(@NonNull Activity activity, @NonNull HeaderAndFooterRecyclerView recyclerView) {
        this.activity = activity;
        View footerView = LayoutInflater.from(activity).inflate(R.layout.footer_vertical, recyclerView.getFooterParent(), false);
        recyclerView.addFooterView(footerView);
        ButterKnife.bind(this, footerView);
    }

}
