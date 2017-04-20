package com.example.root.sohaari.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.root.sohaari.R;
import com.example.root.sohaari.fragments.SendMoneyFragment;

public class SendMoney extends AppCompatActivity {
    //FrameLayout fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        //animation
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        setUpToolbar();
        if (findViewById(R.id.fragment_container) != null) {
            SendMoneyFragment sendMoneyFragment = new SendMoneyFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, sendMoneyFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
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
}
