package com.takwolf.android.hfrecyclerviewdemo.activity;

import android.os.Bundle;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.adapter.GridVerticalAdapter;
import com.takwolf.android.hfrecyclerviewdemo.holder.OptionViewHolder;
import com.takwolf.android.hfrecyclerviewdemo.holder.VerticalFooter;
import com.takwolf.android.hfrecyclerviewdemo.holder.VerticalHeader;
import com.takwolf.android.hfrecyclerviewdemo.listener.CustomSpanSizeLookup;
import com.takwolf.android.hfrecyclerviewdemo.listener.NavigationFinishClickListener;
import com.takwolf.android.hfrecyclerviewdemo.model.ApiClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GridVerticalActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    HeaderAndFooterRecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        toolbar.setTitle("Grid Vertical");
        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        new OptionViewHolder(this, recyclerView, RecyclerView.VERTICAL);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new CustomSpanSizeLookup(layoutManager, recyclerView.getProxyAdapter()));
        recyclerView.setLayoutManager(layoutManager);

        new VerticalHeader(this, recyclerView);
        new VerticalHeader(this, recyclerView);
        new VerticalFooter(this, recyclerView);
        new VerticalFooter(this, recyclerView);

        GridVerticalAdapter adapter = new GridVerticalAdapter(this);
        adapter.getIllustList().addAll(ApiClient.buildIllustList());
        recyclerView.setAdapter(adapter);
    }

}
