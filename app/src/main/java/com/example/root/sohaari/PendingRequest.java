package com.example.root.sohaari;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.root.sohaari.utils.MakeCall.makeCall;

public class PendingRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_request);

        makeCall("*99*5", this, this);
    }
}
