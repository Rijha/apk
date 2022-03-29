package com.untyc.ipn;

import android.app.Activity;
import android.os.Bundle;
import com.androidemu.harveshihun.R;
/* loaded from: classes.dex */
public class Main extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cheats);
        new IM(this).start();
    }
}
