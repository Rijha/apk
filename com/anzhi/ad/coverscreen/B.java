package com.anzhi.ad.coverscreen;

import android.view.View;
import android.view.animation.Animation;
/* loaded from: classes.dex */
final class B implements Animation.AnimationListener {
    private final /* synthetic */ View a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public B(x xVar, View view) {
        this.a = view;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        this.a.setVisibility(0);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
    }
}
