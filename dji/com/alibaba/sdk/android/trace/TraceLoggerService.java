package com.alibaba.sdk.android.trace;

public interface TraceLoggerService {
    public static final int LEVEL_ALL = 7;
    public static final int LEVEL_DEV = 3;
    public static final int LEVEL_ISV_DEV = 2;
    public static final int LEVEL_ISV_DEV_ONLINE = 6;
    public static final int LEVEL_ONLINE = 4;
    public static final int LEVEL_SDK_DEV = 1;
    public static final int LEVEL_SDK_DEV_ONLINE = 5;
    public static final int LEVEL_TRACK = 8;
    public static final String TAG = "AliSDK";

    ActionTraceLogger action(int i, String str);

    ActionTraceLogger action(String str);
}
