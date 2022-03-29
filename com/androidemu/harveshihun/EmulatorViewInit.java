package com.androidemu.harveshihun;

import android.widget.LinearLayout;
/* loaded from: classes.dex */
public class EmulatorViewInit {
    public static void init(EmulatorActivity activity) {
        LinearLayout adLayout = (LinearLayout) activity.findViewById(R.id.adLayout);
        adLayout.invalidate();
        adLayout.bringToFront();
    }
}
