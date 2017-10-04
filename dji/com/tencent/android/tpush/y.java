package com.tencent.android.tpush;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.n;
import com.tencent.android.tpush.common.o;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.encrypt.Rijndael;
import dji.pilot.usercenter.protocol.d;

final class y implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ XGIOperateCallback b;
    final /* synthetic */ long c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;
    final /* synthetic */ String f;
    final /* synthetic */ String g;
    final /* synthetic */ int h;

    y(Context context, XGIOperateCallback xGIOperateCallback, long j, String str, String str2, String str3, String str4, int i) {
        this.a = context;
        this.b = xGIOperateCallback;
        this.c = j;
        this.d = str;
        this.e = str2;
        this.f = str3;
        this.g = str4;
        this.h = i;
    }

    public void run() {
        try {
            int a = p.a(this.a);
            if (a == 0) {
                long accessId = this.c > 0 ? this.c : XGPushConfig.getAccessId(this.a);
                String accessKey = p.a(this.d) ? XGPushConfig.getAccessKey(this.a) : this.d;
                if (accessId <= 0 || p.a(accessKey)) {
                    this.b.onFail(null, Constants.CODE_LOGIC_ILLEGAL_ARGUMENT, "The accessId or accessKey is(are) invalid!@accessId:" + accessId + ", @accessKey:" + accessKey);
                    return;
                }
                p.g(this.a);
                Intent intent = new Intent("com.tencent.android.tpush.action.REGISTER");
                intent.putExtra("accId", Rijndael.encrypt("" + accessId));
                intent.putExtra("accKey", Rijndael.encrypt(accessKey));
                intent.putExtra(Constants.FLAG_PACK_NAME, Rijndael.encrypt(this.a.getPackageName()));
                intent.putExtra("appVer", p.f(this.a));
                if (n.a(this.a) != null) {
                    intent.putExtra("reserved", Rijndael.encrypt(n.a(this.a).a()));
                }
                if (this.e != null) {
                    intent.putExtra("account", Rijndael.encrypt(this.e));
                }
                if (this.f != null) {
                    intent.putExtra(Constants.FLAG_TICKET, Rijndael.encrypt(this.f));
                }
                if (this.g != null) {
                    intent.putExtra("qua", Rijndael.encrypt(this.g));
                }
                intent.putExtra(Constants.FLAG_TICKET_TYPE, this.h);
                intent.putExtra("operation", 100);
                intent.putExtra("aidl", p.b(this.a));
                intent.putExtra("currentTimeMillis", System.currentTimeMillis());
                intent.putExtra("opType", 0);
                boolean a2 = o.a(this.a).a();
                if (p.d(this.a) != 1 || a2) {
                    XGPushManager.a(this.a, intent, this.b, a2);
                } else {
                    XGPushManager.b(this.a, intent, this.b);
                }
            } else if (this.b != null) {
                this.b.onFail(null, a, Constants.errCodeToMsg(a));
            }
        } catch (Throwable th) {
            a.c(XGPushManager.a(), d.I, th);
        }
    }
}
