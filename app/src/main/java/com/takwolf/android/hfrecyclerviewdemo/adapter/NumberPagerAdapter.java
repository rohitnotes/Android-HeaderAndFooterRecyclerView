package com.takwolf.android.hfrecyclerviewdemo.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.takwolf.android.hfrecyclerviewdemo.R;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NumberPagerAdapter extends PagerAdapter {

    private final LayoutInflater inflater;
    private final int count;

    public NumberPagerAdapter(@NonNull Activity activity, int count) {
        inflater = LayoutInflater.from(activity);
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.item_page, container, false));
        holder.bind(position);
        container.addView(holder.getItemView());
        return holder;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewHolder holder = (ViewHolder) object;
        container.removeView(holder.getItemView());
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        ViewHolder holder = (ViewHolder) object;
        return view == holder.getItemView();
    }

    static class ViewHolder {

        @BindView(R.id.tv_text)
        TextView tvText;

        private final View itemView;

        ViewHolder(@NonNull View itemView) {
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        @NonNull
        View getItemView() {
            return itemView;
        }

        void bind(int position) {
            tvText.setText(String.valueOf(position));
        }

    }

}
