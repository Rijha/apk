package com.anzhi.ad.coverscreen;

import java.util.TimerTask;
/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.anzhi.ad.coverscreen.a  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0000a extends TimerTask {
    private /* synthetic */ AdComponent a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0000a(AdComponent adComponent) {
        this.a = adComponent;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        this.a.c();
    }
}
