package com.hcm.sale_laptop.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcm.base.BaseViewHolder;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.data.model.other.PopularProductModel;

import java.util.List;

public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.PopularProductViewHolder> {

    private final List<PopularProductModel> listModel;
    private final Context context;

    public PopularProductAdapter(List<PopularProductModel> listModel, Context context) {
        this.listModel = listModel;
        this.context = context;
    }

    @NonNull
    @Override
    public PopularProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_popular_product, parent, false);
        return new PopularProductAdapter.PopularProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularProductViewHolder holder, int position) {
        holder.onBind(position, listModel);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class PopularProductViewHolder extends BaseViewHolder<PopularProductModel> {
        public PopularProductViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBind(int position, List<PopularProductModel> list) {

        }
    }
}
