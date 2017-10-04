package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsCheckMsgReq extends g {
    public String token = "";

    public TpnsCheckMsgReq(String str) {
        this.token = str;
    }

    public void writeTo(f fVar) {
        fVar.c(this.token, 0);
    }

    public void readFrom(e eVar) {
        this.token = eVar.a(0, true);
    }
}
