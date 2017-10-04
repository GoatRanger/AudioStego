package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsPushClientReport extends g {
    public long accessId = 0;
    public byte ackType = (byte) 0;
    public byte apn = (byte) 0;
    public long broadcastId = 0;
    public long confirmMs = 0;
    public byte isp = (byte) 0;
    public long locip = 0;
    public int locport = 0;
    public long msgId = 0;
    public byte pack = (byte) 0;
    public String qua = "";
    public long receiveTime = 0;
    public String serviceHost = "";
    public long timeUs = 0;
    public long timestamp = 0;
    public long type = 0;

    public TpnsPushClientReport(long j, long j2, byte b, byte b2, byte b3, String str, long j3, int i, String str2, long j4, long j5, long j6, long j7, long j8, long j9, byte b4) {
        this.msgId = j;
        this.accessId = j2;
        this.isp = b;
        this.apn = b2;
        this.pack = b3;
        this.qua = str;
        this.locip = j3;
        this.locport = i;
        this.serviceHost = str2;
        this.timeUs = j4;
        this.confirmMs = j5;
        this.broadcastId = j6;
        this.timestamp = j7;
        this.type = j8;
        this.receiveTime = j9;
        this.ackType = b4;
    }

    public void writeTo(f fVar) {
        fVar.a(this.msgId, 0);
        fVar.a(this.accessId, 1);
        fVar.b(this.isp, 2);
        fVar.b(this.apn, 3);
        fVar.b(this.pack, 4);
        if (this.qua != null) {
            fVar.c(this.qua, 5);
        }
        fVar.a(this.locip, 6);
        fVar.a(this.locport, 7);
        if (this.serviceHost != null) {
            fVar.c(this.serviceHost, 8);
        }
        fVar.a(this.timeUs, 9);
        fVar.a(this.confirmMs, 10);
        fVar.a(this.broadcastId, 11);
        fVar.a(this.timestamp, 12);
        fVar.a(this.type, 13);
        fVar.a(this.receiveTime, 14);
        fVar.b(this.ackType, 15);
    }

    public void readFrom(e eVar) {
        this.msgId = eVar.a(this.msgId, 0, true);
        this.accessId = eVar.a(this.accessId, 1, true);
        this.isp = eVar.a(this.isp, 2, false);
        this.apn = eVar.a(this.apn, 3, false);
        this.pack = eVar.a(this.pack, 4, false);
        this.qua = eVar.a(5, false);
        this.locip = eVar.a(this.locip, 6, false);
        this.locport = eVar.a(this.locport, 7, false);
        this.serviceHost = eVar.a(8, false);
        this.timeUs = eVar.a(this.timeUs, 9, false);
        this.confirmMs = eVar.a(this.confirmMs, 10, false);
        this.broadcastId = eVar.a(this.broadcastId, 11, false);
        this.timestamp = eVar.a(this.timestamp, 12, false);
        this.type = eVar.a(this.type, 13, false);
        this.receiveTime = eVar.a(this.receiveTime, 14, false);
        this.ackType = eVar.a(this.ackType, 15, false);
    }
}
