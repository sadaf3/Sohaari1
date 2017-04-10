package com.example.root.sohaari;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class SendMoney extends AppCompatActivity {
    Fragment fr;
    FrameLayout frame1,frame2,frame3,frame4,frame5,frame6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);
        frame1=(FrameLayout)findViewById(R.id.frame1);
        frame2=(FrameLayout)findViewById(R.id.frame2);
        frame3=(FrameLayout)findViewById(R.id.frame3);
        frame4=(FrameLayout)findViewById(R.id.frame4);
        frame5=(FrameLayout)findViewById(R.id.frame5);
        frame6=(FrameLayout)findViewById(R.id.frame6);
        frame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            fr=new FragmentA();
                changeFragment(fr);

            }
        });
    }
    public void changeFragment(Fragment fr) {
        FragmentManager frm=getSupportFragmentManager();
        FragmentTransaction trans=frm.beginTransaction();
        trans.replace(R.id.f,fr);
        trans.commit();


    }
}
