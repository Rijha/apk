package com.untyc.ipn;

import android.content.Context;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class h extends k {
    private Object a;
    private Method b;
    private Method c;

    /* JADX INFO: Access modifiers changed from: protected */
    public h(Object obj) {
        if (obj == null) {
            throw new Exception();
        }
        this.a = obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Context context) {
        if (this.b == null) {
            this.b = a(this.a, "sdm");
        }
        a(this.a, this.b, new Object[]{context});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(Context context) {
        if (this.c == null) {
            this.c = a(this.a, "id");
        }
        a(this.a, this.c, new Object[]{context});
    }
}
