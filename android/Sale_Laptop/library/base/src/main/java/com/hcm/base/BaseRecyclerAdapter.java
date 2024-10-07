package com.hcm.base;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context context;
    protected List<T> items;
    protected LayoutInflater inflater;

    public BaseRecyclerAdapter(Context context, List<T> items) {
        this.context = context;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * Cập nhật danh sách dữ liệu và làm mới giao diện.
     *
     * @param newItems Danh sách mới cần cập nhật.
     */
    @SuppressLint("NotifyDataSetChanged")
    public void updateItems(List<T> newItems) {
        this.items = newItems;
        notifyDataSetChanged();  // Thông báo cho adapter rằng dữ liệu đã thay đổi.
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(getLayoutResourceId(), parent, false);
        return createViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        bindDataToViewHolder(holder, items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    /**
     * Hàm trừu tượng, được các adapter con override để cung cấp layout cho từng item.
     *
     * @return ID của layout item.
     */
    protected abstract int getLayoutResourceId();

    /**
     * Hàm trừu tượng, được các adapter con override để tạo ViewHolder.
     *
     * @param itemView View của item.
     * @return ViewHolder mới.
     */
    protected abstract VH createViewHolder(View itemView);

    /**
     * Hàm trừu tượng, được các adapter con override để ánh xạ dữ liệu vào ViewHolder.
     *
     * @param holder   ViewHolder cần ánh xạ dữ liệu.
     * @param item     Dữ liệu của item.
     * @param position Vị trí của item trong danh sách.
     */
    protected abstract void bindDataToViewHolder(VH holder, T item, int position);
}

