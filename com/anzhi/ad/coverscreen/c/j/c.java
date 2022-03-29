package com.anzhi.ad.coverscreen.c.j;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes.dex */
public final class c {
    static {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    public static String a(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str.substring(0, 4));
        stringBuffer.append(str.substring(str.length() - 4));
        return stringBuffer.toString();
    }

    public static String a(String str, String str2) {
        if (str == null) {
            return null;
        }
        return new String(c(str, str2));
    }

    public static String a(String str, String str2, boolean z) {
        if (str == null) {
            return null;
        }
        return a.a(b(str, str2), false);
    }

    private static byte[] b(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "DES");
            Cipher instance = Cipher.getInstance("DES");
            byte[] bytes = str.getBytes("utf-8");
            instance.init(1, secretKeySpec);
            return instance.doFinal(bytes);
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (InvalidKeyException e2) {
            return null;
        } catch (NoSuchAlgorithmException e3) {
            return null;
        } catch (BadPaddingException e4) {
            return null;
        } catch (IllegalBlockSizeException e5) {
            return null;
        } catch (NoSuchPaddingException e6) {
            return null;
        }
    }

    private static byte[] c(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "DES");
            Cipher instance = Cipher.getInstance("DES");
            byte[] a = a.a(str);
            instance.init(2, secretKeySpec);
            return instance.doFinal(a);
        } catch (InvalidKeyException e) {
            return null;
        } catch (NoSuchAlgorithmException e2) {
            return null;
        } catch (BadPaddingException e3) {
            return null;
        } catch (IllegalBlockSizeException e4) {
            return null;
        } catch (NoSuchPaddingException e5) {
            return null;
        }
    }
}
