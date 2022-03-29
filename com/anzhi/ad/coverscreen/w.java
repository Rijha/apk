package com.anzhi.ad.coverscreen;

import android.content.Context;
import com.anzhi.ad.coverscreen.c.d.c;
import com.anzhi.ad.coverscreen.c.g.a;
import com.anzhi.ad.coverscreen.c.j.e;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class w implements e {
    private final /* synthetic */ Context a;
    private final /* synthetic */ String b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public w(SR sr, Context context, String str) {
        this.a = context;
        this.b = str;
    }

    @Override // com.anzhi.ad.coverscreen.c.j.e
    public final void a(boolean z, String str) {
        a.a("服务器返回（sendAppInstall from ScreenReceiver）：" + str);
        if (z) {
            a.a("上传安装信息成功！");
            return;
        }
        a.a("上传安装信息失败：" + str);
        String a = c.a(this.a, "DP_COVER_FILE", "packnames", (String) null);
        if (a != null) {
            new StringBuilder(String.valueOf(a)).append(",").append(this.b);
        } else {
            String str2 = this.b;
        }
        c.b(this.a, "DP_COVER_FILE", "packnames", this.b);
    }
}
