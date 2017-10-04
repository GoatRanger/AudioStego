package dji.internal.version.a;

import dji.internal.version.DJIModelUpgradePackList;
import dji.internal.version.DJIModelUpgradePackList.DJIUpgradePack;
import dji.internal.version.DJIVersionBaseComponent;
import java.util.ArrayList;

public class j extends DJIVersionBaseComponent {
    protected String[] a() {
        return new String[]{"2601", "2900", "2500", "2501", "0407", "1100", "1101", "1102", "1103", "1104", "1105", "1106", "1200", "1201", "1202", "1203", "1204", "1205", "0305", "0306"};
    }

    protected ArrayList<DJIUpgradePack> a(DJIModelUpgradePackList dJIModelUpgradePackList) {
        return dJIModelUpgradePackList.versionlistm600;
    }

    protected String a(DJIUpgradePack dJIUpgradePack) {
        if (dJIUpgradePack == null) {
            return null;
        }
        return dJIUpgradePack.version;
    }

    protected String b() {
        return "DJIVersionM600Component";
    }
}
