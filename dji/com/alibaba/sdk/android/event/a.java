package com.alibaba.sdk.android.event;

import com.alibaba.sdk.android.trace.AliSDKLogger;
import java.util.List;

final class a implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ Event b;
    final /* synthetic */ EventBus c;

    a(EventBus eventBus, List list, Event event) {
        this.c = eventBus;
        this.a = list;
        this.b = event;
    }

    public final void run() {
        for (EventListener eventListener : this.a) {
            int threadModel = eventListener.getThreadModel();
            if (threadModel == 2) {
                try {
                    eventListener.onEvent(this.b);
                } catch (Throwable th) {
                    AliSDKLogger.e("eventBus", "fail to execute the event " + this.b.name + " the error message is " + th.getMessage(), th);
                }
            } else if (threadModel == 1) {
                com.alibaba.sdk.android.b.a.f.postUITask(new a(eventListener, this.b));
            } else {
                AliSDKLogger.e("eventBus", "No support for thread model " + threadModel + ", fail to dispatch the event " + this.b.name);
            }
        }
    }
}
