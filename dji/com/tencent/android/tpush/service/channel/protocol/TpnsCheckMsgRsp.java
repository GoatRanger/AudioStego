package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsCheckMsgRsp extends g {
    public short result = (short) 0;

    public TpnsCheckMsgRsp(short s) {
        this.result = s;
    }

    public void writeTo(f fVar) {
        fVar.a(this.result, 0);
    }

    public void readFrom(e eVar) {
        this.result = eVar.a(this.result, 0, true);
    }
}
