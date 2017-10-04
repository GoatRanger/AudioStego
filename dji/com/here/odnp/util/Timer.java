package com.here.odnp.util;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

public class Timer {
    private Handler mHandler;
    private final Object mToken = new Object();

    public abstract class Task implements Runnable {
        public void beforeSchedule() {
        }

        public void cancel() {
            if (Timer.this.mHandler == null) {
                throw new IllegalStateException("Timer start not called");
            }
            Timer.this.mHandler.removeCallbacks(this);
        }
    }

    public synchronized void start() {
        if (this.mHandler == null) {
            this.mHandler = new Handler(Looper.getMainLooper());
        }
    }

    public void stop() {
        cancel();
        this.mHandler = null;
    }

    public void schedule(Task task, long j) {
        task.beforeSchedule();
        this.mHandler.postAtTime(task, this.mToken, SystemClock.uptimeMillis() + j);
    }

    public void cancel() {
        this.mHandler.removeCallbacksAndMessages(this.mToken);
    }
}
