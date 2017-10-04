package dji.pilot.publics.control.p3cupgrade;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCommonGetDeviceStatus;
import dji.midware.data.model.P3.DataCommonRequestReceiveData;
import dji.midware.data.model.P3.DataCommonRequestUpgrade;
import dji.midware.data.model.P3.DataCommonRestartDevice;
import dji.midware.data.model.P3.DataCommonTranslateComplete;
import dji.midware.e.e;
import dji.midware.util.m;
import java.io.RandomAccessFile;

public class c {
    private static final int b = 5;
    public final String a = "UpgradeDeviceModel";
    private Handler c;
    private DeviceType d = null;
    private dji.pilot.publics.control.upgrade.e.a e = null;
    private c f = null;
    private int g = 0;
    private RandomAccessFile h = null;
    private dji.midware.data.config.P3.a i = null;
    private d j;

    private enum a {
        ToRequestUpgrade,
        ToCheck,
        ToReceiveData,
        ToTranslateData,
        ToSendCompleteNotify,
        ToRestart,
        ToFails,
        ToReCheck,
        ToFinish
    }

    private class b extends Handler {
        final /* synthetic */ c a;

        public b(c cVar, Looper looper) {
            this.a = cVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (a.values()[message.what]) {
                case ToRequestUpgrade:
                    if (this.a.j == null) {
                        this.a.j = d.RequestUpgrade;
                        this.a.c();
                        break;
                    }
                    break;
                case ToCheck:
                    if (this.a.j == d.RequestUpgrade) {
                        this.a.j = d.CheckStatus;
                        this.a.d();
                        break;
                    }
                    break;
                case ToReceiveData:
                    if (this.a.j == d.CheckStatus) {
                        this.a.j = d.ReceiveData;
                        this.a.e();
                        break;
                    }
                    break;
                case ToTranslateData:
                    if (this.a.j == d.ReceiveData) {
                        this.a.j = d.TranslateData;
                        this.a.f();
                        break;
                    }
                    break;
                case ToSendCompleteNotify:
                    if (this.a.j == d.TranslateData) {
                        this.a.j = d.SendCompleteNotify;
                        this.a.g();
                        break;
                    }
                    break;
                case ToRestart:
                    if (this.a.j == d.SendCompleteNotify) {
                        this.a.j = d.RestartModel;
                        this.a.h();
                        break;
                    }
                    break;
                case ToReCheck:
                    if (this.a.j == d.RestartModel) {
                        this.a.j = d.RecheckStatus;
                        this.a.i();
                        break;
                    }
                    break;
                case ToFinish:
                    if (this.a.j == d.RecheckStatus) {
                        this.a.j = d.Success;
                        break;
                    }
                    break;
                case ToFails:
                    this.a.j = d.Fails;
                    break;
            }
            e.a("UpgradeDeviceModel", String.format("firmwareInfo = %s msg = %s status = %s", new Object[]{this.a.e.toString(), "" + r0, "" + this.a.j}));
            if (this.a.j == d.Success || this.a.j == d.Fails) {
                if (this.a.f != null) {
                    if (this.a.j == d.Success) {
                        this.a.f.b();
                    } else if (this.a.j == d.Fails) {
                        e.a("UpgradeDeviceModel", "fails : " + this.a.i);
                        this.a.f.a(this.a.i);
                    }
                }
                this.a.b();
            }
        }
    }

    public interface c {
        void a();

        void a(int i);

        void a(dji.midware.data.config.P3.a aVar);

        void b();
    }

    private enum d {
        RequestUpgrade,
        CheckStatus,
        ReceiveData,
        TranslateData,
        SendCompleteNotify,
        RestartModel,
        Success,
        Fails,
        RecheckStatus
    }

    private void b() {
        this.c = null;
        this.f = null;
    }

    private void a(a aVar) {
        this.c.sendEmptyMessage(aVar.ordinal());
    }

    public c(DeviceType deviceType, dji.pilot.publics.control.upgrade.e.a aVar, RandomAccessFile randomAccessFile, c cVar) {
        this.d = deviceType;
        this.e = aVar;
        this.f = cVar;
        this.h = randomAccessFile;
        this.c = new b(this, dji.midware.util.b.b());
        try {
            this.h.seek(0);
        } catch (Exception e) {
        }
    }

    public void a() {
        a(a.ToRequestUpgrade);
    }

    private void c() {
        e dataCommonRequestUpgrade = new DataCommonRequestUpgrade();
        dataCommonRequestUpgrade.setReceiveType(this.d).setReceiveId(this.e.b);
        new m(dataCommonRequestUpgrade, 5, new dji.midware.e.d(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.c.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a(a.ToCheck);
                    }
                }, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.a(a.ToFails);
            }
        }).a();
    }

    private void d() {
        int i = 10;
        if (this.e.a == 7) {
            if (!ServiceManager.getInstance().isConnected()) {
                dji.midware.b.c.getInstance().a();
                e.a("UpgradeDeviceModel", "tryReConnect");
            }
            i = 30;
            e.a("UpgradeDeviceModel", "connected is ok");
        }
        e dataCommonGetDeviceStatus = new DataCommonGetDeviceStatus();
        dataCommonGetDeviceStatus.setReceiveType(this.d).setReceiveId(this.e.b).setVersioin(0, 0);
        new m(dataCommonGetDeviceStatus, i, new dji.midware.e.d(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.a(a.ToReceiveData);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.i = aVar;
                this.a.a(a.ToFails);
            }
        }).a();
    }

    private void e() {
        final e dataCommonRequestReceiveData = new DataCommonRequestReceiveData();
        dataCommonRequestReceiveData.setReceiveType(this.d).setReceiveId(this.e.b).setDataLength((long) this.e.h);
        new m(dataCommonRequestReceiveData, 5, new dji.midware.e.d(this) {
            final /* synthetic */ c b;

            public void onSuccess(Object obj) {
                this.b.g = dataCommonRequestReceiveData.getReceiveDataLength();
                DJILogHelper.getInstance().LOGD("UpgradeDeviceModel", "translateDateLen : " + this.b.g);
                this.b.a(a.ToTranslateData);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.i = aVar;
                this.b.a(a.ToFails);
            }
        }).a();
    }

    private void f() {
        new h(this.h, this.e, this.g, this.d).a(new dji.pilot.publics.control.p3cupgrade.h.a(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a(a.ToFails);
            }

            public void b() {
                this.a.a(a.ToSendCompleteNotify);
            }

            public void a(int i) {
                if (this.a.f != null) {
                    this.a.f.a(i);
                }
            }
        });
    }

    private void g() {
        e dataCommonTranslateComplete = new DataCommonTranslateComplete();
        dataCommonTranslateComplete.setReceiveType(this.d).setReceiveId(this.e.b).setMD5(this.e.j);
        new m(dataCommonTranslateComplete, new dji.midware.e.d(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.a(a.ToRestart);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.i = aVar;
                this.a.a(a.ToFails);
            }
        }).a();
    }

    private void h() {
        e dataCommonRestartDevice = new DataCommonRestartDevice();
        dataCommonRestartDevice.setReceiveType(this.d).setReceiveId(this.e.b).setRestartType(0).setDelay(1000);
        new m(dataCommonRestartDevice, new dji.midware.e.d(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.c.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass6 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a(a.ToReCheck);
                    }
                }, 8000);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (this.a.e.a == 9) {
                    this.a.c.postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a(a.ToReCheck);
                        }
                    }, 8000);
                    return;
                }
                this.a.i = aVar;
                this.a.a(a.ToFails);
            }
        }).a();
    }

    private void i() {
        int i = 10;
        if (this.e.a == 7 && !ServiceManager.getInstance().isConnected()) {
            dji.midware.b.c.getInstance().a();
            e.a("UpgradeDeviceModel", "tryReConnect");
            i = 30;
        }
        final e dataCommonGetDeviceStatus = new DataCommonGetDeviceStatus();
        dataCommonGetDeviceStatus.setReceiveType(this.d).setReceiveId(this.e.b).setVersioin(0, 0);
        new m(dataCommonGetDeviceStatus, i, new dji.midware.e.d(this) {
            final /* synthetic */ c b;

            public void onSuccess(Object obj) {
                if (dataCommonGetDeviceStatus.getMode() == 0) {
                    this.b.a(a.ToFinish);
                } else {
                    this.b.a(a.ToFails);
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.i = aVar;
                this.b.a(a.ToFails);
            }
        }).a();
    }
}
