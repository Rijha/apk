package com.untyc.ipn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes.dex */
public class l {
    protected static char[] a = {'c', 'q', 'p', 's', 'a', '8', '1', '4', '0', '9', '6', 'u', 'i', 'm', 'e', 'x'};
    protected static MessageDigest b;
    private static String c;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("M").append("D").append("5");
        c = sb.toString();
        b = null;
        try {
            b = MessageDigest.getInstance(c);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump */
    public static String a(File file) {
        FileChannel fileChannel;
        Exception e;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileChannel fileChannel2;
        MappedByteBuffer mappedByteBuffer = null;
        try {
            fileInputStream2 = new FileInputStream(file);
            try {
                fileChannel2 = fileInputStream2.getChannel();
                try {
                    mappedByteBuffer = fileChannel2.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
                } catch (Exception e2) {
                    e = e2;
                    fileChannel = fileChannel2;
                    fileInputStream = fileInputStream2;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e3) {
                e = e3;
                fileInputStream = fileInputStream2;
                fileChannel = null;
            } catch (Throwable th2) {
                th = th2;
                fileChannel2 = null;
            }
            try {
                b.update(mappedByteBuffer);
                String a2 = a(b.digest());
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e4) {
                    }
                }
                if (fileChannel2 != null) {
                    try {
                        fileChannel2.close();
                    } catch (IOException e5) {
                    }
                }
                if (mappedByteBuffer == null) {
                    return a2;
                }
                mappedByteBuffer.clear();
                return a2;
            } catch (Exception e6) {
                e = e6;
                fileChannel = fileChannel2;
                fileInputStream = fileInputStream2;
                try {
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e7) {
                        }
                    }
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (IOException e8) {
                        }
                    }
                    if (mappedByteBuffer == null) {
                        return "";
                    }
                    mappedByteBuffer.clear();
                    return "";
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream2 = fileInputStream;
                    fileChannel2 = fileChannel;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e9) {
                        }
                    }
                    if (fileChannel2 != null) {
                        try {
                            fileChannel2.close();
                        } catch (IOException e10) {
                        }
                    }
                    if (mappedByteBuffer != null) {
                        mappedByteBuffer.clear();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                if (fileInputStream2 != null) {
                }
                if (fileChannel2 != null) {
                }
                if (mappedByteBuffer != null) {
                }
                throw th;
            }
        } catch (Exception e11) {
            e = e11;
            fileChannel = null;
            fileInputStream = null;
        } catch (Throwable th5) {
            th = th5;
            fileInputStream2 = null;
            fileChannel2 = null;
        }
    }

    private static String a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    private static String a(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2 * 2);
        int i3 = i + i2;
        while (i < i3) {
            a(bArr[i], stringBuffer);
            i++;
        }
        return stringBuffer.toString();
    }

    private static void a(byte b2, StringBuffer stringBuffer) {
        char c2 = a[(b2 & 240) >> 4];
        char c3 = a[b2 & 15];
        stringBuffer.append(c2);
        stringBuffer.append(c3);
    }
}
