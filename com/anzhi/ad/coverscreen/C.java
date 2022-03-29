package com.anzhi.ad.coverscreen;

import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.anzhi.ad.coverscreen.b.a;
import com.anzhi.ad.coverscreen.c.d.b;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class C extends x {
    private int d = 5;
    private int e = 32;
    private int f = this.e / 2;
    private int g = 275;
    private int h = 180;
    private int i = 5;
    private int j = 50;
    private int k = 50;
    private int l = 14;
    private int m = 96;
    private int n = 36;
    private int o = (this.g + this.e) + (this.d << 1);
    private int p = (((this.h + this.e) + (this.d << 1)) + this.i) + this.k;
    private ImageView q;
    private RelativeLayout r;

    public C(SA sa, a aVar, boolean z) {
        super(sa, aVar, z);
        e();
    }

    @Override // com.anzhi.ad.coverscreen.x
    protected final void a() {
        double d = 2.0d;
        this.d = b.a(getContext(), (float) this.d);
        this.e = b.a(getContext(), (float) this.e);
        this.o = b.a(getContext(), (float) this.o);
        this.p = b.a(getContext(), (float) this.p);
        this.f = b.a(getContext(), (float) this.f);
        this.g = b.a(getContext(), (float) this.g);
        this.h = b.a(getContext(), (float) this.h);
        this.i = b.a(getContext(), (float) this.i);
        this.j = b.a(getContext(), (float) this.j);
        this.k = b.a(getContext(), (float) this.k);
        this.n = b.a(getContext(), (float) this.n);
        this.m = b.a(getContext(), (float) this.m);
        Rect rect = new Rect();
        this.c.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        com.anzhi.ad.coverscreen.c.g.a.a("initParam: [imageWidth: " + this.g + ",imageHeight: " + this.h + ",outContainerWidth: " + this.o + ",outContainerHeight: " + this.p + "]" + rect);
        int width = rect.width() - this.o;
        com.anzhi.ad.coverscreen.c.g.a.a("extraWidth: " + width + ",frame.width: " + rect.width() + ",outContainerHeight: " + this.o);
        if (width < 0 || Math.abs(width) > 10) {
            double height = ((double) (rect.height() - 10)) / ((double) this.p);
            double width2 = ((double) (rect.width() - 10)) / ((double) this.o);
            if (height <= width2) {
                width2 = height;
            }
            if (width2 <= 2.0d) {
                d = width2;
            }
            this.d = (int) (((double) this.d) * d);
            this.e = (int) (((double) this.e) * d);
            this.o = (int) (((double) this.o) * d);
            this.p = (int) (((double) this.p) * d);
            this.f = (int) (((double) this.f) * d);
            this.g = (int) (((double) this.g) * d);
            this.h = (int) (((double) this.h) * d);
            this.i = (int) (((double) this.i) * d);
            this.j = (int) (((double) this.j) * d);
            this.k = (int) (((double) this.k) * d);
            this.n = (int) (((double) this.n) * d);
            this.m = (int) (((double) this.m) * d);
            this.l = (int) (((double) this.l) * d);
            com.anzhi.ad.coverscreen.c.g.a.a("initParam: [rate: " + d + ",imageWidth: " + this.g + ",imageHeight: " + this.h + ",outContainerWidth: " + this.o + ",outContainerHeight: " + this.p + "]");
        }
    }

    @Override // com.anzhi.ad.coverscreen.x
    protected final void b() {
        float f = (float) this.d;
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{f, f, f, f, f, f, f, f}, null, null));
        shapeDrawable.setPadding(this.d, this.d, this.d, this.d);
        shapeDrawable.getPaint().setColor(-1);
        this.r = new RelativeLayout(getContext());
        this.r.setLayoutParams(new LinearLayout.LayoutParams(this.o, this.p));
        addView(this.r);
        LinearLayout linearLayout = new LinearLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(this.f, this.f, this.f, this.f);
        layoutParams.addRule(13, -1);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundDrawable(shapeDrawable);
        linearLayout.setOrientation(1);
        this.r.addView(linearLayout);
        this.q = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(10, -1);
        layoutParams2.addRule(11, -1);
        int i = this.e;
        layoutParams2.width = i;
        layoutParams2.height = i;
        this.q.setLayoutParams(layoutParams2);
        this.q.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView imageView = this.q;
        getContext();
        imageView.setImageDrawable(b.a(com.anzhi.ad.coverscreen.a.a.c));
        this.r.addView(this.q);
        ImageView imageView2 = new ImageView(getContext());
        imageView2.setLayoutParams(new LinearLayout.LayoutParams(this.g, this.h));
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        a(imageView2, this.b.b());
        linearLayout.addView(imageView2);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.topMargin = this.i;
        linearLayout2.setLayoutParams(layoutParams3);
        linearLayout2.setOrientation(0);
        linearLayout.addView(linearLayout2);
        ImageView imageView3 = new ImageView(getContext());
        imageView3.setLayoutParams(new LinearLayout.LayoutParams(this.j, this.k));
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        a(imageView3, this.b.j());
        linearLayout2.addView(imageView3);
        LinearLayout linearLayout3 = new LinearLayout(getContext());
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, -1);
        layoutParams4.leftMargin = b.a(getContext(), 4.0f);
        layoutParams4.weight = 1.0f;
        linearLayout3.setLayoutParams(layoutParams4);
        linearLayout3.setGravity(16);
        linearLayout3.setOrientation(1);
        linearLayout2.addView(linearLayout3);
        TextView textView = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        if ("download".equals(this.b.h())) {
            textView.setText(this.b.d());
        } else {
            textView.setText(this.b.c());
        }
        textView.setTextColor(-16777216);
        textView.setTextSize((float) this.l);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setLayoutParams(layoutParams5);
        linearLayout3.addView(textView);
        if ("download".equals(this.b.h())) {
            TextView textView2 = new TextView(getContext());
            LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams6.topMargin = b.a(getContext(), 2.0f);
            textView2.setText(this.b.l());
            textView2.setTextColor(-16777216);
            textView2.setTextSize((float) (this.l - 2));
            textView2.setLayoutParams(layoutParams6);
            linearLayout3.addView(textView2);
        }
        ImageView imageView4 = new ImageView(getContext());
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(this.m, this.n);
        layoutParams7.gravity = 16;
        imageView4.setLayoutParams(layoutParams7);
        imageView4.setScaleType(ImageView.ScaleType.FIT_XY);
        if ("download".equals(this.b.h())) {
            getContext();
            imageView4.setImageDrawable(b.a(com.anzhi.ad.coverscreen.a.a.d));
        } else {
            getContext();
            imageView4.setImageDrawable(b.a(com.anzhi.ad.coverscreen.a.a.e));
        }
        linearLayout2.addView(imageView4);
        if (this.a) {
            a(this.r);
        } else {
            b(imageView2);
        }
    }

    @Override // com.anzhi.ad.coverscreen.x
    protected final View c() {
        return this.q;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.anzhi.ad.coverscreen.x
    public final View d() {
        return this.r;
    }
}
