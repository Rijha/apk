package com.androidemu.harveshihun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.widget.Toast;
import com.androidemu.Emulator;
import com.androidemu.harveshihun.wrapper.Wrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
/* loaded from: classes.dex */
public class EmulatorSettings extends PreferenceActivity implements Preference.OnPreferenceChangeListener {
    private static final String GAME_GRIPPER_URI = "https://sites.google.com/site/gamegripper";
    private static final String MARKET_URI = "http://market.android.com/details?id=";
    private static final int REQUEST_LOAD_KEY_PROFILE = 1;
    private static final int REQUEST_SAVE_KEY_PROFILE = 2;
    private static final String SEARCH_ROM_URI = "http://www.romfind.com/snes-roms.html?sid=YONG";
    private SharedPreferences settings;
    private static final Uri ABOUT_URI = Uri.parse("file:///android_asset/about.html");
    public static final int[] gameKeys = {Emulator.GAMEPAD_UP, Emulator.GAMEPAD_DOWN, Emulator.GAMEPAD_LEFT, Emulator.GAMEPAD_RIGHT, Emulator.GAMEPAD_UP_LEFT, Emulator.GAMEPAD_UP_RIGHT, Emulator.GAMEPAD_DOWN_LEFT, Emulator.GAMEPAD_DOWN_RIGHT, Emulator.GAMEPAD_SELECT, Emulator.GAMEPAD_START, Emulator.GAMEPAD_A, Emulator.GAMEPAD_B, 64, Emulator.GAMEPAD_Y, 32, 16, 48};
    public static final String[] gameKeysPref = {"gamepad_up", "gamepad_down", "gamepad_left", "gamepad_right", "gamepad_up_left", "gamepad_up_right", "gamepad_down_left", "gamepad_down_right", "gamepad_select", "gamepad_start", "gamepad_A", "gamepad_B", "gamepad_X", "gamepad_Y", "gamepad_TL", "gamepad_TR", "gamepad_TL_TR"};
    public static final String[] gameKeysPref2 = {"gamepad2_up", "gamepad2_down", "gamepad2_left", "gamepad2_right", "gamepad2_up_left", "gamepad2_up_right", "gamepad2_down_left", "gamepad2_down_right", "gamepad2_select", "gamepad2_start", "gamepad2_A", "gamepad2_B", "gamepad2_X", "gamepad2_Y", "gamepad2_TL", "gamepad2_TR", "gamepad2_TL_TR"};
    private static final int[] gameKeysName = {R.string.gamepad_up, R.string.gamepad_down, R.string.gamepad_left, R.string.gamepad_right, R.string.gamepad_up_left, R.string.gamepad_up_right, R.string.gamepad_down_left, R.string.gamepad_down_right, R.string.gamepad_select, R.string.gamepad_start, R.string.gamepad_A, R.string.gamepad_B, R.string.gamepad_X, R.string.gamepad_Y, R.string.gamepad_TL, R.string.gamepad_TR, R.string.gamepad_TL_TR};

    static {
        int n = gameKeys.length;
        if (gameKeysPref.length != n || gameKeysPref2.length != n || gameKeysName.length != n) {
            throw new AssertionError("Key configurations are not consistent");
        }
    }

    public static Intent getSearchROMIntent() {
        return new Intent("android.intent.action.VIEW", Uri.parse(SEARCH_ROM_URI)).setFlags(268435456);
    }

    private static ArrayList<String> getAllKeyPrefs() {
        ArrayList<String> result = new ArrayList<>();
        result.addAll(Arrays.asList(gameKeysPref));
        result.addAll(Arrays.asList(gameKeysPref2));
        result.add("gamepad_superscope_turbo");
        result.add("gamepad_superscope_pause");
        result.add("gamepad_superscope_cursor");
        return result;
    }

    private Map<String, Integer> getKeyMappings() {
        TreeMap<String, Integer> mappings = new TreeMap<>();
        Iterator<String> it = getAllKeyPrefs().iterator();
        while (it.hasNext()) {
            String key = it.next();
            mappings.put(key, new Integer(((KeyPreference) findPreference(key)).getKeyValue()));
        }
        return mappings;
    }

    private void setKeyMappings(Map<String, Integer> mappings) {
        SharedPreferences.Editor editor = this.settings.edit();
        Iterator<String> it = getAllKeyPrefs().iterator();
        while (it.hasNext()) {
            String key = it.next();
            Integer value = mappings.get(key);
            if (value != null) {
                ((KeyPreference) findPreference(key)).setKey(value.intValue());
                editor.putInt(key, value.intValue());
            }
        }
        editor.commit();
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.settings);
        addPreferencesFromResource(R.xml.preferences);
        this.settings = PreferenceManager.getDefaultSharedPreferences(this);
        int[] defaultKeys = DefaultPreferences.getKeyMappings(this);
        PreferenceGroup group = (PreferenceGroup) findPreference("gamepad1");
        for (int i = 0; i < gameKeysPref.length; i++) {
            KeyPreference pref = new KeyPreference(this);
            pref.setKey(gameKeysPref[i]);
            pref.setTitle(gameKeysName[i]);
            pref.setDefaultValue(Integer.valueOf(defaultKeys[i]));
            group.addPreference(pref);
        }
        PreferenceGroup group2 = (PreferenceGroup) findPreference("gamepad2");
        for (int i2 = 0; i2 < gameKeysPref2.length; i2++) {
            KeyPreference pref2 = new KeyPreference(this);
            pref2.setKey(gameKeysPref2[i2]);
            pref2.setTitle(gameKeysName[i2]);
            group2.addPreference(pref2);
        }
        PreferenceGroup group3 = (PreferenceGroup) findPreference("superScope");
        KeyPreference pref3 = new KeyPreference(this);
        pref3.setKey("gamepad_superscope_turbo");
        pref3.setTitle(R.string.gamepad_superscope_turbo);
        group3.addPreference(pref3);
        KeyPreference pref4 = new KeyPreference(this);
        pref4.setKey("gamepad_superscope_pause");
        pref4.setTitle(R.string.gamepad_superscope_pause);
        group3.addPreference(pref4);
        KeyPreference pref5 = new KeyPreference(this);
        pref5.setKey("gamepad_superscope_cursor");
        pref5.setTitle(R.string.gamepad_superscope_cursor);
        group3.addPreference(pref5);
        if (!Wrapper.supportsMultitouch(this)) {
            findPreference("enableVKeypad").setSummary(R.string.multitouch_unsupported);
        }
        findPreference("apuEnabled").setOnPreferenceChangeListener(this);
        findPreference("enableLightGun").setOnPreferenceChangeListener(this);
        findPreference("useCCore").setOnPreferenceChangeListener(this);
        findPreference("about").setIntent(new Intent(this, HelpActivity.class).setData(ABOUT_URI));
        findPreference("upgrade").setIntent(new Intent("android.intent.action.VIEW", Uri.parse(MARKET_URI + getPackageName())));
        findPreference("searchRoms").setIntent(getSearchROMIntent());
        findPreference("gameGripper").setIntent(new Intent("android.intent.action.VIEW", Uri.parse(GAME_GRIPPER_URI)));
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onActivityResult(int request, int result, Intent data) {
        switch (request) {
            case 1:
                if (result == -1) {
                    setKeyMappings(KeyProfilesActivity.loadProfile(this, data.getAction()));
                    return;
                }
                return;
            case 2:
                if (result == -1) {
                    KeyProfilesActivity.saveProfile(this, data.getAction(), getKeyMappings());
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // android.preference.PreferenceActivity
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        String key = preference.getKey();
        if ("loadKeyProfile".equals(key)) {
            Intent intent = new Intent(this, KeyProfilesActivity.class);
            intent.putExtra("title", getString(R.string.load_profile));
            startActivityForResult(intent, 1);
            return true;
        } else if (!"saveKeyProfile".equals(key)) {
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        } else {
            Intent intent2 = new Intent(this, KeyProfilesActivity.class);
            intent2.putExtra("title", getString(R.string.save_profile));
            startActivityForResult(intent2, 2);
            return true;
        }
    }

    @Override // android.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Toast.makeText(this, (int) R.string.game_reset_needed_prompt, 0).show();
        return true;
    }
}
