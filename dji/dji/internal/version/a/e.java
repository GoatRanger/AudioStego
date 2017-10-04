package dji.internal.version.a;

import dji.internal.version.DJIModelUpgradePackList;
import dji.internal.version.DJIModelUpgradePackList.DJIUpgradePack;
import dji.internal.version.DJIVersionBaseComponent;
import java.util.ArrayList;

public class e extends DJIVersionBaseComponent {
    protected String[] a() {
        return new String[]{"0400", "0100", "0807"};
    }

    protected ArrayList<DJIUpgradePack> a(DJIModelUpgradePackList dJIModelUpgradePackList) {
        return dJIModelUpgradePackList.versionlistxt;
    }

    protected String a(DJIUpgradePack dJIUpgradePack) {
        if (dJIUpgradePack == null) {
            return null;
        }
        return dJIUpgradePack.version;
    }

    protected String b() {
        return "DJIVersionInspire1XTComponent";
    }
}
