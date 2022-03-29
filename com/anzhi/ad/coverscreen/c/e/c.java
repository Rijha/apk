package com.anzhi.ad.coverscreen.c.e;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.anzhi.ad.coverscreen.c.g.a;
import java.io.IOException;
import java.lang.ref.SoftReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c implements Runnable {
    private /* synthetic */ a a;
    private final /* synthetic */ Context b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ boolean e;
    private final /* synthetic */ Handler f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(a aVar, Context context, String str, String str2, boolean z, Handler handler) {
        this.a = aVar;
        this.b = context;
        this.c = str;
        this.d = str2;
        this.e = z;
        this.f = handler;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Drawable a = this.a.a(this.b, this.c, this.d);
        a.b("loadDrawable from method: getDrawableFromCache, drawable: " + a + ", url: " + this.c);
        if (a == null) {
            try {
                a.b("save to file start: " + this.d + ", url: " + this.c);
                a aVar = this.a;
                Context context = this.b;
                a.a(this.c, this.d);
                a = this.a.a(this.b, this.c, this.d);
            } catch (IOException e) {
                a.a(e);
                a = null;
                a.b("save to file failed: " + this.d + ", url: " + this.c);
            }
        }
        if (this.e && a != null) {
            this.a.c.put(this.c, new SoftReference(a));
        }
        this.f.sendMessage(this.f.obtainMessage(0, a));
    }
}
