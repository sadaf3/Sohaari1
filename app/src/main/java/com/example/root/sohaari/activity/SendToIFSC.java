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

public class SendToIFSC extends android.support.v4.app.Fragment {
    EditText ifsc, account_no, amount;
    Button send_continue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.send_to_ifsc, container, false);

        ifsc = (EditText) view.findViewById(R.id.ifsc);
        amount = (EditText) view.findViewById(R.id.amount);
        account_no = (EditText) view.findViewById(R.id.account_no);
        send_continue = (Button) view.findViewById(R.id.send_continue);

        send_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall("*99*5*" + ifsc.getText().toString() + "*" +
                                account_no.getText().toString() + "*" +
                                amount.getText().toString(),
                        getContext(), getActivity());
            }
        });
        return view;
    }
}
