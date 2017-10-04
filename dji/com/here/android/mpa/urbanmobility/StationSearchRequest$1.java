package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.au;
import com.nokia.maps.am;

class StationSearchRequest$1 implements am<StationSearchRequest, au> {
    StationSearchRequest$1() {
    }

    public StationSearchRequest a(au auVar) {
        if (auVar == null) {
            return null;
        }
        try {
            return new StationSearchRequest(auVar, null);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
