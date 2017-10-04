package dji.pilot.fpv.d;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.man.MANService;

class a$1 implements InitResultCallback {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void onSuccess() {
        ((MANService) AlibabaSDK.getService(MANService.class)).getMANAnalytics().turnOffCrashHandler();
        a.a(true);
    }

    public void onFailure(int i, String str) {
    }
}
