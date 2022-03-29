package com.anzhi.ad.coverscreen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.anzhi.ad.coverscreen.c.c;
import com.anzhi.ad.coverscreen.c.d.b;
/* loaded from: classes.dex */
public class WA extends Activity {
    WebView a;
    String b;
    private TextView c;

    private void a() {
        this.b = getIntent().getStringExtra("url");
        if (c.a(this.b)) {
            finish();
            return;
        }
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.a = new WebView(this);
        this.a.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.a.getSettings().setJavaScriptEnabled(true);
        this.a.getSettings().setUseWideViewPort(true);
        this.a.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.a.getSettings().setCacheMode(0);
        this.a.getSettings().setBlockNetworkImage(true);
        this.a.getSettings().setSupportZoom(true);
        this.a.getSettings().setBuiltInZoomControls(true);
        this.a.getSettings().setLightTouchEnabled(true);
        this.a.setHorizontalScrollBarEnabled(false);
        this.a.setVerticalScrollBarEnabled(false);
        this.a.setDownloadListener(new D(this));
        this.a.setWebChromeClient(new E(this));
        this.a.setWebViewClient(new F(this));
        frameLayout.addView(this.a);
        this.c = new TextView(this);
        this.c.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        this.c.setPadding(b.a(this, 6.0f), b.a(this, 6.0f), b.a(this, 6.0f), b.a(this, 6.0f));
        this.c.setBackgroundColor(Color.parseColor("#66000000"));
        this.c.setTextColor(-1);
        this.c.setTextSize(16.0f);
        frameLayout.addView(this.c);
        setContentView(frameLayout);
        this.a.loadData("<h3>Loading...</h3>", "text/html", "UTF-8");
        this.a.loadUrl(this.b);
    }

    public static /* synthetic */ void a(WA wa, String str) {
        if (str == null) {
            wa.c.setText("");
            wa.c.setVisibility(8);
            return;
        }
        wa.c.setText(str);
        wa.c.setVisibility(0);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.a == null || !this.a.canGoBack()) {
            super.onBackPressed();
        } else {
            this.a.goBack();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        a();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        a();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        WebView.enablePlatformNotifications();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
    }
}
