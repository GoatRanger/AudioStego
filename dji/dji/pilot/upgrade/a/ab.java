package dji.pilot.upgrade.a;

import dji.dbox.upgrade.p4.a.a;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.pilot.publics.model.DJIUpgradePackListModel;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.pilot.upgrade.UpgradeBaseComponent;
import java.util.ArrayList;

public class ab extends UpgradeBaseComponent {

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
        return new String[0];
    }

    protected boolean b() {
        return ServiceManager.getInstance().isRemoteOK() && ServiceManager.getInstance().isOK();
    }

    protected ArrayList<DJIUpgradePack> a(DJIUpgradePackListModel dJIUpgradePackListModel) {
        return dJIUpgradePackListModel.versionlistx;
    }

    public void onEventBackgroundThread(o oVar) {
        switch (AnonymousClass1.a[oVar.ordinal()]) {
        }
    }

    public String e() {
        return a.p;
    }

    protected String a(DJIUpgradePack dJIUpgradePack) {
        return null;
    }

    protected String c() {
        return ab.class.getSimpleName();
    }
}
