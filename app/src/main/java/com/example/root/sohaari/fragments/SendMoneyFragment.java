package com.example.root.sohaari.fragments;

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
    LinearLayout l1, l2, l3, l4, l5;
    Fragment fr;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_send_money, container, false);

        l1 = (LinearLayout) view.findViewById(R.id.mobile_number);
        l2 = (LinearLayout) view.findViewById(R.id.aadhar_number);
        l3 = (LinearLayout) view.findViewById(R.id.payment_address);
        l4 = (LinearLayout) view.findViewById(R.id.saved_beneficiary);
        l5 = (LinearLayout) view.findViewById(R.id.ifsc_account);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new SendToMobile();
                changeFragment(fr);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new SendToAadhar();
                changeFragment(fr);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new SendToPaymentAddress();
                changeFragment(fr);
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new SendToBeneficiary();
                changeFragment(fr);
            }
        });
        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new SendToIFSC();
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
