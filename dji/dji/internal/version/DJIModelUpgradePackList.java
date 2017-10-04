package dji.internal.version;

import com.alipay.sdk.h.a;
import dji.midware.data.config.P3.ProductType;
import java.util.ArrayList;

public class DJIModelUpgradePackList {
    public DJIUpgradeAnnouncement announcement;
    public DJIUpgradeAnnouncement announcementAndroid;
    public DJIUpgradeAppVersion application;
    public ArrayList<DJIUpgradePack> versionlist;
    public ArrayList<DJIUpgradePack> versionlistc;
    public ArrayList<DJIUpgradePack> versionlisthg;
    public ArrayList<DJIUpgradePack> versionlisthgX5;
    public ArrayList<DJIUpgradePack> versionlisthgX5R;
    public ArrayList<DJIUpgradePack> versionlistlb2;
    public ArrayList<DJIUpgradePack> versionlistm100;
    public ArrayList<DJIUpgradePack> versionlistm600;
    public ArrayList<DJIUpgradePack> versionlists;
    public ArrayList<DJIUpgradePack> versionlistx;
    public ArrayList<DJIUpgradePack> versionlistx5;
    public ArrayList<DJIUpgradePack> versionlistx5r;
    public ArrayList<DJIUpgradePack> versionlistxt;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$dji$midware$data$config$P3$ProductType = new int[ProductType.values().length];

        static {
            try {
                $SwitchMap$dji$midware$data$config$P3$ProductType[ProductType.Orange.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$ProductType[ProductType.BigBanana.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$ProductType[ProductType.litchiX.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$ProductType[ProductType.litchiS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$ProductType[ProductType.litchiC.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$ProductType[ProductType.N1.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$ProductType[ProductType.Longan.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$dji$midware$data$config$P3$ProductType[ProductType.Grape2.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public static class DJIUpgradeAnnouncement {
        public String en;
        public String guid;
        public String jp;
        public String zh;
    }

    public static class DJIUpgradeAppVersion {
        public String android;
        public int significant1;
    }

    public static class DJIUpgradeDevice {
        public int isLock;
        public String name;
        public String version;
    }

    public static class DJIUpgradePack {
        public int android_ignore;
        public long date;
        public String m0100;
        public String m0101;
        public String m0104;
        public String m0106;
        public String m0305;
        public String m0306;
        public String m0400;
        public String m0407;
        public String m0500;
        public String m0700;
        public String m0800;
        public String m0801;
        public String m0807;
        public String m0900;
        public String m1100;
        public String m1101;
        public String m1102;
        public String m1103;
        public String m1104;
        public String m1105;
        public String m1106;
        public String m1200;
        public String m1201;
        public String m1202;
        public String m1203;
        public String m1204;
        public String m1205;
        public String m1300;
        public String m1301;
        public String m1400;
        public String m1401;
        public String m1405;
        public String m1500;
        public String m1501;
        public String m1600;
        public String m1601;
        public String m1700;
        public String m1701;
        public String m1900;
        public String m2000;
        public String m2001;
        public String m2002;
        public String m2500;
        public String m2501;
        public String m2700;
        public String m2900;
        public String packurl;
        public int priority;
        public String rc1url;
        public String rcurl;
        public String rcversion;
        public ReleaseNote release_note;
        public String version;

        public static String getVersion(String str) {
            return str.split(a.b)[0];
        }

        public static int getFlag(String str) {
            return Integer.parseInt(str.split(a.b)[1]);
        }
    }

    public static class ReleaseNote {
        public String cn;
        public String en;
    }

    public ArrayList<DJIUpgradePack> getVersionList(ProductType productType) {
        switch (AnonymousClass1.$SwitchMap$dji$midware$data$config$P3$ProductType[productType.ordinal()]) {
            case 1:
            case 2:
                return this.versionlist;
            case 3:
                return this.versionlistx;
            case 4:
                return this.versionlists;
            case 5:
                return this.versionlistc;
            case 6:
                return this.versionlistm100;
            case 7:
                return this.versionlisthg;
            case 8:
                return this.versionlistlb2;
            default:
                return null;
        }
    }
}
