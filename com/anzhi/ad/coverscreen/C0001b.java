package com.anzhi.ad.coverscreen;

import com.anzhi.ad.coverscreen.c.d.c;
import com.anzhi.ad.coverscreen.c.g.a;
import com.anzhi.ad.coverscreen.c.j.e;
/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.anzhi.ad.coverscreen.b  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0001b implements e {
    private /* synthetic */ AdComponent a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0001b(AdComponent adComponent) {
        this.a = adComponent;
    }

    @Override // com.anzhi.ad.coverscreen.c.j.e
    public final void a(boolean z, String str) {
        a.a("服务器返回（uploadClientInfo）：" + str);
        c.b(this.a.d, "coverscreen", "isFirstRun", false);
        this.a.a();
    }
}
