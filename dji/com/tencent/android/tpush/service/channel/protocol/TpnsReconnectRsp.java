package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;
import java.util.ArrayList;

public final class TpnsReconnectRsp extends g {
    static ArrayList cache_appOfflinePushMsgList;
    public ArrayList appOfflinePushMsgList = null;
    public long confVersion = 0;
    public long timeUs = 0;

    public TpnsReconnectRsp(long j, ArrayList arrayList, long j2) {
        this.confVersion = j;
        this.appOfflinePushMsgList = arrayList;
        this.timeUs = j2;
    }

    public void writeTo(f fVar) {
        fVar.a(this.confVersion, 0);
        if (this.appOfflinePushMsgList != null) {
            fVar.a(this.appOfflinePushMsgList, 1);
        }
        fVar.a(this.timeUs, 2);
    }

    public void readFrom(e eVar) {
        this.confVersion = eVar.a(this.confVersion, 0, true);
        if (cache_appOfflinePushMsgList == null) {
            cache_appOfflinePushMsgList = new ArrayList();
            cache_appOfflinePushMsgList.add(new TpnsPushMsg());
        }
        this.appOfflinePushMsgList = (ArrayList) eVar.a(cache_appOfflinePushMsgList, 1, false);
        this.timeUs = eVar.a(this.timeUs, 2, false);
    }
}
