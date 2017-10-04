package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsUnregisterRsp extends g {
    public byte unregResult = (byte) 0;

    public TpnsUnregisterRsp(byte b) {
        this.unregResult = b;
    }

    public void writeTo(f fVar) {
        fVar.b(this.unregResult, 0);
    }

    public void readFrom(e eVar) {
        this.unregResult = eVar.a(this.unregResult, 0, true);
    }
}
