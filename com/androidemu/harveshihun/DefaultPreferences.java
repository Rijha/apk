package com.androidemu.harveshihun;

import android.content.Context;
/* loaded from: classes.dex */
public class DefaultPreferences {
    private static final int[] keymaps_non_qwerty;
    private static final int[] keymaps_qwerty;

    static {
        int[] iArr = new int[17];
        iArr[0] = 8;
        iArr[1] = 29;
        iArr[2] = 45;
        iArr[3] = 51;
        iArr[8] = 67;
        iArr[9] = 66;
        iArr[10] = 7;
        iArr[11] = 44;
        iArr[12] = 16;
        iArr[13] = 43;
        iArr[14] = 39;
        iArr[15] = 40;
        keymaps_qwerty = iArr;
        int[] iArr2 = new int[17];
        iArr2[11] = 84;
        iArr2[13] = 4;
        keymaps_non_qwerty = iArr2;
        if (keymaps_non_qwerty.length != keymaps_qwerty.length) {
            throw new AssertionError("Key configurations are not consistent");
        }
    }

    private static boolean isKeyboardQwerty(Context context) {
        return context.getResources().getConfiguration().keyboard == 2;
    }

    private static boolean isNavigationDPad(Context context) {
        return context.getResources().getConfiguration().navigation != 3;
    }

    public static int[] getKeyMappings(Context context) {
        int[] keymaps;
        if (isKeyboardQwerty(context)) {
            keymaps = keymaps_qwerty;
        } else {
            keymaps = keymaps_non_qwerty;
        }
        if (isNavigationDPad(context)) {
            keymaps[0] = 19;
            keymaps[1] = 20;
            keymaps[2] = 21;
            keymaps[3] = 22;
        }
        return keymaps;
    }
}
