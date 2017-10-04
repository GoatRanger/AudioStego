package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsPushMsg extends g {
    public long accessId = 0;
    public String appPkgName = "";
    public long busiMsgId = 0;
    public String content = "";
    public String date = "";
    public long msgId = 0;
    public long multiPkg = 0;
    public long serverTime = 0;
    public long timestamp = 0;
    public String title = "";
    public int ttl = 0;
    public long type = 0;

    public TpnsPushMsg(long j, long j2, long j3, String str, String str2, long j4, String str3, long j5, long j6, String str4, long j7, int i) {
        this.msgId = j;
        this.accessId = j2;
        this.busiMsgId = j3;
        this.title = str;
        this.content = str2;
        this.type = j4;
        this.appPkgName = str3;
        this.timestamp = j5;
        this.multiPkg = j6;
        this.date = str4;
        this.serverTime = j7;
        this.ttl = i;
    }

    public void writeTo(f fVar) {
        fVar.a(this.msgId, 0);
        fVar.a(this.accessId, 1);
        fVar.a(this.busiMsgId, 2);
        fVar.c(this.title, 3);
        fVar.c(this.content, 4);
        fVar.a(this.type, 5);
        if (this.appPkgName != null) {
            fVar.c(this.appPkgName, 6);
        }
        fVar.a(this.timestamp, 7);
        fVar.a(this.multiPkg, 8);
        if (this.date != null) {
            fVar.c(this.date, 9);
        }
        fVar.a(this.serverTime, 10);
        fVar.a(this.ttl, 11);
    }

    public void readFrom(e eVar) {
        this.msgId = eVar.a(this.msgId, 0, true);
        this.accessId = eVar.a(this.accessId, 1, true);
        this.busiMsgId = eVar.a(this.busiMsgId, 2, true);
        this.title = eVar.a(3, true);
        this.content = eVar.a(4, true);
        this.type = eVar.a(this.type, 5, true);
        this.appPkgName = eVar.a(6, false);
        this.timestamp = eVar.a(this.timestamp, 7, false);
        this.multiPkg = eVar.a(this.multiPkg, 8, false);
        this.date = eVar.a(9, false);
        this.serverTime = eVar.a(this.serverTime, 10, false);
        this.ttl = eVar.a(this.ttl, 11, false);
    }
}
