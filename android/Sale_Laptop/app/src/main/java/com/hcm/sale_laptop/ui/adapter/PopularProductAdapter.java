package com.hcm.sale_laptop.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hcm.base.BaseAdapter;
import com.hcm.base.OnItemClick;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.data.model.other.ProductModel;
import com.hcm.sale_laptop.databinding.ItemPopularProductBinding;
import com.hcm.sale_laptop.utils.AppUtils;

import java.util.List;

public class PopularProductAdapter extends BaseAdapter<ProductModel, ItemPopularProductBinding> {

    public PopularProductAdapter(List<ProductModel> itemList, OnItemClick<ProductModel> listener) {
        super(itemList, listener);
    }

    @Override
    protected ItemPopularProductBinding createBinding(LayoutInflater inflater, ViewGroup parent) {
        return ItemPopularProductBinding.inflate(inflater, parent, false);
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void bindData(ProductModel item, ItemPopularProductBinding binding, int position) {
        binding.txtProductName.setText(item.getTitle());
        // Số tiền
        final String price = String.format("%.0f", item.getPrice());
        // Đơn vị tiền tệ
        final String currency = binding.getRoot().getContext().getString(R.string.currency);

        // Tạo SpannableString
        final SpannableString spannableString = new SpannableString(price + " " + currency);

        // Đổi màu cho phần "VNĐ"
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0808")), price.length() + 1, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set text vào TextView
        binding.txtPrice.setText(spannableString);
        AppUtils.setImageUrl(binding.imageView, item.getPicture());
    }
}
