package com.takwolf.android.hfrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class AdapterProxy extends RecyclerView.Adapter {

    static final int TYPE_HEADER = -1;
    static final int TYPE_FOOTER = -2;

    private final HeaderAndFooterRecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    private final RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() {

        @Override
        public void onChanged() {
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            notifyItemRangeChanged(positionStart + getPositionOffset(), itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            notifyItemRangeInserted(positionStart + getPositionOffset(), itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            notifyItemRangeRemoved(positionStart + getPositionOffset(), itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            if (itemCount == 1) {
                notifyItemMoved(fromPosition + getPositionOffset(), toPosition + getPositionOffset());
            } else {
                notifyDataSetChanged();
            }
        }

    };

    AdapterProxy(@NonNull HeaderAndFooterRecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (this.adapter == adapter) {
            return;
        }
        if (this.adapter != null) {
            this.adapter.unregisterAdapterDataObserver(adapterDataObserver);
            this.adapter.onDetachedFromRecyclerView(recyclerView);
        }
        this.adapter = adapter;
        if (this.adapter != null) {
            this.adapter.registerAdapterDataObserver(adapterDataObserver);
            this.adapter.onAttachedToRecyclerView(recyclerView);
        }
        notifyDataSetChanged();
    }

    void notifyHeaderInserted() {
        if (recyclerView.getHeaderViewCount() == 1) {
            notifyItemInserted(0);
        }
    }

    void notifyHeaderRemoved() {
        if (recyclerView.getHeaderViewCount() == 0) {
            notifyItemRemoved(0);
        }
    }

    void notifyFooterInserted() {
        if (recyclerView.getFooterViewCount() == 1) {
            notifyItemInserted(getItemCount() - 1);
        }
    }

    void notifyFooterRemoved() {
        if (recyclerView.getFooterViewCount() == 0) {
            notifyItemRemoved(getItemCount());
        }
    }

    private boolean isShowHeaderViewHolder() {
        return recyclerView.getHeaderViewCount() > 0;
    }

    private boolean isShowFooterViewHolder() {
        return recyclerView.getFooterViewCount() > 0;
    }

    private boolean isHeaderViewHolderPosition(int position) {
        return position == 0 && isShowHeaderViewHolder();
    }

    private boolean isFooterViewHolderPosition(int position) {
        return position == getItemCount() - 1 && isShowFooterViewHolder();
    }

    private int getHeaderViewHolderCount() {
        return isShowHeaderViewHolder() ? 1 : 0;
    }

    private int getFooterViewHolderCount() {
        return isShowFooterViewHolder() ? 1 : 0;
    }

    private int getPositionOffset() {
        return getHeaderViewHolderCount();
    }

    @Override
    public int getItemCount() {
        return (adapter == null ? 0 : adapter.getItemCount()) + getHeaderViewHolderCount() + getFooterViewHolderCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewHolderPosition(position)) {
            return TYPE_HEADER;
        } else if (isFooterViewHolderPosition(position)) {
            return TYPE_FOOTER;
        } else {
            if (adapter != null) {
                int viewType = adapter.getItemViewType(position - getPositionOffset());
                if (viewType == TYPE_HEADER) {
                    throw new RuntimeException(TYPE_HEADER + " is already used for view type Header, please replace another value.");
                } else if (viewType == TYPE_FOOTER) {
                    throw new RuntimeException(TYPE_FOOTER + " is already used for view type Footer, please replace another value.");
                } else {
                    return viewType;
                }
            } else {
                return 0;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return new FixedViewHolder(recyclerView.getHeaderParent());
            case TYPE_FOOTER:
                return new FixedViewHolder(recyclerView.getFooterParent());
            default:
                if (adapter != null) {
                    return adapter.onCreateViewHolder(parent, viewType);
                } else {
                    return null;
                }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_HEADER) {
            recyclerView.adjustFixedViewParentLayoutParamsAndOrientation(recyclerView.getHeaderParent());
        } else if (holder.getItemViewType() == TYPE_FOOTER) {
            recyclerView.adjustFixedViewParentLayoutParamsAndOrientation(recyclerView.getFooterParent());
        } else if (adapter != null) {
            //noinspection unchecked
            adapter.onBindViewHolder(holder, position - getPositionOffset());
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        if (holder.getItemViewType() == TYPE_HEADER) {
            recyclerView.adjustFixedViewParentLayoutParamsAndOrientation(recyclerView.getHeaderParent());
        } else if (holder.getItemViewType() == TYPE_FOOTER) {
            recyclerView.adjustFixedViewParentLayoutParamsAndOrientation(recyclerView.getFooterParent());
        } else if (adapter != null) {
            //noinspection unchecked
            adapter.onBindViewHolder(holder, position - getPositionOffset(), payloads);
        }
    }

    @Override
    public long getItemId(int position) {
        if (adapter != null && !isHeaderViewHolderPosition(position) && !isFooterViewHolderPosition(position)) {
            return adapter.getItemId(position - getPositionOffset());
        } else {
            return RecyclerView.NO_ID;
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if (adapter != null && holder.getItemViewType() != TYPE_HEADER && holder.getItemViewType() != TYPE_FOOTER) {
            //noinspection unchecked
            adapter.onViewRecycled(holder);
        }
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        //noinspection SimplifiableIfStatement
        if (adapter != null && holder.getItemViewType() != TYPE_HEADER && holder.getItemViewType() != TYPE_FOOTER) {
            //noinspection unchecked
            return adapter.onFailedToRecycleView(holder);
        } else {
            return false;
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        if (adapter != null && holder.getItemViewType() != TYPE_HEADER && holder.getItemViewType() != TYPE_FOOTER) {
            //noinspection unchecked
            adapter.onViewAttachedToWindow(holder);
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (adapter != null && holder.getItemViewType() != TYPE_HEADER && holder.getItemViewType() != TYPE_FOOTER) {
            //noinspection unchecked
            adapter.onViewDetachedFromWindow(holder);
        }
    }

    private static class FixedViewHolder extends RecyclerView.ViewHolder {

        FixedViewHolder(View itemView) {
            super(itemView);
        }

    }

}
