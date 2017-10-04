package dji.pilot.fpv.control.a;

import android.os.Message;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class a$1 implements d {
    final /* synthetic */ byte a;
    final /* synthetic */ a b;

    a$1(a aVar, byte b) {
        this.b = aVar;
        this.a = b;
    }

    public void onSuccess(Object obj) {
        Message obtainMessage = a.f(this.b).obtainMessage();
        obtainMessage.obj = obj;
        obtainMessage.what = 1;
        a.f(this.b).sendMessage(obtainMessage);
        if (this.a == (byte) 1) {
            DJILogHelper.getInstance().LOGE("agps", "send agps success");
        }
        a.a(this.b, "sendData success ");
    }

    public void onFailure(a aVar) {
        a.f(this.b).sendEmptyMessageDelayed(2, 10000);
        a.a(this.b, "sendData fail ");
    }
}
