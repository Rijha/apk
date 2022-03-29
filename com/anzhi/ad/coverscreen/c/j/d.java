package com.anzhi.ad.coverscreen.c.j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes.dex */
public final class d {
    private static d a = null;
    private ExecutorService b;

    private d() {
        this.b = null;
        this.b = Executors.newFixedThreadPool(5);
    }

    public static d a() {
        if (a == null) {
            a = new d();
        }
        return a;
    }

    public final void a(f fVar) {
        this.b.submit(fVar);
    }
}
