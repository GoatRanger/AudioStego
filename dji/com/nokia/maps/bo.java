package com.nokia.maps;

import android.content.Context;

public class bo {
    public static MapGestureHandler a(MapImpl mapImpl, Context context) {
        if (MapSettings.p()) {
            return new NmaaGestureHandler(mapImpl, context);
        }
        return new bp(mapImpl, context);
    }
}
