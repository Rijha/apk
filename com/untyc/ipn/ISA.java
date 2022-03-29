package com.untyc.ipn;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
/* loaded from: classes.dex */
public class ISA extends Service {
    private static ISA a;
    private i b;

    private i a() {
        if (this.b == null) {
            this.b = f.c(this);
            this.b.a(this);
        }
        return this.b;
    }

    private static void a(ISA isa) {
        a = isa;
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
        o.f(this);
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
