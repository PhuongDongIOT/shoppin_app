package com.hcm.sale_laptop.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hcm.base.BaseAdapter;
import com.hcm.base.OnItemClick;
import com.hcm.sale_laptop.data.model.other.DiscountedProductModel;
import com.hcm.sale_laptop.databinding.ItemDiscountedProductBinding;

import java.util.List;

public class DiscountedProductAdapter extends BaseAdapter<DiscountedProductModel, ItemDiscountedProductBinding> {

    public DiscountedProductAdapter(List<DiscountedProductModel> itemList, OnItemClick<DiscountedProductModel> listener) {
        super(itemList, listener);
    }

    @Override
    protected ItemDiscountedProductBinding createBinding(LayoutInflater inflater, ViewGroup parent) {
        return ItemDiscountedProductBinding.inflate(inflater, parent, false);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    protected void bindData(DiscountedProductModel item, ItemDiscountedProductBinding binding, int position) {

    }
}
