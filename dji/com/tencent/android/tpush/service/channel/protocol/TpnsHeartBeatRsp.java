package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsHeartBeatRsp extends g {
    public byte padding = (byte) 0;

    public TpnsHeartBeatRsp(byte b) {
        this.padding = b;
    }

    public void writeTo(f fVar) {
        fVar.b(this.padding, 0);
    }

    public void readFrom(e eVar) {
        this.padding = eVar.a(this.padding, 0, true);
    }
}
