package com.anzhi.ad.coverscreen.c.e;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b extends Handler {
    private final /* synthetic */ d a;
    private final /* synthetic */ String b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(a aVar, d dVar, String str) {
        this.a = dVar;
        this.b = str;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        if (message.obj != null && this.a != null) {
            String str = this.b;
            this.a.a((Drawable) message.obj);
        }
    }
}
