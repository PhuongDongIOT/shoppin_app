package com.hcm.sale_laptop.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcm.base.BaseFragment;
import com.hcm.sale_laptop.R;


public class InfoFragment extends BaseFragment {

    @Override
    protected void setupUI() {

    }

    @Override
    protected void setupAction() {

    }

    @Override
    protected void setupData() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    protected int getLayoutResourceId() {
        return 0;
    }
}