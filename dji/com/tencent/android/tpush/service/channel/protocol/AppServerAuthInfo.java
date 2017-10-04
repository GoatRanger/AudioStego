package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class AppServerAuthInfo extends g {
    public long accessId = 0;
    public String secretKey = "";

    public AppServerAuthInfo(long j, String str) {
        this.accessId = j;
        this.secretKey = str;
    }

    public void writeTo(f fVar) {
        fVar.a(this.accessId, 0);
        fVar.c(this.secretKey, 1);
    }

    public void readFrom(e eVar) {
        this.accessId = eVar.a(this.accessId, 0, true);
        this.secretKey = eVar.a(1, true);
    }
}
