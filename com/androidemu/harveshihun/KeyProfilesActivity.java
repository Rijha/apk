package com.androidemu.harveshihun;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Xml;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;
/* loaded from: classes.dex */
public class KeyProfilesActivity extends ListActivity {
    private static final int DIALOG_EDIT_PROFILE = 1;
    public static final String EXTRA_TITLE = "title";
    private static final int MENU_ITEM_DELETE = 2;
    private static final int MENU_ITEM_EDIT = 1;
    private static final String XML_ENCODING = "UTF-8";
    private static String currentProfile;

    private static File getKeyProfilesDir(Context context) {
        return context.getDir("key-profiles", 0);
    }

    private static String getProfileName(String file) {
        int dot = file.lastIndexOf(46);
        if (dot >= 0) {
            file = file.substring(0, dot);
        }
        return Uri.decode(file);
    }

    private static File getProfileFile(Context context, String name) {
        return new File(getKeyProfilesDir(context), String.valueOf(Uri.encode(name)) + ".xml");
    }

    public static Map<String, Integer> loadProfile(Context context, String name) {
        Throwable th;
        BufferedInputStream in;
        HashMap<String, Integer> mappings = new HashMap<>();
        BufferedInputStream in2 = null;
        try {
            in = new BufferedInputStream(new FileInputStream(getProfileFile(context, name)));
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(in, XML_ENCODING);
            for (int event = parser.getEventType(); event != 1; event = parser.next()) {
                if (event == 2) {
                    if (parser.getName().equals("key")) {
                        try {
                            mappings.put(parser.getAttributeValue(null, "name"), new Integer(parser.nextText()));
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e2) {
                }
            }
            return mappings;
        } catch (Throwable th3) {
            th = th3;
            in2 = in;
            if (in2 != null) {
                try {
                    in2.close();
                } catch (Exception e3) {
                }
            }
            throw th;
        }
    }

    public static void saveProfile(Context context, String name, Map<String, Integer> mappings) {
        BufferedOutputStream out = null;
        try {
            BufferedOutputStream out2 = new BufferedOutputStream(new FileOutputStream(getProfileFile(context, name)));
            try {
                XmlSerializer serializer = Xml.newSerializer();
                serializer.setOutput(out2, XML_ENCODING);
                serializer.startDocument(null, null);
                serializer.startTag(null, "key-profile");
                for (Map.Entry<String, Integer> pairs : mappings.entrySet()) {
                    serializer.startTag(null, "key");
                    serializer.attribute(null, "name", pairs.getKey());
                    serializer.text(pairs.getValue().toString());
                    serializer.endTag(null, "key");
                }
                serializer.endTag(null, "key-profile");
                serializer.endDocument();
                if (out2 != null) {
                    try {
                        out2.close();
                    } catch (Exception e) {
                    }
                }
            } catch (Throwable th) {
                th = th;
                out = out2;
                if (out != null) {
                    try {
                        out.close();
                    } catch (Exception e2) {
                        return;
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refresh() {
        String[] files = getKeyProfilesDir(this).list(new FilenameFilter() { // from class: com.androidemu.harveshihun.KeyProfilesActivity.1
            @Override // java.io.FilenameFilter
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".xml");
            }
        });
        if (files == null) {
            files = new String[0];
        }
        ArrayList<String> items = new ArrayList<>(files.length);
        for (String f : files) {
            items.add(getProfileName(f));
        }
        Collections.sort(items, String.CASE_INSENSITIVE_ORDER);
        setListAdapter(new ArrayAdapter(this, 17367043, items));
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra("title"));
        getListView().setOnCreateContextMenuListener(this);
        refresh();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.key_profiles, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_new_profile:
                currentProfile = null;
                showDialog(1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 1, 0, R.string.menu_edit);
        menu.add(0, 2, 0, R.string.menu_delete);
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case 1:
                currentProfile = (String) getListAdapter().getItem(info.position);
                showDialog(1);
                return true;
            case 2:
                if (getProfileFile(this, (String) getListAdapter().getItem(info.position)).delete()) {
                    refresh();
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override // android.app.Activity
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case 1:
                ((TextView) dialog.findViewById(R.id.profile_name)).setText(currentProfile);
                return;
            default:
                return;
        }
    }

    @Override // android.app.ListActivity
    protected void onListItemClick(ListView l, View v, int position, long id) {
        setResult(-1, new Intent(l.getItemAtPosition(position).toString()));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean createOrRenameProfile(String oldName, String newName) {
        if (newName.length() == 0) {
            return false;
        }
        File newFile = getProfileFile(this, newName);
        try {
            if (oldName == null) {
                return newFile.createNewFile();
            }
            if (oldName.equals(newName)) {
                return true;
            }
            return getProfileFile(this, oldName).renameTo(newFile);
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 1:
                return new AlertDialog.Builder(this).setTitle(R.string.new_profile_title).setView(getLayoutInflater().inflate(R.layout.new_profile, (ViewGroup) null)).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.androidemu.harveshihun.KeyProfilesActivity.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        String oldName = KeyProfilesActivity.currentProfile;
                        KeyProfilesActivity.currentProfile = null;
                        if (KeyProfilesActivity.this.createOrRenameProfile(oldName, ((TextView) ((Dialog) dialog).findViewById(R.id.profile_name)).getText().toString().trim())) {
                            KeyProfilesActivity.this.refresh();
                        } else {
                            Toast.makeText(KeyProfilesActivity.this, (int) R.string.new_profile_error, 0).show();
                        }
                    }
                }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
            default:
                return super.onCreateDialog(id);
        }
    }
}
