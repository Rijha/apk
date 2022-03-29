package com.tabca.dp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
/* loaded from: classes.dex */
public class ss extends Service {
    static String b = c.a;
    d a;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.a = d.a(getApplicationContext(), b);
        this.a.a("setContext", getApplicationContext(), Context.class);
        this.a.a("onCreate");
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        this.a = d.a(getApplicationContext(), b);
        this.a.a("setContext", getApplicationContext(), Context.class);
        this.a.a("onStart", new Object[]{intent, Integer.valueOf(i)}, new Class[]{Intent.class, Integer.TYPE});
    }
}
