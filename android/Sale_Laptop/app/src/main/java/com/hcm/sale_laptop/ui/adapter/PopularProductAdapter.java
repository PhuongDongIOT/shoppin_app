package com.hcm.sale_laptop.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hcm.base.BaseAdapter;
import com.hcm.base.OnItemClick;
import com.hcm.sale_laptop.data.model.other.PopularProductModel;
import com.hcm.sale_laptop.databinding.ItemPopularProductBinding;

import java.util.List;

public class PopularProductAdapter extends BaseAdapter<PopularProductModel, ItemPopularProductBinding> {

    public PopularProductAdapter(List<PopularProductModel> itemList, OnItemClick<PopularProductModel> listener) {
        super(itemList, listener);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    protected ItemPopularProductBinding createBinding(LayoutInflater inflater, ViewGroup parent) {
        return ItemPopularProductBinding.inflate(inflater, parent, false);
    }

    @Override
    protected void bindData(PopularProductModel item, ItemPopularProductBinding binding, int position) {

    }
}
