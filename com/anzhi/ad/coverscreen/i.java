package com.anzhi.ad.coverscreen;

import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.anzhi.ad.coverscreen.b.a;
import com.anzhi.ad.coverscreen.c.d.b;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i extends x {
    private int d = 5;
    private int e = 32;
    private int f = this.e / 2;
    private int g = 390;
    private int h = 250;
    private int i = (this.g + this.e) + (this.d << 1);
    private int j = (this.h + this.e) + (this.d << 1);
    private ImageView k;
    private RelativeLayout l;

    public i(SA sa, a aVar, boolean z) {
        super(sa, aVar, z);
        e();
    }

    @Override // com.anzhi.ad.coverscreen.x
    protected final void a() {
        double d = 2.0d;
        this.d = b.a(getContext(), (float) this.d);
        this.e = b.a(getContext(), (float) this.e);
        this.i = b.a(getContext(), (float) this.i);
        this.j = b.a(getContext(), (float) this.j);
        this.f = b.a(getContext(), (float) this.f);
        this.g = b.a(getContext(), (float) this.g);
        this.h = b.a(getContext(), (float) this.h);
        Rect rect = new Rect();
        this.c.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        com.anzhi.ad.coverscreen.c.g.a.a("initParam: [imageWidth: " + this.g + ",imageHeight: " + this.h + ",outContainerWidth: " + this.i + ",outContainerHeight: " + this.j + "]" + rect);
        int height = rect.height() - this.j;
        com.anzhi.ad.coverscreen.c.g.a.a("extraHeight: " + height + ",frame.height: " + rect.height() + ",outContainerHeight: " + this.j);
        if (height < 0 || Math.abs(height) > 10) {
            double height2 = ((double) (rect.height() - 10)) / ((double) this.j);
            double width = ((double) (rect.width() - 10)) / ((double) this.i);
            if (height2 <= width) {
                width = height2;
            }
            if (width <= 2.0d) {
                d = width;
            }
            this.d = (int) (((double) this.d) * d);
            this.e = (int) (((double) this.e) * d);
            this.i = (int) (((double) this.i) * d);
            this.j = (int) (((double) this.j) * d);
            this.f = (int) (((double) this.f) * d);
            this.g = (int) (((double) this.g) * d);
            this.h = (int) (((double) this.h) * d);
            com.anzhi.ad.coverscreen.c.g.a.a("initParam: [rate: " + d + ",imageWidth: " + this.g + ",imageHeight: " + this.h + ",outContainerWidth: " + this.i + ",outContainerHeight: " + this.j + "]");
        }
    }

    @Override // com.anzhi.ad.coverscreen.x
    protected final void b() {
        float f = (float) this.d;
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{f, f, f, f, f, f, f, f}, null, null));
        shapeDrawable.setPadding(this.d, this.d, this.d, this.d);
        shapeDrawable.getPaint().setColor(-1);
        this.l = new RelativeLayout(getContext());
        this.l.setLayoutParams(new LinearLayout.LayoutParams(this.i, this.j));
        addView(this.l);
        LinearLayout linearLayout = new LinearLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(this.f, this.f, this.f, this.f);
        layoutParams.addRule(13, -1);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundDrawable(shapeDrawable);
        linearLayout.setOrientation(1);
        this.l.addView(linearLayout);
        this.k = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(10, -1);
        layoutParams2.addRule(11, -1);
        int i = this.e;
        layoutParams2.width = i;
        layoutParams2.height = i;
        this.k.setLayoutParams(layoutParams2);
        this.k.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView imageView = this.k;
        getContext();
        imageView.setImageDrawable(b.a(com.anzhi.ad.coverscreen.a.a.c));
        this.l.addView(this.k);
        ImageView imageView2 = new ImageView(getContext());
        imageView2.setLayoutParams(new LinearLayout.LayoutParams(this.g, this.h));
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        a(imageView2, this.b.b());
        linearLayout.addView(imageView2);
        if (this.a) {
            a(this.l);
        } else {
            b(imageView2);
        }
    }

    @Override // com.anzhi.ad.coverscreen.x
    protected final View c() {
        return this.k;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.anzhi.ad.coverscreen.x
    public final View d() {
        return this.l;
    }
}
