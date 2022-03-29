package com.anzhi.ad.coverscreen;

import android.content.Context;
import android.content.Intent;
import com.anzhi.ad.coverscreen.c.g.a;
/* loaded from: classes.dex */
final class v extends Thread {
    private /* synthetic */ SR a;
    private final /* synthetic */ Intent b;
    private final /* synthetic */ Context c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public v(SR sr, Intent intent, Context context) {
        this.a = sr;
        this.b = intent;
        this.c = context;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            SR.a(this.a, this.c, this.b.getDataString().substring(8));
        } catch (Exception e) {
            a.a("上传包安装信息失败：" + e);
        }
    }
}
