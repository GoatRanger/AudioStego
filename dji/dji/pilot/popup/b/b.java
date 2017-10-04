package dji.pilot.popup.b;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.alibaba.sdk.android.ut.UTConstants;
import com.alipay.sdk.h.a;
import com.dji.frame.c.h;
import dji.midware.data.manager.P3.ServiceManager;
import dji.pilot.R;
import dji.pilot.countrycode.a.c;
import dji.pilot.popup.model.PopupModel;
import dji.pilot.popup.model.PopupModel.Result;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.g;
import dji.pilot.publics.objects.j;
import dji.pilot.upgrade.e;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import java.security.SignatureException;

public class b {
    private static final String a = "DJIPopupProtocolManager";
    private static b d = null;
    private String b = "";
    private boolean c = false;

    private b() {
    }

    public static b getInstance() {
        if (d == null) {
            synchronized (b.class) {
                if (d == null) {
                    d = new b();
                }
            }
        }
        return d;
    }

    public void a(String str) {
        this.b = str;
    }

    private String b() {
        String str = "en";
        String country = DJIApplication.a().getResources().getConfiguration().locale.getCountry();
        if (TextUtils.isEmpty(country)) {
            return str;
        }
        if (country.equalsIgnoreCase("CN")) {
            return "zh_cn";
        }
        if (country.equalsIgnoreCase("TW")) {
            return "zh_tw";
        }
        if (country.equalsIgnoreCase("JP")) {
            return "ja";
        }
        return str;
    }

    public void a(final Context context, boolean z) {
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        StringBuilder stringBuilder = new StringBuilder();
        String string = DJIApplication.a().getResources().getString(R.string.versionname);
        bVar.a(UTConstants.APP_VERSION, string);
        stringBuilder.append("app_version=");
        stringBuilder.append(string);
        stringBuilder.append(a.b);
        bVar.a("os_platform", "android");
        StringBuilder stringBuilder2 = new StringBuilder();
        if (z) {
            stringBuilder.append("type=0");
            stringBuilder.append(a.b);
            bVar.a("notify_type", "0");
        } else {
            bVar.a("notify_type", "1");
            stringBuilder.append("type=1");
            stringBuilder.append(a.b);
            string = e.getInstance().b();
            if (!(string == null || string.equals("00.00.00.00") || string.equals("N/A") || string.isEmpty())) {
                bVar.a("firmware_version", string);
                stringBuilder.append("firmware_version=");
                stringBuilder.append(string);
                stringBuilder.append(a.b);
            }
            if (ServiceManager.getInstance().isRemoteOK()) {
                string = dji.pilot.fpv.d.b.u(null);
                bVar.a("product_type", string);
                stringBuilder.append("product_type=");
                stringBuilder.append(string);
                stringBuilder.append(a.b);
            }
            if (!(this.b == null || this.b.isEmpty())) {
                stringBuilder2.append(this.b);
                bVar.a(n.bc, this.b);
                stringBuilder.append("sn geted");
                stringBuilder.append(a.b);
            }
        }
        string = f.getInstance().o();
        if (!(string == null || string.isEmpty())) {
            bVar.a("uid", string);
            stringBuilder.append("uid geted");
            stringBuilder.append(a.b);
        }
        Location e = dji.a.a.getInstance().e();
        if (e != null) {
            bVar.a(n.x, e.getLatitude() + "");
            bVar.a(n.y, e.getLongitude() + "");
            stringBuilder.append("loc=");
            stringBuilder.append(e.getLatitude());
            stringBuilder.append(" lng=");
            stringBuilder.append(e.getLongitude());
            stringBuilder.append(a.b);
        }
        string = c.getInstance().a();
        bVar.a("nation_code", string);
        stringBuilder.append("nation_code=");
        stringBuilder.append(string);
        stringBuilder.append(a.b);
        bVar.a("lang", b());
        string = (System.currentTimeMillis() / 1000) + "";
        bVar.a(n.ax, string);
        stringBuilder2.append(string);
        stringBuilder2.append(dji.pilot.popup.a.a.b);
        string = "";
        try {
            string = j.a(stringBuilder2.toString(), dji.pilot.popup.a.a.b);
        } catch (SignatureException e2) {
            e2.printStackTrace();
        }
        bVar.a(d.L, string);
        dji.pilot.popup.c.a.b(stringBuilder.toString());
        com.dji.frame.c.c.a().b(dji.pilot.popup.a.a.a, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ b b;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                dji.pilot.popup.c.a.b(str);
                PopupModel popupModel = (PopupModel) h.b(str, PopupModel.class);
                if (b.b(popupModel)) {
                    for (Result result : popupModel.result) {
                        if (g.b(context, dji.pilot.popup.activity.a.a + result.id, true)) {
                            dji.pilot.popup.activity.a.a(context, result);
                        }
                    }
                }
            }

            public void a(Throwable th, int i, String str) {
                dji.pilot.popup.c.a.a(b.a, th, i, str);
            }
        });
    }

    private static boolean b(PopupModel popupModel) {
        if (popupModel == null) {
            dji.pilot.popup.c.a.b("null model");
            return false;
        } else if (popupModel.status != 0) {
            dji.pilot.popup.c.a.b("model.status error");
            return false;
        } else if (popupModel.result != null && popupModel.result.length > 0) {
            return true;
        } else {
            dji.pilot.popup.c.a.b("model.result is empty");
            return false;
        }
    }

    void a(boolean z) {
        this.c = z;
    }

    public boolean a() {
        return this.c;
    }
}
