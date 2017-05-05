package com.example.root.sohaari.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.sohaari.R;
import com.example.root.sohaari.activity.PendingRequest;
import com.example.root.sohaari.activity.Recharge;
import com.example.root.sohaari.activity.RequestMoney;
import com.example.root.sohaari.activity.SendMoney;
import com.example.root.sohaari.activity.Transactions;
import com.example.root.sohaari.activity.UpiPin;
import com.example.root.sohaari.utils.BalanceEventBus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.example.root.sohaari.utils.Constants.BALANCE;
import static com.example.root.sohaari.utils.Constants.myPref;
import static com.example.root.sohaari.utils.MakeCall.makeCall;

/**
 * Created by rahul on 17-Apr-17.
 */

public class Home extends Fragment {
    public static int checkUSSD = 0;
    ImageView send, request, recharge;
    LinearLayout l1, l2, l3, l4;
    Button update_balance;
    TextView main_balance, testCall, balance_header;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        checkUSSD = 0;

        send = (ImageView) view.findViewById(R.id.send);
        request = (ImageView) view.findViewById(R.id.request);
        recharge = (ImageView) view.findViewById(R.id.recharge);
        l1 = (LinearLayout) view.findViewById(R.id.transactions);
        l2 = (LinearLayout) view.findViewById(R.id.pending_requests);
        l3 = (LinearLayout) view.findViewById(R.id.upi_pin);
        l4 = (LinearLayout) view.findViewById(R.id.language);
        update_balance = (Button) view.findViewById(R.id.update_balance);
        main_balance = (TextView) view.findViewById(R.id.main_balance);
        testCall = (TextView) view.findViewById(R.id.testCall);
        balance_header = (TextView) view.findViewById(R.id.balance_header);

        checkUSSD = 0;

        sharedPreferences = this.getActivity().getSharedPreferences(myPref, 0);
        String balance = sharedPreferences.getString(BALANCE, "Balance");

        //Toast.makeText(getContext(), "jj "+balance, Toast.LENGTH_SHORT).show();
        if (!balance.toLowerCase().equals("balance"))
            setBalance(balance);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SendMoney.class));
            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RequestMoney.class));
            }
        });
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "recharge : under progress", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), Recharge.class));
            }
        });

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Transactions.class));
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PendingRequest.class));
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UpiPin.class));
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), Transactions.class));
            }
        });

        update_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUSSD = 1;
                makeCall("*99*3", getContext(), getActivity());
                //makeCall("*123", getContext(), getActivity());
            }
        });
        return view;
    }

//    public void showDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_upipin, null);
//        Button dialogButton = (Button) view.findViewById(R.id.dialogButton);
//        editText = (EditText) view.findViewById(R.id.editTextUpiPin);
//
//
//        builder.setView(view);
//        final AlertDialog dialog = builder.create();
//
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkUSSD = 1;
//                Toast.makeText(getActivity(), editText.getText().toString(), Toast.LENGTH_SHORT).show();
//                makeCall("*99*3*" + editText.getText().toString(), getContext(), getActivity());
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("eventbus4+Home", "on create called");
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
            Log.d("eventbus4+Home", "registered");
        }
    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
            Log.d("eventbus4+Home", "deregistered");
        }
        Log.d("eventbus4+Home", "ondestroy called");
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BalanceEventBus event) {
        if (checkUSSD != 1) return;
        String mainBalance = event.balance;
        Log.d("eventbus+HomeActivity ", mainBalance);

        setBalance(mainBalance);

        sharedPreferences = this.getActivity().getSharedPreferences(myPref, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BALANCE, mainBalance);
        editor.apply();
    }

    private void setBalance(String balance) {
        main_balance.setText("₹ " + balance);
        balance_header.setTextColor(Color.parseColor("#FFFFFF"));
    }
}
