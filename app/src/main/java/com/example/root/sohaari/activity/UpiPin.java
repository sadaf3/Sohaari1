package com.example.root.sohaari.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.root.sohaari.R;

import static com.example.root.sohaari.utils.MakeCall.makeCall;

public class UpiPin extends AppCompatActivity {
    Button set_upi_pin, change_upi_pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upi_pin);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        setUpToolbar();

        set_upi_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall("*99*7*1", getApplicationContext(), UpiPin.this);
            }
        });

        change_upi_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall("*99*7*2", getApplicationContext(), UpiPin.this);
            }
        });
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
