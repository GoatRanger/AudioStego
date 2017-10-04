package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsRedirectRsp extends g {
    public long ip = 0;
    public int port = 0;

    public TpnsRedirectRsp(long j, int i) {
        this.ip = j;
        this.port = i;
    }

    public void writeTo(f fVar) {
        fVar.a(this.ip, 0);
        fVar.a(this.port, 1);
    }

    public void readFrom(e eVar) {
        this.ip = eVar.a(this.ip, 0, false);
        this.port = eVar.a(this.port, 1, false);
    }
}
