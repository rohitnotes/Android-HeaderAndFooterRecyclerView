package com.takwolf.android.hfrecyclerviewdemo.viewholder;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadMoreFooter {

    public static final int STATE_NORMAL = 0;
    public static final int STATE_LOADING = 1;

    @IntDef({STATE_NORMAL, STATE_LOADING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {}

    public interface OnLoadMoreListener {

        void onLoadMore();

    }

    @BindView(R.id.icon_loading)
    View iconLoading;

    @State
    private int state = STATE_NORMAL;

    private final OnLoadMoreListener loadMoreListener;

    public LoadMoreFooter(@NonNull Context context, @NonNull HeaderAndFooterRecyclerView recyclerView, @NonNull OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
        View footerView = LayoutInflater.from(context).inflate(R.layout.footer_load_more, recyclerView.getFooterParent(), false);
        recyclerView.addFooterView(footerView);
        ButterKnife.bind(this, footerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                    checkLoadMore();
                }
            }

        });
    }

    @State
    public int getState() {
        return state;
    }

    public void setState(@State int state) {
        if (this.state != state) {
            this.state = state;
            switch (state) {
                case STATE_NORMAL:
                    iconLoading.setVisibility(View.INVISIBLE);
                    break;
                case STATE_LOADING:
                    iconLoading.setVisibility(View.VISIBLE);
                    break;
                default:
                    throw new AssertionError("Unknow load more state.");
            }
        }
    }

    private void checkLoadMore() {
        if (getState() == STATE_NORMAL) {
            setState(STATE_LOADING);
            loadMoreListener.onLoadMore();
        }
    }

}
