package com.alibaba.sdk.android.event;

public interface EventListener {
    public static final int NO_UI_THREAD = 2;
    public static final int UI_THREAD = 1;

    int getThreadModel();

    void onEvent(Event event);
}
