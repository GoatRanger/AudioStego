package dji.pilot.fpv.control;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import dji.log.DJILogHelper;
import dji.log.WM220LogUtil;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCommonSetMultiParam;
import dji.midware.data.model.P3.DataWifiGetChannelList;
import dji.midware.data.model.P3.DataWifiGetPassword;
import dji.midware.data.model.P3.DataWifiGetSSID;
import dji.midware.data.model.P3.DataWifiRestart;
import dji.midware.data.model.P3.DataWifiSetPassword;
import dji.midware.data.model.P3.DataWifiSetSSID;
import dji.midware.data.model.P3.DataWifiSetSweepFrequency;
import dji.midware.e.d;
import dji.pilot.publics.objects.g;
import java.lang.ref.WeakReference;

public class t {
    private static final boolean E = true;
    public static final String a = "key_phone_support_5g";
    private static final String b = t.class.getSimpleName();
    private static final int c = 4096;
    private static final int d = 4097;
    private static final int e = 4098;
    private static final int f = 4099;
    private static final int g = 4100;
    private static final int h = 4101;
    private static final int i = 4102;
    private static final int j = 4103;
    private static final int k = 0;
    private static final int l = 1;
    private static final int m = 0;
    private static final int n = 1;
    private static final int o = 2;
    private static final int p = 3;
    private static final String q = "";
    private static final String r = "";
    private b A;
    private String B;
    private String C;
    private volatile int D;
    private String s;
    private String t;
    private final DataWifiGetSSID u;
    private final DataWifiGetPassword v;
    private final DataWifiSetSSID w;
    private final DataWifiSetPassword x;
    private final a y;
    private volatile boolean z;

    private static final class a extends Handler {
        private final WeakReference<t> a;

        private a(t tVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(tVar);
        }

        public void handleMessage(Message message) {
            dji.midware.data.config.P3.a aVar = null;
            t tVar = (t) this.a.get();
            if (tVar != null && tVar.i()) {
                int i;
                switch (message.what) {
                    case 4096:
                        i = message.arg1;
                        if (message.obj instanceof dji.midware.data.config.P3.a) {
                            aVar = (dji.midware.data.config.P3.a) message.obj;
                        }
                        tVar.a(i, aVar);
                        return;
                    case 4097:
                        i = message.arg1;
                        if (message.obj instanceof dji.midware.data.config.P3.a) {
                            aVar = (dji.midware.data.config.P3.a) message.obj;
                        }
                        tVar.b(i, aVar);
                        return;
                    case 4098:
                        tVar.a(message.arg1, message.obj);
                        return;
                    case 4099:
                        tVar.b(message.arg1, message.obj);
                        return;
                    case 4100:
                        tVar.a(message.arg1, message.arg2, message.obj);
                        return;
                    case 4101:
                        tVar.b(message.arg1, message.arg2, message.obj);
                        return;
                    case t.i /*4102*/:
                        tVar.c(message.arg1, message.obj);
                        return;
                    case t.j /*4103*/:
                        tVar.d(message.arg1, message.obj);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public interface b {
        void a(boolean z, dji.midware.data.config.P3.a aVar);

        void a(boolean z, dji.midware.data.config.P3.a aVar, int i);

        void a(boolean z, String str, dji.midware.data.config.P3.a aVar, int i);

        void b(boolean z, dji.midware.data.config.P3.a aVar);

        void b(boolean z, String str, dji.midware.data.config.P3.a aVar, int i);

        void c(boolean z, String str, dji.midware.data.config.P3.a aVar, int i);

        void d(boolean z, String str, dji.midware.data.config.P3.a aVar, int i);
    }

    private static final class c {
        private static final t a = new t();

        private c() {
        }
    }

    public static t getInstance() {
        return c.a;
    }

    public synchronized void a() {
        if (!this.z) {
            this.z = true;
        }
    }

    public synchronized void b() {
        if (this.z) {
            this.z = false;
        }
    }

    public void a(b bVar) {
        this.A = bVar;
    }

    public String c() {
        return this.s;
    }

    public String d() {
        return this.t;
    }

    public void a(String str, String str2) {
        int i;
        if (this.s.equals(str)) {
            i = 0;
        } else {
            i = 1;
        }
        if (!this.t.equals(str2)) {
            i |= 2;
        }
        Object bytes;
        if (i == 3) {
            this.B = str;
            this.C = str2;
            bytes = str.getBytes();
            Object bytes2 = str2.getBytes();
            byte[] bArr = new byte[((((bytes.length + 3) + 2) + bytes2.length) + 2)];
            bArr[0] = (byte) 3;
            bArr[1] = (byte) dji.midware.data.config.P3.o.a.b.a();
            bArr[2] = (byte) bytes.length;
            System.arraycopy(bytes, 0, bArr, 3, bytes.length);
            bArr[bytes.length + 3] = (byte) dji.midware.data.config.P3.o.a.f.a();
            bArr[bytes.length + 4] = (byte) bytes2.length;
            System.arraycopy(bytes2, 0, bArr, bytes.length + 5, bytes2.length);
            bArr[bytes.length + 5] = (byte) dji.midware.data.config.P3.o.a.j.a();
            bArr[bytes.length + 6] = (byte) 0;
            a(bArr);
        } else if (i == 1) {
            this.B = str;
            this.C = this.t;
            bytes = str.getBytes();
            r3 = new byte[((bytes.length + 3) + 2)];
            r3[0] = (byte) 2;
            r3[1] = (byte) dji.midware.data.config.P3.o.a.b.a();
            r3[2] = (byte) bytes.length;
            System.arraycopy(bytes, 0, r3, 3, bytes.length);
            r3[bytes.length + 3] = (byte) dji.midware.data.config.P3.o.a.j.a();
            r3[bytes.length + 4] = (byte) 0;
            a(r3);
        } else if (i == 2) {
            this.B = this.s;
            this.C = str2;
            bytes = str2.getBytes();
            r3 = new byte[((bytes.length + 3) + 2)];
            r3[0] = (byte) 2;
            r3[1] = (byte) dji.midware.data.config.P3.o.a.f.a();
            r3[2] = (byte) bytes.length;
            System.arraycopy(bytes, 0, r3, 3, bytes.length);
            r3[bytes.length + 3] = (byte) dji.midware.data.config.P3.o.a.j.a();
            r3[bytes.length + 4] = (byte) 0;
            a(r3);
        } else {
            a(true, dji.midware.data.config.P3.a.c, 0);
        }
    }

    private void a(byte[] bArr) {
        final DataCommonSetMultiParam dataCommonSetMultiParam = new DataCommonSetMultiParam();
        dataCommonSetMultiParam.a(DeviceType.WIFI).a(bArr);
        dataCommonSetMultiParam.start(new d(this) {
            final /* synthetic */ t b;

            public void onSuccess(Object obj) {
                this.b.y.obtainMessage(4100, 0, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.y.obtainMessage(4100, 1, dataCommonSetMultiParam.a(), aVar).sendToTarget();
            }
        });
    }

    public boolean b(String str, String str2) {
        return (this.s.equals(str) && this.t.equals(str2)) ? false : true;
    }

    public void c(String str, String str2) {
        this.D = 0;
        if (!this.s.equals(str)) {
            this.D |= 1;
        }
        if (!this.t.equals(str2)) {
            this.D |= 2;
        }
        if (this.D == 0) {
            a(true, dji.midware.data.config.P3.a.c, 0);
        } else if ((this.D & 1) != 0) {
            this.C = str2;
            a(str);
        } else if ((this.D & 2) != 0) {
            b(str2);
        }
    }

    private void a(final String str) {
        this.w.a(str.getBytes()).start(new d(this) {
            final /* synthetic */ t b;

            public void onSuccess(Object obj) {
                this.b.y.obtainMessage(4098, 0, 0, str).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.y.obtainMessage(4098, 1, 0, aVar).sendToTarget();
            }
        });
    }

    private void b(final String str) {
        this.x.a(str.getBytes()).start(new d(this) {
            final /* synthetic */ t b;

            public void onSuccess(Object obj) {
                this.b.y.obtainMessage(4099, 0, 0, str).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.y.obtainMessage(4099, 1, 0, aVar).sendToTarget();
            }
        });
    }

    public void e() {
        DataWifiRestart.getInstance().start(new d(this) {
            final /* synthetic */ t a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.y.obtainMessage(4101, 0, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.y.obtainMessage(4101, 1, 0, aVar).sendToTarget();
            }
        });
    }

    public void f() {
        this.u.start(new d(this) {
            final /* synthetic */ t a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.y.obtainMessage(4096, 0, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.y.obtainMessage(4096, 1, 0, aVar).sendToTarget();
            }
        });
        this.v.start(new d(this) {
            final /* synthetic */ t a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.y.obtainMessage(4097, 0, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.y.obtainMessage(4097, 1, 0, aVar).sendToTarget();
            }
        });
        DataWifiGetChannelList.getInstance().start(new d(this) {
            final /* synthetic */ t a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.y.obtainMessage(t.i, 0, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.y.obtainMessage(t.i, 1, 0, aVar).sendToTarget();
            }
        });
    }

    public void onEventMainThread(p pVar) {
        if (pVar != p.a) {
        }
    }

    private boolean i() {
        return this.z;
    }

    private void a(boolean z, dji.midware.data.config.P3.a aVar, int i) {
        if (this.A != null) {
            this.A.a(z, aVar, i);
        }
    }

    private void a(boolean z, String str, dji.midware.data.config.P3.a aVar, int i) {
        if (this.A != null) {
            this.A.a(z, str, aVar, i);
        }
    }

    private void b(boolean z, String str, dji.midware.data.config.P3.a aVar, int i) {
        if (this.A != null) {
            this.A.b(z, str, aVar, i);
        }
    }

    private void c(boolean z, String str, dji.midware.data.config.P3.a aVar, int i) {
        if (this.A != null) {
            this.A.c(z, str, aVar, i);
        }
    }

    private void d(boolean z, String str, dji.midware.data.config.P3.a aVar, int i) {
        if (this.A != null) {
            this.A.d(z, str, aVar, i);
        }
    }

    private void a(int i, dji.midware.data.config.P3.a aVar) {
        boolean z;
        if (i == 0) {
            this.s = this.u.getSSID();
        }
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        a(z, this.s, aVar, 0);
    }

    private void b(int i, dji.midware.data.config.P3.a aVar) {
        boolean z;
        if (i == 0) {
            this.t = this.v.getPassword();
        }
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        b(z, this.t, aVar, 0);
    }

    private void a(int i, Object obj) {
        DJILogHelper.getInstance().LOGD(b, "== Wifi SSID result[" + i + "]obj[" + obj + dji.pilot.usercenter.protocol.d.H, false, true);
        if (i == 0) {
            this.s = (String) obj;
            if ((this.D & 1) != 0) {
                this.D &= -2;
                if (this.D == 0) {
                    e();
                    return;
                } else if ((this.D & 2) != 0) {
                    b(this.C);
                    return;
                } else {
                    return;
                }
            }
            c(true, this.s, null, 0);
        } else if ((this.D & 1) != 0) {
            this.D = 0;
            a(false, obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) obj : null, 0);
        } else {
            c(false, this.s, obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) obj : null, 0);
        }
    }

    private void b(int i, Object obj) {
        DJILogHelper.getInstance().LOGD(b, "== Wifi Pwd result[" + i + "]obj[" + obj + dji.pilot.usercenter.protocol.d.H, false, true);
        if (i == 0) {
            this.t = (String) obj;
            if ((this.D & 2) != 0) {
                this.D &= -3;
                if (this.D == 0) {
                    e();
                    return;
                }
                return;
            }
            d(true, this.t, null, 0);
        } else if ((this.D & 2) != 0) {
            this.D = 0;
            a(false, obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) obj : null, 0);
        } else {
            d(false, this.t, obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) obj : null, 0);
        }
    }

    private void a(int i, int i2, Object obj) {
        if (i == 0) {
            this.s = this.B;
            this.t = this.C;
            a(true, null, i2);
            return;
        }
        dji.midware.data.config.P3.a aVar = obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) obj : dji.midware.data.config.P3.a.D;
        if (dji.midware.data.config.P3.o.a.b.a() != i2) {
            if (dji.midware.data.config.P3.o.a.f.a() == i2) {
                this.s = this.B;
            } else if (dji.midware.data.config.P3.o.a.j.a() == i2) {
                this.s = this.B;
                this.t = this.C;
            }
        }
        a(false, aVar, i2);
    }

    private void b(int i, int i2, Object obj) {
        DJILogHelper.getInstance().LOGD(b, "==== Wifi Restart result[" + i + "]obj[" + obj + dji.pilot.usercenter.protocol.d.H, false, true);
        if (i == 0) {
            a(true, null, i2);
        } else {
            a(false, obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) obj : dji.midware.data.config.P3.a.D, i2);
        }
    }

    private void c(int i, Object obj) {
        if (this.A != null) {
            if (i == 0) {
                this.A.a(true, null);
            } else {
                this.A.a(false, obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) obj : dji.midware.data.config.P3.a.D);
            }
        }
    }

    private void d(int i, Object obj) {
        if (this.A != null) {
            if (i == 0) {
                this.A.b(true, null);
            } else {
                this.A.b(false, obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) obj : dji.midware.data.config.P3.a.D);
            }
        }
    }

    public void g() {
        DataWifiSetSweepFrequency.getInstance().a(true).start(new d(this) {
            final /* synthetic */ t a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                WM220LogUtil.LOGI("**open wifi sweep onSuccess");
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                WM220LogUtil.LOGI("**open wifi sweep onFailure");
            }
        });
    }

    public void h() {
        DataWifiSetSweepFrequency.getInstance().a(false).start(new d(this) {
            final /* synthetic */ t a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                WM220LogUtil.LOGI("**close wifi sweep onSuccess");
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                WM220LogUtil.LOGI("**close wifi sweep onFailure");
            }
        });
    }

    private t() {
        this.s = "";
        this.t = "";
        this.u = DataWifiGetSSID.getInstance();
        this.v = DataWifiGetPassword.getInstance();
        this.w = DataWifiSetSSID.getInstance();
        this.x = DataWifiSetPassword.getInstance();
        this.z = false;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = 0;
        this.y = new a();
    }

    public static boolean a(Context context) {
        boolean z = true;
        boolean b = g.b(context, a, false);
        WM220LogUtil.LOGD("**SharedPreferences :support5G= " + b);
        if (!b) {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (VERSION.SDK_INT < 21 || !wifiManager.is5GHzBandSupported()) {
                if (wifiManager.isWifiEnabled()) {
                    boolean z2;
                    WM220LogUtil.LOGD("** scan result");
                    if (context instanceof Activity) {
                        getInstance().a((Activity) context);
                    }
                    for (ScanResult scanResult : wifiManager.getScanResults()) {
                        if (scanResult.frequency >= 5000) {
                            z2 = true;
                            break;
                        }
                    }
                    z2 = b;
                    WM220LogUtil.LOGD("**scan if support 5g: " + z2);
                    z = z2;
                } else {
                    z = b;
                }
            }
            g.a(context, a, z);
        }
        return z;
    }

    public void a(Activity activity) {
        WM220LogUtil.LOGD("**verifyWifiPermissions: ");
        String[] strArr = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        int checkSelfPermission = ActivityCompat.checkSelfPermission(activity, "android.permission.ACCESS_COARSE_LOCATION");
        if (VERSION.SDK_INT >= 23 && checkSelfPermission != 0) {
            ActivityCompat.requestPermissions(activity, strArr, 0);
        }
    }
}
