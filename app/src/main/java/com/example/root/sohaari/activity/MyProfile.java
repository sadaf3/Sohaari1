package com.example.root.sohaari.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.root.sohaari.R;

import static com.example.root.sohaari.utils.Constants.ACCOUNT_NUMBER;
import static com.example.root.sohaari.utils.Constants.BANKNAME;
import static com.example.root.sohaari.utils.Constants.IS_UPI_PIN_SET;
import static com.example.root.sohaari.utils.Constants.PAYMENT_ADDRESS;
import static com.example.root.sohaari.utils.Constants.myPref;
import static com.example.root.sohaari.utils.MakeCall.makeCall;

/**
 * Created by root on 16/4/17.
 */

public class MyProfile extends Fragment {
    LinearLayout l1, l2, l3, l4;
    TextView isUpiPinSet, userName, paymentAddress, accountNumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myprofile_fragment, container, false);

        l1 = (LinearLayout) view.findViewById(R.id.l1);
        l2 = (LinearLayout) view.findViewById(R.id.l2);
        l3 = (LinearLayout) view.findViewById(R.id.l3);
        l4 = (LinearLayout) view.findViewById(R.id.l4);

        userName = (TextView) view.findViewById(R.id.userName);
        paymentAddress = (TextView) view.findViewById(R.id.paymentAddress);
        accountNumber = (TextView) view.findViewById(R.id.accountNumber);
        isUpiPinSet = (TextView) view.findViewById(R.id.isUpiPinSet);

        SharedPreferences sharedPreferences;
        sharedPreferences = this.getActivity().getSharedPreferences(myPref, 0);

        String bank_name = "Name : " + sharedPreferences.getString(BANKNAME, "null");
        //bank_name = bank_name.replace("customer", "");
        String payment_address = "Payment Address : " + sharedPreferences.getString(PAYMENT_ADDRESS, "null");
        String account_number = "Account Number : " + sharedPreferences.getString(ACCOUNT_NUMBER, "null");
        String is_upi_pin_set = "UPI Pin : " + sharedPreferences.getString(IS_UPI_PIN_SET, "null");
        is_upi_pin_set = is_upi_pin_set.replace(", ok", "");

        userName.setText(bank_name);
        paymentAddress.setText(payment_address);
        accountNumber.setText(account_number);
        isUpiPinSet.setText(is_upi_pin_set);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "l1", Toast.LENGTH_SHORT).show();
                makeCall("*99*4*1", getContext(), getActivity());
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "l2", Toast.LENGTH_SHORT).show();
                makeCall("*99*4*5", getContext(), getActivity());
            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "l3", Toast.LENGTH_SHORT).show();
                makeCall("*99*4*6", getContext(), getActivity());
            }
        });

        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "l4", Toast.LENGTH_SHORT).show();
                makeCall("*99*4*7", getContext(), getActivity());
            }
        });
        return view;
    }

}
