package com.here.services.location;

import android.location.Location;
import com.here.services.HereLocationApiClient;

public interface LocationApi {
    Location getLastLocation(HereLocationApiClient hereLocationApiClient);
}
