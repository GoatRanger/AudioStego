package dji.sdksharedlib.hardware.abstractions.h;

import android.util.SparseArray;
import dji.common.error.DJIError;
import dji.common.remotecontroller.DJIRCControlPermission;
import dji.common.remotecontroller.DJIRCInfo;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcGetSearchMasters;
import dji.midware.data.model.P3.DataRcGetSlaveList.RcModel;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;
import java.util.ArrayList;

class a$21 implements d {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ e b;
    final /* synthetic */ a c;

    a$21(a aVar, ArrayList arrayList, e eVar) {
        this.c = aVar;
        this.a = arrayList;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        SparseArray list = DataRcGetSearchMasters.getInstance().getList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            RcModel rcModel = (RcModel) list.get(list.keyAt(i));
            this.a.add(new DJIRCInfo(rcModel.id, rcModel.name, "", (short) rcModel.quality, new DJIRCControlPermission(rcModel.yaw, rcModel.roll, rcModel.pitch, rcModel.playback, rcModel.record, rcModel.takephoto)));
        }
        if (this.b != null) {
            this.b.a((DJIRCInfo[]) this.a.toArray(new DJIRCInfo[this.a.size()]));
        }
    }

    public void onFailure(a aVar) {
        if (this.b != null) {
            this.b.a(DJIError.getDJIError(aVar));
        }
    }
}
