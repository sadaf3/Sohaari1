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

import static com.example.root.sohaari.activity.Main2Activity.PENDING_REQUESTS;
import static com.example.root.sohaari.activity.Main2Activity.TRANSACTIONS;
import static com.example.root.sohaari.activity.Main2Activity.myPref;
import static com.example.root.sohaari.fragments.Home.checkUSSD;
import static com.example.root.sohaari.utils.MakeCall.makeCall;

public class PendingRequest extends AppCompatActivity {
    Button update_pending_requests;
    TextView pending_requests_balance;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_request);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        // registering eventBus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        setUpToolbar();

        checkUSSD = 0;

        update_pending_requests = (Button) findViewById(R.id.update_pending_requests);
        pending_requests_balance = (TextView) findViewById(R.id.pending_requests_balance);

        sharedPreferences = getSharedPreferences(myPref, 0);
        String pendingRequestsBalance = sharedPreferences.getString(PENDING_REQUESTS, PENDING_REQUESTS);
        if (!pendingRequestsBalance.toLowerCase().equals(PENDING_REQUESTS))
            setPendingRequestsBalance(pendingRequestsBalance);

        update_pending_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUSSD = 3;
                makeCall("*99*5", getApplicationContext(), PendingRequest.this);
                //makeCall("*123", getApplicationContext(), Transactions.this);
            }
        });
    }

    private void setPendingRequestsBalance(String balance) {
        pending_requests_balance.setText(balance);
        pending_requests_balance.setGravity(Gravity.START);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BalanceEventBus event) {
        if (checkUSSD != 3) return;
        String pendingRequestssBalance = event.balance;
        Log.d("eventbus+Transactions ", pendingRequestssBalance);

        setPendingRequestsBalance(pendingRequestssBalance);

        sharedPreferences = getSharedPreferences(myPref, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PENDING_REQUESTS, pendingRequestssBalance);
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
