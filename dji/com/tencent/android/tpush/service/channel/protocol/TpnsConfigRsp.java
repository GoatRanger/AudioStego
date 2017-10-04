package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsConfigRsp extends g {
    public String confContent = "";
    public long confVersion = 0;

    public TpnsConfigRsp(long j, String str) {
        this.confVersion = j;
        this.confContent = str;
    }

    public void writeTo(f fVar) {
        fVar.a(this.confVersion, 0);
        fVar.c(this.confContent, 1);
    }

    public void readFrom(e eVar) {
        this.confVersion = eVar.a(this.confVersion, 0, true);
        this.confContent = eVar.a(1, true);
    }
}
