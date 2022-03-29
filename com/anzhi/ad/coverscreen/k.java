package com.anzhi.ad.coverscreen;
/* loaded from: classes.dex */
final class k implements Runnable {
    private /* synthetic */ SA a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(SA sa) {
        this.a = sa;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.a();
    }
}
