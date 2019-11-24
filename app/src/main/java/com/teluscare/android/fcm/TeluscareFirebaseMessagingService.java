package com.teluscare.android.fcm;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by SandeepY on 24112019
 **/


public class TeluscareFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("TAG","Refreshed token: " + token);

        //sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        if(remoteMessage.getData().size() > 0){
            if (null!=remoteMessage.getData().get("msg_type")){
                if (remoteMessage.getData().get("msg_type").equalsIgnoreCase("notification")) {
                    Bitmap bigImageBitmap = null;
                    Bitmap largeIconBitmap = null;

                    //generateNotification(remoteMessage.getData(), bigImageBitmap, largeIconBitmap);
                }
            }
        }
    }
}
