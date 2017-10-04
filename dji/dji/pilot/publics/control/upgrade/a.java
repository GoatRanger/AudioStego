package dji.pilot.publics.control.upgrade;

import com.dji.frame.c.f;
import com.dji.frame.c.h;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.publics.control.upgrade.DJIProductUpgradeListModel.DJIUpgradeModel;
import dji.pilot.publics.objects.DJIApplication;
import java.util.ArrayList;
import java.util.Iterator;

public class a {
    private DJIProductUpgradeListModel a = ((DJIProductUpgradeListModel) h.b(f.a(DJIApplication.a(), R.raw.upgrade_config), DJIProductUpgradeListModel.class));

    private static final class a {
        private static final a a = new a();

        private a() {
        }
    }

    public static a getInstance() {
        return a.a;
    }

    public DJIUpgradeModel a(ProductType productType) {
        ArrayList arrayList = this.a.products;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            DJIUpgradeModel dJIUpgradeModel = (DJIUpgradeModel) it.next();
            if (dJIUpgradeModel.productId == productType.value()) {
                return dJIUpgradeModel;
            }
        }
        return (DJIUpgradeModel) arrayList.get(0);
    }
}
