package dji.pilot.fpv.activity;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetMeteringArea;
import dji.midware.e.d;
import dji.pilot.publics.objects.DJIBaseActivity;

class DJIPreviewActivityGrape$1 implements d {
    final /* synthetic */ DJIPreviewActivityGrape a;

    DJIPreviewActivityGrape$1(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
    }

    public void onSuccess(Object obj) {
        DataCameraGetMeteringArea dataCameraGetMeteringArea = (DataCameraGetMeteringArea) obj;
        this.a.c = dataCameraGetMeteringArea.getHnum();
        this.a.d = dataCameraGetMeteringArea.getVnum();
        this.a.b = DJIBaseActivity.screenWidth / this.a.c;
        this.a.a = DJIBaseActivity.screenHeight / this.a.d;
        DJILogHelper.getInstance().LOGD(this.a.TAG, "测光区域获取成功 " + this.a.c + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.a.d);
    }

    public void onFailure(a aVar) {
        this.a.b = 0;
        DJILogHelper.getInstance().LOGD(this.a.TAG, "测光区域获取 " + aVar);
    }
}
