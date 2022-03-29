package com.anzhi.ad.coverscreen.c.j;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.telephony.TelephonyManager;
import com.anzhi.ad.coverscreen.c.c;
/* loaded from: classes.dex */
public final class b {
    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static int b(Context context) {
        boolean z = true;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return 0;
        }
        String typeName = activeNetworkInfo.getTypeName();
        if (typeName.equalsIgnoreCase("WIFI")) {
            return 4;
        }
        if (!typeName.equalsIgnoreCase("MOBILE")) {
            return 0;
        }
        if (!c.a(Proxy.getDefaultHost())) {
            return 1;
        }
        switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
            case 1:
                z = false;
                break;
            case 2:
                z = false;
                break;
            case 4:
                z = false;
                break;
            case 7:
                z = false;
                break;
            case 11:
                z = false;
                break;
        }
        return z ? 3 : 2;
    }
}
