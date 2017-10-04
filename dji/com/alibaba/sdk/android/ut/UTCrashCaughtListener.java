package com.alibaba.sdk.android.ut;

import java.util.Map;

public interface UTCrashCaughtListener {
    Map<String, String> onCrashCaught(Thread thread, Throwable th);
}
