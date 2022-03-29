package com.androidemu.harveshihun;

import android.app.Application;
import android.util.Log;
import com.isw.android.corp.util.WinksApplication;
/* loaded from: classes.dex */
public class AppApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Log.d("AppApplication", "app create.");
        WinksApplication.onCreate(this);
    }
}
