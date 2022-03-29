package com.anzhi.ad.coverscreen.c;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import java.util.Random;
/* loaded from: classes.dex */
public class c {
    private static c a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;

    public c() {
    }

    private c(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String networkOperator = telephonyManager.getNetworkOperator();
            this.b = networkOperator.substring(0, 3);
            this.c = networkOperator.substring(3);
            if (telephonyManager.getCellLocation() instanceof CdmaCellLocation) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) telephonyManager.getCellLocation();
                this.d = String.valueOf(cdmaCellLocation.getBaseStationId());
                this.e = String.valueOf(cdmaCellLocation.getNetworkId());
                this.f = telephonyManager.getLine1Number();
            } else if (telephonyManager.getCellLocation() instanceof GsmCellLocation) {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
                this.d = String.valueOf(gsmCellLocation.getCid());
                this.e = String.valueOf(gsmCellLocation.getLac());
                this.f = telephonyManager.getLine1Number();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static c a(Context context) {
        if (a == null) {
            a = new c(context);
        }
        return a;
    }

    public static String a(int i) {
        String[] strArr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        while (i > 0) {
            sb.append(strArr[random.nextInt(10)]);
            i--;
        }
        return sb.toString();
    }

    public static boolean a(String str) {
        return str == null || str.trim().length() == 0 || str.equals("null");
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return a(this.f) ? "" : this.f;
    }
}
