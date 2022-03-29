package com.anzhi.ad.coverscreen;

import android.view.View;
import com.anzhi.ad.coverscreen.c.g.a;
/* loaded from: classes.dex */
final class z implements View.OnClickListener {
    private /* synthetic */ x a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public z(x xVar) {
        this.a = xVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        a.a("Ad View Click: " + view);
        this.a.c.a(this.a.b);
    }
}
