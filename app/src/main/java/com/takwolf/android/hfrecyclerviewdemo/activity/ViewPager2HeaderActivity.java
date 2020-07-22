package com.takwolf.android.hfrecyclerviewdemo.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.adapter.LinearVerticalAdapter;
import com.takwolf.android.hfrecyclerviewdemo.holder.ViewPager2Header;
import com.takwolf.android.hfrecyclerviewdemo.listener.NavigationFinishClickListener;
import com.takwolf.android.hfrecyclerviewdemo.model.ApiClient;
import com.takwolf.android.hfrecyclerviewdemo.util.HandlerUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPager2HeaderActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    HeaderAndFooterRecyclerView recyclerView;

    private LinearVerticalAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_header);
        ButterKnife.bind(this);

        toolbar.setTitle("ViewPager2 Header");
        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new ViewPager2Header(this, recyclerView, new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                // TODO why ?
                HandlerUtils.handler.post(() -> {
                    adapter.getIllustList().clear();
                    adapter.getIllustList().addAll(ApiClient.buildIllustList());
                    adapter.notifyDataSetChanged();
                });
            }

        });

        adapter = new LinearVerticalAdapter(this);
        adapter.getIllustList().addAll(ApiClient.buildIllustList());
        recyclerView.setAdapter(adapter);
    }

}
