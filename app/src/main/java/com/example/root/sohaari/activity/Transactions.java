package com.example.root.sohaari.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.root.sohaari.R;
import com.example.root.sohaari.utils.BalanceEventBus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.example.root.sohaari.activity.Main2Activity.TRANSACTIONS;
import static com.example.root.sohaari.activity.Main2Activity.myPref;
import static com.example.root.sohaari.fragments.Home.checkUSSD;
import static com.example.root.sohaari.utils.MakeCall.makeCall;

public class Transactions extends AppCompatActivity {
    Button update_transactions;
    TextView transactions_balance;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        // registering eventBus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        setUpToolbar();

        checkUSSD = 0;

        transactions_balance = (TextView) findViewById(R.id.transactions_balance);
        update_transactions = (Button) findViewById(R.id.update_transactions);

        sharedPreferences = getSharedPreferences(myPref, 0);
        String transactionBalance = sharedPreferences.getString(TRANSACTIONS, TRANSACTIONS);

        if (!transactionBalance.toLowerCase().equals(TRANSACTIONS))
            setTranscationBalance(transactionBalance);

        update_transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUSSD = 2;
                makeCall("*99*6*1", getApplicationContext(), Transactions.this);
                //makeCall("*123", getApplicationContext(), Transactions.this);
            }
        });
    }

    private void setTranscationBalance(String balance) {
        transactions_balance.setText(balance);
        transactions_balance.setGravity(Gravity.START);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BalanceEventBus event) {
        if (checkUSSD != 2) return;
        String transactionBalance = event.balance;
        Log.d("eventbus+Transactions ", transactionBalance);

        setTranscationBalance(transactionBalance);

        sharedPreferences = getSharedPreferences(myPref, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TRANSACTIONS, transactionBalance);
        editor.apply();
    }

    @Override
    protected void onStop() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onStop();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar == null) return;
        //toolbar.setTitle("Transactions");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
