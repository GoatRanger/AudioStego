package com.amap.api.mapcore.util;

import java.util.HashMap;
import java.util.Map;

@el(a = "file")
public class fd {
    @em(a = "filename", b = 6)
    private String a;
    @em(a = "md5", b = 6)
    private String b;
    @em(a = "sdkname", b = 6)
    private String c;
    @em(a = "version", b = 6)
    private String d;
    @em(a = "dynamicversion", b = 6)
    private String e;
    @em(a = "status", b = 6)
    private String f;

    public static class a {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f = "copy";

        public a(String str, String str2, String str3, String str4, String str5) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
        }

        public a a(String str) {
            this.f = str;
            return this;
        }

        public fd a() {
            return new fd();
        }
    }

    private fd(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
    }

    private fd() {
    }

    public static String a(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("sdkname", str);
        hashMap.put("dynamicversion", str2);
        return ek.a(hashMap);
    }

    public static String b(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("sdkname", str);
        hashMap.put("status", str2);
        return ek.a(hashMap);
    }

    public static String a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("sdkname", str);
        return ek.a(hashMap);
    }

    public static String b(String str) {
        Map hashMap = new HashMap();
        hashMap.put("filename", str);
        return ek.a(hashMap);
    }

    public static String a(String str, String str2, String str3, String str4) {
        Map hashMap = new HashMap();
        hashMap.put("filename", str);
        hashMap.put("sdkname", str2);
        hashMap.put("dynamicversion", str4);
        hashMap.put("version", str3);
        return ek.a(hashMap);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }

    public void c(String str) {
        this.f = str;
    }
}
