package com.tencent.android.tpush.service;

import com.f.a.a.g;
import com.tencent.android.tpush.horse.DefaultServer;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.channel.protocol.TpnsGetApListRsp;

class s implements p {
    final /* synthetic */ o a;

    s(o oVar) {
        this.a = oVar;
    }

    public void a(g gVar, int i, g gVar2, a aVar) {
        if (i == 0) {
            DefaultServer.a(((TpnsGetApListRsp) gVar2).apList);
            CacheManager.saveLoadIpTime(l.e(), System.currentTimeMillis());
            return;
        }
        com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", ">> loadIPList fail responseCode=" + i);
    }

    public void a(g gVar, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", "@@ loadIPList.onMessageSendFailed " + channelException.errorCode + "," + channelException.getMessage());
    }

    public void a(g gVar, a aVar) {
    }
}
