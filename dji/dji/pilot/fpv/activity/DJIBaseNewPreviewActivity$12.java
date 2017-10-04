package dji.pilot.fpv.activity;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetMeteringArea;
import dji.midware.e.d;
import dji.pilot.publics.objects.DJIBaseActivity;

class DJIBaseNewPreviewActivity$12 implements d {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$12(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void onSuccess(Object obj) {
        DataCameraGetMeteringArea dataCameraGetMeteringArea = (DataCameraGetMeteringArea) obj;
        this.a.w = dataCameraGetMeteringArea.getHnum();
        this.a.x = dataCameraGetMeteringArea.getVnum();
        this.a.u = DJIBaseActivity.screenWidth / this.a.w;
        this.a.t = DJIBaseActivity.screenHeight / this.a.x;
        DJILogHelper.getInstance().LOGD(this.a.TAG, "测光区域获取成功 " + this.a.w + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.a.x);
    }

    public void onFailure(a aVar) {
        this.a.u = 0;
        DJILogHelper.getInstance().LOGD(this.a.TAG, "测光区域获取 " + aVar);
    }
}
