package com.androidemu.harveshihun;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class EmulatorService extends Service {
    static final String ACTION_BACKGROUND = "com.androidemu.actions.BACKGROUND";
    static final String ACTION_FOREGROUND = "com.androidemu.actions.FOREGROUND";
    private static final String LOG_TAG = "EmulatorService";
    private static final Class[] mStartForegroundSignature = {Integer.TYPE, Notification.class};
    private static final Class[] mStopForegroundSignature = {Boolean.TYPE};
    private NotificationManager mNM;
    private Method mStartForeground;
    private Method mStopForeground;
    private Object[] mStartForegroundArgs = new Object[2];
    private Object[] mStopForegroundArgs = new Object[1];

    @Override // android.app.Service
    public void onCreate() {
        this.mNM = (NotificationManager) getSystemService("notification");
        try {
            this.mStartForeground = getClass().getMethod("startForeground", mStartForegroundSignature);
            this.mStopForeground = getClass().getMethod("stopForeground", mStopForegroundSignature);
        } catch (NoSuchMethodException e) {
            this.mStopForeground = null;
            this.mStartForeground = null;
        }
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int startId) {
        handleCommand(intent);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleCommand(intent);
        return 0;
    }

    void handleCommand(Intent intent) {
        if (ACTION_FOREGROUND.equals(intent.getAction())) {
            CharSequence text = getText(R.string.emulator_service_running);
            Notification notification = new Notification(R.drawable.app_icon, text, System.currentTimeMillis());
            notification.setLatestEventInfo(this, getText(R.string.app_label), text, PendingIntent.getActivity(this, 0, new Intent(this, EmulatorActivity.class), 0));
            startForegroundCompat(R.string.emulator_service_running, notification);
        } else if (ACTION_BACKGROUND.equals(intent.getAction())) {
            stopForegroundCompat(R.string.emulator_service_running);
        }
    }

    void startForegroundCompat(int id, Notification notification) {
        if (this.mStartForeground != null) {
            this.mStartForegroundArgs[0] = Integer.valueOf(id);
            this.mStartForegroundArgs[1] = notification;
            try {
                this.mStartForeground.invoke(this, this.mStartForegroundArgs);
            } catch (IllegalAccessException e) {
                Log.w(LOG_TAG, "Unable to invoke startForeground", e);
            } catch (InvocationTargetException e2) {
                Log.w(LOG_TAG, "Unable to invoke startForeground", e2);
            }
        } else {
            setForeground(true);
            this.mNM.notify(id, notification);
        }
    }

    void stopForegroundCompat(int id) {
        if (this.mStopForeground != null) {
            this.mStopForegroundArgs[0] = Boolean.TRUE;
            try {
                this.mStopForeground.invoke(this, this.mStopForegroundArgs);
            } catch (IllegalAccessException e) {
                Log.w(LOG_TAG, "Unable to invoke stopForeground", e);
            } catch (InvocationTargetException e2) {
                Log.w(LOG_TAG, "Unable to invoke stopForeground", e2);
            }
        } else {
            this.mNM.cancel(id);
            setForeground(false);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        stopForegroundCompat(R.string.emulator_service_running);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }
}
