package com.hcm.sale_laptop.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcm.base.BaseViewHolder;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.data.model.other.DiscountedProductModel;

import java.util.List;

public class DiscountedProductAdapter extends RecyclerView.Adapter<DiscountedProductAdapter.DiscountedProductViewHolder> {
    private final List<DiscountedProductModel> listModel;
    private final Context context;

    public DiscountedProductAdapter(List<DiscountedProductModel> listModel, Context context) {
        this.listModel = listModel;
        this.context = context;
    }

    @NonNull
    @Override
    public DiscountedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_discounted_product, parent, false);
        return new DiscountedProductAdapter.DiscountedProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountedProductViewHolder holder, int position) {
        holder.onBind(position, listModel);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class DiscountedProductViewHolder extends BaseViewHolder<DiscountedProductModel> {
        public DiscountedProductViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBind(int position, List<DiscountedProductModel> list) {

        }
    }
}
