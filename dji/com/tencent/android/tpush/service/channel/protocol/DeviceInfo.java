package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class DeviceInfo extends g {
    public String apiLevel = "";
    public String appList = "";
    public String cpuInfo = "";
    public String imei = "";
    public long isRooted = 0;
    public String language = "";
    public String launcherName = "";
    public String manu = "";
    public String model = "";
    public String network = "";
    public String os = "";
    public String resolution = "";
    public String sdCard = "";
    public String sdDouble = "";
    public String sdkVersion = "";
    public String sdkVersionName = "";
    public String timezone = "";
    public String xgAppList = "";

    public DeviceInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, long j, String str12, String str13, String str14, String str15, String str16, String str17) {
        this.imei = str;
        this.model = str2;
        this.os = str3;
        this.network = str4;
        this.sdCard = str5;
        this.sdDouble = str6;
        this.resolution = str7;
        this.manu = str8;
        this.apiLevel = str9;
        this.sdkVersion = str10;
        this.sdkVersionName = str11;
        this.isRooted = j;
        this.appList = str12;
        this.cpuInfo = str13;
        this.language = str14;
        this.timezone = str15;
        this.launcherName = str16;
        this.xgAppList = str17;
    }

    public void writeTo(f fVar) {
        if (this.imei != null) {
            fVar.c(this.imei, 0);
        }
        if (this.model != null) {
            fVar.c(this.model, 1);
        }
        if (this.os != null) {
            fVar.c(this.os, 2);
        }
        if (this.network != null) {
            fVar.c(this.network, 3);
        }
        if (this.sdCard != null) {
            fVar.c(this.sdCard, 4);
        }
        if (this.sdDouble != null) {
            fVar.c(this.sdDouble, 5);
        }
        if (this.resolution != null) {
            fVar.c(this.resolution, 6);
        }
        if (this.manu != null) {
            fVar.c(this.manu, 7);
        }
        if (this.apiLevel != null) {
            fVar.c(this.apiLevel, 8);
        }
        if (this.sdkVersion != null) {
            fVar.c(this.sdkVersion, 9);
        }
        if (this.sdkVersionName != null) {
            fVar.c(this.sdkVersionName, 10);
        }
        fVar.a(this.isRooted, 11);
        if (this.appList != null) {
            fVar.c(this.appList, 12);
        }
        if (this.cpuInfo != null) {
            fVar.c(this.cpuInfo, 13);
        }
        if (this.language != null) {
            fVar.c(this.language, 14);
        }
        if (this.timezone != null) {
            fVar.c(this.timezone, 15);
        }
        if (this.launcherName != null) {
            fVar.c(this.launcherName, 16);
        }
        if (this.xgAppList != null) {
            fVar.c(this.xgAppList, 17);
        }
    }

    public void readFrom(e eVar) {
        this.imei = eVar.a(0, false);
        this.model = eVar.a(1, false);
        this.os = eVar.a(2, false);
        this.network = eVar.a(3, false);
        this.sdCard = eVar.a(4, false);
        this.sdDouble = eVar.a(5, false);
        this.resolution = eVar.a(6, false);
        this.manu = eVar.a(7, false);
        this.apiLevel = eVar.a(8, false);
        this.sdkVersion = eVar.a(9, false);
        this.sdkVersionName = eVar.a(10, false);
        this.isRooted = eVar.a(this.isRooted, 11, false);
        this.appList = eVar.a(12, false);
        this.cpuInfo = eVar.a(13, false);
        this.language = eVar.a(14, false);
        this.timezone = eVar.a(15, false);
        this.launcherName = eVar.a(16, false);
        this.xgAppList = eVar.a(17, false);
    }
}
