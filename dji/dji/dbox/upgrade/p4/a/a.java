package dji.dbox.upgrade.p4.a;

import android.os.Process;
import com.dji.frame.c.d;
import dji.dbox.upgrade.p4.model.DJIUpCfgModel.DJIFirmwareGroup;
import dji.dbox.upgrade.p4.model.DJIUpListElement;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.ServiceManager;
import java.util.ArrayList;
import java.util.List;

public class a {
    public static final boolean a = false;
    public static final boolean b = false;
    public static final boolean c = false;
    public static final int d = 50;
    public static final String e = (d.b + "/DJI/1860log/");
    public static final String f = (a() + "firmware/upgrade_log");
    public static final String g = (a() + "firmware/release_note");
    public static final String h = "UP_";
    public static final String i = "/upCfgFiles/";
    public static String j = "";
    public static DeviceType k = DeviceType.DM368;
    public static String l = "";
    public static String m = "";
    public static boolean n = false;
    public static String o = "";
    public static String p = "";
    public static String q = "";
    public static boolean r = false;
    public static boolean s = false;
    public static List<DJIUpListElement> t;
    public static DJIUpListElement u;
    public static DJIUpListElement v;
    public static String w = "";
    public static ArrayList<DJIFirmwareGroup> x = new ArrayList();

    public static String a() {
        return "https://mydjiflight.dji.com/api/v2/";
    }

    public static void a(String str) {
        String str2 = "DeviceLogByKeyGuan";
    }

    public static String b() {
        String b = DJIUpgradeP4Service.b();
        StringBuilder stringBuilder = new StringBuilder(50);
        stringBuilder.append(DJILogHelper.getInstance().getLogParentDir()).append(h).append(b).append(dji.pilot.usercenter.protocol.d.t).append(DJILogHelper.getInstance().getLogName());
        return stringBuilder.toString();
    }

    public static void a(String str, String str2) {
        DJILogHelper.getInstance().LOGE(str, str2 + " pid(" + Process.myPid() + "),tid(" + Process.myTid() + ")", h + DJIUpgradeP4Service.b());
    }

    public static void b(String str, String str2) {
        DJILogHelper.getInstance().LOGD(str, str2, h + DJIUpgradeP4Service.b());
    }

    public static void c() {
        o = "";
        p = "";
        q = "";
        s = false;
        t = null;
        u = null;
        r = false;
    }

    public static void d() {
        if (DJIUpgradeP4Service.d()) {
            if (ServiceManager.getInstance().isRemoteOK()) {
                x.add(DJIFirmwareGroup.RC);
                x.add(DJIFirmwareGroup.AC);
                x.add(DJIFirmwareGroup.GL);
                w = "ALL";
            } else {
                x.add(DJIFirmwareGroup.RC);
                w = "RC";
            }
        } else if (DJIUpgradeP4Service.e()) {
            x.add(DJIFirmwareGroup.AC);
            w = "";
        }
        b("", "initFirmwareGroup groupKey=" + w);
    }

    public static void c(String str, String str2) {
        l = str;
        m = str2;
    }

    public static boolean e() {
        return !l.equals("");
    }
}
