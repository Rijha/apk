package com.anzhi.ad.coverscreen;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.anzhi.ad.coverscreen.c.e.d;
/* loaded from: classes.dex */
final class A implements d {
    private final /* synthetic */ ImageView a;
    private final /* synthetic */ String b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public A(x xVar, ImageView imageView, String str) {
        this.a = imageView;
        this.b = str;
    }

    @Override // com.anzhi.ad.coverscreen.c.e.d
    public final void a(Drawable drawable) {
        if (drawable != null && this.a.getTag() != null && this.a.getTag().equals(this.b)) {
            this.a.setImageDrawable(drawable);
        }
    }
}
