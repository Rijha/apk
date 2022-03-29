package com.anzhi.ad.coverscreen.c.a;

import android.content.Context;
import com.anzhi.ad.coverscreen.c.c;
import com.anzhi.ad.coverscreen.c.i.b;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class a {
    public static JSONObject a(Context context, double d, double d2, String str, int i, String str2, String str3) {
        String a = b.a(context);
        String c = c.a(context).c();
        String d3 = c.a(context).d();
        String b = c.a(context).b();
        String a2 = c.a(context).a();
        String c2 = b.c(context);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("devid", a);
            jSONObject.put("adid", str2);
            jSONObject.put("appkey", str);
            jSONObject.put("type", 0);
            jSONObject.put("lat", 0.0d);
            jSONObject.put("lon", 0.0d);
            jSONObject.put("cellid", c);
            jSONObject.put("lac", d3);
            jSONObject.put("mcc", a2);
            jSONObject.put("mnc", b);
            jSONObject.put("wifi", c2);
            jSONObject.put("url", str3);
            jSONObject.put("adsdkversion", "1.1");
            jSONObject.put("sdktype", "COVERSCREEN");
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public static JSONObject a(Context context, int i) {
        String a = b.a(context);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("devid", a);
            jSONObject.put("adid", i);
            jSONObject.put("adsdkversion", "1.1");
            jSONObject.put("sdktype", "COVERSCREEN");
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public static JSONObject a(Context context, String str) {
        String a = b.a(context);
        String b = c.a(context).b();
        String a2 = b.a();
        com.anzhi.ad.coverscreen.c.i.a.a d = b.d(context);
        com.anzhi.ad.coverscreen.c.i.a.a();
        boolean a3 = com.anzhi.ad.coverscreen.c.i.a.a(context, "com.tencent.mm");
        int b2 = com.anzhi.ad.coverscreen.c.j.b.b(context);
        String str2 = String.valueOf(com.anzhi.ad.coverscreen.c.f.a.c().a()) + ":" + com.anzhi.ad.coverscreen.c.f.a.c().b();
        String c = c.a(context).c();
        String d2 = c.a(context).d();
        String str3 = (c.a(c) || c.a(d2)) ? "" : String.valueOf(c) + ":" + d2;
        String c2 = b.c(context);
        String b3 = b.b();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("adsdkversion", str);
            jSONObject.put("devid", a);
            jSONObject.put("brand", a2);
            jSONObject.put("model", b3);
            jSONObject.put("latlon", str2);
            jSONObject.put("cidlac", str3);
            jSONObject.put("wifi", c2);
            jSONObject.put("mnc", b);
            jSONObject.put("width", d.a());
            jSONObject.put("weixinflag", a3);
            jSONObject.put("networktype", b2);
            jSONObject.put("sdktype", "COVERSCREEN");
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public static JSONObject a(Context context, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        String a = b.a(context);
        String b = b.b();
        com.anzhi.ad.coverscreen.c.i.a.a();
        String a2 = com.anzhi.ad.coverscreen.c.i.a.a(context, false);
        String str3 = String.valueOf(b.d(context).a()) + "*" + b.d(context).b();
        String a3 = b.a();
        String d = b.d();
        String c = b.c();
        String c2 = c.a(context).c();
        String b2 = c.a(context).b();
        String e = c.a(context).e();
        com.anzhi.ad.coverscreen.c.i.a.a d2 = b.d(context);
        String str4 = String.valueOf(d2.a()) + "*" + d2.b();
        String b3 = b.b(context);
        String c3 = b.c(context);
        try {
            jSONObject.put("devid", a);
            jSONObject.put("model", b);
            jSONObject.put("packagenames", a2);
            jSONObject.put("resolution", str3);
            jSONObject.put("brand", a3);
            jSONObject.put("versionrelease", d);
            jSONObject.put("versioncode", c);
            jSONObject.put("appkey", str);
            jSONObject.put("adsdkversion", str2);
            jSONObject.put("sdktype", "COVERSCREEN");
            jSONObject.put("wifi", c3);
            jSONObject.put("call_id", c2);
            jSONObject.put("mac", b3);
            jSONObject.put("screen_size", str4);
            jSONObject.put("mobile_num", e);
            jSONObject.put("mnc", b2);
        } catch (JSONException e2) {
        }
        return jSONObject;
    }

    public static JSONObject a(Context context, String str, JSONArray jSONArray) {
        String a = b.a(context);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("adsdkversion", str);
            jSONObject.put("devid", a);
            jSONObject.put("installCount", jSONArray);
            jSONObject.put("sdktype", "COVERSCREEN");
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
