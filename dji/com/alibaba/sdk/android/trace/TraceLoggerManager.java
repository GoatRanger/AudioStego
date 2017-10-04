package com.alibaba.sdk.android.trace;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.alibaba.sdk.android.b.a;
import com.alibaba.sdk.android.ut.UserTrackerService;
import com.alibaba.sdk.android.util.TraceHelper;
import com.alipay.sdk.j.f;
import dji.pilot.usercenter.protocol.d;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TraceLoggerManager implements TraceLoggerService {
    public static final TraceLoggerManager INSTANCE = new TraceLoggerManager();
    private int a = 4;
    private boolean b;
    private b c = new b();
    private final Map<String, Map<String, AtomicInteger[]>> d = new LinkedHashMap();
    private final ReadWriteLock e = new ReentrantReadWriteLock();
    private volatile long f;
    private a g;
    private UserTrackerService h;
    private volatile String i = "";

    private TraceLoggerManager() {
    }

    public synchronized void init(boolean z, boolean z2) {
        try {
            String a;
            this.i = a(new StringBuilder(), TraceHelper.clientTTID, a()).toString();
            this.b = z;
            if (z) {
                this.a |= 2;
                if (z2) {
                    this.a |= 1;
                }
            } else {
                this.a = 4;
            }
            b bVar = this.c;
            Context context = a.a;
            bVar.a();
            byte[] bArr = null;
            if ((this.a & 1) == 0) {
                a = a();
                if (a != null) {
                    bArr = a.getBytes();
                }
            }
            a = Environment.getExternalStorageDirectory().getAbsolutePath();
            a(a, bArr);
            if (this.g != null) {
                this.g.b();
            }
            this.g = new a(a + d.t + a.a.getPackageName() + "/asdklog_a", 5242880, bArr);
        } catch (Throwable e) {
            Log.w(TraceLoggerService.TAG, e);
        }
    }

    public void setUserTrackerService(UserTrackerService userTrackerService) {
        this.h = userTrackerService;
    }

    public synchronized void release() {
        a(true);
        this.e.writeLock().lock();
        try {
            this.d.clear();
            this.e.writeLock().unlock();
            if (this.g != null) {
                this.g.a();
            }
        } catch (Throwable th) {
            this.e.writeLock().unlock();
        }
    }

    protected void finalize() throws Throwable {
        release();
        super.finalize();
    }

    public void log(String str, int i, int i2, String str2, String str3, String str4) {
        if (((this.a & 1) & i) > 0 || ((this.a & 2) & i) > 0) {
            a(str, i2, str2, str3, str4);
        }
    }

    public void log(int i, int i2, String str, String str2, String str3) {
        if (i2 == 6 || ((this.a & 1) & i) > 0 || ((this.a & 2) & i) > 0) {
            a(TraceLoggerService.TAG, i2, str, str2, str3);
        }
    }

    private void a(String str, int i, String str2, String str3, String str4) {
        if (this.b) {
            Appendable stringBuilder = new StringBuilder(this.i);
            a(stringBuilder, str2);
            if (str3 != null) {
                stringBuilder.append(str3);
            }
            if (str4 != null) {
                stringBuilder.append(" : ").append(str4);
            }
            Log.println(i, str, stringBuilder.toString());
        }
    }

    private static Appendable a(Appendable appendable, CharSequence... charSequenceArr) {
        try {
            for (CharSequence charSequence : charSequenceArr) {
                appendable.append('[');
                if (charSequence != null) {
                    appendable.append(charSequence);
                }
                appendable.append(']');
            }
        } catch (Throwable e) {
            Log.w(TraceLoggerService.TAG, e);
        }
        return appendable;
    }

    public ActionTraceLogger action(String str) {
        return new ActionTraceLogger(7, "core", str);
    }

    public ActionTraceLogger action(int i, String str) {
        return new ActionTraceLogger(i, "core", str);
    }

    public ActionTraceLogger action(String str, String str2) {
        return new ActionTraceLogger(7, str, str2);
    }

    public ActionTraceLogger action(int i, String str, String str2) {
        return new ActionTraceLogger(i, str, str2);
    }

    public void actionCountTrack(String str, String str2, boolean z, int i) {
        Object obj = null;
        this.e.readLock().lock();
        try {
            Object obj2;
            Map map = (Map) this.d.get(str);
            if (map != null) {
                obj = (AtomicInteger[]) map.get(str2);
            }
            this.e.readLock().unlock();
            if (obj == null) {
                this.e.writeLock().lock();
                try {
                    Map map2;
                    map = (Map) this.d.get(str);
                    if (map == null) {
                        HashMap hashMap = new HashMap();
                        this.d.put(str, hashMap);
                        map2 = hashMap;
                        obj2 = obj;
                    } else {
                        map2 = map;
                        obj2 = (AtomicInteger[]) map.get(str2);
                    }
                    if (obj2 == null) {
                        obj2 = new AtomicInteger[4];
                        for (int i2 = 0; i2 < obj2.length; i2++) {
                            obj2[i2] = new AtomicInteger();
                        }
                        map2.put(str2, obj2);
                    }
                    this.e.writeLock().unlock();
                } catch (Throwable th) {
                    this.e.writeLock().unlock();
                }
            } else {
                obj2 = obj;
            }
            if (z) {
                obj2[0].incrementAndGet();
                obj2[1].addAndGet(i);
            } else {
                obj2[2].incrementAndGet();
                obj2[3].addAndGet(i);
            }
            a(false);
        } catch (Throwable th2) {
            this.e.readLock().unlock();
        }
    }

    public boolean isLogCatDebugEnabled() {
        return this.b;
    }

    private void a(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if ((z || currentTimeMillis - this.f >= 60000) && this.h != null) {
            try {
                this.e.readLock().lock();
                for (Entry entry : this.d.entrySet()) {
                    String str = (String) entry.getKey();
                    for (Entry entry2 : ((Map) entry2.getValue()).entrySet()) {
                        AtomicInteger[] atomicIntegerArr = (AtomicInteger[]) entry2.getValue();
                        if (atomicIntegerArr[0].get() > 0 || atomicIntegerArr[2].get() > 0) {
                            String str2 = (String) entry2.getKey();
                            Map hashMap = new HashMap();
                            hashMap.put("success", String.valueOf(atomicIntegerArr[0]));
                            hashMap.put("successTime", String.valueOf(atomicIntegerArr[1]));
                            hashMap.put(f.b, String.valueOf(atomicIntegerArr[2]));
                            hashMap.put("failedTime", String.valueOf(atomicIntegerArr[3]));
                            this.h.sendCustomHit(str2, 60, str, hashMap);
                            log(1, 3, "trace", "ActionCount", str2);
                        }
                    }
                }
                this.e.readLock().unlock();
                this.e.writeLock().lock();
                this.d.clear();
                this.e.writeLock().unlock();
                this.f = currentTimeMillis;
            } catch (Throwable e) {
                Log.w(TraceLoggerService.TAG, e);
            } catch (Throwable th) {
                this.e.readLock().unlock();
            }
        }
    }

    private synchronized void a(String str, byte[] bArr) {
        a aVar = new a(str + d.t + a.a.getPackageName() + "/asdklog_s", 1048576, bArr);
        for (int i = 0; i < 80; i++) {
            aVar.b("#");
        }
        aVar.a("sys: " + this.i);
        aVar.b();
    }

    private String a() {
        return this.h == null ? null : this.h.getDefaultUserTrackerId();
    }
}
