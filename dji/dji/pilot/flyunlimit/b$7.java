package dji.pilot.flyunlimit;

import com.dji.frame.c.h;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.forbid.NFZLogUtil;
import dji.midware.data.forbid.UnlimitAreaRecordElement;
import dji.pilot.flyunlimit.b.f;
import dji.pilot.flyunlimit.jsonbean.DJILicenseUnlockListResult;
import dji.pilot.flyunlimit.jsonbean.DJILicenseUnlockListResult.ListData;
import dji.pilot.flyunlimit.jsonbean.UnlockListItem;
import dji.thirdparty.afinal.b;
import dji.thirdparty.afinal.f.a;
import java.util.ArrayList;
import java.util.List;

class b$7 extends a<String> {
    final /* synthetic */ f a;
    final /* synthetic */ b b;

    b$7(b bVar, f fVar) {
        this.b = bVar;
        this.a = fVar;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        DJILicenseUnlockListResult dJILicenseUnlockListResult = (DJILicenseUnlockListResult) h.b(str, DJILicenseUnlockListResult.class);
        NFZLogUtil.LOGD("getLicenseUnlockList onSuccess: " + str);
        if (dJILicenseUnlockListResult == null) {
            NFZLogUtil.LOGD("getLicenseUnlockList result null");
            if (this.a != null) {
                this.a.a();
            }
        } else if (dJILicenseUnlockListResult.signature != null && !dJILicenseUnlockListResult.signature.equals(dji.pilot.a.a.c("" + dJILicenseUnlockListResult.status + dJILicenseUnlockListResult.time, b.a))) {
            NFZLogUtil.LOGD("getLicenseUnlockList result signature wrong");
            if (this.a != null) {
                this.a.a();
            }
        } else if (dJILicenseUnlockListResult.status != 200 || dJILicenseUnlockListResult.data.isEmpty()) {
            if (this.a != null) {
                this.a.a();
            }
            NFZLogUtil.LOGD("getLicenseUnlockList result status or data wrong");
        } else {
            b db = DJIFlyForbidController.getInstance().getDb();
            List<UnlockListItem> arrayList = new ArrayList();
            for (ListData listData : dJILicenseUnlockListResult.data) {
                if (!listData.disable.equals("1")) {
                    if (dji.pilot.a.a.c(listData.id + listData.account + listData.begin_at + listData.end_at + listData.country + listData.city + listData.areas_type + listData.areas_id + listData.location + listData.places + listData.type + listData.sn + listData.timezone + listData.disable + listData.status + listData.begin_time + listData.end_time + listData.os, b.a).equals(listData.signature)) {
                        for (String valueOf : listData.areas_id.split(",")) {
                            UnlimitAreaRecordElement unlimitAreaRecordElement = new UnlimitAreaRecordElement(Integer.valueOf(valueOf).intValue(), listData.begin_time, listData.end_time, listData.sn, dji.pilot.usercenter.b.f.getInstance().o(), true);
                            if (db.c(UnlimitAreaRecordElement.class, "area_id=" + unlimitAreaRecordElement.area_id).size() > 0) {
                                db.a(UnlimitAreaRecordElement.class, "area_id=" + unlimitAreaRecordElement.area_id);
                            }
                            db.a(unlimitAreaRecordElement);
                        }
                        arrayList.add(new UnlockListItem(listData.places, listData.location, listData.begin_at, listData.end_at, listData.status, listData.areas_type, listData.os));
                    } else {
                        NFZLogUtil.LOGD("getLicenseUnlockList id: " + listData.id + " signature is wrong.");
                        if (this.a != null) {
                            this.a.a();
                        }
                    }
                }
            }
            db.a(UnlockListItem.class);
            db.b(new UnlockListItem());
            for (UnlockListItem c : arrayList) {
                db.c(c);
            }
            db.a();
            if (this.a != null) {
                this.a.a(arrayList);
            }
        }
    }

    public void a(Throwable th, int i, String str) {
        if (this.a != null) {
            this.a.a();
        }
        NFZLogUtil.LOGD("getLicenseUnlockList onFailure: " + th);
    }
}
