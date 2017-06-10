package com.takwolf.android.hfrecyclerviewdemo.util;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;

public final class RefreshUtils {

    private RefreshUtils() {}

    public static void refreshCompat(@NonNull final SwipeRefreshLayout refreshLayout, @NonNull final SwipeRefreshLayout.OnRefreshListener refreshListener) {
        if (!refreshLayout.isRefreshing()) {
            HandlerUtils.handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (!refreshLayout.isRefreshing()) {
                        refreshLayout.setRefreshing(true);
                        refreshListener.onRefresh();
                    }
                }

            }, 100);
        }
    }

}
