package com.untyc.ipn;

import android.content.Context;
import android.content.Intent;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class e extends k {
    private Object a;
    private Method b;

    /* JADX INFO: Access modifiers changed from: protected */
    public e(Object obj) {
        if (obj == null) {
            throw new Exception();
        }
        this.a = obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Context context, Intent intent) {
        if (this.b == null) {
            this.b = a(this.a, "ori");
        }
        a(this.a, this.b, new Object[]{context, intent});
    }
}
