package com.example.root.sohaari.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.root.sohaari.R;

import static com.example.root.sohaari.utils.MakeCall.makeCall;

/**
 * Created by root on 11/4/17.
 */

public class SendToPaymentAddress extends android.support.v4.app.Fragment {
    EditText payment_address, amount;
    Button send_continue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.send_to_payment_address, container, false);

        payment_address = (EditText) view.findViewById(R.id.payment_address);
        amount = (EditText) view.findViewById(R.id.amount);
        send_continue = (Button) view.findViewById(R.id.send_continue);

        send_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall("*99*1*3*" + payment_address.getText().toString() + "*" + amount.getText().toString(), getContext(), getActivity());
            }
        });
        return view;
    }
}
