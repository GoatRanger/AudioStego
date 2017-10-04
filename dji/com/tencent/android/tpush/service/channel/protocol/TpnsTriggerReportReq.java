package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsTriggerReportReq extends g {
    public long timeEnd = 0;
    public long timeStart = 0;

    public TpnsTriggerReportReq(long j, long j2) {
        this.timeStart = j;
        this.timeEnd = j2;
    }

    public void writeTo(f fVar) {
        fVar.a(this.timeStart, 0);
        fVar.a(this.timeEnd, 1);
    }

    public void readFrom(e eVar) {
        this.timeStart = eVar.a(this.timeStart, 0, true);
        this.timeEnd = eVar.a(this.timeEnd, 1, true);
    }
}
