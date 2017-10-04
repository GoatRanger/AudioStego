package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.p;
import com.nokia.maps.am;

class DepartureBoardRequest$1 implements am<DepartureBoardRequest, p> {
    DepartureBoardRequest$1() {
    }

    public DepartureBoardRequest a(p pVar) {
        if (pVar == null) {
            return null;
        }
        try {
            return new DepartureBoardRequest(pVar, null);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
