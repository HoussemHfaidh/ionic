package com.NotifAlarm.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class LaunchReceiver extends BroadcastReceiver {

	public static final String ACTION_PULSE_SERVER_ALARM = "com.Bsmobile.send.ACTION_PULSE_SERVER_ALARM";

	@Override
	public void onReceive(Context context, Intent intent) {

		Intent serviceIntent = new Intent(context, GetService.class);
		context.startService(serviceIntent);
	}

}