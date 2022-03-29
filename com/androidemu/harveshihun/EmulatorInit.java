package com.androidemu.harveshihun;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public class EmulatorInit {
    public static final String DIR_NAME;
    public static final String FILE_NAME;
    private static final String TAG;

    public static void init(final MainActivity context) {
        context.setVisible(false);
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("加载中,请稍候...");
        try {
            dialog.show();
        } catch (Throwable th) {
        }
        File dir = new File(Environment.getExternalStorageDirectory() + DIR_NAME);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        final Handler handler = new Handler() { // from class: com.androidemu.harveshihun.EmulatorInit.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    context.onFileSelected(Uri.fromFile(new File(String.valueOf(EmulatorInit.getRomDir(context)) + EmulatorInit.FILE_NAME + ".smc")));
                    try {
                        dialog.dismiss();
                    } catch (Throwable th2) {
                    }
                    context.finish();
                } else if (msg.what == 2) {
                    Toast.makeText(context, "加载成功,祝您游戏愉快:)", 1).show();
                } else if (msg.what == 3) {
                    Toast.makeText(context, "请检查您的您的手机存储是否已满", 1).show();
                }
            }
        };
        new Thread(new Runnable() { // from class: com.androidemu.harveshihun.EmulatorInit.2
            @Override // java.lang.Runnable
            public void run() {
                File file = new File(String.valueOf(EmulatorInit.getRomDir(context)) + EmulatorInit.FILE_NAME + ".smc");
                if (file.exists()) {
                    handler.sendEmptyMessage(1);
                } else if (file.exists()) {
                } else {
                    if (EmulatorInit.copyFile2Rom(context, "harveshihun.png", "harveshihun.smc")) {
                        handler.sendEmptyMessage(2);
                        handler.sendEmptyMessage(1);
                        return;
                    }
                    handler.sendEmptyMessage(3);
                }
            }
        }).start();
    }

    public static boolean copyFile2Rom(Context context, String srcFile, String desName) {
        File filePath = new File(getRomDir(context));
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        try {
            InputStream in = context.getAssets().open(srcFile, 2);
            FileOutputStream out = new FileOutputStream(new File(filePath, desName));
            EmulatorProvider.c(context);
            byte[] buf = new byte[10240];
            while (true) {
                int len = in.read(buf);
                if (len <= 0) {
                    out.flush();
                    out.close();
                    in.close();
                    Log.d("snes", "copy file success.");
                    return true;
                }
                out.write(buf, 0, len);
            }
        } catch (IOException e) {
            Log.e(TAG, "copyFile2Rom() fail.", e);
            return false;
        }
    }

    public static String getRomDir(Context context) {
        return "/data/data/" + context.getPackageName() + "/" + DIR_NAME + "/";
    }
}
