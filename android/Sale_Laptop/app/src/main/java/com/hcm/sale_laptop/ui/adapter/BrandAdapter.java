package com.hcm.sale_laptop.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcm.base.BaseViewHolder;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.data.model.other.BrandModel;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandViewHolder> {

    private final List<BrandModel> listModel;
    private final Context context;

    public BrandAdapter(List<BrandModel> listModel, Context context) {
        this.listModel = listModel;
        this.context = context;
    }

    @NonNull
    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_brand, parent, false);
        return new BrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {
        holder.onBind(position, listModel);
    }

    @Override
    public int getItemCount() {
        return listModel.size();
    }

    public static class BrandViewHolder extends BaseViewHolder<BrandModel> {
        ImageView imageView;
        TextView textView;

        public BrandViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.txt_brand);
        }

        @Override
        public void onBind(int position, List<BrandModel> list) {
            final BrandModel model = list.get(position);
            imageView.setImageResource(model.getImageView());
            textView.setText(model.getBrandName());
        }
    }

}
