package com.tencent.android.tpush.service.channel;

import com.f.a.a.g;
import com.tencent.android.tpush.service.c.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;

class e implements p {
    final /* synthetic */ b a;

    e(b bVar) {
        this.a = bVar;
    }

    public void a(g gVar, int i, g gVar2, a aVar) {
        if (i == 0) {
            a.a();
            if (b.a >= 3) {
                b.a++;
            } else {
                b.a++;
            }
        }
        com.tencent.android.tpush.a.a.c("TpnsChannel", "heartbeat success rsp = " + aVar.c() + " heartbeattimes = " + b.a);
    }

    public void a(g gVar, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.h("TpnsChannel", "heartbeat failed onMessageSendFailed " + channelException.errorCode + "," + channelException.getMessage());
    }

    public void a(g gVar, a aVar) {
        com.tencent.android.tpush.a.a.h("TpnsChannel", "heartbeat failed TpnsMessage.IEventListener.onMessageDiscarded");
    }
}
