package com.anzhi.ad.coverscreen.c.h;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
/* loaded from: classes.dex */
public final class a {
    private static a b = null;
    private static Context c;
    private NotificationManager a;

    private a(Context context) {
        this.a = null;
        if (this.a == null) {
            this.a = (NotificationManager) context.getSystemService("notification");
        }
    }

    private ImageView a(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount(); childCount > 0; childCount--) {
                ImageView a = a(viewGroup.getChildAt(childCount - 1));
                if (a != null && a.getId() == 16908294) {
                    return a;
                }
            }
            return null;
        } else if (view instanceof ImageView) {
            return (ImageView) view;
        } else {
            return null;
        }
    }

    public static a a(Context context) {
        c = context;
        if (b == null) {
            b = new a(c);
        }
        return b;
    }

    public final void a(int i, int i2, String str, String str2, String str3, Intent intent, int i3, String str4) {
        Notification notification = new Notification();
        notification.icon = 17301586;
        notification.tickerText = str;
        notification.flags = 16;
        notification.setLatestEventInfo(c, str2, str3, PendingIntent.getActivity(c, 0, intent, 134217728));
        if (str4 != null && str4.length() != 0 && notification != null) {
            try {
                ImageView a = a(View.inflate(c, notification.contentView.getLayoutId(), null));
                com.anzhi.ad.coverscreen.c.d.a.a();
                Bitmap a2 = com.anzhi.ad.coverscreen.c.e.a.a().a(c, com.anzhi.ad.coverscreen.c.d.a.a(c, com.anzhi.ad.coverscreen.a.a.a, str4));
                if (a2 != null) {
                    notification.contentView.setImageViewBitmap(a.getId(), a2);
                    this.a.notify(i, notification);
                }
            } catch (Exception e) {
                com.anzhi.ad.coverscreen.c.g.a.a("NotificationManagerTool >> " + e.getMessage());
            }
        }
    }
}
