package com.tencent.android.tpush.stat;

public enum StatReportStrategy {
    INSTANT(1),
    ONLY_WIFI(2),
    BATCH(3),
    APP_LAUNCH(4),
    DEVELOPER(5),
    PERIOD(6),
    ONLY_WIFI_NO_CACHE(7);
    
    int v;

    private StatReportStrategy(int i) {
        this.v = i;
    }

    public int a() {
        return this.v;
    }

    public static StatReportStrategy a(int i) {
        for (StatReportStrategy statReportStrategy : values()) {
            if (i == statReportStrategy.a()) {
                return statReportStrategy;
            }
        }
        return null;
    }
}
