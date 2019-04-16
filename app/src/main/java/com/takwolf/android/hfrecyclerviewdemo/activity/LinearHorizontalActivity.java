package com.takwolf.android.hfrecyclerviewdemo.activity;

import android.os.Bundle;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.adapter.LinearHorizontalAdapter;
import com.takwolf.android.hfrecyclerviewdemo.holder.HorizontalFooter;
import com.takwolf.android.hfrecyclerviewdemo.holder.HorizontalHeader;
import com.takwolf.android.hfrecyclerviewdemo.holder.OptionViewHolder;
import com.takwolf.android.hfrecyclerviewdemo.listener.NavigationFinishClickListener;
import com.takwolf.android.hfrecyclerviewdemo.model.ApiClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LinearHorizontalActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    HeaderAndFooterRecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        toolbar.setTitle("Linear Horizontal");
        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        new OptionViewHolder(this, recyclerView, RecyclerView.HORIZONTAL);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        new HorizontalHeader(this, recyclerView);
        new HorizontalHeader(this, recyclerView);
        new HorizontalFooter(this, recyclerView);
        new HorizontalFooter(this, recyclerView);

        LinearHorizontalAdapter adapter = new LinearHorizontalAdapter(this);
        adapter.getIllustList().addAll(ApiClient.buildIllustList());
        recyclerView.setAdapter(adapter);
    }

}
