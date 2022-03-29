package com.anzhi.ad.coverscreen.c.c;

import android.content.Context;
import com.androidemu.Emulator;
import com.anzhi.ad.coverscreen.c.d.a;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/* loaded from: classes.dex */
public final class b implements Runnable {
    private int e;
    private c j;
    private int a = 0;
    private int b = -1;
    private int c = 0;
    private int d = 0;
    private String f = null;
    private String g = null;
    private Context h = null;
    private URL i = null;

    public final void a(int i) {
        this.e = i;
    }

    public final void a(Context context, String str, String str2) {
        this.h = context;
        this.f = str2;
        this.g = str;
        try {
            this.i = new URL(str);
        } catch (MalformedURLException e) {
        }
    }

    public final void a(c cVar) {
        this.j = cVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01cb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01d0 A[Catch: IOException -> 0x01dc, TryCatch #28 {IOException -> 0x01dc, blocks: (B:89:0x01cb, B:91:0x01d0, B:93:0x01d5), top: B:146:0x01cb }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01d5 A[Catch: IOException -> 0x01dc, TRY_LEAVE, TryCatch #28 {IOException -> 0x01dc, blocks: (B:89:0x01cb, B:91:0x01d0, B:93:0x01d5), top: B:146:0x01cb }] */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r1v45 */
    /* JADX WARN: Type inference failed for: r1v75, types: [com.anzhi.ad.coverscreen.c.c.c] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v25, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v28, types: [int] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // java.lang.Runnable
    /* Code decompiled incorrectly, please refer to instructions dump */
    public final void run() {
        ?? r3;
        FileOutputStream fileOutputStream;
        InputStream inputStream;
        FileNotFoundException e;
        InputStream inputStream2;
        FileOutputStream fileOutputStream2;
        Exception e2;
        InputStream inputStream3;
        FileOutputStream fileOutputStream3;
        InterruptedException e3;
        InputStream inputStream4;
        FileOutputStream fileOutputStream4;
        IOException e4;
        HttpURLConnection httpURLConnection = null;
        if (this.i == null) {
            throw new IllegalArgumentException("URL must be init!");
        }
        this.a = 0;
        this.b = -1;
        this.d = 0;
        File file = new File(this.f);
        FileOutputStream exists = file.exists();
        if (exists == 0) {
            file.mkdirs();
        }
        try {
            String str = String.valueOf(this.f) + this.g.substring(this.g.lastIndexOf("/") + 1);
            File file2 = new File(str);
            byte[] bArr = new byte[Emulator.GAMEPAD_DOWN];
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) this.i.openConnection();
                try {
                    httpURLConnection2.setConnectTimeout(20000);
                    httpURLConnection2.setReadTimeout(20000);
                    httpURLConnection2.connect();
                    this.c = httpURLConnection2.getContentLength();
                    System.out.println("DownloadFile > " + this.c + " == " + file2.length());
                    if (((long) this.c) == file2.length()) {
                        exists = this.j;
                        r3 = this.e;
                        exists.a(r3, str);
                        if (httpURLConnection2 != null) {
                            try {
                                httpURLConnection2.disconnect();
                            } catch (IOException e5) {
                            }
                        }
                    } else {
                        file2.delete();
                        r3 = httpURLConnection2.getInputStream();
                        try {
                            a.a();
                            FileOutputStream fileOutputStream5 = a.b() ? new FileOutputStream(file2) : this.h.openFileOutput(this.g.substring(this.g.lastIndexOf("/") + 1), 1);
                            try {
                                this.j.a(0);
                                Thread.sleep(10);
                                while (true) {
                                    int read = r3.read(bArr);
                                    this.b = read;
                                    if (read == -1) {
                                        break;
                                    }
                                    fileOutputStream5.write(bArr, 0, this.b);
                                    this.a += this.b;
                                    if (this.a < this.c && (this.a * 100) / this.c > this.d) {
                                        this.d = (this.a * 100) / this.c;
                                        if (this.d < 100) {
                                            this.j.a(this.d);
                                        }
                                    }
                                }
                                if (this.a == this.c) {
                                    this.j.a(this.e, str);
                                }
                                fileOutputStream5.flush();
                                fileOutputStream5.close();
                                if (fileOutputStream5 != null) {
                                    try {
                                        fileOutputStream5.close();
                                    } catch (IOException e6) {
                                        return;
                                    }
                                }
                                if (r3 != 0) {
                                    r3.close();
                                }
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                }
                            } catch (FileNotFoundException e7) {
                                e = e7;
                                inputStream = r3;
                                fileOutputStream = fileOutputStream5;
                                httpURLConnection = httpURLConnection2;
                                try {
                                    Context context = this.h;
                                    com.anzhi.ad.coverscreen.c.g.a.b(e.getMessage());
                                    if (fileOutputStream != null) {
                                        try {
                                            fileOutputStream.close();
                                        } catch (IOException e8) {
                                            return;
                                        }
                                    }
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    exists = fileOutputStream;
                                    r3 = inputStream;
                                    if (exists != 0) {
                                        try {
                                            exists.close();
                                        } catch (IOException e9) {
                                            throw th;
                                        }
                                    }
                                    if (r3 != 0) {
                                        r3.close();
                                    }
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    throw th;
                                }
                            } catch (IOException e10) {
                                e4 = e10;
                                fileOutputStream4 = fileOutputStream5;
                                httpURLConnection = httpURLConnection2;
                                inputStream4 = r3;
                                Context context2 = this.h;
                                com.anzhi.ad.coverscreen.c.g.a.b(e4.getMessage());
                                if (fileOutputStream4 != null) {
                                    try {
                                        fileOutputStream4.close();
                                    } catch (IOException e11) {
                                        return;
                                    }
                                }
                                if (inputStream4 != null) {
                                    inputStream4.close();
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                            } catch (InterruptedException e12) {
                                e3 = e12;
                                fileOutputStream3 = fileOutputStream5;
                                httpURLConnection = httpURLConnection2;
                                inputStream3 = r3;
                                Context context3 = this.h;
                                com.anzhi.ad.coverscreen.c.g.a.b(e3.getMessage());
                                if (fileOutputStream3 != null) {
                                    try {
                                        fileOutputStream3.close();
                                    } catch (IOException e13) {
                                        return;
                                    }
                                }
                                if (inputStream3 != null) {
                                    inputStream3.close();
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                            } catch (Exception e14) {
                                e2 = e14;
                                fileOutputStream2 = fileOutputStream5;
                                httpURLConnection = httpURLConnection2;
                                inputStream2 = r3;
                                Context context4 = this.h;
                                com.anzhi.ad.coverscreen.c.g.a.b(e2.getMessage());
                                if (fileOutputStream2 != null) {
                                    try {
                                        fileOutputStream2.close();
                                    } catch (IOException e15) {
                                        return;
                                    }
                                }
                                if (inputStream2 != null) {
                                    inputStream2.close();
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                exists = fileOutputStream5;
                                httpURLConnection = httpURLConnection2;
                                if (exists != 0) {
                                }
                                if (r3 != 0) {
                                }
                                if (httpURLConnection != null) {
                                }
                                throw th;
                            }
                        } catch (FileNotFoundException e16) {
                            e = e16;
                            inputStream = r3;
                            fileOutputStream = null;
                            httpURLConnection = httpURLConnection2;
                        } catch (IOException e17) {
                            e4 = e17;
                            fileOutputStream4 = null;
                            httpURLConnection = httpURLConnection2;
                            inputStream4 = r3;
                        } catch (InterruptedException e18) {
                            e3 = e18;
                            fileOutputStream3 = null;
                            httpURLConnection = httpURLConnection2;
                            inputStream3 = r3;
                        } catch (Exception e19) {
                            e2 = e19;
                            fileOutputStream2 = null;
                            httpURLConnection = httpURLConnection2;
                            inputStream2 = r3;
                        } catch (Throwable th3) {
                            th = th3;
                            exists = 0;
                            httpURLConnection = httpURLConnection2;
                        }
                    }
                } catch (FileNotFoundException e20) {
                    e = e20;
                    fileOutputStream = null;
                    httpURLConnection = httpURLConnection2;
                    inputStream = null;
                } catch (IOException e21) {
                    e4 = e21;
                    inputStream4 = null;
                    fileOutputStream4 = null;
                    httpURLConnection = httpURLConnection2;
                } catch (InterruptedException e22) {
                    e3 = e22;
                    inputStream3 = null;
                    fileOutputStream3 = null;
                    httpURLConnection = httpURLConnection2;
                } catch (Exception e23) {
                    e2 = e23;
                    inputStream2 = null;
                    fileOutputStream2 = null;
                    httpURLConnection = httpURLConnection2;
                } catch (Throwable th4) {
                    th = th4;
                    r3 = 0;
                    exists = 0;
                    httpURLConnection = httpURLConnection2;
                }
            } catch (FileNotFoundException e24) {
                e = e24;
                inputStream = null;
                fileOutputStream = null;
            } catch (IOException e25) {
                e4 = e25;
                inputStream4 = null;
                fileOutputStream4 = null;
            } catch (InterruptedException e26) {
                e3 = e26;
                inputStream3 = null;
                fileOutputStream3 = null;
            } catch (Exception e27) {
                e2 = e27;
                inputStream2 = null;
                fileOutputStream2 = null;
            } catch (Throwable th5) {
                th = th5;
                r3 = 0;
                exists = 0;
            }
        } catch (Throwable th6) {
            th = th6;
        }
    }
}
