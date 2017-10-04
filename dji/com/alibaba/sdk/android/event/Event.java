package com.alibaba.sdk.android.event;

import java.util.Map;

public class Event {
    public String name;
    public Map<String, Object> params;

    public static Event newEvent(String str, Map<String, Object> map) {
        Event event = new Event();
        event.name = str;
        event.params = map;
        return event;
    }

    public int hashCode() {
        return (this.name == null ? 0 : this.name.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Event event = (Event) obj;
        if (this.name == null) {
            if (event.name != null) {
                return false;
            }
            return true;
        } else if (this.name.equals(event.name)) {
            return true;
        } else {
            return false;
        }
    }
}
