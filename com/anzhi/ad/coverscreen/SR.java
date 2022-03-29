package com.anzhi.ad.coverscreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.anzhi.ad.coverscreen.c.a.b;
import com.anzhi.ad.coverscreen.c.d.c;
import com.anzhi.ad.coverscreen.c.g.a;
import com.anzhi.ad.coverscreen.c.j.d;
import com.anzhi.ad.coverscreen.c.j.f;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class SR extends BroadcastReceiver {
    public static /* synthetic */ void a(SR sr, Context context, String str) {
        String str2;
        String a = c.a(context, "DP_COVER_FILE", str.replace(".", ""));
        a.a("根据包名查询广告ID, [" + str + "," + a + "]");
        long j = 0;
        if (a == null || a.split(",").length != 2) {
            str2 = null;
        } else {
            str2 = a.split(",")[0];
            try {
                j = Long.parseLong(a.split(",")[1]);
            } catch (Exception e) {
            }
            if (new Date().getTime() - j > 120000) {
                str2 = null;
            }
        }
        String a2 = c.a(context, "DP_COVER_FILE", "appkey");
        if (com.anzhi.ad.coverscreen.c.c.a(a2)) {
            a.a("上传包安装信息失败：appkey为空");
            return;
        }
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("packageName", str);
        jSONObject.put("adid", str2);
        jSONArray.put(jSONObject);
        if (str2 != null) {
            c.b(context, "DP_COVER_FILE", str.replace(".", ""));
        }
        JSONObject a3 = com.anzhi.ad.coverscreen.c.a.a.a(context, "1.1", jSONArray);
        f fVar = new f();
        fVar.a(context, b.d(), a2, a3.toString());
        fVar.a(new w(sr, context, str));
        d.a().a(fVar);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        a.a("接收到广播：" + intent.getAction());
        if (!com.anzhi.ad.coverscreen.c.j.b.a(context) && !intent.getAction().equalsIgnoreCase("android.intent.action.PACKAGE_ADDED")) {
            return;
        }
        if (intent.getAction().equalsIgnoreCase("android.intent.action.PACKAGE_ADDED")) {
            new v(this, intent, context).start();
        } else if (intent.getAction().equalsIgnoreCase("android.intent.action.USER_PRESENT")) {
            String str = "show" + com.anzhi.ad.coverscreen.c.b.a.a(new Date(), "yyyyMMdd");
            int a = c.a(context, "DP_COVER_FILE", "dpnum", 0);
            int a2 = c.a(context, "DP_COVER_FILE", str, 0);
            boolean a3 = c.a(context, "DP_COVER_FILE", "showatscreenonplatform", true);
            boolean a4 = c.a(context, "DP_COVER_FILE", "showatscreenonuser", true);
            String a5 = c.a(context, "DP_COVER_FILE", "appkey", (String) null);
            if (com.anzhi.ad.coverscreen.c.c.a(a5)) {
                a.a("启动开屏广告失败：appkey为空");
            } else if (!a4) {
                a.a("启动开屏广告失败：用户未启用");
            } else {
                if (CoverAdComponent.getInstance() == null) {
                    CoverAdComponent.init(context, a5);
                    CoverAdComponent.setShowAtScreenOn(true);
                }
                if (!a3) {
                    a.a("启动开屏广告失败：平台未启用");
                    return;
                }
                int a6 = c.a(context, "DP_COVER_FILE", "dprate", 10);
                if (a6 <= 0) {
                    a.a("展示概率为：" + a6);
                    return;
                }
                int a7 = com.anzhi.ad.coverscreen.c.b.a(100);
                a.a("随机概率值：" + a7 + ",展示概率为：" + a6);
                if (a7 <= a6) {
                    a.a("最大开屏展示次数为：" + a + ", 当天已展示次数： " + a2);
                    if (a == 0) {
                        return;
                    }
                    if ((a <= 0 || a2 < a) && CoverAdComponent.showAd(context) == 0) {
                        c.b(context, "DP_COVER_FILE", str, a2 + 1);
                    }
                }
            }
        } else if (intent.getAction().equalsIgnoreCase("broadcast.route.control")) {
            switch (intent.getIntExtra("type", 0)) {
                case 1:
                    String stringExtra = intent.getStringExtra("packageName");
                    if (stringExtra != null && stringExtra.length() > 0) {
                        c.b(context, "DP_COVER_FILE", stringExtra.replace(".", ""));
                        a.a("清理包名对应的广告ID：" + stringExtra);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
