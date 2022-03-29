package com.anzhi.ad.coverscreen.c.i;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.anzhi.ad.coverscreen.c.c;
import java.util.List;
/* loaded from: classes.dex */
public final class a {
    private static a a;

    private a() {
    }

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public static String a(Context context, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = installedPackages.get(i);
            if ((packageInfo.applicationInfo.flags & 1) == 0) {
                stringBuffer.append(",");
                stringBuffer.append(packageInfo.packageName);
            }
        }
        return stringBuffer.toString().substring(1);
    }

    public static boolean a(Context context, String str) {
        if (c.a(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
