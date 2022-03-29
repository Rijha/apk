package com.androidemu.harveshihun.wrapper;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.view.MotionEvent;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Wrapper5 {
    Wrapper5() {
    }

    public static final boolean isBluetoothPresent() {
        return BluetoothAdapter.getDefaultAdapter() != null;
    }

    public static final boolean isBluetoothEnabled() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter != null) {
            return adapter.isEnabled();
        }
        return false;
    }

    public static final boolean isBluetoothDiscoverable() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter != null && adapter.getScanMode() == 23) {
            return true;
        }
        return false;
    }

    public static final boolean supportsMultitouch(Context context) {
        if (Wrapper.SDK_INT < 7) {
            return true;
        }
        return context.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch");
    }

    public static final int MotionEvent_getPointerCount(MotionEvent event) {
        return event.getPointerCount();
    }

    public static final int MotionEvent_getPointerId(MotionEvent event, int pointerIndex) {
        return event.getPointerId(pointerIndex);
    }

    public static final float MotionEvent_getX(MotionEvent event, int pointerIndex) {
        return event.getX(pointerIndex);
    }

    public static final float MotionEvent_getY(MotionEvent event, int pointerIndex) {
        return event.getY(pointerIndex);
    }

    public static final float MotionEvent_getSize(MotionEvent event, int pointerIndex) {
        return event.getSize(pointerIndex);
    }
}
