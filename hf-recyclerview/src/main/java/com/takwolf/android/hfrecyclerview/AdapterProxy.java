package com.takwolf.android.hfrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class AdapterProxy<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
