package com.androidemu.harveshihun;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
/* loaded from: classes.dex */
public class StateSlotsActivity extends ListActivity {
    public static final String EXTRA_SAVE_MODE = "saveMode";
    private static final int MENU_ITEM_DELETE = 1;
    private SaveSlotAdapter adapter;
    private LayoutInflater inflater;
    private boolean isSaveMode;

    public static String getSlotFileName(String fileName, int slot) {
        int len = fileName.lastIndexOf(46);
        if (len < 0) {
            len = fileName.length();
        }
        return new StringBuffer(len + 4).append((CharSequence) fileName, 0, len).append(".ss").append(slot).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap getScreenshot(File file) {
        Throwable th;
        ZipEntry entry;
        ZipInputStream in = null;
        try {
            ZipInputStream in2 = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
            try {
                do {
                    try {
                        entry = in2.getNextEntry();
                        if (entry == null) {
                            break;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        in = in2;
                        if (in != null) {
                            try {
                                in.close();
                            } catch (Exception e) {
                            }
                        }
                        throw th;
                    }
                } while (!entry.getName().equals("screenshot.png"));
                break;
                break;
            } catch (Exception e2) {
            }
            if (entry != null) {
                Bitmap decodeStream = BitmapFactory.decodeStream(in2);
                if (in2 != null) {
                    in2.close();
                }
                return decodeStream;
            }
            if (in2 != null) {
                in2.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.inflater = (LayoutInflater) getSystemService("layout_inflater");
        Intent intent = getIntent();
        this.isSaveMode = intent.getBooleanExtra(EXTRA_SAVE_MODE, false);
        setTitle(this.isSaveMode ? R.string.save_state_title : R.string.load_state_title);
        getListView().setOnCreateContextMenuListener(this);
        this.adapter = new SaveSlotAdapter(intent.getData().getPath());
        setListAdapter(this.adapter);
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(getSlotName(info.position));
        if (((File) getListView().getItemAtPosition(info.position)).exists()) {
            menu.add(0, 1, 0, R.string.menu_delete);
        }
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case 1:
                this.adapter.delete(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override // android.app.ListActivity
    protected void onListItemClick(ListView l, View v, int position, long id) {
        File file = (File) l.getItemAtPosition(position);
        if (this.isSaveMode || file.exists()) {
            Intent intent = new Intent();
            intent.setData(Uri.fromFile(file));
            setResult(-1, intent);
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSlotName(int slot) {
        if (slot == 0) {
            return getString(R.string.slot_quick);
        }
        return getString(R.string.slot_nth, new Object[]{Integer.valueOf(slot)});
    }

    /* loaded from: classes.dex */
    private class SaveSlotAdapter extends BaseAdapter {
        private File[] files = new File[10];

        public SaveSlotAdapter(String path) {
            for (int i = 0; i < this.files.length; i++) {
                this.files[i] = new File(StateSlotsActivity.getSlotFileName(path, i));
            }
        }

        public void delete(int position) {
            if (((File) getItem(position)).delete()) {
                notifyDataSetChanged();
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.files.length;
        }

        @Override // android.widget.Adapter
        public long getItemId(int position) {
            return (long) position;
        }

        @Override // android.widget.Adapter
        public Object getItem(int position) {
            return this.files[position];
        }

        @Override // android.widget.Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = StateSlotsActivity.this.inflater.inflate(R.layout.state_slot_item, (ViewGroup) null);
            }
            ((TextView) convertView.findViewById(R.id.name)).setText(StateSlotsActivity.this.getSlotName(position));
            TextView detailView = (TextView) convertView.findViewById(R.id.detail);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.screenshot);
            File file = (File) getItem(position);
            if (file.exists()) {
                detailView.setText(DateFormat.format("yyyy-MM-dd hh:mm:ss", file.lastModified()));
                imageView.setImageBitmap(StateSlotsActivity.getScreenshot(file));
            } else {
                detailView.setText(StateSlotsActivity.this.getString(R.string.slot_empty));
                imageView.setImageBitmap(null);
            }
            return convertView;
        }
    }
}
