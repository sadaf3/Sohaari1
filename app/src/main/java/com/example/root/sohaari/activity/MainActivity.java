package com.example.root.sohaari.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.root.sohaari.R;

import static com.example.root.sohaari.fragments.Home.checkUSSD;
import static com.example.root.sohaari.utils.MakeCall.makeCall;

public class MainActivity extends AppCompatActivity {
    Button bt1, button_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button) findViewById(R.id.button);
        button_verify = (Button) findViewById(R.id.button_verify);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
        button_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUSSD = 10;
                makeCall("*99*4*3", getBaseContext(), MainActivity.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //start background service to monitor if this app is running or not
        //startService(new Intent(this, BackgroundService.class));
    }
}
