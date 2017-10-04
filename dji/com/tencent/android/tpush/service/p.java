package com.tencent.android.tpush.service;

import com.f.a.a.g;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectRsp;

class p implements com.tencent.android.tpush.service.channel.p {
    final /* synthetic */ o a;

    p(o oVar) {
        this.a = oVar;
    }

    public void a(g gVar, int i, g gVar2, a aVar) {
        if (i == 0) {
            if (gVar != null) {
                com.tencent.android.tpush.a.a.a(7, ((TpnsReconnectReq) gVar).recvMsgList);
                CacheManager.updateUnregUninList(l.e(), ((TpnsReconnectReq) gVar).unregInfoList);
                com.tencent.android.tpush.service.b.a.a().c(l.e(), ((TpnsReconnectReq) gVar).recvMsgList);
                com.tencent.android.tpush.service.b.a.a().b(l.e(), ((TpnsReconnectReq) gVar).msgClickList);
            }
            TpnsReconnectRsp tpnsReconnectRsp = (TpnsReconnectRsp) gVar2;
            if (!(tpnsReconnectRsp == null || tpnsReconnectRsp.appOfflinePushMsgList == null || tpnsReconnectRsp.appOfflinePushMsgList.size() <= 0)) {
                com.tencent.android.tpush.service.b.a.a().a(tpnsReconnectRsp.appOfflinePushMsgList, tpnsReconnectRsp.timeUs, aVar);
            }
            if (tpnsReconnectRsp != null) {
                this.a.a(aVar.b(), tpnsReconnectRsp.confVersion);
                return;
            }
            return;
        }
        com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", ">> reconn failed responseCode=" + i);
    }

    public void a(g gVar, ChannelException channelException, a aVar) {
    }

    public void a(g gVar, a aVar) {
    }
}
