package com.tencent.android.tpush.service.b;

import android.content.Context;
import android.content.Intent;
import com.f.a.a.g;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.l;
import java.util.ArrayList;

class d implements p {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ Context b;
    final /* synthetic */ Intent c;
    final /* synthetic */ a d;

    d(a aVar, ArrayList arrayList, Context context, Intent intent) {
        this.d = aVar;
        this.a = arrayList;
        this.b = context;
        this.c = intent;
    }

    public void a(g gVar, int i, g gVar2, a aVar) {
        com.tencent.android.tpush.service.c.a.c(this.a);
        if (i == 0) {
            this.d.b(l.e(), this.a);
            com.tencent.android.tpush.common.g.a().a(new e(this), 10000);
        } else {
            com.tencent.android.tpush.a.a.h("MessageManager", ">> msg ckicled ack failed responseCode=" + i);
        }
        a.g = false;
    }

    public void a(g gVar, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.h("MessageManager", "### msg ack onMessageSendFailed  responseCode=" + channelException.errorCode);
        a.g = false;
    }

    public void a(g gVar, a aVar) {
        a.g = false;
    }
}
