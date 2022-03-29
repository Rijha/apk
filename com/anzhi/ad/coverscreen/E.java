package com.anzhi.ad.coverscreen;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class E extends WebChromeClient {
    private /* synthetic */ WA a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public E(WA wa) {
        this.a = wa;
    }

    @Override // android.webkit.WebChromeClient
    public final void onProgressChanged(WebView webView, int i) {
        this.a.setProgress(i * 100);
        WA.a(this.a, "已加载 >> " + i + "%");
        if (i >= 100) {
            WA.a(this.a, null);
        }
    }
}
