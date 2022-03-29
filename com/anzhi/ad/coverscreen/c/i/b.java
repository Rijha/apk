package com.anzhi.ad.coverscreen.c.i;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.anzhi.ad.coverscreen.c.c;
import com.anzhi.ad.coverscreen.c.i.a.a;
/* loaded from: classes.dex */
public final class b {
    public static String a() {
        try {
            return Build.MANUFACTURER;
        } catch (Exception e) {
            return "";
        }
    }

    public static String a(Context context) {
        String str;
        try {
            str = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            str = null;
        }
        if (c.a(str)) {
            str = b(context);
        }
        if (!c.a(str)) {
            return str;
        }
        String string = context.getSharedPreferences("SAVE_IMEI", 0).getString("UDID", null);
        if (!c.a(string)) {
            return string;
        }
        String str2 = String.valueOf(System.currentTimeMillis()) + c.a(3);
        context.getSharedPreferences("SAVE_IMEI", 0).edit().putString("UDID", str2).commit();
        return str2;
    }

    public static String b() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (Exception e) {
            return null;
        }
    }

    public static String c() {
        try {
            return Build.VERSION.SDK;
        } catch (Exception e) {
            return "";
        }
    }

    public static String c(Context context) {
        try {
            return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getBSSID();
        } catch (Exception e) {
            return null;
        }
    }

    public static a d(Context context) {
        a aVar = new a();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        aVar.a(displayMetrics.widthPixels);
        aVar.b(displayMetrics.heightPixels);
        return aVar;
    }

    public static String d() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Exception e) {
            return "";
        }
    }
}
