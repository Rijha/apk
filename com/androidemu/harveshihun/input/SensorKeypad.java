package com.androidemu.harveshihun.input;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
/* loaded from: classes.dex */
public class SensorKeypad implements SensorEventListener {
    public static final int DOWN = 8;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    private static final float[] THRESHOLD_VALUES = {30.0f, 20.0f, 15.0f, 10.0f, 8.0f, 6.0f, 5.0f, 3.0f, 2.0f, 1.0f};
    public static final int UP = 4;
    private Context context;
    private GameKeyListener gameKeyListener;
    private int keyStates;
    private float threshold = THRESHOLD_VALUES[7];

    public SensorKeypad(Context ctx) {
        this.context = ctx;
    }

    public final int getKeyStates() {
        return this.keyStates;
    }

    public final void setSensitivity(int value) {
        if (value < 0) {
            value = 0;
        } else if (value > 9) {
            value = 9;
        }
        this.threshold = THRESHOLD_VALUES[value];
    }

    public final void setGameKeyListener(GameKeyListener l) {
        if (this.gameKeyListener != l) {
            SensorManager sensorManager = (SensorManager) this.context.getSystemService("sensor");
            if (this.gameKeyListener != null) {
                sensorManager.unregisterListener(this);
            }
            this.gameKeyListener = l;
            if (this.gameKeyListener != null) {
                sensorManager.registerListener(this, sensorManager.getDefaultSensor(3), 1);
            }
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent event) {
        float leftRight;
        if (this.context.getResources().getConfiguration().orientation == 2) {
            leftRight = -event.values[1];
        } else {
            leftRight = -event.values[2];
        }
        int states = 0;
        if (leftRight < (-this.threshold)) {
            states = 0 | 1;
        } else if (leftRight > this.threshold) {
            states = 0 | 2;
        }
        if (states != this.keyStates) {
            this.keyStates = states;
            if (this.gameKeyListener != null) {
                this.gameKeyListener.onGameKeyChanged();
            }
        }
    }
}
