package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class NetworkInfo extends g {
    public int ip = 0;
    public byte network = (byte) 0;
    public byte op = (byte) 0;

    public NetworkInfo(int i, byte b, byte b2) {
        this.ip = i;
        this.network = b;
        this.op = b2;
    }

    public void writeTo(f fVar) {
        fVar.a(this.ip, 0);
        fVar.b(this.network, 1);
        fVar.b(this.op, 2);
    }

    public void readFrom(e eVar) {
        this.ip = eVar.a(this.ip, 0, true);
        this.network = eVar.a(this.network, 1, true);
        this.op = eVar.a(this.op, 2, false);
    }
}
