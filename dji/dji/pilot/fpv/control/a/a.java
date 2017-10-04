package dji.pilot.fpv.control.a;

import android.location.Location;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataFlycGetPushAgpsStatus;
import dji.midware.data.model.P3.DataFlycSendAgpsData;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

public class a {
    private static b a = null;
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static DataFlycSendAgpsData f;
    private static int i = 128;
    private a g;
    private b h;
    private short j;
    private RandomAccessFile k;
    private byte[] l;
    private int m;

    private a() {
        this.h = b.a;
        this.j = (short) 0;
        this.k = null;
        this.l = new byte[i];
    }

    public void a() {
        a("start init agpsManager");
        a = b.getInstance();
        f = DataFlycSendAgpsData.getInstance();
        this.g = new a(this);
        if (!c.a().c((Object) this)) {
            c.a().a((Object) this);
        }
        this.g.sendEmptyMessageDelayed(3, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
    }

    public void b() {
        if (!c.a().c((Object) this)) {
            c.a().d((Object) this);
        }
    }

    public static a getInstance() {
        return c.a;
    }

    public void onEventBackgroundThread(DataFlycGetPushAgpsStatus dataFlycGetPushAgpsStatus) {
        int timeStamp = dataFlycGetPushAgpsStatus.getTimeStamp();
        DJILogHelper.getInstance().LOGE("agps", "onEventBackgroundThread DataFlycGetPushAgpsStatus time = " + timeStamp);
        if (!a.a(timeStamp)) {
            DJILogHelper.getInstance().LOGE("agps", "dataFlycGetPushAgpsStatus not need push");
            a("dataFlycGetPushAgpsStatus not need push ");
        } else if (this.h != b.a) {
            DJILogHelper.getInstance().LOGE("agps", "sendstatus is " + this.h);
        } else {
            this.h = b.b;
            String d = a.d();
            if (d != null) {
                a("file path=" + d);
                File file = new File(d);
                if (file.exists()) {
                    try {
                        this.k = new RandomAccessFile(file, "r");
                        this.g.sendEmptyMessage(0);
                        return;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        this.h = b.a;
                        return;
                    }
                }
                this.h = b.a;
                return;
            }
            a("file path= null");
            a.c();
        }
    }

    private void e() {
        try {
            if (this.k != null) {
                this.k.seek(0);
            }
            this.k.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void a(short s, byte b, byte b2, byte[] bArr) {
        if (bArr == null) {
            e();
            this.g.sendEmptyMessage(2);
            return;
        }
        f.a(s, b, b2);
        f.a(bArr);
        f.start(new 1(this, b));
    }

    private byte[] a(short s) {
        try {
            this.k.seek((long) (i * s));
            this.m = this.k.read(this.l, 0, i);
            if (this.m < i) {
                Object obj = new byte[this.m];
                System.arraycopy(this.l, 0, obj, 0, this.m);
                this.l = obj;
            }
            return this.l;
        } catch (Exception e) {
            DJILogHelper.getInstance().LOGE("agps", "read data error index=" + s);
            e.printStackTrace();
            this.g.sendEmptyMessage(2);
            return null;
        }
    }

    private byte[] a(int i, short s) {
        Object obj = new byte[10];
        System.arraycopy(dji.midware.util.c.a((int) (System.currentTimeMillis() / 1000)), 0, obj, 0, 4);
        System.arraycopy(dji.midware.util.c.a(i), 0, obj, 4, 4);
        System.arraycopy(dji.midware.util.c.b(s), 0, obj, 8, 2);
        return obj;
    }

    private byte[] f() {
        Calendar instance = Calendar.getInstance();
        short s = (short) instance.get(1);
        byte b = (byte) (instance.get(2) + 1);
        byte b2 = (byte) instance.get(5);
        byte b3 = (byte) instance.get(11);
        byte b4 = (byte) instance.get(12);
        byte b5 = (byte) instance.get(13);
        Location e = dji.a.a.getInstance().e();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        if (e != null) {
            i = (int) e.getLatitude();
            i2 = (int) e.getLongitude();
            i3 = (int) e.getAltitude();
            i4 = (int) e.getAccuracy();
        }
        Object obj = new byte[23];
        System.arraycopy(dji.midware.util.c.b(s), 0, obj, 0, 2);
        obj[2] = b;
        obj[3] = b2;
        obj[4] = b3;
        obj[5] = b4;
        obj[6] = b5;
        System.arraycopy(dji.midware.util.c.a(i), 0, obj, 7, 4);
        System.arraycopy(dji.midware.util.c.a(i2), 0, obj, 11, 4);
        System.arraycopy(dji.midware.util.c.a(i3), 0, obj, 15, 4);
        System.arraycopy(dji.midware.util.c.a(i4), 0, obj, 19, 4);
        return obj;
    }

    private void a(String str) {
    }
}
