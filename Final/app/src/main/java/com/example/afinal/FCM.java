package com.example.afinal;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;


public class FCM extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        String from= remoteMessage.getFrom();
      //  if (remoteMessage.getNotification()!=null){

           // Log.e("TAG","Recibe el tutulo"+remoteMessage.getNotification().getTitle());
          //Log.e("TAG","Recibe el body"+remoteMessage.getNotification().getBody());
     //   }
        //Con clave valor

        if (remoteMessage.getData().size()>0){
            Log.e("TAG","Recibe el tutulo por clave valor "+remoteMessage.getData().get("titulo"));
            Log.e("TAG","Recibe el body por clave valor "+remoteMessage.getData().get("body"));

            String t= remoteMessage.getData().get("titulo");
            String b= remoteMessage.getData().get("body");
            canales(t,b);
        }
    }
    private void canales(String t,String b) {
        String id="mensaje";
        NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,id);
        //android mayor a oreo
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nc= new NotificationChannel(id,"nuevo",NotificationManager.IMPORTANCE_DEFAULT);
            nc.setShowBadge(true);
            nm.createNotificationChannel(nc);
        }
        Context context= getApplicationContext();
        builder.setAutoCancel(true)
                .setContentTitle(t)
                .setSmallIcon(R.drawable.icbrt)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icbrt))
                .setContentText(b)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                //  .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.birretes))
                .setContentIntent(ruta())
                .setContentInfo("nuevo");
        Random random=new Random();
        int idNotify= random.nextInt(8000);
        nm.notify(idNotify,builder.build());
    }
    public PendingIntent ruta(){

        Intent nf =new Intent(Intent.ACTION_VIEW);
        nf.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_SINGLE_TOP );
        return PendingIntent.getActivity(this,0,nf,0);
    }
}


