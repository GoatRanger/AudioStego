package dji.pilot.active;

import android.util.Log;
import com.dji.frame.c.h;
import com.dji.frame.c.l;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.pilot2.usercenter.activate.a.b;
import dji.pilot2.usercenter.activate.c;
import dji.thirdparty.afinal.f.a;

class DJIActiveController$6 extends a<String> {
    final /* synthetic */ DJIActiveController a;

    DJIActiveController$6(DJIActiveController dJIActiveController) {
        this.a = dJIActiveController;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        i.getInstance().c();
        DJIActiveController.a(this.a, (DJIActiveSnModel) h.b(str, DJIActiveSnModel.class));
        String str2 = "";
        Log.i("DJIActiveController", "activeSnModel.status:" + DJIActiveController.a(this.a).status);
        switch (DJIActiveController.a(this.a).status) {
            case 1000:
                str2 = "signature认证失败";
                break;
            case 1001:
                str2 = "请求超时";
                break;
            case 1002:
                str2 = "参数不全或不合法";
                break;
            default:
                if (dji.pilot2.usercenter.activate.a.getInstance().f() == b.WIFI) {
                    DJIActiveController.b(this.a, DJIActiveController.a(this.a));
                } else {
                    DJIActiveController.c(this.a, DJIActiveController.a(this.a));
                }
                DJILogHelper.getInstance().LOGD("DJIActiveController", "activeSnModel", false, true);
                return;
        }
        c.a("激活失败 ：" + str2);
        DJIActiveController.a(this.a, DJIActiveController$a.ServerBackFail);
        if (DJIActiveController.b(this.a) != null) {
            DJIActiveController.b(this.a).c();
        }
        try {
            this.a.a(str, DJIActiveController.a(this.a).status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Throwable th, int i, String str) {
        String str2;
        switch (i) {
            case 1000:
                str2 = "signature认证失败";
                break;
            case 1001:
                str2 = "请求超时";
                break;
            case 1002:
                str2 = "参数不全或不合法";
                break;
            default:
                str2 = str;
                break;
        }
        c.a("激活失败 ：" + str2);
        str2 = new DataCommonGetVersion().setDeviceType(DeviceType.FLYC).getFirmVer(".");
        if (DJIActiveController.g().contains(DeviceType.FLYC) && l.a(str2)) {
            DJIActiveController.a(this.a, DJIActiveController$a.FirmwareNotMatch);
        } else {
            DJIActiveController.a(this.a, DJIActiveController$a.ServerBackFail);
        }
        if (DJIActiveController.b(this.a) != null) {
            DJIActiveController.b(this.a).c();
        }
        try {
            this.a.a(str, DJIActiveController.a(this.a).status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
