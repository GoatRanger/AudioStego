package com.alibaba.sdk.android.event;

import android.text.TextUtils;
import com.alibaba.sdk.android.trace.AliSDKLogger;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private static final EventBus a = new EventBus();
    private ConcurrentHashMap<String, List<EventListener>> b = new ConcurrentHashMap();
    private Map<String, Event> c = Collections.synchronizedMap(new LinkedHashMap());

    private static class a implements Runnable {
        private EventListener a;
        private Event b;

        public a(EventListener eventListener, Event event) {
            this.b = event;
            this.a = eventListener;
        }

        public final void run() {
            try {
                this.a.onEvent(this.b);
            } catch (Throwable th) {
                AliSDKLogger.e("eventBus", "fail to execute the event " + this.b.name + " the error message is " + th.getMessage(), th);
            }
        }
    }

    public static EventBus getDefault() {
        return a;
    }

    public void sendEvent(String str) {
        sendEvent(str, null);
    }

    public void sendEvent(String str, Map<String, Object> map) {
        if (!TextUtils.isEmpty(str)) {
            Event newEvent = Event.newEvent(str, map);
            List list = (List) this.b.get(newEvent.name);
            if (list == null || list.size() == 0) {
                this.c.put(newEvent.name, newEvent);
            } else {
                com.alibaba.sdk.android.b.a.f.postTask(new a(this, list, newEvent));
            }
        }
    }

    public void registerEventListener(String str, EventListener eventListener) {
        int i;
        int i2 = 1;
        if (str == null) {
            i = 1;
        } else {
            i = 0;
        }
        if (eventListener != null) {
            i2 = 0;
        }
        if ((i2 | i) == 0) {
            List list = (List) this.b.get(str);
            if (list == null) {
                this.b.putIfAbsent(str, new CopyOnWriteArrayList());
                list = (List) this.b.get(str);
            }
            list.add(eventListener);
            if (((Event) this.c.get(str)) != null) {
                sendEvent(str);
            }
        }
    }

    public void registerEventListener(String[] strArr, EventListener eventListener) {
        if (strArr != null && eventListener != null) {
            for (String registerEventListener : strArr) {
                registerEventListener(registerEventListener, eventListener);
            }
        }
    }

    public void unregisterEventListener(String str, EventListener eventListener) {
        List list = (List) this.b.get(str);
        if (list != null) {
            list.remove(eventListener);
        }
    }
}
