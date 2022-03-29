package com.tabca.dp;

import com.androidemu.Emulator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
/* loaded from: classes.dex */
public class b {
    static final /* synthetic */ boolean a;
    private static final byte[] b;
    private static final byte[] c;
    private static final byte[] d;
    private static final byte[] e;
    private static final byte[] f;
    private static final byte[] g;

    static {
        a = !b.class.desiredAssertionStatus();
        b = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        byte[] bArr = new byte[Emulator.GAMEPAD_RIGHT];
        // fill-array-data instruction
        bArr[0] = -9;
        bArr[1] = -9;
        bArr[2] = -9;
        bArr[3] = -9;
        bArr[4] = -9;
        bArr[5] = -9;
        bArr[6] = -9;
        bArr[7] = -9;
        bArr[8] = -9;
        bArr[9] = -5;
        bArr[10] = -5;
        bArr[11] = -9;
        bArr[12] = -9;
        bArr[13] = -5;
        bArr[14] = -9;
        bArr[15] = -9;
        bArr[16] = -9;
        bArr[17] = -9;
        bArr[18] = -9;
        bArr[19] = -9;
        bArr[20] = -9;
        bArr[21] = -9;
        bArr[22] = -9;
        bArr[23] = -9;
        bArr[24] = -9;
        bArr[25] = -9;
        bArr[26] = -9;
        bArr[27] = -9;
        bArr[28] = -9;
        bArr[29] = -9;
        bArr[30] = -9;
        bArr[31] = -9;
        bArr[32] = -5;
        bArr[33] = -9;
        bArr[34] = -9;
        bArr[35] = -9;
        bArr[36] = -9;
        bArr[37] = -9;
        bArr[38] = -9;
        bArr[39] = -9;
        bArr[40] = -9;
        bArr[41] = -9;
        bArr[42] = -9;
        bArr[43] = 62;
        bArr[44] = -9;
        bArr[45] = -9;
        bArr[46] = -9;
        bArr[47] = 63;
        bArr[48] = 52;
        bArr[49] = 53;
        bArr[50] = 54;
        bArr[51] = 55;
        bArr[52] = 56;
        bArr[53] = 57;
        bArr[54] = 58;
        bArr[55] = 59;
        bArr[56] = 60;
        bArr[57] = 61;
        bArr[58] = -9;
        bArr[59] = -9;
        bArr[60] = -9;
        bArr[61] = -1;
        bArr[62] = -9;
        bArr[63] = -9;
        bArr[64] = -9;
        bArr[65] = 0;
        bArr[66] = 1;
        bArr[67] = 2;
        bArr[68] = 3;
        bArr[69] = 4;
        bArr[70] = 5;
        bArr[71] = 6;
        bArr[72] = 7;
        bArr[73] = 8;
        bArr[74] = 9;
        bArr[75] = 10;
        bArr[76] = 11;
        bArr[77] = 12;
        bArr[78] = 13;
        bArr[79] = 14;
        bArr[80] = 15;
        bArr[81] = 16;
        bArr[82] = 17;
        bArr[83] = 18;
        bArr[84] = 19;
        bArr[85] = 20;
        bArr[86] = 21;
        bArr[87] = 22;
        bArr[88] = 23;
        bArr[89] = 24;
        bArr[90] = 25;
        bArr[91] = -9;
        bArr[92] = -9;
        bArr[93] = -9;
        bArr[94] = -9;
        bArr[95] = -9;
        bArr[96] = -9;
        bArr[97] = 26;
        bArr[98] = 27;
        bArr[99] = 28;
        bArr[100] = 29;
        bArr[101] = 30;
        bArr[102] = 31;
        bArr[103] = 32;
        bArr[104] = 33;
        bArr[105] = 34;
        bArr[106] = 35;
        bArr[107] = 36;
        bArr[108] = 37;
        bArr[109] = 38;
        bArr[110] = 39;
        bArr[111] = 40;
        bArr[112] = 41;
        bArr[113] = 42;
        bArr[114] = 43;
        bArr[115] = 44;
        bArr[116] = 45;
        bArr[117] = 46;
        bArr[118] = 47;
        bArr[119] = 48;
        bArr[120] = 49;
        bArr[121] = 50;
        bArr[122] = 51;
        bArr[123] = -9;
        bArr[124] = -9;
        bArr[125] = -9;
        bArr[126] = -9;
        bArr[127] = -9;
        bArr[128] = -9;
        bArr[129] = -9;
        bArr[130] = -9;
        bArr[131] = -9;
        bArr[132] = -9;
        bArr[133] = -9;
        bArr[134] = -9;
        bArr[135] = -9;
        bArr[136] = -9;
        bArr[137] = -9;
        bArr[138] = -9;
        bArr[139] = -9;
        bArr[140] = -9;
        bArr[141] = -9;
        bArr[142] = -9;
        bArr[143] = -9;
        bArr[144] = -9;
        bArr[145] = -9;
        bArr[146] = -9;
        bArr[147] = -9;
        bArr[148] = -9;
        bArr[149] = -9;
        bArr[150] = -9;
        bArr[151] = -9;
        bArr[152] = -9;
        bArr[153] = -9;
        bArr[154] = -9;
        bArr[155] = -9;
        bArr[156] = -9;
        bArr[157] = -9;
        bArr[158] = -9;
        bArr[159] = -9;
        bArr[160] = -9;
        bArr[161] = -9;
        bArr[162] = -9;
        bArr[163] = -9;
        bArr[164] = -9;
        bArr[165] = -9;
        bArr[166] = -9;
        bArr[167] = -9;
        bArr[168] = -9;
        bArr[169] = -9;
        bArr[170] = -9;
        bArr[171] = -9;
        bArr[172] = -9;
        bArr[173] = -9;
        bArr[174] = -9;
        bArr[175] = -9;
        bArr[176] = -9;
        bArr[177] = -9;
        bArr[178] = -9;
        bArr[179] = -9;
        bArr[180] = -9;
        bArr[181] = -9;
        bArr[182] = -9;
        bArr[183] = -9;
        bArr[184] = -9;
        bArr[185] = -9;
        bArr[186] = -9;
        bArr[187] = -9;
        bArr[188] = -9;
        bArr[189] = -9;
        bArr[190] = -9;
        bArr[191] = -9;
        bArr[192] = -9;
        bArr[193] = -9;
        bArr[194] = -9;
        bArr[195] = -9;
        bArr[196] = -9;
        bArr[197] = -9;
        bArr[198] = -9;
        bArr[199] = -9;
        bArr[200] = -9;
        bArr[201] = -9;
        bArr[202] = -9;
        bArr[203] = -9;
        bArr[204] = -9;
        bArr[205] = -9;
        bArr[206] = -9;
        bArr[207] = -9;
        bArr[208] = -9;
        bArr[209] = -9;
        bArr[210] = -9;
        bArr[211] = -9;
        bArr[212] = -9;
        bArr[213] = -9;
        bArr[214] = -9;
        bArr[215] = -9;
        bArr[216] = -9;
        bArr[217] = -9;
        bArr[218] = -9;
        bArr[219] = -9;
        bArr[220] = -9;
        bArr[221] = -9;
        bArr[222] = -9;
        bArr[223] = -9;
        bArr[224] = -9;
        bArr[225] = -9;
        bArr[226] = -9;
        bArr[227] = -9;
        bArr[228] = -9;
        bArr[229] = -9;
        bArr[230] = -9;
        bArr[231] = -9;
        bArr[232] = -9;
        bArr[233] = -9;
        bArr[234] = -9;
        bArr[235] = -9;
        bArr[236] = -9;
        bArr[237] = -9;
        bArr[238] = -9;
        bArr[239] = -9;
        bArr[240] = -9;
        bArr[241] = -9;
        bArr[242] = -9;
        bArr[243] = -9;
        bArr[244] = -9;
        bArr[245] = -9;
        bArr[246] = -9;
        bArr[247] = -9;
        bArr[248] = -9;
        bArr[249] = -9;
        bArr[250] = -9;
        bArr[251] = -9;
        bArr[252] = -9;
        bArr[253] = -9;
        bArr[254] = -9;
        bArr[255] = -9;
        c = bArr;
        d = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        byte[] bArr2 = new byte[Emulator.GAMEPAD_RIGHT];
        // fill-array-data instruction
        bArr2[0] = -9;
        bArr2[1] = -9;
        bArr2[2] = -9;
        bArr2[3] = -9;
        bArr2[4] = -9;
        bArr2[5] = -9;
        bArr2[6] = -9;
        bArr2[7] = -9;
        bArr2[8] = -9;
        bArr2[9] = -5;
        bArr2[10] = -5;
        bArr2[11] = -9;
        bArr2[12] = -9;
        bArr2[13] = -5;
        bArr2[14] = -9;
        bArr2[15] = -9;
        bArr2[16] = -9;
        bArr2[17] = -9;
        bArr2[18] = -9;
        bArr2[19] = -9;
        bArr2[20] = -9;
        bArr2[21] = -9;
        bArr2[22] = -9;
        bArr2[23] = -9;
        bArr2[24] = -9;
        bArr2[25] = -9;
        bArr2[26] = -9;
        bArr2[27] = -9;
        bArr2[28] = -9;
        bArr2[29] = -9;
        bArr2[30] = -9;
        bArr2[31] = -9;
        bArr2[32] = -5;
        bArr2[33] = -9;
        bArr2[34] = -9;
        bArr2[35] = -9;
        bArr2[36] = -9;
        bArr2[37] = -9;
        bArr2[38] = -9;
        bArr2[39] = -9;
        bArr2[40] = -9;
        bArr2[41] = -9;
        bArr2[42] = -9;
        bArr2[43] = -9;
        bArr2[44] = -9;
        bArr2[45] = 62;
        bArr2[46] = -9;
        bArr2[47] = -9;
        bArr2[48] = 52;
        bArr2[49] = 53;
        bArr2[50] = 54;
        bArr2[51] = 55;
        bArr2[52] = 56;
        bArr2[53] = 57;
        bArr2[54] = 58;
        bArr2[55] = 59;
        bArr2[56] = 60;
        bArr2[57] = 61;
        bArr2[58] = -9;
        bArr2[59] = -9;
        bArr2[60] = -9;
        bArr2[61] = -1;
        bArr2[62] = -9;
        bArr2[63] = -9;
        bArr2[64] = -9;
        bArr2[65] = 0;
        bArr2[66] = 1;
        bArr2[67] = 2;
        bArr2[68] = 3;
        bArr2[69] = 4;
        bArr2[70] = 5;
        bArr2[71] = 6;
        bArr2[72] = 7;
        bArr2[73] = 8;
        bArr2[74] = 9;
        bArr2[75] = 10;
        bArr2[76] = 11;
        bArr2[77] = 12;
        bArr2[78] = 13;
        bArr2[79] = 14;
        bArr2[80] = 15;
        bArr2[81] = 16;
        bArr2[82] = 17;
        bArr2[83] = 18;
        bArr2[84] = 19;
        bArr2[85] = 20;
        bArr2[86] = 21;
        bArr2[87] = 22;
        bArr2[88] = 23;
        bArr2[89] = 24;
        bArr2[90] = 25;
        bArr2[91] = -9;
        bArr2[92] = -9;
        bArr2[93] = -9;
        bArr2[94] = -9;
        bArr2[95] = 63;
        bArr2[96] = -9;
        bArr2[97] = 26;
        bArr2[98] = 27;
        bArr2[99] = 28;
        bArr2[100] = 29;
        bArr2[101] = 30;
        bArr2[102] = 31;
        bArr2[103] = 32;
        bArr2[104] = 33;
        bArr2[105] = 34;
        bArr2[106] = 35;
        bArr2[107] = 36;
        bArr2[108] = 37;
        bArr2[109] = 38;
        bArr2[110] = 39;
        bArr2[111] = 40;
        bArr2[112] = 41;
        bArr2[113] = 42;
        bArr2[114] = 43;
        bArr2[115] = 44;
        bArr2[116] = 45;
        bArr2[117] = 46;
        bArr2[118] = 47;
        bArr2[119] = 48;
        bArr2[120] = 49;
        bArr2[121] = 50;
        bArr2[122] = 51;
        bArr2[123] = -9;
        bArr2[124] = -9;
        bArr2[125] = -9;
        bArr2[126] = -9;
        bArr2[127] = -9;
        bArr2[128] = -9;
        bArr2[129] = -9;
        bArr2[130] = -9;
        bArr2[131] = -9;
        bArr2[132] = -9;
        bArr2[133] = -9;
        bArr2[134] = -9;
        bArr2[135] = -9;
        bArr2[136] = -9;
        bArr2[137] = -9;
        bArr2[138] = -9;
        bArr2[139] = -9;
        bArr2[140] = -9;
        bArr2[141] = -9;
        bArr2[142] = -9;
        bArr2[143] = -9;
        bArr2[144] = -9;
        bArr2[145] = -9;
        bArr2[146] = -9;
        bArr2[147] = -9;
        bArr2[148] = -9;
        bArr2[149] = -9;
        bArr2[150] = -9;
        bArr2[151] = -9;
        bArr2[152] = -9;
        bArr2[153] = -9;
        bArr2[154] = -9;
        bArr2[155] = -9;
        bArr2[156] = -9;
        bArr2[157] = -9;
        bArr2[158] = -9;
        bArr2[159] = -9;
        bArr2[160] = -9;
        bArr2[161] = -9;
        bArr2[162] = -9;
        bArr2[163] = -9;
        bArr2[164] = -9;
        bArr2[165] = -9;
        bArr2[166] = -9;
        bArr2[167] = -9;
        bArr2[168] = -9;
        bArr2[169] = -9;
        bArr2[170] = -9;
        bArr2[171] = -9;
        bArr2[172] = -9;
        bArr2[173] = -9;
        bArr2[174] = -9;
        bArr2[175] = -9;
        bArr2[176] = -9;
        bArr2[177] = -9;
        bArr2[178] = -9;
        bArr2[179] = -9;
        bArr2[180] = -9;
        bArr2[181] = -9;
        bArr2[182] = -9;
        bArr2[183] = -9;
        bArr2[184] = -9;
        bArr2[185] = -9;
        bArr2[186] = -9;
        bArr2[187] = -9;
        bArr2[188] = -9;
        bArr2[189] = -9;
        bArr2[190] = -9;
        bArr2[191] = -9;
        bArr2[192] = -9;
        bArr2[193] = -9;
        bArr2[194] = -9;
        bArr2[195] = -9;
        bArr2[196] = -9;
        bArr2[197] = -9;
        bArr2[198] = -9;
        bArr2[199] = -9;
        bArr2[200] = -9;
        bArr2[201] = -9;
        bArr2[202] = -9;
        bArr2[203] = -9;
        bArr2[204] = -9;
        bArr2[205] = -9;
        bArr2[206] = -9;
        bArr2[207] = -9;
        bArr2[208] = -9;
        bArr2[209] = -9;
        bArr2[210] = -9;
        bArr2[211] = -9;
        bArr2[212] = -9;
        bArr2[213] = -9;
        bArr2[214] = -9;
        bArr2[215] = -9;
        bArr2[216] = -9;
        bArr2[217] = -9;
        bArr2[218] = -9;
        bArr2[219] = -9;
        bArr2[220] = -9;
        bArr2[221] = -9;
        bArr2[222] = -9;
        bArr2[223] = -9;
        bArr2[224] = -9;
        bArr2[225] = -9;
        bArr2[226] = -9;
        bArr2[227] = -9;
        bArr2[228] = -9;
        bArr2[229] = -9;
        bArr2[230] = -9;
        bArr2[231] = -9;
        bArr2[232] = -9;
        bArr2[233] = -9;
        bArr2[234] = -9;
        bArr2[235] = -9;
        bArr2[236] = -9;
        bArr2[237] = -9;
        bArr2[238] = -9;
        bArr2[239] = -9;
        bArr2[240] = -9;
        bArr2[241] = -9;
        bArr2[242] = -9;
        bArr2[243] = -9;
        bArr2[244] = -9;
        bArr2[245] = -9;
        bArr2[246] = -9;
        bArr2[247] = -9;
        bArr2[248] = -9;
        bArr2[249] = -9;
        bArr2[250] = -9;
        bArr2[251] = -9;
        bArr2[252] = -9;
        bArr2[253] = -9;
        bArr2[254] = -9;
        bArr2[255] = -9;
        e = bArr2;
        f = new byte[]{45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};
        g = new byte[]{-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    }

    private b() {
    }

    public static byte[] a(String str) {
        return b(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00bc, code lost:
        throw new java.lang.IllegalArgumentException(java.lang.String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", java.lang.Integer.valueOf(r9.length), java.lang.Integer.valueOf(r6)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00df, code lost:
        r1 = new byte[r0];
        java.lang.System.arraycopy(r9, 0, r1, 0, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:?, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump */
    private static byte[] a(byte[] bArr, int i) {
        int i2;
        int i3;
        if (bArr != null) {
            if (i + 0 <= bArr.length) {
                if (i != 0) {
                    if (i >= 4) {
                        byte[] bArr2 = c;
                        byte[] bArr3 = new byte[(i * 3) / 4];
                        byte[] bArr4 = new byte[4];
                        int i4 = 0;
                        int i5 = 0;
                        int i6 = 0;
                        while (true) {
                            if (i4 >= i + 0) {
                                i2 = i6;
                                break;
                            }
                            byte b2 = bArr2[bArr[i4] & 255];
                            if (b2 >= -5) {
                                if (b2 >= -1) {
                                    i5++;
                                    bArr4[i5] = bArr[i4];
                                    if (i5 <= 3) {
                                        i6 = i6;
                                    } else if (3 >= bArr4.length) {
                                        throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", Integer.valueOf(bArr4.length), 0));
                                    } else if (i6 < 0 || i6 + 2 >= bArr3.length) {
                                        break;
                                    } else {
                                        byte[] bArr5 = c;
                                        if (bArr4[2] == 61) {
                                            bArr3[i6] = (byte) ((((bArr5[bArr4[1]] & 255) << 12) | ((bArr5[bArr4[0]] & 255) << 18)) >>> 16);
                                            i3 = 1;
                                        } else if (bArr4[3] == 61) {
                                            int i7 = ((bArr5[bArr4[2]] & 255) << 6) | ((bArr5[bArr4[0]] & 255) << 18) | ((bArr5[bArr4[1]] & 255) << 12);
                                            bArr3[i6] = (byte) (i7 >>> 16);
                                            bArr3[i6 + 1] = (byte) (i7 >>> 8);
                                            i3 = 2;
                                        } else {
                                            int i8 = (bArr5[bArr4[3]] & 255) | ((bArr5[bArr4[0]] & 255) << 18) | ((bArr5[bArr4[1]] & 255) << 12) | ((bArr5[bArr4[2]] & 255) << 6);
                                            bArr3[i6] = (byte) (i8 >> 16);
                                            bArr3[i6 + 1] = (byte) (i8 >> 8);
                                            bArr3[i6 + 2] = (byte) i8;
                                            i3 = 3;
                                        }
                                        i2 = i3 + i6;
                                        if (bArr[i4] == 61) {
                                            break;
                                        }
                                        i6 = i2;
                                        i5 = 0;
                                    }
                                } else {
                                    i5 = i5;
                                    i6 = i6;
                                }
                                i4++;
                            } else {
                                throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", Integer.valueOf(bArr[i4] & 255), Integer.valueOf(i4)));
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("Base64-encoded string must have at least four characters, but length specified was " + i);
                    }
                } else {
                    return new byte[0];
                }
            } else {
                throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", Integer.valueOf(bArr.length), 0, Integer.valueOf(i)));
            }
        } else {
            throw new NullPointerException("Cannot decode null source array.");
        }
    }

    private static byte[] b(String str) {
        byte[] bytes;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayInputStream byteArrayInputStream2;
        Throwable th;
        GZIPInputStream gZIPInputStream = null;
        if (str == null) {
            throw new NullPointerException("Input string was null.");
        }
        try {
            bytes = str.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e2) {
            bytes = str.getBytes();
        }
        byte[] a2 = a(bytes, bytes.length);
        if (a2 != null && a2.length >= 4 && 35615 == ((a2[0] & 255) | ((a2[1] << 8) & 65280))) {
            byte[] bArr = new byte[Emulator.GAMEPAD_UP];
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byteArrayInputStream2 = new ByteArrayInputStream(a2);
                    try {
                        GZIPInputStream gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream2);
                        while (true) {
                            try {
                                int read = gZIPInputStream2.read(bArr);
                                if (read < 0) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            } catch (IOException e3) {
                                gZIPInputStream = gZIPInputStream2;
                                byteArrayInputStream = byteArrayInputStream2;
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Exception e4) {
                                }
                                try {
                                    gZIPInputStream.close();
                                } catch (Exception e5) {
                                }
                                try {
                                    byteArrayInputStream.close();
                                } catch (Exception e6) {
                                }
                                return a2;
                            } catch (Throwable th2) {
                                th = th2;
                                gZIPInputStream = gZIPInputStream2;
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Exception e7) {
                                }
                                try {
                                    gZIPInputStream.close();
                                } catch (Exception e8) {
                                }
                                try {
                                    byteArrayInputStream2.close();
                                } catch (Exception e9) {
                                }
                                throw th;
                            }
                        }
                        a2 = byteArrayOutputStream.toByteArray();
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e10) {
                        }
                        try {
                            gZIPInputStream2.close();
                        } catch (Exception e11) {
                        }
                        try {
                            byteArrayInputStream2.close();
                        } catch (Exception e12) {
                        }
                    } catch (IOException e13) {
                        byteArrayInputStream = byteArrayInputStream2;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (IOException e14) {
                    byteArrayInputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                    byteArrayInputStream2 = null;
                }
            } catch (IOException e15) {
                byteArrayOutputStream = null;
                byteArrayInputStream = null;
            } catch (Throwable th5) {
                th = th5;
                byteArrayOutputStream = null;
                byteArrayInputStream2 = null;
            }
        }
        return a2;
    }
}
