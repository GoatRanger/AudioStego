package dji.sdksharedlib.hardware.abstractions.e;

import android.util.Log;
import dji.common.gimbal.DJIGimbalBalanceTestResult;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.e;
import dji.midware.data.model.P3.DataGimbalGetUserParams;
import dji.midware.e.d;

class h$9 implements Runnable {
    final /* synthetic */ h a;

    h$9(h hVar) {
        this.a = hVar;
    }

    public void run() {
        DataGimbalGetUserParams.getInstance().setInfos(new String[]{h.r()[23]}).start(new d(this) {
            final /* synthetic */ h$9 a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                Log.e("BalanceTest", "update balance test result");
                int intValue = e.read(h.r()[23]).value.intValue();
                if (((intValue >> 6) & 3) == 2) {
                    Log.e("BalanceTest", "test finished");
                    this.a.a.a = true;
                    this.a.a.c(false);
                    int i = (intValue >> 2) & 3;
                    intValue = (intValue >> 4) & 3;
                    this.a.a.a(DJIGimbalBalanceTestResult.values()[i]);
                    this.a.a.b(DJIGimbalBalanceTestResult.values()[intValue]);
                    h.a(this.a.a).countDownLatch();
                    return;
                }
                Log.e("BalanceTest", "test not finished");
                this.a.a.c(true);
                Log.e("BalanceTest", String.valueOf(this.a.a.f));
            }

            public void onFailure(a aVar) {
            }
        });
    }
}
