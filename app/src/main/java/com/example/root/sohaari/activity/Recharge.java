package com.example.root.sohaari.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.root.sohaari.R;

import static com.example.root.sohaari.utils.MakeCall.makeCall;

/**
 * Created by rahul on 24-Apr-17.
 */

public class Recharge extends AppCompatActivity {
    Toolbar toolbar;
    Button save_our_address, send_recharge_amount, missed_call;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharge);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        save_our_address = (Button) findViewById(R.id.save_our_address);
        send_recharge_amount = (Button) findViewById(R.id.send_recharge_amount);
        missed_call = (Button) findViewById(R.id.missed_call);

        save_our_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall("*99*4*5*1*3", getApplicationContext(), Recharge.this);
            }
        });

        send_recharge_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall("*99*1*4", getApplicationContext(), Recharge.this);
            }
        });

        missed_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //makeCall("*99*1*4*1*10", getApplicationContext(), Recharge.this);
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:9872497727"));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
