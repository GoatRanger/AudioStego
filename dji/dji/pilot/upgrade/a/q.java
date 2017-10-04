package dji.pilot.upgrade.a;

import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.pilot.publics.model.DJIUpgradePackListModel;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.pilot.upgrade.UpgradeBaseComponent;
import java.util.ArrayList;

public class q extends UpgradeBaseComponent {

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[o.values().length];

        static {
            try {
                a[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    protected String[] a() {
        return new String[]{"0306", "0500", "0900", "1200", "1201", "1202", "1203", "1500", "1900"};
    }

    protected boolean b() {
        return ServiceManager.getInstance().isRemoteOK() && ServiceManager.getInstance().isOK();
    }

    protected ArrayList<DJIUpgradePack> a(DJIUpgradePackListModel dJIUpgradePackListModel) {
        return dJIUpgradePackListModel.versionlistm100;
    }

    public void onEventBackgroundThread(o oVar) {
        switch (AnonymousClass1.a[oVar.ordinal()]) {
            case 1:
                h();
                return;
            default:
                return;
        }
    }

    protected String a(DJIUpgradePack dJIUpgradePack) {
        if (dJIUpgradePack == null) {
            return null;
        }
        return dJIUpgradePack.version;
    }

    protected String c() {
        return "UpgradeM100Component";
    }
}
