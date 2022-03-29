package com.tabca.dp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/* loaded from: classes.dex */
public class se extends BroadcastReceiver {
    static String b = c.b;
    d a;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            this.a = d.a(context, b);
            this.a.a("onReceive", new Object[]{context, intent}, new Class[]{Context.class, Intent.class});
        } catch (Exception e) {
        }
    }
}
