package com.here.android.mpa.common;

import com.nokia.maps.ViewObjectImpl;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.k;

@OnlineNative
public abstract class ViewObject {
    private ViewObjectImpl a;

    @Online
    public enum Type {
        USER_OBJECT,
        PROXY_OBJECT,
        UNKNOWN_OBJECT
    }

    protected ViewObject(ViewObjectImpl viewObjectImpl) {
        this.a = viewObjectImpl;
    }

    @Online
    public Type getBaseType() {
        return this.a.k();
    }

    @Online
    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    @Online
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!ViewObject.class.isInstance(obj)) {
            return false;
        }
        return this.a.equals(((ViewObject) obj).a);
    }

    static {
        ViewObjectImpl.c(new k<ViewObject, ViewObjectImpl>() {
            public ViewObjectImpl a(ViewObject viewObject) {
                return viewObject.a;
            }
        });
    }
}
