package com.example.root.sohaari.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.root.sohaari.R;
import com.example.root.sohaari.service.BackgroundService;
import com.example.root.sohaari.service.USSDAccessibilityService;

public class Main2Activity extends AppCompatActivity {
    public static String PACKAGE_NAME;
    GridView gv1;
    String TAG = "main2activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        PACKAGE_NAME = getApplicationContext().getPackageName();

        startService(new Intent(this, BackgroundService.class));

        if (!isAccessibilitySettingsOn(getApplicationContext())) {
            {
                startActivity(new Intent(this, AccessibilityNotEnabled.class));
            }
        }


        gv1 = (GridView) findViewById(R.id.gv);
        gv1.setAdapter(new ImageAdapter(this));
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent go = new Intent(Main2Activity.this, SendMoney.class);
                    startActivity(go);
                } else if (position == 1) {
                    Intent go = new Intent(Main2Activity.this, RequestMoney.class);
                    startActivity(go);
                } else if (position == 2) {
                    Intent go = new Intent(Main2Activity.this, Transactions.class);
                    startActivity(go);
                } else if (position == 3) {
                    Intent go = new Intent(Main2Activity.this, PendingRequest.class);
                    startActivity(go);
                }
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
    }

    private boolean isAccessibilitySettingsOn(Context mContext) {
        int accessibilityEnabled = 0;
        final String service = PACKAGE_NAME + "/" + USSDAccessibilityService.class.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    mContext.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
            Log.v(TAG, "accessibilityEnabled = " + accessibilityEnabled);
        } catch (Settings.SettingNotFoundException e) {
            Log.e(TAG, "Error finding setting, default accessibility to not found: "
                    + e.getMessage());
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            Log.v(TAG, "***ACCESSIBILITY IS ENABLED*** -----------------");
            String settingValue = Settings.Secure.getString(
                    mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();

                    Log.v(TAG, "-------------- > accessibilityService :: " + accessibilityService + " " + service);
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        Log.v(TAG, "We've found the correct setting - accessibility is switched on!");
                        return true;
                    }
                }
            }
        } else {
            Log.v(TAG, "***ACCESSIBILITY IS DISABLED***");
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //stopService(new Intent(this, BackgroundService.class));
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "destroyed", Toast.LENGTH_SHORT).show();
        stopService(new Intent(this, BackgroundService.class));
    }
}
