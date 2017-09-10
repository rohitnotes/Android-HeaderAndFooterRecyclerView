package com.takwolf.android.hfrecyclerviewdemo.viewholder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.util.RandomUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;

public class VerticalHeader {

    @BindView(R.id.tv_name)
    TextView tvName;

    private final HeaderAndFooterRecyclerView recyclerView;
    private final View headerView;

    public VerticalHeader(@NonNull Activity activity, @NonNull HeaderAndFooterRecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        headerView = LayoutInflater.from(activity).inflate(R.layout.header_vertical, recyclerView.getHeaderContainer(), false);
        recyclerView.addHeaderView(headerView);
        ButterKnife.bind(this, headerView);
        tvName.setBackgroundColor(RandomUtils.randomColor());
    }

    @OnLongClick(R.id.btn_item)
    boolean onBtnItemLongClick() {
        recyclerView.removeHeaderView(headerView);
        return true;
    }

}
