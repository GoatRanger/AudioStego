package com.tencent.android.tpush.service;

import android.content.Context;
import com.f.a.a.g;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.b;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterRsp;
import dji.pilot.usercenter.protocol.d;

class f implements p {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ boolean c;
    final /* synthetic */ Context d;
    final /* synthetic */ a e;

    f(a aVar, String str, String str2, boolean z, Context context) {
        this.e = aVar;
        this.a = str;
        this.b = str2;
        this.c = z;
        this.d = context;
    }

    public void a(g gVar, int i, g gVar2, a aVar) {
        b.a().b(true);
        if (i == 0) {
            if (XGPushConfig.enableDebug) {
                com.tencent.android.tpush.a.a.c(a.a, ">> Register [accId = " + this.a + " , packName = " + this.b + " , rsp = " + aVar.c() + d.H);
            }
            this.e.a(i, (TpnsRegisterRsp) gVar2, (TpnsRegisterReq) gVar, aVar, this.b, this.c);
            try {
                this.e.c(this.d);
                return;
            } catch (Throwable th) {
                com.tencent.android.tpush.a.a.c(a.a, "handler app info failed", th);
                return;
            }
        }
        com.tencent.android.tpush.a.a.h(a.a, ">> Register ack fail responseCode = " + i);
        this.e.a(i, "服务器处理失败，返回错误", (TpnsRegisterReq) gVar, aVar, this.b);
    }

    public void a(g gVar, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.i(a.a, "@@ TpnsMessage.IEventListener.onMessageSendFailed " + channelException.errorCode + "," + channelException.getMessage());
        this.e.a(channelException.errorCode, channelException.getMessage(), (TpnsRegisterReq) gVar, aVar, this.b);
    }

    public void a(g gVar, a aVar) {
    }
}
