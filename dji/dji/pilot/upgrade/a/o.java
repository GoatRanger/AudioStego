package dji.pilot.upgrade.a;

import dji.midware.data.manager.P3.ServiceManager;
import dji.pilot.publics.model.DJIUpgradePackListModel;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.pilot.upgrade.UpgradeBaseComponent;
import java.util.ArrayList;

public class o extends UpgradeBaseComponent {

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[dji.midware.data.manager.P3.o.values().length];

        static {
            try {
                a[dji.midware.data.manager.P3.o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    protected String[] a() {
        return new String[]{"0800", "0700", "0100", "0101", "0104", "0106", "0400", "0900"};
    }

    protected boolean b() {
        return ServiceManager.getInstance().isRemoteOK() && ServiceManager.getInstance().isOK();
    }

    protected ArrayList<DJIUpgradePack> a(DJIUpgradePackListModel dJIUpgradePackListModel) {
        return dJIUpgradePackListModel.versionlisthgX5R;
    }

    public void onEventBackgroundThread(dji.midware.data.manager.P3.o oVar) {
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
        return "UpgradeLonganRawComponent";
    }
}