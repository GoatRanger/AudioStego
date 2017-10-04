package com.tencent.android.tpush.service;

import com.f.a.a.g;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.channel.protocol.TpnsConfigRsp;

class q implements p {
    final /* synthetic */ o a;

    q(o oVar) {
        this.a = oVar;
    }

    public void a(g gVar, int i, g gVar2, a aVar) {
        if (i == 0) {
            com.tencent.android.tpush.service.a.a.a(((TpnsConfigRsp) gVar2).confContent);
            return;
        }
        com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", ">> loadConfig fail responseCode=" + i);
        this.a.a(i, "", aVar);
    }

    public void a(g gVar, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", "@@ loadConfiguration.onMessageSendFailed " + channelException.errorCode + "," + channelException.getMessage());
        this.a.a(channelException.errorCode, channelException.getMessage(), aVar);
    }

    public void a(g gVar, a aVar) {
    }
}
