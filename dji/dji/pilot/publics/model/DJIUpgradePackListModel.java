package dji.pilot.publics.model;

import com.alipay.sdk.h.a;
import dji.midware.data.config.P3.ProductType;
import java.util.ArrayList;

public class DJIUpgradePackListModel {
    public DJIUpgradeAnnouncement announcement;
    public DJIUpgradeAnnouncement announcementAndroid;
    public DJIUpgradeAppVersion application;
    public ArrayList<DJIUpgradePack> versionlist;
    public ArrayList<DJIUpgradePack> versionlistHG300;
    public ArrayList<DJIUpgradePack> versionlista3;
    public ArrayList<DJIUpgradePack> versionlistc;
    public ArrayList<DJIUpgradePack> versionlistcv600;
    public ArrayList<DJIUpgradePack> versionlisthg;
    public ArrayList<DJIUpgradePack> versionlisthgX5;
    public ArrayList<DJIUpgradePack> versionlisthgX5R;
    public ArrayList<DJIUpgradePack> versionlistlb2;
    public ArrayList<DJIUpgradePack> versionlistm100;
    public ArrayList<DJIUpgradePack> versionlistm600;
    public ArrayList<DJIUpgradePack> versionlistm600pro;
    public ArrayList<DJIUpgradePack> versionlistn3;
    public ArrayList<DJIUpgradePack> versionlists;
    public ArrayList<DJIUpgradePack> versionlistx;
    public ArrayList<DJIUpgradePack> versionlistx5;
    public ArrayList<DJIUpgradePack> versionlistx5r;
    public ArrayList<DJIUpgradePack> versionlistxt;
    public ArrayList<DJIUpgradePack> versionlistxw;
    public ArrayList<DJIUpgradePack> versionlistz3;
    public ArrayList<DJIUpgradePack> versionlistz30;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ProductType.values().length];

        static {
            try {
                a[ProductType.Orange.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ProductType.BigBanana.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ProductType.Olives.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ProductType.OrangeRAW.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[ProductType.OrangeCV600.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[ProductType.litchiX.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[ProductType.litchiS.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[ProductType.litchiC.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[ProductType.N1.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[ProductType.PM820.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[ProductType.PM820PRO.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[ProductType.Longan.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[ProductType.LonganPro.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[ProductType.LonganRaw.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[ProductType.LonganZoom.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[ProductType.LonganMobile.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[ProductType.Grape2.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[ProductType.A2.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                a[ProductType.P34K.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                a[ProductType.Tomato.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                a[ProductType.Pomato.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
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
        public String m0401;
        public String m0402;
        public String m0403;
        public String m0407;
        public String m0500;
        public String m0605;
        public String m0700;
        public String m0800;
        public String m0801;
        public String m0807;
        public String m0900;
        public String m0901;
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
        public String m2003;
        public String m2500;
        public String m2501;
        public String m2601;
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
        switch (AnonymousClass1.a[productType.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return this.versionlist;
            case 6:
                return this.versionlistx;
            case 7:
                return this.versionlists;
            case 8:
                return this.versionlistc;
            case 9:
                return this.versionlistm100;
            case 10:
                return this.versionlistm600;
            case 11:
                return this.versionlistm600pro;
            case 12:
                return this.versionlisthg;
            case 13:
                return this.versionlisthgX5;
            case 14:
                return this.versionlisthgX5R;
            case 15:
                return this.versionlistcv600;
            case 16:
                return this.versionlistHG300;
            case 17:
            case 18:
                return this.versionlistlb2;
            case 19:
                return this.versionlistxw;
            case 20:
            case 21:
                return this.versionlistx;
            default:
                return null;
        }
    }
}
