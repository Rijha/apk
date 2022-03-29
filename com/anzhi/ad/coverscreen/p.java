package com.anzhi.ad.coverscreen;

import com.anzhi.ad.coverscreen.b.a;
/* loaded from: classes.dex */
final class p implements Runnable {
    private /* synthetic */ n a;
    private final /* synthetic */ String b;
    private final /* synthetic */ a c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(n nVar, String str, a aVar) {
        this.a = nVar;
        this.b = str;
        this.c = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.anzhi.ad.coverscreen.c.h.a.a(this.a.a.getApplicationContext()).a(this.c.a(), 17301586, this.c.d(), this.c.d(), "下载完成，点击安装", com.anzhi.ad.coverscreen.c.a.a(this.b), 16, this.c.k());
    }
}
