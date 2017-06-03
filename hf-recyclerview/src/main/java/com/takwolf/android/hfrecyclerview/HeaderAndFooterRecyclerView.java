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

    @Override
    public void setLayoutManager(LayoutManager layoutManager) {
        super.setLayoutManager(layoutManager);
        onLayoutManagerUpdate();
    }

    public void onLayoutManagerUpdate() {
        if (getLayoutManager() != null) {
            // Orientation and LayoutParams
            adjustFixedViewParentOrientationAndLayoutParams(headerParent, getLayoutManager());
            adjustFixedViewParentOrientationAndLayoutParams(footerParent, getLayoutManager());
            // SpanSizeLookup
            if (getLayoutManager() instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) getLayoutManager();
                FixedViewSpanSizeLookup fixedViewSpanSizeLookup;
                if (gridLayoutManager.getSpanSizeLookup() == null || gridLayoutManager.getSpanSizeLookup().getClass() != FixedViewSpanSizeLookup.class) {
                    fixedViewSpanSizeLookup = new FixedViewSpanSizeLookup();
                    gridLayoutManager.setSpanSizeLookup(fixedViewSpanSizeLookup);
                } else {
                    fixedViewSpanSizeLookup = (FixedViewSpanSizeLookup) gridLayoutManager.getSpanSizeLookup();
                }
                fixedViewSpanSizeLookup.setTargets(adapterProxy, gridLayoutManager);
            }
        }
    }

    private void adjustFixedViewParentOrientationAndLayoutParams(@NonNull LinearLayout fixedViewParent, @NonNull LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            int orientation;
            GridLayoutManager.LayoutParams layoutParams;
            if (((GridLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.VERTICAL) {
                orientation = LinearLayout.VERTICAL;
                layoutParams = new GridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            } else {
                orientation = LinearLayout.HORIZONTAL;
                layoutParams = new GridLayoutManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            fixedViewParent.setOrientation(orientation);
            fixedViewParent.setLayoutParams(layoutParams);
        } else if (layoutManager instanceof LinearLayoutManager) {
            int orientation;
            LayoutParams layoutParams;
            if (((LinearLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.VERTICAL) {
                orientation = LinearLayout.VERTICAL;
                layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            } else {
                orientation = LinearLayout.HORIZONTAL;
                layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            fixedViewParent.setOrientation(orientation);
            fixedViewParent.setLayoutParams(layoutParams);
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation;
            StaggeredGridLayoutManager.LayoutParams layoutParams;
            if (((StaggeredGridLayoutManager) layoutManager).getOrientation() == StaggeredGridLayoutManager.VERTICAL) {
                orientation = LinearLayout.VERTICAL;
                layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            } else {
                orientation = LinearLayout.HORIZONTAL;
                layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            layoutParams.setFullSpan(true);
            fixedViewParent.setOrientation(orientation);
            fixedViewParent.setLayoutParams(layoutParams);
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
