package dji.sdksharedlib.hardware.abstractions.e;

import android.util.Log;
import com.alipay.sdk.j.f;
import dji.common.gimbal.DJIGimbalCalibrationState;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.e;
import dji.midware.data.model.P3.DataGimbalGetUserParams;
import dji.midware.e.d;

class h$7 implements Runnable {
    final /* synthetic */ h a;

    h$7(h hVar) {
        this.a = hVar;
    }

    public void run() {
        DataGimbalGetUserParams.getInstance().setInfos(new String[]{h.r()[22]}).start(new d(this) {
            final /* synthetic */ h$7 a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                int intValue = e.read(h.r()[22]).value.intValue();
                Log.e("CalSystem", "update value onSuccess: " + intValue);
                switch (h$2.a[DJIGimbalCalibrationState.values()[intValue].ordinal()]) {
                    case 1:
                        this.a.a.d = false;
                        this.a.a.e = true;
                        return;
                    case 2:
                        this.a.a.d = true;
                        return;
                    case 3:
                        Log.e("CalSystem", f.b);
                        this.a.a.d = false;
                        this.a.a.e = false;
                        this.a.a.a = true;
                        h.a(this.a.a).countDownLatch();
                        return;
                    case 4:
                        Log.e("CalSystem", "successful");
                        this.a.a.d = false;
                        this.a.a.e = true;
                        this.a.a.a = true;
                        h.a(this.a.a).countDownLatch();
                        return;
                    default:
                        return;
                }
            }

            public void onFailure(a aVar) {
            }
        });
    }
}
