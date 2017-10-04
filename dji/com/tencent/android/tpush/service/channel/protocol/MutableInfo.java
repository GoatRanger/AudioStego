package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class MutableInfo extends g {
    public String bssid = "";
    public String mac = "";
    public String ssid = "";
    public String wflist = "";

    public MutableInfo(String str, String str2, String str3, String str4) {
        this.ssid = str;
        this.bssid = str2;
        this.mac = str3;
        this.wflist = str4;
    }

    public void writeTo(f fVar) {
        if (this.ssid != null) {
            fVar.c(this.ssid, 0);
        }
        if (this.bssid != null) {
            fVar.c(this.bssid, 1);
        }
        if (this.mac != null) {
            fVar.c(this.mac, 2);
        }
        if (this.wflist != null) {
            fVar.c(this.wflist, 3);
        }
    }

    public void readFrom(e eVar) {
        this.ssid = eVar.a(0, false);
        this.bssid = eVar.a(1, false);
        this.mac = eVar.a(2, false);
        this.wflist = eVar.a(3, false);
    }
}
