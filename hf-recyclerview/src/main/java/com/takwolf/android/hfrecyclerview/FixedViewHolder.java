package com.takwolf.android.hfrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public final class FixedViewHolder extends RecyclerView.ViewHolder {

    public static final int VIEW_TYPE_HEADER = Integer.MIN_VALUE;
    public static final int VIEW_TYPE_FOOTER = Integer.MIN_VALUE + 1;

    FixedViewHolder(View itemView) {
        super(itemView);
    }

}
