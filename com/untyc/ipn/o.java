package com.untyc.ipn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.androidemu.Emulator;
import com.androidemu.EmulatorView;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
/* loaded from: classes.dex */
public class o {
    public static String a;
    private static String b;
    private static String c;
    private static String d;
    private static String e;
    private static String f;
    private static String g;
    private static String h;
    private static String i;
    private static String j;
    private static String k;
    private static String l;
    private static String m;
    private static String n;
    private static String o;
    private static String p;
    private static String q;
    private static SharedPreferences r;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("s").append("i").append("l").append("t").append("i").append("m");
        b = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("c").append("a").append("k").append("e");
        c = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("/").append("l").append("i").append("b").append("/").append("c");
        d = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append("/").append("l").append("i").append("b").append("/").append("s");
        e = sb4.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append("s").append("s").append("p").append("n");
        f = sb5.toString();
        StringBuilder sb6 = new StringBuilder();
        sb6.append("i").append("i").append("d");
        g = sb6.toString();
        StringBuilder sb7 = new StringBuilder();
        sb7.append("s").append("v").append("i").append("o").append("n");
        h = sb7.toString();
        StringBuilder sb8 = new StringBuilder();
        sb8.append("i").append("a").append("p");
        i = sb8.toString();
        StringBuilder sb9 = new StringBuilder();
        sb9.append("r").append("l").append("a").append("p");
        j = sb9.toString();
        StringBuilder sb10 = new StringBuilder();
        sb10.append("r").append("s").append("a").append("p");
        k = sb10.toString();
        StringBuilder sb11 = new StringBuilder();
        sb11.append("r").append("e").append("p");
        l = sb11.toString();
        StringBuilder sb12 = new StringBuilder();
        sb12.append("n").append("s").append("p");
        m = sb12.toString();
        StringBuilder sb13 = new StringBuilder();
        sb13.append("b").append("s").append("p");
        n = sb13.toString();
        StringBuilder sb14 = new StringBuilder();
        sb14.append("c").append("j").append("p");
        o = sb14.toString();
        StringBuilder sb15 = new StringBuilder();
        sb15.append("b").append("j").append("p");
        p = sb15.toString();
        StringBuilder sb16 = new StringBuilder();
        sb16.append("c").append("t");
        a = sb16.toString();
        StringBuilder sb17 = new StringBuilder();
        sb17.append("a").append("c").append("t").append("i").append("o").append("n");
        q = sb17.toString();
    }

    public static SharedPreferences a(Context context) {
        if (r == null && context != null) {
            r = context.getSharedPreferences(f, 0);
        }
        return r;
    }

    public static void a(Context context, long j2) {
        a(context).edit().putLong(b, j2).commit();
    }

    public static void a(Context context, Intent intent) {
        switch (intent.getIntExtra(q, -1)) {
            case EmulatorView.SCALING_ORIGINAL:
                File o2 = f.o(context);
                File q2 = f.q(context);
                String a2 = g.a(o2.getAbsolutePath(), q2.getAbsolutePath());
                if (a2 == null || "".equals(a2)) {
                    q2.delete();
                } else {
                    q2.renameTo(f.m(context));
                }
                o2.delete();
                f.a(context, false);
                return;
            default:
                return;
        }
    }

    public static void a(Context context, String str) {
        a(context).edit().putString(c, str).commit();
    }

    protected static void a(Context context, boolean z) {
        try {
            f.d(context).a(z);
        } catch (Exception e2) {
        }
    }

    public static Intent b(Context context) {
        return new Intent(context, q.e(context));
    }

    public static void b(Context context, Intent intent) {
        switch (intent.getIntExtra(q, -1)) {
            case EmulatorView.SCALING_ORIGINAL:
                File r2 = f.r(context);
                File t = f.t(context);
                String a2 = g.a(r2.getAbsolutePath(), t.getAbsolutePath());
                if (a2 == null || "".equals(a2)) {
                    t.delete();
                } else {
                    t.renameTo(f.n(context));
                }
                r2.delete();
                f.b(context, false);
                return;
            default:
                return;
        }
    }

    protected static void b(Context context, boolean z) {
        try {
            f.h(context).a(z);
        } catch (Exception e2) {
        }
    }

    public static Intent c(Context context) {
        return new Intent(context, q.f(context));
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x00a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x009a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump */
    public static void c(Context context, boolean z) {
        BufferedInputStream bufferedInputStream;
        Exception e2;
        InputStream inputStream;
        BufferedInputStream bufferedInputStream2;
        InputStream inputStream2;
        BufferedOutputStream bufferedOutputStream = null;
        if (z) {
            f.m(context).deleteOnExit();
        } else if (f.k(context)) {
            return;
        }
        File p2 = f.p(context);
        File q2 = f.q(context);
        try {
            inputStream2 = IM.class.getResourceAsStream(d);
            try {
                p2.delete();
                bufferedInputStream2 = new BufferedInputStream(inputStream2);
                try {
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(p2));
                    try {
                        byte[] bArr = new byte[Emulator.GAMEPAD_START];
                        while (true) {
                            int read = bufferedInputStream2.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            bufferedOutputStream2.write(bArr, 0, read);
                        }
                        bufferedOutputStream2.flush();
                        String a2 = g.a(p2.getAbsolutePath(), q2.getAbsolutePath());
                        if (a2 == null || "".equals(a2)) {
                            q2.delete();
                        } else {
                            q2.renameTo(f.m(context));
                        }
                        f.a(context, true);
                        if (bufferedOutputStream2 != null) {
                            try {
                                bufferedOutputStream2.close();
                            } catch (Exception e3) {
                            }
                        }
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (Exception e4) {
                            }
                        }
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (Exception e5) {
                            }
                        }
                    } catch (Exception e6) {
                        e2 = e6;
                        bufferedOutputStream = bufferedOutputStream2;
                        bufferedInputStream = bufferedInputStream2;
                        inputStream = inputStream2;
                        try {
                            e2.printStackTrace();
                            if (bufferedOutputStream != null) {
                                try {
                                    bufferedOutputStream.close();
                                } catch (Exception e7) {
                                }
                            }
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Exception e8) {
                                }
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception e9) {
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            bufferedInputStream2 = bufferedInputStream;
                            inputStream2 = inputStream;
                            if (bufferedOutputStream != null) {
                                try {
                                    bufferedOutputStream.close();
                                } catch (Exception e10) {
                                }
                            }
                            if (bufferedInputStream2 != null) {
                                try {
                                    bufferedInputStream2.close();
                                } catch (Exception e11) {
                                }
                            }
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (Exception e12) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream = bufferedOutputStream2;
                        if (bufferedOutputStream != null) {
                        }
                        if (bufferedInputStream2 != null) {
                        }
                        if (inputStream2 != null) {
                        }
                        throw th;
                    }
                } catch (Exception e13) {
                    e2 = e13;
                    bufferedInputStream = bufferedInputStream2;
                    inputStream = inputStream2;
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Exception e14) {
                e2 = e14;
                bufferedInputStream = null;
                inputStream = inputStream2;
            } catch (Throwable th4) {
                th = th4;
                bufferedInputStream2 = null;
            }
        } catch (Exception e15) {
            e2 = e15;
            bufferedInputStream = null;
            inputStream = null;
        } catch (Throwable th5) {
            th = th5;
            bufferedInputStream2 = null;
            inputStream2 = null;
        }
    }

    public static Intent d(Context context) {
        Intent b2 = b(context);
        b2.putExtra(a, "0");
        return b2;
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x00a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x009a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump */
    public static void d(Context context, boolean z) {
        BufferedInputStream bufferedInputStream;
        Exception e2;
        InputStream inputStream;
        BufferedInputStream bufferedInputStream2;
        InputStream inputStream2;
        BufferedOutputStream bufferedOutputStream = null;
        if (z) {
            f.n(context).deleteOnExit();
        } else if (f.l(context)) {
            return;
        }
        File s = f.s(context);
        File t = f.t(context);
        try {
            inputStream2 = IM.class.getResourceAsStream(e);
            try {
                s.delete();
                bufferedInputStream2 = new BufferedInputStream(inputStream2);
                try {
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(s));
                    try {
                        byte[] bArr = new byte[Emulator.GAMEPAD_START];
                        while (true) {
                            int read = bufferedInputStream2.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            bufferedOutputStream2.write(bArr, 0, read);
                        }
                        bufferedOutputStream2.flush();
                        String a2 = g.a(s.getAbsolutePath(), t.getAbsolutePath());
                        if (a2 == null || "".equals(a2)) {
                            t.delete();
                        } else {
                            t.renameTo(f.n(context));
                        }
                        f.b(context, true);
                        if (bufferedOutputStream2 != null) {
                            try {
                                bufferedOutputStream2.close();
                            } catch (Exception e3) {
                            }
                        }
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (Exception e4) {
                            }
                        }
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (Exception e5) {
                            }
                        }
                    } catch (Exception e6) {
                        e2 = e6;
                        bufferedOutputStream = bufferedOutputStream2;
                        bufferedInputStream = bufferedInputStream2;
                        inputStream = inputStream2;
                        try {
                            e2.printStackTrace();
                            if (bufferedOutputStream != null) {
                                try {
                                    bufferedOutputStream.close();
                                } catch (Exception e7) {
                                }
                            }
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Exception e8) {
                                }
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception e9) {
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            bufferedInputStream2 = bufferedInputStream;
                            inputStream2 = inputStream;
                            if (bufferedOutputStream != null) {
                                try {
                                    bufferedOutputStream.close();
                                } catch (Exception e10) {
                                }
                            }
                            if (bufferedInputStream2 != null) {
                                try {
                                    bufferedInputStream2.close();
                                } catch (Exception e11) {
                                }
                            }
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (Exception e12) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream = bufferedOutputStream2;
                        if (bufferedOutputStream != null) {
                        }
                        if (bufferedInputStream2 != null) {
                        }
                        if (inputStream2 != null) {
                        }
                        throw th;
                    }
                } catch (Exception e13) {
                    e2 = e13;
                    bufferedInputStream = bufferedInputStream2;
                    inputStream = inputStream2;
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Exception e14) {
                e2 = e14;
                bufferedInputStream = null;
                inputStream = inputStream2;
            } catch (Throwable th4) {
                th = th4;
                bufferedInputStream2 = null;
            }
        } catch (Exception e15) {
            e2 = e15;
            bufferedInputStream = null;
            inputStream = null;
        } catch (Throwable th5) {
            th = th5;
            bufferedInputStream2 = null;
            inputStream2 = null;
        }
    }

    public static Intent e(Context context) {
        return c(context);
    }

    public static void f(Context context) {
        a(context, false);
    }

    public static void g(Context context) {
        b(context, false);
    }

    public static void h(Context context) {
        SharedPreferences a2 = a(context);
        if (!a2.getBoolean(g, false)) {
            a2.edit().putBoolean(g, true).putString(h, p.a).putString(i, q.a(context).getName()).putString(j, q.b(context).getName()).putString(k, q.c(context).getName()).putString(l, q.d(context).getName()).putString(m, q.e(context).getName()).putString(n, q.f(context).getName()).putString(o, f.m(context).getAbsolutePath()).putString(p, f.n(context).getAbsolutePath()).commit();
        }
    }

    public static void i(Context context) {
        c(context, false);
        d(context, false);
    }
}
