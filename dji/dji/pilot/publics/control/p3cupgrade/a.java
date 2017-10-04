package dji.pilot.publics.control.p3cupgrade;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
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

public class a implements dji.pilot.publics.model.a {
    private static final int A = 259;
    private static final int B = 260;
    private static final int C = 261;
    private static final int D = 262;
    private static final int E = 264;
    private static final int F = 512;
    private static final long G = 50;
    private static final long H = 2200;
    private static final long I = 4000;
    private static final int J = 0;
    private static final int K = 1;
    private static final String j = a.class.getSimpleName();
    private static final long k = 50;
    private static final int l = 5;
    private static final int x = 256;
    private static final int y = 257;
    private static final int z = 258;
    final DataCommonTranslateData a = new DataCommonTranslateData();
    private DeviceType m = null;
    private dji.pilot.publics.control.rc.a.a n = null;
    private a o = null;
    private b p = null;
    private boolean q = false;
    private int r = 0;
    private RandomAccessFile s = null;
    private c t = null;
    private boolean u = false;
    private volatile boolean v = false;
    private long w = 0;

    private static final class a extends Handler {
        private final WeakReference<a> a;

        private a(a aVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(aVar);
        }

        public void handleMessage(Message message) {
            a aVar = (a) this.a.get();
            if (aVar != null && !aVar.q) {
                if (aVar.v) {
                    aVar.v = false;
                    DJILogHelper.getInstance().LOGD("", "==== Wait For Here ====", false, true);
                    if (message.arg1 == 1) {
                        message.arg2 = 0;
                    }
                    sendMessageDelayed(obtainMessage(message.what, message.arg1, message.arg2, message.obj), a.I);
                    return;
                }
                switch (message.what) {
                    case 256:
                        if (message.arg1 == 0) {
                            aVar.a(257, 0, dji.midware.data.config.P3.a.D);
                            return;
                        } else {
                            aVar.a(message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                            return;
                        }
                    case 257:
                        if (message.arg1 != 0) {
                            aVar.a(257, message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                            return;
                        } else if (message.arg2 == 1) {
                            aVar.b(0, dji.midware.data.config.P3.a.D);
                            return;
                        } else {
                            aVar.a(0, dji.midware.data.config.P3.a.D);
                            return;
                        }
                    case 258:
                        if (message.arg1 == 0) {
                            aVar.r = message.arg2;
                            aVar.e();
                            return;
                        }
                        aVar.b(message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                        return;
                    case 259:
                        aVar.b(0, 0, dji.midware.data.config.P3.a.D);
                        return;
                    case 260:
                        aVar.b(message.arg1, message.arg2, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                        return;
                    case 261:
                        if (message.arg1 == 0) {
                            aVar.c(262, 0, dji.midware.data.config.P3.a.D);
                            return;
                        } else {
                            aVar.c(message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                            return;
                        }
                    case 262:
                        if (message.arg1 == 0) {
                            aVar.a(264, 0, dji.midware.data.config.P3.a.D);
                            return;
                        } else {
                            aVar.c(262, message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                            return;
                        }
                    case 264:
                        if (message.arg1 != 0) {
                            aVar.a(264, message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                            return;
                        } else if (message.arg2 == 0) {
                            aVar.a(2);
                            return;
                        } else {
                            aVar.a(264, message.arg2 + 1, message.obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) message.obj : dji.midware.data.config.P3.a.D);
                            return;
                        }
                    case 512:
                        aVar.b(0, 0, dji.midware.data.config.P3.a.D);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public interface b {
        void a(a aVar, int i);

        void a(a aVar, int i, int i2);

        void a(a aVar, dji.midware.data.config.P3.a aVar2, int i, int i2);

        void b(a aVar, int i);
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
            this.c = dji.midware.data.config.P3.a.D;
            this.d = null;
            this.e = false;
        }
    }

    public a(DeviceType deviceType, dji.pilot.publics.control.rc.a.a aVar, RandomAccessFile randomAccessFile, b bVar) {
        this.m = deviceType;
        this.n = aVar;
        this.p = bVar;
        this.s = randomAccessFile;
        try {
            this.s.seek(0);
        } catch (Exception e) {
        }
        this.o = new a();
    }

    public void a(boolean z) {
        this.u = z;
    }

    public DeviceType a() {
        return this.m;
    }

    public void b() {
        b(0);
        a(0, dji.midware.data.config.P3.a.D);
    }

    public void c() {
        this.q = true;
    }

    private void a(dji.midware.data.config.P3.a aVar, int i, int i2) {
        if (this.p != null) {
            this.p.a(this, aVar, i, i2);
        }
    }

    private void a(int i) {
        if (this.p != null) {
            this.p.b(this, i);
        }
    }

    private void b(int i) {
        if (this.p != null) {
            this.p.a(this, i);
        }
    }

    private void a(int i, int i2) {
        if (this.p != null) {
            this.p.a(this, i, i2);
        }
    }

    private void a(final int i, dji.midware.data.config.P3.a aVar) {
        DJILogHelper.getInstance().LOGD(j, "requestEnterUpgradeMode[" + i + "]c[" + aVar + d.H, false, true);
        if (i < 5) {
            new DataCommonRequestUpgrade().setReceiveType(this.m).setReceiveId(this.n.b).start(new dji.midware.e.d(this) {
                final /* synthetic */ a b;

                public void onSuccess(Object obj) {
                    this.b.o.obtainMessage(256, 0, i).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.b.o.sendMessageDelayed(this.b.o.obtainMessage(256, 1, i, aVar), 50);
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
            dataCommonGetDeviceStatus.setReceiveType(this.m).setReceiveId(this.n.b).setVersioin(0, 0).start(new dji.midware.e.d(this) {
                final /* synthetic */ a d;

                public void onSuccess(Object obj) {
                    this.d.o.sendMessageDelayed(this.d.o.obtainMessage(i, 0, dataCommonGetDeviceStatus.getMode()), 50);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.d.o.sendMessageDelayed(this.d.o.obtainMessage(i, 1, i2, aVar), 50);
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
            dataCommonRequestReceiveData.setReceiveType(this.m).setReceiveId(this.n.b).setDataLength((long) this.n.h).start(new dji.midware.e.d(this) {
                final /* synthetic */ a c;

                public void onSuccess(Object obj) {
                    this.c.o.obtainMessage(258, 0, dataCommonRequestReceiveData.getReceiveDataLength()).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.c.o.sendMessageDelayed(this.c.o.obtainMessage(258, 1, i, aVar), 50);
                }
            });
            return;
        }
        a(aVar, 103, (int) R.string.rcupgrade_fail_receive_data);
    }

    private void d() {
        int i = 0;
        Arrays.fill(this.t.d, (byte) 0);
        int i2 = this.r * this.t.b;
        int i3 = this.r;
        if (this.r + i2 >= this.n.h) {
            i3 = this.n.h - i2;
            this.t.e = true;
        }
        try {
            this.s.seek((long) (i2 + this.n.g));
            do {
                i2 = this.s.read(this.t.d, i, i3 - i);
                if (i2 == -1) {
                    break;
                }
                i += i2;
            } while (i < i3);
        } catch (Exception e) {
        }
        this.w += (long) i3;
        a(i3, this.n.h);
    }

    private void e() {
        this.o.sendEmptyMessageDelayed(512, 50);
    }

    private void b(int i, int i2, dji.midware.data.config.P3.a aVar) {
        if (this.t == null) {
            this.t = new c();
            this.t.d = new byte[this.r];
        }
        c cVar;
        if (i != 0) {
            int i3 = i2 + 1;
            this.t.c = aVar;
            if (i3 == this.t.b || !(aVar == dji.midware.data.config.P3.a.s || aVar == dji.midware.data.config.P3.a.D)) {
                cVar = this.t;
                cVar.a++;
            } else {
                this.t.a = 0;
                this.t.b = i3;
                d();
            }
            f();
        } else if (this.t.e) {
            c(0, dji.midware.data.config.P3.a.D);
        } else {
            if (this.t.b == 0) {
                DJILogHelper.getInstance().LOGD("", "translate time start[" + (System.currentTimeMillis() / 1000) + d.H, false, true);
            }
            this.t.a = 0;
            this.t.c = dji.midware.data.config.P3.a.D;
            cVar = this.t;
            cVar.b++;
            d();
            f();
        }
    }

    private void f() {
        if (this.t.a < 5) {
            this.a.setReceiveType(this.m).setReceiveId(this.n.b).setSequence(this.t.b).setData(this.t.d).start(new dji.midware.e.d(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.o.obtainMessage(260, 0, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.o.sendMessageDelayed(this.a.o.obtainMessage(260, 1, this.a.a.getSequence(), aVar), 50);
                }
            });
        } else {
            a(this.t.c, 104, (int) R.string.rcupgrade_fail_translate_data);
        }
    }

    private void c(final int i, dji.midware.data.config.P3.a aVar) {
        DJILogHelper.getInstance().LOGD(j, "translateComplete[" + i + "]c[" + aVar + d.H, false, true);
        if (i < 5) {
            new DataCommonTranslateComplete().setReceiveType(this.m).setReceiveId(this.n.b).setMD5(this.n.j).start(new dji.midware.e.d(this) {
                final /* synthetic */ a b;

                public void onSuccess(Object obj) {
                    this.b.o.obtainMessage(261, 0, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.b.o.sendMessageDelayed(this.b.o.obtainMessage(261, 1, i, aVar), 50);
                }
            });
        } else {
            a(aVar, 105, (int) R.string.rcupgrade_fail_translate_complete);
        }
    }

    private void c(final int i, int i2, dji.midware.data.config.P3.a aVar) {
        if (this.u) {
            DJILogHelper.getInstance().LOGD(j, "restartModel[" + i2 + "]c[" + aVar + d.H, false, true);
            if (i2 < 5) {
                new DataCommonRestartDevice().setReceiveType(this.m).setReceiveId(this.n.b).setRestartType(0).setDelay(0).start(new dji.midware.e.d(this) {
                    final /* synthetic */ a b;

                    public void onSuccess(Object obj) {
                        this.b.o.sendMessageDelayed(this.b.o.obtainMessage(i, 0, 0), a.H);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.b.o.sendMessageDelayed(this.b.o.obtainMessage(i, 0, 0), a.H);
                    }
                });
                return;
            } else {
                a(aVar, 102, (int) R.string.rcupgrade_fail_restart_model);
                return;
            }
        }
        a(0);
    }
}
