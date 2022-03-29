package com.anzhi.ad.coverscreen.c.j;

import com.androidemu.Emulator;
import java.util.Arrays;
/* loaded from: classes.dex */
public final class a {
    private static final char[] a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final int[] b;
    private static final byte[] c;

    static {
        int[] iArr = new int[Emulator.GAMEPAD_RIGHT];
        b = iArr;
        Arrays.fill(iArr, -1);
        int length = a.length;
        for (int i = 0; i < length; i++) {
            b[a[i]] = i;
        }
        b[61] = 0;
        byte[] bArr = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        c = new byte[Emulator.GAMEPAD_A];
        for (int i2 = 0; i2 < 128; i2++) {
            c[i2] = -1;
        }
        for (int i3 = 65; i3 <= 90; i3++) {
            c[i3] = (byte) (i3 - 65);
        }
        for (int i4 = 97; i4 <= 122; i4++) {
            c[i4] = (byte) ((i4 - 97) + 26);
        }
        for (int i5 = 48; i5 <= 57; i5++) {
            c[i5] = (byte) ((i5 - 48) + 52);
        }
        c[43] = 62;
        c[47] = 63;
    }

    public static final String a(byte[] bArr, boolean z) {
        return new String(b(bArr, z));
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0081 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump */
    public static final byte[] a(String str) {
        boolean z;
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            byte charAt = (byte) str.charAt(i2);
            if (charAt != 61) {
                if (charAt < 0 || charAt >= 128) {
                    z = false;
                } else if (c[charAt] == -1) {
                    z = false;
                }
                if (!z) {
                    stringBuffer.append(str.charAt(i2));
                }
            }
            z = true;
            if (!z) {
            }
        }
        String stringBuffer2 = stringBuffer.toString();
        byte[] bArr = stringBuffer2.charAt(stringBuffer2.length() + -2) == '=' ? new byte[(((stringBuffer2.length() / 4) - 1) * 3) + 1] : stringBuffer2.charAt(stringBuffer2.length() + -1) == '=' ? new byte[(((stringBuffer2.length() / 4) - 1) * 3) + 2] : new byte[(stringBuffer2.length() / 4) * 3];
        int i3 = 0;
        while (i3 < stringBuffer2.length() - 4) {
            byte b2 = c[stringBuffer2.charAt(i3)];
            byte b3 = c[stringBuffer2.charAt(i3 + 1)];
            byte b4 = c[stringBuffer2.charAt(i3 + 2)];
            byte b5 = c[stringBuffer2.charAt(i3 + 3)];
            bArr[i] = (byte) ((b2 << 2) | (b3 >> 4));
            bArr[i + 1] = (byte) ((b3 << 4) | (b4 >> 2));
            bArr[i + 2] = (byte) ((b4 << 6) | b5);
            i3 += 4;
            i += 3;
        }
        if (stringBuffer2.charAt(stringBuffer2.length() - 2) == '=') {
            bArr[bArr.length - 1] = (byte) ((c[stringBuffer2.charAt(stringBuffer2.length() - 4)] << 2) | (c[stringBuffer2.charAt(stringBuffer2.length() - 3)] >> 4));
        } else if (stringBuffer2.charAt(stringBuffer2.length() - 1) == '=') {
            byte b6 = c[stringBuffer2.charAt(stringBuffer2.length() - 4)];
            byte b7 = c[stringBuffer2.charAt(stringBuffer2.length() - 3)];
            byte b8 = c[stringBuffer2.charAt(stringBuffer2.length() - 2)];
            bArr[bArr.length - 2] = (byte) ((b6 << 2) | (b7 >> 4));
            bArr[bArr.length - 1] = (byte) ((b7 << 4) | (b8 >> 2));
        } else {
            byte b9 = c[stringBuffer2.charAt(stringBuffer2.length() - 4)];
            byte b10 = c[stringBuffer2.charAt(stringBuffer2.length() - 3)];
            byte b11 = c[stringBuffer2.charAt(stringBuffer2.length() - 2)];
            byte b12 = c[stringBuffer2.charAt(stringBuffer2.length() - 1)];
            bArr[bArr.length - 3] = (byte) ((b9 << 2) | (b10 >> 4));
            bArr[bArr.length - 2] = (byte) ((b10 << 4) | (b11 >> 2));
            bArr[bArr.length - 1] = (byte) ((b11 << 6) | b12);
        }
        return bArr;
    }

    private static char[] b(byte[] bArr, boolean z) {
        int i = 0;
        int length = bArr != null ? bArr.length : 0;
        if (length == 0) {
            return new char[0];
        }
        int i2 = (length / 3) * 3;
        int i3 = (((length - 1) / 3) + 1) << 2;
        int i4 = i3 + (z ? ((i3 - 1) / 76) << 1 : 0);
        char[] cArr = new char[i4];
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i7 < i2) {
            int i8 = i7 + 1;
            int i9 = i8 + 1;
            int i10 = ((bArr[i8] & 255) << 8) | ((bArr[i7] & 255) << 16);
            i7 = i9 + 1;
            int i11 = i10 | (bArr[i9] & 255);
            int i12 = i6 + 1;
            cArr[i6] = a[(i11 >>> 18) & 63];
            int i13 = i12 + 1;
            cArr[i12] = a[(i11 >>> 12) & 63];
            int i14 = i13 + 1;
            cArr[i13] = a[(i11 >>> 6) & 63];
            i6 = i14 + 1;
            cArr[i14] = a[i11 & 63];
            if (z && (i5 = i5 + 1) == 19 && i6 < i4 - 2) {
                int i15 = i6 + 1;
                cArr[i6] = '\r';
                i6 = i15 + 1;
                cArr[i15] = '\n';
                i5 = 0;
            }
        }
        int i16 = length - i2;
        if (i16 > 0) {
            int i17 = (bArr[i2] & 255) << 10;
            if (i16 == 2) {
                i = (bArr[length - 1] & 255) << 2;
            }
            int i18 = i | i17;
            cArr[i4 - 4] = a[i18 >> 12];
            cArr[i4 - 3] = a[(i18 >>> 6) & 63];
            cArr[i4 - 2] = i16 == 2 ? a[i18 & 63] : '=';
            cArr[i4 - 1] = '=';
        }
        return cArr;
    }
}
