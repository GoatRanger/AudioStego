package com.dji.a;

import android.content.Context;
import java.io.Serializable;
import java.util.HashMap;

public class b implements Serializable {
    public boolean a = false;
    private String b;
    private String c;
    private HashMap<String, String> d;

    public enum a {
        INIT_DATA("init_data"),
        LOG_DATA("log_data");
        
        private String c;

        private a(String str) {
            this.c = str;
        }

        public String a() {
            return this.c;
        }
    }

    public String a() {
        return "http://statistical-report.djiservice.org/api/report/clientContext";
    }

    public synchronized void a(String str, String str2) {
        if (this.d == null) {
            this.d = new HashMap();
        }
        this.d.put(str, str2);
    }

    public HashMap<String, String> b() {
        return this.d;
    }

    public String c() {
        return "http://statistical-report.djiservice.org/api/report/clientUUID";
    }

    public HashMap<String, String> d() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("ostype", "android");
        return hashMap;
    }

    public int e() {
        return 300000;
    }

    public int f() {
        return 5;
    }

    public void a(String str) {
        this.b = str;
    }

    public String g() {
        return this.b;
    }

    public void b(String str) {
        this.c = str;
    }

    public String h() {
        return this.c;
    }

    public Context i() {
        return a.d;
    }

    public int j() {
        if (a.b) {
            return 500;
        }
        return 60000;
    }
}
