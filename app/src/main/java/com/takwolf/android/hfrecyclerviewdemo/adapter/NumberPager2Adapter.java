package com.takwolf.android.hfrecyclerviewdemo.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.takwolf.android.hfrecyclerviewdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NumberPager2Adapter extends RecyclerView.Adapter<NumberPager2Adapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final int count;

    public NumberPager2Adapter(@NonNull Activity activity, int count) {
        inflater = LayoutInflater.from(activity);
        this.count = count;
    }

    @Override
    public int getItemCount() {
        return count;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_page, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_text)
        TextView tvText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            tvText.setText(String.valueOf(position));
        }

    }

}
