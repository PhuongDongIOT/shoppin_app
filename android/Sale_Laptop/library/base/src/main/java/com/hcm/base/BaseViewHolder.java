package com.hcm.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(int position, List<T> list);

}
