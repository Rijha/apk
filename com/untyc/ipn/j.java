package com.untyc.ipn;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class j extends k {
    private Method A;
    private Method B;
    private Method C;
    private Method D;
    private Method E;
    private Method F;
    private Method G;
    private Method H;
    private Method I;
    private Method J;
    private Method K;
    private Method L;
    private Object a;
    private Method b;
    private Method c;
    private Method d;
    private Method e;
    private Method f;
    private Method g;
    private Method h;
    private Method i;
    private Method j;
    private Method k;
    private Method l;
    private Method m;
    private Method n;
    private Method o;
    private Method p;
    private Method q;
    private Method r;
    private Method s;
    private Method t;
    private Method u;
    private Method v;
    private Method w;
    private Method x;
    private Method y;
    private Method z;

    /* JADX INFO: Access modifiers changed from: protected */
    public j(Object obj) {
        if (obj == null) {
            throw new Exception();
        }
        this.a = obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Dialog a(int i, Bundle bundle) {
        if (this.x == null) {
            this.x = a(this.a, "onCreateDialog2");
        }
        Object a = a(this.a, this.x, new Object[]{Integer.valueOf(i), bundle});
        if (a != null) {
            return (Dialog) a;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View a(int i) {
        if (this.v == null) {
            this.v = a(this.a, "onCreatePanelView");
        }
        Object a = a(this.a, this.v, new Object[]{Integer.valueOf(i)});
        if (a != null) {
            return (View) a;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View a(String str, Context context, AttributeSet attributeSet) {
        if (this.y == null) {
            this.y = a(this.a, "onCreateView");
        }
        Object a = a(this.a, this.y, new Object[]{str, context, attributeSet});
        if (a != null) {
            return (View) a;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a() {
        if (this.g == null) {
            this.g = a(this.a, "onContentChanged");
        }
        a(this.a, this.g, (Object[]) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(int i, int i2, Intent intent) {
        if (this.d == null) {
            this.d = a(this.a, "onActivityResult");
        }
        a(this.a, this.d, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), intent});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Intent intent) {
        if (this.r == null) {
            this.r = a(this.a, "onNewIntent");
        }
        a(this.a, this.r, new Object[]{intent});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Configuration configuration) {
        if (this.f == null) {
            this.f = a(this.a, "onConfigurationChanged");
        }
        a(this.a, this.f, new Object[]{configuration});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Resources.Theme theme, int i, boolean z) {
        if (this.e == null) {
            this.e = a(this.a, "onApplyThemeResource");
        }
        a(this.a, this.e, new Object[]{theme, Integer.valueOf(i), Boolean.valueOf(z)});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Bundle bundle) {
        if (this.c == null) {
            this.c = a(this.a, "onCreate");
        }
        a(this.a, this.c, new Object[]{bundle});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        if (this.j == null) {
            this.j = a(this.a, "onCreateContextMenu");
        }
        a(this.a, this.j, new Object[]{contextMenu, view, contextMenuInfo});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Menu menu) {
        if (this.i == null) {
            this.i = a(this.a, "onContextMenuClosed");
        }
        a(this.a, this.i, new Object[]{menu});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(IB ib) {
        if (this.b == null) {
            this.b = a(this.a, "sa");
        }
        a(this.a, this.b, new Object[]{ib});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(boolean z) {
        if (this.I == null) {
            this.I = a(this.a, "onWindowFocusChanged");
        }
        a(this.a, this.I, new Object[]{Boolean.valueOf(z)});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(int i, int i2, KeyEvent keyEvent) {
        if (this.n == null) {
            this.n = a(this.a, "onKeyMultiple");
        }
        Object a = a(this.a, this.n, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), keyEvent});
        if (a != null) {
            return ((Boolean) a).booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(int i, KeyEvent keyEvent) {
        if (this.l == null) {
            this.l = a(this.a, "onKeyDown");
        }
        Object a = a(this.a, this.l, new Object[]{Integer.valueOf(i), keyEvent});
        if (a != null) {
            return ((Boolean) a).booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(int i, Menu menu) {
        if (this.q == null) {
            this.q = a(this.a, "onMenuOpened");
        }
        Object a = a(this.a, this.q, new Object[]{Integer.valueOf(i), menu});
        if (a != null) {
            return ((Boolean) a).booleanValue();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(int i, MenuItem menuItem) {
        if (this.p == null) {
            this.p = a(this.a, "onMenuItemSelected");
        }
        Object a = a(this.a, this.p, new Object[]{Integer.valueOf(i), menuItem});
        if (a != null) {
            return ((Boolean) a).booleanValue();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(MenuItem menuItem) {
        if (this.h == null) {
            this.h = a(this.a, "onContextItemSelected");
        }
        Object a = a(this.a, this.h, new Object[]{menuItem});
        if (a != null) {
            return ((Boolean) a).booleanValue();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(MotionEvent motionEvent) {
        if (this.H == null) {
            this.H = a(this.a, "onTouchEvent");
        }
        Object a = a(this.a, this.H, new Object[]{motionEvent});
        if (a != null) {
            return ((Boolean) a).booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Dialog b(int i) {
        if (this.w == null) {
            this.w = a(this.a, "onCreateDialog");
        }
        Object a = a(this.a, this.w, new Object[]{Integer.valueOf(i)});
        if (a != null) {
            return (Dialog) a;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b() {
        if (this.A == null) {
            this.A = a(this.a, "onPause");
        }
        a(this.a, this.A, (Object[]) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(Bundle bundle) {
        if (this.D == null) {
            this.D = a(this.a, "onRestoreInstanceState");
        }
        a(this.a, this.D, new Object[]{bundle});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b(int i, KeyEvent keyEvent) {
        if (this.m == null) {
            this.m = a(this.a, "onKeyLongPress");
        }
        Object a = a(this.a, this.m, new Object[]{Integer.valueOf(i), keyEvent});
        if (a != null) {
            return ((Boolean) a).booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b(int i, Menu menu) {
        if (this.u == null) {
            this.u = a(this.a, "onCreatePanelMenu");
        }
        Object a = a(this.a, this.u, new Object[]{Integer.valueOf(i), menu});
        if (a != null) {
            return ((Boolean) a).booleanValue();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b(Menu menu) {
        if (this.k == null) {
            this.k = a(this.a, "onCreateOptionsMenu");
        }
        Object a = a(this.a, this.k, new Object[]{menu});
        if (a != null) {
            return ((Boolean) a).booleanValue();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b(MenuItem menuItem) {
        if (this.s == null) {
            this.s = a(this.a, "onOptionsItemSelected");
        }
        Object a = a(this.a, this.s, new Object[]{menuItem});
        if (a != null) {
            return ((Boolean) a).booleanValue();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int c(int i) {
        if (this.L == null) {
            this.L = a(this.a, "setTheme");
        }
        Object a = a(this.a, this.L, new Object[]{Integer.valueOf(i)});
        return a != null ? ((Integer) a).intValue() : i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c() {
        if (this.B == null) {
            this.B = a(this.a, "onRestart");
        }
        a(this.a, this.B, (Object[]) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(int i, Menu menu) {
        if (this.z == null) {
            this.z = a(this.a, "onPanelClosed");
        }
        a(this.a, this.z, new Object[]{Integer.valueOf(i), menu});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(Bundle bundle) {
        if (this.E == null) {
            this.E = a(this.a, "onSaveInstanceState");
        }
        a(this.a, this.E, new Object[]{bundle});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(Menu menu) {
        if (this.t == null) {
            this.t = a(this.a, "onOptionsMenuClosed");
        }
        a(this.a, this.t, new Object[]{menu});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean c(int i, KeyEvent keyEvent) {
        if (this.o == null) {
            this.o = a(this.a, "onKeyUp");
        }
        Object a = a(this.a, this.o, new Object[]{Integer.valueOf(i), keyEvent});
        if (a != null) {
            return ((Boolean) a).booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d() {
        if (this.C == null) {
            this.C = a(this.a, "onResume");
        }
        a(this.a, this.C, (Object[]) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e() {
        if (this.F == null) {
            this.F = a(this.a, "onStart");
        }
        a(this.a, this.F, (Object[]) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f() {
        if (this.G == null) {
            this.G = a(this.a, "onStop");
        }
        a(this.a, this.G, (Object[]) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void g() {
        if (this.J == null) {
            this.J = a(this.a, "onBackPressed");
        }
        a(this.a, this.J, (Object[]) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void h() {
        if (this.K == null) {
            this.K = a(this.a, "onDestroy");
        }
        a(this.a, this.K, (Object[]) null);
    }
}
