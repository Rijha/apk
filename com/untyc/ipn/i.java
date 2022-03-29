package com.untyc.ipn;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class i extends k {
    private Object a;
    private Method b;
    private Method c;
    private Method d;
    private Method e;
    private Method f;
    private Method g;
    private Method h;
    private Method i;
    private Method j;
    private Method k;

    public i(Object obj) {
        if (obj == null) {
            throw new Exception();
        }
        this.a = obj;
    }

    public int a(Intent intent, int i, int i2) {
        if (this.h == null) {
            this.h = a(this.a, "onStartCommand");
        }
        Object a = a(this.a, this.h, new Object[]{intent, Integer.valueOf(i), Integer.valueOf(i2)});
        if (a != null) {
            return ((Integer) a).intValue();
        }
        return 3;
    }

    public IBinder a(Intent intent) {
        if (this.e == null) {
            this.e = a(this.a, "onBind");
        }
        Object a = a(this.a, this.e, new Object[]{intent});
        if (a != null) {
            return (IBinder) a;
        }
        return null;
    }

    public void a() {
        if (this.c == null) {
            this.c = a(this.a, "onCreate");
        }
        a(this.a, this.c, (Object[]) null);
    }

    public void a(Intent intent, int i) {
        if (this.d == null) {
            this.d = a(this.a, "onStart");
        }
        a(this.a, this.d, new Object[]{intent, Integer.valueOf(i)});
    }

    public void a(Configuration configuration) {
        if (this.i == null) {
            this.i = a(this.a, "onConfigurationChanged");
        }
        a(this.a, this.i, new Object[]{configuration});
    }

    public void a(ISA isa) {
        if (this.b == null) {
            this.b = a(this.a, "sns");
        }
        a(this.a, this.b, new Object[]{isa});
    }

    public void b() {
        if (this.j == null) {
            this.j = a(this.a, "onLowMemory");
        }
        a(this.a, this.j, (Object[]) null);
    }

    public boolean b(Intent intent) {
        if (this.f == null) {
            this.f = a(this.a, "onUnbind");
        }
        Object a = a(this.a, this.f, new Object[]{intent});
        if (a != null) {
            return ((Boolean) a).booleanValue();
        }
        return true;
    }

    public void c() {
        if (this.k == null) {
            this.k = a(this.a, "onDestroy");
        }
        a(this.a, this.k, (Object[]) null);
    }

    public void c(Intent intent) {
        if (this.g == null) {
            this.g = a(this.a, "onRebind");
        }
        a(this.a, this.g, new Object[]{intent, intent});
    }
}
