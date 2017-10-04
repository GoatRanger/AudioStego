package com.amap.api.mapcore.util;

@el(a = "update_item_file")
class bu {
    @em(a = "mAdcode", b = 6)
    private String a = "";
    @em(a = "file", b = 6)
    private String b = "";

    public bu(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String a() {
        return this.b;
    }

    public static String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mAdcode");
        stringBuilder.append("='");
        stringBuilder.append(str);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }

    public static String b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mAdcode");
        stringBuilder.append("='");
        stringBuilder.append(str);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }
}
