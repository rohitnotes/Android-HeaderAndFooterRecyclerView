package com.takwolf.android.hfrecyclerviewdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.adapter.LinearVerticalAdapter;
import com.takwolf.android.hfrecyclerviewdemo.listener.NavigationFinishClickListener;
import com.takwolf.android.hfrecyclerviewdemo.model.ApiClient;
import com.takwolf.android.hfrecyclerviewdemo.viewholder.VerticalFooter;
import com.takwolf.android.hfrecyclerviewdemo.viewholder.VerticalHeader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MultiRecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view_0)
    HeaderAndFooterRecyclerView recyclerView0;

    @BindView(R.id.recycler_view_1)
    HeaderAndFooterRecyclerView recyclerView1;

    private LinearVerticalAdapter adapter0;
    private LinearVerticalAdapter adapter1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_recycler_view);
        ButterKnife.bind(this);

        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        recyclerView0.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        new VerticalHeader(this, recyclerView0);
        new VerticalHeader(this, recyclerView0);
        new VerticalFooter(this, recyclerView0);
        new VerticalFooter(this, recyclerView0);

        new VerticalHeader(this, recyclerView1);
        new VerticalHeader(this, recyclerView1);
        new VerticalFooter(this, recyclerView1);
        new VerticalFooter(this, recyclerView1);

        adapter0 = new LinearVerticalAdapter(this);
        adapter0.getIllustList().addAll(ApiClient.buildIllustList());
        recyclerView0.setAdapter(adapter0);

        adapter1 = new LinearVerticalAdapter(this);
        adapter1.getIllustList().addAll(ApiClient.buildIllustList());
        recyclerView1.setAdapter(adapter1);
    }

    @OnClick(R.id.btn_replace_adapters)
    void onBtnReplaceAdaptersClick() {
        LinearVerticalAdapter adapter = adapter0;
        adapter0 = adapter1;
        adapter1 = adapter;

        recyclerView0.setAdapter(adapter0);
        recyclerView1.setAdapter(adapter1);
    }

    @OnClick(R.id.btn_swap_adapters)
    void onBtnSwapAdaptersClick() {
        LinearVerticalAdapter adapter = adapter0;
        adapter0 = adapter1;
        adapter1 = adapter;

        recyclerView0.swapAdapter(adapter0, false);
        recyclerView1.swapAdapter(adapter1, false);
    }

}
