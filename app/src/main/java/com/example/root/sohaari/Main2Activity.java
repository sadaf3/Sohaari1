package com.example.root.sohaari;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class Main2Activity extends AppCompatActivity {
    GridView gv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gv1=(GridView)findViewById(R.id.gv);
        gv1.setAdapter(new ImageAdapter(this));
    }
}
