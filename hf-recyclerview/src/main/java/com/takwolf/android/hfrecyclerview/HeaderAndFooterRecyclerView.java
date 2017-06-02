package com.takwolf.android.hfrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class HeaderAndFooterRecyclerView extends RecyclerView {

    private LinearLayout headerParent;
    private LinearLayout footerParent;

    private AdapterProxy adapterProxy;

    public HeaderAndFooterRecyclerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public HeaderAndFooterRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HeaderAndFooterRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(@NonNull Context context) {
        headerParent = new LinearLayout(context);
        footerParent = new LinearLayout(context);

        adapterProxy = new AdapterProxy(this);
        super.setAdapter(adapterProxy);
    }

    @NonNull
    public LinearLayout getHeaderParent() {
        return headerParent;
    }

    public int getHeaderViewCount() {
        return headerParent.getChildCount();
    }

    public void addHeaderView(@NonNull View view) {
        headerParent.addView(view);
        adapterProxy.notifyHeaderInserted();
    }

    public void addHeaderView(@NonNull View view, int index) {
        headerParent.addView(view, index);
        adapterProxy.notifyHeaderInserted();
    }

    public void removeHeaderView(@NonNull View view) {
        headerParent.removeView(view);
        adapterProxy.notifyHeaderRemoved();
    }

    public void removeHeaderView(int index) {
        headerParent.removeViewAt(index);
        adapterProxy.notifyHeaderRemoved();
    }

    @NonNull
    public LinearLayout getFooterParent() {
        return footerParent;
    }

    public int getFooterViewCount() {
        return footerParent.getChildCount();
    }

    public void addFooterView(@NonNull View view) {
        footerParent.addView(view);
        adapterProxy.notifyFooterInserted();
    }

    public void addFooterView(@NonNull View view, int index) {
        footerParent.addView(view, index);
        adapterProxy.notifyFooterInserted();
    }

    public void removeFooterView(@NonNull View view) {
        footerParent.removeView(view);
        adapterProxy.notifyFooterRemoved();
    }

    public void removeFooterView(int index) {
        footerParent.removeViewAt(index);
        adapterProxy.notifyFooterRemoved();
    }

    private void setHeaderOrFooterOrientation(@NonNull LinearLayout parent, int orientation, boolean isStaggeredGridLayout) {
        parent.setOrientation(orientation);
        if (isStaggeredGridLayout) {
            StaggeredGridLayoutManager.LayoutParams layoutParams;
            if (orientation == LinearLayout.VERTICAL) {
                layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            } else {
                layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            layoutParams.setFullSpan(true);
            parent.setLayoutParams(layoutParams);
        } else {
            LinearLayout.LayoutParams layoutParams;
            if (orientation == LinearLayout.VERTICAL) {
                layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            } else {
                layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            parent.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void setLayoutManager(LayoutManager layoutManager) {
        super.setLayoutManager(layoutManager);
        if (layoutManager != null) {
            // Orientation
            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                if (linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                    setHeaderOrFooterOrientation(headerParent, LinearLayout.VERTICAL, false);
                    setHeaderOrFooterOrientation(footerParent, LinearLayout.VERTICAL, false);
                } else if (linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                    setHeaderOrFooterOrientation(headerParent, LinearLayout.HORIZONTAL, false);
                    setHeaderOrFooterOrientation(footerParent, LinearLayout.HORIZONTAL, false);
                }
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (staggeredGridLayoutManager.getOrientation() == StaggeredGridLayoutManager.VERTICAL) {
                    setHeaderOrFooterOrientation(headerParent, LinearLayout.VERTICAL, true);
                    setHeaderOrFooterOrientation(footerParent, LinearLayout.VERTICAL, true);
                } else if (staggeredGridLayoutManager.getOrientation() == StaggeredGridLayoutManager.HORIZONTAL) {
                    setHeaderOrFooterOrientation(headerParent, LinearLayout.HORIZONTAL, true);
                    setHeaderOrFooterOrientation(footerParent, LinearLayout.HORIZONTAL, true);
                }
            }
            // SpanSizeLookup
            if (layoutManager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                gridLayoutManager.setSpanSizeLookup(new FixedViewSpanSizeLookup(adapterProxy, gridLayoutManager));
            }
        }
    }

    @Override
    public Adapter getAdapter() {
        return adapterProxy.getAdapter();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter != null && adapter.hasStableIds() != adapterProxy.hasStableIds()) {
            super.setAdapter(null);
            adapterProxy.setHasStableIds(adapter.hasStableIds());
            super.setAdapter(adapterProxy);
        }
        adapterProxy.setAdapter(adapter);
    }

}
