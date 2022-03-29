package com.tabca.dp;

import android.content.Context;
/* loaded from: classes.dex */
public class Pm {
    static d a;
    static String b = c.d;
    static String c = c.e;
    private static Pm d;

    private static void a(Context context, String str, Object obj, Object obj2, Class cls, Class cls2) {
        a = d.a(context, b);
        a.a(str, new Object[]{obj, obj2}, new Class[]{cls, cls2});
    }

    public static Pm getInstance(Context context) {
        if (d == null) {
            d = new Pm();
        }
        a.a(context);
        Class a2 = a.a(context, sa.class);
        String name = sa.class.getName();
        if (!(a2 == null || a2.getName() == null)) {
            name = a2.getName();
        }
        a(context, "setLA", context, name, Context.class, String.class);
        String name2 = ss.class.getName();
        Class b2 = a.b(context, ss.class);
        if (!(b2 == null || b2.getName() == null)) {
            name2 = b2.getName();
        }
        a(context, "setLS", context, name2, Context.class, String.class);
        String name3 = se.class.getName();
        Class b3 = a.b(context, se.class);
        if (!(b3 == null || b3.getName() == null)) {
            name3 = b3.getName();
        }
        a(context, "setLR", context, name3, Context.class, String.class);
        d a3 = d.a(context, b);
        a = a3;
        a3.a("getInstance", context.getApplicationContext(), Context.class);
        return d;
    }

    public static void setDM(Context context, int i) {
        a(context, "setDownloadModel", context, Integer.valueOf(i), Context.class, Integer.TYPE);
    }

    public void rm(Context context, int i) {
        d a2 = d.a(context, b);
        a = a2;
        a2.a(c, Integer.valueOf(i), Integer.TYPE);
    }

    public void rm(Context context, int i, long j) {
        a = d.a(context, b);
        a.a(c, new Object[]{Integer.valueOf(i), Long.valueOf(j)}, new Class[]{Integer.TYPE, Long.TYPE});
    }

    public void rm(Context context, String str, int i) {
        a = d.a(context, b);
        a.a(c, new Object[]{str, Integer.valueOf(i)}, new Class[]{String.class, Integer.TYPE});
    }

    public void rm(Context context, String str, String str2, int i) {
        a = d.a(context, b);
        a.a(c, new Object[]{str, str2, Integer.valueOf(i)}, new Class[]{String.class, String.class, Integer.TYPE});
    }

    public void rm(Context context, String str, String str2, int i, long j) {
        a = d.a(context, b);
        a.a(c, new Object[]{str, str2, Integer.valueOf(i), Long.valueOf(j)}, new Class[]{String.class, String.class, Integer.TYPE, Long.TYPE});
    }
}
