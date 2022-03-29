package com.anzhi.ad.coverscreen;

import com.anzhi.ad.coverscreen.c.c;
import com.anzhi.ad.coverscreen.c.d.a;
import org.json.JSONArray;
/* renamed from: com.anzhi.ad.coverscreen.d */
/* loaded from: classes.dex */
final class RunnableC0003d implements Runnable {
    private /* synthetic */ AdComponent a;
    private final /* synthetic */ JSONArray b;

    public RunnableC0003d(AdComponent adComponent, JSONArray jSONArray) {
        this.a = adComponent;
        this.b = jSONArray;
    }

    @Override // java.lang.Runnable
    public final void run() {
        for (int i = 0; i < this.b.length(); i++) {
            String optString = this.b.optJSONObject(i).optString("image");
            if (c.a(optString)) {
                a.a();
                com.anzhi.ad.coverscreen.c.e.a.a().a(this.a.f(), optString, a.a(this.a.f(), com.anzhi.ad.coverscreen.a.a.a, optString), new C0004e(this));
            }
            String optString2 = this.b.optJSONObject(i).optString("icon");
            if (c.a(optString2)) {
                a.a();
                com.anzhi.ad.coverscreen.c.e.a.a().a(this.a.f(), optString2, a.a(this.a.f(), com.anzhi.ad.coverscreen.a.a.a, optString2), new C0005f(this));
            }
        }
    }
}
