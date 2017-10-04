package dji.dbox.upgrade.p4.d;

import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.dbox.upgrade.p4.a.a;
import dji.midware.data.model.P3.DataCommonGetCfgFile;
import dji.midware.e.d;
import java.io.IOException;

class a$1 implements d {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void onSuccess(Object obj) {
        DataCommonGetCfgFile dataCommonGetCfgFile = (DataCommonGetCfgFile) a.a(this.a).peek();
        if (dataCommonGetCfgFile == null) {
            a.a("mInnerDownCallBack-> mDownQueue.peek() == null !!!!!");
            a.b(this.a).add(Boolean.valueOf(false));
            a.c(this.a);
            return;
        }
        if (a.d(this.a) == a.e(this.a)) {
            a.a(this.a, dataCommonGetCfgFile.getRelLength());
        }
        a.a(this.a, dataCommonGetCfgFile.getBuffer(a.f(this.a)));
        a.b(this.a, a.g(this.a) + ((long) a.h(this.a)));
        a.c(this.a, dataCommonGetCfgFile.getRemainLength());
        try {
            a.i(this.a);
            if (a.j(this.a) == 0) {
                a.d(this.a, a.k(this.a));
                a.a(" mTotalLen = " + a.j(this.a) + "B = " + ((a.j(this.a) / 1024) / 1024) + "MB");
            }
            int round = ((a.j(this.a) == 0 ? 100 : Math.round((((float) (a.j(this.a) - a.k(this.a))) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) / ((float) a.j(this.a)))) * 100) / a.l(this.a);
            if (a.m(this.a) < round) {
                a.b(this.a, round);
                a.a("mInnerDownCallBack->onSuccess() mProgress=" + a.m(this.a) + ",mReadLen=" + a.d(this.a) + " mBufferSize=" + a.h(this.a) + " mRemainLen=" + a.k(this.a));
                a.n(this.a).a(round);
            }
            if (a.k(this.a) > 0) {
                a.o(this.a);
            } else if (a.k(this.a) == 0) {
                a.b(this.a).add(Boolean.valueOf(true));
                a.c(this.a);
            }
        } catch (IOException e) {
            e.printStackTrace();
            a.a("writeToLocal->onFailure, IOException=" + e + ",continue download the next");
            a.b(this.a).add(Boolean.valueOf(false));
            a.c(this.a);
        }
    }

    public void onFailure(dji.midware.data.config.P3.a aVar) {
        a.a("mInnerDownCallBack->onFailure, ccode=" + aVar + " may be down part!!!,,continue download the next");
        a.b(this.a).add(Boolean.valueOf(false));
        a.a(this.a, null);
        a.c(this.a);
    }
}
