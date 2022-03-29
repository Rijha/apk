package com.anzhi.ad.coverscreen;

import com.anzhi.ad.coverscreen.b.a;
import com.anzhi.ad.coverscreen.c.c.c;
import java.util.Date;
/* loaded from: classes.dex */
public final class n implements c {
    final /* synthetic */ SA a;
    private final /* synthetic */ a b;

    public n(SA sa, a aVar) {
        this.a = sa;
        this.b = aVar;
    }

    @Override // com.anzhi.ad.coverscreen.c.c.c
    public final void a(int i) {
        this.a.a.post(new o(this, this.b, i));
    }

    @Override // com.anzhi.ad.coverscreen.c.c.c
    public final void a(int i, String str) {
        com.anzhi.ad.coverscreen.c.d.c.b(this.a.getApplicationContext(), "DP_COVER_FILE", this.b.e().replace(".", ""), String.valueOf(this.b.a()) + "," + new Date().getTime());
        try {
            com.anzhi.ad.coverscreen.c.a.a();
            com.anzhi.ad.coverscreen.c.a.a(this.a.getApplicationContext(), str);
        } catch (Exception e) {
            this.a.getApplicationContext();
            com.anzhi.ad.coverscreen.c.g.a.b("start install apk error: " + e);
        }
        if (i > 0) {
            this.a.a.post(new p(this, str, this.b));
        }
    }
}
