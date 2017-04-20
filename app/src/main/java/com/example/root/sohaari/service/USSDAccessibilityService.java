package com.example.root.sohaari.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import com.example.root.sohaari.activity.Main2Activity;
import com.example.root.sohaari.utils.BalanceEventBus;
import com.example.root.sohaari.utils.CheckBackgroundService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.root.sohaari.fragments.Home.checkUSSD;

/**
 * Created by rahul on 1/2/2017.
 */

public class USSDAccessibilityService extends AccessibilityService {
    //String TAG = "AccessibilityService";
    String Tag = "bhimService";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d("bhimService", "onAccessibilityEvent called");

        AccessibilityNodeInfo source = event.getSource();
        //if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED && !event.getClassName().equals("android.app.AlertDialog"))
        // android.app.AlertDialog is the standard but not for all phones
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED && !String.valueOf(event.getClassName()).contains("AlertDialog")) {
            return;
        }
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED && (source == null || !source.getClassName().equals("android.widget.TextView"))) {
            return;
        }
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED && TextUtils.isEmpty(source.getText())) {
            return;
        }

        List<CharSequence> eventText;
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            eventText = event.getText();
        } else {
            eventText = Collections.singletonList(source.getText());
        }

        String text = eventText.toString();
        Log.d("bhimService1", text);

        if (TextUtils.isEmpty(text)) return;

        if (checkUSSD == 1) {
            performGlobalAction(GLOBAL_ACTION_BACK);
            parseBankBalance(text.trim());
        } else if (checkUSSD == 2) {
            parseTransactionBalance(text.trim());
            checkUSSD = 0;
        } else if (checkUSSD == 3) {
            performGlobalAction(GLOBAL_ACTION_BACK);
            parseTransactionBalance(text.trim());
            checkUSSD = 0;
        } else if (checkUSSD == 10){
            performGlobalAction(GLOBAL_ACTION_BACK);
            parseUserDetails(text.toLowerCase().trim());
            checkUSSD = 0;
        }
        Log.d("bhimService2", text);
    }

    private void parseUserDetails(String text) {
        String tokens[] = text.toLowerCase().split("[:,.\\s+]]");
        ArrayList<String> keywords  = new ArrayList<>(Arrays.asList(tokens));
        if (keywords.contains("not registered on upi")){

        }
    }

    private void parseTransactionBalance(String text) {
        EventBus.getDefault().post(new BalanceEventBus(text));
        Log.d("eventbus3", text);
    }

    private void parseBankBalance(String text) {
        //String tokens[] = text.toLowerCase().split("[:,.\\s]+]");
        //[[your account balance is rs, 8618.40, ok]]
        //[[enter 6  digit upi pin for your state bank of india account no, xxxxxx7369, cancel, send]]

        String tokens[] = text.toLowerCase().split("[:,.\\s]*\\s+");
        ArrayList<String> keywords = new ArrayList<>(Arrays.asList(tokens));
        if (keywords.contains("rs")) {
            String balance = keywords.get(keywords.indexOf("rs") + 1);
            Log.d("eventbus1", "Main balance is" + balance);
            EventBus.getDefault().post(new BalanceEventBus(balance));
        }
        //EventBus.getDefault().post(new BalanceEventBus(keywords.toString()));
        Log.d("eventbus2", keywords.toString());
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d("bhimService", "USSDAccessibilityService connected");

        if (CheckBackgroundService.isAccessibilityEnabled(getApplicationContext())) {
            Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Main2Activity.class);
            //addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                startActivity(intent);
                Log.d("bhimService", "Service connected");
                //Toast.makeText(this, "service connected", Toast.LENGTH_SHORT).show();
            } catch (IllegalStateException e) {
            } catch (ActivityNotFoundException e2) {
            }
        }

        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.flags = AccessibilityServiceInfo.DEFAULT;
        info.packageNames = new String[]{"com.android.phone"};
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED | AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        setServiceInfo(info);
    }

    @Override
    public void onInterrupt() {
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
