package com.anzhi.ad.coverscreen;

import android.webkit.WebView;
import android.webkit.WebViewClient;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class F extends WebViewClient {
    private /* synthetic */ WA a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public F(WA wa) {
        this.a = wa;
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        this.a.a.getSettings().setBlockNetworkImage(false);
        super.onPageFinished(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        if (this.a.b.equalsIgnoreCase(str2) || (String.valueOf(this.a.b) + "/").equalsIgnoreCase(str2)) {
            this.a.a.loadData("<h1>Page load Error !</h1>", "text/html", "UTF-8");
        }
        WA.a(this.a, "加载失败");
    }
}
