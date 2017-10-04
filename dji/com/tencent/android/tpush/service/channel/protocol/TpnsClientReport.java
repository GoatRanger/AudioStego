package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsClientReport extends g {
    static int cache_commandId;
    public long acceptTime = 0;
    public long accip = 0;
    public byte apn = (byte) 0;
    public byte available = (byte) 0;
    public int commandId = 0;
    public long connectTime = 0;
    public String domain = "";
    public long downstreamTime = 0;
    public byte isp = (byte) 0;
    public String lbs = "";
    public byte pack = (byte) 0;
    public int port = 0;
    public String qua = "";
    public byte result = (byte) 0;
    public long resultCode = 0;
    public String signal = "";
    public long tmcost = 0;
    public long upstreamTime = 0;

    public TpnsClientReport(int i, byte b, int i2, long j, byte b2, byte b3, byte b4, long j2, byte b5, long j3, String str, String str2, long j4, long j5, long j6, long j7, String str3, String str4) {
        this.commandId = i;
        this.isp = b;
        this.port = i2;
        this.accip = j;
        this.apn = b2;
        this.pack = b3;
        this.available = b4;
        this.tmcost = j2;
        this.result = b5;
        this.resultCode = j3;
        this.domain = str;
        this.qua = str2;
        this.connectTime = j4;
        this.upstreamTime = j5;
        this.downstreamTime = j6;
        this.acceptTime = j7;
        this.signal = str3;
        this.lbs = str4;
    }

    public void writeTo(f fVar) {
        fVar.a(this.commandId, 0);
        fVar.b(this.isp, 1);
        fVar.a(this.port, 2);
        fVar.a(this.accip, 3);
        fVar.b(this.apn, 4);
        fVar.b(this.pack, 5);
        fVar.b(this.available, 6);
        fVar.a(this.tmcost, 7);
        fVar.b(this.result, 8);
        fVar.a(this.resultCode, 9);
        if (this.domain != null) {
            fVar.c(this.domain, 10);
        }
        if (this.qua != null) {
            fVar.c(this.qua, 11);
        }
        fVar.a(this.connectTime, 12);
        fVar.a(this.upstreamTime, 13);
        fVar.a(this.downstreamTime, 14);
        fVar.a(this.acceptTime, 15);
        if (this.signal != null) {
            fVar.c(this.signal, 16);
        }
        if (this.lbs != null) {
            fVar.c(this.lbs, 17);
        }
    }

    public void readFrom(e eVar) {
        this.commandId = eVar.a(this.commandId, 0, false);
        this.isp = eVar.a(this.isp, 1, false);
        this.port = eVar.a(this.port, 2, false);
        this.accip = eVar.a(this.accip, 3, false);
        this.apn = eVar.a(this.apn, 4, false);
        this.pack = eVar.a(this.pack, 5, false);
        this.available = eVar.a(this.available, 6, false);
        this.tmcost = eVar.a(this.tmcost, 7, false);
        this.result = eVar.a(this.result, 8, false);
        this.resultCode = eVar.a(this.resultCode, 9, false);
        this.domain = eVar.a(10, false);
        this.qua = eVar.a(11, false);
        this.connectTime = eVar.a(this.connectTime, 12, false);
        this.upstreamTime = eVar.a(this.upstreamTime, 13, false);
        this.downstreamTime = eVar.a(this.downstreamTime, 14, false);
        this.acceptTime = eVar.a(this.acceptTime, 15, false);
        this.signal = eVar.a(16, false);
        this.lbs = eVar.a(17, false);
    }
}
