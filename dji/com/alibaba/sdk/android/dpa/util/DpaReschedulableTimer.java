package com.alibaba.sdk.android.dpa.util;

import java.util.Timer;
import java.util.TimerTask;

public class DpaReschedulableTimer extends Timer {
    private Runnable task;
    private Timer timer = new Timer();
    private TimerTask timerTask;

    public void schedule(Runnable runnable, long j) {
        if (this.task != null) {
            reschedule(j);
            return;
        }
        this.task = runnable;
        this.timerTask = new TimerTask() {
            public void run() {
                DpaReschedulableTimer.this.task.run();
            }
        };
        this.timer.schedule(this.timerTask, j);
    }

    public void reschedule(long j) {
        this.timerTask.cancel();
        this.timerTask = new TimerTask() {
            public void run() {
                DpaReschedulableTimer.this.task.run();
            }
        };
        this.timer.schedule(this.timerTask, j);
    }
}
