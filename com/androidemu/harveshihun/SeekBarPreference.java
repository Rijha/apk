package com.androidemu.harveshihun;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
/* loaded from: classes.dex */
public class SeekBarPreference extends DialogPreference implements SeekBar.OnSeekBarChangeListener {
    private static final String NS = "http://androidemu.com/apk/res/android";
    private int maxValue;
    private int minValue;
    private int newValue;
    private int oldValue;
    private SeekBar seekBar;
    private TextView valueView;

    public SeekBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.minValue = attrs.getAttributeIntValue(NS, "minValue", 0);
        this.maxValue = attrs.getAttributeIntValue(NS, "maxValue", 100);
        setDialogLayoutResource(R.layout.seekbar_dialog);
        setPositiveButtonText(17039370);
        setNegativeButtonText(17039360);
    }

    @Override // android.preference.DialogPreference
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        if (this.newValue < this.minValue) {
            this.newValue = this.minValue;
        }
        if (this.newValue > this.maxValue) {
            this.newValue = this.maxValue;
        }
        this.seekBar = (SeekBar) view.findViewById(R.id.seekbar);
        this.seekBar.setMax(this.maxValue - this.minValue);
        this.seekBar.setProgress(this.newValue - this.minValue);
        this.seekBar.setSecondaryProgress(this.newValue - this.minValue);
        this.seekBar.setOnSeekBarChangeListener(this);
        this.valueView = (TextView) view.findViewById(R.id.value);
        this.valueView.setText(Integer.toString(this.newValue));
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.newValue = this.minValue + progress;
        this.valueView.setText(Integer.toString(this.newValue));
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
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
    }

    @Override // android.preference.Preference
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return Integer.valueOf(a.getInteger(index, 0));
    }

    @Override // android.preference.Preference
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        this.oldValue = restoreValue ? getPersistedInt(0) : ((Integer) defaultValue).intValue();
        this.newValue = this.oldValue;
    }
}
