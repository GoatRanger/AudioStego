package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.b;
import com.nokia.maps.a.c;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public abstract class AbstractListRequest<T> extends AbstractRequest<T> {
    abstract b<T, ?, ?> a();

    /* synthetic */ c b() {
        return a();
    }

    AbstractListRequest(b bVar) {
        super(bVar);
    }

    public AbstractRequest<T> setMaximumResults(int i) {
        a().a(i);
        return this;
    }

    @Deprecated
    public int getMaximumResults() {
        return a().a();
    }
}
