package com.takwolf.android.hfrecyclerviewdemo.activity;

import android.content.Intent;
import android.os.Bundle;

import com.takwolf.android.hfrecyclerviewdemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_linear_vertical)
    void onBtnLinearVerticalClick() {
        startActivity(new Intent(this, LinearVerticalActivity.class));
    }

    @OnClick(R.id.btn_linear_horizontal)
    void onBtnLinearHorizontalClick() {
        startActivity(new Intent(this, LinearHorizontalActivity.class));
    }

    @OnClick(R.id.btn_grid_vertical)
    void onBtnGridVerticalClick() {
        startActivity(new Intent(this, GridVerticalActivity.class));
    }

    @OnClick(R.id.btn_grid_horizontal)
    void onBtnGridHorizontalClick() {
        startActivity(new Intent(this, GridHorizontalActivity.class));
    }

    @OnClick(R.id.btn_staggered_vertical)
    void onBtnStaggeredVerticalClick() {
        startActivity(new Intent(this, StaggeredVerticalActivity.class));
    }

    @OnClick(R.id.btn_staggered_horizontal)
    void onBtnStaggeredHorizontalClick() {
        startActivity(new Intent(this, StaggeredHorizontalActivity.class));
    }

    @OnClick(R.id.btn_multi_recycler_view)
    void onBtnMultiRecyclerViewClick() {
        startActivity(new Intent(this, MultiRecyclerViewActivity.class));
    }

    @OnClick(R.id.btn_view_pager_header)
    void onBtnViewPagerHeaderClick() {
        startActivity(new Intent(this, ViewPagerHeaderActivity.class));
    }

    @OnClick(R.id.btn_view_pager_2_header)
    void onBtnViewPager2HeaderClick() {
        startActivity(new Intent(this, ViewPager2HeaderActivity.class));
    }

    @OnClick(R.id.btn_refresh_and_load_more)
    void onBtnRefreshAndLoadMoreClick() {
        startActivity(new Intent(this, RefreshAndLoadMoreActivity.class));
    }

}
