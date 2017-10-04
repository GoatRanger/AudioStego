package dji.device.common;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import dji.thirdparty.a.c;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;

public class b {
    private static String[] E = null;
    private static long[] F = null;
    public static long a = 2454447;
    public static long b = 2655033;
    public static long c = 2655033;
    public static long d = 2735514;
    public static long e = 1300104;
    public static long f = 1300111;
    public static long g = 1300104;
    public static long h = 1285289;
    public static long i = 20014;
    public static long j = 1300138;
    public static long k = 88;
    public static final String l = ".";
    public static final int m = 0;
    public static final int n = 1;
    public static final int o = 2;
    public static final int p = 3;
    public static final int q = 4;
    static final String r = "Failed";
    private static b t = null;
    private static final int u = 6;
    private static final int v = 7;
    private static final int w = 300;
    private static final int x = 5;
    private DataCommonGetVersion A = null;
    private DataCommonGetVersion B = null;
    private DataCommonGetVersion C = null;
    private DataCommonGetVersion[] D = new DataCommonGetVersion[5];
    private d G = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            this.a.s.obtainMessage(0, 6, 0).sendToTarget();
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            this.a.s.obtainMessage(0, 7, 0).sendToTarget();
        }
    };
    private d H = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            this.a.s.obtainMessage(1, 6, 0).sendToTarget();
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            this.a.s.obtainMessage(1, 7, 0).sendToTarget();
        }
    };
    private d I = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            this.a.s.obtainMessage(2, 6, 0).sendToTarget();
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            this.a.s.obtainMessage(2, 7, 0).sendToTarget();
        }
    };
    private d J = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            this.a.s.obtainMessage(4, 6, 0).sendToTarget();
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            this.a.s.obtainMessage(4, 7, 0).sendToTarget();
        }
    };
    private d K = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            this.a.s.obtainMessage(3, 6, 0).sendToTarget();
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            this.a.s.obtainMessage(3, 7, 0).sendToTarget();
        }
    };
    a s;
    private DataCommonGetVersion y = null;
    private DataCommonGetVersion z = null;

    private static final class a extends Handler {
        private final WeakReference<b> a;

        public a(b bVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(bVar);
        }

        public void handleMessage(Message message) {
            b bVar = (b) this.a.get();
            if (bVar != null) {
                bVar.a(message.what, message.arg1);
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface b {
    }

    public static synchronized b getInstance() {
        b bVar;
        synchronized (b.class) {
            if (t == null) {
                t = new b();
            }
            bVar = t;
        }
        return bVar;
    }

    private b() {
        E = new String[5];
        F = new long[5];
        this.y = new DataCommonGetVersion().setDeviceType(DeviceType.CAMERA);
        this.z = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL);
        this.A = new DataCommonGetVersion().setDeviceType(DeviceType.WIFI);
        this.B = new DataCommonGetVersion().setDeviceType(DeviceType.DM368);
        this.C = new DataCommonGetVersion().setDeviceType(DeviceType.OFDM);
        this.D[0] = this.y;
        this.D[1] = this.z;
        this.D[2] = this.A;
        this.D[3] = this.B;
        this.D[4] = this.C;
        this.s = new a(this);
    }

    public void a() {
        c();
    }

    public void a(int i, d dVar) {
        if (i == 0) {
            this.y.start(dVar, 300, 5);
        } else if (i == 1) {
            this.z.start(dVar, 300, 5);
        } else if (i == 2) {
            this.A.start(dVar, 300, 5);
        } else if (i == 4) {
            this.C.start(dVar, 300, 5);
        } else if (i == 3) {
            this.B.start(dVar, 300, 5);
        }
    }

    private void c() {
        a(0, this.G);
        a(1, this.H);
        a(2, this.I);
        a(4, this.J);
        a(3, this.K);
    }

    public boolean a(int i) {
        return !TextUtils.isEmpty(E[0]);
    }

    private void a(int i, int i2) {
        if (i2 == 6) {
            E[i] = a(this.D[i]);
            c.a().e(this);
        }
    }

    private String a(DataCommonGetVersion dataCommonGetVersion) {
        return dataCommonGetVersion.getFirmVer(".");
    }

    public String b(int i) {
        return E[i];
    }

    public long c(int i) {
        long j = 0;
        try {
            j = Long.parseLong(E[i].replace(".", ""));
        } catch (Exception e) {
        }
        return j;
    }

    public String b() {
        try {
            Class cls = Class.forName("dji.pilot.reflect.SetReflect");
            String str = (String) cls.getMethod("getFirmwareVersion", new Class[0]).invoke(cls, new Object[0]);
            return str == null ? "0.0.0.0" : str;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "0.0.0.0";
        } catch (NoSuchMethodException e2) {
            return "0.0.0.0";
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return "0.0.0.0";
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
            return "0.0.0.0";
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return "0.0.0.0";
        }
    }
}
