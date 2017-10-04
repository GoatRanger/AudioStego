package dji.pilot.flyunlimit;

import com.dji.frame.c.h;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.forbid.NFZLogUtil;
import dji.midware.data.model.P3.DataFlycGetPushRequestLimitUpdate;
import dji.pilot.flyunlimit.b.e;
import dji.pilot.flyunlimit.jsonbean.DJIUnlimitUnlockApplyResult;
import dji.pilot.usercenter.b.f;
import dji.thirdparty.a.c;
import dji.thirdparty.afinal.f.a;
import java.util.List;

class b$4 extends a<String> {
    final /* synthetic */ e a;
    final /* synthetic */ List b;
    final /* synthetic */ b c;

    b$4(b bVar, e eVar, List list) {
        this.c = bVar;
        this.a = eVar;
        this.b = list;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        NFZLogUtil.LOGD("MOBILE_UNLOCK_AREAS return: " + str);
        DJIUnlimitUnlockApplyResult dJIUnlimitUnlockApplyResult = (DJIUnlimitUnlockApplyResult) h.b(str, DJIUnlimitUnlockApplyResult.class);
        if (dJIUnlimitUnlockApplyResult != null) {
            if (dJIUnlimitUnlockApplyResult.signature != null) {
                if (dJIUnlimitUnlockApplyResult.signature.compareTo(dji.pilot.a.a.c(String.format("%d%d%s%s%s", new Object[]{Long.valueOf(dJIUnlimitUnlockApplyResult.status), Long.valueOf(dJIUnlimitUnlockApplyResult.time), dJIUnlimitUnlockApplyResult.areas_type, dJIUnlimitUnlockApplyResult.areas_id, dJIUnlimitUnlockApplyResult.unlock_apply_id}), b.a)) != 0) {
                    b.a(this.c, 19);
                    if (this.a != null) {
                        this.a.a("unlock apply signature is wrong");
                        return;
                    }
                    return;
                }
            }
            if (dJIUnlimitUnlockApplyResult.status == 200 && Integer.parseInt(dJIUnlimitUnlockApplyResult.unlock_apply_id) > 1) {
                new Thread(new Runnable(this) {
                    final /* synthetic */ b$4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        DJIFlyForbidController.getInstance().removeArrayFromCheckResult(this.a.b, f.getInstance().o(), dji.pilot.flyforbid.a.getInstance(b.b(this.a.c)).d());
                        c.a().e(DataFlycGetPushRequestLimitUpdate.getInstance());
                        if (this.a.a != null) {
                            this.a.a.a();
                        }
                    }
                }).start();
            } else if (this.a != null) {
                this.a.a(a.b(b.b(this.c), (int) dJIUnlimitUnlockApplyResult.status));
            }
        } else if (this.a != null) {
            this.a.a("Apply result is null");
        }
    }

    public void a(Throwable th, int i, String str) {
        if (this.a != null) {
            this.a.a("unlock areas fail, err str: " + str);
        }
    }
}
