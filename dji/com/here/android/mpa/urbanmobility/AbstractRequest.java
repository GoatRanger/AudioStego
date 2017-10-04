package com.here.android.mpa.urbanmobility;

import com.here.android.mpa.common.TransitType;
import com.nokia.maps.a.c;
import com.nokia.maps.annotation.HybridPlus;
import java.util.List;

@HybridPlus
public abstract class AbstractRequest<T> {
    abstract c<T, ?, ?> b();

    AbstractRequest(c cVar) {
    }

    public void execute() {
        b().d();
    }

    @Deprecated
    public String getHost() {
        return b().e();
    }

    @Deprecated
    public String getClient() {
        return b().f();
    }

    @Deprecated
    public String getLanguage() {
        return b().g();
    }

    @Deprecated
    public List<TransitType> getTransitTypes() {
        return b().h();
    }

    public AbstractRequest<T> setClient(String str) {
        b().a(str);
        return this;
    }

    @Deprecated
    public AbstractRequest<T> setLanguage(String str) {
        b().b(str);
        return this;
    }

    @Deprecated
    public AbstractRequest<T> setTransitTypes(List<TransitType> list) {
        b().a(list);
        return this;
    }

    public AbstractRequest<T> setTransportTypes(List<TransportType> list) {
        b().b(list);
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return b().equals(((AbstractRequest) obj).b());
    }

    public int hashCode() {
        return b().hashCode() + 31;
    }

    static {
        c.a(new 1(), new 2());
    }
}
