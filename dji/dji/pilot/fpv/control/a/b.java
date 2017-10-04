package dji.pilot.fpv.control.a;

import android.content.Context;
import android.os.Handler;
import dji.log.DJILogHelper;
import dji.midware.natives.GroudStation;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJINetWorkReceiver.a;
import dji.pilot.publics.objects.g;
import dji.pilot.publics.objects.j;
import dji.pilot.usercenter.b.c;
import dji.pilot.usercenter.b.c.d;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SignatureException;

public class b {
    private static final String d = "http://offline-live1.services.u-blox.com/GetOfflineData.ashx?token=dG0-ULt6oEi5Ili9LZBzug;gnss=gps,glo;period=1;resolution=1";
    private static b r;
    d a = new 1(this);
    Handler b = new 2(this);
    private final String c = "com.dji.go.android.agps";
    private final String e = "http://staging-dji-service-cn.aasky.net/api/gnss_service/assistnow_offline_data?timestamp=%s&signature=%s";
    private final String f = "https://mydjiflight.dji.com/api/gnss_service/assistnow_offline_data?timestamp=%s&signature=%s";
    private final String g = "DeCdaqUf7Kekqauws4AB^^5M#V*Z1UFr";
    private final int h = 432000;
    private boolean i = false;
    private final String j = "com.dji.go.android.agps.lastUpdateTime";
    private final String k = "com.dji.go.android.agps.dataCRC16Hash";
    private final String l = "com.dji.go.android.agps.dataLength";
    private final int m = 5;
    private int n = 0;
    private final Context o = DJIApplication.a();
    private int p = 0;
    private String q = null;
    private final int s = 0;
    private final int t = 1;

    private b() {
    }

    public static b getInstance() {
        if (r == null) {
            r = new b();
        }
        return r;
    }

    public void a() {
        c.getInstance().a(DJIApplication.a());
        if (c() && !dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
        d("download manager init");
    }

    public void b() {
        if (dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().d((Object) this);
        }
        d("download manager uninit");
    }

    public void onEventBackgroundThread(a aVar) {
        d("DJINetWorkStatusEvent = " + aVar.toString());
        if (aVar == a.CONNECT_OK) {
            c();
        }
    }

    public synchronized boolean c() {
        boolean z;
        if (!h() || this.i) {
            z = false;
        } else {
            g();
            z = true;
        }
        return z;
    }

    private boolean h() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        d("isNeedUpdate mLastUpdateTime = " + i() + " time = " + currentTimeMillis);
        return currentTimeMillis - i() > 432000;
    }

    public boolean a(int i) {
        DJILogHelper.getInstance().LOGE("agps", "isNeedPush timestamp=" + i + " getLastUpdateTime = " + i());
        return i <= i();
    }

    public String d() {
        int i = i();
        if (i == 0) {
            return null;
        }
        if (this.q == null) {
            this.q = e(i);
        }
        return this.q;
    }

    private int i() {
        return g.b(this.o, "com.dji.go.android.agps.lastUpdateTime", 0);
    }

    private void b(int i) {
        g.a(this.o, "com.dji.go.android.agps.lastUpdateTime", i);
    }

    public short e() {
        return (short) g.b(this.o, "com.dji.go.android.agps.dataCRC16Hash", 0);
    }

    private void c(int i) {
        g.a(this.o, "com.dji.go.android.agps.dataCRC16Hash", i);
    }

    public int f() {
        return g.b(this.o, "com.dji.go.android.agps.dataLength", 0);
    }

    private void d(int i) {
        g.a(this.o, "com.dji.go.android.agps.dataLength", i);
    }

    public void g() {
        d("start downLoadAGPSOfflineData");
        this.i = true;
        String str = d;
        String l = l();
        try {
            str = String.format("https://mydjiflight.dji.com/api/gnss_service/assistnow_offline_data?timestamp=%s&signature=%s", new Object[]{Integer.valueOf(this.p), j.a(this.p + "", "DeCdaqUf7Kekqauws4AB^^5M#V*Z1UFr")});
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        d("url=" + str);
        d("filename=" + l);
        c.getInstance().a(str, l, true, true, null, this.a);
    }

    private short a(String str) {
        BufferedInputStream bufferedInputStream;
        IOException e;
        Throwable th;
        short s = (short) 0;
        File file = new File(str);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());
        if (file.exists()) {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = bufferedInputStream.read(bArr, 0, 1024);
                        if (-1 == read) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    s = GroudStation.native_calcCrc16(byteArrayOutputStream.toByteArray());
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e2 = e3;
                    try {
                        e2.printStackTrace();
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        return s;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e222 = e4;
                bufferedInputStream = null;
                e222.printStackTrace();
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                return s;
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = null;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th;
            }
        }
        return s;
    }

    private long b(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.length();
        }
        return 0;
    }

    private void c(String str) {
        File file = new File(j());
        for (String str2 : file.list(new 3(this, str))) {
            d("delOverdueFile name = " + file + str2);
            File file2 = new File(file + dji.pilot.usercenter.protocol.d.t + str2);
            if (file2.exists()) {
                file2.delete();
            }
        }
    }

    private String j() {
        return this.o.getDir("com.dji.go.android.agps", 0).getAbsolutePath();
    }

    private synchronized boolean k() {
        this.n++;
        return this.n < 5;
    }

    private String l() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        this.p = currentTimeMillis;
        return e(currentTimeMillis);
    }

    private String e(int i) {
        return j() + dji.pilot.usercenter.protocol.d.t + i;
    }

    private void d(String str) {
    }
}
