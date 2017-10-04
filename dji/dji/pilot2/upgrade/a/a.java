package dji.pilot2.upgrade.a;

import android.text.TextUtils;
import dji.pilot.usercenter.b.f;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.c.d;

public class a implements d {
    private static final String b = "UpgradeLogManager";
    private static a i = null;
    @Deprecated
    a a;
    private boolean c = false;
    private boolean d = false;
    private DJISDKCache e = DJISDKCache.getInstance();
    private c f = null;
    private String g = "";
    private String h = "";

    @Deprecated
    public interface a {
        void a(String str);

        void b(String str);
    }

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (i == null) {
                i = new a();
            }
            aVar = i;
        }
        return aVar;
    }

    private a() {
        dji.thirdparty.a.c.a().a((Object) this);
        this.f = new dji.sdksharedlib.b.c.a().b(e.a).a(Integer.valueOf(0)).d("InternalSerialNumber").a();
        this.h = dji.pilot.server.a.a();
        dji.sdksharedlib.d.a availableValue = this.e.getAvailableValue(this.f);
        if (availableValue != null && availableValue.e() != null) {
            this.g = (String) availableValue.e();
        }
    }

    public void a(a aVar) {
        this.c = false;
        this.d = false;
        this.a = aVar;
        new Thread(new 1(this)).start();
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (cVar.equals(this.f) && aVar2 != null && aVar2.e() != null) {
            String str = (String) aVar2.e();
            dji.dbox.upgrade.p4.a.a.a("获取飞机FlycSn=" + str);
            if (!TextUtils.isEmpty(str)) {
                this.g = str;
                a();
            }
        }
    }

    public void onEventBackgroundThread(dji.pilot.server.b.a aVar) {
        if (aVar == dji.pilot.server.b.a.a) {
            dji.dbox.upgrade.p4.a.a.a("token get" + this.h);
            this.h = dji.pilot.server.a.a();
            a();
        }
    }

    public void onEventBackgroundThread(dji.pilot.publics.objects.DJINetWorkReceiver.a aVar) {
        if (aVar == dji.pilot.publics.objects.DJINetWorkReceiver.a.CONNECT_OK) {
            dji.dbox.upgrade.p4.a.a.a("startLogUploadWithParamCheck（）when DJINetWorkStatusEvent = " + aVar.toString());
            a();
        }
    }

    private void a() {
        dji.dbox.upgrade.p4.a.a.a("UpgradeLogManager:Param mIsDownComplete =" + this.c + ",mIsUpComplete=" + this.d + ",mDeviceToken=" + this.h + ",mFlycSn=" + this.g);
        if (this.c && !this.d && !TextUtils.isEmpty(this.h) && !TextUtils.isEmpty(this.g)) {
            b();
        }
    }

    private void b() {
        dji.dbox.upgrade.p4.d.a.getInstance().a(this.g, this.h, f.getInstance().o(), new 2(this));
    }
}
