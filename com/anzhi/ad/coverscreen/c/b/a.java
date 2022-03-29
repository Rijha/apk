package com.anzhi.ad.coverscreen.c.b;

import java.text.SimpleDateFormat;
import java.util.Date;
/* loaded from: classes.dex */
public final class a {
    public static String a(Date date, String str) {
        try {
            return new SimpleDateFormat(str).format(date);
        } catch (Exception e) {
            return null;
        }
    }
}
