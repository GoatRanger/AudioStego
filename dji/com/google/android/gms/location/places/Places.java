package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.location.places.internal.zzd;
import com.google.android.gms.location.places.internal.zze;
import com.google.android.gms.location.places.internal.zze.zza;
import com.google.android.gms.location.places.internal.zzj;
import com.google.android.gms.location.places.internal.zzk;

public class Places {
    public static final Api<PlacesOptions> GEO_DATA_API = new Api("Places.GEO_DATA_API", new zza(null, null), zzaGz);
    public static final GeoDataApi GeoDataApi = new zzd();
    public static final Api<PlacesOptions> PLACE_DETECTION_API = new Api("Places.PLACE_DETECTION_API", new zzk.zza(null, null), zzaGA);
    public static final PlaceDetectionApi PlaceDetectionApi = new zzj();
    public static final zzc<zzk> zzaGA = new zzc();
    public static final zzc<zze> zzaGz = new zzc();

    private Places() {
    }
}
