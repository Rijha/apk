package com.untyc.ipn;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
/* loaded from: classes.dex */
public class ISB extends Service {
    private static ISB a;
    private m b;

    private m a() {
        if (this.b == null) {
            this.b = f.g(this);
            this.b.a(this);
        }
        return this.b;
    }

    private static void a(ISB isb) {
        a = isb;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        try {
            return a().a(intent);
        } catch (Exception e) {
            return null;
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            a().a(configuration);
        } catch (Exception e) {
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        a(this);
        o.g(this);
        try {
            a().a();
        } catch (Exception e) {
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        try {
            a().c();
        } catch (Exception e) {
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        try {
            a().b();
        } catch (Exception e) {
        }
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        try {
            a().c(intent);
        } catch (Exception e) {
        }
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        try {
            a().a(intent, i);
        } catch (Exception e) {
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        int onStartCommand = super.onStartCommand(intent, i, i2);
        try {
            return a().a(intent, i, i2);
        } catch (Exception e) {
            return onStartCommand;
        }
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        boolean onUnbind = super.onUnbind(intent);
        try {
            return a().b(intent);
        } catch (Exception e) {
            return onUnbind;
        }
    }
}
