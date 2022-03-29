package com.anzhi.ad.coverscreen;

import com.anzhi.ad.coverscreen.c.g.a;
/* loaded from: classes.dex */
final class u implements Runnable {
    private /* synthetic */ t a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u(t tVar) {
        this.a = tVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.a.a.b.size() > 1) {
            this.a.a.a(false);
        }
        a.a("TimerTask run");
    }
}
