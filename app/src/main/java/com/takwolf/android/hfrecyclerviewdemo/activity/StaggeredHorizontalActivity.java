package com.takwolf.android.hfrecyclerviewdemo.activity;

import android.os.Bundle;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.adapter.StaggeredHorizontalAdapter;
import com.takwolf.android.hfrecyclerviewdemo.holder.HorizontalFooter;
import com.takwolf.android.hfrecyclerviewdemo.holder.HorizontalHeader;
import com.takwolf.android.hfrecyclerviewdemo.holder.OptionViewHolder;
import com.takwolf.android.hfrecyclerviewdemo.listener.NavigationFinishClickListener;
import com.takwolf.android.hfrecyclerviewdemo.model.ApiClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StaggeredHorizontalActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    HeaderAndFooterRecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        toolbar.setTitle("Staggered Horizontal");
        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        new OptionViewHolder(this, recyclerView, RecyclerView.HORIZONTAL);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        new HorizontalHeader(this, recyclerView);
        new HorizontalHeader(this, recyclerView);
        new HorizontalFooter(this, recyclerView);
        new HorizontalFooter(this, recyclerView);

        StaggeredHorizontalAdapter adapter = new StaggeredHorizontalAdapter(this);
        adapter.getIllustList().addAll(ApiClient.buildIllustList());
        recyclerView.setAdapter(adapter);
    }

}
