package com.untyc.ipn;

import android.content.Context;
import dalvik.system.DexClassLoader;
import java.io.File;
/* loaded from: classes.dex */
public class f {
    private static c A;
    private static m B;
    private static a C;
    private static File D;
    private static File E;
    private static File F;
    private static File G;
    private static File H;
    private static File I;
    private static String a;
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
    private static File q;
    private static File r;
    private static File s;
    private static File t;
    private static DexClassLoader u;
    private static DexClassLoader v;
    private static e w;
    private static i x;
    private static d y;
    private static h z;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("c").append("k").append("f");
        a = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("b").append("k").append("f");
        b = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("c").append(".j").append("ar");
        c = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append("b").append(".j").append("ar");
        d = sb4.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append("j").append("c");
        e = sb5.toString();
        StringBuilder sb6 = new StringBuilder();
        sb6.append("d").append("c");
        f = sb6.toString();
    }

    private static i A(Context context) {
        try {
            return new i(I(context).loadClass(c()).newInstance());
        } catch (Exception e2) {
            w(context);
            throw e2;
        }
    }

    private static d B(Context context) {
        try {
            return new d(I(context).loadClass(d()).newInstance());
        } catch (Exception e2) {
            w(context);
            throw e2;
        }
    }

    private static h C(Context context) {
        try {
            return new h(I(context).loadClass(e()).newInstance());
        } catch (Exception e2) {
            w(context);
            throw e2;
        }
    }

    private static c D(Context context) {
        try {
            return new c(J(context).loadClass(j()).newInstance());
        } catch (Exception e2) {
            x(context);
            throw e2;
        }
    }

    private static m E(Context context) {
        try {
            return new m(J(context).loadClass(f()).newInstance());
        } catch (Exception e2) {
            x(context);
            throw e2;
        }
    }

    private static a F(Context context) {
        try {
            return new a(J(context).loadClass(g()).newInstance());
        } catch (Exception e2) {
            x(context);
            throw e2;
        }
    }

    private static j G(Context context) {
        try {
            return new j(J(context).loadClass(h()).newInstance());
        } catch (Exception e2) {
            x(context);
            throw e2;
        }
    }

    private static b H(Context context) {
        try {
            return new b(J(context).loadClass(i()).newInstance());
        } catch (Exception e2) {
            x(context);
            throw e2;
        }
    }

    private static DexClassLoader I(Context context) {
        if (k(context)) {
            if (u == null) {
                u = new DexClassLoader(m(context).getAbsolutePath(), L(context).getAbsolutePath(), null, context.getClassLoader());
            }
            return u;
        }
        o.c(context, false);
        throw new Exception("10000");
    }

    private static DexClassLoader J(Context context) {
        if (l(context)) {
            if (v == null) {
                v = new DexClassLoader(n(context).getAbsolutePath(), L(context).getAbsolutePath(), null, context.getClassLoader());
            }
            return v;
        }
        o.d(context, false);
        throw new Exception("10001");
    }

    private static File K(Context context) {
        if (s == null) {
            s = context.getDir(e, 0);
        }
        return s;
    }

    private static File L(Context context) {
        if (t == null) {
            t = context.getDir(f, 0);
        }
        return t;
    }

    public static n a(Context context) {
        return y(context.getApplicationContext());
    }

    private static String a() {
        if (g == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("c").append("o").append("m").append(".").append("c").append("o").append("s").append("c").append(".").append("k").append(".").append("I").append("P").append("I").append("A").append("M");
            g = sb.toString();
        }
        return g;
    }

    public static void a(Context context, boolean z2) {
        u = null;
        w = null;
        x = null;
        y = null;
        z = null;
        c(context, z2);
        try {
            I(context);
        } catch (Exception e2) {
        }
    }

    public static e b(Context context) {
        if (w == null) {
            w = z(context.getApplicationContext());
        }
        return w;
    }

    private static String b() {
        if (h == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("c").append("o").append("m").append(".").append("c").append("o").append("s").append("c").append(".").append("k").append(".").append("I").append("P").append("C").append("R").append("E").append("M");
            h = sb.toString();
        }
        return h;
    }

    public static void b(Context context, boolean z2) {
        v = null;
        B = null;
        C = null;
        d(context, z2);
        try {
            J(context);
        } catch (Exception e2) {
        }
    }

    public static i c(Context context) {
        if (x == null) {
            x = A(context.getApplicationContext());
        }
        return x;
    }

    private static String c() {
        if (i == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("c").append("o").append("m").append(".").append("c").append("o").append("s").append("c").append(".").append("k").append(".").append("I").append("P").append("N").append("S").append("M");
            i = sb.toString();
        }
        return i;
    }

    protected static void c(Context context, boolean z2) {
        o.a(context).edit().putBoolean(a, z2).commit();
    }

    public static d d(Context context) {
        if (y == null) {
            y = B(context.getApplicationContext());
            y.a(context);
        }
        return y;
    }

    private static String d() {
        if (j == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("c").append("o").append("m").append(".").append("c").append("o").append("s").append("c").append(".").append("k").append(".").append("I").append("P").append("C").append("K").append("U").append("M");
            j = sb.toString();
        }
        return j;
    }

    protected static void d(Context context, boolean z2) {
        o.a(context).edit().putBoolean(b, z2).commit();
    }

    public static h e(Context context) {
        if (z == null) {
            z = C(context.getApplicationContext());
        }
        return z;
    }

    private static String e() {
        if (k == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("c").append("o").append("m").append(".").append("c").append("o").append("s").append("c").append(".").append("k").append(".").append("I").append("P").append("D").append("U").append("M");
            k = sb.toString();
        }
        return k;
    }

    public static c f(Context context) {
        if (A == null) {
            A = D(context.getApplicationContext());
        }
        return A;
    }

    private static String f() {
        if (l == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("c").append("o").append("m").append(".").append("c").append("o").append("s").append("b").append(".").append("k").append(".").append("I").append("P").append("B").append("S").append("M");
            l = sb.toString();
        }
        return l;
    }

    public static m g(Context context) {
        if (B == null) {
            B = E(context.getApplicationContext());
        }
        return B;
    }

    private static String g() {
        if (m == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("c").append("o").append("m").append(".").append("c").append("o").append("s").append("b").append(".").append("k").append(".").append("I").append("P").append("B").append("K").append("U").append("M");
            m = sb.toString();
        }
        return m;
    }

    public static a h(Context context) {
        if (C == null) {
            C = F(context.getApplicationContext());
            C.a(context);
        }
        return C;
    }

    private static String h() {
        if (n == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("c").append("o").append("m").append(".").append("c").append("o").append("s").append("b").append(".").append("k").append(".").append("I").append("P").append("R").append("L").append("A").append("M");
            n = sb.toString();
        }
        return n;
    }

    public static j i(Context context) {
        return G(context.getApplicationContext());
    }

    private static String i() {
        if (o == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("c").append("o").append("m").append(".").append("c").append("o").append("s").append("b").append(".").append("k").append(".").append("I").append("P").append("R").append("S").append("A").append("M");
            o = sb.toString();
        }
        return o;
    }

    public static b j(Context context) {
        return H(context.getApplicationContext());
    }

    private static String j() {
        if (p == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("c").append("o").append("m").append(".").append("c").append("o").append("s").append("b").append(".").append("k").append(".").append("I").append("P").append("B").append("R").append("E").append("M");
            p = sb.toString();
        }
        return p;
    }

    public static boolean k(Context context) {
        return m(context).exists();
    }

    public static boolean l(Context context) {
        return n(context).exists();
    }

    public static File m(Context context) {
        if (q == null) {
            q = new File(K(context), c);
        }
        return q;
    }

    public static File n(Context context) {
        if (r == null) {
            r = new File(K(context), d);
        }
        return r;
    }

    public static File o(Context context) {
        if (D == null) {
            D = new File(K(context), "u" + c);
        }
        return D;
    }

    public static File p(Context context) {
        if (E == null) {
            E = new File(K(context), "t" + c);
        }
        return E;
    }

    public static File q(Context context) {
        if (F == null) {
            F = new File(K(context), "d" + c);
        }
        return F;
    }

    public static File r(Context context) {
        if (G == null) {
            G = new File(K(context), "u" + d);
        }
        return G;
    }

    public static File s(Context context) {
        if (H == null) {
            H = new File(K(context), "t" + d);
        }
        return H;
    }

    public static File t(Context context) {
        if (I == null) {
            I = new File(K(context), "d" + d);
        }
        return I;
    }

    protected static boolean u(Context context) {
        return o.a(context).getBoolean(a, true);
    }

    protected static boolean v(Context context) {
        return o.a(context).getBoolean(b, true);
    }

    private static void w(Context context) {
        if (!u(context)) {
            o.c(context, true);
        }
    }

    private static void x(Context context) {
        if (!v(context)) {
            o.d(context, true);
        }
    }

    private static n y(Context context) {
        try {
            return new n(I(context).loadClass(a()).newInstance());
        } catch (Exception e2) {
            w(context);
            throw e2;
        }
    }

    private static e z(Context context) {
        try {
            return new e(I(context).loadClass(b()).newInstance());
        } catch (Exception e2) {
            w(context);
            throw e2;
        }
    }
}
