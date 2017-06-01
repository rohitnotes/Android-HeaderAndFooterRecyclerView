package com.takwolf.android.hfrecyclerviewdemo.viewholder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;

import butterknife.ButterKnife;
import butterknife.OnLongClick;

public class HorizontalFooter {

    private final Activity activity;
    private final HeaderAndFooterRecyclerView recyclerView;
    private final View footerView;

    public HorizontalFooter(@NonNull Activity activity, @NonNull HeaderAndFooterRecyclerView recyclerView) {
        this.activity = activity;
        this.recyclerView = recyclerView;
        footerView = LayoutInflater.from(activity).inflate(R.layout.footer_horizontal, recyclerView.getFooterParent(), false);
        recyclerView.addFooterView(footerView);
        ButterKnife.bind(this, footerView);
    }

    @OnLongClick(R.id.btn_item)
    protected boolean onBtnItemLongClick() {
        recyclerView.removeFooterView(footerView);
        return true;
    }

}
