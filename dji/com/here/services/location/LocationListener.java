package com.here.services.location;

import android.location.Location;

public interface LocationListener {
    void onLocationChanged(Location location);

    void onOptionsChanged(OptionsChangedEvent optionsChangedEvent);
}
