package dji.sdksharedlib.hardware.abstractions.h.a;

import dji.midware.data.model.P3.DataOsdGetSdrConfig;
import dji.midware.data.model.P3.DataRcGetFDRcCalibrationState;
import dji.midware.e.d;
import dji.sdksharedlib.b.a.c;
import dji.sdksharedlib.b.i;
import dji.sdksharedlib.hardware.a.e;
import dji.sdksharedlib.hardware.a.f;
import dji.sdksharedlib.hardware.a.h;
import java.util.List;

public class a extends h {

    private class a implements d {
        final /* synthetic */ a a;
        private f[] b;

        public a(a aVar, f[] fVarArr) {
            this.a = aVar;
            this.b = fVarArr;
        }

        public void onSuccess(Object obj) {
            int i = 0;
            new dji.sdksharedlib.b.c.a().b(i.a).a(Integer.valueOf(0)).c(c.h).b(Integer.valueOf(0));
            DataOsdGetSdrConfig.getInstance().getSelectionMode();
            f[] fVarArr = this.b;
            int length = fVarArr.length;
            while (i < length) {
                f fVar = fVarArr[i];
                if (fVar.a.compareTo(i.ab) == 0) {
                    fVar.b.a(Integer.valueOf(DataRcGetFDRcCalibrationState.getInstance().getASegmentFilledUpState()));
                } else if (fVar.a.compareTo(i.ac) == 0) {
                    fVar.b.a(Integer.valueOf(DataRcGetFDRcCalibrationState.getInstance().getBSegmentFilledUpState()));
                } else if (fVar.a.compareTo(i.ad) == 0) {
                    fVar.b.a(Integer.valueOf(DataRcGetFDRcCalibrationState.getInstance().getCSegmentFilledUpState()));
                } else if (fVar.a.compareTo(i.ae) == 0) {
                    fVar.b.a(Integer.valueOf(DataRcGetFDRcCalibrationState.getInstance().getDSegmentFilledUpState()));
                } else if (fVar.a.compareTo(i.af) == 0) {
                    fVar.b.a(Integer.valueOf(DataRcGetFDRcCalibrationState.getInstance().getESegmentFilledUpState()));
                } else if (fVar.a.compareTo(i.ag) == 0) {
                    fVar.b.a(Integer.valueOf(DataRcGetFDRcCalibrationState.getInstance().getFSegmentFilledUpState()));
                } else if (fVar.a.compareTo(i.ah) == 0) {
                    fVar.b.a(Integer.valueOf(DataRcGetFDRcCalibrationState.getInstance().getGSegmentFilledUpState()));
                } else if (fVar.a.compareTo(i.ai) == 0) {
                    fVar.b.a(Integer.valueOf(DataRcGetFDRcCalibrationState.getInstance().getHSegmentFilledUpState()));
                } else if (fVar.a.compareTo(i.aa) == 0) {
                    fVar.b.a(Integer.valueOf(DataRcGetFDRcCalibrationState.getInstance().getSegmentNumber()));
                }
                i++;
            }
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
        }
    }

    protected void a(List<Object> list) {
        DataRcGetFDRcCalibrationState.getInstance().start(new a(this, (f[]) list.toArray(new f[list.size()])));
    }

    public void a(String str, e eVar) {
        a((Object) new f(str, eVar));
    }
}
