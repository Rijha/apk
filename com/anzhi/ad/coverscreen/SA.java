package com.anzhi.ad.coverscreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.anzhi.ad.coverscreen.c.b;
import com.anzhi.ad.coverscreen.c.d.c;
import com.anzhi.ad.coverscreen.c.g.a;
import com.anzhi.ad.coverscreen.c.j.d;
import com.anzhi.ad.coverscreen.c.j.f;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class SA extends Activity {
    Vector b;
    private x d;
    private Timer h;
    private TimerTask i;
    Handler a = new Handler();
    int c = 0;
    private int e = 0;
    private int f = 0;
    private int g = 10000;
    private BroadcastReceiver j = new j(this);

    public void a() {
        Vector b = b();
        if (b == null || b.isEmpty()) {
            a.a("no ad image ready, exit");
            finish();
            return;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.e = displayMetrics.widthPixels;
        this.f = displayMetrics.heightPixels;
        this.g = c.a(this, "DP_COVER_FILE", "dpswitchdelay", 10000);
        this.b = b;
        if (this.c == 0) {
            this.c = b.a(this.b.size());
        }
        a(true);
    }

    public void a(boolean z) {
        if (!this.b.isEmpty()) {
            if (this.c >= this.b.size()) {
                this.c = 0;
            }
            com.anzhi.ad.coverscreen.b.a aVar = (com.anzhi.ad.coverscreen.b.a) this.b.get(this.c);
            if (this.e > this.f) {
                this.d = new i(this, aVar, z);
            } else {
                this.d = new C(this, aVar, z);
            }
            setContentView(this.d, new ViewGroup.LayoutParams(-1, -1));
        }
    }

    private Vector b() {
        com.anzhi.ad.coverscreen.b.a aVar;
        String stringExtra = getIntent().getStringExtra("ads");
        if (stringExtra == null) {
            return null;
        }
        Vector vector = new Vector();
        try {
            JSONArray jSONArray = new JSONArray(stringExtra);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject == null) {
                    aVar = null;
                } else {
                    aVar = new com.anzhi.ad.coverscreen.b.a();
                    aVar.b(jSONObject.optInt("id"));
                    aVar.a(jSONObject.optInt("adid", 0));
                    aVar.a(jSONObject.optString("image"));
                    aVar.b(jSONObject.optString("name"));
                    aVar.i(jSONObject.optString("icon"));
                    aVar.g(jSONObject.optString("clickType"));
                    aVar.j(jSONObject.optString("appicon"));
                    aVar.c(jSONObject.optString("appname"));
                    aVar.d(jSONObject.optString("apppackage"));
                    aVar.e(jSONObject.optString("appurl"));
                    aVar.k(jSONObject.optString("appsize"));
                    aVar.f(jSONObject.optString("wxurl"));
                    aVar.h(jSONObject.optString("weburl"));
                }
                if (aVar != null && aVar.a() > 0) {
                    com.anzhi.ad.coverscreen.c.d.a.a();
                    String a = com.anzhi.ad.coverscreen.c.d.a.a(getApplicationContext(), com.anzhi.ad.coverscreen.a.a.a, aVar.b());
                    Drawable a2 = com.anzhi.ad.coverscreen.c.e.a.a().a(getApplicationContext(), aVar.b(), a);
                    if (a2 == null || !a2.isVisible()) {
                        com.anzhi.ad.coverscreen.c.e.a.a().a(getApplicationContext(), aVar.b(), a, new s(this, aVar, vector));
                    } else {
                        a.a("添加到广告展示列表：" + aVar.c());
                        vector.add(aVar);
                    }
                }
            }
            return vector;
        } catch (JSONException e) {
            return null;
        }
    }

    public void c() {
        a.a("startRefreshTask");
        this.i = new t(this);
        this.h.schedule(this.i, (long) this.g, (long) this.g);
    }

    public void c(com.anzhi.ad.coverscreen.b.a aVar) {
        com.anzhi.ad.coverscreen.c.d.a.a();
        com.anzhi.ad.coverscreen.c.e.a.a().a(getApplicationContext(), aVar.k(), com.anzhi.ad.coverscreen.c.d.a.a(getApplicationContext(), com.anzhi.ad.coverscreen.a.a.a, aVar.k()), null);
        com.anzhi.ad.coverscreen.c.c.b bVar = new com.anzhi.ad.coverscreen.c.c.b();
        bVar.a(aVar.a());
        Context applicationContext = getApplicationContext();
        String f = aVar.f();
        com.anzhi.ad.coverscreen.c.d.a.a();
        bVar.a(applicationContext, f, com.anzhi.ad.coverscreen.c.d.a.a(getApplicationContext(), com.anzhi.ad.coverscreen.a.a.b, ""));
        bVar.a(new n(this, aVar));
        com.anzhi.ad.coverscreen.c.c.a.a(getApplicationContext()).a(bVar);
        Toast.makeText(getApplicationContext(), "正在下载  " + aVar.d() + " 可到通知栏查看", 0).show();
    }

    public final void a(com.anzhi.ad.coverscreen.b.a aVar) {
        if (aVar != null) {
            a.a("点击类型：" + aVar.h());
            if (!com.anzhi.ad.coverscreen.c.j.b.a(getApplicationContext())) {
                Log.w("LOG", "NO NETWORK");
                return;
            }
            if (!com.anzhi.ad.coverscreen.c.c.a(aVar.e())) {
                Intent intent = new Intent("broadcast.route.control");
                intent.putExtra("type", 1);
                intent.putExtra("packageName", aVar.e());
                sendBroadcast(intent);
            }
            JSONObject a = com.anzhi.ad.coverscreen.c.a.a.a(getApplicationContext(), 0.0d, 0.0d, CoverAdComponent.getInstance().e(), 0, new StringBuilder().append(aVar.m()).toString(), "");
            f fVar = new f();
            fVar.a(getApplicationContext(), com.anzhi.ad.coverscreen.c.a.b.a(), CoverAdComponent.getInstance().e(), a.toString());
            fVar.a(new q(this));
            d.a().a(fVar);
            this.b.remove(aVar);
            if ("download".equals(aVar.h())) {
                boolean a2 = c.a((Context) this, "DP_COVER_FILE", "download_confirm", false);
                int b = com.anzhi.ad.coverscreen.c.j.b.b(getApplicationContext());
                a.a("clickTypeDownLoadFile, NetWorkType: " + b + ", downloadConfirm: " + a2);
                if (b == 4 || !a2) {
                    c(aVar);
                    if (this.b.isEmpty()) {
                        finish();
                    } else {
                        a(false);
                    }
                } else {
                    if (this.i != null) {
                        this.i.cancel();
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setCancelable(false);
                    builder.setTitle("提示");
                    builder.setMessage("确定下载 " + aVar.d() + " ？");
                    builder.setPositiveButton("确定", new l(this, aVar));
                    builder.setNegativeButton("取消", new m(this));
                    builder.show();
                }
            } else if ("weixin".equals(aVar.h())) {
                a.a("打开微信关注：" + aVar.g());
                Intent intent2 = new Intent();
                intent2.setFlags(335544320);
                try {
                    intent2.setClassName("com.tencent.mm", "com.tencent.mm.ui.qrcode.GetQRCodeInfoUI");
                    intent2.setData(Uri.parse(aVar.g()));
                    startActivity(intent2);
                } catch (ActivityNotFoundException e) {
                    a.a("打开微信关注失败（weixin）：" + e);
                    intent2.setAction("android.intent.action.VIEW");
                    intent2.setData(Uri.parse(aVar.g()));
                    startActivity(intent2);
                }
                if (this.b.isEmpty()) {
                    finish();
                } else {
                    a(false);
                }
            } else if ("weburl".equals(aVar.h())) {
                Intent intent3 = new Intent(this, WA.class);
                intent3.putExtra("url", aVar.i());
                startActivity(intent3);
                if (this.b.isEmpty()) {
                    finish();
                } else {
                    a(false);
                }
            }
        }
    }

    public final void b(com.anzhi.ad.coverscreen.b.a aVar) {
        if (aVar != null) {
            JSONObject a = com.anzhi.ad.coverscreen.c.a.a.a(getApplicationContext(), aVar.m());
            f fVar = new f();
            fVar.a(getApplicationContext(), com.anzhi.ad.coverscreen.c.a.b.b(), CoverAdComponent.getInstance().e(), a.toString());
            fVar.a(new r(this));
            d.a().a(fVar);
        }
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        CoverAdComponent.close(this);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        setContentView(frameLayout);
        if (bundle != null) {
            this.c = bundle.getInt("currentIndex", 0);
        }
        registerReceiver(this.j, new IntentFilter("com.screen.main.coverscreen.close"));
        this.h = new Timer();
        this.a.postDelayed(new k(this), 50);
    }

    @Override // android.app.Activity
    protected final void onDestroy() {
        super.onDestroy();
        if (this.j != null) {
            unregisterReceiver(this.j);
        }
        this.h.cancel();
        a.a("TimerTask onDestroy");
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        a.a("Ad Activity onKeyDown: " + i);
        return true;
    }

    @Override // android.app.Activity
    protected final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        a.a("SA onNewIntent");
        this.c = 0;
        a();
    }

    @Override // android.app.Activity
    protected final void onPause() {
        super.onPause();
        this.i.cancel();
        a.a("TimerTask onPause");
    }

    @Override // android.app.Activity
    protected final void onResume() {
        super.onResume();
        c();
    }

    @Override // android.app.Activity
    protected final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("currentIndex", this.c);
    }

    @Override // android.app.Activity
    protected final void onStop() {
        super.onStop();
        this.i.cancel();
        a.a("TimerTask onStop");
    }
}
