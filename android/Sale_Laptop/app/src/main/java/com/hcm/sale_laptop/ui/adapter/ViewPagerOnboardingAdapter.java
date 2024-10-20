package com.hcm.sale_laptop.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hcm.base.BaseAdapter;
import com.hcm.base.OnItemClick;
import com.hcm.sale_laptop.databinding.ItemPageOnboardingBinding;

import java.util.List;

public class ViewPagerOnboardingAdapter extends BaseAdapter<String, ItemPageOnboardingBinding> {

    public ViewPagerOnboardingAdapter(List<String> itemList, OnItemClick<String> listener) {
        super(itemList, listener);
    }

    @Override
    protected ItemPageOnboardingBinding createBinding(LayoutInflater inflater, ViewGroup parent) {
        return ItemPageOnboardingBinding.inflate(inflater, parent, false);
    }

    @Override
    protected void bindData(String item, ItemPageOnboardingBinding binding, int position) {
        binding.txtTitle.setText(item);
    }
}


