package com.tabca.dp;

import android.app.Activity;
import android.os.Bundle;
/* loaded from: classes.dex */
public class sa extends Activity {
    static String b = c.c;
    d a;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        this.a = d.a(this, b);
        this.a.a("setActivity", this, Activity.class);
        this.a.a("onCreate", bundle, Bundle.class);
    }
}
