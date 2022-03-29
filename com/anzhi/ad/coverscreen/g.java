package com.anzhi.ad.coverscreen;

import com.anzhi.ad.coverscreen.c.d.c;
import com.anzhi.ad.coverscreen.c.g.a;
import com.anzhi.ad.coverscreen.c.j.e;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
final class g implements e {
    private /* synthetic */ CoverAdComponent a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(CoverAdComponent coverAdComponent) {
        this.a = coverAdComponent;
    }

    @Override // com.anzhi.ad.coverscreen.c.j.e
    public final void a(boolean z, String str) {
        int i = 10;
        this.a.f();
        a.b("获取广告的网络请求得到返回结果：" + str);
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int optInt = jSONObject.optInt("errorcode", 1);
                String optString = jSONObject.optString("errormessage");
                if (optInt != 0) {
                    this.a.f();
                    a.b("request failed: " + optString);
                } else {
                    int optInt2 = jSONObject.optInt("screenOnNum", -1);
                    int optInt3 = jSONObject.optInt("screenOnShowRate", -1);
                    int optInt4 = optInt2 == -1 ? jSONObject.optInt("screenNoNum", 3) : optInt2;
                    int optInt5 = optInt3 == -1 ? jSONObject.optInt("screenNoShowRate", 10) : optInt3;
                    int optInt6 = jSONObject.optInt("screenOnSwitchDelay", 10);
                    boolean optBoolean = jSONObject.optBoolean("screenOnShow", true);
                    boolean optBoolean2 = jSONObject.optBoolean("coverDownloadConfirm", false);
                    this.a.c = jSONObject.optJSONArray("ads");
                    if (optInt6 > 0) {
                        i = optInt6;
                    }
                    if (this.a.c != null) {
                        this.a.a(this.a.c);
                    }
                    this.a.f();
                    a.b("已获取到广告: " + this.a.c);
                    c.b(this.a.f(), "DP_COVER_FILE", "dpnum", optInt4);
                    c.b(this.a.f(), "DP_COVER_FILE", "dprate", optInt5);
                    c.b(this.a.f(), "DP_COVER_FILE", "dpswitchdelay", i * 1000);
                    c.b(this.a.f(), "DP_COVER_FILE", "showatscreenonplatform", optBoolean);
                    c.b(this.a.f(), "DP_COVER_FILE", "download_confirm", optBoolean2);
                    a.a("setContent, screenOnNum: " + optInt4 + ", screenOnShowRate: " + optInt5 + ", screenOnSwitchDelay: " + (i * 1000) + ", enableScreenOnShow: " + optBoolean + ", downloadConfirm: " + optBoolean2);
                }
            } catch (JSONException e) {
                this.a.f();
                a.b("error result: " + e);
            }
        }
        this.a.d();
        this.a.f();
        a.b("开启下次获取广告任务");
        this.a.a(false);
    }
}
