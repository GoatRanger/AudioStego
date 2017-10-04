package dji.pilot.battery.service;

import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot.battery.model.BanSnMd5;
import dji.pilot.publics.objects.g;
import dji.thirdparty.afinal.f.a;

class BatteryCheckService$a$4 extends a<String> {
    final /* synthetic */ BatteryCheckService.a a;

    BatteryCheckService$a$4(BatteryCheckService.a aVar) {
        this.a = aVar;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        if (str != null) {
            BanSnMd5 banSnMd5 = (BanSnMd5) h.b(str, BanSnMd5.class);
            DJILogHelper.getInstance().LOGI("BatteryCheck", "battery md5 list json string: " + str);
            if (banSnMd5 != null && banSnMd5.status == 0) {
                DJILogHelper.getInstance().LOGI("BatteryCheck", "battery SN md5 object: " + banSnMd5);
                this.a.a.o = banSnMd5;
                g.a(this.a.a, BatteryCheckService.k, str);
            }
        }
    }

    public void a(Throwable th, int i, String str) {
    }
}
