package dji.dbox.upgrade.p4.statemachine;

import android.content.Context;
import dji.dbox.upgrade.p4.d.b;
import dji.dbox.upgrade.p4.d.b.a;
import dji.dbox.upgrade.p4.model.DJIUpCfgModel;
import dji.midware.data.config.P3.DeviceType;

public class DJIUpCollectPack_wm620_Manager extends b {
    private DJIUpCfgModel acCfgModel;
    b requestAcCfg;

    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    public DJIUpCollectPack_wm620_Manager(final g gVar, Context context) {
        super(gVar, context);
        this.requestAcCfg = new b(context, DeviceType.DM368, new a(this) {
            final /* synthetic */ DJIUpCollectPack_wm620_Manager b;

            public void a(DJIUpCfgModel dJIUpCfgModel) {
                this.b.acCfgModel = dJIUpCfgModel;
                dji.dbox.upgrade.p4.a.a.p = this.b.acCfgModel.b;
                dji.dbox.upgrade.p4.a.a.b(this.b.TAG, "getcfg flycVertion=" + dji.dbox.upgrade.p4.a.a.p);
                this.b.setCfgModel(this.b.acCfgModel);
                this.b.getDeviceVers();
                gVar.j();
            }

            public void a() {
            }
        });
    }

    protected void startGetDeviceCfg() {
        super.startGetDeviceCfg();
        this.requestAcCfg.b();
    }

    protected void resetStatus() {
        super.resetStatus();
        this.requestAcCfg.a();
    }
}
