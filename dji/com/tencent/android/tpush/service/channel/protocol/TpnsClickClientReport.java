package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsClickClientReport extends g {
    public long accessId = 0;
    public long action = 0;
    public long broadcastId = 0;
    public long clickTime = 0;
    public long msgId = 0;
    public long timestamp = 0;
    public long type = 0;

    public TpnsClickClientReport(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        this.msgId = j;
        this.accessId = j2;
        this.broadcastId = j3;
        this.timestamp = j4;
        this.type = j5;
        this.clickTime = j6;
        this.action = j7;
    }

    public void writeTo(f fVar) {
        fVar.a(this.msgId, 0);
        fVar.a(this.accessId, 1);
        fVar.a(this.broadcastId, 2);
        fVar.a(this.timestamp, 3);
        fVar.a(this.type, 4);
        fVar.a(this.clickTime, 5);
        fVar.a(this.action, 6);
    }

    public void readFrom(e eVar) {
        this.msgId = eVar.a(this.msgId, 0, true);
        this.accessId = eVar.a(this.accessId, 1, true);
        this.broadcastId = eVar.a(this.broadcastId, 2, false);
        this.timestamp = eVar.a(this.timestamp, 3, false);
        this.type = eVar.a(this.type, 4, false);
        this.clickTime = eVar.a(this.clickTime, 5, false);
        this.action = eVar.a(this.action, 6, false);
    }
}
