package com.here.odnp.util;

import android.os.Handler;
import android.os.Looper;

public class SafeHandler {
    private static final String TAG = "odnp.util.SafeHandler";
    private final Handler mHandler;

    public SafeHandler(Looper looper) {
        if (looper == null) {
            throw new IllegalArgumentException("looper is null");
        }
        this.mHandler = new Handler(looper);
    }

    public boolean post(Runnable runnable, boolean z) {
        if (runnable == null) {
            return false;
        }
        if (z && isInHandlerThread()) {
            runnable.run();
            return true;
        } else if (isAlive()) {
            return this.mHandler.post(runnable);
        } else {
            return false;
        }
    }

    public boolean post(Runnable runnable) {
        return post(runnable, true);
    }

    public boolean postDelayed(Runnable runnable, long j) {
        if (runnable != null && isAlive()) {
            return this.mHandler.postDelayed(runnable, j);
        }
        return false;
    }

    public boolean postAtTime(Runnable runnable, long j) {
        if (runnable != null && isAlive()) {
            return this.mHandler.postAtTime(runnable, j);
        }
        return false;
    }

    public boolean postAtTime(Runnable runnable, Object obj, long j) {
        if (runnable != null && isAlive()) {
            return this.mHandler.postAtTime(runnable, obj, j);
        }
        return false;
    }

    public void removeCallbacks() {
        removeCallbacks(null);
    }

    public void removeCallbacks(Runnable runnable) {
        this.mHandler.removeCallbacks(runnable);
    }

    public void removeCallbacksAndMessages(Object obj) {
        this.mHandler.removeCallbacksAndMessages(obj);
    }

    public Looper getLooper() {
        return this.mHandler.getLooper();
    }

    private boolean isInHandlerThread() {
        return Thread.currentThread().equals(this.mHandler.getLooper().getThread());
    }

    private boolean isAlive() {
        Looper looper = getLooper();
        if (looper != null) {
            Thread thread = looper.getThread();
            if (thread != null) {
                return thread.isAlive();
            }
        }
        return false;
    }
}
