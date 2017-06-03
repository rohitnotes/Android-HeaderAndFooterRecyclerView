package com.takwolf.android.hfrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public final class FixedViewHolder extends RecyclerView.ViewHolder {

    public static final int TYPE_HEADER = -1;
    public static final int TYPE_FOOTER = -2;

    FixedViewHolder(View itemView) {
        super(itemView);
    }

}
