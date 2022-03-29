package com.androidemu.harveshihun;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
/* loaded from: classes.dex */
public class MediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {
    private MediaScannerConnection conn;
    private String filePath;
    private String mimeType;

    public MediaScanner(Context context) {
        this.conn = new MediaScannerConnection(context, this);
        this.conn.connect();
    }

    public void scanFile(String path, String mime) {
        if (this.conn.isConnected()) {
            this.conn.scanFile(path, mime);
            return;
        }
        this.filePath = path;
        this.mimeType = mime;
    }

    @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
    public void onMediaScannerConnected() {
        if (this.filePath != null) {
            this.conn.scanFile(this.filePath, this.mimeType);
        }
        this.filePath = null;
        this.mimeType = null;
    }

    @Override // android.media.MediaScannerConnection.OnScanCompletedListener
    public void onScanCompleted(String path, Uri uri) {
    }
}
