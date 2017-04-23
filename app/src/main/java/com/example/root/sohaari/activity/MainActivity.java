package com.example.root.sohaari.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.root.sohaari.R;
import com.example.root.sohaari.service.BackgroundService;
import com.example.root.sohaari.service.USSDAccessibilityService;
import com.example.root.sohaari.utils.BalanceEventBus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.root.sohaari.fragments.Home.checkUSSD;
import static com.example.root.sohaari.utils.Constants.ACCOUNT_NUMBER;
import static com.example.root.sohaari.utils.Constants.BANKNAME;
import static com.example.root.sohaari.utils.Constants.ISVERIFIED;
import static com.example.root.sohaari.utils.Constants.IS_UPI_PIN_SET;
import static com.example.root.sohaari.utils.Constants.PAYMENT_ADDRESS;
import static com.example.root.sohaari.utils.Constants.myPref;
import static com.example.root.sohaari.utils.MakeCall.makeCall;

// an offline banking solution to every Indian

public class MainActivity extends AppCompatActivity {
    String PACKAGE_NAME;
    //Button bt1, button_verify;
    Button button_verify;
    Context context;
    String TAG = "mainactivity";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify);

        context = this;

        sharedPreferences = getSharedPreferences(myPref, 0);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
            Log.d("eventbus+mainActivity", "eventbus registered");
        }

        PACKAGE_NAME = getApplicationContext().getPackageName();
        startService(new Intent(this, BackgroundService.class));

        if (sharedPreferences.getBoolean(ISVERIFIED, false)) {
            startActivity(new Intent(this, Main2Activity.class));
            Log.d("eventbus+mainActivity", "verified");
        } else {
            Log.d("eventbus+mainActivity", "not verified");
            if (!isAccessibilitySettingsOn(getApplicationContext())) {
                {
                    Log.d("eventbus+mainActivity", "Accessibility service is off, turn it on");
                    Toast.makeText(this, "Accessibility service is off, turn it on", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, AccessibilityNotEnabled.class));
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            }
        }

        button_verify = (Button) findViewById(R.id.button_verify);

        button_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUSSD = 10;
                //startActivity(new Intent(getApplicationContext(), Home.class));
                callToVerify();
            }
        });
    }

    private void callToVerify() {
        Log.d("eventbus+MainActivity ", "ussd=10");
        makeCall("*99*4*3", context, MainActivity.this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BalanceEventBus event) {
        if (checkUSSD != 10) return;
        String details = event.balance;
        if (details.equals("not found")) {
            //Todo register for upi
            startActivity(new Intent(this, Help.class));
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        } else {
            //Todo save user details and open app
            String tokens[] = details.split(":");
            ArrayList<String> keywords = new ArrayList<>(Arrays.asList(tokens));
            keywords.add("null");
            keywords.add("null");
            keywords.add("null");
            keywords.add("null");

            String bank_name = keywords.get(0);
            String payment_address = keywords.get(1);
            String account_number = keywords.get(2);
            String is_upi_pin_set = keywords.get(3);

            SharedPreferences sharedPreferences1;
            sharedPreferences1 = getSharedPreferences(myPref, 0);

            SharedPreferences.Editor editor = sharedPreferences1.edit();
            editor.putBoolean(ISVERIFIED, true);
            editor.putString(BANKNAME, bank_name);
            editor.putString(PAYMENT_ADDRESS, payment_address);
            editor.putString(ACCOUNT_NUMBER, account_number);
            editor.putString(IS_UPI_PIN_SET, is_upi_pin_set);
            editor.apply();

            Log.d(TAG, "details=" + bank_name + payment_address + account_number + is_upi_pin_set);

            startActivity(new Intent(getApplicationContext(), Main2Activity.class));
        }
        Log.d("eventbus+MainActivity ", details);
    }

    @Override
    protected void onStop() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
            Log.d("eventbus+mainActivity", "eventBus deRegistered");
        }
        //Toast.makeText(this, "MainActivity onStop", Toast.LENGTH_SHORT).show();
        //stopService(new Intent(this, BackgroundService.class));
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        //moveTaskToBack(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //start background service to monitor if this app is running or not
        //startService(new Intent(this, BackgroundService.class));
    }

    private boolean isAccessibilitySettingsOn(Context mContext) {
        int accessibilityEnabled = 0;
        String TAG = "mainactivity";
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

}
