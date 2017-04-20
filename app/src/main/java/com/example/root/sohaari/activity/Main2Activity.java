package com.example.root.sohaari.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;

import com.example.root.sohaari.R;
import com.example.root.sohaari.fragments.Home;
import com.example.root.sohaari.fragments.SupportFragment;
import com.example.root.sohaari.service.BackgroundService;
import com.example.root.sohaari.service.USSDAccessibilityService;

public class Main2Activity extends AppCompatActivity {
    public static final String myPref = "com.app.sohaari";
    public static final String BALANCE = "balance";
    public static final String PENDING_REQUESTS = "pending requests";
    public static final String TRANSACTIONS = "transactions";
    public static String PACKAGE_NAME;
    public int currentFragment;
    String TAG = "main2activity";
    Fragment fr;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.home));
        //toolbar.setTitle("");
        setSupportActionBar(toolbar);

        fr = new Home();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fr).commit();
        currentFragment = 1;

        PACKAGE_NAME = getApplicationContext().getPackageName();
        startService(new Intent(this, BackgroundService.class));

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
                if (id == (R.id.menu_one) && currentFragment != 1) {
                    fr = new Home();
                    FragmentTransaction fc = getSupportFragmentManager().beginTransaction();
                    fc.setCustomAnimations(R.anim.slide_from_left, R.anim.slide_to_right);
                    fc.replace(R.id.frag_container, fr).commit();
                    setUpToolbar(getResources().getString(R.string.home));
                    //setUpToolbar("");
                    currentFragment = 1;
                } else if (id == (R.id.menu_three) && currentFragment != 2) {
                    fr = new MyProfile();
                    FragmentTransaction fc = getSupportFragmentManager().beginTransaction();
                    if (currentFragment == 1)
                        fc.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left);
                    else
                        fc.setCustomAnimations(R.anim.slide_from_left, R.anim.slide_to_right);
                    fc.replace(R.id.frag_container, fr).commit();
                    setUpToolbar(getResources().getString(R.string.menu_profile));
                    currentFragment = 2;
                } else if (id == (R.id.menu_four) && currentFragment != 3) {
                    fr = new SupportFragment();
                    FragmentTransaction fc = getSupportFragmentManager().beginTransaction();
                    fc.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left);
                    fc.replace(R.id.frag_container, fr).commit();
                    setUpToolbar(getResources().getString(R.string.menu_support));
                    currentFragment = 3;
                }
                return true;
            }
        });
    }

    public void setUpToolbar(String title) {
        if (toolbar == null) return;
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStop() {
        super.onStop();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, BackgroundService.class));
    }
}
