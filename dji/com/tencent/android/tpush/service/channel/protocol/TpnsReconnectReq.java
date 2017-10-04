package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;
import java.util.ArrayList;

public final class TpnsReconnectReq extends g {
    static ArrayList cache_msgClickList;
    static MutableInfo cache_mutableInfo;
    static ArrayList cache_recvMsgList;
    static ArrayList cache_unregInfoList;
    public String deviceId = "";
    public short deviceType = (short) 0;
    public ArrayList msgClickList = null;
    public MutableInfo mutableInfo = null;
    public short networkType = (short) 0;
    public ArrayList recvMsgList = null;
    public String reserved = "";
    public String sdkVersion = "";
    public String token = "";
    public ArrayList unregInfoList = null;

    public TpnsReconnectReq(String str, String str2, short s, ArrayList arrayList, ArrayList arrayList2, MutableInfo mutableInfo, short s2, ArrayList arrayList3, String str3, String str4) {
        this.token = str;
        this.deviceId = str2;
        this.networkType = s;
        this.unregInfoList = arrayList;
        this.recvMsgList = arrayList2;
        this.mutableInfo = mutableInfo;
        this.deviceType = s2;
        this.msgClickList = arrayList3;
        this.sdkVersion = str3;
        this.reserved = str4;
    }

    public void writeTo(f fVar) {
        fVar.c(this.token, 0);
        fVar.c(this.deviceId, 1);
        fVar.a(this.networkType, 2);
        if (this.unregInfoList != null) {
            fVar.a(this.unregInfoList, 3);
        }
        if (this.recvMsgList != null) {
            fVar.a(this.recvMsgList, 4);
        }
        if (this.mutableInfo != null) {
            fVar.a(this.mutableInfo, 5);
        }
        fVar.a(this.deviceType, 6);
        if (this.msgClickList != null) {
            fVar.a(this.msgClickList, 7);
        }
        if (this.sdkVersion != null) {
            fVar.c(this.sdkVersion, 8);
        }
        if (this.reserved != null) {
            fVar.c(this.reserved, 9);
        }
    }

    public void readFrom(e eVar) {
        this.token = eVar.a(0, true);
        this.deviceId = eVar.a(1, true);
        this.networkType = eVar.a(this.networkType, 2, true);
        if (cache_unregInfoList == null) {
            cache_unregInfoList = new ArrayList();
            cache_unregInfoList.add(new UnregInfo());
        }
        this.unregInfoList = (ArrayList) eVar.a(cache_unregInfoList, 3, false);
        if (cache_recvMsgList == null) {
            cache_recvMsgList = new ArrayList();
            cache_recvMsgList.add(new TpnsPushClientReport());
        }
        this.recvMsgList = (ArrayList) eVar.a(cache_recvMsgList, 4, false);
        if (cache_mutableInfo == null) {
            cache_mutableInfo = new MutableInfo();
        }
        this.mutableInfo = (MutableInfo) eVar.b(cache_mutableInfo, 5, false);
        this.deviceType = eVar.a(this.deviceType, 6, false);
        if (cache_msgClickList == null) {
            cache_msgClickList = new ArrayList();
            cache_msgClickList.add(new TpnsClickClientReport());
        }
        this.msgClickList = (ArrayList) eVar.a(cache_msgClickList, 7, false);
        this.sdkVersion = eVar.a(8, false);
        this.reserved = eVar.a(9, false);
    }
}
