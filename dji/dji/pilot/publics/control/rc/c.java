package dji.pilot.publics.control.rc;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCommonGetDeviceStatus;
import dji.midware.data.model.P3.DataCommonRequestReceiveData;
import dji.midware.data.model.P3.DataCommonRequestUpgrade;
import dji.midware.data.model.P3.DataCommonRestartDevice;
import dji.midware.data.model.P3.DataCommonTranslateComplete;
import dji.midware.data.model.P3.DataCommonTranslateData;
import dji.pilot.R;
import dji.pilot.usercenter.protocol.d;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public class c implements dji.pilot.publics.model.a {
    private static final int C = 256;
    private static final int D = 257;
    private static final int E = 258;
    private static final int F = 259;
    private static final int G = 260;
    private static final int H = 261;
    private static final int I = 262;
    private static final int J = 263;
    private static final int K = 264;
    private static final int L = 265;
    private static final int M = 266;
    private static final int N = 512;
    private static final long O = 50;
    private static final int P = 768;
    private static final long Q = 1000;
    private static final long R = 130000;
    private static final long S = 2200;
    private static final long T = 6000;
    private static final int U = 0;
    private static final int V = 1;
    private static final String j = c.class.getSimpleName();
    private static final long k = 50;
    private static final boolean l = true;
    private static final int m = 5;
    private static final int n = 2048;
    private long A = 0;
    private int B = 0;
    final DataCommonTranslateData a = new DataCommonTranslateData();
    private DeviceType o = null;
    private int p = 0;
    private dji.pilot.publics.control.rc.a.a q = null;
    private a r = null;
    private b s = null;
    private boolean t = false;
    private int u = 0;
    private RandomAccessFile v = null;
    private c w = null;
    private boolean x = false;
    private boolean y = false;
    private volatile boolean z = false;

    public interface b {
        void a(c cVar, int i);

        void a(c cVar, int i, int i2);

        void a(c cVar, dji.midware.data.config.P3.a aVar, int i, int i2);

        void b(c cVar, int i);
    }

    private static final class a extends Handler {
        private final WeakReference<c> a;

        private a(c cVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(cVar);
        }

        public void handleMessage(Message message) {
            c cVar = (c) this.a.get();
            if (cVar != null && !cVar.t) {
                if (cVar.z) {
                    cVar.z = false;
                    DJILogHelper.getInstance().LOGD(c.j, "==== Wait For Here ====", false, true);
                    if (message.arg1 == 1) {
                        message.arg2 = 0;
                    }
                    sendMessageDelayed(obtainMessage(message.what, message.arg1, message.arg2, message.obj), 6000);
                    return;
                }
                switch (message.what) {
                    case 256:
                        if (message.arg1 == 0) {
                            cVar.a(257, 0, dji.midware.data.config.P3.a.D);
                            return;
                        } else {
                            cVar.a(message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                            return;
                        }
                    case 257:
                        if (message.arg1 != 0) {
                            cVar.a(257, message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                            return;
                        } else if (message.arg2 == 1) {
                            cVar.b(0, dji.midware.data.config.P3.a.D);
                            return;
                        } else {
                            cVar.a(0, dji.midware.data.config.P3.a.D);
                            return;
                        }
                    case 258:
                        if (message.arg1 == 0) {
                            cVar.u = message.arg2;
                            cVar.h();
                            return;
                        }
                        cVar.b(message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                        return;
                    case 259:
                        cVar.b(0, 0, dji.midware.data.config.P3.a.D);
                        return;
                    case 260:
                        cVar.b(message.arg1, message.arg2, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                        return;
                    case 261:
                        if (message.arg1 == 0) {
                            cVar.c(262, 0, dji.midware.data.config.P3.a.D);
                            return;
                        } else {
                            cVar.c(message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                            return;
                        }
                    case 262:
                        if (message.arg1 == 0) {
                            cVar.a(264, 0, dji.midware.data.config.P3.a.D);
                            return;
                        } else {
                            cVar.c(262, message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                            return;
                        }
                    case 263:
                        removeMessages(c.P);
                        cVar.b(0, 0, dji.midware.data.config.P3.a.D);
                        return;
                    case 264:
                        if (message.arg1 != 0) {
                            cVar.a(264, message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                            return;
                        } else if (message.arg2 == 0) {
                            cVar.a(2);
                            return;
                        } else {
                            cVar.a(264, message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                            return;
                        }
                    case c.L /*265*/:
                        if (message.arg1 == 0) {
                            cVar.a(0, dji.midware.data.config.P3.a.D);
                            return;
                        } else {
                            cVar.c((int) c.L, message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                            return;
                        }
                    case c.M /*266*/:
                        if (message.arg1 == 0) {
                            cVar.a(0, dji.midware.data.config.P3.a.D);
                            return;
                        } else {
                            cVar.a((int) c.M, message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                            return;
                        }
                    case 512:
                        cVar.b(0, 0, dji.midware.data.config.P3.a.D);
                        return;
                    case c.P /*768*/:
                        cVar.i();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static final class c {
        public int a;
        public int b;
        public dji.midware.data.config.P3.a c;
        public byte[] d;
        public boolean e;

        private c() {
            this.a = 0;
            this.b = -1;
            this.c = dji.midware.data.config.P3.a.a;
            this.d = null;
            this.e = false;
        }
    }

    public c(DeviceType deviceType, int i, dji.pilot.publics.control.rc.a.a aVar, RandomAccessFile randomAccessFile, b bVar) {
        this.o = deviceType;
        this.p = i;
        this.q = aVar;
        this.s = bVar;
        this.v = randomAccessFile;
        try {
            this.v.seek(0);
        } catch (Exception e) {
        }
        this.r = new a();
        if (this.o == DeviceType.FPGA_G) {
            this.x = true;
            this.B = (int) (((((long) aVar.h) * 1000) / 3) / R);
        }
    }

    public void a(boolean z) {
        this.z = z;
    }

    public void b(boolean z) {
        this.y = z;
    }

    public DeviceType a() {
        return this.o;
    }

    public void b() {
        DJILogHelper.getInstance().LOGD(j, "startUpgrade", false, true);
        b(0);
        if (this.y) {
            c((int) L, 0, dji.midware.data.config.P3.a.D);
        } else {
            a(0, dji.midware.data.config.P3.a.D);
        }
    }

    public void c() {
        this.t = true;
    }

    public void d() {
        this.r.removeCallbacksAndMessages(null);
    }

    private void a(dji.midware.data.config.P3.a aVar, int i, int i2) {
        if (this.s != null) {
            this.s.a(this, aVar, i, i2);
        }
    }

    private void a(int i) {
        if (this.s != null) {
            this.s.a(this, i);
        }
    }

    private void b(int i) {
        if (this.s != null) {
            this.s.b(this, i);
        }
    }

    private void a(int i, int i2) {
        if (this.s != null) {
            this.s.a(this, i, i2);
        }
    }

    private void a(final int i, dji.midware.data.config.P3.a aVar) {
        DJILogHelper.getInstance().LOGD(j, "requestEnterUpgradeMode[" + i + "]c[" + aVar + d.H, false, true);
        if (i < 5) {
            new DataCommonRequestUpgrade().setReceiveType(this.o).setReceiveId(this.p).start(new dji.midware.e.d(this) {
                final /* synthetic */ c b;

                public void onSuccess(Object obj) {
                    this.b.r.obtainMessage(256, 0, i).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.b.r.sendMessageDelayed(this.b.r.obtainMessage(256, 1, i, aVar), 50);
                }
            });
        } else {
            a(aVar, 101, (int) R.string.rcupgrade_fail_request_upgrade_mode);
        }
    }

    private void a(final int i, final int i2, dji.midware.data.config.P3.a aVar) {
        DJILogHelper.getInstance().LOGD(j, "checkModelStatus id[" + i + "]times[" + i2 + "]c[" + aVar + d.H, false, true);
        if (i2 < 5) {
            final DataCommonGetDeviceStatus dataCommonGetDeviceStatus = new DataCommonGetDeviceStatus();
            dataCommonGetDeviceStatus.setReceiveType(this.o).setReceiveIdForce(this.p).setVersioin(0, 0).start(new dji.midware.e.d(this) {
                final /* synthetic */ c d;

                public void onSuccess(Object obj) {
                    this.d.r.sendMessageDelayed(this.d.r.obtainMessage(i, 0, dataCommonGetDeviceStatus.getMode()), 50);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.d.r.sendMessageDelayed(this.d.r.obtainMessage(i, 1, i2, aVar), 50);
                }
            });
            return;
        }
        a(aVar, 102, (int) R.string.rcupgrade_fail_check_status);
    }

    private void b(final int i, dji.midware.data.config.P3.a aVar) {
        DJILogHelper.getInstance().LOGD(j, "requestReceiveData[" + i + "]c[" + aVar + d.H, false, true);
        if (i < 5) {
            final DataCommonRequestReceiveData dataCommonRequestReceiveData = new DataCommonRequestReceiveData();
            dataCommonRequestReceiveData.setReceiveType(this.o).setReceiveId(this.p).setDataLength((long) this.q.h).start(new dji.midware.e.d(this) {
                final /* synthetic */ c c;

                public void onSuccess(Object obj) {
                    this.c.r.obtainMessage(258, 0, dataCommonRequestReceiveData.getReceiveDataLength()).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.c.r.sendMessageDelayed(this.c.r.obtainMessage(258, 1, i, aVar), 50);
                }
            });
            return;
        }
        a(aVar, 103, (int) R.string.rcupgrade_fail_receive_data);
    }

    public short e() {
        try {
            int i;
            byte[] bArr = new byte[2048];
            this.v.seek((long) this.q.g);
            int i2 = this.q.h;
            if (i2 > 2048) {
                i = 2048;
            } else {
                i = i2;
            }
            long j = 0;
            while (i2 > 0) {
                int read = this.v.read(bArr, 0, i);
                if (read == -1) {
                    break;
                }
                for (i = 0; i < read; i++) {
                    j += (long) (bArr[i] & 255);
                }
                i2 -= read;
                i = i2 > 2048 ? 2048 : i2;
            }
            short s = (short) ((int) j);
            try {
                DJILogHelper.getInstance().LOGD(j, "checkSum[" + s + "]sumHex[" + dji.midware.util.c.i(dji.midware.util.c.b(s)) + d.H, false, true);
                return s;
            } catch (Exception e) {
                return s;
            }
        } catch (Exception e2) {
            return (short) 0;
        } catch (Throwable th) {
        }
    }

    private void g() {
        int i = 0;
        Arrays.fill(this.w.d, (byte) 0);
        int i2 = this.w.b * this.u;
        int i3 = this.u;
        if (this.u + i2 >= this.q.h) {
            i3 = this.q.h - i2;
            this.w.e = true;
        } else {
            this.w.e = false;
        }
        int i4 = this.B != 0 ? this.w.e ? (int) (((long) this.q.h) - this.A) : (i3 * 2) / 3 : i3;
        try {
            this.v.seek((long) (i2 + this.q.g));
            do {
                i2 = this.v.read(this.w.d, i, i3 - i);
                if (i2 == -1) {
                    break;
                }
                i += i2;
            } while (i < i3);
        } catch (Exception e) {
        }
        this.A += (long) i4;
        a(i4, this.q.h);
    }

    private void h() {
        if (this.o == DeviceType.FPGA_G) {
            this.r.sendEmptyMessageDelayed(512, 50);
        } else {
            this.r.sendEmptyMessageDelayed(512, 50);
        }
    }

    private void i() {
        if (ServiceManager.getInstance().isConnected()) {
            this.A += (long) this.B;
            a(this.B, this.q.h);
            if (this.r.hasMessages(263)) {
                this.r.sendEmptyMessageDelayed(P, 1000);
                return;
            }
            return;
        }
        a(dji.midware.data.config.P3.a.E, 103, (int) R.string.rcupgrade_fail_receive_data);
    }

    private void b(int i, int i2, dji.midware.data.config.P3.a aVar) {
        if (this.w == null) {
            this.w = new c();
            this.w.d = new byte[this.u];
        }
        c cVar;
        Object b;
        if (i != 0) {
            int i3 = i2 + 1;
            this.w.c = aVar;
            if (i3 == this.w.b || !(aVar == dji.midware.data.config.P3.a.s || aVar == dji.midware.data.config.P3.a.D)) {
                cVar = this.w;
                cVar.a++;
                j();
            } else if (this.u * i3 < this.q.h) {
                this.w.a = 0;
                this.w.b = i3;
                g();
                j();
            } else {
                this.w.e = true;
                if (this.o == DeviceType.FPGA_G) {
                    b = dji.midware.util.c.b(e());
                    Arrays.fill(this.q.j, (byte) 0);
                    System.arraycopy(b, 0, this.q.j, 0, 2);
                }
                c(0, dji.midware.data.config.P3.a.D);
            }
        } else if (this.w.e) {
            if (this.o == DeviceType.FPGA_G) {
                b = dji.midware.util.c.b(e());
                Arrays.fill(this.q.j, (byte) 0);
                System.arraycopy(b, 0, this.q.j, 0, 2);
            }
            c(0, dji.midware.data.config.P3.a.D);
        } else if (this.x && this.w.b == 0) {
            this.x = false;
            this.r.sendEmptyMessageDelayed(263, R);
            this.r.sendEmptyMessageDelayed(P, 1000);
        } else {
            if (this.w.b == 0) {
                DJILogHelper.getInstance().LOGD(j, "translate time start[" + (System.currentTimeMillis() / 1000) + d.H, false, true);
            }
            this.w.a = 0;
            this.w.c = dji.midware.data.config.P3.a.a;
            cVar = this.w;
            cVar.b++;
            g();
            j();
        }
    }

    private void j() {
        if (this.w.a < 5) {
            this.a.setReceiveType(this.o).setReceiveId(this.p).setSequence(this.w.b).setData(this.w.d).start(new dji.midware.e.d(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.r.obtainMessage(260, 0, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.r.sendMessageDelayed(this.a.r.obtainMessage(260, 1, this.a.a.getSequence(), aVar), 50);
                }
            });
        } else {
            a(this.w.c, 104, (int) R.string.rcupgrade_fail_translate_data);
        }
    }

    private void c(final int i, dji.midware.data.config.P3.a aVar) {
        DJILogHelper.getInstance().LOGD(j, "translateComplete[" + i + "]c[" + aVar + d.H, false, true);
        if (i < 5) {
            new DataCommonTranslateComplete().setReceiveType(this.o).setReceiveId(this.p).setMD5(this.q.j).start(new dji.midware.e.d(this) {
                final /* synthetic */ c b;

                public void onSuccess(Object obj) {
                    this.b.r.obtainMessage(261, 0, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.b.r.sendMessageDelayed(this.b.r.obtainMessage(261, 1, i, aVar), 50);
                }
            });
        } else {
            a(aVar, 105, (int) R.string.rcupgrade_fail_translate_complete);
        }
    }

    private void c(final int i, final int i2, dji.midware.data.config.P3.a aVar) {
        if (this.y) {
            DJILogHelper.getInstance().LOGD(j, "restartModel[" + i2 + "]c[" + aVar + d.H, false, true);
            if (i2 < 5) {
                new DataCommonRestartDevice().setReceiveType(this.o).setReceiveIdForce(this.p).setRestartType(0).setDelay(i == L ? 1000 : 0).start(new dji.midware.e.d(this) {
                    final /* synthetic */ c c;

                    public void onSuccess(Object obj) {
                        this.c.r.sendMessageDelayed(this.c.r.obtainMessage(i, 0, 0), c.S);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        if (i == c.L) {
                            this.c.r.sendMessageDelayed(this.c.r.obtainMessage(i, 1, i2, aVar), 50);
                        } else {
                            this.c.r.sendMessageDelayed(this.c.r.obtainMessage(i, 0, 0), c.S);
                        }
                    }
                });
                return;
            }
            a(aVar, 102, (int) R.string.rcupgrade_fail_restart_model);
            return;
        }
        a(0);
    }
}
