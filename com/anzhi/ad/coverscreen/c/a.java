package com.anzhi.ad.coverscreen.c;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.io.File;
/* loaded from: classes.dex */
public final class a {
    private static a a = null;

    private a() {
    }

    public static Intent a(String str) {
        if (c.a(str)) {
            return null;
        }
        File file = new File(str);
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        return intent;
    }

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public static void a(Context context, String str) {
        if (!c.a(str)) {
            File file = new File(str);
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setAction("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }
}
