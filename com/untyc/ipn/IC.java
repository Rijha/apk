package com.untyc.ipn;

import android.app.Activity;
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
/* loaded from: classes.dex */
public class IC extends Activity {
    private b a;

    private b a() {
        if (this.a == null) {
            this.a = f.j(this);
            this.a.a(this);
        }
        return this.a;
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        try {
            a().a(i, i2, intent);
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper
    protected void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, i, z);
        try {
            a().a(theme, i, z);
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        try {
            a().g();
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            a().a(configuration);
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        super.onContentChanged();
        try {
            a().a();
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) {
        boolean z = false;
        try {
            z = a().a(menuItem);
        } catch (Exception e) {
        }
        return z ? z : super.onContextItemSelected(menuItem);
    }

    @Override // android.app.Activity
    public void onContextMenuClosed(Menu menu) {
        super.onContextMenuClosed(menu);
        try {
            a().a(menu);
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            a().a(bundle);
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        try {
            a().a(contextMenu, view, contextMenuInfo);
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        try {
            return a().b(i);
        } catch (Exception e) {
            return onCreateDialog;
        }
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i, Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(i, bundle);
        try {
            return a().a(i, bundle);
        } catch (Exception e) {
            return onCreateDialog;
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean z = false;
        try {
            z = a().b(menu);
        } catch (Exception e) {
        }
        return z ? z : super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        boolean z = false;
        try {
            z = a().b(i, menu);
        } catch (Exception e) {
        }
        return z ? z : super.onCreatePanelMenu(i, menu);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public View onCreatePanelView(int i) {
        View onCreatePanelView = super.onCreatePanelView(i);
        try {
            return a().a(i);
        } catch (Exception e) {
            return onCreatePanelView;
        }
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View onCreateView = super.onCreateView(str, context, attributeSet);
        try {
            return a().a(str, context, attributeSet);
        } catch (Exception e) {
            return onCreateView;
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        try {
            a().h();
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = false;
        try {
            z = a().a(i, keyEvent);
        } catch (Exception e) {
        }
        return z ? z : super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        boolean z = false;
        try {
            z = a().b(i, keyEvent);
        } catch (Exception e) {
        }
        return z ? z : super.onKeyLongPress(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        boolean z = false;
        try {
            z = a().a(i, i2, keyEvent);
        } catch (Exception e) {
        }
        return z ? z : super.onKeyMultiple(i, i2, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        boolean z = false;
        try {
            z = a().c(i, keyEvent);
        } catch (Exception e) {
        }
        return z ? z : super.onKeyUp(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        boolean z = false;
        try {
            z = a().a(i, menuItem);
        } catch (Exception e) {
        }
        return z ? z : super.onMenuItemSelected(i, menuItem);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        boolean z = false;
        try {
            z = a().a(i, menu);
        } catch (Exception e) {
        }
        return z ? z : super.onMenuOpened(i, menu);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        try {
            a().a(intent);
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean z = false;
        try {
            z = a().b(menuItem);
        } catch (Exception e) {
        }
        return z ? z : super.onOptionsItemSelected(menuItem);
    }

    @Override // android.app.Activity
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        try {
            a().c(menu);
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
        try {
            a().c(i, menu);
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        try {
            a().b();
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        try {
            a().c();
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        try {
            a().b(bundle);
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            a().d();
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        try {
            a().c(bundle);
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        try {
            a().e();
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        try {
            a().f();
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        try {
            z = a().a(motionEvent);
        } catch (Exception e) {
        }
        return z ? z : super.onTouchEvent(motionEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        try {
            a().a(z);
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity, android.content.Context, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void setTheme(int i) {
        try {
            i = a().c(i);
        } catch (Exception e) {
        }
        super.setTheme(i);
    }
}
