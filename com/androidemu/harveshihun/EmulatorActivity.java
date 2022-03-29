package com.androidemu.harveshihun;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.androidemu.Emulator;
import com.androidemu.EmulatorView;
import com.androidemu.harveshihun.input.GameKeyListener;
import com.androidemu.harveshihun.input.Keyboard;
import com.androidemu.harveshihun.input.SensorKeypad;
import com.androidemu.harveshihun.input.VirtualKeypad;
import com.androidemu.harveshihun.wrapper.Wrapper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.http.conn.util.InetAddressUtils;
/* loaded from: classes.dex */
public class EmulatorActivity extends Activity implements Emulator.FrameUpdateListener, Emulator.VideoSizeChangeListener, SharedPreferences.OnSharedPreferenceChangeListener, SurfaceHolder.Callback, View.OnTouchListener, EmulatorView.OnTrackballListener, GameKeyListener {
    private static final int DIALOG_QUIT_GAME;
    private static final int DIALOG_REPLACE_GAME;
    private static final int DIALOG_WIFI_CONNECT;
    private static final int GAMEPAD_DIRECTION;
    private static final int GAMEPAD_LEFT_RIGHT;
    private static final int GAMEPAD_UP_DOWN;
    private static final String LOG_TAG;
    private static final int MESSAGE_SYNC_CLIENT;
    private static final int NETPLAY_TCP_PORT;
    private static final int REQUEST_BT_DEVICE;
    private static final int REQUEST_ENABLE_BT_CLIENT;
    private static final int REQUEST_ENABLE_BT_SERVER;
    private static final int REQUEST_LOAD_STATE;
    private static final int REQUEST_SAVE_STATE;
    private static final int[] SENSOR_MAP_DPAD = {Emulator.GAMEPAD_LEFT, Emulator.GAMEPAD_RIGHT, Emulator.GAMEPAD_UP, Emulator.GAMEPAD_DOWN};
    private static final int[] SENSOR_MAP_TRIGGERS;
    private int autoSyncClientInterval;
    private Emulator emulator;
    private EmulatorView emulatorView;
    private int fastForwardKey;
    private float fastForwardSpeed;
    private boolean flipScreen;
    private boolean inFastForward;
    private Keyboard keyboard;
    private boolean lightGunEnabled;
    private MediaScanner mediaScanner;
    private NetPlayService netPlayService;
    private Intent newIntent;
    private int quickLoadKey;
    private int quickSaveKey;
    private int screenshotKey;
    private SensorKeypad sensor;
    private int[] sensorMappings;
    private SharedPreferences sharedPrefs;
    private int surfaceHeight;
    private int surfaceWidth;
    private int trackballSensitivity;
    private VirtualKeypad vkeypad;
    private NetWaitDialog waitDialog;
    private Rect surfaceRegion = new Rect();
    private Handler netPlayHandler = new Handler() { // from class: com.androidemu.harveshihun.EmulatorActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (EmulatorActivity.this.netPlayService != null) {
                EmulatorActivity.this.pauseEmulator();
                switch (msg.what) {
                    case 1:
                        EmulatorActivity.this.applyNetplaySettings();
                        if (EmulatorActivity.this.netPlayService.isServer()) {
                            EmulatorActivity.this.onNetPlaySync();
                        }
                        EmulatorActivity.this.emulator.setFrameUpdateListener(EmulatorActivity.this);
                        EmulatorActivity.this.netPlayService.sendMessageReply();
                        if (EmulatorActivity.this.waitDialog != null) {
                            EmulatorActivity.this.waitDialog.dismiss();
                            EmulatorActivity.this.waitDialog = null;
                            break;
                        }
                        break;
                    case 2:
                        EmulatorActivity.this.onDisconnect();
                        if (EmulatorActivity.this.waitDialog != null) {
                            EmulatorActivity.this.waitDialog.dismiss();
                            EmulatorActivity.this.waitDialog = null;
                        }
                        int error = R.string.connection_closed;
                        switch (msg.arg1) {
                            case 1:
                                error = R.string.connect_failed;
                                break;
                            case 2:
                                error = R.string.protocol_incompatible;
                                break;
                        }
                        Toast.makeText(EmulatorActivity.this, error, 1).show();
                        break;
                    case 3:
                        EmulatorActivity.this.emulator.power();
                        EmulatorActivity.this.netPlayService.sendMessageReply();
                        break;
                    case 4:
                        EmulatorActivity.this.emulator.reset();
                        EmulatorActivity.this.netPlayService.sendMessageReply();
                        break;
                    case 5:
                        File file = EmulatorActivity.this.getTempStateFile();
                        try {
                            EmulatorActivity.writeFile(file, (byte[]) msg.obj);
                            EmulatorActivity.this.emulator.loadState(file.getAbsolutePath());
                        } catch (IOException e) {
                        } finally {
                            file.delete();
                        }
                        EmulatorActivity.this.netPlayService.sendMessageReply();
                        break;
                    case EmulatorActivity.MESSAGE_SYNC_CLIENT:
                        if (EmulatorActivity.this.hasWindowFocus()) {
                            EmulatorActivity.this.onNetPlaySync();
                        }
                        EmulatorActivity.this.startAutoSyncClient();
                        break;
                }
                EmulatorActivity.this.resumeEmulator();
            }
        }
    };

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        processMenuItem(item);
        switch (item.getItemId()) {
            case R.id.menu_netplay_sync:
                onNetPlaySync();
                return true;
            case R.id.menu_load_state:
                onLoadState();
                return true;
            case R.id.menu_save_state:
                onSaveState();
                return true;
            case R.id.menu_settings:
                startActivity(new Intent(this, EmulatorSettings.class));
                return true;
            case R.id.menu_fast_forward:
                onFastForward();
                return true;
            case R.id.menu_netplay_connect:
            default:
                return super.onOptionsItemSelected(item);
            case R.id.menu_bluetooth_server:
                if (checkBluetoothEnabled(3)) {
                    onBluetoothServer();
                }
                return true;
            case R.id.menu_bluetooth_client:
                if (checkBluetoothEnabled(4)) {
                    onBluetoothClient();
                }
                return true;
            case R.id.menu_wifi_server:
                onWifiServer();
                return true;
            case R.id.menu_wifi_client:
                showDialog(3);
                return true;
            case R.id.menu_netplay_disconnect:
                onDisconnect();
                return true;
            case R.id.menu_cheats:
                startActivity(new Intent(this, CheatsActivity.class));
                return true;
            case R.id.menu_reset:
                try {
                    if (this.netPlayService != null) {
                        this.netPlayService.sendResetROM();
                    }
                    this.emulator.reset();
                } catch (IOException e) {
                }
                return true;
            case R.id.menu_screenshot:
                onScreenshot();
                return true;
            case R.id.menu_close:
                finish();
                return true;
        }
    }

    private void processMenuItem(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_more_games:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://game.shougj.net")));
                return;
            default:
                return;
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!"android.intent.action.VIEW".equals(getIntent().getAction())) {
            finish();
            return;
        }
        requestWindowFeature(1);
        setVolumeControlStream(3);
        this.sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences prefs = this.sharedPrefs;
        prefs.registerOnSharedPreferenceChangeListener(this);
        this.emulator = Emulator.createInstance(getApplicationContext(), getEmulatorEngine(prefs));
        this.emulator.setVideoSizeChangeListener(this);
        setContentView(R.layout.emulator);
        this.emulatorView = (EmulatorView) findViewById(R.id.emulator);
        this.emulatorView.getHolder().addCallback(this);
        this.emulatorView.setOnTouchListener(this);
        this.emulatorView.requestFocus();
        this.keyboard = new Keyboard(this.emulatorView, this);
        for (String key : new String[]{"fullScreenMode", "flipScreen", "fastForwardSpeed", "frameSkipMode", "maxFrameSkips", "refreshRate", "enableLightGun", "enableGamepad2", "soundEnabled", "soundVolume", "transparencyEnabled", "enableHiRes", "enableTrackball", "trackballSensitivity", "useSensor", "sensorSensitivity", "enableVKeypad", "scalingMode", "aspectRatio", "enableCheats", "orientation", "useInputMethod", "quickLoad", "quickSave", "fastForward", "screenshot"}) {
            onSharedPreferenceChanged(prefs, key);
        }
        loadKeyBindings(prefs);
        this.emulator.setOption("enableSRAM", true);
        this.emulator.setOption("apuEnabled", prefs.getBoolean("apuEnabled", true));
        if (!loadROM()) {
            finish();
            return;
        }
        startService(new Intent(this, EmulatorService.class).setAction("com.androidemu.actions.FOREGROUND"));
        EmulatorViewInit.init(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.emulator != null) {
            this.emulator.unloadROM();
        }
        onDisconnect();
        stopService(new Intent(this, EmulatorService.class));
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        pauseEmulator();
        if (this.sensor != null) {
            this.sensor.setGameKeyListener(null);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.sensor != null) {
            this.sensor.setGameKeyListener(this);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        pauseEmulator();
        setFlipScreen(this.sharedPrefs, newConfig);
        resumeEmulator();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            this.keyboard.reset();
            if (this.vkeypad != null) {
                this.vkeypad.reset();
            }
            this.emulator.setKeyStates(0);
            this.emulator.resume();
            return;
        }
        this.emulator.pause();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        if ("android.intent.action.VIEW".equals(intent.getAction())) {
            this.newIntent = intent;
            pauseEmulator();
            showDialog(2);
        }
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 1:
                return createQuitGameDialog();
            case 2:
                return createReplaceGameDialog();
            case 3:
                return createWifiConnectDialog();
            default:
                return super.onCreateDialog(id);
        }
    }

    @Override // android.app.Activity
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case 3:
                TextView v = (TextView) dialog.findViewById(R.id.port);
                if (v.getText().length() == 0) {
                    v.setText(Integer.toString(NETPLAY_TCP_PORT));
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == this.quickLoadKey) {
            quickLoad();
            return true;
        } else if (keyCode == this.quickSaveKey) {
            quickSave();
            return true;
        } else if (keyCode == this.fastForwardKey) {
            onFastForward();
            return true;
        } else if (keyCode == this.screenshotKey) {
            onScreenshot();
            return true;
        } else if (keyCode == 27 || keyCode == 84) {
            return true;
        } else {
            if (keyCode != 4) {
                return super.onKeyDown(keyCode, event);
            }
            pauseEmulator();
            showDialog(1);
            return true;
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.emulator, menu);
        if (Wrapper.isBluetoothPresent()) {
            return true;
        }
        menu.findItem(R.id.menu_bluetooth_server).setVisible(false);
        menu.findItem(R.id.menu_bluetooth_client).setVisible(false);
        return true;
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean netplay;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i;
        super.onPrepareOptionsMenu(menu);
        pauseEmulator();
        if (this.netPlayService != null) {
            netplay = true;
        } else {
            netplay = false;
        }
        MenuItem findItem = menu.findItem(R.id.menu_netplay_connect);
        if (netplay) {
            z = false;
        } else {
            z = true;
        }
        findItem.setVisible(z);
        menu.findItem(R.id.menu_netplay_disconnect).setVisible(netplay);
        menu.findItem(R.id.menu_netplay_sync).setVisible(netplay);
        MenuItem findItem2 = menu.findItem(R.id.menu_cheats);
        if (netplay) {
            z2 = false;
        } else {
            z2 = true;
        }
        findItem2.setVisible(z2);
        MenuItem findItem3 = menu.findItem(R.id.menu_fast_forward);
        if (netplay) {
            z3 = false;
        } else {
            z3 = true;
        }
        findItem3.setVisible(z3);
        MenuItem findItem4 = menu.findItem(R.id.menu_cheats);
        if (this.emulator.getCheats() != null) {
            z4 = true;
        } else {
            z4 = false;
        }
        findItem4.setEnabled(z4);
        MenuItem findItem5 = menu.findItem(R.id.menu_fast_forward);
        if (this.inFastForward) {
            i = R.string.no_fast_forward;
        } else {
            i = R.string.fast_forward;
        }
        findItem5.setTitle(i);
        return true;
    }

    @Override // android.app.Activity
    protected void onActivityResult(int request, int result, Intent data) {
        switch (request) {
            case 1:
                if (result == -1) {
                    loadState(data.getData().getPath());
                    return;
                }
                return;
            case 2:
                if (result == -1) {
                    saveState(data.getData().getPath());
                    return;
                }
                return;
            case 3:
                if (result == -1) {
                    onBluetoothServer();
                    return;
                }
                return;
            case 4:
                if (result == -1) {
                    onBluetoothClient();
                    return;
                }
                return;
            case 5:
                if (result == -1) {
                    onBluetoothConnect(data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS));
                    return;
                }
                return;
            default:
                return;
        }
    }

    private static int makeKeyStates(int p1, int p2) {
        return (p2 << 16) | (65535 & p1);
    }

    @Override // com.androidemu.Emulator.FrameUpdateListener
    public int onFrameUpdate(int keys) throws IOException, InterruptedException {
        int remote = this.netPlayService.sendFrameUpdate(keys);
        if (this.netPlayService.isServer()) {
            return makeKeyStates(keys, remote);
        }
        return makeKeyStates(remote, keys);
    }

    @Override // com.androidemu.Emulator.VideoSizeChangeListener
    public void onVideoSizeChange(int w, int h) {
        this.emulatorView.post(new Runnable() { // from class: com.androidemu.harveshihun.EmulatorActivity.2
            @Override // java.lang.Runnable
            public void run() {
                EmulatorActivity.this.emulatorView.setVisibility(4);
                EmulatorActivity.this.emulatorView.setActualSize(EmulatorActivity.this.emulator.getVideoWidth(), EmulatorActivity.this.emulator.getVideoHeight());
                EmulatorActivity.this.emulatorView.setVisibility(0);
            }
        });
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        int i;
        EmulatorActivity emulatorActivity;
        if (key.startsWith("gamepad")) {
            loadKeyBindings(prefs);
        } else if ("fullScreenMode".equals(key)) {
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            if (prefs.getBoolean("fullScreenMode", true)) {
                attrs.flags |= Emulator.GAMEPAD_DOWN;
            } else {
                attrs.flags &= -1025;
            }
            getWindow().setAttributes(attrs);
        } else if ("flipScreen".equals(key)) {
            setFlipScreen(prefs, getResources().getConfiguration());
        } else if ("fastForwardSpeed".equals(key)) {
            String value = prefs.getString(key, "2x");
            this.fastForwardSpeed = Float.parseFloat(value.substring(0, value.length() - 1));
            if (this.inFastForward) {
                setGameSpeed(this.fastForwardSpeed);
            }
        } else if ("frameSkipMode".equals(key)) {
            this.emulator.setOption(key, prefs.getString(key, "auto"));
        } else if ("maxFrameSkips".equals(key)) {
            this.emulator.setOption(key, Integer.toString(prefs.getInt(key, 2)));
        } else if ("maxFramesAhead".equals(key)) {
            if (this.netPlayService != null) {
                this.netPlayService.setMaxFramesAhead(prefs.getInt(key, 0));
            }
        } else if ("autoSyncClient".equals(key) || "autoSyncClientInterval".equals(key)) {
            if (this.netPlayService != null && this.netPlayService.isServer()) {
                stopAutoSyncClient();
                if (this.sharedPrefs.getBoolean("autoSyncClient", false)) {
                    this.autoSyncClientInterval = Integer.valueOf(this.sharedPrefs.getString("autoSyncClientInterval", "30")).intValue();
                    this.autoSyncClientInterval *= MESSAGE_SYNC_CLIENT;
                    startAutoSyncClient();
                }
            }
        } else if ("refreshRate".equals(key)) {
            this.emulator.setOption(key, prefs.getString(key, "default"));
        } else if ("enableLightGun".equals(key)) {
            this.lightGunEnabled = prefs.getBoolean(key, false);
            this.emulator.setOption(key, this.lightGunEnabled);
        } else if ("enableGamepad2".equals(key)) {
            this.emulator.setOption(key, prefs.getBoolean(key, false));
        } else if ("soundEnabled".equals(key)) {
            this.emulator.setOption(key, prefs.getBoolean(key, true));
        } else if ("soundVolume".equals(key)) {
            this.emulator.setOption(key, prefs.getInt(key, 100));
        } else if ("transparencyEnabled".equals(key)) {
            this.emulator.setOption(key, prefs.getBoolean(key, true));
        } else if ("enableHiRes".equals(key)) {
            this.emulator.setOption(key, prefs.getBoolean(key, true));
        } else if ("enableTrackball".equals(key)) {
            EmulatorView emulatorView = this.emulatorView;
            if (prefs.getBoolean(key, true)) {
                emulatorActivity = this;
            } else {
                emulatorActivity = null;
            }
            emulatorView.setOnTrackballListener(emulatorActivity);
        } else if ("trackballSensitivity".equals(key)) {
            this.trackballSensitivity = (prefs.getInt(key, 2) * 5) + 10;
        } else if ("useSensor".equals(key)) {
            this.sensorMappings = getSensorMappings(prefs.getString(key, null));
            if (this.sensorMappings == null) {
                this.sensor = null;
            } else if (this.sensor == null) {
                this.sensor = new SensorKeypad(this);
                this.sensor.setSensitivity(prefs.getInt("sensorSensitivity", 7));
            }
        } else if ("sensorSensitivity".equals(key)) {
            if (this.sensor != null) {
                this.sensor.setSensitivity(prefs.getInt(key, 7));
            }
        } else if ("enableVKeypad".equals(key)) {
            if (!prefs.getBoolean(key, true)) {
                if (this.vkeypad != null) {
                    this.vkeypad.destroy();
                    this.vkeypad = null;
                }
            } else if (this.vkeypad == null) {
                this.vkeypad = new VirtualKeypad(this.emulatorView, this);
            }
        } else if ("scalingMode".equals(key)) {
            this.emulatorView.setScalingMode(getScalingMode(prefs.getString(key, "proportional")));
        } else if ("aspectRatio".equals(key)) {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            float ratio = Float.parseFloat(prefs.getString(key, "1.3333"));
            if (ratio != 0.0f) {
                float dpiRatio = metrics.xdpi / metrics.ydpi;
                if (dpiRatio < 1.6667f && dpiRatio > 0.6f) {
                    ratio *= dpiRatio;
                }
            }
            this.emulatorView.setAspectRatio(ratio);
        } else if ("enableCheats".equals(key)) {
            this.emulator.enableCheats(prefs.getBoolean(key, true));
        } else if ("orientation".equals(key)) {
            setRequestedOrientation(getScreenOrientation(prefs.getString(key, "landscape")));
        } else if ("useInputMethod".equals(key)) {
            Window window = getWindow();
            if (prefs.getBoolean(key, false)) {
                i = 0;
            } else {
                i = 131072;
            }
            window.setFlags(i, 131072);
        } else if ("quickLoad".equals(key)) {
            this.quickLoadKey = prefs.getInt(key, 0);
        } else if ("quickSave".equals(key)) {
            this.quickSaveKey = prefs.getInt(key, 0);
        } else if ("fastForward".equals(key)) {
            this.fastForwardKey = prefs.getInt(key, 0);
        } else if ("screenshot".equals(key)) {
            this.screenshotKey = prefs.getInt(key, 0);
        }
    }

    @Override // com.androidemu.harveshihun.input.GameKeyListener
    public void onGameKeyChanged() {
        int states = this.keyboard.getKeyStates();
        if (this.sensor != null) {
            int keys = this.sensor.getKeyStates();
            if ((keys & 1) != 0) {
                states |= this.sensorMappings[0];
            }
            if ((keys & 2) != 0) {
                states |= this.sensorMappings[1];
            }
            if ((keys & 4) != 0) {
                states |= this.sensorMappings[2];
            }
            if ((keys & 8) != 0) {
                states |= this.sensorMappings[3];
            }
        }
        if (this.flipScreen) {
            states = flipGameKeys(states);
        }
        if (this.vkeypad != null) {
            states |= this.vkeypad.getKeyStates();
        }
        if ((states & GAMEPAD_LEFT_RIGHT) == GAMEPAD_LEFT_RIGHT) {
            states &= -769;
        }
        if ((states & GAMEPAD_UP_DOWN) == GAMEPAD_UP_DOWN) {
            states &= -3073;
        }
        this.emulator.setKeyStates(states);
    }

    @Override // com.androidemu.EmulatorView.OnTrackballListener
    public boolean onTrackball(MotionEvent event) {
        float dx = event.getX();
        float dy = event.getY();
        if (this.flipScreen) {
            dx = -dx;
            dy = -dy;
        }
        int duration1 = (int) (((float) this.trackballSensitivity) * dx);
        int duration2 = (int) (((float) this.trackballSensitivity) * dy);
        int key1 = 0;
        int key2 = 0;
        if (duration1 < 0) {
            key1 = Emulator.GAMEPAD_LEFT;
        } else if (duration1 > 0) {
            key1 = Emulator.GAMEPAD_RIGHT;
        }
        if (duration2 < 0) {
            key2 = Emulator.GAMEPAD_UP;
        } else if (duration2 > 0) {
            key2 = Emulator.GAMEPAD_DOWN;
        }
        if (key1 == 0 && key2 == 0) {
            return false;
        }
        this.emulator.processTrackball(key1, Math.abs(duration1), key2, Math.abs(duration2));
        return true;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) {
        this.emulator.setSurface(holder);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (this.vkeypad != null) {
            this.vkeypad.destroy();
        }
        this.emulator.setSurface(null);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.surfaceWidth = width;
        this.surfaceHeight = height;
        if (this.vkeypad != null) {
            this.vkeypad.resize(width, height);
        }
        int w = this.emulator.getVideoWidth();
        int h = this.emulator.getVideoHeight();
        this.surfaceRegion.left = (width - w) / 2;
        this.surfaceRegion.top = (height - h) / 2;
        this.surfaceRegion.right = this.surfaceRegion.left + w;
        this.surfaceRegion.bottom = this.surfaceRegion.top + h;
        this.emulator.setSurfaceRegion(this.surfaceRegion.left, this.surfaceRegion.top, w, h);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        if (this.vkeypad != null) {
            return this.vkeypad.onTouch(event, this.flipScreen);
        }
        if (this.lightGunEnabled && event.getAction() == 0) {
            int x = (((int) event.getX()) * this.surfaceWidth) / this.emulatorView.getWidth();
            int y = (((int) event.getY()) * this.surfaceHeight) / this.emulatorView.getHeight();
            if (this.flipScreen) {
                x = this.surfaceWidth - x;
                y = this.surfaceHeight - y;
            }
            if (this.surfaceRegion.contains(x, y)) {
                this.emulator.fireLightGun(x - this.surfaceRegion.left, y - this.surfaceRegion.top);
                return true;
            }
        }
        return false;
    }

    public void pauseEmulator() {
        this.emulator.pause();
    }

    public void resumeEmulator() {
        if (hasWindowFocus()) {
            this.emulator.resume();
        }
    }

    private boolean checkBluetoothEnabled(int request) {
        if (Wrapper.isBluetoothEnabled()) {
            return true;
        }
        startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), request);
        return false;
    }

    private void setFlipScreen(SharedPreferences prefs, Configuration config) {
        if (config.orientation == 2) {
            this.flipScreen = prefs.getBoolean("flipScreen", false);
        } else {
            this.flipScreen = false;
        }
        this.emulator.setOption("flipScreen", this.flipScreen);
    }

    private int flipGameKeys(int keys) {
        int newKeys = keys & -3841;
        if ((keys & Emulator.GAMEPAD_LEFT) != 0) {
            newKeys |= Emulator.GAMEPAD_RIGHT;
        }
        if ((keys & Emulator.GAMEPAD_RIGHT) != 0) {
            newKeys |= Emulator.GAMEPAD_LEFT;
        }
        if ((keys & Emulator.GAMEPAD_UP) != 0) {
            newKeys |= Emulator.GAMEPAD_DOWN;
        }
        if ((keys & Emulator.GAMEPAD_DOWN) != 0) {
            return newKeys | Emulator.GAMEPAD_UP;
        }
        return newKeys;
    }

    private static int getScalingMode(String mode) {
        if (mode.equals("original")) {
            return 0;
        }
        if (mode.equals("2x")) {
            return 1;
        }
        if (mode.equals("proportional")) {
            return 2;
        }
        return 3;
    }

    private static int getScreenOrientation(String orientation) {
        if (orientation.equals("landscape")) {
            return 0;
        }
        if (orientation.equals("portrait")) {
            return 1;
        }
        return -1;
    }

    static {
        int[] iArr = new int[4];
        iArr[0] = 32;
        iArr[1] = 16;
        SENSOR_MAP_TRIGGERS = iArr;
    }

    private static int[] getSensorMappings(String as) {
        if ("dpad".equals(as)) {
            return SENSOR_MAP_DPAD;
        }
        if ("triggers".equals(as)) {
            return SENSOR_MAP_TRIGGERS;
        }
        return null;
    }

    private String getEmulatorEngine(SharedPreferences prefs) {
        return prefs.getBoolean("useCCore", false) ? "snes_comp" : "snes";
    }

    private void loadKeyBindings(SharedPreferences prefs) {
        int[] gameKeys = EmulatorSettings.gameKeys;
        int[] defaultKeys = DefaultPreferences.getKeyMappings(this);
        this.keyboard.clearKeyMap();
        String[] gameKeysPref = EmulatorSettings.gameKeysPref;
        for (int i = 0; i < gameKeysPref.length; i++) {
            this.keyboard.mapKey(gameKeys[i], prefs.getInt(gameKeysPref[i], defaultKeys[i]));
        }
        String[] gameKeysPref2 = EmulatorSettings.gameKeysPref2;
        for (int i2 = 0; i2 < gameKeysPref2.length; i2++) {
            this.keyboard.mapKey(gameKeys[i2] << 16, prefs.getInt(gameKeysPref2[i2], 0));
        }
        this.keyboard.mapKey(1, prefs.getInt("gamepad_superscope_turbo", 0));
        this.keyboard.mapKey(2, prefs.getInt("gamepad_superscope_pause", 0));
        this.keyboard.mapKey(4, prefs.getInt("gamepad_superscope_cursor", 0));
    }

    private Dialog createQuitGameDialog() {
        return new AlertDialog.Builder(this).setTitle(R.string.quit_game_title).setItems(R.array.exit_game_options, new DialogInterface.OnClickListener() { // from class: com.androidemu.harveshihun.EmulatorActivity.3
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 1:
                        EmulatorActivity.this.quickSave();
                        break;
                    case 2:
                        break;
                    default:
                        return;
                }
                EmulatorActivity.this.finish();
            }
        }).create();
    }

    private Dialog createReplaceGameDialog() {
        DialogInterface.OnClickListener l = new DialogInterface.OnClickListener() { // from class: com.androidemu.harveshihun.EmulatorActivity.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                if (which == -1) {
                    EmulatorActivity.this.setIntent(EmulatorActivity.this.newIntent);
                    EmulatorActivity.this.loadROM();
                }
                EmulatorActivity.this.newIntent = null;
            }
        };
        return new AlertDialog.Builder(this).setCancelable(false).setTitle(R.string.replace_game_title).setMessage(R.string.replace_game_message).setPositiveButton(17039379, l).setNegativeButton(17039369, l).create();
    }

    private Dialog createWifiConnectDialog() {
        return new AlertDialog.Builder(this).setTitle(R.string.wifi_client).setView(getLayoutInflater().inflate(R.layout.wifi_connect, (ViewGroup) null)).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.androidemu.harveshihun.EmulatorActivity.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                Dialog d = (Dialog) dialog;
                EmulatorActivity.this.onWifiConnect(((TextView) d.findViewById(R.id.ip_address)).getText().toString(), ((TextView) d.findViewById(R.id.port)).getText().toString());
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
    }

    private String getROMFilePath() {
        return getIntent().getData().getPath();
    }

    private boolean isROMSupported(String file) {
        String file2 = file.toLowerCase();
        for (String f : getResources().getStringArray(R.array.file_chooser_filters)) {
            if (file2.endsWith(f)) {
                return true;
            }
        }
        return false;
    }

    public boolean loadROM() {
        String path = getROMFilePath();
        if (!isROMSupported(path)) {
            Toast.makeText(this, (int) R.string.rom_not_supported, 0).show();
            finish();
            return false;
        } else if (!this.emulator.loadROM(path)) {
            Toast.makeText(this, (int) R.string.load_rom_failed, 0).show();
            finish();
            return false;
        } else {
            this.inFastForward = false;
            this.emulatorView.setActualSize(this.emulator.getVideoWidth(), this.emulator.getVideoHeight());
            if (this.sharedPrefs.getBoolean("quickLoadOnStart", true)) {
                quickLoad();
            }
            return true;
        }
    }

    private Dialog createNetWaitDialog(CharSequence title, CharSequence message) {
        if (this.waitDialog != null) {
            this.waitDialog.dismiss();
            this.waitDialog = null;
        }
        this.waitDialog = new NetWaitDialog();
        this.waitDialog.setTitle(title);
        this.waitDialog.setMessage(message);
        return this.waitDialog;
    }

    public void ensureDiscoverable() {
        if (!Wrapper.isBluetoothDiscoverable()) {
            startActivity(new Intent("android.bluetooth.adapter.action.REQUEST_DISCOVERABLE"));
        }
    }

    private void onWifiServer() {
        int ip;
        WifiManager wifi = (WifiManager) getSystemService("wifi");
        WifiInfo info = wifi != null ? wifi.getConnectionInfo() : null;
        if (info != null) {
            ip = info.getIpAddress();
        } else {
            ip = 0;
        }
        if (ip == 0) {
            Toast.makeText(this, (int) R.string.wifi_not_available, 0).show();
            return;
        }
        InetAddress addr = null;
        try {
            addr = InetAddress.getByAddress(new byte[]{(byte) ip, (byte) (ip >>> 8), (byte) (ip >>> 16), (byte) (ip >>> 24)});
        } catch (UnknownHostException e) {
        }
        try {
            NetPlayService np = new NetPlayService(this.netPlayHandler);
            int port = np.tcpListen(addr, NETPLAY_TCP_PORT);
            this.netPlayService = np;
            createNetWaitDialog(getText(R.string.wifi_server), getString(R.string.wifi_server_listening, new Object[]{addr.getHostAddress(), Integer.valueOf(port)})).show();
        } catch (IOException e2) {
        }
    }

    public void onWifiConnect(String ip, String portStr) {
        InetAddress addr = null;
        try {
            if (InetAddressUtils.isIPv4Address(ip)) {
                addr = InetAddress.getByName(ip);
            }
        } catch (UnknownHostException e) {
        }
        if (addr == null) {
            Toast.makeText(this, (int) R.string.invalid_ip_address, 0).show();
            return;
        }
        int port = 0;
        try {
            port = Integer.parseInt(portStr);
        } catch (NumberFormatException e2) {
        }
        if (port <= 0) {
            Toast.makeText(this, (int) R.string.invalid_port, 0).show();
            return;
        }
        this.netPlayService = new NetPlayService(this.netPlayHandler);
        this.netPlayService.tcpConnect(addr, port);
        createNetWaitDialog(getText(R.string.wifi_client), getString(R.string.client_connecting)).show();
    }

    private void onBluetoothServer() {
        try {
            NetPlayService np = new NetPlayService(this.netPlayHandler);
            np.bluetoothListen();
            this.netPlayService = np;
            createNetWaitDialog(getText(R.string.bluetooth_server), getString(R.string.bluetooth_server_listening));
            this.waitDialog.setOnClickListener(new DialogInterface.OnClickListener() { // from class: com.androidemu.harveshihun.EmulatorActivity.6
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int button) {
                    EmulatorActivity.this.ensureDiscoverable();
                }
            });
            this.waitDialog.show();
        } catch (IOException e) {
        }
    }

    private void onBluetoothConnect(String address) {
        try {
            NetPlayService np = new NetPlayService(this.netPlayHandler);
            np.bluetoothConnect(address);
            this.netPlayService = np;
            createNetWaitDialog(getText(R.string.bluetooth_client), getString(R.string.client_connecting)).show();
        } catch (IOException e) {
        }
    }

    private void onBluetoothClient() {
        startActivityForResult(new Intent(this, DeviceListActivity.class), 5);
    }

    public void onNetPlaySync() {
        pauseEmulator();
        File file = getTempStateFile();
        try {
            this.emulator.saveState(file.getAbsolutePath());
            this.netPlayService.sendSavedState(readFile(file));
        } catch (IOException e) {
        }
        file.delete();
        resumeEmulator();
    }

    public void onDisconnect() {
        if (this.netPlayService != null) {
            onSharedPreferenceChanged(this.sharedPrefs, "enableCheats");
            onSharedPreferenceChanged(this.sharedPrefs, "enableGamepad2");
            stopAutoSyncClient();
            this.emulator.setFrameUpdateListener(null);
            this.netPlayService.disconnect();
            this.netPlayService = null;
        }
    }

    private void onLoadState() {
        Intent intent = new Intent(this, StateSlotsActivity.class);
        intent.setData(getIntent().getData());
        startActivityForResult(intent, 1);
    }

    private void onSaveState() {
        Intent intent = new Intent(this, StateSlotsActivity.class);
        intent.setData(getIntent().getData());
        intent.putExtra(StateSlotsActivity.EXTRA_SAVE_MODE, true);
        startActivityForResult(intent, 2);
    }

    public void applyNetplaySettings() {
        this.emulator.setOption("enableGamepad2", true);
        this.emulator.setOption("enableCheats", false);
        onSharedPreferenceChanged(this.sharedPrefs, "maxFramesAhead");
        onSharedPreferenceChanged(this.sharedPrefs, "autoSyncClient");
        if (this.inFastForward) {
            this.inFastForward = false;
            setGameSpeed(1.0f);
        }
    }

    public void startAutoSyncClient() {
        this.netPlayHandler.sendMessageDelayed(this.netPlayHandler.obtainMessage(MESSAGE_SYNC_CLIENT), (long) this.autoSyncClientInterval);
    }

    private void stopAutoSyncClient() {
        this.netPlayHandler.removeMessages(MESSAGE_SYNC_CLIENT);
    }

    private void setGameSpeed(float speed) {
        pauseEmulator();
        this.emulator.setOption("gameSpeed", Float.toString(speed));
        resumeEmulator();
    }

    private void onFastForward() {
        if (this.netPlayService == null) {
            this.inFastForward = !this.inFastForward;
            setGameSpeed(this.inFastForward ? this.fastForwardSpeed : 1.0f);
        }
    }

    private void onScreenshot() {
        Throwable th;
        File dir = new File("/sdcard/screenshot");
        if (dir.exists() || dir.mkdir()) {
            File file = new File(dir, String.valueOf(Long.toString(System.currentTimeMillis())) + ".png");
            pauseEmulator();
            FileOutputStream out = null;
            try {
                FileOutputStream out2 = new FileOutputStream(file);
                try {
                    Bitmap bitmap = getScreenshot();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out2);
                    bitmap.recycle();
                    Toast.makeText(this, (int) R.string.screenshot_saved, 0).show();
                    if (this.mediaScanner == null) {
                        this.mediaScanner = new MediaScanner(this);
                    }
                    this.mediaScanner.scanFile(file.getAbsolutePath(), "image/png");
                    if (out2 != null) {
                        try {
                            out2.close();
                        } catch (IOException e) {
                        }
                    }
                    resumeEmulator();
                } catch (Throwable th2) {
                    th = th2;
                    out = out2;
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } else {
            Log.w(LOG_TAG, "Could not create directory for screenshots");
        }
    }

    public File getTempStateFile() {
        return new File(getCacheDir(), "saved_state");
    }

    private static byte[] readFile(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        try {
            if (in.read(buffer) != -1) {
                return buffer;
            }
            throw new IOException();
        } finally {
            in.close();
        }
    }

    public static void writeFile(File file, byte[] buffer) throws IOException {
        FileOutputStream out = new FileOutputStream(file);
        try {
            out.write(buffer);
        } finally {
            out.close();
        }
    }

    private void saveState(String fileName) {
        Throwable th;
        pauseEmulator();
        ZipOutputStream out = null;
        try {
            ZipOutputStream out2 = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
            try {
                out2.putNextEntry(new ZipEntry("screenshot.png"));
                Bitmap bitmap = getScreenshot();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out2);
                bitmap.recycle();
                if (out2 != null) {
                    try {
                        out2.close();
                    } catch (Exception e) {
                    }
                }
                this.emulator.saveState(fileName);
                resumeEmulator();
            } catch (Throwable th2) {
                th = th2;
                out = out2;
                if (out != null) {
                    try {
                        out.close();
                    } catch (Exception e2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private void loadState(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            pauseEmulator();
            try {
                if (this.netPlayService != null) {
                    this.netPlayService.sendSavedState(readFile(file));
                }
                this.emulator.loadState(fileName);
            } catch (IOException e) {
            }
            resumeEmulator();
        }
    }

    private Bitmap getScreenshot() {
        int w = this.emulator.getVideoWidth();
        int h = this.emulator.getVideoHeight();
        ByteBuffer buffer = ByteBuffer.allocateDirect(w * h * 2);
        this.emulator.getScreenshot(buffer);
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        bitmap.copyPixelsFromBuffer(buffer);
        return bitmap;
    }

    private String getQuickSlotFileName() {
        return StateSlotsActivity.getSlotFileName(getROMFilePath(), 0);
    }

    public void quickSave() {
        saveState(getQuickSlotFileName());
    }

    private void quickLoad() {
        loadState(getQuickSlotFileName());
    }

    /* loaded from: classes.dex */
    public class NetWaitDialog extends ProgressDialog implements DialogInterface.OnCancelListener {
        private DialogInterface.OnClickListener onClickListener;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NetWaitDialog() {
            super(r2);
            EmulatorActivity.this = r2;
            setIndeterminate(true);
            setCancelable(true);
            setOnCancelListener(this);
        }

        public void setOnClickListener(DialogInterface.OnClickListener l) {
            this.onClickListener = l;
        }

        @Override // android.app.Dialog, android.view.Window.Callback
        public boolean dispatchTouchEvent(MotionEvent event) {
            if (this.onClickListener == null || event.getAction() != 1) {
                return super.dispatchTouchEvent(event);
            }
            this.onClickListener.onClick(this, -1);
            return true;
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialog) {
            EmulatorActivity.this.waitDialog = null;
            EmulatorActivity.this.netPlayService.disconnect();
            EmulatorActivity.this.netPlayService = null;
        }
    }
}
