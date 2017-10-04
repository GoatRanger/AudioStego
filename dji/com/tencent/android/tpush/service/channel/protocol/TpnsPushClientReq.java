package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;
import java.util.ArrayList;

public final class TpnsPushClientReq extends g {
    static ArrayList cache_msgList;
    public ArrayList msgList = null;
    public long timeUs = 0;

    public TpnsPushClientReq(ArrayList arrayList, long j) {
        this.msgList = arrayList;
        this.timeUs = j;
    }

    public void writeTo(f fVar) {
        fVar.a(this.msgList, 0);
        fVar.a(this.timeUs, 1);
    }

    public void readFrom(e eVar) {
        if (cache_msgList == null) {
            cache_msgList = new ArrayList();
            cache_msgList.add(new TpnsPushMsg());
        }
        this.msgList = (ArrayList) eVar.a(cache_msgList, 0, true);
        this.timeUs = eVar.a(this.timeUs, 1, true);
    }
}
