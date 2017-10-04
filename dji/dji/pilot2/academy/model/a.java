package dji.pilot2.academy.model;

import android.content.Context;
import com.dji.frame.c.f;
import com.dji.frame.c.h;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.publics.objects.g;
import dji.pilot2.academy.model.AcademyProductTypeModel.ProductTypeStruct;
import dji.pilot2.main.a.b;
import java.util.ArrayList;
import java.util.Iterator;

public class a {
    private static a b = null;
    private static AcademyProductTypeModel c = null;
    private Context a;

    private a(Context context) {
        this.a = context.getApplicationContext();
        c = (AcademyProductTypeModel) h.b(f.a(context, R.raw.academy_product_config), AcademyProductTypeModel.class);
        c.generateProductCode();
        b();
    }

    public static a getInstance(Context context) {
        if (b == null) {
            b = new a(context);
        }
        return b;
    }

    public ArrayList<ProductTypeStruct> a() {
        return c.academy_products;
    }

    private void b() {
        Iterator it = c.academy_products.iterator();
        while (it.hasNext()) {
            ProductTypeStruct productTypeStruct = (ProductTypeStruct) it.next();
            String b = g.b(this.a, productTypeStruct.learn_more_short + b.a, "");
            if (b.contains("http")) {
                productTypeStruct.learn_more_link = b;
            }
            b = g.b(this.a, productTypeStruct.learn_more_short + b.b, "");
            if (b.contains("http")) {
                productTypeStruct.learn_more_link_never_connected = b;
            }
        }
    }

    public String a(ProductType productType) {
        Iterator it = c.academy_products.iterator();
        while (it.hasNext()) {
            ProductTypeStruct productTypeStruct = (ProductTypeStruct) it.next();
            if (productTypeStruct.mProductCode.equals(productType)) {
                return productTypeStruct.learn_more_link;
            }
        }
        return "";
    }

    public String b(ProductType productType) {
        Iterator it = c.academy_products.iterator();
        while (it.hasNext()) {
            ProductTypeStruct productTypeStruct = (ProductTypeStruct) it.next();
            if (productTypeStruct.mProductCode.equals(productType)) {
                return productTypeStruct.learn_more_link_never_connected;
            }
        }
        return "";
    }
}
