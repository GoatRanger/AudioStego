package dji.pilot.flyforbid;

import com.dji.frame.c.h;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.forbid.FlyForbidElement;
import dji.midware.data.forbid.FlyForbidElementAirMap;
import dji.midware.data.forbid.NFZLogUtil;
import dji.pilot.flyforbid.jsonbean.FlyforbidServerResult;
import dji.pilot.flyforbid.jsonbean.FlyforbidServerResultAirMap;
import dji.pilot.publics.objects.g;
import dji.thirdparty.afinal.f.a;

class a$2 extends a<String> {
    final /* synthetic */ boolean a;
    final /* synthetic */ double b;
    final /* synthetic */ double c;
    final /* synthetic */ a d;

    a$2(a aVar, boolean z, double d, double d2) {
        this.d = aVar;
        this.a = z;
        this.b = d;
        this.c = d2;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(final String str) {
        NFZLogUtil.LOGD("*********downloadDataFromServer onSuccess: type: " + a.b(this.d));
        new Thread(new Runnable(this) {
            final /* synthetic */ a$2 b;

            public void run() {
                if (a.b(this.b.d).equals(DJIFlyForbidController.AIRMAP_DATA_SOURCE)) {
                    NFZLogUtil.LOGD("get airmap data onSuccess");
                    FlyforbidServerResultAirMap flyforbidServerResultAirMap = (FlyforbidServerResultAirMap) h.b(str, FlyforbidServerResultAirMap.class);
                    if (flyforbidServerResultAirMap == null || flyforbidServerResultAirMap.release_limits == null) {
                        NFZLogUtil.LOGD("get airmap data onSuccess result or release_limits: null");
                    } else {
                        try {
                            a.a(this.b.d, g.b(a.a(this.b.d), FlyforbidUpdateService.b, 0));
                            this.b.d.c.a(new FlyForbidElementAirMap(), "area_id");
                            for (FlyForbidElementAirMap a : flyforbidServerResultAirMap.release_limits) {
                                long a2 = a.a(this.b.d, a);
                                if (a2 > a.c(this.b.d)) {
                                    a.a(this.b.d, a2);
                                }
                            }
                            g.a(a.a(this.b.d), FlyforbidUpdateService.b, a.c(this.b.d));
                            this.b.d.c.b();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    DJIFlyForbidController.getInstance().refreshDatabase();
                    a.c(this.b.d, false);
                    NFZLogUtil.LOGD("***get airmap data onSuccess done");
                    if (!this.b.a) {
                        NFZLogUtil.LOGD("**into download from dji");
                        a.a(this.b.d, this.b.b, this.b.c, true);
                    }
                } else if (a.b(this.b.d).equals(DJIFlyForbidController.DJI_DATA_SOURCE)) {
                    NFZLogUtil.LOGD("get dji data onSuccess");
                    FlyforbidServerResult flyforbidServerResult = (FlyforbidServerResult) h.b(str, FlyforbidServerResult.class);
                    if (flyforbidServerResult == null || flyforbidServerResult.release_limits == null) {
                        NFZLogUtil.LOGD("get dji data onSuccess result or release_limits: null");
                        a.c(this.b.d, false);
                        return;
                    }
                    try {
                        this.b.d.c.a(new FlyForbidElement(), "area_id");
                        for (FlyForbidElement a3 : flyforbidServerResult.release_limits) {
                            a.a(this.b.d, a3);
                        }
                        this.b.d.c.b();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    DJIFlyForbidController.getInstance().refreshDatabase();
                    NFZLogUtil.LOGD("***get dji data onSuccess done");
                }
                a.c(this.b.d, false);
                a.d(this.b.d, true);
                a.a(this.b.d, this.b.b);
                a.b(this.b.d, this.b.c);
            }
        }).start();
    }

    public void a(Throwable th, int i, String str) {
        NFZLogUtil.LOGD("********downloadDataFromServer onFailure: " + str);
        a.c(this.d, false);
    }
}
