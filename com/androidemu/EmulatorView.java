package com.androidemu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
/* loaded from: classes.dex */
public class EmulatorView extends SurfaceView {
    public static final int SCALING_2X = 1;
    public static final int SCALING_ORIGINAL = 0;
    public static final int SCALING_PROPORTIONAL = 2;
    public static final int SCALING_STRETCH = 3;
    private int actualHeight;
    private int actualWidth;
    private float aspectRatio;
    private OnTrackballListener onTrackballListener;
    private int scalingMode = 2;

    /* loaded from: classes.dex */
    public interface OnTrackballListener {
        boolean onTrackball(MotionEvent motionEvent);
    }

    public EmulatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SurfaceHolder holder = getHolder();
        holder.setFormat(4);
        holder.setKeepScreenOn(true);
        setFocusableInTouchMode(true);
    }

    public void setOnTrackballListener(OnTrackballListener l) {
        this.onTrackballListener = l;
    }

    public void setActualSize(int w, int h) {
        if (this.actualWidth != w || this.actualHeight != h) {
            this.actualWidth = w;
            this.actualHeight = h;
            updateSurfaceSize();
        }
    }

    public void setScalingMode(int mode) {
        if (this.scalingMode != mode) {
            this.scalingMode = mode;
            updateSurfaceSize();
        }
    }

    public void setAspectRatio(float ratio) {
        if (this.aspectRatio != ratio) {
            this.aspectRatio = ratio;
            updateSurfaceSize();
        }
    }

    private void updateSurfaceSize() {
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        if (viewWidth != 0 && viewHeight != 0 && this.actualWidth != 0 && this.actualHeight != 0) {
            int w = 0;
            int h = 0;
            if (!(this.scalingMode == 3 || this.aspectRatio == 0.0f)) {
                viewWidth = (int) (((float) viewWidth) / ((this.aspectRatio * ((float) this.actualHeight)) / ((float) this.actualWidth)));
            }
            switch (this.scalingMode) {
                case SCALING_ORIGINAL /* 0 */:
                    w = viewWidth;
                    h = viewHeight;
                    break;
                case 1:
                    w = viewWidth / 2;
                    h = viewHeight / 2;
                    break;
                case 3:
                    if (this.actualHeight * viewWidth >= this.actualWidth * viewHeight) {
                        w = this.actualWidth;
                        h = this.actualHeight;
                        break;
                    }
                    break;
            }
            if (w < this.actualWidth || h < this.actualHeight) {
                h = this.actualHeight;
                w = (h * viewWidth) / viewHeight;
                if (w < this.actualWidth) {
                    w = this.actualWidth;
                    h = (w * viewHeight) / viewWidth;
                }
            }
            getHolder().setFixedSize((w + 3) & -4, (h + 3) & -4);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        updateSurfaceSize();
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent event) {
        if (this.onTrackballListener == null || !this.onTrackballListener.onTrackball(event)) {
            return super.onTrackballEvent(event);
        }
        return true;
    }
}
