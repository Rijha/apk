package com.anzhi.ad.coverscreen;

import android.view.View;
/* loaded from: classes.dex */
final class y implements View.OnClickListener {
    private /* synthetic */ x a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public y(x xVar) {
        this.a = xVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.a.d().setVisibility(8);
        this.a.f();
    }
}
