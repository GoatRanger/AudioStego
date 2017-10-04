package dji.pilot.publics.model;

import java.util.ArrayList;
import java.util.Iterator;

public class DJIProductListModel {
    public ArrayList<DJIProductModel> products;
    public String vertion;

    public static class DJIProductModel {
        public String activeName;
        public String activePlaneName;
        public String activeTip;
        public String activeTitle;
        public String collegeName;
        public String college_appid;
        public String compass_h;
        public String compass_h_desc;
        public String compass_v;
        public String compass_v_desc;
        public String icon_1;
        public String icon_2;
        public String icon_3;
        public String moment_endding_sub_title;
        public String moment_endding_title;
        public String name;
        public int pageLoc;
        public String pic_connect;
        public String pic_disconnect;
        public String series;
        public String shareName;
        public int showPage;
        public String sub_title_connect;
        public String title_connect;
        public int value;
        public DJIProductVerModel verModel;
        public String verlist;
        public String waterTitle;
    }

    public int getPageid(int i) {
        Iterator it = this.products.iterator();
        while (it.hasNext()) {
            DJIProductModel dJIProductModel = (DJIProductModel) it.next();
            if (dJIProductModel.value == i) {
                return dJIProductModel.showPage;
            }
        }
        return 0;
    }

    public int getPageLoc(int i) {
        Iterator it = this.products.iterator();
        while (it.hasNext()) {
            DJIProductModel dJIProductModel = (DJIProductModel) it.next();
            if (dJIProductModel.value == i) {
                return dJIProductModel.pageLoc;
            }
        }
        return 0;
    }
}
