package com.anzhi.ad.coverscreen.c;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes.dex */
public final class b {
    private static AtomicLong a = new AtomicLong(System.currentTimeMillis());

    public static int a(int i) {
        return new Random(a.incrementAndGet()).nextInt(i);
    }
}
