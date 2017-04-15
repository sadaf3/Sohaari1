package com.example.root.sohaari.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.root.sohaari.R;

/**
 * Created by rahul on 14-Apr-17.
 */

public class SendMoneyFragment extends android.support.v4.app.Fragment {
    LinearLayout frame1, frame2, frame3, frame4, frame5, frame6;
    Fragment fr;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_send_money, container, false);

        frame1 = (LinearLayout) view.findViewById(R.id.frame1);
        frame2 = (LinearLayout) view.findViewById(R.id.frame2);
        frame3 = (LinearLayout) view.findViewById(R.id.frame3);
        frame4 = (LinearLayout) view.findViewById(R.id.frame4);
        frame5 = (LinearLayout) view.findViewById(R.id.frame5);
        frame6 = (LinearLayout) view.findViewById(R.id.frame6);
        frame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new SendToMobile();
                changeFragment(fr);
            }
        });
        frame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new SendToAadhar();
                changeFragment(fr);
            }
        });
        frame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new SendToPaymentAddress();
                changeFragment(fr);
            }
        });
        frame4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new SendToBeneficiary();
                changeFragment(fr);
            }
        });
        frame5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new SendToIFSC();
                changeFragment(fr);
            }
        });
        frame6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new SendToMMID();
                changeFragment(fr);
            }
        });
        return view;
    }

    public void changeFragment(Fragment fr) {
        if (view.findViewById(R.id.fragment_container) != null) {
            android.support.v4.app.FragmentManager frm = getFragmentManager();
            android.support.v4.app.FragmentTransaction trans = frm.beginTransaction();
            trans.setCustomAnimations(R.anim.slide_from_right,
                    R.anim.slide_to_left,
                    R.anim.slide_from_left,
                    R.anim.slide_to_right);
            trans.replace(R.id.fragment_container, fr).addToBackStack("sendmoney");
            trans.commit();
        }
    }
}
