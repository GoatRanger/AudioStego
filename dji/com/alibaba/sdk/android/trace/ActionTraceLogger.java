package com.alibaba.sdk.android.trace;

import com.alibaba.sdk.android.util.JSONUtils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ActionTraceLogger {
    private final int a;
    private final String b;
    private final String c;
    private Map<String, Object> d;
    private long e;

    ActionTraceLogger(int i, String str, String str2) {
        this.a = i;
        this.b = str;
        this.c = str2;
    }

    public String getAction() {
        return this.c;
    }

    public ActionTraceLogger info(String str, Object obj) {
        if (this.d == null) {
            this.d = new LinkedHashMap();
        }
        this.d.put(str, obj);
        return this;
    }

    public ActionTraceLogger infos(Map<String, ?> map) {
        if (map != null) {
            if (this.d == null) {
                this.d = new LinkedHashMap(map);
            } else {
                this.d.putAll(map);
            }
        }
        return this;
    }

    public ActionTraceLogger begin() {
        this.e = System.currentTimeMillis();
        AliSDKLogger.d(this.a, this.b, this.c, a());
        this.d = null;
        return this;
    }

    public void done() {
        a(".Done");
    }

    private String a() {
        if (this.d == null || this.d.size() <= 0) {
            return null;
        }
        return JSONUtils.toJson(this.d);
    }

    private void a(boolean z) {
        if (z) {
            a(".Success");
        } else {
            a(".Failed");
        }
    }

    private void a(String str) {
        long j;
        if (this.e > 0) {
            long currentTimeMillis = System.currentTimeMillis() - this.e;
            if (currentTimeMillis > 0) {
                if (this.d == null) {
                    this.d = new HashMap();
                }
                this.d.put("caseTime", Long.valueOf(currentTimeMillis));
                j = currentTimeMillis;
            } else {
                j = 0;
            }
        } else {
            j = 0;
        }
        TraceLoggerManager.INSTANCE.log(this.a, 3, this.b, this.c + str, a());
        if ((this.a & 4) > 0) {
            TraceLoggerManager.INSTANCE.actionCountTrack(this.b, this.c, true, (int) j);
        }
        this.d = null;
    }

    public long getCaseTime() {
        if (this.e > 0) {
            return System.currentTimeMillis() - this.e;
        }
        return 0;
    }

    public void success() {
        a(true);
    }

    public void failed() {
        a(false);
    }

    public void failed(String str, Object obj) {
        info(str, obj);
        failed();
    }

    public void success(String str, Object obj) {
        info(str, obj);
        success();
    }
}
