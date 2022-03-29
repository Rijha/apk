package com.anzhi.ad.coverscreen;

import android.content.Context;
import android.content.Intent;
import com.anzhi.ad.coverscreen.c.d.c;
import com.anzhi.ad.coverscreen.c.g.a;
import com.anzhi.ad.coverscreen.c.j.b;
import com.anzhi.ad.coverscreen.c.j.d;
import com.anzhi.ad.coverscreen.c.j.f;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class CoverAdComponent extends AdComponent {
    private static CoverAdComponent d;
    volatile JSONArray c = null;

    private CoverAdComponent(Context context, String str) {
        super(context, str);
    }

    public static void close(Context context) {
        context.sendBroadcast(new Intent("com.screen.main.coverscreen.close"));
    }

    public static void destory(Context context) {
        if (d != null && context != null) {
            close(context);
            d.b();
        }
    }

    public static CoverAdComponent getInstance() {
        return d;
    }

    public static CoverAdComponent init(Context context, String str) {
        if (d != null) {
            return d;
        }
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        } else if (str == null || str.trim().length() < 8) {
            throw new IllegalArgumentException("appkey is error");
        } else {
            CoverAdComponent coverAdComponent = new CoverAdComponent(context, str);
            d = coverAdComponent;
            return coverAdComponent;
        }
    }

    public static void setShowAtScreenOn(boolean z) {
        if (d != null) {
            a = z;
            c.b(d.f(), "DP_COVER_FILE", "showatscreenonuser", z);
        }
    }

    public static int showAd(Context context) {
        boolean z;
        if (d == null) {
            return 1;
        }
        CoverAdComponent coverAdComponent = d;
        if (context == null || !b.a(context)) {
            a.a("无网络");
            return 2;
        } else if (coverAdComponent.c == null || coverAdComponent.c.length() == 0) {
            a.a("无广告");
            coverAdComponent.a(true);
            return 2;
        } else {
            coverAdComponent.f();
            if (com.anzhi.ad.coverscreen.c.d.b.a(com.anzhi.ad.coverscreen.a.a.c) == null) {
                a.a("关闭图标缺失");
                return 2;
            }
            JSONArray jSONArray = coverAdComponent.c;
            int i = 0;
            while (true) {
                if (i >= jSONArray.length()) {
                    z = false;
                    break;
                }
                String optString = jSONArray.optJSONObject(i).optString("image");
                com.anzhi.ad.coverscreen.c.d.a.a();
                String a = com.anzhi.ad.coverscreen.c.d.a.a(coverAdComponent.f(), com.anzhi.ad.coverscreen.a.a.a, optString);
                if (com.anzhi.ad.coverscreen.c.e.a.a().a(context, a) != null) {
                    a.a("check image cache: " + a + ", exist");
                    z = true;
                    break;
                }
                a.a("check image cache: " + a + ", not exist");
                if (com.anzhi.ad.coverscreen.c.e.a.a().a(coverAdComponent.f(), optString, a, new h(coverAdComponent)) != null) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                a.a("广告准备中");
                return 3;
            } else if (!coverAdComponent.a(jSONArray.toString())) {
                return 4;
            } else {
                coverAdComponent.c = null;
                coverAdComponent.a(true);
                return 0;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.anzhi.ad.coverscreen.AdComponent
    public final void b() {
        super.b();
        d = null;
        this.c = null;
        a.a("广告占用资源释放完成");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.anzhi.ad.coverscreen.AdComponent
    public final void c() {
        if (this.b) {
            f();
            a.b("广告正在获取中，此次调用忽略");
            return;
        }
        this.b = true;
        JSONObject a = com.anzhi.ad.coverscreen.c.a.a.a(f(), "1.1");
        f fVar = new f();
        fVar.a(f(), com.anzhi.ad.coverscreen.c.a.b.e(), e(), a.toString());
        fVar.a(new g(this));
        f();
        a.b("开始发送获取广告的网络请求");
        d.a().a(fVar);
    }
}
