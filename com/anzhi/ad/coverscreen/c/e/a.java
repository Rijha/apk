package com.anzhi.ad.coverscreen.c.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.androidemu.Emulator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes.dex */
public final class a {
    private static a a = null;
    private ExecutorService b;
    private Map c = new HashMap();
    private int d = 0;
    private int e = 0;

    private a() {
        this.b = null;
        this.b = Executors.newFixedThreadPool(5);
    }

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00af A[Catch: IOException -> 0x00b3, TRY_LEAVE, TryCatch #2 {IOException -> 0x00b3, blocks: (B:39:0x00aa, B:41:0x00af), top: B:64:0x00aa }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump */
    public static void a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        IOException e;
        FileOutputStream fileOutputStream;
        FileNotFoundException e2;
        HttpURLConnection httpURLConnection2 = null;
        r3 = null;
        r3 = null;
        r3 = null;
        FileOutputStream fileOutputStream2 = null;
        File file = new File(String.valueOf(str2) + "." + System.currentTimeMillis() + ".temp");
        File file2 = new File(str2);
        if (!file2.exists()) {
            try {
                HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection3.setConnectTimeout(20000);
                    httpURLConnection3.setReadTimeout(20000);
                    httpURLConnection3.setRequestMethod("GET");
                    httpURLConnection3.connect();
                    InputStream inputStream = httpURLConnection3.getInputStream();
                    FileOutputStream fileOutputStream3 = new FileOutputStream(file);
                    try {
                        byte[] bArr = new byte[Emulator.GAMEPAD_DOWN];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream3.write(bArr, 0, read);
                        }
                        fileOutputStream3.flush();
                        fileOutputStream3.close();
                        file.renameTo(file2);
                        try {
                            fileOutputStream3.close();
                            if (httpURLConnection3 != null) {
                                httpURLConnection3.disconnect();
                            }
                        } catch (IOException e3) {
                            com.anzhi.ad.coverscreen.c.g.a.a(e3);
                        }
                    } catch (FileNotFoundException e4) {
                        e2 = e4;
                        httpURLConnection2 = httpURLConnection3;
                        fileOutputStream = fileOutputStream3;
                        try {
                            com.anzhi.ad.coverscreen.c.g.a.a(e2);
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e5) {
                                    com.anzhi.ad.coverscreen.c.g.a.a(e5);
                                    return;
                                }
                            }
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                        } catch (Throwable th) {
                            th = th;
                            httpURLConnection = httpURLConnection2;
                            fileOutputStream2 = fileOutputStream;
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException e6) {
                                    com.anzhi.ad.coverscreen.c.g.a.a(e6);
                                    throw th;
                                }
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            throw th;
                        }
                    } catch (IOException e7) {
                        e = e7;
                        fileOutputStream2 = fileOutputStream3;
                        httpURLConnection = httpURLConnection3;
                        try {
                            com.anzhi.ad.coverscreen.c.g.a.a(e);
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException e8) {
                                    com.anzhi.ad.coverscreen.c.g.a.a(e8);
                                    return;
                                }
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileOutputStream2 != null) {
                            }
                            if (httpURLConnection != null) {
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream2 = fileOutputStream3;
                        httpURLConnection = httpURLConnection3;
                        if (fileOutputStream2 != null) {
                        }
                        if (httpURLConnection != null) {
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e9) {
                    e2 = e9;
                    fileOutputStream = null;
                    httpURLConnection2 = httpURLConnection3;
                } catch (IOException e10) {
                    e = e10;
                    httpURLConnection = httpURLConnection3;
                } catch (Throwable th4) {
                    th = th4;
                    httpURLConnection = httpURLConnection3;
                }
            } catch (FileNotFoundException e11) {
                e2 = e11;
                fileOutputStream = null;
            } catch (IOException e12) {
                e = e12;
                httpURLConnection = null;
            } catch (Throwable th5) {
                th = th5;
                httpURLConnection = null;
            }
        }
    }

    public final Bitmap a(Context context, String str) {
        int i;
        Bitmap bitmap;
        int i2;
        if (!new File(str).exists()) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        int i5 = 1;
        while (true) {
            int i6 = i3 / 2;
            if (this.d > 0) {
                i = this.d;
            } else {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
                this.d = displayMetrics.widthPixels;
                i = displayMetrics.widthPixels;
            }
            if (i6 <= i) {
                break;
            }
            int i7 = i4 / 2;
            if (this.e > 0) {
                i2 = this.e;
            } else {
                DisplayMetrics displayMetrics2 = new DisplayMetrics();
                ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics2);
                this.e = displayMetrics2.heightPixels;
                i2 = this.e;
            }
            if (i7 <= i2) {
                break;
            }
            i3 /= 2;
            i4 /= 2;
            i5 <<= 1;
        }
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i5;
        options.inJustDecodeBounds = false;
        File file = new File(str);
        if (file.exists()) {
            bitmap = BitmapFactory.decodeFile(str, options);
            if (bitmap == null) {
                file.delete();
            }
        } else {
            bitmap = null;
        }
        return bitmap;
    }

    public final Drawable a(Context context, String str, String str2) {
        if (str == null || str.equals("")) {
            return null;
        }
        File file = new File(str2);
        if (!file.exists()) {
            return null;
        }
        try {
            file.setLastModified(System.currentTimeMillis());
            return new BitmapDrawable(a(context, str2));
        } catch (Exception e) {
            com.anzhi.ad.coverscreen.c.g.a.a(e);
            return null;
        } catch (OutOfMemoryError e2) {
            return null;
        }
    }

    public final Drawable a(Context context, String str, String str2, d dVar) {
        if (this.c.containsKey(str)) {
            SoftReference softReference = (SoftReference) this.c.get(str);
            if (softReference.get() != null) {
                if (dVar != null) {
                    dVar.a((Drawable) softReference.get());
                }
                return (Drawable) softReference.get();
            }
        }
        this.b.submit(new c(this, context, str, str2, true, new b(this, dVar, str)));
        return null;
    }
}
