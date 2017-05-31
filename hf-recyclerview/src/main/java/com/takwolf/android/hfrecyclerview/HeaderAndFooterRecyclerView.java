package com.takwolf.android.hfrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

public class HeaderAndFooterRecyclerView extends RecyclerView {

    public static final int TYPE_HEADER = -1;
    public static final int TYPE_FOOTER = -2;

    private LinearLayout headerParent;
    private LinearLayout footerParent;

    private AdapterProxy adapterProxy;

    public HeaderAndFooterRecyclerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public HeaderAndFooterRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HeaderAndFooterRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(@NonNull Context context) {
        headerParent = new LinearLayout(context);
        footerParent = new LinearLayout(context);

        adapterProxy = new AdapterProxy();
        super.setAdapter(adapterProxy);
    }

    @NonNull
    public LinearLayout getHeaderParent() {
        return headerParent;
    }

    public void addHeaderView(@NonNull View view) {
        headerParent.addView(view);
    }

    public void addHeaderView(@NonNull View view, int index) {
        headerParent.addView(view, index);
    }

    public void removeHeaderView(@NonNull View view) {
        headerParent.removeView(view);
    }

    public void removeHeaderView(int index) {
        headerParent.removeViewAt(index);
    }

    public int getHeaderViewCount() {
        return headerParent.getChildCount();
    }

    @NonNull
    public LinearLayout getFooterParent() {
        return footerParent;
    }

    public void addFooterView(@NonNull View view) {
        footerParent.addView(view);
    }

    public void addFooterView(@NonNull View view, int index) {
        footerParent.addView(view, index);
    }

    public void removeFooterView(@NonNull View view) {
        footerParent.removeView(view);
    }

    public void removeFooterView(int index) {
        footerParent.removeViewAt(index);
    }

    public int getFooterViewCount() {
        return footerParent.getChildCount();
    }

    private void setHeaderOrFooterOrientation(@NonNull LinearLayout parent, int orientation, boolean isStaggeredGridLayout) {
        parent.setOrientation(orientation);
        if (isStaggeredGridLayout) {
            StaggeredGridLayoutManager.LayoutParams layoutParams;
            if (orientation == LinearLayout.VERTICAL) {
                layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            } else {
                layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            layoutParams.setFullSpan(true);
            parent.setLayoutParams(layoutParams);
        } else {
            LinearLayout.LayoutParams layoutParams;
            if (orientation == LinearLayout.VERTICAL) {
                layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            } else {
                layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            parent.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void setLayoutManager(LayoutManager layoutManager) {
        super.setLayoutManager(layoutManager);
        if (layoutManager != null) {
            // Orientation
            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                if (linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                    setHeaderOrFooterOrientation(headerParent, LinearLayout.VERTICAL, false);
                    setHeaderOrFooterOrientation(footerParent, LinearLayout.VERTICAL, false);
                } else if (linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                    setHeaderOrFooterOrientation(headerParent, LinearLayout.HORIZONTAL, false);
                    setHeaderOrFooterOrientation(footerParent, LinearLayout.HORIZONTAL, false);
                }
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (staggeredGridLayoutManager.getOrientation() == StaggeredGridLayoutManager.VERTICAL) {
                    setHeaderOrFooterOrientation(headerParent, LinearLayout.VERTICAL, true);
                    setHeaderOrFooterOrientation(footerParent, LinearLayout.VERTICAL, true);
                } else if (staggeredGridLayoutManager.getOrientation() == StaggeredGridLayoutManager.HORIZONTAL) {
                    setHeaderOrFooterOrientation(headerParent, LinearLayout.HORIZONTAL, true);
                    setHeaderOrFooterOrientation(footerParent, LinearLayout.HORIZONTAL, true);
                }
            }
            // SpanSizeLookup
            if (layoutManager instanceof GridLayoutManager) {
                final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

                    @Override
                    public int getSpanSize(int position) {
                        int viewType = adapterProxy.getItemViewType(position);
                        if (viewType == TYPE_HEADER || viewType == TYPE_FOOTER) {
                            return gridLayoutManager.getSpanCount();
                        } else {
                            return 1;
                        }
                    }

                });
            }
        }
    }

    @Override
    public Adapter getAdapter() {
        return adapterProxy.getAdapter();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        adapterProxy.setAdapter(adapter);
    }

    private static class FixedViewHolder extends ViewHolder {

        FixedViewHolder(View itemView) {
            super(itemView);
        }

    }

    private class AdapterProxy extends Adapter {

        private final AdapterDataObserver adapterDataObserver = new AdapterDataObserver() {

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                notifyItemRangeChanged(positionStart + 1, itemCount);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                notifyItemRangeInserted(positionStart + 1, itemCount);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                notifyItemRangeRemoved(positionStart + 1, itemCount);
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                if (itemCount == 1) {
                    notifyItemMoved(fromPosition + 1, toPosition + 1);
                } else {
                    notifyDataSetChanged();
                }
            }

        };

        private Adapter adapter;

        public Adapter getAdapter() {
            return adapter;
        }

        public void setAdapter(Adapter adapter) {
            if (this.adapter == adapter) {
                return;
            }
            if (this.adapter != null) {
                this.adapter.unregisterAdapterDataObserver(adapterDataObserver);
                this.adapter.onDetachedFromRecyclerView(HeaderAndFooterRecyclerView.this);
            }
            this.adapter = adapter;
            if (this.adapter != null) {
                this.adapter.registerAdapterDataObserver(adapterDataObserver);
                this.adapter.onAttachedToRecyclerView(HeaderAndFooterRecyclerView.this);
                notifyItemRangeChanged(1, adapter.getItemCount());
            }
        }

        @Override
        public int getItemCount() {
            return (adapter == null ? 0 : adapter.getItemCount()) + 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_HEADER;
            } else if (position == getItemCount() - 1) {
                return TYPE_FOOTER;
            } else {
                if (adapter != null) {
                    int viewType = adapter.getItemViewType(position - 1);
                    if (viewType == TYPE_HEADER) {
                        throw new RuntimeException(TYPE_HEADER + " is used for type header, please use other value.");
                    } else if (viewType == TYPE_FOOTER) {
                        throw new RuntimeException(TYPE_FOOTER + " is used for type footer, please use other value.");
                    } else {
                        return viewType;
                    }
                } else {
                    return 0;
                }
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case TYPE_HEADER:
                    return new FixedViewHolder(headerParent);
                case TYPE_FOOTER:
                    return new FixedViewHolder(footerParent);
                default:
                    if (adapter != null) {
                        return adapter.onCreateViewHolder(parent, viewType);
                    } else {
                        return null;
                    }
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (adapter != null && position != 0 && position != getItemCount() - 1) {
                adapter.onBindViewHolder(holder, position - 1);
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position, List payloads) {
            if (adapter != null && position != 0 && position != getItemCount() - 1) {
                adapter.onBindViewHolder(holder, position - 1, payloads);
            }
        }

        @Override
        public void setHasStableIds(boolean hasStableIds) {
            if (adapter != null) {
                adapter.setHasStableIds(hasStableIds);
            }
        }

        @Override
        public long getItemId(int position) {
            if (adapter != null && position != 0 && position != getItemCount() - 1) {
                return adapter.getItemId(position - 1);
            } else {
                return NO_ID;
            }
        }

        @Override
        public void onViewRecycled(ViewHolder holder) {
            if (adapter != null && holder.getItemViewType() != TYPE_HEADER && holder.getItemViewType() != TYPE_FOOTER) {
                adapter.onViewRecycled(holder);
            }
        }

        @Override
        public boolean onFailedToRecycleView(ViewHolder holder) {
            if (adapter != null && holder.getItemViewType() != TYPE_HEADER && holder.getItemViewType() != TYPE_FOOTER) {
                return adapter.onFailedToRecycleView(holder);
            } else {
                return false;
            }
        }

        @Override
        public void onViewAttachedToWindow(ViewHolder holder) {
            if (adapter != null && holder.getItemViewType() != TYPE_HEADER && holder.getItemViewType() != TYPE_FOOTER) {
                adapter.onViewAttachedToWindow(holder);
            }
        }

        @Override
        public void onViewDetachedFromWindow(ViewHolder holder) {
            if (adapter != null && holder.getItemViewType() != TYPE_HEADER && holder.getItemViewType() != TYPE_FOOTER) {
                adapter.onViewDetachedFromWindow(holder);
            }
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            if (adapter != null) {
                adapter.onAttachedToRecyclerView(recyclerView);
            }
        }

        @Override
        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            if (adapter != null) {
                adapter.onDetachedFromRecyclerView(recyclerView);
            }
        }

    }

}
