package com.here.odnp.adaptations;

import com.here.odnp.power.IAlarmManager;
import com.here.posclient.IPowerManager;
import com.here.posclient.Status;

public class PowerManager implements IPowerManager {
    private IAlarmManager mAlarmManager;

    public PowerManager setAlarmManager(IAlarmManager iAlarmManager) {
        if (iAlarmManager == null) {
            throw new IllegalArgumentException("alarmManager is null");
        }
        this.mAlarmManager = iAlarmManager;
        return this;
    }

    public void open() {
        if (this.mAlarmManager != null) {
            this.mAlarmManager.open();
        }
    }

    public void close() {
        if (this.mAlarmManager != null) {
            this.mAlarmManager.close();
        }
    }

    public int setAlarm(long j, long j2, long j3) {
        if (this.mAlarmManager == null || !this.mAlarmManager.setAlarm(j, j2, j3)) {
            return Status.GeneralError.toInt();
        }
        return Status.Ok.toInt();
    }

    public int cancelAlarm(long j) {
        if (this.mAlarmManager == null) {
            return Status.GeneralError.toInt();
        }
        if (this.mAlarmManager.cancelAlarm(j)) {
            return Status.Ok.toInt();
        }
        return Status.NotFoundError.toInt();
    }
}
