package com.takwolf.android.hfrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

final class FixedViewSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private ProxyAdapter proxyAdapter;
    private GridLayoutManager gridLayoutManager;

    void setTargets(@NonNull ProxyAdapter proxyAdapter, @NonNull GridLayoutManager gridLayoutManager) {
        this.proxyAdapter = proxyAdapter;
        this.gridLayoutManager = gridLayoutManager;
    }

    @Override
    public int getSpanSize(int position) {
        int viewType = proxyAdapter.getItemViewType(position);
        if (viewType == FixedViewHolder.TYPE_HEADER || viewType == FixedViewHolder.TYPE_FOOTER) {
            return gridLayoutManager.getSpanCount();
        } else {
            return 1;
        }
    }

}
