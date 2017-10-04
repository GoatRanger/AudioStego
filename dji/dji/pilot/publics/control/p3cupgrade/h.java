package dji.pilot.publics.control.p3cupgrade;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.model.P3.DataCommonTranslateData;
import dji.midware.e.d;
import dji.midware.util.m;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

public class h implements Runnable {
    public final String a = "UpgradeTranslateData";
    d b;
    private DeviceType c = null;
    private dji.pilot.publics.control.upgrade.e.a d = null;
    private int e = 0;
    private RandomAccessFile f = null;
    private a g;
    private int h = -1;
    private CountDownLatch i;
    private dji.midware.data.config.P3.a j;

    public interface a {
        void a();

        void a(int i);

        void b();
    }

    public h(RandomAccessFile randomAccessFile, dji.pilot.publics.control.upgrade.e.a aVar, int i, DeviceType deviceType) {
        this.f = randomAccessFile;
        this.d = aVar;
        this.e = i;
        this.c = deviceType;
    }

    public void a(a aVar) {
        this.g = aVar;
        new Thread(this, "dji.pilot.publics.control.p3cupgrade.UpgradeTranslateData").start();
    }

    public void run() {
        int i = 0;
        int i2 = this.d.h / this.e;
        this.j = dji.midware.data.config.P3.a.b;
        try {
            this.f.seek((long) this.d.g);
            int i3 = 0;
            while (i3 < this.d.h) {
                byte[] bArr = new byte[this.e];
                if (this.e + i3 <= this.d.h) {
                    this.f.read(bArr, 0, this.e);
                } else {
                    this.f.read(bArr, 0, this.d.h - i3);
                }
                final Object data = new DataCommonTranslateData().setReceiveType(this.c).setReceiveId(this.d.b).setSequence(i).setData(bArr);
                this.i = new CountDownLatch(1);
                new m(data, 10, 1000, new d(this) {
                    final /* synthetic */ h b;

                    public void onSuccess(Object obj) {
                        this.b.j = dji.midware.data.config.P3.a.c;
                        this.b.i.countDown();
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.b.j = aVar;
                        this.b.i.countDown();
                        e.a("UpgradeTranslateData", "data transform ret failed for" + aVar + "sequence:" + data.getSequence() + "fail sequence:" + data.getFailSequence());
                    }
                }).a();
                this.i.await();
                if (this.j != dji.midware.data.config.P3.a.g && this.j != dji.midware.data.config.P3.a.s) {
                    if (this.j != dji.midware.data.config.P3.a.c) {
                        break;
                    }
                    i++;
                } else {
                    i = data.getSequence() + 1;
                }
                i3 += this.e;
                this.f.seek((long) (this.d.g + i3));
                int i4 = (i * 100) / i2;
                if (i4 > 100) {
                    i4 = 100;
                }
                if (this.g != null) {
                    this.g.a(i4);
                }
                e.a("UpgradeTranslateData", "progress : " + i4);
                this.h = i4;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.j = dji.midware.data.config.P3.a.a;
        }
        if (this.j == dji.midware.data.config.P3.a.c) {
            this.g.b();
        } else {
            this.g.a();
        }
    }
}
