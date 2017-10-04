package com.here.odnp.power;

import android.content.Context;
import com.here.odnp.util.AlarmTimer;
import com.here.posclient.AlarmManager.IListener;
import java.util.HashMap;
import java.util.Map;

public class PlatformAlarmManager implements IAlarmManager {
    private static final String TAG = "odnp.power.PlatformAlarmManager";
    private final AlarmTimer mAlarmTimer;
    private final IListener mListener;
    private final Map<Long, Task> mTimerTasks = new HashMap();

    class Task extends com.here.odnp.util.AlarmTimer.Task {
        public final Long mId;

        Task(AlarmTimer alarmTimer, long j) {
            alarmTimer.getClass();
            super();
            this.mId = Long.valueOf(j);
        }

        public void run() {
            if (PlatformAlarmManager.this.removeTimerTask(this.mId)) {
                PlatformAlarmManager.this.onTimerExpired(this);
            }
        }
    }

    public PlatformAlarmManager(Context context, IListener iListener) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        } else if (iListener == null) {
            throw new IllegalArgumentException("listener is null");
        } else {
            this.mAlarmTimer = new AlarmTimer(context);
            this.mListener = iListener;
        }
    }

    public void open() {
        this.mAlarmTimer.start();
    }

    public void close() {
        this.mAlarmTimer.stop();
    }

    public synchronized boolean setAlarm(long j, long j2, long j3) {
        com.here.odnp.util.AlarmTimer.Task task;
        com.here.odnp.util.AlarmTimer.Task task2 = (Task) this.mTimerTasks.get(Long.valueOf(j));
        if (task2 != null) {
            task2.cancel();
            task = task2;
        } else {
            task = new Task(this.mAlarmTimer, j);
            this.mTimerTasks.put(Long.valueOf(j), task);
        }
        this.mAlarmTimer.schedule(task, j2, j3);
        return true;
    }

    public synchronized boolean cancelAlarm(long j) {
        boolean z;
        Task task = (Task) this.mTimerTasks.remove(Long.valueOf(j));
        if (task == null) {
            z = false;
        } else {
            task.cancel();
            z = true;
        }
        return z;
    }

    private synchronized boolean removeTimerTask(Long l) {
        return this.mTimerTasks.remove(l) != null;
    }

    private void onTimerExpired(Task task) {
        if (task != null) {
            this.mListener.onTimerExpired(task.mId.longValue());
        }
    }
}
