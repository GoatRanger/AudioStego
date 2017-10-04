package com.tencent.android.tpush.service;

import com.f.a.a.g;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq;
import dji.pilot.usercenter.protocol.d;

class h implements p {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ a c;

    h(a aVar, String str, String str2) {
        this.c = aVar;
        this.a = str;
        this.b = str2;
    }

    public void a(g gVar, int i, g gVar2, a aVar) {
        if (i == 0) {
            com.tencent.android.tpush.a.a.e(a.a, ">> UnRegister ack with [accId = " + this.a + " , packName = " + this.b + " , rsp = " + aVar.c() + d.H);
            this.c.a(i, (TpnsUnregisterReq) gVar, aVar, this.b);
            return;
        }
        com.tencent.android.tpush.a.a.h(a.a, ">> unregeister ack failed responseCode=" + i);
        this.c.a(i, "服务器处理失败，返回错误", (TpnsUnregisterReq) gVar, aVar, this.b);
    }

    public void a(g gVar, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.h(a.a, "@@ unregister onMessageSendFailed " + channelException.errorCode + "," + channelException.getMessage());
        this.c.a(channelException.errorCode, channelException.getMessage(), (TpnsUnregisterReq) gVar, aVar, this.b);
    }

    public void a(g gVar, a aVar) {
    }
}
