package dji.internal.version.a;

import dji.internal.version.DJIModelUpgradePackList;
import dji.internal.version.DJIModelUpgradePackList.DJIUpgradePack;
import dji.internal.version.DJIVersionBaseComponent;
import dji.midware.data.manager.P3.p;
import java.util.ArrayList;

public class s extends DJIVersionBaseComponent {

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

    protected ArrayList<DJIUpgradePack> a(DJIModelUpgradePackList dJIModelUpgradePackList) {
        return null;
    }

    public void onEventBackgroundThread(p pVar) {
        switch (AnonymousClass1.a[pVar.ordinal()]) {
            case 1:
                g();
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

    protected String b() {
        return s.class.getSimpleName();
    }
}
