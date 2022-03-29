package com.anzhi.ad.coverscreen.c.j;

import android.content.Context;
import android.net.Proxy;
import android.util.Log;
import com.anzhi.ad.coverscreen.c.c;
import com.anzhi.ad.coverscreen.c.g.a;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
/* loaded from: classes.dex */
public final class f implements Runnable {
    private URL a = null;
    private e b;
    private String c;
    private String d;
    private Context e;

    public final void a(Context context, String str, String str2, String str3) {
        this.e = context;
        this.c = str2;
        this.d = str3;
        try {
            this.a = new URL(str);
        } catch (MalformedURLException e) {
            a.b(new StringBuilder().append(e).toString());
        }
        a.b("setBaseRequestInfo, url: " + str);
    }

    public final void a(e eVar) {
        this.b = eVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r1v12, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // java.lang.Runnable
    /* Code decompiled incorrectly, please refer to instructions dump */
    public final void run() {
        HttpURLConnection httpURLConnection;
        int b = b.b(this.e);
        if (b != 0) {
            Context context = this.e;
            a.b("\nG_获取数据  > " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
            Context context2 = this.e;
            a.b("\nG_JSON      " + this.d);
            if (this.a == null) {
                Context context3 = this.e;
                a.b("\nG_error > URL must be init!");
                throw new IllegalArgumentException("URL must be init!");
            }
            ?? a = c.a(this.c);
            if (a != 0) {
                Context context4 = this.e;
                a.b("\nG_error > password must be init!");
                throw new IllegalArgumentException("password must be init!");
            }
            try {
                a = 0;
                try {
                    try {
                        try {
                            String defaultHost = Proxy.getDefaultHost();
                            int defaultPort = Proxy.getDefaultPort();
                            a.a("proxyHost: " + defaultHost + ", port: " + defaultPort);
                            if (2 != b || defaultHost == null || defaultPort <= 0) {
                                httpURLConnection = (HttpURLConnection) this.a.openConnection();
                            } else {
                                Log.i("D", "user Proxy, proxyHost: " + defaultHost + ", port: " + defaultPort);
                                httpURLConnection = (HttpURLConnection) this.a.openConnection(new java.net.Proxy(Proxy.Type.HTTP, new InetSocketAddress(defaultHost, defaultPort)));
                            }
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                            httpURLConnection.addRequestProperty("appkey", this.c);
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setConnectTimeout(60000);
                            httpURLConnection.setReadTimeout(30000);
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()));
                            bufferedWriter.write(c.a(this.d, c.a(this.c), false));
                            bufferedWriter.close();
                            int responseCode = httpURLConnection.getResponseCode();
                            a.a("get url response code: " + responseCode + ", " + this.a);
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseCode != 200 ? httpURLConnection.getErrorStream() : httpURLConnection.getInputStream()));
                            StringBuilder sb = new StringBuilder();
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                sb.append(readLine);
                            }
                            bufferedReader.close();
                            if (responseCode == 200) {
                                this.b.a(true, c.a(sb.toString(), c.a(this.c)));
                            } else {
                                this.b.a(false, c.a(sb.toString(), c.a(this.c)));
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                        } catch (SocketTimeoutException e) {
                            Context context5 = this.e;
                            a.b("\nG_error > SocketTimeoutException > " + e + " > ");
                            this.b.a(false, "SocketTimeoutException " + e);
                            if (0 != 0) {
                                a.disconnect();
                            }
                        }
                    } catch (SocketException e2) {
                        Context context6 = this.e;
                        a.b("\nG_error SocketException > " + e2 + " > ");
                        this.b.a(false, "SocketException " + e2);
                        if (0 != 0) {
                            a.disconnect();
                        }
                    }
                } catch (IOException e3) {
                    Context context7 = this.e;
                    a.b("\nG_error IOException > " + e3 + " > ");
                    this.b.a(false, "IOException: " + e3);
                    if (0 != 0) {
                        a.disconnect();
                    }
                } catch (Exception e4) {
                    this.b.a(false, "Exception: " + e4);
                    Context context8 = this.e;
                    a.b("\nG_error Exception > " + e4 + " > ");
                    if (0 != 0) {
                        a.disconnect();
                    }
                }
            } catch (Throwable th) {
                if (a != 0) {
                    a.disconnect();
                }
                throw th;
            }
        }
    }
}
