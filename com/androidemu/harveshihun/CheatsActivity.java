package com.androidemu.harveshihun;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
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
import com.androidemu.Cheats;
import com.androidemu.Emulator;
import java.util.List;
/* loaded from: classes.dex */
public class CheatsActivity extends ListActivity {
    private static final int DIALOG_EDIT_CHEAT = 1;
    private static final int MENU_ITEM_DELETE = 2;
    private static final int MENU_ITEM_EDIT = 1;
    private static Cheats.Item currentCheat;
    private ArrayAdapter<Cheats.Item> adapter;
    private Cheats cheats = Emulator.getInstance().getCheats();

    private void syncCheckedStates() {
        List<Cheats.Item> items = this.cheats.getAll();
        ListView listView = getListView();
        for (int i = 0; i < items.size(); i++) {
            listView.setItemChecked(i, items.get(i).enabled);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.cheats);
        setContentView(R.layout.cheats);
        getListView().setEmptyView(findViewById(R.id.empty));
        this.adapter = new ArrayAdapter<>(this, 17367056, this.cheats.getAll());
        setListAdapter(this.adapter);
        ListView listView = getListView();
        listView.setOnCreateContextMenuListener(this);
        listView.setItemsCanFocus(false);
        listView.setChoiceMode(2);
        syncCheckedStates();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.cheats.save();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addCheat(String code, String name) {
        if (this.cheats.add(code, name) == null) {
            Toast.makeText(this, (int) R.string.invalid_cheat_code, 0).show();
            return;
        }
        this.adapter.notifyDataSetChanged();
        syncCheckedStates();
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 1:
                return new AlertDialog.Builder(this).setTitle(R.string.new_cheat).setView(getLayoutInflater().inflate(R.layout.new_cheat, (ViewGroup) null)).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.androidemu.harveshihun.CheatsActivity.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        Dialog d = (Dialog) dialog;
                        String name = ((TextView) d.findViewById(R.id.cheat_name)).getText().toString();
                        if (CheatsActivity.currentCheat != null) {
                            CheatsActivity.currentCheat.name = name;
                            CheatsActivity.currentCheat = null;
                            CheatsActivity.this.cheats.setModified();
                            CheatsActivity.this.adapter.notifyDataSetChanged();
                            return;
                        }
                        CheatsActivity.this.addCheat(((TextView) d.findViewById(R.id.cheat_code)).getText().toString(), name);
                    }
                }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
            default:
                return super.onCreateDialog(id);
        }
    }

    @Override // android.app.Activity
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case 1:
                TextView codeView = (TextView) dialog.findViewById(R.id.cheat_code);
                TextView nameView = (TextView) dialog.findViewById(R.id.cheat_name);
                if (currentCheat == null) {
                    codeView.setEnabled(true);
                    codeView.setText((CharSequence) null);
                    nameView.setText((CharSequence) null);
                } else {
                    codeView.setEnabled(false);
                    codeView.setText(currentCheat.code);
                    nameView.setText(currentCheat.name);
                }
                nameView.requestFocus();
                return;
            default:
                return;
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.cheats, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_cheat:
                currentCheat = null;
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
                currentCheat = this.adapter.getItem(info.position);
                showDialog(1);
                return true;
            case 2:
                this.cheats.remove(info.position);
                this.adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override // android.app.ListActivity
    protected void onListItemClick(ListView l, View v, int position, long id) {
        this.cheats.enable(position, l.isItemChecked(position));
    }
}
