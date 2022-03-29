package com.androidemu.harveshihun.input;

import android.view.KeyEvent;
import android.view.View;
import com.androidemu.Emulator;
/* loaded from: classes.dex */
public class Keyboard implements View.OnKeyListener {
    private GameKeyListener gameKeyListener;
    private int keyStates;
    private int[] keysMap = new int[Emulator.GAMEPAD_A];

    public Keyboard(View view, GameKeyListener listener) {
        this.gameKeyListener = listener;
        view.setOnKeyListener(this);
    }

    public final int getKeyStates() {
        return this.keyStates;
    }

    public void reset() {
        this.keyStates = 0;
    }

    public void clearKeyMap() {
        for (int i = 0; i < this.keysMap.length; i++) {
            this.keysMap[i] = 0;
        }
    }

    public void mapKey(int gameKey, int keyCode) {
        if (keyCode >= 0 && keyCode < this.keysMap.length) {
            int[] iArr = this.keysMap;
            iArr[keyCode] = iArr[keyCode] | gameKey;
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        int gameKey;
        if (keyCode < this.keysMap.length && (gameKey = this.keysMap[keyCode]) != 0) {
            if (event.getRepeatCount() == 0) {
                if (event.getAction() == 0) {
                    this.keyStates |= gameKey;
                } else {
                    this.keyStates &= gameKey ^ -1;
                }
                this.gameKeyListener.onGameKeyChanged();
            }
            return true;
        }
        return false;
    }
}
