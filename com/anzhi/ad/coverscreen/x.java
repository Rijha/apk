package com.anzhi.ad.coverscreen;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.anzhi.ad.coverscreen.b.a;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class x extends LinearLayout {
    boolean a;
    a b;
    SA c;

    public x(SA sa, a aVar, boolean z) {
        super(sa);
        new Handler();
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        setGravity(17);
        this.a = z;
        this.c = sa;
        this.b = aVar;
    }

    protected static void b(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(1000);
        view.startAnimation(alphaAnimation);
    }

    protected void a() {
    }

    protected final void a(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setAnimationListener(new B(this, view));
        view.startAnimation(scaleAnimation);
    }

    protected final void a(ImageView imageView, String str) {
        com.anzhi.ad.coverscreen.c.d.a.a();
        String a = com.anzhi.ad.coverscreen.c.d.a.a(getContext().getApplicationContext(), com.anzhi.ad.coverscreen.a.a.a, str);
        Drawable a2 = com.anzhi.ad.coverscreen.c.e.a.a().a(getContext(), str, a);
        if (a2 == null) {
            imageView.setTag(str);
            a2 = com.anzhi.ad.coverscreen.c.e.a.a().a(getContext(), str, a, new A(this, imageView, str));
        }
        if (a2 != null) {
            imageView.setImageDrawable(a2);
        }
    }

    protected void b() {
    }

    protected View c() {
        return null;
    }

    public View d() {
        return null;
    }

    protected final void e() {
        a();
        b();
        c().setOnClickListener(new y(this));
        d().setOnClickListener(new z(this));
        this.c.b(this.b);
    }

    public final void f() {
        this.c.finish();
    }
}
