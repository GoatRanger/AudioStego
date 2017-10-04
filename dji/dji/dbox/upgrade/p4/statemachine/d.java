package dji.dbox.upgrade.p4.statemachine;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service.a;
import dji.midware.data.model.P3.DataCommonControlUpgrade;
import dji.midware.data.model.P3.DataCommonControlUpgrade.ControlCmd;
import dji.midware.data.model.P3.DataCommonGetPushUpgradeStatus;
import dji.midware.data.model.P3.DataCommonGetPushUpgradeStatus.DJIUpgradeCompleteReason;
import dji.midware.data.model.P3.DataCommonGetPushUpgradeStatus.DJIUpgradeStep;
import dji.midware.data.model.P3.DataCommonGetPushUpgradeStatus.DataCommonGetPushUpgradeStatusInfo;
import dji.midware.data.model.P3.DataNotifyDisconnect;
import dji.publics.DJIObject.c;
import java.util.Timer;
import java.util.TimerTask;

class d extends c {
    private String a = getClass().getSimpleName();
    private g b;
    private DataCommonControlUpgrade c;
    private int d;
    private Timer e;
    private int f;
    private float g;
    private DJIUpgradeCompleteReason h = null;

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[a.values().length];

        static {
            c = new int[DJIUpgradeCompleteReason.values().length];
            try {
                c[DJIUpgradeCompleteReason.Success.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            b = new int[DJIUpgradeStep.values().length];
            try {
                b[DJIUpgradeStep.Verify.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[DJIUpgradeStep.UserConfirm.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[DJIUpgradeStep.Upgrading.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            try {
                b[DJIUpgradeStep.Complete.ordinal()] = 4;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[a.d.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public d(g gVar) {
        this.b = gVar;
        this.c = new DataCommonControlUpgrade();
        this.c.a(1);
        c();
    }

    protected void a() {
        dji.thirdparty.a.c.a().a(this);
    }

    public void b() {
        dji.thirdparty.a.c.a().d(this);
    }

    private void c() {
        DataCommonGetPushUpgradeStatusInfo descList = DataCommonGetPushUpgradeStatus.getInstance().getDescList();
        if (descList != null && descList.mUpgradeStep != null) {
            this.b.u();
            onEventMainThread(DataCommonGetPushUpgradeStatus.getInstance());
        }
    }

    private void d() {
        DataCommonGetPushUpgradeStatusInfo descList = DataCommonGetPushUpgradeStatus.getInstance().getDescList();
        if (descList != null && descList.mUpgradeStep != null && descList.completeReason != DJIUpgradeCompleteReason.Success) {
            this.b.u();
        }
    }

    public void onEventMainThread(DataNotifyDisconnect dataNotifyDisconnect) {
        this.d = dataNotifyDisconnect.b();
        if (this.d > DJIUpgradeP4Service.c) {
            DJIUpgradeP4Service.c = this.d;
        } else {
            this.d = DJIUpgradeP4Service.c;
        }
        dji.dbox.upgrade.p4.a.a.a(this.a, "notifyDisconnect timeout=" + this.d);
        e();
    }

    public void onEventMainThread(a aVar) {
        switch (AnonymousClass3.a[aVar.ordinal()]) {
            case 1:
                if (dji.dbox.upgrade.p4.a.a.n) {
                    this.d = 2;
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void e() {
        int i = 99 - this.f;
        final float f = (((float) i) * 1.0f) / ((float) this.d);
        this.g = (float) this.f;
        f();
        this.e = new Timer();
        dji.dbox.upgrade.p4.a.a.a(this.a, "timer schedule remainProcess=" + i);
        this.e.schedule(new TimerTask(this) {
            final /* synthetic */ d b;

            public void run() {
                this.b.d = this.b.d - 1;
                if (this.b.d <= 0) {
                    this.b.f();
                    return;
                }
                this.b.g = this.b.g + f;
                int round = Math.round(this.b.g);
                dji.dbox.upgrade.p4.a.a.a(this.b.a, "tmpProcess=" + this.b.g + " mpgs=" + round + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.b.e);
                if (round >= 100) {
                    round = 99;
                }
                this.b.a(round);
            }
        }, 0, 1000);
    }

    public void onEventMainThread(DataCommonGetPushUpgradeStatus dataCommonGetPushUpgradeStatus) {
        if (DJIUpgradeP4Service.c()) {
            if (!dji.dbox.upgrade.p4.a.a.n) {
                dji.dbox.upgrade.p4.a.a.n = true;
                dji.dbox.upgrade.p4.a.a.a(this.a, "recover to progress!!!!!!!!!! ");
                d();
            }
            DataCommonGetPushUpgradeStatusInfo descList = dataCommonGetPushUpgradeStatus.getDescList();
            switch (descList.mUpgradeStep) {
                case Verify:
                    g();
                    return;
                case UserConfirm:
                    h();
                    return;
                case Upgrading:
                    f();
                    a(descList.mUpgradeProcess);
                    return;
                case Complete:
                    f();
                    a(descList.completeReason);
                    return;
                default:
                    return;
            }
        }
    }

    private void f() {
        if (this.e != null) {
            this.e.cancel();
            this.e = null;
        }
    }

    private void g() {
        this.b.a("固件校验中", 0);
    }

    private void h() {
        this.b.a("用户确认中", 0);
    }

    private void a(int i) {
        if (i != this.f) {
            this.f = i;
            dji.dbox.upgrade.p4.a.a.a(this.a, "mUpgradeProcess=" + i);
        }
        this.b.a("升级中", i);
    }

    private void a(DJIUpgradeCompleteReason dJIUpgradeCompleteReason) {
        if (this.h != dJIUpgradeCompleteReason) {
            this.h = dJIUpgradeCompleteReason;
            this.b.a("结束原因：" + dJIUpgradeCompleteReason.toString(), 100);
            dji.dbox.upgrade.p4.a.a.a(this.a, "结束原因：" + dJIUpgradeCompleteReason.toString());
            this.c.a(DataCommonGetPushUpgradeStatus.getInstance().getSenderDeviceType());
            this.c.a(ControlCmd.StopPush).start(new dji.midware.e.d(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    dji.dbox.upgrade.p4.a.a.a(this.a.a, "setControlCmd StopPush suc");
                    this.a.h = null;
                    dji.dbox.upgrade.p4.a.a.n = false;
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    dji.dbox.upgrade.p4.a.a.a(this.a.a, "setControlCmd StopPush ccode+" + aVar);
                    this.a.h = null;
                    dji.dbox.upgrade.p4.a.a.n = false;
                }
            });
            switch (dJIUpgradeCompleteReason) {
                case Success:
                    this.b.t();
                    return;
                default:
                    this.b.a(dJIUpgradeCompleteReason);
                    return;
            }
        }
    }
}
