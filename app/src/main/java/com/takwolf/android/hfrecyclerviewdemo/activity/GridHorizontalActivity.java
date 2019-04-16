package com.takwolf.android.hfrecyclerviewdemo.activity;

import android.os.Bundle;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.adapter.GridHorizontalAdapter;
import com.takwolf.android.hfrecyclerviewdemo.holder.HorizontalFooter;
import com.takwolf.android.hfrecyclerviewdemo.holder.HorizontalHeader;
import com.takwolf.android.hfrecyclerviewdemo.holder.OptionViewHolder;
import com.takwolf.android.hfrecyclerviewdemo.listener.NavigationFinishClickListener;
import com.takwolf.android.hfrecyclerviewdemo.model.ApiClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GridHorizontalActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    HeaderAndFooterRecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        toolbar.setTitle("Grid Horizontal");
        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        new OptionViewHolder(this, recyclerView, RecyclerView.HORIZONTAL);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        new HorizontalHeader(this, recyclerView);
        new HorizontalHeader(this, recyclerView);
        new HorizontalFooter(this, recyclerView);
        new HorizontalFooter(this, recyclerView);

        GridHorizontalAdapter adapter = new GridHorizontalAdapter(this);
        adapter.getIllustList().addAll(ApiClient.buildIllustList());
        recyclerView.setAdapter(adapter);
    }

}
