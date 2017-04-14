package com.example.root.sohaari;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class SendMoney extends AppCompatActivity {
    Fragment fr;
    LinearLayout frame1, frame2, frame3, frame4, frame5, frame6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        setUpToolbar();

        frame1 = (LinearLayout) findViewById(R.id.frame1);
        frame2 = (LinearLayout) findViewById(R.id.frame2);
        frame3 = (LinearLayout) findViewById(R.id.frame3);
        frame4 = (LinearLayout) findViewById(R.id.frame4);
        frame5 = (LinearLayout) findViewById(R.id.frame5);
        frame6 = (LinearLayout) findViewById(R.id.frame6);
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
                fr = new FragmentB();
                changeFragment(fr);
            }
        });
        frame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new FragmentC();
                changeFragment(fr);
            }
        });
        frame4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new FragmentD();
                changeFragment(fr);
            }
        });
        frame5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new FragmentE();
                changeFragment(fr);
            }
        });
        frame6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new FragmentF();
                changeFragment(fr);
            }
        });
    }

    public void changeFragment(Fragment fr) {
        if (findViewById(R.id.fragment_container) != null) {
            android.support.v4.app.FragmentManager frm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction trans = frm.beginTransaction();
            trans.setCustomAnimations(R.anim.slide_from_right,
                    R.anim.slide_to_left);
            trans.replace(R.id.f, fr);
            trans.commit();
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

        toolbar.setTitle("Send Money to");
        //toolbar.setNavigationIcon(R.drawable.ic_close);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
    }
}
