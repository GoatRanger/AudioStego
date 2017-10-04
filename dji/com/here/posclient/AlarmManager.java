package com.here.posclient;

public class AlarmManager {

    public interface IListener {
        void onTimerExpired(long j);
    }

    public static native void alarmTimerExpired(long j);
}
