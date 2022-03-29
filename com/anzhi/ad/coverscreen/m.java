package com.anzhi.ad.coverscreen;

import android.content.DialogInterface;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class m implements DialogInterface.OnClickListener {
    private /* synthetic */ SA a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(SA sa) {
        this.a = sa;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.a.b.isEmpty()) {
            this.a.finish();
            return;
        }
        this.a.a(false);
        this.a.c();
    }
}
