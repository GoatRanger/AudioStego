package com.tencent.android.tpush.service.b;

import android.content.Context;
import com.f.a.a.g;
import com.here.odnp.config.OdnpConfigStatic;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq;
import com.tencent.android.tpush.service.l;
import java.util.ArrayList;
import java.util.List;

class b implements p {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ Context b;
    final /* synthetic */ a c;

    b(a aVar, ArrayList arrayList, Context context) {
        this.c = aVar;
        this.a = arrayList;
        this.b = context;
    }

    public void a(g gVar, int i, g gVar2, a aVar) {
        a.e = false;
        if (i == 0) {
            com.tencent.android.tpush.a.a.a(6, this.a);
            List list = ((TpnsPushVerifyReq) gVar).msgReportList;
            com.tencent.android.tpush.a.a.a(7, list);
            com.tencent.android.tpush.service.c.a.b(this.a);
            if (list == null || list.size() == 0) {
                com.tencent.android.tpush.a.a.h("MessageManager", "requestAck ack failed with null tReq.msgReportList rsp = " + aVar.c());
            }
            this.c.c(l.e(), list);
            com.tencent.android.tpush.common.g.a().a(2);
            com.tencent.android.tpush.common.g.a().a(new i(this.c, this.b, 2), 2, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
            return;
        }
        com.tencent.android.tpush.a.a.h("MessageManager", ">> msg ack onMessageSendFailed  responseCode=" + i);
    }

    public void a(g gVar, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.h(Constants.ServiceLogTag, "@@ TpnsMessage.IEventListener.onMessageSendFailed " + channelException.errorCode + "," + channelException.getMessage());
        a.e = false;
        com.tencent.android.tpush.a.a.a(8, this.a);
    }

    public void a(g gVar, a aVar) {
        a.e = false;
        com.tencent.android.tpush.a.a.h(Constants.ServiceLogTag, "@@ TpnsMessage.IEventListener.onMessageDiscarded ");
    }
}
