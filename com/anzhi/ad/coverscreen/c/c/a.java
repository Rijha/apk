package com.anzhi.ad.coverscreen.c.c;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes.dex */
public final class a {
    private static a b = null;
    private ExecutorService a;

    private a() {
        this.a = null;
        if (this.a == null) {
            this.a = Executors.newFixedThreadPool(5);
        }
    }

    public static a a(Context context) {
        if (b == null) {
            b = new a();
        }
        return b;
    }

    public final void a(b bVar) {
        this.a.submit(bVar);
    }
}
