package com.tencent.android.tpush;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.o;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.encrypt.Rijndael;

final class v implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ XGIOperateCallback b;
    final /* synthetic */ long c;
    final /* synthetic */ String d;

    v(Context context, XGIOperateCallback xGIOperateCallback, long j, String str) {
        this.a = context;
        this.b = xGIOperateCallback;
        this.c = j;
        this.d = str;
    }

    public void run() {
        try {
            int a = p.a(this.a);
            if (a == 0) {
                long accessId = this.c <= 0 ? XGPushConfig.getAccessId(this.a) : this.c;
                String accessKey = p.a(this.d) ? XGPushConfig.getAccessKey(this.a) : this.d;
                String token = XGPushConfig.getToken(this.a);
                if ((accessId <= 0 || p.a(accessKey) || p.a(token)) && this.b != null) {
                    this.b.onFail(null, Constants.CODE_LOGIC_ILLEGAL_ARGUMENT, "The accessId, accessKey or token is invalid! accessId=" + accessId + ",accessKey=" + accessKey + ",token=" + token);
                    throw new IllegalArgumentException("accessId, accessKey or token is invalid.");
                }
                Intent intent = new Intent("com.tencent.android.tpush.action.UNREGISTER");
                intent.putExtra("accId", Rijndael.encrypt("" + accessId));
                intent.putExtra("accKey", Rijndael.encrypt(accessKey));
                intent.putExtra("token", Rijndael.encrypt(token));
                intent.putExtra(Constants.FLAG_PACK_NAME, Rijndael.encrypt(this.a.getPackageName()));
                intent.putExtra("operation", 101);
                intent.putExtra("opType", 1);
                boolean a2 = o.a(this.a).a();
                if (p.d(this.a) != 1 || a2) {
                    XGPushManager.a(this.a, intent, this.b, a2);
                } else {
                    XGPushManager.a(this.a, intent, this.b);
                }
            } else if (this.b != null) {
                this.b.onFail(null, a, "XINGE SDK config error");
            }
        } catch (Throwable th) {
            a.d(Constants.LogTag, "unregisterPush", th);
        }
    }
}
