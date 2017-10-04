package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsTokenTagReq extends g {
    public long accessId = 0;
    public int flag = 0;
    public String tag = "";

    public TpnsTokenTagReq(long j, String str, int i) {
        this.accessId = j;
        this.tag = str;
        this.flag = i;
    }

    public void writeTo(f fVar) {
        fVar.a(this.accessId, 0);
        fVar.c(this.tag, 1);
        fVar.a(this.flag, 2);
    }

    public void readFrom(e eVar) {
        this.accessId = eVar.a(this.accessId, 0, true);
        this.tag = eVar.a(1, true);
        this.flag = eVar.a(this.flag, 2, true);
    }
}
