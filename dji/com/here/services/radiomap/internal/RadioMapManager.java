package com.here.services.radiomap.internal;

import android.content.Context;
import com.here.services.internal.ServiceNotFoundException;

public final class RadioMapManager {
    public static IRadioMapManager open(Context context) throws ServiceNotFoundException {
        return RadioMapManagerClient.open(context);
    }

    private RadioMapManager() {
    }
}
