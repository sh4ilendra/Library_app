package com.example.dr.library_app;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class NotificationMessage extends AppCompatActivity{

    private BroadcastReceiver notifRegBroadcastReceiver;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_message);

        notifRegBroadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //check type of intent filter
                if(intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_SUCCESS))
                {
                    //Registration Success
                    String token=intent.getStringExtra("token");
                    Toast.makeText(getApplicationContext(),"GCM token:"+token,Toast.LENGTH_LONG).show();

                }else if(intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_ERROR))
                {
                    //Registration Error
                    Toast.makeText(getApplicationContext(),"GCM Registration error !!!",Toast.LENGTH_LONG).show();

                }else
                {
                    Toast.makeText(getApplicationContext(), "Error occurred", Toast.LENGTH_LONG).show();

                }
            }
        };
        //check status of google play service in device
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if(ConnectionResult.SUCCESS !=resultCode)
        {

            if (apiAvailability.isUserResolvableError(resultCode)) {
                Toast.makeText(getApplicationContext(),"Google Play Service is not installed/enabled in this device !!!",Toast.LENGTH_LONG).show();
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Toast.makeText(getApplicationContext(),"This device does not support Google Play Services",Toast.LENGTH_LONG).show();
                finish();
            }
            //type of error

        }else
        {
            //start service
            Intent intent=new Intent(this,GCMRegistrationIntentService.class);
            startService(intent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("NotificationMessage","onResume");
        LocalBroadcastManager.getInstance(this).registerReceiver(notifRegBroadcastReceiver,
                new IntentFilter(GCMRegistrationIntentService.REGISTRATION_SUCCESS));
        LocalBroadcastManager.getInstance(this).registerReceiver(notifRegBroadcastReceiver,
                new IntentFilter(GCMRegistrationIntentService.REGISTRATION_ERROR));

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("NotificationMessage","onPause");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(notifRegBroadcastReceiver);
    }
}
