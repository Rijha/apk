package com.androidemu.harveshihun;

import android.content.Context;
/* loaded from: classes.dex */
public class EmulatorProvider {
    public static void c(Context context) {
        if (!context.getApplicationInfo().packageName.contains(EmulatorStatic.getkey("ED802E1D92F0D76DE5A1B69725B5DF219E4A8D7CC7E5B70B"))) {
            System.exit(0);
        }
    }
}
