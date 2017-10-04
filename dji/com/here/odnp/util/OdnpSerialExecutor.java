package com.here.odnp.util;

import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;

public class OdnpSerialExecutor {
    private static final String TAG = "odnp.util.OdnpSerialExecutor";
    private final Handler mHandler;

    public interface Task {
        boolean run();
    }

    public OdnpSerialExecutor(Looper looper) {
        this.mHandler = new Handler(looper);
    }

    public boolean post(Runnable runnable) {
        return this.mHandler.post(runnable);
    }

    public boolean postAtFrontOfQueue(Runnable runnable) {
        return this.mHandler.postAtFrontOfQueue(runnable);
    }

    public boolean runBlockingTask(final Task task) {
        final ObjectHolder objectHolder = new ObjectHolder(Boolean.valueOf(false));
        if (Thread.currentThread() == this.mHandler.getLooper().getThread()) {
            objectHolder.set(Boolean.valueOf(task.run()));
        } else {
            final ConditionVariable conditionVariable = new ConditionVariable();
            if (this.mHandler.post(new Runnable() {
                public void run() {
                    objectHolder.set(Boolean.valueOf(task.run()));
                    conditionVariable.open();
                }
            })) {
                conditionVariable.block();
            }
        }
        return ((Boolean) objectHolder.get()).booleanValue();
    }

    public void removeCallbacksAndMessages(Object obj) {
        this.mHandler.removeCallbacksAndMessages(obj);
    }
}
