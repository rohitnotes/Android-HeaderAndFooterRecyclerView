package com.takwolf.android.hfrecyclerviewdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.takwolf.android.hfrecyclerviewdemo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_linear_vertical)
    protected void onBtnLinearVerticalClick() {
        startActivity(new Intent(this, LinearVerticalActivity.class));
    }

    @OnClick(R.id.btn_linear_horizontal)
    protected void onBtnLinearHorizontalClick() {
        startActivity(new Intent(this, LinearHorizontalActivity.class));
    }

    @OnClick(R.id.btn_grid_vertical)
    protected void onBtnGridVerticalClick() {
        startActivity(new Intent(this, GridVerticalActivity.class));
    }

    @OnClick(R.id.btn_grid_horizontal)
    protected void onBtnGridHorizontalClick() {
        startActivity(new Intent(this, GridHorizontalActivity.class));
    }

    @OnClick(R.id.btn_staggered_vertical)
    protected void onBtnStaggeredVerticalClick() {
        startActivity(new Intent(this, StaggeredVerticalActivity.class));
    }

    @OnClick(R.id.btn_staggered_horizontal)
    protected void onBtnStaggeredHorizontalClick() {
        startActivity(new Intent(this, StaggeredHorizontalActivity.class));
    }

}
