package com.androidemu;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.media.AudioTrack;
import android.view.SurfaceHolder;
import java.nio.Buffer;
/* loaded from: classes.dex */
public class EmuMedia {
    private static Bitmap bitmap;
    private static boolean firstBlt;
    private static SurfaceHolder holder;
    private static Picture overlay;
    private static AudioTrack track;
    private static Rect region = new Rect();
    private static Rect dirty = new Rect();
    private static float volume = AudioTrack.getMaxVolume();

    static void destroy() {
        overlay = null;
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
        if (track != null) {
            track.stop();
            track = null;
        }
    }

    static void setSurface(SurfaceHolder h) {
        holder = h;
    }

    static Bitmap setSurfaceRegion(int x, int y, int w, int h) {
        firstBlt = true;
        region.set(x, y, x + w, y + h);
        if (!(bitmap != null && bitmap.getWidth() == w && bitmap.getHeight() == h)) {
            if (bitmap != null) {
                bitmap.recycle();
            }
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        }
        return bitmap;
    }

    static void setOverlay(Picture pic) {
        overlay = pic;
    }

    static void bitBlt(Buffer src, boolean flip) {
        Canvas canvas;
        if (src != null) {
            bitmap.copyPixelsFromBuffer(src);
        }
        if (firstBlt) {
            firstBlt = false;
            canvas = holder.lockCanvas();
            canvas.drawColor(-16777216);
        } else {
            dirty.set(region);
            canvas = holder.lockCanvas(dirty);
        }
        if (flip) {
            canvas.rotate(180.0f, (float) (canvas.getWidth() / 2), (float) (canvas.getHeight() / 2));
        }
        canvas.drawBitmap(bitmap, (float) region.left, (float) region.top, (Paint) null);
        if (overlay != null) {
            overlay.draw(canvas);
        }
        holder.unlockCanvasAndPost(canvas);
    }

    static boolean audioCreate(int rate, int bits, int channels) {
        int format;
        int channelConfig;
        if (bits == 16) {
            format = 2;
        } else {
            format = 3;
        }
        if (channels == 2) {
            channelConfig = 3;
        } else {
            channelConfig = 2;
        }
        if (track != null && track.getSampleRate() == rate && track.getAudioFormat() == format && track.getChannelCount() == channels) {
            return true;
        }
        int minBufferSize = ((((bits / 8) * rate) * channels) / 60) * 2;
        int bufferSize = AudioTrack.getMinBufferSize(rate, channelConfig, format);
        if (bufferSize < minBufferSize) {
            bufferSize = minBufferSize;
        }
        try {
            track = new AudioTrack(3, rate, channelConfig, format, bufferSize, 1);
            if (track.getState() == 0) {
                track = null;
            }
        } catch (IllegalArgumentException e) {
            track = null;
        }
        if (track == null) {
            return false;
        }
        track.setStereoVolume(volume, volume);
        return true;
    }

    static void audioSetVolume(int vol) {
        float min = AudioTrack.getMinVolume();
        volume = (((AudioTrack.getMaxVolume() - min) * ((float) vol)) / 100.0f) + min;
        if (track != null) {
            track.setStereoVolume(volume, volume);
        }
    }

    static void audioDestroy() {
        if (track != null) {
            track.stop();
            track = null;
        }
    }

    static void audioStart() {
        if (track != null) {
            track.play();
        }
    }

    static void audioStop() {
        if (track != null) {
            track.stop();
            track.flush();
        }
    }

    static void audioPause() {
        if (track != null) {
            track.pause();
        }
    }

    static void audioPlay(byte[] data, int size) {
        if (track != null) {
            track.write(data, 0, size);
        }
    }
}
