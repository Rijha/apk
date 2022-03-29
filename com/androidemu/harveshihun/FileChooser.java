package com.androidemu.harveshihun;

import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class FileChooser extends ListActivity implements FileFilter, View.OnClickListener, View.OnKeyListener {
    public static final String EXTRA_FILTERS = "filters";
    public static final String EXTRA_TITLE = "title";
    private static final String ROM_GRIPPER_ACTION = "com.bingo.rom_gripper.action.ROM_LIST";
    private static final String ROM_GRIPPER_PACKAGE = "com.bingo.rom_gripper";
    private static final Uri ROM_GRIPPER_URI = Uri.parse("http://market.android.com/details?id=com.bingo.rom_gripper");
    private ArrayAdapter<String> adapter;
    private File currentDir;
    private String[] filters;
    private EditText pathEdit;
    private final File sdcardDir = new File("/sdcard");

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        String path;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_chooser);
        getListView().setEmptyView(findViewById(R.id.empty));
        this.pathEdit = (EditText) findViewById(R.id.path);
        this.pathEdit.setOnKeyListener(this);
        findViewById(R.id.rom_gripper).setOnClickListener(this);
        findViewById(R.id.goto_sdcard).setOnClickListener(this);
        findViewById(R.id.goto_parent).setOnClickListener(this);
        String title = getIntent().getStringExtra("title");
        if (title != null) {
            setTitle(title);
        }
        this.filters = getFileFilter();
        if (savedInstanceState != null) {
            path = savedInstanceState.getString("currentDir");
        } else {
            path = getInitialPath();
        }
        File dir = null;
        if (path != null) {
            dir = getDirectoryFromFile(path);
        }
        if (dir == null) {
            dir = this.sdcardDir;
        }
        changeTo(dir);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.file_chooser, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_rom_gripper /* 2131230757 */:
                startROMGripper();
                return true;
            case R.id.menu_refresh /* 2131230758 */:
                changeTo(this.currentDir);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.currentDir != null) {
            outState.putString("currentDir", this.currentDir.getAbsolutePath());
        }
    }

    @Override // android.app.ListActivity
    protected void onListItemClick(ListView l, View v, int position, long id) {
        File f = new File(this.currentDir, l.getItemAtPosition(position).toString());
        if (f.isDirectory()) {
            changeTo(f);
        } else {
            onFileSelected(Uri.fromFile(f));
        }
    }

    protected String[] getFileFilter() {
        return getIntent().getStringArrayExtra(EXTRA_FILTERS);
    }

    protected String getInitialPath() {
        Uri uri = getIntent().getData();
        if (uri == null) {
            return null;
        }
        return uri.getPath();
    }

    protected File getFile(int position) {
        return new File(this.currentDir, this.adapter.getItem(position));
    }

    protected void removeFile(int position) {
        this.adapter.remove(this.adapter.getItem(position));
    }

    protected void onFileSelected(Uri uri) {
        setResult(-1, new Intent().setData(uri));
        finish();
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == 66) {
            String name = this.pathEdit.getText().toString().trim();
            if (name.length() > 0) {
                File dir = new File(name);
                if (dir.isDirectory()) {
                    changeTo(dir);
                } else {
                    Toast.makeText(this, (int) R.string.invalid_dir, 0).show();
                }
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rom_gripper /* 2131230727 */:
                startROMGripper();
                return;
            case R.id.goto_sdcard /* 2131230728 */:
                changeTo(this.sdcardDir);
                return;
            case R.id.goto_parent /* 2131230729 */:
                File parent = this.currentDir.getParentFile();
                if (parent != null) {
                    changeTo(parent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        String name = file.getName();
        if (name.startsWith(".")) {
            return false;
        }
        if (file.isDirectory()) {
            return true;
        }
        String name2 = name.toLowerCase();
        for (String f : this.filters) {
            if (name2.endsWith(f)) {
                return true;
            }
        }
        return false;
    }

    private File getDirectoryFromFile(String path) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            return dir;
        }
        File dir2 = dir.getParentFile();
        if (dir2 == null || dir2.isDirectory()) {
            return dir2;
        }
        return null;
    }

    private void changeTo(File dir) {
        FileFilter fileFilter;
        if (this.filters == null) {
            fileFilter = null;
        } else {
            fileFilter = this;
        }
        File[] files = dir.listFiles(fileFilter);
        if (files == null) {
            files = new File[0];
        }
        this.currentDir = dir;
        this.pathEdit.setText(dir.getAbsolutePath());
        List<String> items = new ArrayList<>(files.length);
        for (File f : files) {
            String name = f.getName();
            if (f.isDirectory()) {
                name = String.valueOf(name) + '/';
            }
            items.add(name);
        }
        Collections.sort(items, String.CASE_INSENSITIVE_ORDER);
        this.adapter = new ArrayAdapter<>(this, 17367043, items);
        setListAdapter(this.adapter);
    }

    private void startROMGripper() {
        Intent intent = new Intent(ROM_GRIPPER_ACTION);
        intent.putExtra("romtype", "SNES");
        intent.addFlags(268435456);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent("android.intent.action.VIEW", ROM_GRIPPER_URI));
        }
    }
}
