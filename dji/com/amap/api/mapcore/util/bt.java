package com.amap.api.mapcore.util;

@el(a = "update_item_download_info")
class bt {
    @em(a = "mAdcode", b = 6)
    private String a = "";
    @em(a = "fileLength", b = 5)
    private long b = 0;
    @em(a = "splitter", b = 2)
    private int c = 0;
    @em(a = "startPos", b = 5)
    private long d = 0;
    @em(a = "endPos", b = 5)
    private long e = 0;

    public bt(String str, long j, int i, long j2, long j3) {
        this.a = str;
        this.b = j;
        this.c = i;
        this.d = j2;
        this.e = j3;
    }

    public long a(int i) {
        switch (i) {
            case 0:
                return b();
            default:
                return 0;
        }
    }

    public long b(int i) {
        switch (i) {
            case 0:
                return c();
            default:
                return 0;
        }
    }

    public static String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mAdcode");
        stringBuilder.append("='");
        stringBuilder.append(str);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }

    public long a() {
        return this.b;
    }

    public long b() {
        return this.d;
    }

    public long c() {
        return this.e;
    }
}
