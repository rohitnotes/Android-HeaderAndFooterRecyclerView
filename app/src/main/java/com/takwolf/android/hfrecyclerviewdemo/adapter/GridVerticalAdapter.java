package com.takwolf.android.hfrecyclerviewdemo.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.model.Illust;
import com.takwolf.android.hfrecyclerviewdemo.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class GridVerticalAdapter extends RecyclerView.Adapter<GridVerticalAdapter.ViewHolder> {

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_FULL_SPAN = 1;

    private final Activity activity;
    private final LayoutInflater inflater;
    private final List<Illust> illustList = new ArrayList<>();

    public GridVerticalAdapter(@NonNull Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    public List<Illust> getIllustList() {
        return illustList;
    }

    @Override
    public int getItemCount() {
        return illustList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 11 == 0) {
            return TYPE_FULL_SPAN;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(activity, inflater.inflate(R.layout.item_grid_vertical, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.update(this, position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_thumb)
        ImageView imgThumb;

        private final Activity activity;

        private GridVerticalAdapter adapter;
        private Illust illust;

        ViewHolder(@NonNull Activity activity, @NonNull View itemView) {
            super(itemView);
            this.activity = activity;
            ButterKnife.bind(this, itemView);
        }

        void update(@NonNull GridVerticalAdapter adapter, int position) {
            this.adapter = adapter;
            illust = adapter.getIllustList().get(position);
            Glide.with(activity).load(illust.getImage()).placeholder(R.drawable.image_placeholder).into(imgThumb);
        }

        @OnClick(R.id.btn_item)
        void onBtnItemClick() {
            int position = adapter.getIllustList().indexOf(illust);
            int newPosition = Math.abs(RandomUtils.random.nextInt()) % adapter.getIllustList().size();
            adapter.getIllustList().add(newPosition, adapter.getIllustList().remove(position));
            adapter.notifyItemMoved(position, newPosition);
        }

        @OnLongClick(R.id.btn_item)
        boolean onBtnItemLongClick() {
            int position = adapter.getIllustList().indexOf(illust);
            adapter.getIllustList().remove(position);
            adapter.notifyItemRemoved(position);
            return true;
        }

    }

}
