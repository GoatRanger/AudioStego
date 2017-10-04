package dji.pilot.publics.c;

import android.content.Context;
import com.dji.frame.c.h;
import com.google.api.client.http.UrlEncodedParser;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.pilot.publics.model.DJIDeviceInfoStatModel;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.pilot.publics.objects.DJINetWorkReceiver.a;
import dji.thirdparty.afinal.c;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.http.entity.StringEntity;

public class b {
    private static b a;
    private c b;
    private dji.thirdparty.afinal.b c;
    private DataCommonGetVersion d;
    private int e = 0;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[a.values().length];

        static {
            try {
                a[a.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    public static synchronized b getInstance(Context context) {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b(context.getApplicationContext());
            }
            bVar = a;
        }
        return bVar;
    }

    public b(Context context) {
        this.b = com.dji.frame.c.c.b(context);
        this.c = com.dji.frame.c.c.c(context);
        if (DJINetWorkReceiver.a(context)) {
            g();
        }
        this.d = new DataCommonGetVersion();
        dji.thirdparty.a.c.a().a(this);
    }

    public void onEventBackgroundThread(a aVar) {
        switch (AnonymousClass2.a[aVar.ordinal()]) {
            case 1:
                g();
                return;
            default:
                return;
        }
    }

    public void a() {
        c();
        d();
        e();
        f();
    }

    private boolean a(DJIDeviceInfoStatModel dJIDeviceInfoStatModel) {
        return this.c.c(DJIDeviceInfoStatModel.class, new StringBuilder().append("devicetype='").append(dJIDeviceInfoStatModel.devicetype).append("' AND devicesn='").append(dJIDeviceInfoStatModel.devicesn).append("' AND deviceversion='").append(dJIDeviceInfoStatModel.deviceversion).append("'").toString()).size() != 0;
    }

    private void c() {
        a(1, DJIGlobalService.f, this.d.setDeviceType(DeviceType.FLYC).getFirmVer("."));
    }

    private void d() {
        a(0, DJIGlobalService.g, this.d.setDeviceType(DeviceType.CAMERA).getFirmVer("."));
    }

    private void e() {
        a(2, DJIGlobalService.i, this.d.setDeviceType(DeviceType.BATTERY).getFirmVer("."));
    }

    private void f() {
        a(3, DJIGlobalService.h, this.d.setDeviceType(DeviceType.OSD).getFirmVer("."));
    }

    private void a(int i, String str, String str2) {
        if (!str.equals("")) {
            DJIDeviceInfoStatModel dJIDeviceInfoStatModel = new DJIDeviceInfoStatModel();
            dJIDeviceInfoStatModel.devicetype = i;
            dJIDeviceInfoStatModel.deviceversion = str2;
            dJIDeviceInfoStatModel.devicesn = str;
            if (!a(dJIDeviceInfoStatModel)) {
                this.c.a(dJIDeviceInfoStatModel);
            }
        }
    }

    public void b() {
        DJIDeviceInfoStatModel dJIDeviceInfoStatModel = new DJIDeviceInfoStatModel();
        dJIDeviceInfoStatModel.devicetype = 1;
        dJIDeviceInfoStatModel.deviceversion = "test_version";
        dJIDeviceInfoStatModel.devicesn = "test_sn";
        if (!a(dJIDeviceInfoStatModel)) {
            this.c.a(dJIDeviceInfoStatModel);
        }
    }

    private synchronized void g() {
        if (this.e == 0) {
            List<DJIDeviceInfoStatModel> c = this.c.c(DJIDeviceInfoStatModel.class, "isUploaded = 0");
            this.e = c.size();
            for (DJIDeviceInfoStatModel b : c) {
                try {
                    b(b);
                } catch (UnsupportedEncodingException e) {
                }
            }
        }
    }

    private void b(final DJIDeviceInfoStatModel dJIDeviceInfoStatModel) throws UnsupportedEncodingException {
        this.b.a(dji.pilot.c.b.f, new StringEntity("Data=" + h.a(dJIDeviceInfoStatModel), "UTF-8"), UrlEncodedParser.CONTENT_TYPE, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ b b;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                this.b.e = this.b.e - 1;
                dJIDeviceInfoStatModel.isUploaded = true;
                this.b.c.e(dJIDeviceInfoStatModel);
            }

            public void a(Throwable th, int i, String str) {
                this.b.e = this.b.e - 1;
            }
        });
    }
}
