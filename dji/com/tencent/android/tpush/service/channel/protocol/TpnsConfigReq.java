package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsConfigReq extends g {
    public long confVersion = 0;

    public TpnsConfigReq(long j) {
        this.confVersion = j;
    }

    public void writeTo(f fVar) {
        fVar.a(this.confVersion, 0);
    }

    public void readFrom(e eVar) {
        this.confVersion = eVar.a(this.confVersion, 0, true);
    }
}
