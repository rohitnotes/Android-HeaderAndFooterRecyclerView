package com.takwolf.android.hfrecyclerviewdemo.holder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.util.RandomUtils;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;

public class VerticalFooter {

    @BindView(R.id.tv_name)
    TextView tvName;

    private final HeaderAndFooterRecyclerView recyclerView;
    private final View footerView;

    public VerticalFooter(@NonNull Activity activity, @NonNull HeaderAndFooterRecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        footerView = LayoutInflater.from(activity).inflate(R.layout.footer_vertical, recyclerView.getFooterContainer(), false);
        recyclerView.addFooterView(footerView);
        ButterKnife.bind(this, footerView);
        tvName.setBackgroundColor(RandomUtils.randomColor());
    }

    @OnLongClick(R.id.btn_item)
    boolean onBtnItemLongClick() {
        recyclerView.removeFooterView(footerView);
        return true;
    }

}
