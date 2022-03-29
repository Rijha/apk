package com.anzhi.ad.coverscreen.c.d;

import android.content.Context;
/* loaded from: classes.dex */
public final class c {
    public static int a(Context context, String str, String str2, int i) {
        return context.getSharedPreferences(str, 0).getInt(str2, i);
    }

    public static String a(Context context, String str, String str2) {
        return a(context, str, str2, "0");
    }

    public static String a(Context context, String str, String str2, String str3) {
        return context.getSharedPreferences(str, 0).getString(str2, str3);
    }

    public static boolean a(Context context, String str, String str2, boolean z) {
        return context.getSharedPreferences(str, 0).getBoolean(str2, z);
    }

    public static void b(Context context, String str, String str2) {
        context.getSharedPreferences(str, 0).edit().remove(str2).commit();
    }

    public static void b(Context context, String str, String str2, int i) {
        context.getSharedPreferences(str, 0).edit().putInt(str2, i).commit();
    }

    public static void b(Context context, String str, String str2, String str3) {
        context.getSharedPreferences(str, 0).edit().putString(str2, str3).commit();
    }

    public static void b(Context context, String str, String str2, boolean z) {
        context.getSharedPreferences(str, 0).edit().putBoolean(str2, z).commit();
    }
}
