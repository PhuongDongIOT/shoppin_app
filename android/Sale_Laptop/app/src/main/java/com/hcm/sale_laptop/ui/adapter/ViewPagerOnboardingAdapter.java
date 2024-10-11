package com.hcm.sale_laptop.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcm.sale_laptop.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerOnboardingAdapter extends RecyclerView.Adapter<PagerVH> {

    private final List<String> stringList = new ArrayList<String>() {{
        add("Chào mừng bạn đến với cửa hàng của chúng tôi");
        add("Giao hàng nhanh"); // red
        add("Hàng chất lượng"); // blue
    }};

    @NonNull
    @Override
    public PagerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PagerVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page_onboarding, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PagerVH holder, int position) {
        final View itemView = holder.itemView;
        final TextView tvTitle = itemView.findViewById(R.id.txt_title);
        tvTitle.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }
}

class PagerVH extends RecyclerView.ViewHolder {
    public PagerVH(@NonNull View itemView) {
        super(itemView);
    }
}

