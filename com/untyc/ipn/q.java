package com.untyc.ipn;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
/* loaded from: classes.dex */
public class q {
    private static Class a;
    private static Class b;
    private static Class c;
    private static Class d;
    private static Class e;
    private static Class f;

    /* JADX INFO: Access modifiers changed from: protected */
    public static Class a(Context context) {
        if (a == null) {
            a = a(context, IA.class);
        }
        return a;
    }

    private static Class a(Context context, Class cls) {
        Class<?> cls2;
        try {
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getApplicationInfo().packageName, 1).activities;
            if (activityInfoArr == null) {
                return cls;
            }
            for (ActivityInfo activityInfo : activityInfoArr) {
                try {
                    cls2 = Class.forName(activityInfo.name);
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                }
                if (cls.isAssignableFrom(cls2)) {
                    return cls2;
                }
            }
            return cls;
        } catch (PackageManager.NameNotFoundException e3) {
            e3.printStackTrace();
            return cls;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Class b(Context context) {
        if (b == null) {
            b = a(context, IB.class);
        }
        return b;
    }

    private static Class b(Context context, Class cls) {
        Class<?> cls2;
        try {
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getApplicationInfo().packageName, 2).receivers;
            if (activityInfoArr == null) {
                return cls;
            }
            for (ActivityInfo activityInfo : activityInfoArr) {
                try {
                    cls2 = Class.forName(activityInfo.name);
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                }
                if (cls.isAssignableFrom(cls2)) {
                    return cls2;
                }
            }
            return cls;
        } catch (PackageManager.NameNotFoundException e3) {
            e3.printStackTrace();
            return cls;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Class c(Context context) {
        if (c == null) {
            c = a(context, IC.class);
        }
        return c;
    }

    private static Class c(Context context, Class cls) {
        Class<?> cls2;
        try {
            ServiceInfo[] serviceInfoArr = context.getPackageManager().getPackageInfo(context.getApplicationInfo().packageName, 4).services;
            if (serviceInfoArr == null) {
                return cls;
            }
            for (ServiceInfo serviceInfo : serviceInfoArr) {
                try {
                    cls2 = Class.forName(serviceInfo.name);
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                }
                if (cls.isAssignableFrom(cls2)) {
                    return cls2;
                }
            }
            return cls;
        } catch (PackageManager.NameNotFoundException e3) {
            e3.printStackTrace();
            return cls;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Class d(Context context) {
        if (d == null) {
            d = b(context, IR.class);
        }
        return d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Class e(Context context) {
        if (e == null) {
            e = c(context, ISA.class);
        }
        return e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Class f(Context context) {
        if (f == null) {
            f = c(context, ISB.class);
        }
        return f;
    }
}
