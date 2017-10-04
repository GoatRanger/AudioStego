package com.here.odnp.util;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import com.here.odnp.config.OdnpConfigStatic;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class AlarmTimer {
    private static final String ACTION_ALARM_FORMAT = "com.here.odnp.util.Alarm.%x";
    private static final long IMMEDIATE_ALARM_WAKELOCK_TIMEOUT = 1000;
    private static final String TAG = "odnp.util.AlarmTimer";
    private static final String TAG_IMMEDIATE_ALARM_WAKELOCK = "com.here.odnp.AlarmTimer";
    private final String mAction;
    private final AlarmManager mAlarmManager;
    private final Context mContext;
    private final Handler mHandler;
    private final PendingIntent mPendingIntent;
    private final Scheduler mScheduler;
    private boolean mStarted;
    private final WakeLock mWakeLock;

    public abstract class Task implements Runnable {
        public void beforeSchedule() {
        }

        public void cancel() {
            AlarmTimer.this.mScheduler.unschedule(this);
        }

        public boolean isDueBefore(long j) {
            return AlarmTimer.this.mScheduler.isDueBefore(this, j);
        }
    }

    class Scheduler extends BroadcastReceiver {
        private long mNextTriggerAtTime;
        private long mNextTriggerEndTime;
        private final PriorityQueue<Entry> mQueue = new PriorityQueue();

        class Entry implements Comparable<Entry>, Runnable {
            private final Task mTask;
            private final long mTolerance;
            private final long mTriggerAtTime;

            Entry(Task task, long j, long j2) {
                if (task == null) {
                    throw new IllegalArgumentException("task is null");
                } else if (j < 0) {
                    throw new IllegalArgumentException("delay is negative");
                } else if (j2 < 0) {
                    throw new IllegalArgumentException("tolerance is negative");
                } else {
                    this.mTask = task;
                    this.mTriggerAtTime = SystemClock.elapsedRealtime() + j;
                    this.mTolerance = j2;
                }
            }

            void execute() {
                this.mTask.run();
            }

            boolean hasTriggered() {
                return this.mTriggerAtTime <= SystemClock.elapsedRealtime();
            }

            long getTriggerAtBegin() {
                return this.mTriggerAtTime;
            }

            long getTriggerAtEnd() {
                return this.mTriggerAtTime + this.mTolerance;
            }

            long getTolerance() {
                return this.mTolerance;
            }

            private void removeFromHandler() {
                AlarmTimer.this.mHandler.removeCallbacks(this);
            }

            public int compareTo(Entry entry) {
                long j = this.mTriggerAtTime - entry.mTriggerAtTime;
                if (j < 0) {
                    return -1;
                }
                return j > 0 ? 1 : 0;
            }

            public void run() {
                AlarmTimer.this.mWakeLock.acquire();
                try {
                    Scheduler.this.onAlarmTriggered();
                } finally {
                    AlarmTimer.this.mWakeLock.release();
                }
            }
        }

        Scheduler() {
        }

        void scheduleTask(Task task, long j, long j2) {
            Entry entry = new Entry(task, j, j2);
            task.beforeSchedule();
            synchronized (this) {
                this.mQueue.add(entry);
            }
            updateSchedule(entry);
        }

        void onAlarmTriggered() {
            List<Entry> arrayList = new ArrayList();
            synchronized (this) {
                while (!this.mQueue.isEmpty()) {
                    Entry entry = (Entry) this.mQueue.peek();
                    if (entry == null) {
                        this.mQueue.poll();
                    } else if (!entry.hasTriggered()) {
                        break;
                    } else {
                        entry = (Entry) this.mQueue.poll();
                        if (entry != null) {
                            arrayList.add(entry);
                            entry.removeFromHandler();
                        }
                    }
                }
                updateSchedule(null);
            }
            for (Entry entry2 : arrayList) {
                entry2.execute();
            }
        }

        private void updateSchedule(Entry entry) {
            Entry entry2;
            if (entry == null) {
                this.mNextTriggerAtTime = 0;
                synchronized (this) {
                    entry2 = (Entry) this.mQueue.peek();
                }
                if (entry2 == null) {
                    return;
                }
            }
            entry2 = entry;
            if (this.mNextTriggerAtTime == 0 || this.mNextTriggerAtTime > entry2.getTriggerAtBegin() || this.mNextTriggerEndTime > entry2.getTriggerAtEnd()) {
                AlarmTimer.this.mAlarmManager.cancel(AlarmTimer.this.mPendingIntent);
                this.mNextTriggerAtTime = entry2.getTriggerAtBegin();
                scheduleTask(entry2);
            }
        }

        public void onReceive(Context context, Intent intent) {
            if (AlarmTimer.this.mAction.equals(intent.getAction())) {
                onAlarmTriggered();
            }
        }

        synchronized void onAlarmStopped() {
            this.mQueue.clear();
            if (this.mNextTriggerAtTime != 0) {
                AlarmTimer.this.mAlarmManager.cancel(AlarmTimer.this.mPendingIntent);
                this.mNextTriggerAtTime = 0;
            }
        }

        private synchronized void unschedule(Task task) {
            if (!this.mQueue.isEmpty()) {
                Iterator it = this.mQueue.iterator();
                while (it.hasNext()) {
                    Entry entry = (Entry) it.next();
                    if (task.equals(entry.mTask)) {
                        it.remove();
                        entry.removeFromHandler();
                        if (entry.mTriggerAtTime == this.mNextTriggerAtTime) {
                            AlarmTimer.this.mAlarmManager.cancel(AlarmTimer.this.mPendingIntent);
                        }
                    }
                }
                updateSchedule(null);
            }
        }

        synchronized boolean isDueBefore(Task task, long j) {
            boolean z;
            long elapsedRealtime = SystemClock.elapsedRealtime() + j;
            Iterator it = this.mQueue.iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.mTask.equals(task)) {
                    z = entry.mTriggerAtTime <= elapsedRealtime;
                }
            }
            z = false;
            return z;
        }

        private void scheduleTask(Entry entry) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (this.mNextTriggerAtTime <= elapsedRealtime) {
                scheduleImmediately(entry);
            } else if (this.mNextTriggerAtTime - elapsedRealtime <= OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL) {
                scheduleDelayed(entry, this.mNextTriggerAtTime - elapsedRealtime);
            } else if (VERSION.SDK_INT >= 19) {
                scheduleAlarmWindowed(entry);
            } else {
                scheduleAlarmLegacy(entry);
            }
        }

        private void scheduleImmediately(Entry entry) {
            AlarmTimer.this.mWakeLock.acquire(1000);
            entry.removeFromHandler();
            AlarmTimer.this.mHandler.post(entry);
        }

        private void scheduleDelayed(Entry entry, long j) {
            entry.removeFromHandler();
            AlarmTimer.this.mHandler.postDelayed(entry, j);
        }

        private void scheduleAlarmLegacy(Entry entry) {
            AlarmTimer.this.mAlarmManager.set(2, this.mNextTriggerAtTime, AlarmTimer.this.mPendingIntent);
        }

        @TargetApi(19)
        private void scheduleAlarmWindowed(Entry entry) {
            if (entry.getTolerance() == 0) {
                AlarmTimer.this.mAlarmManager.setExact(2, this.mNextTriggerAtTime, AlarmTimer.this.mPendingIntent);
                return;
            }
            long triggerAtEnd = entry.getTriggerAtEnd();
            Iterator it = this.mQueue.iterator();
            while (it.hasNext()) {
                Entry entry2 = (Entry) it.next();
                if (!entry2.equals(entry)) {
                    if (triggerAtEnd < entry2.getTriggerAtBegin()) {
                        break;
                    } else if (triggerAtEnd > entry2.getTriggerAtEnd()) {
                        triggerAtEnd = entry2.getTriggerAtEnd();
                    }
                }
            }
            long j = triggerAtEnd - this.mNextTriggerAtTime;
            if (j > 0) {
                this.mNextTriggerEndTime = this.mNextTriggerAtTime + j;
                AlarmTimer.this.mAlarmManager.setWindow(2, this.mNextTriggerAtTime, j, AlarmTimer.this.mPendingIntent);
                return;
            }
            AlarmTimer.this.mAlarmManager.setExact(2, this.mNextTriggerAtTime, AlarmTimer.this.mPendingIntent);
        }
    }

    public AlarmTimer(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        this.mContext = context;
        this.mAction = String.format(ACTION_ALARM_FORMAT, new Object[]{Integer.valueOf(hashCode())});
        this.mPendingIntent = PendingIntent.getBroadcast(this.mContext, 0, new Intent(this.mAction), 134217728);
        this.mAlarmManager = (AlarmManager) this.mContext.getSystemService("alarm");
        this.mScheduler = new Scheduler();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mWakeLock = ((PowerManager) this.mContext.getSystemService("power")).newWakeLock(1, TAG_IMMEDIATE_ALARM_WAKELOCK);
        this.mWakeLock.setReferenceCounted(false);
    }

    public void schedule(Task task, long j) {
        schedule(task, j, 0);
    }

    public void schedule(Task task, long j, long j2) {
        if (task != null) {
            if (this.mStarted) {
                this.mScheduler.scheduleTask(task, j, j2);
                return;
            }
            throw new IllegalStateException("AlarmTimer not started");
        }
    }

    public void start() {
        this.mContext.registerReceiver(this.mScheduler, new IntentFilter(this.mAction), null, null);
        this.mStarted = true;
    }

    public void stop() {
        if (this.mStarted) {
            this.mScheduler.onAlarmStopped();
            this.mContext.unregisterReceiver(this.mScheduler);
            this.mHandler.removeCallbacksAndMessages(null);
            this.mStarted = false;
        }
    }
}
