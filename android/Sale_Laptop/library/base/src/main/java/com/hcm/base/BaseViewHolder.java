package com.hcm.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract <T> void onBind(int position, List<T> list);

}
