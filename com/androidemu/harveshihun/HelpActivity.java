package com.androidemu.harveshihun;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
/* loaded from: classes.dex */
public class HelpActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView view = new WebView(this);
        view.setWebChromeClient(new WebChromeClient() { // from class: com.androidemu.harveshihun.HelpActivity.1
            @Override // android.webkit.WebChromeClient
            public void onReceivedTitle(WebView view2, String title) {
                HelpActivity.this.setTitle(title);
            }
        });
        setContentView(view);
        view.loadUrl(getIntent().getData().toString());
    }
}
