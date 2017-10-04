package com.tencent.android.tpush.service;

import android.content.Context;
import com.f.a.a.g;
import com.tencent.android.tpush.service.channel.b;
import com.tencent.android.tpush.service.channel.protocol.TpnsClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsClientReportReq;
import com.tencent.android.tpush.service.d.e;
import java.util.ArrayList;

class d implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ long b;
    final /* synthetic */ a c;

    d(a aVar, Context context, long j) {
        this.c = aVar;
        this.a = context;
        this.b = j;
    }

    public void run() {
        TpnsClientReport tpnsClientReport = new TpnsClientReport();
        tpnsClientReport.commandId = 0;
        tpnsClientReport.signal = e.l(this.a).toString();
        g tpnsClientReportReq = new TpnsClientReportReq();
        tpnsClientReportReq.reportMsgs = new ArrayList();
        tpnsClientReportReq.reportMsgs.add(tpnsClientReport);
        b.a().a(tpnsClientReportReq, new e(this));
    }
}
