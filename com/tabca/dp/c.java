package com.tabca.dp;

import java.io.IOException;
/* loaded from: classes.dex */
public final class c {
    static String a;
    static String b;
    static String c;
    static String d;
    static String e;

    static {
        a = "Y24ueHYucG8uTXlTZXJ2aWNl";
        b = "Y24ueHYucG8uTXlSZWNlaXZlcg==";
        c = "Y24ueHYucG8uTXlBY3Rpdml0eQ==";
        d = "Y24ueHYucG8uUE1hbmFnZXI=";
        e = "cmVjTWVz";
        try {
            a = new String(b.a(a));
            b = new String(b.a(b));
            c = new String(b.a(c));
            d = new String(b.a(d));
            e = new String(b.a(e));
        } catch (IOException e2) {
        }
    }
}
