package dji.pilot2.academy.model;

import dji.midware.data.config.P3.ProductType;
import java.util.ArrayList;
import java.util.Iterator;

public class AcademyProductTypeModel {
    public ArrayList<ProductTypeStruct> academy_products;
    public String version;

    public static class ProductTypeStruct {
        public String learn_more_link;
        public String learn_more_link_never_connected;
        public String learn_more_short;
        public ProductType mProductCode;
        public String mSeries;
        public String mSubVersion;
        public int mVersion;
        public int value;

        public String getLearnMoreUrl() {
            return this.learn_more_link;
        }

        public String getLearnMoreNeverUrl() {
            return this.learn_more_link_never_connected;
        }
    }

    public void generateProductCode() {
        Iterator it = this.academy_products.iterator();
        while (it.hasNext()) {
            ProductTypeStruct productTypeStruct = (ProductTypeStruct) it.next();
            productTypeStruct.mProductCode = ProductType.find(productTypeStruct.value);
        }
    }

    public int indexOfByProductType(ProductType productType) {
        for (int i = 0; i != this.academy_products.size(); i++) {
            if (((ProductTypeStruct) this.academy_products.get(i)).mProductCode == productType) {
                return i;
            }
        }
        return -1;
    }
}
