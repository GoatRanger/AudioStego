package dji.pilot.battery.service;

import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot.battery.model.BanSN;
import dji.pilot.publics.objects.g;
import dji.thirdparty.afinal.f.a;

class BatteryCheckService$a$3 extends a<String> {
    final /* synthetic */ BatteryCheckService.a a;

    BatteryCheckService$a$3(BatteryCheckService.a aVar) {
        this.a = aVar;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        if (str != null) {
            BanSN banSN = (BanSN) h.b(str, BanSN.class);
            DJILogHelper.getInstance().LOGI("BatteryCheck", "battery list json string: " + str);
            if (banSN != null && banSN.status == 0) {
                DJILogHelper.getInstance().LOGI("BatteryCheck", "battery SN object: " + banSN);
                this.a.a.n = banSN;
                g.a(this.a.a, BatteryCheckService.j, str);
            }
        }
    }

    public void a(Throwable th, int i, String str) {
    }
}
