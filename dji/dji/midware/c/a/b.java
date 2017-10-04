package dji.midware.c.a;

import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILog;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class b {
    public final String a = "DM368GBlockRequest";
    private DataCommonGetVersion b = null;
    private CountDownLatch c = null;
    private boolean d = false;

    public DataCommonGetVersion a() {
        this.c = new CountDownLatch(1);
        this.b = new DataCommonGetVersion();
        this.b.setDeviceType(DeviceType.DM368_G);
        this.d = false;
        this.b.start(new d(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.c.countDown();
                this.a.d = true;
            }

            public void onFailure(a aVar) {
                this.a.c.countDown();
            }
        });
        try {
            this.c.await(OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DJILog.d("DM368GBlockRequest", "getDM368Block()");
        if (this.d) {
            return this.b;
        }
        return null;
    }
}
