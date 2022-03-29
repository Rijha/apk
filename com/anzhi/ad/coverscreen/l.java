package com.anzhi.ad.coverscreen;

import android.content.DialogInterface;
import com.anzhi.ad.coverscreen.b.a;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class l implements DialogInterface.OnClickListener {
    private /* synthetic */ SA a;
    private final /* synthetic */ a b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(SA sa, a aVar) {
        this.a = sa;
        this.b = aVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.c(this.b);
        if (this.a.b.isEmpty()) {
            this.a.finish();
            return;
        }
        this.a.a(false);
        this.a.c();
    }
}
