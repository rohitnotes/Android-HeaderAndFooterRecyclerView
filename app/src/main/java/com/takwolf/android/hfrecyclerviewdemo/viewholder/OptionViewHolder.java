package com.takwolf.android.hfrecyclerviewdemo.viewholder;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OptionViewHolder {

    public static final int ORIENTATION_VERTICAL = 0;
    public static final int ORIENTATION_HORIZONTAL = 1;

    private final Activity activity;
    private final HeaderAndFooterRecyclerView recyclerView;
    private final int orientation;

    public OptionViewHolder(@NonNull Activity activity, @NonNull HeaderAndFooterRecyclerView recyclerView, int orientation) {
        this.activity = activity;
        this.recyclerView = recyclerView;
        this.orientation = orientation;
        ButterKnife.bind(this, activity.findViewById(R.id.layout_controller));
    }

    @OnClick(R.id.btn_add_header)
    void onBtnAddHeaderClick() {
        if (orientation == ORIENTATION_VERTICAL) {
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
        if (orientation == ORIENTATION_VERTICAL) {
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
