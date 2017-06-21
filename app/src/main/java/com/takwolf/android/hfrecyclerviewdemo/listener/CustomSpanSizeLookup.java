package com.takwolf.android.hfrecyclerviewdemo.listener;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

import com.takwolf.android.hfrecyclerview.FixedViewHolder;
import com.takwolf.android.hfrecyclerview.ProxyAdapter;
import com.takwolf.android.hfrecyclerviewdemo.adapter.GridVerticalAdapter;

public final class CustomSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private final GridLayoutManager gridLayoutManager;
    private final ProxyAdapter proxyAdapter;

    public CustomSpanSizeLookup(@NonNull GridLayoutManager gridLayoutManager, @NonNull ProxyAdapter proxyAdapter) {
        this.gridLayoutManager = gridLayoutManager;
        this.proxyAdapter = proxyAdapter;
    }

    @Override
    public int getSpanSize(int position) {
        int viewType = proxyAdapter.getItemViewType(position);
        if (viewType == FixedViewHolder.VIEW_TYPE_HEADER || viewType == FixedViewHolder.VIEW_TYPE_FOOTER) {
            return gridLayoutManager.getSpanCount();
        } else if (viewType == GridVerticalAdapter.TYPE_FULL_SPAN) {
            return gridLayoutManager.getSpanCount();
        } else {
            return 1;
        }
    }

}
