package com.anzhi.ad.coverscreen;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class D implements DownloadListener {
    private /* synthetic */ WA a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public D(WA wa) {
        this.a = wa;
    }

    @Override // android.webkit.DownloadListener
    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }
}
