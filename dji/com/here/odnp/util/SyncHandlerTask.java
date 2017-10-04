package com.here.odnp.util;

import android.os.ConditionVariable;

public abstract class SyncHandlerTask<T> extends HandlerTask<T> {
    private final ConditionVariable lock = new ConditionVariable();

    public class TimedResult {
        private final T mResult;
        private final boolean mTimedOut;

        TimedResult(T t, boolean z) {
            this.mResult = t;
            this.mTimedOut = z;
        }

        public boolean hasTimedOut() {
            return this.mTimedOut;
        }

        public T getResult() {
            return this.mResult;
        }
    }

    public void run() {
        try {
            super.run();
        } finally {
            this.lock.open();
        }
    }

    public T getResult() {
        this.lock.block();
        return super.getResult();
    }

    public TimedResult getResult(long j) {
        if (this.lock.block(j)) {
            return new TimedResult(super.getResult(), false);
        }
        return new TimedResult(null, true);
    }
}
