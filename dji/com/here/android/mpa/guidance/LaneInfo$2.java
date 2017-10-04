package com.here.android.mpa.guidance;

import com.nokia.maps.LaneInfoImpl;
import com.nokia.maps.am;

class LaneInfo$2 implements am<LaneInfo, LaneInfoImpl> {
    LaneInfo$2() {
    }

    public LaneInfo a(LaneInfoImpl laneInfoImpl) {
        return laneInfoImpl != null ? new LaneInfo(laneInfoImpl, null) : null;
    }
}
