package com.example.root.sohaari.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

/**
 * Created by rahul on 11-Apr-17.
 */

public class MakeCall {
    public static int PERMISSION_ALL = 1;
    public static String[] PERMISSIONS = {Manifest.permission.CALL_PHONE};

    public static void makeCall(final String ussdCode, final Context context, final Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent fetchBalanceIntent = new Intent(Intent.ACTION_CALL);
                fetchBalanceIntent.setData(Uri.parse("tel:" + ussdCode + Uri.encode("#")));

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions(activity, PERMISSIONS, PERMISSION_ALL);
                    return;
                }
                context.startActivity(fetchBalanceIntent);
            }
        }).start();
    }
}
