package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsRedirectReq extends g {
    public byte network = (byte) 0;
    public byte op = (byte) 0;

    public TpnsRedirectReq(byte b, byte b2) {
        this.network = b;
        this.op = b2;
    }

    public void writeTo(f fVar) {
        fVar.b(this.network, 0);
        fVar.b(this.op, 1);
    }

    public void readFrom(e eVar) {
        this.network = eVar.a(this.network, 0, false);
        this.op = eVar.a(this.op, 1, false);
    }
}
