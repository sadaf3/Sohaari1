package com.example.root.sohaari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.BottomNavigationView;
import android.widget.AdapterView;
import android.widget.GridView;
public class Main2Activity extends AppCompatActivity {
    GridView gv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gv1=(GridView)findViewById(R.id.gv);
        gv1.setAdapter(new ImageAdapter(this));
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    Intent go = new Intent(Main2Activity.this,MiniStatement.class);
                    startActivity(go);
                }
                else if(position==1) {
                    Intent go = new Intent(Main2Activity.this,SendMoney.class);
                    startActivity(go);
                }
                else if(position==2) {
                    Intent go = new Intent(Main2Activity.this,RequestMoney.class);
                    startActivity(go);
                }
                else if(position==3) {
                    Intent go = new Intent(Main2Activity.this,PendingRequest.class);
                    startActivity(go);
                }
                else if(position==4) {
                    Intent go = new Intent(Main2Activity.this,Transactions.class);
                    startActivity(go);
                }
                else if(position==5) {
                    Intent go = new Intent(Main2Activity.this,UpiPin.class);
                    startActivity(go);
                }
            }
        });
    }
}
