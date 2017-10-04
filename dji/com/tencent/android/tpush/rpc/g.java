package com.tencent.android.tpush.rpc;

import android.content.ServiceConnection;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.l;

public class g extends e {
    private ServiceConnection a;

    public void a(ServiceConnection serviceConnection) {
        this.a = serviceConnection;
    }

    public void a() {
        try {
            if (l.e() != null) {
                l.e().unbindService(this.a);
                this.a = null;
            }
        } catch (Throwable th) {
            a.c(Constants.ServiceLogTag, "unBind", th);
        }
    }
}
