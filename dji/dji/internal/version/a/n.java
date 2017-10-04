package dji.internal.version.a;

import dji.internal.version.DJIModelUpgradePackList;
import dji.internal.version.DJIModelUpgradePackList.DJIUpgradePack;
import dji.internal.version.DJIVersionBaseComponent;
import java.util.ArrayList;

public class n extends DJIVersionBaseComponent {
    private static final String a = "UpgradeP3cComponent";

    protected String[] a() {
        return new String[]{"0400", "1100", "0100", "0101", "0305", "0306", "0700", "0900", "1200", "1201", "1202", "1203", "1400", "2700"};
    }

    protected ArrayList<DJIUpgradePack> a(DJIModelUpgradePackList dJIModelUpgradePackList) {
        if (dJIModelUpgradePackList == null) {
            return null;
        }
        return dJIModelUpgradePackList.versionlistc;
    }

    protected String a(DJIUpgradePack dJIUpgradePack) {
        if (dJIUpgradePack == null) {
            return null;
        }
        return dJIUpgradePack.version;
    }

    protected String b() {
        return "DJIVersionP3cComponent";
    }
}
