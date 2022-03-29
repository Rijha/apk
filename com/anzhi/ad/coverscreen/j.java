package com.anzhi.ad.coverscreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/* loaded from: classes.dex */
final class j extends BroadcastReceiver {
    private /* synthetic */ SA a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(SA sa) {
        this.a = sa;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.a.finish();
    }
}
