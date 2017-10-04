package dji.pilot.upgrade.a;

import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.pilot.publics.model.DJIUpgradePackListModel;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.pilot.upgrade.UpgradeBaseComponent;
import java.util.ArrayList;

public class w extends UpgradeBaseComponent {
    private static final String b = "UpgradeP3cComponent";

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
        return new String[]{"0400", "1100", "1101", "0100", "0101", "0305", "0306", "0700", "0900", "1200", "1201", "1202", "1203", "1400", "2700"};
    }

    protected boolean b() {
        return ServiceManager.getInstance().isRemoteOK() && ServiceManager.getInstance().isOK();
    }

    protected ArrayList<DJIUpgradePack> a(DJIUpgradePackListModel dJIUpgradePackListModel) {
        if (dJIUpgradePackListModel == null) {
            return null;
        }
        return dJIUpgradePackListModel.versionlistc;
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
        return b;
    }
}
