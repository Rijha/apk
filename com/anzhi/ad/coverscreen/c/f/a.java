package com.anzhi.ad.coverscreen.c.f;
/* loaded from: classes.dex */
public final class a {
    private static a c;
    private double a = 0.0d;
    private double b = 0.0d;

    public a() {
        new b(this);
    }

    public static a c() {
        if (c == null) {
            c = new a();
        }
        return c;
    }

    public final double a() {
        return this.a;
    }

    public final double b() {
        return this.b;
    }
}
