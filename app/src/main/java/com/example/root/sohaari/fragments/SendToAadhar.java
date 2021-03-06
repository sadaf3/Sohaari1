package com.example.root.sohaari.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.root.sohaari.R;

import static com.example.root.sohaari.utils.MakeCall.makeCall;

/**
 * Created by root on 11/4/17.
 */

public class SendToAadhar extends android.support.v4.app.Fragment {
    TextView aadhar_no, amount;
    Button send_continue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.send_to_aadhar, container, false);

        aadhar_no = (TextView) view.findViewById(R.id.aadhar_no);
        amount = (TextView) view.findViewById(R.id.amount);
        send_continue = (Button) view.findViewById(R.id.send_continue);

        send_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall("*99*1*2*" + aadhar_no.getText().toString() + "*" + amount.getText().toString(), getContext(), getActivity());
            }
        });
        return view;
    }
}
