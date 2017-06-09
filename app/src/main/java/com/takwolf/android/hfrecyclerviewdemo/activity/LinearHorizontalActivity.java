package com.takwolf.android.hfrecyclerviewdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.adapter.LinearHorizontalAdapter;
import com.takwolf.android.hfrecyclerviewdemo.listener.NavigationFinishClickListener;
import com.takwolf.android.hfrecyclerviewdemo.model.ApiClient;
import com.takwolf.android.hfrecyclerviewdemo.viewholder.ControllerViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinearHorizontalActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    HeaderAndFooterRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);

        toolbar.setTitle("Linear Horizontal");
        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        new ControllerViewHolder(this, recyclerView, ControllerViewHolder.ORIENTATION_HORIZONTAL);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        LinearHorizontalAdapter adapter = new LinearHorizontalAdapter(this);
        adapter.getIllustList().addAll(ApiClient.buildIllustList());
        recyclerView.setAdapter(adapter);
    }

}
