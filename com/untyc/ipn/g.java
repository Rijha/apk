package com.untyc.ipn;

import com.androidemu.Emulator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes.dex */
public class g {
    private static String a;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("D").append("E").append("S");
        a = sb.toString();
    }

    private static String a(InputStream inputStream, OutputStream outputStream) {
        String str;
        Throwable th;
        CipherOutputStream cipherOutputStream = null;
        try {
            try {
                byte[] bArr = new byte[32];
                byte[] bArr2 = new byte[Emulator.GAMEPAD_DOWN];
                str = inputStream.read(bArr) != -1 ? new String(bArr) : "";
                try {
                    cipherOutputStream = new CipherOutputStream(outputStream, a(a(str)));
                    if (cipherOutputStream != null) {
                        while (true) {
                            try {
                                int read = inputStream.read(bArr2);
                                if (read == -1) {
                                    break;
                                }
                                cipherOutputStream.write(bArr2, 0, read);
                            } catch (Exception e) {
                                e = e;
                                cipherOutputStream = cipherOutputStream;
                                e.printStackTrace();
                                if (cipherOutputStream != null) {
                                    try {
                                        cipherOutputStream.close();
                                    } catch (Exception e2) {
                                    }
                                }
                                return str;
                            } catch (Throwable th2) {
                                th = th2;
                                if (cipherOutputStream != null) {
                                    try {
                                        cipherOutputStream.close();
                                    } catch (Exception e3) {
                                    }
                                }
                                throw th;
                            }
                        }
                        cipherOutputStream.flush();
                    }
                    if (cipherOutputStream != null) {
                        try {
                            cipherOutputStream.close();
                        } catch (Exception e4) {
                        }
                    }
                } catch (Exception e5) {
                    e = e5;
                }
            } catch (Exception e6) {
                e = e6;
                str = "";
            }
            return str;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x006e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump */
    public static String a(String str, String str2) {
        FileOutputStream fileOutputStream;
        Exception e;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        String str3 = "";
        if (new File(str).exists()) {
            File file = new File(str2);
            file.delete();
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    fileOutputStream = new FileOutputStream(str2);
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream = null;
                    fileInputStream2 = fileInputStream;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = null;
                }
            } catch (Exception e3) {
                e = e3;
                fileOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
                fileInputStream = null;
            }
            try {
                String a2 = a(fileInputStream, fileOutputStream);
                String a3 = l.a(file);
                if (a3 != null && !"".equals(a3)) {
                    if (a3.equals(a2)) {
                        str3 = a2;
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
            } catch (Exception e6) {
                e = e6;
                fileInputStream2 = fileInputStream;
                try {
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e7) {
                            e7.printStackTrace();
                        }
                    }
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                    }
                    return str3;
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = fileInputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e9) {
                            e9.printStackTrace();
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e10) {
                            e10.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                if (fileOutputStream != null) {
                }
                if (fileInputStream != null) {
                }
                throw th;
            }
        }
        return str3;
    }

    private static Key a(String str) {
        try {
            byte[] bytes = str.getBytes();
            byte[] bArr = new byte[8];
            int i = 0;
            while (i < bArr.length && i < bytes.length) {
                bArr[i] = bytes[i];
                i++;
            }
            return new SecretKeySpec(bArr, a);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Cipher a(Key key) {
        Cipher instance = Cipher.getInstance(a);
        instance.init(2, key);
        return instance;
    }
}
