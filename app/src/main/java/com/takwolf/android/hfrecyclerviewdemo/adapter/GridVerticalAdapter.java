package com.takwolf.android.hfrecyclerviewdemo.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.model.GlideApp;
import com.takwolf.android.hfrecyclerviewdemo.model.Illust;
import com.takwolf.android.hfrecyclerviewdemo.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_grid_vertical, parent, false), activity);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(this, position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_thumb)
        ImageView imgThumb;

        private final Activity activity;

        private GridVerticalAdapter adapter;
        private Illust illust;

        ViewHolder(@NonNull View itemView, @NonNull Activity activity) {
            super(itemView);
            this.activity = activity;
            ButterKnife.bind(this, itemView);
        }

        void bind(@NonNull GridVerticalAdapter adapter, int position) {
            this.adapter = adapter;
            illust = adapter.getIllustList().get(position);
            GlideApp.with(activity).load(illust.getImage()).placeholder(R.drawable.image_placeholder).into(imgThumb);
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
