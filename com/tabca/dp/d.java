package com.tabca.dp;

import android.content.Context;
import dalvik.system.DexClassLoader;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public final class d {
    private static d e;
    private static String f = "";
    private DexClassLoader a;
    private Class b;
    private Object c;
    private String d;

    public static d a(Context context, String str) {
        if (e == null) {
            d dVar = new d();
            e = dVar;
            dVar.a(context);
            e.b(str);
        } else if (!e.d.equals(str)) {
            e.a.clearAssertionStatus();
            e.a(context);
            e.b(str);
        }
        return e;
    }

    private void a(Context context) {
        InputStream inputStream;
        DataInputStream dataInputStream;
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2 = null;
        if ("".equals(f)) {
            try {
                f = context.getDir("mdexk", 0).getAbsolutePath() + "/";
                File file = new File(f + "ghkn.zip");
                inputStream = context.getAssets().open("local");
                try {
                    dataInputStream = new DataInputStream(inputStream);
                    try {
                        int readInt = dataInputStream.readInt();
                        if (!file.exists() || file.length() != ((long) readInt)) {
                            byte[] bArr = new byte[readInt];
                            file.createNewFile();
                            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                            try {
                                dataInputStream.read(bArr);
                                bufferedOutputStream.write(bArr);
                                bufferedOutputStream.close();
                                dataInputStream.close();
                                inputStream.close();
                                try {
                                    dataInputStream.close();
                                } catch (IOException e2) {
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e3) {
                                    }
                                }
                                try {
                                    bufferedOutputStream.close();
                                } catch (IOException e4) {
                                }
                            } catch (Exception e5) {
                                if (dataInputStream != null) {
                                    try {
                                        dataInputStream.close();
                                    } catch (IOException e6) {
                                    }
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e7) {
                                    }
                                }
                                if (bufferedOutputStream != null) {
                                    try {
                                        bufferedOutputStream.close();
                                    } catch (IOException e8) {
                                    }
                                }
                                this.a = new DexClassLoader(f + "ghkn.zip", f, null, context.getClassLoader());
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedOutputStream2 = bufferedOutputStream;
                                if (dataInputStream != null) {
                                    try {
                                        dataInputStream.close();
                                    } catch (IOException e9) {
                                    }
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e10) {
                                    }
                                }
                                if (bufferedOutputStream2 != null) {
                                    try {
                                        bufferedOutputStream2.close();
                                    } catch (IOException e11) {
                                    }
                                }
                                throw th;
                            }
                        } else {
                            try {
                                dataInputStream.close();
                            } catch (IOException e12) {
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e13) {
                                }
                            }
                        }
                    } catch (Exception e14) {
                        bufferedOutputStream = null;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Exception e15) {
                    bufferedOutputStream = null;
                    dataInputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                    dataInputStream = null;
                }
            } catch (Exception e16) {
                bufferedOutputStream = null;
                dataInputStream = null;
                inputStream = null;
            } catch (Throwable th5) {
                th = th5;
                dataInputStream = null;
                inputStream = null;
            }
        }
        this.a = new DexClassLoader(f + "ghkn.zip", f, null, context.getClassLoader());
    }

    private void b(String str) {
        this.d = str;
        try {
            this.b = this.a.loadClass(str);
            this.c = this.b.getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e2) {
        }
    }

    public final Object a(String str) {
        return a(str, new Object[0], new Class[0]);
    }

    public final Object a(String str, Object obj, Class cls) {
        return a(str, new Object[]{obj}, new Class[]{cls});
    }

    public final Object a(String str, Object[] objArr, Class[] clsArr) {
        try {
            Method declaredMethod = this.b.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(this.c, objArr);
        } catch (Exception e2) {
            return null;
        }
    }
}
