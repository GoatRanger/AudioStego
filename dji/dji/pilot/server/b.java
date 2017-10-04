package dji.pilot.server;

import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import com.alibaba.sdk.android.ut.UTConstants;
import com.dji.frame.c.c;
import com.dji.frame.c.f;
import com.facebook.internal.ab;
import com.google.api.client.http.UrlEncodedParser;
import dji.a.a;
import dji.log.DJILogHelper;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.pilot.publics.objects.j;
import dji.pilot.server.model.DJIRegisterDeviceResultModel;
import dji.pilot.usercenter.protocol.d;
import java.io.File;
import java.io.IOException;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class b {
    protected static String a = "";
    private String b = (Environment.getExternalStorageDirectory() + "/DJI/" + DJIApplication.f + d.t);
    private String c = (this.b + ".RegisterDeviceKey");
    private String d = "9l2K4fdY3hZV50Wg";
    private String e = "&&";
    private String f = getClass().getSimpleName();
    private File g = null;
    private DJIRegisterDeviceResultModel h;
    private Location i;

    public b() {
        File file = new File(this.b);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.g = new File(this.c);
        if (!this.g.exists()) {
            try {
                this.g.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.i = a.getInstance().e();
    }

    private String b() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(System.currentTimeMillis()));
    }

    public void onEventMainThread(DJINetWorkReceiver.a aVar) {
        DJILogHelper.getInstance().LOGD(this.f, "DJINetWorkReceiver.DJINetWorkStatusEvent netEvent", "ServerLog");
        if (!d() && aVar == DJINetWorkReceiver.a.CONNECT_OK) {
            try {
                a();
            } catch (SignatureException e) {
                e.printStackTrace();
            }
        }
    }

    public void a() throws SignatureException {
        double d = 0.0d;
        if (d()) {
            a(a.a);
            return;
        }
        double longitude;
        String str = DJIApplication.e;
        String str2 = "djigo";
        String str3 = "android";
        String str4 = Build.SERIAL;
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        bVar.a(UTConstants.APP_VERSION, str);
        bVar.a(ab.y, str2);
        bVar.a("app_datetime", b());
        bVar.a("device_sn", str4);
        bVar.a("os_platform", str3);
        bVar.a("os_version", "" + VERSION.SDK_INT);
        if (this.i != null) {
            longitude = this.i.getLongitude();
            d = this.i.getLatitude();
        } else {
            longitude = 0.0d;
        }
        bVar.a("longitude", "" + longitude);
        bVar.a("latitude", "" + d);
        bVar.a("network", "");
        bVar.a("operator", "");
        bVar.a(com.alipay.sdk.f.d.j, "2");
        bVar.a("lang", Locale.getDefault().toString());
        bVar.a("sign", j.a(str + str2 + str4 + str3, this.d));
        c.a().a(a.b, null, bVar, UrlEncodedParser.CONTENT_TYPE, new 1(this));
    }

    private void a(a aVar) {
        dji.thirdparty.a.c.a().e(aVar);
    }

    private void c() {
        if (this.h.status == 0) {
            a = this.h.token;
            f.a(this.g, System.currentTimeMillis() + this.e + this.h.token);
            DJILogHelper.getInstance().LOGD(this.f, "server deviceToken getted", "ServerLog");
            a(a.a);
            return;
        }
        DJILogHelper.getInstance().LOGD(this.f, "resultModel.status=" + this.h.status, "ServerLog");
        a(a.b);
    }

    private boolean d() {
        String a = f.a(this.g);
        if (!a.contains(this.e)) {
            return false;
        }
        String[] split = a.split(this.e);
        if (split.length != 2) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long parseLong = Long.parseLong(split[0]);
        if ((currentTimeMillis / 86400000) - (parseLong / 86400000) >= 1) {
            DJILogHelper.getInstance().LOGD(this.f, "第2天过期 time=" + parseLong, "ServerLog");
            return false;
        }
        a = split[1];
        return true;
    }
}
