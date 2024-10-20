package com.hcm.sale_laptop.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hcm.base.BaseAdapter;
import com.hcm.base.OnItemClick;
import com.hcm.sale_laptop.data.model.other.BrandModel;
import com.hcm.sale_laptop.databinding.ItemBrandBinding;

import java.util.List;

public class BrandAdapter extends BaseAdapter<BrandModel, ItemBrandBinding> {

    public BrandAdapter(List<BrandModel> listModel, OnItemClick<BrandModel> listener) {
        super(listModel, listener);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    protected ItemBrandBinding createBinding(LayoutInflater inflater, ViewGroup parent) {
        return ItemBrandBinding.inflate(inflater, parent, false);
    }

    @Override
    protected void bindData(BrandModel item, ItemBrandBinding binding, int position) {
        binding.imageView.setImageResource(item.getImageView());
        binding.txtBrand.setText(item.getBrandName());
    }
}
