package com.takwolf.android.hfrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

final class FixedViewSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private GridLayoutManager gridLayoutManager;
    private ProxyAdapter proxyAdapter;

    void setTargets(@NonNull GridLayoutManager gridLayoutManager, @NonNull ProxyAdapter proxyAdapter) {
        this.gridLayoutManager = gridLayoutManager;
        this.proxyAdapter = proxyAdapter;
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
