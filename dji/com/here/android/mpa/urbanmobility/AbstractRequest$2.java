package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.c;
import com.nokia.maps.am;

class AbstractRequest$2 implements am<AbstractRequest, c> {
    AbstractRequest$2() {
    }

    public AbstractRequest a(final c cVar) {
        if (cVar == null) {
            return null;
        }
        try {
            return new AbstractRequest(this, cVar) {
                final /* synthetic */ AbstractRequest$2 b;

                c b() {
                    return cVar;
                }
            };
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
