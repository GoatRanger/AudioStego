package dji.dbox.upgrade.p4.model;

import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import java.util.ArrayList;

public class DJIUpCfgModel {
    public String a;
    public String b;
    public int c;
    public int d;
    public String e;
    public String f;
    public int g;
    public ArrayList<a> h;

    public enum DJIFirmwareGroup {
        AC,
        RC,
        GL
    }

    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public int e;
        public int f;
        public boolean g = false;
        public int h;
        public DJIFirmwareGroup i;

        public void a(String str) {
            this.a = str;
            this.e = Integer.parseInt(str.substring(0, 2));
            this.f = Integer.parseInt(str.substring(2));
        }

        public DJIFirmwareGroup b(String str) {
            if (str != null) {
                for (DJIFirmwareGroup dJIFirmwareGroup : DJIFirmwareGroup.values()) {
                    if (dJIFirmwareGroup.toString().equalsIgnoreCase(str)) {
                        return dJIFirmwareGroup;
                    }
                }
            }
            return DJIFirmwareGroup.AC;
        }
    }

    public static DJIUpCfgModel a() {
        dji.dbox.upgrade.p4.a.a.b("", "getCfgCallBack makeNullDeviceCfg");
        DJIUpCfgModel dJIUpCfgModel = new DJIUpCfgModel();
        dJIUpCfgModel.d = 0;
        dJIUpCfgModel.c = 0;
        dJIUpCfgModel.a = DJIUpgradeP4Service.a();
        dJIUpCfgModel.f = "00-00-00";
        dJIUpCfgModel.e = "00-00-00";
        dJIUpCfgModel.b = "00.00.0000";
        dJIUpCfgModel.h = new ArrayList();
        return dJIUpCfgModel;
    }

    public boolean b() {
        return this.b == null || this.b.equals("00.00.0000");
    }
}
