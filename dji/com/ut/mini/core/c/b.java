package com.ut.mini.core.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.NotificationManagerCompat;
import com.here.odnp.config.OdnpConfigStatic;
import com.ut.mini.a.a;
import com.ut.mini.d.c;
import com.ut.mini.d.i;
import com.ut.mini.d.m;
import com.ut.mini.d.n;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

public class b {
    private static Random b = new Random();
    private Context a = null;
    private SharedPreferences c = null;
    private Editor d;
    private int e;
    private SortedSet<String> f;
    private final Timer g = new Timer("save_to_storage_after_log_exceed", true);
    private volatile boolean h;

    private b() {
    }

    private synchronized void b(Context context) {
        this.a = context;
        this.c = this.a.getSharedPreferences(i.a(this.a, "UTMCLog"), 0);
        this.d = this.c.edit();
        Map all = this.c.getAll();
        this.e = all.size();
        this.f = new TreeSet(all.keySet());
    }

    public static b a(Context context) {
        if (context == null) {
            return null;
        }
        b bVar = new b();
        bVar.b(context);
        return bVar;
    }

    private static String a(String str) {
        if (n.a(str)) {
            str = "3";
        }
        return str + System.currentTimeMillis() + ("" + (b.nextInt(99999) + 100000));
    }

    public synchronized String a(String str, String str2) {
        String a;
        a = a(str2);
        try {
            this.d.putString(a, new String(c.c(a.a(str.getBytes(), com.ut.mini.base.a.b()), 2), "UTF-8"));
            com.ut.mini.b.a.b(1, "cache_log", str);
            this.f.add(a);
            this.e++;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int i = this.e + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
        if (i > 0) {
            com.ut.mini.b.a.b(2, "cacheLog[cache-full]", "start clear log, diff = " + i);
            a(i);
        }
        return a;
    }

    private synchronized void a(int i) {
        while (i > 0) {
            if (this.f.isEmpty()) {
                break;
            }
            String str = (String) this.f.first();
            if (this.f.remove(str)) {
                this.d.remove(str);
                this.e--;
            }
            i--;
        }
        if (!this.h) {
            this.g.schedule(new TimerTask(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.b();
                    this.a.h = false;
                }
            }, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
            this.h = true;
        }
    }

    public synchronized void a(List<String> list) {
        for (String str : list) {
            if (this.f.remove(str)) {
                this.d.remove(str);
                this.e--;
            }
        }
    }

    public synchronized Map<String, Object> a() {
        return this.c.getAll();
    }

    public synchronized void b() {
        m.a(this.d);
        if (com.ut.mini.b.a.a()) {
            com.ut.mini.b.a.b(2, "saveToStorage", "commit to storage");
        }
    }
}
