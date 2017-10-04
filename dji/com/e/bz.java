package com.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Process;
import android.support.v4.media.TransportMediator;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.json.JSONObject;

public class bz {
    private Context a = null;
    private boolean b = true;
    private int c = 1270;
    private int d = 310;
    private int e = 4;
    private int f = 200;
    private int g = 1;
    private int h = 0;
    private int i = 0;
    private long j = 0;
    private b k = null;

    protected bz(Context context) {
        this.a = context;
    }

    private static int a(byte[] bArr, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < 4) {
            i3 += (bArr[i2 + i] & 255) << (i2 * 8);
            i2++;
        }
        return i3;
    }

    protected static bz a(Context context) {
        Throwable th;
        Throwable th2;
        boolean z = true;
        bz bzVar = new bz(context);
        bzVar.h = 0;
        bzVar.i = 0;
        bzVar.j = ((System.currentTimeMillis() + 28800000) / 86400000) * 86400000;
        FileInputStream fileInputStream = null;
        FileInputStream fileInputStream2;
        try {
            File file = new File(b(context) + File.separator + "data_carrier_status");
            if (file.exists()) {
                fileInputStream2 = new FileInputStream(file);
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[32];
                    while (true) {
                        int read = fileInputStream2.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    byteArrayOutputStream.flush();
                    bArr = byteArrayOutputStream.toByteArray();
                    if (bArr != null && bArr.length >= 22) {
                        if (bArr[0] == (byte) 0) {
                            z = false;
                        }
                        bzVar.b = z;
                        bzVar.c = (bArr[1] * 10) * 1024;
                        bzVar.d = (bArr[2] * 10) * 1024;
                        bzVar.e = bArr[3];
                        bzVar.f = bArr[4] * 10;
                        bzVar.g = bArr[5];
                        long b = b(bArr, 14);
                        if (bzVar.j - b < 86400000) {
                            bzVar.j = b;
                            bzVar.h = a(bArr, 6);
                            bzVar.i = a(bArr, 10);
                        }
                    }
                    byteArrayOutputStream.close();
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        bc.a(th, "CollectorStatus", "load");
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        return bzVar;
                    } catch (Throwable th4) {
                        th2 = th4;
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Throwable th5) {
                            }
                        }
                        throw th2;
                    }
                }
                return bzVar;
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Throwable th6) {
                }
            }
            return bzVar;
        } catch (Throwable th7) {
            th2 = th7;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th2;
        }
    }

    private static byte[] a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) ((int) ((j >> (i * 8)) & 255));
        }
        return bArr;
    }

    private static long b(byte[] bArr, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < 8) {
            i3 += (bArr[i2 + i] & 255) << (i2 * 8);
            i2++;
        }
        return (long) i3;
    }

    private static String b(Context context) {
        try {
            File a = Process.myUid() != 1000 ? cb.a(context) : null;
            return (("mounted".equals(Environment.getExternalStorageState()) || !cb.a()) && a != null) ? a.getPath() : context.getFilesDir().getPath();
        } catch (Throwable th) {
            bc.a(th, "CollectorStatus", "getDiskFileDir");
            return null;
        }
    }

    private static byte[] c(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & 255);
        }
        return bArr;
    }

    private void h() {
        long currentTimeMillis = System.currentTimeMillis() + 28800000;
        if (currentTimeMillis - this.j > 86400000) {
            this.j = (currentTimeMillis / 86400000) * 86400000;
            this.h = 0;
            this.i = 0;
        }
    }

    protected void a(int i) {
        h();
        if (i < 0) {
            i = 0;
        }
        this.h = i;
    }

    protected void a(b bVar) {
        this.k = bVar;
    }

    protected boolean a() {
        boolean z = true;
        try {
            h();
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.a.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return this.b;
            }
            if (activeNetworkInfo.getType() == 1) {
                return this.b && this.h < this.c;
            } else {
                if (!this.b || this.i >= this.d) {
                    z = false;
                }
                return z;
            }
        } catch (Throwable th) {
            bc.a(th, "CollectorStatus", "isEnabled");
            return false;
        }
    }

    protected boolean a(String str) {
        boolean z = true;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("e")) {
                this.b = jSONObject.getInt("e") != 0;
            }
            if (jSONObject.has("d")) {
                int i = jSONObject.getInt("d");
                this.c = ((i & TransportMediator.KEYCODE_MEDIA_PAUSE) * 10) * 1024;
                this.d = (((i & 3968) >> 7) * 10) * 1024;
                this.e = (520192 & i) >> 12;
                this.f = ((66584576 & i) >> 19) * 10;
                this.g = (i & 2080374784) >> 26;
                if (this.g == 31) {
                    this.g = 1500;
                }
                if (this.k != null) {
                    this.k.a();
                }
            }
            if (jSONObject.has("u")) {
                if (jSONObject.getInt("u") == 0) {
                    z = false;
                }
                g();
                return z;
            }
        } catch (Throwable th) {
            bc.a(th, "CollectorStatus", "parse");
        }
        z = false;
        g();
        return z;
    }

    protected int b() {
        return this.e;
    }

    protected void b(int i) {
        h();
        if (i < 0) {
            i = 0;
        }
        this.i = i;
    }

    protected int c() {
        return this.f;
    }

    protected int d() {
        return this.g;
    }

    protected int e() {
        h();
        return this.h;
    }

    protected int f() {
        h();
        return this.i;
    }

    protected void g() {
        FileOutputStream fileOutputStream;
        Throwable th;
        int i = 1;
        FileOutputStream fileOutputStream2 = null;
        try {
            h();
            File file = new File(b(this.a) + File.separator + "data_carrier_status");
            if (file.exists()) {
                fileOutputStream = new FileOutputStream(file);
                try {
                    byte[] c = c(this.h);
                    byte[] c2 = c(this.i);
                    byte[] a = a(this.j);
                    byte[] bArr = new byte[22];
                    if (!this.b) {
                        i = 0;
                    }
                    bArr[0] = (byte) i;
                    bArr[1] = (byte) (this.c / 10240);
                    bArr[2] = (byte) (this.d / 10240);
                    bArr[3] = (byte) this.e;
                    bArr[4] = (byte) (this.f / 10);
                    bArr[5] = (byte) this.g;
                    bArr[6] = c[0];
                    bArr[7] = c[1];
                    bArr[8] = c[2];
                    bArr[9] = c[3];
                    bArr[10] = c2[0];
                    bArr[11] = c2[1];
                    bArr[12] = c2[2];
                    bArr[13] = c2[3];
                    bArr[14] = a[0];
                    bArr[15] = a[1];
                    bArr[16] = a[2];
                    bArr[17] = a[3];
                    bArr[18] = a[4];
                    bArr[19] = a[5];
                    bArr[20] = a[6];
                    bArr[21] = a[7];
                    fileOutputStream.write(bArr);
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        bc.a(th, "CollectorStatus", "save");
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th4) {
                            }
                        }
                        throw th;
                    }
                }
            } else if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Throwable th5) {
                }
            }
        } catch (Throwable th6) {
            th = th6;
            fileOutputStream = fileOutputStream2;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }
}
