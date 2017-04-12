package com.example.root.sohaari;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by root on 10/4/17.
 */

public class FragmentA extends android.support.v4.app.Fragment {
    TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        tv = (TextView) view.findViewById(R.id.sendto);
        return view;
    }

    public void setTv(String s) {
        s="REQUEST MONEY";
        tv.setText(s);
    }
}
