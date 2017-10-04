package dji.pilot.active;

import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataFlycSetActiveResult.DJIActivationState;
import dji.pilot.publics.model.DJIOnBoardResultDataModel;
import dji.pilot.publics.model.DJIOnBoardResultModel;
import dji.thirdparty.afinal.f.a;

class d$1 extends a<String> {
    final /* synthetic */ d a;

    d$1(d dVar) {
        this.a = dVar;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        DJILogHelper.getInstance().LOGE(d.a(this.a), "onboard success " + str, false, true);
        DJIOnBoardResultModel dJIOnBoardResultModel = (DJIOnBoardResultModel) h.b(str, DJIOnBoardResultModel.class);
        if (dJIOnBoardResultModel.status == 0) {
            DJIOnBoardResultDataModel dJIOnBoardResultDataModel = (DJIOnBoardResultDataModel) h.b(d.a(this.a, dJIOnBoardResultModel.data), DJIOnBoardResultDataModel.class);
            DJILogHelper.getInstance().LOGE(d.a(this.a), "onboard success " + dJIOnBoardResultDataModel.app_level, false, true);
            d.a(this.a, DJIActivationState.a, dJIOnBoardResultDataModel);
        } else if (dJIOnBoardResultModel.status == 2) {
            d.a(this.a, DJIActivationState.c);
        } else if (dJIOnBoardResultModel.status == 3) {
            d.a(this.a, DJIActivationState.d);
        } else {
            d.a(this.a, DJIActivationState.d);
        }
    }

    public void a(Throwable th, int i, String str) {
        DJILogHelper.getInstance().LOGE(d.a(this.a), "onboard " + str, false, true);
        d.a(this.a, DJIActivationState.c);
    }
}
