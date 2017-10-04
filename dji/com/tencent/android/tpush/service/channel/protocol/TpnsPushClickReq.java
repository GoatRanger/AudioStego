package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;
import java.util.ArrayList;

public final class TpnsPushClickReq extends g {
    static ArrayList cache_msgClickList;
    public ArrayList msgClickList = null;

    public TpnsPushClickReq(ArrayList arrayList) {
        this.msgClickList = arrayList;
    }

    public void writeTo(f fVar) {
        fVar.a(this.msgClickList, 1);
    }

    public void readFrom(e eVar) {
        if (cache_msgClickList == null) {
            cache_msgClickList = new ArrayList();
            cache_msgClickList.add(new TpnsClickClientReport());
        }
        this.msgClickList = (ArrayList) eVar.a(cache_msgClickList, 1, true);
    }
}
