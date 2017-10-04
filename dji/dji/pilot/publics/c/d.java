package dji.pilot.publics.c;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.dji.frame.c.f;
import com.dji.frame.c.h;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.publics.model.DJIProductListModel;
import dji.pilot.publics.model.DJIProductListModel.DJIProductModel;
import dji.pilot.publics.model.DJIProductVerModel;
import java.util.ArrayList;
import java.util.Iterator;

public class d {
    private static final String a = "DJIProductConfigManager";
    private static d b;
    private DJIProductListModel c;
    private Context d;

    public static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            if (b == null) {
                b = new d(context.getApplicationContext());
            }
            dVar = b;
        }
        return dVar;
    }

    public static d getInstance() {
        return b;
    }

    public d(Context context) {
        this.d = context;
        this.c = (DJIProductListModel) h.b(f.a(context, (int) R.raw.product_config), DJIProductListModel.class);
        Iterator it = this.c.products.iterator();
        while (it.hasNext()) {
            DJIProductModel dJIProductModel = (DJIProductModel) it.next();
            int identifier = context.getResources().getIdentifier(dJIProductModel.verlist, "raw", context.getPackageName());
            if (identifier > 0) {
                dJIProductModel.verModel = (DJIProductVerModel) h.b(f.a(context, identifier), DJIProductVerModel.class);
            }
        }
    }

    public DJIProductListModel a() {
        return this.c;
    }

    public ProductType a(String str) {
        Iterator it = this.c.products.iterator();
        while (it.hasNext()) {
            DJIProductModel dJIProductModel = (DJIProductModel) it.next();
            if (dJIProductModel.activeName.equals(str)) {
                return ProductType.find(dJIProductModel.value);
            }
        }
        return ProductType.None;
    }

    public DJIProductModel a(ProductType productType) {
        ArrayList arrayList = this.c.products;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            DJIProductModel dJIProductModel = (DJIProductModel) it.next();
            if (dJIProductModel.value == productType.value()) {
                return dJIProductModel;
            }
        }
        return (DJIProductModel) arrayList.get(0);
    }

    public Drawable a(int i) {
        String str = "";
        Iterator it = this.c.products.iterator();
        while (it.hasNext()) {
            String str2;
            DJIProductModel dJIProductModel = (DJIProductModel) it.next();
            if (i == dJIProductModel.value) {
                str2 = dJIProductModel.pic_disconnect;
            } else {
                str2 = str;
            }
            str = str2;
        }
        return this.d.getResources().getDrawable(this.d.getResources().getIdentifier(str, "drawable", this.d.getPackageName()));
    }

    public Drawable b(int i) {
        String str = "";
        Iterator it = this.c.products.iterator();
        while (it.hasNext()) {
            String str2;
            DJIProductModel dJIProductModel = (DJIProductModel) it.next();
            if (i == dJIProductModel.value) {
                str2 = dJIProductModel.pic_connect;
            } else {
                str2 = str;
            }
            str = str2;
        }
        return this.d.getResources().getDrawable(this.d.getResources().getIdentifier(str, "drawable", this.d.getPackageName()));
    }

    public int c(int i) {
        String str = "";
        Iterator it = this.c.products.iterator();
        while (it.hasNext()) {
            String str2;
            DJIProductModel dJIProductModel = (DJIProductModel) it.next();
            if (i == dJIProductModel.value) {
                str2 = dJIProductModel.title_connect;
            } else {
                str2 = str;
            }
            str = str2;
        }
        return this.d.getResources().getIdentifier(str, "string", this.d.getPackageName());
    }

    public int d(int i) {
        String str = "";
        Iterator it = this.c.products.iterator();
        while (it.hasNext()) {
            String str2;
            DJIProductModel dJIProductModel = (DJIProductModel) it.next();
            if (i == dJIProductModel.value) {
                str2 = dJIProductModel.sub_title_connect;
            } else {
                str2 = str;
            }
            str = str2;
        }
        return this.d.getResources().getIdentifier(str, "string", this.d.getPackageName());
    }

    public String b(ProductType productType) {
        return a(productType).shareName;
    }

    public int c(ProductType productType) {
        return a(productType).showPage;
    }

    public int d(ProductType productType) {
        return a(productType).pageLoc;
    }

    public int e(ProductType productType) {
        return this.d.getResources().getIdentifier(a(productType).activeTitle, "string", this.d.getPackageName());
    }

    public int f(ProductType productType) {
        return this.d.getResources().getIdentifier(a(productType).activeTip, "drawable", this.d.getPackageName());
    }

    public Drawable g(ProductType productType) {
        return this.d.getResources().getDrawable(this.d.getResources().getIdentifier(a(productType).icon_1, "drawable", this.d.getPackageName()));
    }

    public int h(ProductType productType) {
        return this.d.getResources().getIdentifier(a(productType).icon_2, "drawable", this.d.getPackageName());
    }

    public int i(ProductType productType) {
        return this.d.getResources().getIdentifier(a(productType).icon_3, "drawable", this.d.getPackageName());
    }

    public String j(ProductType productType) {
        return a(productType).collegeName;
    }

    public String k(ProductType productType) {
        return a(productType).college_appid;
    }

    public int l(ProductType productType) {
        return this.d.getResources().getIdentifier(a(productType).compass_h, "drawable", this.d.getPackageName());
    }

    public int m(ProductType productType) {
        return this.d.getResources().getIdentifier(a(productType).compass_h_desc, "string", this.d.getPackageName());
    }

    public int n(ProductType productType) {
        return this.d.getResources().getIdentifier(a(productType).compass_v, "drawable", this.d.getPackageName());
    }

    public int o(ProductType productType) {
        return this.d.getResources().getIdentifier(a(productType).compass_v_desc, "string", this.d.getPackageName());
    }

    public String p(ProductType productType) {
        return a(productType).moment_endding_title;
    }

    public String q(ProductType productType) {
        return a(productType).moment_endding_sub_title;
    }
}
