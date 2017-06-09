package com.takwolf.android.hfrecyclerviewdemo.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.adapter.LinearVerticalAdapter;
import com.takwolf.android.hfrecyclerviewdemo.listener.NavigationFinishClickListener;
import com.takwolf.android.hfrecyclerviewdemo.model.ApiClient;
import com.takwolf.android.hfrecyclerviewdemo.util.HandlerUtils;
import com.takwolf.android.hfrecyclerviewdemo.viewholder.LoadMoreFooter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RefreshAndLoadMoreActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, LoadMoreFooter.OnLoadMoreListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recycler_view)
    HeaderAndFooterRecyclerView recyclerView;

    private LoadMoreFooter loadMoreFooter;
    private LinearVerticalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_and_load_more);
        ButterKnife.bind(this);

        toolbar.setTitle("Refresh and Load more");
        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadMoreFooter = new LoadMoreFooter(this, recyclerView, this);

        adapter = new LinearVerticalAdapter(this);
        adapter.getIllustList().addAll(ApiClient.buildIllustList());
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        HandlerUtils.handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                adapter.getIllustList().clear();
                adapter.getIllustList().addAll(ApiClient.buildIllustList());
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }

        }, 1000);
    }

    @Override
    public void onLoadMore() {
        HandlerUtils.handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                int startPosition = adapter.getItemCount();
                adapter.getIllustList().addAll(ApiClient.buildIllustList());
                adapter.notifyItemRangeInserted(startPosition, ApiClient.PAGE_SIZE);
                loadMoreFooter.setState(LoadMoreFooter.STATE_NORMAL);
            }

        }, 1000);
    }

}
