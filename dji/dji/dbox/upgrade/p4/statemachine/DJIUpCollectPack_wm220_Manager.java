package dji.dbox.upgrade.p4.statemachine;

import android.content.Context;
import dji.dbox.upgrade.p4.d.b;
import dji.dbox.upgrade.p4.d.b.a;
import dji.dbox.upgrade.p4.model.DJIUpCfgModel;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.ServiceManager;

public class DJIUpCollectPack_wm220_Manager extends b {
    private DJIUpCfgModel acCfgModel;
    private boolean acCfgSetted;
    private DJIUpCfgModel rcCfgModel;
    private boolean rcCfgSetted;
    b requestAcCfg;
    private b requestRcCfg;
    private DJIUpCfgModel smallCfgModel;

    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    public DJIUpCollectPack_wm220_Manager(g gVar, Context context) {
        super(gVar, context);
        this.requestAcCfg = new b(context, DeviceType.DM368, new a(this) {
            final /* synthetic */ DJIUpCollectPack_wm220_Manager a;

            {
                this.a = r1;
            }

            public void a(DJIUpCfgModel dJIUpCfgModel) {
                this.a.acCfgModel = dJIUpCfgModel;
                dji.dbox.upgrade.p4.a.a.p = this.a.acCfgModel.b;
                dji.dbox.upgrade.p4.a.a.b(this.a.TAG, "getcfg flycVertion=" + dji.dbox.upgrade.p4.a.a.p);
                this.a.acCfgSetted = true;
                this.a.monitorCfgCallBack();
            }

            public void a() {
                this.a.acCfgSetted = true;
            }
        });
        this.requestRcCfg = new b(context, DeviceType.DM368_G, new a(this) {
            final /* synthetic */ DJIUpCollectPack_wm220_Manager a;

            {
                this.a = r1;
            }

            public void a(DJIUpCfgModel dJIUpCfgModel) {
                this.a.rcCfgModel = dJIUpCfgModel;
                dji.dbox.upgrade.p4.a.a.q = this.a.rcCfgModel.b;
                dji.dbox.upgrade.p4.a.a.b(this.a.TAG, "getcfg rcVertion=" + dji.dbox.upgrade.p4.a.a.q);
                this.a.rcCfgSetted = true;
                this.a.monitorCfgCallBack();
            }

            public void a() {
                this.a.rcCfgSetted = true;
            }
        });
    }

    private synchronized void monitorCfgCallBack() {
        if (this.rcCfgSetted && this.acCfgSetted) {
            pickSmallVersion();
            if (this.smallCfgModel != null) {
                setCfgModel(this.smallCfgModel);
                getDeviceVers();
                this.stateMachine.j();
            }
        }
    }

    private void pickSmallVersion() {
        if (this.acCfgModel == null || this.acCfgModel.b()) {
            this.smallCfgModel = this.rcCfgModel;
        } else if (this.rcCfgModel == null || this.rcCfgModel.b()) {
            this.smallCfgModel = this.acCfgModel;
        } else if (compareFirVer(this.acCfgModel.b, this.rcCfgModel.b) >= 0) {
            this.smallCfgModel = this.rcCfgModel;
        } else {
            this.smallCfgModel = this.acCfgModel;
        }
    }

    protected void startGetDeviceCfg() {
        super.startGetDeviceCfg();
        if (DJIUpgradeP4Service.e() || ServiceManager.getInstance().isRemoteOK()) {
            this.requestAcCfg.b();
        } else {
            this.acCfgSetted = true;
        }
        if (DJIUpgradeP4Service.d()) {
            this.requestRcCfg.b();
        } else {
            this.rcCfgSetted = true;
        }
    }

    protected boolean isAllSetted() {
        return super.isAllSetted() && this.rcCfgSetted;
    }

    protected void resetStatus() {
        super.resetStatus();
        this.requestAcCfg.a();
        this.acCfgSetted = false;
        this.requestRcCfg.a();
        this.rcCfgSetted = false;
    }
}
