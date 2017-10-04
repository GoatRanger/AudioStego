package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class ApList extends g {
    static ArrayList cache_portList;
    static Map cache_primary;
    static Map cache_secondary;
    static ArrayList cache_speedTestIpList;
    public long backup = 0;
    public String domain = "";
    public ArrayList portList = null;
    public Map primary = null;
    public Map secondary = null;
    public ArrayList speedTestIpList = null;

    public ApList(Map map, Map map2, long j, String str, ArrayList arrayList, ArrayList arrayList2) {
        this.primary = map;
        this.secondary = map2;
        this.backup = j;
        this.domain = str;
        this.portList = arrayList;
        this.speedTestIpList = arrayList2;
    }

    public void writeTo(f fVar) {
        fVar.a(this.primary, 0);
        fVar.a(this.secondary, 1);
        fVar.a(this.backup, 2);
        fVar.c(this.domain, 3);
        fVar.a(this.portList, 4);
        fVar.a(this.speedTestIpList, 5);
    }

    public void readFrom(e eVar) {
        if (cache_primary == null) {
            cache_primary = new HashMap();
            cache_primary.put(Byte.valueOf((byte) 0), Long.valueOf(0));
        }
        this.primary = (Map) eVar.a(cache_primary, 0, true);
        if (cache_secondary == null) {
            cache_secondary = new HashMap();
            cache_secondary.put(Byte.valueOf((byte) 0), Long.valueOf(0));
        }
        this.secondary = (Map) eVar.a(cache_secondary, 1, true);
        this.backup = eVar.a(this.backup, 2, true);
        this.domain = eVar.a(3, true);
        if (cache_portList == null) {
            cache_portList = new ArrayList();
            cache_portList.add(Integer.valueOf(0));
        }
        this.portList = (ArrayList) eVar.a(cache_portList, 4, true);
        if (cache_speedTestIpList == null) {
            cache_speedTestIpList = new ArrayList();
            cache_speedTestIpList.add(Long.valueOf(0));
        }
        this.speedTestIpList = (ArrayList) eVar.a(cache_speedTestIpList, 5, true);
    }
}
