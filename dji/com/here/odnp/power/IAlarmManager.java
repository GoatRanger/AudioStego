package com.here.odnp.power;

public interface IAlarmManager {
    boolean cancelAlarm(long j);

    void close();

    void open();

    boolean setAlarm(long j, long j2, long j3);
}
