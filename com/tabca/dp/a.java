package com.tabca.dp;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import com.androidemu.Emulator;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
/* loaded from: classes.dex */
public final class a {
    private static boolean a = false;

    public static Class a(Context context, Class cls) {
        Class<?> cls2;
        try {
            for (ActivityInfo activityInfo : context.getPackageManager().getPackageInfo(context.getApplicationInfo().packageName, 1).activities) {
                try {
                    cls2 = Class.forName(activityInfo.name);
                } catch (ClassNotFoundException e) {
                }
                if (cls.isAssignableFrom(cls2)) {
                    return cls2;
                }
            }
        } catch (PackageManager.NameNotFoundException e2) {
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Context context) {
        DataInputStream dataInputStream;
        ZipInputStream zipInputStream;
        ZipInputStream zipInputStream2 = null;
        zipInputStream2 = null;
        DataInputStream dataInputStream2 = null;
        String str = "";
        try {
            str = new String(b.a("cHVzaGltZ3M="));
        } catch (IOException e) {
        }
        File dir = context.getDir(str, 0);
        int length = dir.listFiles().length;
        if (!dir.exists() || !(length == 17 || length == 18)) {
            try {
                String absolutePath = dir.getAbsolutePath();
                dataInputStream = new DataInputStream(context.getAssets().open("local"));
                try {
                    dataInputStream.read(new byte[dataInputStream.readInt()]);
                    dataInputStream.readInt();
                    zipInputStream = new ZipInputStream(dataInputStream);
                    while (true) {
                        try {
                            ZipEntry nextEntry = zipInputStream.getNextEntry();
                            if (nextEntry != null) {
                                String name = nextEntry.getName();
                                if (nextEntry.isDirectory()) {
                                    new File(absolutePath + File.separator + name.substring(0, name.length() - 1)).mkdirs();
                                } else {
                                    File file = new File(absolutePath + File.separator + name);
                                    file.createNewFile();
                                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                                    byte[] bArr = new byte[Emulator.GAMEPAD_DOWN];
                                    while (true) {
                                        int read = zipInputStream.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        fileOutputStream.write(bArr, 0, read);
                                        fileOutputStream.flush();
                                    }
                                    fileOutputStream.close();
                                }
                            } else {
                                try {
                                    break;
                                } catch (IOException e2) {
                                }
                            }
                        } catch (Exception e3) {
                            zipInputStream2 = zipInputStream;
                            if (zipInputStream2 != null) {
                                try {
                                    zipInputStream2.close();
                                } catch (IOException e4) {
                                }
                            }
                            if (dataInputStream != null) {
                                try {
                                    dataInputStream.close();
                                    return;
                                } catch (IOException e5) {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } catch (Throwable th) {
                            th = th;
                            dataInputStream2 = dataInputStream;
                            if (zipInputStream != null) {
                                try {
                                    zipInputStream.close();
                                } catch (IOException e6) {
                                }
                            }
                            if (dataInputStream2 != null) {
                                try {
                                    dataInputStream2.close();
                                } catch (IOException e7) {
                                }
                            }
                            throw th;
                        }
                    }
                    zipInputStream.close();
                    try {
                        dataInputStream.close();
                    } catch (IOException e8) {
                    }
                } catch (Exception e9) {
                } catch (Throwable th2) {
                    th = th2;
                    zipInputStream = null;
                    dataInputStream2 = dataInputStream;
                }
            } catch (Exception e10) {
                dataInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                zipInputStream = null;
            }
        }
    }

    public static Class b(Context context, Class cls) {
        Class<?> cls2;
        try {
            ServiceInfo[] serviceInfoArr = context.getPackageManager().getPackageInfo(context.getApplicationInfo().packageName, 4).services;
            if (serviceInfoArr != null) {
                for (ServiceInfo serviceInfo : serviceInfoArr) {
                    try {
                        cls2 = Class.forName(serviceInfo.name);
                    } catch (ClassNotFoundException e) {
                    }
                    if (cls.isAssignableFrom(cls2)) {
                        return cls2;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e2) {
        }
        return null;
    }
}
