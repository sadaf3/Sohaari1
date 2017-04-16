package com.example.root.sohaari.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.root.sohaari.R;

import static com.example.root.sohaari.utils.MakeCall.makeCall;

/**
 * Created by root on 11/4/17.
 */

public class SendToMMID extends android.support.v4.app.Fragment {
    EditText mobile_no, mmid, amount;
    Button send_continue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.send_to_mmid, container, false);

        mobile_no = (EditText) view.findViewById(R.id.mobile_no);
        amount = (EditText) view.findViewById(R.id.amount);
        mmid = (EditText) view.findViewById(R.id.mmid);
        send_continue = (Button) view.findViewById(R.id.send_continue);

        send_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall("*99*6*" + mobile_no.getText().toString() + "*" +
                                mmid.getText().toString() + "*" +
                                amount.getText().toString(),
                        getContext(), getActivity());
            }
        });
        return view;
    }
}
