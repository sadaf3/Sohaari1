package com.example.root.sohaari.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.sohaari.R;
import com.example.root.sohaari.activity.PendingRequest;
import com.example.root.sohaari.activity.RequestMoney;
import com.example.root.sohaari.activity.SendMoney;
import com.example.root.sohaari.activity.Transactions;

import static com.example.root.sohaari.utils.Constants.BALANCE;
import static com.example.root.sohaari.utils.Constants.myPref;
import static com.example.root.sohaari.utils.MakeCall.makeCall;

/**
 * Created by root on 16/4/17.
 */

public class HomeFragment extends android.support.v4.app.Fragment {
    //public int checkUSSD = 0;
    LinearLayout ll1, ll2, ll3, ll4;
    TextView main_balance;
    CardView balanceCard;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        ll1 = (LinearLayout) view.findViewById(R.id.send);
        ll2 = (LinearLayout) view.findViewById(R.id.request);
        ll3 = (LinearLayout) view.findViewById(R.id.tran);
        ll4 = (LinearLayout) view.findViewById(R.id.pend);
        main_balance = (TextView) view.findViewById(R.id.main_balance);
        balanceCard = (CardView) view.findViewById(R.id.balanceCard);

        sharedPreferences = this.getActivity().getSharedPreferences(myPref, 0);
        String balance = sharedPreferences.getString(BALANCE, "Balance");
        //Toast.makeText(getContext(), "jj "+balance, Toast.LENGTH_SHORT).show();
        //setBalance(balance);

        balanceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Checking balance", Toast.LENGTH_SHORT).show();
                makeCall("*99*3", getContext(), getActivity());
            }
        });
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getActivity(), SendMoney.class);
                startActivity(go);
            }
        });
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getActivity(), RequestMoney.class);
                startActivity(go);
            }
        });
        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getActivity(), Transactions.class);
                startActivity(go);
            }
        });
        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getActivity(), PendingRequest.class);
                startActivity(go);
            }
        });
        return view;
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEvent(BalanceEventBus event) {
//        String mainBalance = event.balance;
//        Log.d("eventbus+HomeActivity ", mainBalance);
//
//        setBalance(mainBalance);
//
//        sharedPreferences = this.getActivity().getSharedPreferences(myPref, 0);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(BALANCE, mainBalance);
//        editor.commit();
//    }

//    private void setBalance(String balance) {
//        main_balance.setText("₹ " + balance);
//    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
//        super.onDestroy();
//    }
}
