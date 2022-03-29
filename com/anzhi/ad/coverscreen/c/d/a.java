package com.anzhi.ad.coverscreen.c.d;

import android.content.Context;
import android.os.Environment;
import java.io.File;
/* loaded from: classes.dex */
public final class a {
    private static a a;

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public static String a(Context context, String str, String str2) {
        String stringBuffer;
        a();
        String replace = str2.replace(":", "_").replace("/", "_");
        a();
        if (b()) {
            a();
            if (!b()) {
                stringBuffer = null;
            } else {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(Environment.getExternalStorageDirectory());
                if (!str.startsWith("/")) {
                    stringBuffer2.append("/");
                }
                stringBuffer2.append(str);
                File file = new File(stringBuffer2.toString());
                if (!file.exists()) {
                    com.anzhi.ad.coverscreen.c.g.a.a("创建文件夹: " + ((Object) stringBuffer2) + ", 是否成功：" + file.mkdirs());
                }
                stringBuffer = stringBuffer2.toString();
            }
            return String.valueOf(stringBuffer) + "/" + replace;
        }
        a();
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append(context.getFilesDir().getAbsolutePath());
        if (!"".startsWith("/")) {
            stringBuffer3.append("/");
        }
        stringBuffer3.append("");
        File file2 = new File(stringBuffer3.toString());
        if (!file2.exists()) {
            file2.mkdirs();
        }
        return String.valueOf(stringBuffer3.toString()) + "/" + replace;
    }

    public static boolean b() {
        return Environment.getExternalStorageState().equals("mounted");
    }
}
