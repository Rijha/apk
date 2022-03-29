package com.androidemu;

import android.content.Context;
import android.graphics.Picture;
import android.os.Build;
import android.view.SurfaceHolder;
import java.io.IOException;
import java.nio.Buffer;
/* loaded from: classes.dex */
public class Emulator {
    public static final int GAMEPAD_A = 128;
    public static final int GAMEPAD_B = 32768;
    public static final int GAMEPAD_DOWN = 1024;
    public static final int GAMEPAD_DOWN_LEFT = 1536;
    public static final int GAMEPAD_DOWN_RIGHT = 1280;
    public static final int GAMEPAD_LEFT = 512;
    public static final int GAMEPAD_RIGHT = 256;
    public static final int GAMEPAD_SELECT = 8192;
    public static final int GAMEPAD_START = 4096;
    public static final int GAMEPAD_SUPERSCOPE_CURSOR = 4;
    public static final int GAMEPAD_SUPERSCOPE_PAUSE = 2;
    public static final int GAMEPAD_SUPERSCOPE_TURBO = 1;
    public static final int GAMEPAD_TL = 32;
    public static final int GAMEPAD_TR = 16;
    public static final int GAMEPAD_UP = 2048;
    public static final int GAMEPAD_UP_LEFT = 2560;
    public static final int GAMEPAD_UP_RIGHT = 2304;
    public static final int GAMEPAD_X = 64;
    public static final int GAMEPAD_Y = 16384;
    private static Emulator emulator;
    private static String engineLib;
    private Cheats cheats;
    private boolean cheatsEnabled;
    private String romFileName;
    private Thread thread;

    /* loaded from: classes.dex */
    public interface FrameUpdateListener {
        int onFrameUpdate(int i) throws IOException, InterruptedException;
    }

    /* loaded from: classes.dex */
    public interface VideoSizeChangeListener {
        void onVideoSizeChange(int i, int i2);
    }

    private native boolean initialize(String str, int i);

    private static native boolean loadEngine(String str, String str2);

    private native boolean nativeLoadROM(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeRun();

    private native void nativeUnloadROM();

    public native void fireLightGun(int i, int i2);

    public native int getOption(String str);

    public native void getScreenshot(Buffer buffer);

    public native int getVideoHeight();

    public native int getVideoWidth();

    public native boolean loadState(String str);

    public native void pause();

    public native void power();

    public native void processTrackball(int i, int i2, int i3, int i4);

    public native void reset();

    public native void resume();

    public native boolean saveState(String str);

    public native void setFrameUpdateListener(FrameUpdateListener frameUpdateListener);

    public native void setKeyStates(int i);

    public native void setOption(String str, String str2);

    public native void setOverlay(Picture picture);

    public native void setSurface(SurfaceHolder surfaceHolder);

    public native void setSurfaceRegion(int i, int i2, int i3, int i4);

    public native void setVideoSizeChangeListener(VideoSizeChangeListener videoSizeChangeListener);

    public static Emulator createInstance(Context context, String engine) {
        if (emulator == null) {
            System.loadLibrary("emu");
        }
        String libDir = "/data/data/" + context.getPackageName() + "/lib";
        if (!engine.equals(engineLib)) {
            engineLib = engine;
            loadEngine(libDir, engine);
        }
        if (emulator == null) {
            emulator = new Emulator(libDir);
        }
        return emulator;
    }

    public static Emulator getInstance() {
        return emulator;
    }

    public static boolean canNotUseHack() {
        return Build.MODEL.contains("Archos");
    }

    private Emulator(String libDir) {
        int sdk = Integer.parseInt(Build.VERSION.SDK);
        if (sdk < 5 && canNotUseHack()) {
            sdk = 5;
        }
        initialize(libDir, sdk);
        this.thread = new Thread() { // from class: com.androidemu.Emulator.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Emulator.this.nativeRun();
            }
        };
        this.thread.start();
    }

    private void interrupt() {
        this.thread.interrupt();
    }

    public final void enableCheats(boolean enable) {
        this.cheatsEnabled = enable;
        if (this.romFileName != null) {
            if (enable && this.cheats == null) {
                this.cheats = new Cheats(this.romFileName);
            } else if (!enable && this.cheats != null) {
                this.cheats.destroy();
                this.cheats = null;
            }
        }
    }

    public final Cheats getCheats() {
        return this.cheats;
    }

    public final boolean loadROM(String file) {
        if (!nativeLoadROM(file)) {
            return false;
        }
        this.romFileName = file;
        if (this.cheatsEnabled) {
            this.cheats = new Cheats(file);
        }
        return true;
    }

    public final void unloadROM() {
        nativeUnloadROM();
        this.cheats = null;
        this.romFileName = null;
    }

    public void setOption(String name, boolean value) {
        setOption(name, value ? "true" : "false");
    }

    public void setOption(String name, int value) {
        setOption(name, Integer.toString(value));
    }
}
