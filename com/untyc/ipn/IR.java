package com.untyc.ipn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/* loaded from: classes.dex */
public class IR extends BroadcastReceiver {
    private String a() {
        return "com.cdib.b";
    }

    private void a(Context context, Intent intent) {
        try {
            f.b(context).a(context, intent);
        } catch (Exception e) {
        }
    }

    private String b() {
        return "com.do.ck";
    }

    private void b(Context context, Intent intent) {
        try {
            f.f(context).a(context, intent);
        } catch (Exception e) {
        }
    }

    private String c() {
        return "com.do.bk";
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        o.h(context);
        if (b().equals(action)) {
            o.a(context, intent);
        } else if (c().equals(action)) {
            o.b(context, intent);
        } else if (a().equals(action)) {
            b(context, intent);
        } else {
            a(context, intent);
            b(context, intent);
        }
    }
}
