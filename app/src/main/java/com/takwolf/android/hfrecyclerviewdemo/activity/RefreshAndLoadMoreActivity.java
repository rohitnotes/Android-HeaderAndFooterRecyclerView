package com.takwolf.android.hfrecyclerviewdemo.activity;

import android.os.Bundle;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.adapter.LinearVerticalAdapter;
import com.takwolf.android.hfrecyclerviewdemo.holder.LoadMoreFooter;
import com.takwolf.android.hfrecyclerviewdemo.holder.VerticalHeader;
import com.takwolf.android.hfrecyclerviewdemo.listener.NavigationFinishClickListener;
import com.takwolf.android.hfrecyclerviewdemo.model.ApiClient;
import com.takwolf.android.hfrecyclerviewdemo.util.HandlerUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_and_load_more);
        ButterKnife.bind(this);

        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new VerticalHeader(this, recyclerView);
        loadMoreFooter = new LoadMoreFooter(this, recyclerView, this);

        adapter = new LinearVerticalAdapter(this);
        recyclerView.setAdapter(adapter);

        refreshLayout.setColorSchemeResources(R.color.color_accent);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        HandlerUtils.handler.postDelayed(() -> {
            adapter.getIllustList().clear();
            adapter.getIllustList().addAll(ApiClient.buildIllustList());
            adapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
            loadMoreFooter.setState(LoadMoreFooter.STATE_ENDLESS);
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        HandlerUtils.handler.postDelayed(() -> {
            int startPosition = adapter.getItemCount();
            adapter.getIllustList().addAll(ApiClient.buildIllustList());
            adapter.notifyItemRangeInserted(startPosition, ApiClient.PAGE_SIZE);
            loadMoreFooter.setState(adapter.getItemCount() >= 200 ? LoadMoreFooter.STATE_FINISHED : LoadMoreFooter.STATE_ENDLESS);
        }, 1000);
    }

}
