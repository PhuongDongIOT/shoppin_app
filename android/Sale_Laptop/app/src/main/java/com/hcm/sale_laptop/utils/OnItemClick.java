package com.hcm.sale_laptop.utils;

public interface OnItemClick<T> {
    void onItemClick(T object);

    void onItemClick(int position);

    void onItemLongClick(T object);
}
