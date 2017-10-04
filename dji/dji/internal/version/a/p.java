package dji.internal.version.a;

import dji.internal.version.DJIModelUpgradePackList;
import dji.internal.version.DJIModelUpgradePackList.DJIUpgradePack;
import dji.internal.version.DJIVersionBaseComponent;
import java.util.ArrayList;

public class p extends DJIVersionBaseComponent {
    protected String[] a() {
        return new String[]{"0305", "0306", "0400", "0900", "1100", "1200", "1201", "1202", "1203", "1500", "1700", "1701", "1900", "0100", "0101"};
    }

    protected ArrayList<DJIUpgradePack> a(DJIModelUpgradePackList dJIModelUpgradePackList) {
        return dJIModelUpgradePackList.versionlistx;
    }

    protected String a(DJIUpgradePack dJIUpgradePack) {
        if (dJIUpgradePack == null) {
            return null;
        }
        return dJIUpgradePack.version;
    }

    protected String b() {
        return "DJIVersionP3xComponent";
    }
}
