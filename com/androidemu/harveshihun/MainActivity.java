package com.androidemu.harveshihun;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import com.androidemu.Cheats;
import java.io.File;
/* loaded from: classes.dex */
public class MainActivity extends FileChooser {
    private static final int DIALOG_SHORTCUT = 1;
    private static final Uri HELP_URI = Uri.parse("file:///android_asset/faq.html");
    private static Intent emulatorIntent;
    private boolean creatingShortcut;
    private SharedPreferences settings;

    private void OpenGlInit() {
        new GameMainIn(this).GamePlayerInit();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.androidemu.harveshihun.FileChooser, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        this.settings = getSharedPreferences("MainActivity", 0);
        super.onCreate(savedInstanceState);
        OpenGlInit();
        setVolumeControlStream(3);
        setTitle(R.string.title_select_rom);
        getListView().setOnCreateContextMenuListener(this);
        this.creatingShortcut = getIntent().getAction().equals("android.intent.action.CREATE_SHORTCUT");
        EmulatorInit.init(this);
    }

    @Override // com.androidemu.harveshihun.FileChooser, android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        if (this.creatingShortcut) {
            return true;
        }
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override // com.androidemu.harveshihun.FileChooser, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                startActivity(new Intent(this, EmulatorSettings.class));
                return true;
            case R.id.menu_search_roms:
                startActivity(EmulatorSettings.getSearchROMIntent());
                return true;
            case R.id.menu_help:
                startActivity(new Intent(this, HelpActivity.class).setData(HELP_URI));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle(getListView().getItemAtPosition(((AdapterView.AdapterContextMenuInfo) menuInfo).position).toString());
        getMenuInflater().inflate(R.menu.main_context, menu);
    }

    private static File getSaveFile(File rom) {
        String name = rom.getName();
        int dot = name.lastIndexOf(46);
        if (dot >= 0) {
            name = name.substring(0, dot);
        }
        return new File(rom.getParentFile(), String.valueOf(name) + ".sav");
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        File file = getFile(info.position);
        switch (item.getItemId()) {
            case R.id.delete_rom:
                if (file.delete()) {
                    removeFile(info.position);
                }
                return true;
            case R.id.clear_saves:
                getSaveFile(file).delete();
                return true;
            case R.id.clear_cheats:
                Cheats.getCheatsFile(file).delete();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 1:
                return createShortcutNameDialog();
            default:
                return super.onCreateDialog(id);
        }
    }

    @Override // android.app.Activity
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case 1:
                String name = new File(emulatorIntent.getData().getPath()).getName();
                int dot = name.lastIndexOf(46);
                if (dot > 0) {
                    name = name.substring(0, dot);
                }
                EditText v = (EditText) dialog.findViewById(R.id.name);
                v.setText(name);
                v.selectAll();
                return;
            default:
                return;
        }
    }

    @Override // com.androidemu.harveshihun.FileChooser
    protected String getInitialPath() {
        return this.settings.getString("lastGame", null);
    }

    @Override // com.androidemu.harveshihun.FileChooser
    protected String[] getFileFilter() {
        return getResources().getStringArray(R.array.file_chooser_filters);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.androidemu.harveshihun.FileChooser
    public void onFileSelected(Uri uri) {
        this.settings.edit().putString("lastGame", uri.getPath()).commit();
        Intent intent = new Intent("android.intent.action.VIEW", uri, this, EmulatorActivity.class);
        if (!this.creatingShortcut) {
            startActivity(intent);
            return;
        }
        emulatorIntent = intent;
        showDialog(1);
    }

    private Dialog createShortcutNameDialog() {
        return new AlertDialog.Builder(this).setTitle(R.string.shortcut_name).setView(getLayoutInflater().inflate(R.layout.shortcut, (ViewGroup) null)).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.androidemu.harveshihun.MainActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                String name = ((EditText) ((Dialog) dialog).findViewById(R.id.name)).getText().toString();
                Intent intent = new Intent();
                intent.putExtra("android.intent.extra.shortcut.INTENT", MainActivity.emulatorIntent);
                intent.putExtra("android.intent.extra.shortcut.NAME", name);
                intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(MainActivity.this, R.drawable.app_icon));
                MainActivity.this.setResult(-1, intent);
                MainActivity.this.finish();
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
    }
}
