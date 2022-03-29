package com.anzhi.ad.coverscreen;

import android.graphics.drawable.Drawable;
import com.anzhi.ad.coverscreen.b.a;
import com.anzhi.ad.coverscreen.c.e.d;
import java.util.Vector;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class s implements d {
    private final /* synthetic */ a a;
    private final /* synthetic */ Vector b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(SA sa, a aVar, Vector vector) {
        this.a = aVar;
        this.b = vector;
    }

    @Override // com.anzhi.ad.coverscreen.c.e.d
    public final void a(Drawable drawable) {
        if (drawable != null) {
            com.anzhi.ad.coverscreen.c.g.a.a("添加到广告展示列表：" + this.a.c());
            this.b.add(this.a);
        }
    }
}
