package com.hcm.base;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public abstract class BaseViewHolder<T, VB extends ViewBinding> extends RecyclerView.ViewHolder {

    protected Context mContext;

    public BaseViewHolder(VB binding) {
        super(binding.getRoot());
        mContext = binding.getRoot().getContext();
    }

    public abstract void onBind(T model);

}
