package com.blackboxstudios.agendaplus;

import android.content.BroadcastReceiver;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.content.Context;
import android.content.Intent;

public class Alarm extends BroadcastReceiver {
    //executes a sound whenever the alarm is fired
    //add ability to choose what ringtone user may want to use?
    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer media = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);  //set ringtone
        media.start();      //executes the mediaPlayer object
    }
}
