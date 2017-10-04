package dji.pilot.fpv.control.a;

import android.os.Message;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.pilot.usercenter.b.c.d;

class b$1 implements d {
    final /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public void a(String str, Object obj, boolean z) {
    }

    public void a(String str, Object obj, int i, int i2) {
    }

    public void a(String str, String str2, Object obj) {
        b.a(this.a, b.a(this.a));
        Message obtainMessage = this.a.b.obtainMessage();
        obtainMessage.what = 0;
        obtainMessage.obj = str2;
        this.a.b.sendMessageDelayed(obtainMessage, 60000);
        short a = b.a(this.a, str2);
        b.b(this.a, a);
        int b = (int) b.b(this.a, str2);
        b.c(this.a, b);
        b.a(this.a, false);
        b.d(this.a, 0);
        this.a.b();
        DJILogHelper.getInstance().LOGE("agps", "agps download success");
        b.c(this.a, "downLoadAGPSOfflineData success: crc16 = " + a + " dataLength = " + b);
        b.c(this.a, "downLoadAGPSOfflineData success: filePath = " + str2 + " currTime = " + b.a(this.a));
    }

    public void a(String str, Object obj, int i) {
        DJILogHelper.getInstance().LOGE("agps", "agps download fail code=" + i);
        this.a.b.sendEmptyMessageDelayed(1, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
    }
}
