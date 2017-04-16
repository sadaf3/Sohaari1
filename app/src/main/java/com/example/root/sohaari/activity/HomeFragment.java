package com.example.root.sohaari.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.root.sohaari.R;

/**
 * Created by root on 16/4/17.
 */

public class HomeFragment extends android.support.v4.app.Fragment {
    LinearLayout ll1, ll2, ll3, ll4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ll1 = (LinearLayout) view.findViewById(R.id.send);
        ll2 = (LinearLayout) view.findViewById(R.id.request);
        ll3 = (LinearLayout) view.findViewById(R.id.tran);
        ll4 = (LinearLayout) view.findViewById(R.id.pend);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getActivity(), SendMoney.class);
                startActivity(go);
                //overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getActivity(), RequestMoney.class);
                startActivity(go);
                //overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getActivity(), Transactions.class);
                startActivity(go);
                // overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getActivity(), PendingRequest.class);
                startActivity(go);
                //overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
        return view;
    }
}
