package com.androidemu.harveshihun.input;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import com.androidemu.Emulator;
import com.androidemu.EmulatorView;
import com.androidemu.harveshihun.NetPlayService;
import com.androidemu.harveshihun.R;
import com.androidemu.harveshihun.wrapper.Wrapper;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class VirtualKeypad {
    private Context context;
    private boolean dpad4Way;
    private GameKeyListener gameKeyListener;
    private boolean inBetweenPress;
    private int keyStates;
    private int marginX;
    private int marginY;
    private Picture overlay;
    private float pointSizeThreshold;
    private float scaleX;
    private float scaleY;
    private Vibrator vibrator;
    private boolean vibratorEnabled;
    private View view;
    private static final int[] DPAD_4WAY = {Emulator.GAMEPAD_LEFT, Emulator.GAMEPAD_UP, Emulator.GAMEPAD_RIGHT, Emulator.GAMEPAD_DOWN};
    private static final int[] BUTTONS_4WAY = {Emulator.GAMEPAD_Y, 64, Emulator.GAMEPAD_A, Emulator.GAMEPAD_B};
    private static final float[] DPAD_DEADZONE_VALUES = {0.1f, 0.14f, 0.1667f, 0.2f, 0.25f};
    private float dpadDeadZone = DPAD_DEADZONE_VALUES[2];
    private ArrayList<Control> controls = new ArrayList<>();
    private Control[] touchedControls = new Control[4];
    private Emulator emulator = Emulator.getInstance();
    private Control dpad = createControl(R.drawable.dpad);
    private Control buttons = createControl(R.drawable.buttons);
    private Control selectStart = createControl(R.drawable.select_start_buttons);
    private Control leftShoulder = createControl(R.drawable.tl_button_top);
    private Control rightShoulder = createControl(R.drawable.tr_button_top);

    public VirtualKeypad(View v, GameKeyListener l) {
        this.view = v;
        this.context = this.view.getContext();
        this.gameKeyListener = l;
        this.vibrator = (Vibrator) this.context.getSystemService("vibrator");
    }

    public final int getKeyStates() {
        return this.keyStates;
    }

    public void reset() {
        this.keyStates = 0;
        for (int i = 0; i < this.touchedControls.length; i++) {
            this.touchedControls[i] = null;
        }
    }

    public final void destroy() {
        this.emulator.setOverlay(null);
    }

    public final void resize(int w, int h) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.context);
        this.vibratorEnabled = prefs.getBoolean("enableVibrator", true);
        this.dpad4Way = prefs.getBoolean("dpad4Way", false);
        int value = prefs.getInt("dpadDeadZone", 2);
        if (value < 0) {
            value = 0;
        } else if (value > 4) {
            value = 4;
        }
        this.dpadDeadZone = DPAD_DEADZONE_VALUES[value];
        this.inBetweenPress = prefs.getBoolean("inBetweenPress", false);
        this.pointSizeThreshold = 1.0f;
        if (prefs.getBoolean("pointSizePress", false)) {
            this.pointSizeThreshold = (((float) prefs.getInt("pointSizePressThreshold", 7)) / 10.0f) - 0.01f;
        }
        this.dpad.hide(prefs.getBoolean("hideDpad", false));
        this.buttons.hide(prefs.getBoolean("hideButtons", false));
        this.selectStart.hide(prefs.getBoolean("hideSelectStart", false));
        this.leftShoulder.hide(prefs.getBoolean("hideShoulders", false));
        this.rightShoulder.hide(prefs.getBoolean("hideShoulders", false));
        this.scaleX = ((float) w) / ((float) this.view.getWidth());
        this.scaleY = ((float) h) / ((float) this.view.getHeight());
        int margin = prefs.getInt("layoutMargin", 2) * 10;
        this.marginX = (int) (((float) margin) * this.scaleX);
        this.marginY = (int) (((float) margin) * this.scaleY);
        float controlScale = getControlScale(prefs);
        float sx = this.scaleX * controlScale;
        float sy = this.scaleY * controlScale;
        Resources res = this.context.getResources();
        Iterator<Control> it = this.controls.iterator();
        while (it.hasNext()) {
            it.next().load(res, sx, sy);
        }
        reposition(w, h, prefs);
        this.overlay = new Picture();
        draw(this.overlay.beginRecording(w, h), prefs);
        this.emulator.setOverlay(this.overlay);
    }

    private static float getControlScale(SharedPreferences prefs) {
        String value = prefs.getString("vkeypadSize", null);
        if ("small".equals(value)) {
            return 1.0f;
        }
        if ("large".equals(value)) {
            return 1.33333f;
        }
        return 1.2f;
    }

    private Control createControl(int resId) {
        Control c = new Control(resId);
        this.controls.add(c);
        return c;
    }

    private void makeBottomBottom(Rect bounds) {
        if (this.dpad.getWidth() + this.buttons.getWidth() > bounds.width()) {
            makeBottomTop(bounds);
            return;
        }
        this.dpad.move((float) bounds.left, (float) (bounds.bottom - this.dpad.getHeight()));
        this.buttons.move((float) (bounds.right - this.buttons.getWidth()), (float) (bounds.bottom - this.buttons.getHeight()));
        this.leftShoulder.move((float) bounds.left, (float) bounds.top);
        this.rightShoulder.move((float) (bounds.right - this.rightShoulder.getWidth()), (float) bounds.top);
        int x = bounds.centerX() - (((this.buttons.getWidth() + this.selectStart.getWidth()) - this.dpad.getWidth()) / 2);
        if (x > this.dpad.getWidth() + bounds.left) {
            this.selectStart.move((float) x, (float) (bounds.bottom - this.selectStart.getHeight()));
            return;
        }
        this.selectStart.move((float) (bounds.centerX() - (this.selectStart.getWidth() / 2)), (float) bounds.top);
    }

    private void makeTopTop(Rect bounds) {
        if (this.dpad.getWidth() + this.buttons.getWidth() > bounds.width()) {
            makeBottomTop(bounds);
            return;
        }
        this.dpad.move((float) bounds.left, (float) bounds.top);
        this.buttons.move((float) (bounds.right - this.buttons.getWidth()), (float) bounds.top);
        this.leftShoulder.reload(this.context.getResources(), R.drawable.tl_button_bottom);
        this.rightShoulder.reload(this.context.getResources(), R.drawable.tr_button_bottom);
        this.leftShoulder.move((float) bounds.left, (float) (bounds.bottom - this.leftShoulder.getHeight()));
        this.rightShoulder.move((float) (bounds.right - this.rightShoulder.getWidth()), (float) (bounds.bottom - this.rightShoulder.getHeight()));
        this.selectStart.move((float) (bounds.centerX() - (this.selectStart.getWidth() / 2)), (float) (bounds.bottom - this.selectStart.getHeight()));
    }

    private void makeTopBottom(Rect bounds) {
        this.dpad.move((float) bounds.left, (float) bounds.top);
        this.buttons.move((float) (bounds.right - this.buttons.getWidth()), (float) (bounds.bottom - this.buttons.getHeight()));
        this.leftShoulder.reload(this.context.getResources(), R.drawable.tl_button_bottom);
        this.leftShoulder.move((float) bounds.left, (float) (bounds.bottom - this.leftShoulder.getHeight()));
        this.rightShoulder.move((float) (bounds.right - this.rightShoulder.getWidth()), (float) bounds.top);
        int x = bounds.centerX() - (((this.buttons.getWidth() + this.selectStart.getWidth()) - this.leftShoulder.getWidth()) / 2);
        if (x > this.leftShoulder.getWidth() + bounds.left) {
            this.selectStart.move((float) x, (float) (bounds.bottom - this.selectStart.getHeight()));
            return;
        }
        this.selectStart.move((float) (bounds.centerX() - ((this.selectStart.getWidth() - this.dpad.getWidth()) / 2)), (float) (bounds.top + this.rightShoulder.getHeight()));
    }

    private void makeBottomTop(Rect bounds) {
        this.dpad.move((float) bounds.left, (float) (bounds.bottom - this.dpad.getHeight()));
        this.buttons.move((float) (bounds.right - this.buttons.getWidth()), (float) bounds.top);
        this.rightShoulder.reload(this.context.getResources(), R.drawable.tr_button_bottom);
        this.leftShoulder.move((float) bounds.left, (float) bounds.top);
        this.rightShoulder.move((float) (bounds.right - this.rightShoulder.getWidth()), (float) (bounds.bottom - this.rightShoulder.getHeight()));
        int x = bounds.centerX() - (((this.selectStart.getWidth() + this.rightShoulder.getWidth()) - this.dpad.getWidth()) / 2);
        if (x > this.dpad.getWidth() + bounds.left) {
            this.selectStart.move((float) x, (float) (bounds.bottom - this.selectStart.getHeight()));
            return;
        }
        this.selectStart.move((float) (bounds.centerX() - ((this.selectStart.getWidth() + this.buttons.getWidth()) / 2)), (float) (bounds.top + this.leftShoulder.getHeight()));
    }

    private void reposition(int w, int h, SharedPreferences prefs) {
        Rect bounds = new Rect();
        if (this.view.getWidth() > this.view.getHeight()) {
            bounds.left = this.marginX;
            bounds.right = w - this.marginX;
            bounds.top = 0;
            bounds.bottom = h;
        } else {
            bounds.left = 0;
            bounds.right = w;
            bounds.top = this.marginY;
            bounds.bottom = h - this.marginY;
        }
        String layout = prefs.getString("vkeypadLayout", "top_bottom");
        if ("top_bottom".equals(layout)) {
            makeBottomBottom(bounds);
        } else if ("bottom_top".equals(layout)) {
            makeBottomBottom(bounds);
        } else if ("top_top".equals(layout)) {
            makeBottomBottom(bounds);
        } else {
            makeBottomBottom(bounds);
        }
    }

    private void draw(Canvas canvas, SharedPreferences prefs) {
        int transparency = prefs.getInt("vkeypadTransparency", 50);
        Paint paint = new Paint();
        paint.setAlpha((transparency * 2) + 30);
        Iterator<Control> it = this.controls.iterator();
        while (it.hasNext()) {
            it.next().draw(canvas, paint);
        }
    }

    private boolean shouldVibrate(int oldStates, int newStates) {
        return ((oldStates ^ newStates) & newStates) != 0;
    }

    private void setKeyStates(int newStates) {
        if (this.keyStates != newStates) {
            if (this.vibratorEnabled && shouldVibrate(this.keyStates, newStates)) {
                this.vibrator.vibrate(33);
            }
            this.keyStates = newStates;
            this.gameKeyListener.onGameKeyChanged();
        }
    }

    private int get4WayDirection(float x, float y) {
        float x2 = x - 0.5f;
        float y2 = y - 0.5f;
        return Math.abs(x2) >= Math.abs(y2) ? x2 < 0.0f ? 0 : 2 : y2 < 0.0f ? 1 : 3;
    }

    private int getDpadStates(float x, float y) {
        if (this.dpad4Way) {
            return DPAD_4WAY[get4WayDirection(x, y)];
        }
        int states = 0;
        if (x < 0.5f - this.dpadDeadZone) {
            states = 0 | Emulator.GAMEPAD_LEFT;
        } else if (x > this.dpadDeadZone + 0.5f) {
            states = 0 | Emulator.GAMEPAD_RIGHT;
        }
        if (y < 0.5f - this.dpadDeadZone) {
            states |= Emulator.GAMEPAD_UP;
        } else if (y > this.dpadDeadZone + 0.5f) {
            states |= Emulator.GAMEPAD_DOWN;
        }
        return states;
    }

    private static boolean isInBetweenPress(float x, float y) {
        return ((int) (x * 3.0f)) + ((int) (3.0f * y)) == 2;
    }

    private int getButtonsStates(float x, float y, float size) {
        int states = BUTTONS_4WAY[get4WayDirection(x, y)];
        if (size <= this.pointSizeThreshold && (!this.inBetweenPress || !isInBetweenPress(x, y))) {
            return states;
        }
        switch (states) {
            case Emulator.GAMEPAD_X:
            case Emulator.GAMEPAD_A:
                return 192;
            case Emulator.GAMEPAD_Y:
            case Emulator.GAMEPAD_B:
                return 49152;
            default:
                return states;
        }
    }

    private int getSelectStartStates(float x, float y) {
        return x < 0.5f ? Emulator.GAMEPAD_SELECT : Emulator.GAMEPAD_START;
    }

    private float getEventX(MotionEvent event, int index, boolean flip) {
        float x = Wrapper.MotionEvent_getX(event, index);
        if (flip) {
            x = ((float) this.view.getWidth()) - x;
        }
        return this.scaleX * x;
    }

    private float getEventY(MotionEvent event, int index, boolean flip) {
        float y = Wrapper.MotionEvent_getY(event, index);
        if (flip) {
            y = ((float) this.view.getHeight()) - y;
        }
        return this.scaleY * y;
    }

    private Control findControl(float x, float y) {
        Control hit = null;
        int min = 99999;
        Iterator<Control> it = this.controls.iterator();
        while (it.hasNext()) {
            Control c = it.next();
            int distance = c.hitTest(x, y);
            if (distance <= 0) {
                return c;
            }
            if (min > distance && c != this.selectStart) {
                min = distance;
                hit = c;
            }
        }
        return hit;
    }

    private int getControlStates(Control c, float x, float y, float size) {
        float x2 = (x - c.getX()) / ((float) c.getWidth());
        float y2 = (y - c.getY()) / ((float) c.getHeight());
        if (c == this.dpad) {
            return getDpadStates(x2, y2);
        }
        if (c == this.buttons) {
            return getButtonsStates(x2, y2, size);
        }
        if (c == this.selectStart) {
            return getSelectStartStates(x2, y2);
        }
        if (c == this.leftShoulder) {
            return 32;
        }
        if (c == this.rightShoulder) {
            return 16;
        }
        return 0;
    }

    public boolean onTouch(MotionEvent event, boolean flip) {
        if (this.dpad.bitmap == null) {
            return false;
        }
        int action = event.getAction();
        int actionCode = action & 255;
        switch (actionCode) {
            case EmulatorView.SCALING_ORIGINAL:
            case NetPlayService.MESSAGE_SAVED_STATE:
            case 6:
                int index = (65280 & action) >> 8;
                int id = Wrapper.MotionEvent_getPointerId(event, index);
                if (id < this.touchedControls.length) {
                    if (actionCode == 6) {
                        this.touchedControls[id] = null;
                        break;
                    } else {
                        this.touchedControls[id] = findControl(getEventX(event, index, flip), getEventY(event, index, flip));
                        break;
                    }
                }
                break;
            case 1:
            case 3:
                for (int i = 0; i < this.touchedControls.length; i++) {
                    this.touchedControls[i] = null;
                }
                break;
            case 2:
            case 4:
                break;
            default:
                return false;
        }
        int states = 0;
        int n = Wrapper.MotionEvent_getPointerCount(event);
        for (int i2 = 0; i2 < n; i2++) {
            int id2 = Wrapper.MotionEvent_getPointerId(event, i2);
            if (id2 < this.touchedControls.length && this.touchedControls[id2] != null) {
                states |= getControlStates(this.touchedControls[id2], getEventX(event, i2, flip), getEventY(event, i2, flip), Wrapper.MotionEvent_getSize(event, i2));
            }
        }
        setKeyStates(states);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Control {
        private Bitmap bitmap;
        private RectF bounds = new RectF();
        private boolean disabled;
        private boolean hidden;
        private int resId;

        Control(int r) {
            this.resId = r;
        }

        final float getX() {
            return this.bounds.left;
        }

        final float getY() {
            return this.bounds.top;
        }

        final int getWidth() {
            return this.bitmap.getWidth();
        }

        final int getHeight() {
            return this.bitmap.getHeight();
        }

        final void hide(boolean b) {
            this.hidden = b;
        }

        final int hitTest(float x, float y) {
            if (this.disabled) {
                return 99999;
            }
            float dx = 0.0f;
            float dy = 0.0f;
            if (x < this.bounds.left) {
                dx = this.bounds.left - x;
            } else if (x > this.bounds.right) {
                dx = x - this.bounds.right;
            }
            if (y < this.bounds.top) {
                dy = this.bounds.top - y;
            } else if (y > this.bounds.bottom) {
                dy = y - this.bounds.bottom;
            }
            return (int) (dx + dy);
        }

        final void move(float x, float y) {
            this.bounds.set(x, y, ((float) this.bitmap.getWidth()) + x, ((float) this.bitmap.getHeight()) + y);
        }

        final void load(Resources res, float sx, float sy) {
            this.bitmap = ((BitmapDrawable) res.getDrawable(this.resId)).getBitmap();
            this.bitmap = Bitmap.createScaledBitmap(this.bitmap, (int) (((float) this.bitmap.getWidth()) * sx), (int) (((float) this.bitmap.getHeight()) * sy), true);
        }

        final void reload(Resources res, int id) {
            int w = this.bitmap.getWidth();
            int h = this.bitmap.getHeight();
            this.bitmap = ((BitmapDrawable) res.getDrawable(id)).getBitmap();
            this.bitmap = Bitmap.createScaledBitmap(this.bitmap, w, h, true);
        }

        final void draw(Canvas canvas, Paint paint) {
            if (!this.hidden && !this.disabled) {
                canvas.drawBitmap(this.bitmap, this.bounds.left, this.bounds.top, paint);
            }
        }
    }
}
