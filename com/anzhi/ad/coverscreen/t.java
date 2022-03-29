package com.anzhi.ad.coverscreen;

import java.util.TimerTask;
/* loaded from: classes.dex */
public final class t extends TimerTask {
    private /* synthetic */ SA a;

    public t(SA sa) {
        this.a = sa;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        this.a.c++;
        this.a.a.postDelayed(new u(this), 1);
    }
}
