package com.hcm.sale_laptop.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcm.base.BaseViewHolder;
import com.hcm.sale_laptop.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerOnboardingAdapter extends RecyclerView.Adapter<PagerVH> {

    private final List<String> stringList = new ArrayList<String>() {{
        add("Chào mừng bạn đến với cửa hàng của chúng tôi");
        add("Giao hàng nhanh");
        add("Hàng chất lượng");
    }};

    @NonNull
    @Override
    public PagerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PagerVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page_onboarding, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PagerVH holder, int position) {
        holder.onBind(position, stringList);
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }
}

class PagerVH extends BaseViewHolder<String> {
    TextView textView;

    public PagerVH(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.txt_title);
    }

    @Override
    public void onBind(int position, List<String> list) {
        textView.setText(list.get(position));
    }

}

