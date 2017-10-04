package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsRegisterRsp extends g {
    public long confVersion = 0;
    public String token = "";

    public TpnsRegisterRsp(long j, String str) {
        this.confVersion = j;
        this.token = str;
    }

    public void writeTo(f fVar) {
        fVar.a(this.confVersion, 0);
        fVar.c(this.token, 1);
    }

    public void readFrom(e eVar) {
        this.confVersion = eVar.a(this.confVersion, 0, true);
        this.token = eVar.a(1, true);
    }
}
