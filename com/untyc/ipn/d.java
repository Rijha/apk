package com.untyc.ipn;

import android.content.Context;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class d extends k {
    private Object a;
    private Method b;
    private Method c;

    /* JADX INFO: Access modifiers changed from: protected */
    public d(Object obj) {
        if (obj == null) {
            throw new Exception();
        }
        this.a = obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Context context) {
        if (this.b == null) {
            this.b = a(this.a, "init");
        }
        a(this.a, this.b, new Object[]{context});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(boolean z) {
        if (this.c == null) {
            this.c = a(this.a, "KU");
        }
        a(this.a, this.c, new Object[]{Boolean.valueOf(z)});
    }
}
