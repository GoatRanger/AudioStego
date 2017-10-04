package com.alibaba.sdk.android.b;

import android.content.Context;
import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.executor.ExecutorService;
import com.alibaba.sdk.android.registry.a.b;
import java.util.concurrent.locks.ReentrantLock;

public final class a {
    public static volatile Context a;
    public static volatile Boolean b;
    public static volatile boolean c;
    public static final ReentrantLock d = new ReentrantLock();
    public static volatile com.alibaba.sdk.android.registry.a e = new com.alibaba.sdk.android.registry.a.a();
    public static ExecutorService f = new com.alibaba.sdk.android.executor.a.a();

    public static Boolean a() {
        try {
            d.lock();
            Boolean bool = b;
            return bool;
        } finally {
            d.unlock();
        }
    }

    public static void b() {
        if (!(e instanceof b) && !Boolean.valueOf(AlibabaSDK.getProperty(SdkConstants.KERNEL_NAME, "disableServiceProxy")).booleanValue()) {
            e = new b(e);
        }
    }
}
