package com.alibaba.sdk.android.plugin;

public enum PluginState {
    LOADED,
    SYNC_STARTING,
    SYNC_START_FAILED,
    SYNC_STARTED,
    ASYNC_STARTING,
    ASYNC_STARTED,
    ASYNC_START_FAILED,
    ASYNC_STOPPING,
    ASYNC_STOPPED,
    ASYNC_STOP_FAILED,
    UNDEFINED
}
