package com.example.root.sohaari.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.sohaari.R;

/**
 * Created by root on 11/4/17.
 */

public class SendToBeneficiary extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.send_to_beneficiary, container, false);
        return view;
    }
}