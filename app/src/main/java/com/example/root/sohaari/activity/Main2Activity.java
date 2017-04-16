package com.example.root.sohaari.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.root.sohaari.R;

import com.example.root.sohaari.service.USSDAccessibilityService;

public class Main2Activity extends AppCompatActivity {
    public static String PACKAGE_NAME;
    android.support.v4.app.Fragment fr;

    String TAG = "main2activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        fr = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fr).commit();

        PACKAGE_NAME = getApplicationContext().getPackageName();

        if (!isAccessibilitySettingsOn(getApplicationContext())) {
            {
                startActivity(new Intent(this, AccessibilityNotEnabled.class));
            }



        }


        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == (R.id.menu_one)) {
                    fr = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fr).commit();
                } else if (id == (R.id.menu_two)) {

                } else if (id == (R.id.menu_three)) {
                    fr = new MyProfile();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fr).commit();

                } else if (id == (R.id.menu_four)) {

                }
                return true;
            }
        });

    }

    public void changeFragment(Fragment fr) {
        if (findViewById(R.id.fragment_container) != null) {
            FragmentManager frm = getFragmentManager();
            FragmentTransaction trans = frm.beginTransaction();
            /*trans.setCustomAnimations(R.anim.slide_from_right,
                    R.anim.slide_to_left,
                    R.anim.slide_from_left,
                    R.anim.slide_to_right);*/
            trans.replace(R.id.fragment_container, fr).addToBackStack("sendmoney");
            trans.commit();
        }
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
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
