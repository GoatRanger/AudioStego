package dji.pilot.flyforbid;

import com.dji.frame.c.h;
import com.dji.frame.c.l;
import dji.logic.f.d;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.forbid.DJIFlyForbidController.GeoStatusEvent;
import dji.midware.data.forbid.NFZLogUtil;
import dji.pilot.flyunlimit.b;
import dji.pilot.flyunlimit.jsonbean.DJINoFlyZoneParamsResult;
import dji.pilot.publics.objects.g;
import dji.thirdparty.a.c;
import dji.thirdparty.afinal.f.a;

class a$1 extends a<String> {
    final /* synthetic */ double a;
    final /* synthetic */ double b;
    final /* synthetic */ a c;

    a$1(a aVar, double d, double d2) {
        this.c = aVar;
        this.a = d;
        this.b = d2;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        a.a(this.c, false);
        NFZLogUtil.LOGD("requestNfzParams onSuccess: " + str);
        DJINoFlyZoneParamsResult dJINoFlyZoneParamsResult = (DJINoFlyZoneParamsResult) h.b(str, DJINoFlyZoneParamsResult.class);
        if (dJINoFlyZoneParamsResult == null) {
            NFZLogUtil.LOGD("requestNfzParams onSuccess: result null");
        } else if (l.a(dJINoFlyZoneParamsResult.mark) || dJINoFlyZoneParamsResult.mark.compareToIgnoreCase("expired") != 0) {
            if (dJINoFlyZoneParamsResult.signature != null) {
                if (dJINoFlyZoneParamsResult.signature.compareTo(dji.pilot.a.a.c(String.format("%d%d%s%s%s%s", new Object[]{Long.valueOf(dJINoFlyZoneParamsResult.status), Long.valueOf(dJINoFlyZoneParamsResult.time), dJINoFlyZoneParamsResult.country, dJINoFlyZoneParamsResult.type, dJINoFlyZoneParamsResult.url_key, dJINoFlyZoneParamsResult.url}), b.a)) != 0) {
                    NFZLogUtil.LOGD("requestNfzParams onSuccess signature wrong");
                    return;
                }
            }
            if (dJINoFlyZoneParamsResult.status == 200) {
                if (dji.pilot.c.a.C && dJINoFlyZoneParamsResult.country.equals("CN")) {
                    dJINoFlyZoneParamsResult.type = DJIFlyForbidController.AIRMAP_DATA_SOURCE;
                }
                NFZLogUtil.LOGD("requestNfzParams onSuccess data source: " + dJINoFlyZoneParamsResult.type + " country: " + dJINoFlyZoneParamsResult.country);
                g.a(a.a(this.c), DJIFlyForbidController.KEY_FLY_FORBID_DATA_SOURCE, dJINoFlyZoneParamsResult.type);
                boolean b = g.b(a.a(this.c), DJIFlyForbidController.KEY_OPEN_GEO, true);
                if (!(d.a() && dji.pilot.c.a.E && b)) {
                    g.a(a.a(this.c), DJIFlyForbidController.KEY_FLY_FORBID_DATA_SOURCE, DJIFlyForbidController.DJI_DATA_SOURCE);
                }
                if (dJINoFlyZoneParamsResult.type.equals(DJIFlyForbidController.AIRMAP_DATA_SOURCE) && d.a()) {
                    g.a(a.a(this.c), DJIFlyForbidController.KEY_CUR_USE_GEO_SYSTEM, true);
                    c.a().e(GeoStatusEvent.OPENED);
                } else {
                    g.a(a.a(this.c), DJIFlyForbidController.KEY_CUR_USE_GEO_SYSTEM, false);
                    c.a().e(GeoStatusEvent.CLOSED);
                }
                g.a(a.a(this.c), DJIFlyForbidController.KEY_DJI_SERVER_TIME, dJINoFlyZoneParamsResult.time);
                a.a(this.c, dJINoFlyZoneParamsResult.country);
                a.b(this.c, dJINoFlyZoneParamsResult.type);
                b.a(dJINoFlyZoneParamsResult.url_key);
                g.a(a.a(this.c), a.b, dJINoFlyZoneParamsResult.url_key);
                a.b(this.c, true);
                a.a(this.c, this.a);
                a.b(this.c, this.b);
                a.a(this.c, this.a, this.b, false);
                return;
            }
            NFZLogUtil.LOGD("requestNfzParams onSuccess status wrong: " + dJINoFlyZoneParamsResult.status);
        } else {
            c.a().e(FlyforbidUpdateService.a.b);
        }
    }

    public void a(Throwable th, int i, String str) {
        a.a(this.c, false);
        NFZLogUtil.LOGD("requestNfzParams onFailure: " + str + "\n" + i + "\n" + th.getMessage());
    }
}
