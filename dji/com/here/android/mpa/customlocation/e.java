package com.here.android.mpa.customlocation;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.here.android.mpa.customlocation.Request.Error;
import com.nokia.maps.ak;
import com.nokia.maps.bj;
import java.lang.ref.WeakReference;

final class e extends a<LocationResponse> {
    private static final String c = a.class.getSimpleName();

    protected /* synthetic */ Object a(String str) throws ak {
        return b(str);
    }

    e(g gVar, WeakReference<f> weakReference) {
        super(gVar, weakReference);
    }

    protected LocationResponse b(String str) throws ak {
        try {
            LocationResponse locationResponse = (LocationResponse) new Gson().fromJson(str, LocationResponse.class);
            switch (this.a) {
                case PROXIMITY:
                    locationResponse.locations = locationResponse.proximityLocations;
                    return locationResponse;
                case BOUNDING_BOX:
                    locationResponse.locations = locationResponse.bblocations;
                    return locationResponse;
                case CORRIDOR:
                    locationResponse.locations = locationResponse.corridorLocations;
                    return locationResponse;
                case ROUTE:
                    locationResponse.locations = locationResponse.corridorLocations;
                    return locationResponse;
                default:
                    return locationResponse;
            }
        } catch (JsonSyntaxException e) {
            bj.c(c, "Exception thrown: %s", new Object[]{e.getLocalizedMessage()});
            return null;
        }
    }

    protected void a(LocationResponse locationResponse, Error error) {
        f fVar = (f) this.b.get();
        if (fVar != null) {
            fVar.a(locationResponse, Error.NONE);
        }
    }
}
