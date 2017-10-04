package com.e;

import java.util.HashMap;
import java.util.Map;

@g(a = "file")
public class y {
    @h(a = "filename", b = 6)
    private String a;
    @h(a = "md5", b = 6)
    private String b;
    @h(a = "sdkname", b = 6)
    private String c;
    @h(a = "version", b = 6)
    private String d;
    @h(a = "dynamicversion", b = 6)
    private String e;
    @h(a = "status", b = 6)
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

        public y a() {
            return new y();
        }
    }

    private y() {
    }

    private y(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
    }

    public static String a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("sdkname", str);
        return f.a(hashMap);
    }

    public static String a(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("sdkname", str);
        hashMap.put("dynamicversion", str2);
        return f.a(hashMap);
    }

    public static String a(String str, String str2, String str3, String str4) {
        Map hashMap = new HashMap();
        hashMap.put("filename", str);
        hashMap.put("sdkname", str2);
        hashMap.put("dynamicversion", str4);
        hashMap.put("version", str3);
        return f.a(hashMap);
    }

    public static String b(String str) {
        Map hashMap = new HashMap();
        hashMap.put("filename", str);
        return f.a(hashMap);
    }

    public static String b(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("sdkname", str);
        hashMap.put("status", str2);
        return f.a(hashMap);
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

    public void c(String str) {
        this.f = str;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }
}
