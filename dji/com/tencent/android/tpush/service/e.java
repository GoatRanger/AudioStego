package com.tencent.android.tpush.service;

import com.f.a.a.g;
import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;

class e implements p {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public void a(g gVar, int i, g gVar2, a aVar) {
        if (i == 0) {
            long j = this.a.b + 86400000;
            try {
                com.tencent.android.tpush.service.d.e.b(this.a.a, "com.tencent.android.tpush.action.next.applist.ts", j);
                m.b(this.a.a, "com.tencent.android.tpush.action.next.applist.ts", j);
            } catch (Throwable th) {
            }
        }
    }

    public void a(g gVar, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.h(a.a, ">>> reportReq onMessageSendFailed(" + gVar + ", " + channelException + ", " + aVar + ")");
    }

    public void a(g gVar, a aVar) {
        com.tencent.android.tpush.a.a.h(a.a, ">>> reportReq onMessageDiscarded(" + gVar + ", " + aVar + ")");
    }
}
