package com.takwolf.android.hfrecyclerviewdemo.activity;

import android.os.Bundle;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.adapter.LinearVerticalAdapter;
import com.takwolf.android.hfrecyclerviewdemo.holder.ViewPagerHeader;
import com.takwolf.android.hfrecyclerviewdemo.listener.NavigationFinishClickListener;
import com.takwolf.android.hfrecyclerviewdemo.model.ApiClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerHeaderActivity extends AppCompatActivity {

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

        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new ViewPagerHeader(this, recyclerView, new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                adapter.getIllustList().clear();
                adapter.getIllustList().addAll(ApiClient.buildIllustList());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {}

        });

        adapter = new LinearVerticalAdapter(this);
        adapter.getIllustList().addAll(ApiClient.buildIllustList());
        recyclerView.setAdapter(adapter);
    }

}
