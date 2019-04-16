package com.takwolf.android.hfrecyclerviewdemo.holder;

import android.app.Activity;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OptionViewHolder {

    private final Activity activity;
    private final HeaderAndFooterRecyclerView recyclerView;
    @RecyclerView.Orientation private final int orientation;

    public OptionViewHolder(@NonNull Activity activity, @NonNull HeaderAndFooterRecyclerView recyclerView, @RecyclerView.Orientation int orientation) {
        this.activity = activity;
        this.recyclerView = recyclerView;
        this.orientation = orientation;
        ButterKnife.bind(this, activity.findViewById(R.id.layout_controller));
    }

    @OnClick(R.id.btn_add_header)
    void onBtnAddHeaderClick() {
        if (orientation == RecyclerView.VERTICAL) {
            new VerticalHeader(activity, recyclerView);
        } else {
            new HorizontalHeader(activity, recyclerView);
        }
    }

    @OnClick(R.id.btn_remove_header)
    void onBtnRemoveHeader() {
        if (recyclerView.getHeaderViewCount() > 0) {
            recyclerView.removeHeaderView(recyclerView.getHeaderViewCount() - 1);
        }
    }

    @OnClick(R.id.btn_add_footer)
    void onBtnAddFooterClick() {
        if (orientation == RecyclerView.VERTICAL) {
            new VerticalFooter(activity, recyclerView);
        } else {
            new HorizontalFooter(activity, recyclerView);
        }
    }

    @OnClick(R.id.btn_remove_footer)
    void onBtnRemoveFooterClick() {
        if (recyclerView.getFooterViewCount() > 0) {
            recyclerView.removeFooterView(recyclerView.getFooterViewCount() - 1);
        }
    }

}
