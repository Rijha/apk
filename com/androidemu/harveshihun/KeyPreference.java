package com.androidemu.harveshihun;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.KeyEvent;
import com.androidemu.Emulator;
import com.androidemu.EmulatorView;
import com.androidemu.harveshihun.input.SensorKeypad;
/* loaded from: classes.dex */
public class KeyPreference extends DialogPreference implements DialogInterface.OnKeyListener {
    private int newValue;
    private int oldValue;
    private Resources resources;

    public KeyPreference(Context context) {
        this(context, null);
    }

    public KeyPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.resources = context.getResources();
        setPositiveButtonText(R.string.key_clear);
        setDefaultValue(0);
    }

    public final int getKeyValue() {
        return this.newValue;
    }

    public final void setKey(int key) {
        this.newValue = key;
        this.oldValue = key;
        updateSummary();
    }

    @Override // android.preference.DialogPreference
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        super.onPrepareDialogBuilder(builder);
        builder.setMessage(R.string.press_key_prompt).setOnKeyListener(this);
    }

    @Override // android.preference.DialogPreference
    protected void showDialog(Bundle state) {
        super.showDialog(state);
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().clearFlags(131072);
        }
    }

    @Override // android.preference.DialogPreference, android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        if (which == -1) {
            this.newValue = 0;
        }
        super.onClick(dialog, which);
    }

    @Override // android.preference.DialogPreference
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        if (!positiveResult) {
            this.newValue = this.oldValue;
            return;
        }
        this.oldValue = this.newValue;
        persistInt(this.newValue);
        updateSummary();
    }

    @Override // android.preference.Preference
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return Integer.valueOf(a.getInteger(index, 0));
    }

    @Override // android.preference.Preference
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        this.oldValue = restoreValue ? getPersistedInt(0) : ((Integer) defaultValue).intValue();
        this.newValue = this.oldValue;
        updateSummary();
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (!isKeyConfigurable(keyCode)) {
            return false;
        }
        this.newValue = keyCode;
        super.onClick(dialog, -1);
        dialog.dismiss();
        return true;
    }

    private void updateSummary() {
        setSummary(getKeyName(this.newValue));
    }

    private static boolean isKeyConfigurable(int keyCode) {
        switch (keyCode) {
            case 3:
            case 26:
            case 82:
                return false;
            default:
                return true;
        }
    }

    private String getKeyName(int keyCode) {
        switch (keyCode) {
            case EmulatorView.SCALING_ORIGINAL:
                return this.resources.getString(R.string.key_none);
            case 1:
            case 2:
            case 3:
            case 6:
            case 17:
            case 18:
            case 26:
            case 28:
            case 61:
            case 63:
            case Emulator.GAMEPAD_X:
            case 65:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 78:
            case 79:
            case 81:
            case 82:
            case 83:
            default:
                return this.resources.getString(R.string.key_unknown);
            case 4:
                return "BACK";
            case NetPlayService.MESSAGE_SAVED_STATE:
                return "CALL";
            case 7:
                return "0";
            case SensorKeypad.DOWN:
                return "1";
            case 9:
                return "2";
            case 10:
                return "3";
            case 11:
                return "4";
            case 12:
                return "5";
            case 13:
                return "6";
            case 14:
                return "7";
            case 15:
                return "8";
            case Emulator.GAMEPAD_TR:
                return "9";
            case 19:
                return "DPAD Up";
            case 20:
                return "DPAD Down";
            case 21:
                return "DPAD Left";
            case 22:
                return "DPAD Right";
            case 23:
                return "DPAD Center";
            case 24:
                return "Volume UP";
            case 25:
                return "Volume DOWN";
            case 27:
                return "CAMERA";
            case 29:
                return "A";
            case 30:
                return "B";
            case 31:
                return "C";
            case Emulator.GAMEPAD_TL:
                return "D";
            case 33:
                return "E";
            case 34:
                return "F";
            case 35:
                return "G";
            case 36:
                return "H";
            case 37:
                return "I";
            case 38:
                return "J";
            case 39:
                return "K";
            case 40:
                return "L";
            case 41:
                return "M";
            case 42:
                return "N";
            case 43:
                return "O";
            case 44:
                return "P";
            case 45:
                return "Q";
            case 46:
                return "R";
            case 47:
                return "S";
            case 48:
                return "T";
            case 49:
                return "U";
            case 50:
                return "V";
            case 51:
                return "W";
            case 52:
                return "X";
            case 53:
                return "Y";
            case 54:
                return "Z";
            case 55:
                return ",";
            case 56:
                return ".";
            case 57:
                return "ALT (left)";
            case 58:
                return "ALT (right)";
            case 59:
                return "SHIFT (left)";
            case 60:
                return "SHIFT (right)";
            case 62:
                return "SPACE";
            case 66:
                return "ENTER";
            case 67:
                return "DEL";
            case 77:
                return "@";
            case 80:
                return "FOCUS";
            case 84:
                return "SEARCH";
        }
    }
}
