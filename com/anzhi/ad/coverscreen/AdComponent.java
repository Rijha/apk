package com.anzhi.ad.coverscreen;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.anzhi.ad.coverscreen.c.d.c;
import com.anzhi.ad.coverscreen.c.g.a;
import com.anzhi.ad.coverscreen.c.j.b;
import com.anzhi.ad.coverscreen.c.j.d;
import com.anzhi.ad.coverscreen.c.j.f;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public abstract class AdComponent {
    public static final int FAIL_IN_PREPARATION;
    public static final int FAIL_NOT_INITIALIZE;
    public static final int FAIL_NO_AD;
    public static final int FAIL_START_ACTIVITY;
    public static final int SUCCESS;
    static boolean a = false;
    private String c;
    private Context d;
    private TimerTask g;
    boolean b = false;
    private boolean e = false;
    private int f = 10;
    private Timer h = new Timer();

    public AdComponent(Context context, String str) {
        this.d = context;
        this.c = str;
        a = false;
        c.b(context, "DP_COVER_FILE", "appkey", str);
        c.b(context, "DP_COVER_FILE", "showatscreenonuser", true);
        a.a("初始化广告组件，appkey: " + str);
        if (!b.a(context)) {
            Log.w("LOG", "NO NETWORK");
        } else if (c.a(this.d, "coverscreen", "isFirstRun", true)) {
            Context context2 = this.d;
            a.b("初始化客户端信息");
            JSONObject a2 = com.anzhi.ad.coverscreen.c.a.a.a(this.d, this.c, "1.1");
            f fVar = new f();
            fVar.a(this.d, com.anzhi.ad.coverscreen.c.a.b.c(), this.c, a2.toString());
            fVar.a(new C0001b(this));
            d.a().a(fVar);
        } else {
            g();
        }
    }

    private void g() {
        String a2 = c.a(this.d, "DP_COVER_FILE", "packnames", (String) null);
        if (a2 == null) {
            a();
            return;
        }
        String[] split = a2.split(",");
        JSONArray jSONArray = new JSONArray();
        for (String str : split) {
            if (!com.anzhi.ad.coverscreen.c.c.a(str)) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("packageName", str);
                } catch (JSONException e) {
                }
                jSONArray.put(jSONObject);
            }
        }
        if (jSONArray.length() == 0) {
            a();
            return;
        }
        JSONObject a3 = com.anzhi.ad.coverscreen.c.a.a.a(this.d, "1.1", jSONArray);
        f fVar = new f();
        fVar.a(this.d, com.anzhi.ad.coverscreen.c.a.b.d(), this.c, a3.toString());
        fVar.a(new C0002c(this));
        d.a().a(fVar);
    }

    public static boolean isShowAtScreenOn() {
        return a;
    }

    public final void a() {
        if (!this.e) {
            Context context = this.d;
            a.b("调用开始加载广告方法");
            a(true);
        }
    }

    protected final void a(JSONArray jSONArray) {
        new Thread(new RunnableC0003d(this, jSONArray)).start();
    }

    protected final void a(boolean z) {
        if (z) {
            this.f = 10;
        } else {
            this.f--;
        }
        a.a("SLEEP_NUM: " + this.f + ",MAX_NUM: " + this.f + ",force: " + z);
        if (this.f < 0) {
            a.a("广告进入休眠状态");
        } else if (this.e) {
            a.a("组件已被释放，取消获取广告任务");
        } else if (z) {
            if (this.g != null) {
                this.g.cancel();
                Context context = this.d;
                a.b("取消原有获取广告任务");
            }
            c();
        } else if (this.g != null) {
            Context context2 = this.d;
            a.b("原有任务已存在，忽略此次调用");
        } else {
            this.g = new C0000a(this);
            this.h.schedule(this.g, 360000);
            Context context3 = this.d;
            a.b("提交执行获取广告定时任务");
        }
    }

    protected final boolean a(String str) {
        try {
            Intent intent = new Intent(this.d, SA.class);
            intent.addFlags(335544320);
            intent.putExtra("ads", str);
            this.d.startActivity(intent);
            return true;
        } catch (Exception e) {
            a.a("启动广告页面失败");
            return false;
        }
    }

    public void b() {
        this.e = true;
        if (this.g != null) {
            this.g.cancel();
            this.g = null;
            this.h.cancel();
        }
        this.d = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void c();

    protected final void d() {
        this.b = false;
        this.g = null;
    }

    protected final String e() {
        return this.c;
    }

    public final Context f() {
        return this.d;
    }
}
