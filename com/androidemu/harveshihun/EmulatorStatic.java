package com.androidemu.harveshihun;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
/* loaded from: classes.dex */
public class EmulatorStatic {
    public static final String K = "harveemu@#harve001";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class DESDecrypt {
        private static byte[] desKey = "harveemu@#harve001".getBytes();

        DESDecrypt() {
        }

        public static byte[] doDecrypt(byte[] encryptText) throws Exception {
            SecureRandom sr = new SecureRandom();
            SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(desKey));
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, key, sr);
            return cipher.doFinal(encryptText);
        }
    }

    public static String byte2hex(byte[] value) {
        String newString = "";
        for (byte b : value) {
            String str = Integer.toHexString(b);
            if (str.length() > 2) {
                str = str.substring(str.length() - 2);
            }
            if (str.length() < 2) {
                str = "0" + str;
            }
            newString = String.valueOf(newString) + str;
        }
        return newString.toUpperCase();
    }

    public static byte[] hex2byte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        int j = 0;
        int l = hex.length();
        int i = 0;
        while (i < l) {
            int i2 = i + 1;
            b[j] = new Integer(Integer.parseInt(new StringBuilder().append(arr[i]).append(arr[i2]).toString(), 16) & 255).byteValue();
            j++;
            i = i2 + 1;
        }
        return b;
    }

    public static String DESDecrypt(String key, String encryptText) throws Exception {
        try {
            return new String(DESDecrypt.doDecrypt(hex2byte(encryptText)));
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getkey(String ekey) {
        try {
            return DESDecrypt("harveemu@#harve001", ekey);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println("key ---> " + getkey("553F1F6F2519792594A77EA6BBDB3D10F487896FF2AAF3F2D1AB2D73186A4F180829E7321C5A09DA"));
    }
}
