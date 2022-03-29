package com.untyc.ipn;

import android.content.Context;
import android.text.TextUtils;
/* loaded from: classes.dex */
public final class IM {
    private static String a;
    private Context b;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("I").append("S").append("V").append("=");
        a = sb.toString();
    }

    public IM(Context context) {
        this.b = context.getApplicationContext();
        o.i(this.b);
    }

    public void setAPKey(String str) {
        if (!TextUtils.isEmpty(str)) {
            o.a(this.b, str.trim());
        }
    }

    public void setDebugMode() {
        if (this.b != null) {
            try {
                f.e(this.b).a(this.b);
            } catch (Exception e) {
            }
        }
    }

    public void setSilentTime(int i) {
        long j = 3600000;
        if (i >= 60 && i <= 720) {
            j = (long) (i * 60 * 1000);
        }
        o.a(this.b, j);
    }

    public void start() {
        System.out.println(String.valueOf(a) + p.a);
        o.h(this.b);
        try {
            f.e(this.b).b(this.b);
        } catch (Exception e) {
        }
        this.b.startService(o.d(this.b));
        this.b.startService(o.e(this.b));
    }
}
