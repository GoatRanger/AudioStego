package dji.pilot.upgrade.a;

import dji.dbox.upgrade.p4.a.a;
import dji.midware.data.manager.P3.ServiceManager;
import dji.pilot.publics.model.DJIUpgradePackListModel;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.pilot.upgrade.UpgradeBaseComponent;
import java.util.ArrayList;

public class t extends UpgradeBaseComponent {
    protected String[] a() {
        return new String[0];
    }

    protected boolean b() {
        return ServiceManager.getInstance().isOK();
    }

    protected ArrayList<DJIUpgradePack> a(DJIUpgradePackListModel dJIUpgradePackListModel) {
        return dJIUpgradePackListModel.versionlistx;
    }

    public String e() {
        return a.q;
    }

    protected String a(DJIUpgradePack dJIUpgradePack) {
        return null;
    }

    protected String c() {
        return t.class.getSimpleName();
    }
}
