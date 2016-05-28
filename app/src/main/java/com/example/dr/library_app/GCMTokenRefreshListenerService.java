package com.example.dr.library_app;


import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

public class GCMTokenRefreshListenerService extends InstanceIDListenerService {
/*
   when token refresh, start service to get new token
 */
    @Override
    public void onTokenRefresh() {
        Intent intent=new Intent(this,GCMRegistrationIntentService.class);
        startService(intent);
    }
}
