package com.example.root.sohaari;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class RequestMoney extends AppCompatActivity {
    Fragment fr;
    LinearLayout frame1, frame2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_money);
        frame1 = (LinearLayout) findViewById(R.id.frame11);
        frame2 = (LinearLayout) findViewById(R.id.frame12);

        frame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new FragmentA();

                changeFragment(fr);

            }
        });
        frame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new FragmentC();
                changeFragment(fr);
            }
        });
    }

    public void changeFragment(Fragment fr) {
        FragmentManager frm = getSupportFragmentManager();
        FragmentTransaction trans = frm.beginTransaction();
        trans.replace(R.id.rel, fr);
        trans.commit();
    }
}
