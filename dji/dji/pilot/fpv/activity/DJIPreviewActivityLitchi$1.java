package dji.pilot.fpv.activity;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetMeteringArea;
import dji.midware.e.d;
import dji.pilot.publics.objects.DJIBaseActivity;

class DJIPreviewActivityLitchi$1 implements d {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$1(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public void onSuccess(Object obj) {
        DataCameraGetMeteringArea dataCameraGetMeteringArea = (DataCameraGetMeteringArea) obj;
        this.a.g = dataCameraGetMeteringArea.getHnum();
        this.a.h = dataCameraGetMeteringArea.getVnum();
        this.a.e = DJIBaseActivity.screenWidth / this.a.g;
        this.a.d = DJIBaseActivity.screenHeight / this.a.h;
        DJILogHelper.getInstance().LOGD(this.a.TAG, "测光区域获取成功 " + this.a.g + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.a.h);
    }

    public void onFailure(a aVar) {
        this.a.e = 0;
        DJILogHelper.getInstance().LOGD(this.a.TAG, "测光区域获取 " + aVar);
    }
}
