package com.anzhi.ad.coverscreen;

import android.content.Intent;
import com.anzhi.ad.coverscreen.b.a;
/* loaded from: classes.dex */
final class o implements Runnable {
    private /* synthetic */ n a;
    private final /* synthetic */ a b;
    private final /* synthetic */ int c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(n nVar, a aVar, int i) {
        this.a = nVar;
        this.b = aVar;
        this.c = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.anzhi.ad.coverscreen.c.h.a.a(this.a.a.getApplicationContext()).a(this.b.a(), 17301586, this.b.d(), this.b.d(), "正在下载，已下载 " + (this.c + 1) + "%", new Intent(), 16, this.b.k());
    }
}
