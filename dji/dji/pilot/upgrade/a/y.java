package dji.pilot.upgrade.a;

import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.p;
import dji.pilot.publics.model.DJIUpgradePackListModel;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.pilot.upgrade.UpgradeBaseComponent;
import dji.pilot.upgrade.d;
import java.util.ArrayList;

public class y extends UpgradeBaseComponent {

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[p.values().length];

        static {
            try {
                a[p.b.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    protected String[] a() {
        return new String[]{"1300", "1400", "1401", "1600", "1601", "2000", "2001", "2002"};
    }

    protected boolean b() {
        boolean isOK = ServiceManager.getInstance().isOK();
        d.a("UpgradeP3sRcComponent connented : " + isOK);
        return isOK;
    }

    protected ArrayList<DJIUpgradePack> a(DJIUpgradePackListModel dJIUpgradePackListModel) {
        return dJIUpgradePackListModel.versionlists;
    }

    public void onEventBackgroundThread(p pVar) {
        d.a("UpgradeP3sRcComponent event : " + pVar);
        switch (AnonymousClass1.a[pVar.ordinal()]) {
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
        return dJIUpgradePack.rcversion;
    }

    protected String c() {
        return "UpgradeP3sRcComponent";
    }
}
