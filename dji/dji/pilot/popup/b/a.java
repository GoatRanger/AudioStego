package dji.pilot.popup.b;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import dji.pilot.publics.objects.DJIApplication;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.c.d;

public class a implements d {
    private static a e = null;
    private DJISDKCache a = DJISDKCache.getInstance();
    private c b = null;
    private c c = null;
    private Context d = DJIApplication.a();

    public static a getInstance() {
        if (e == null) {
            synchronized (a.class) {
                if (e == null) {
                    e = new a();
                }
            }
        }
        return e;
    }

    private a() {
        dji.sdksharedlib.b.c.a aVar = new dji.sdksharedlib.b.c.a();
        this.b = aVar.b(e.a).a(Integer.valueOf(0)).d("InternalSerialNumber").a();
        this.a.startListeningForUpdates(this.b, this, false);
        this.c = aVar.b(e.a).a(Integer.valueOf(0)).d(e.ak).a();
        this.a.startListeningForUpdates(this.c, this, false);
        dji.sdksharedlib.d.a availableValue = this.a.getAvailableValue(this.b);
        if (!(availableValue == null || availableValue.e() == null)) {
            b.getInstance().a((String) availableValue.e());
        }
        availableValue = this.a.getAvailableValue(this.c);
        if (availableValue != null && availableValue.e() != null) {
            b.getInstance().a(((Boolean) availableValue.e()).booleanValue());
        }
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (cVar.equals(this.b)) {
            dji.pilot.popup.c.a.a(this.d, "onValueChange is FlycSn");
            if (aVar2 != null && aVar2.e() != null) {
                String str = (String) aVar2.e();
                dji.pilot.popup.c.a.a(this.d, "FlycSn = " + str);
                if (!TextUtils.isEmpty(str)) {
                    b.getInstance().a(str);
                    new Handler().postDelayed(new 1(this), 10000);
                }
            }
        } else if (cVar.equals(this.c)) {
            dji.pilot.popup.c.a.a(this.d, "onValueChange is mKeyMotorOn");
            if (aVar2 != null && aVar2.e() != null) {
                boolean booleanValue = ((Boolean) aVar2.e()).booleanValue();
                b.getInstance().a(booleanValue);
                dji.pilot.popup.c.a.a(this.d, "mIsMotorOn = " + booleanValue);
            }
        }
    }
}
