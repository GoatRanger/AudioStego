package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class zzd implements FusedLocationProviderApi {

    private static abstract class zza extends com.google.android.gms.location.LocationServices.zza<Status> {
        public zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public /* synthetic */ Result zzb(Status status) {
            return zzd(status);
        }

        public Status zzd(Status status) {
            return status;
        }
    }

    public Location getLastLocation(GoogleApiClient googleApiClient) {
        try {
            return LocationServices.zzd(googleApiClient).getLastLocation();
        } catch (Exception e) {
            return null;
        }
    }

    public LocationAvailability getLocationAvailability(GoogleApiClient googleApiClient) {
        try {
            return LocationServices.zzd(googleApiClient).zzwD();
        } catch (Exception e) {
            return null;
        }
    }

    public PendingResult<Status> removeLocationUpdates(GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return googleApiClient.zzb(new zza(this, googleApiClient) {
            final /* synthetic */ zzd zzaFf;

            protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
                com_google_android_gms_location_internal_zzl.zza(pendingIntent, new com.google.android.gms.location.internal.zzg.zza(this) {
                    final /* synthetic */ AnonymousClass9 zzaFq;

                    {
                        this.zzaFq = r1;
                    }

                    public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
                        this.zzaFq.zzb(fusedLocationProviderResult.getStatus());
                    }
                });
            }
        });
    }

    public PendingResult<Status> removeLocationUpdates(GoogleApiClient googleApiClient, final LocationCallback locationCallback) {
        return googleApiClient.zzb(new zza(this, googleApiClient) {
            final /* synthetic */ zzd zzaFf;

            protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
                com_google_android_gms_location_internal_zzl.zza(locationCallback, new com.google.android.gms.location.internal.zzg.zza(this) {
                    final /* synthetic */ AnonymousClass2 zzaFi;

                    {
                        this.zzaFi = r1;
                    }

                    public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
                        this.zzaFi.zzb(fusedLocationProviderResult.getStatus());
                    }
                });
            }
        });
    }

    public PendingResult<Status> removeLocationUpdates(GoogleApiClient googleApiClient, final LocationListener locationListener) {
        return googleApiClient.zzb(new zza(this, googleApiClient) {
            final /* synthetic */ zzd zzaFf;

            protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
                com_google_android_gms_location_internal_zzl.zza(locationListener, new com.google.android.gms.location.internal.zzg.zza(this) {
                    final /* synthetic */ AnonymousClass8 zzaFp;

                    {
                        this.zzaFp = r1;
                    }

                    public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
                        this.zzaFp.zzb(fusedLocationProviderResult.getStatus());
                    }
                });
            }
        });
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient googleApiClient, final LocationRequest locationRequest, final PendingIntent pendingIntent) {
        return googleApiClient.zzb(new zza(this, googleApiClient) {
            final /* synthetic */ zzd zzaFf;

            protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
                com_google_android_gms_location_internal_zzl.zza(locationRequest, pendingIntent, new com.google.android.gms.location.internal.zzg.zza(this) {
                    final /* synthetic */ AnonymousClass7 zzaFo;

                    {
                        this.zzaFo = r1;
                    }

                    public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
                        this.zzaFo.zzb(fusedLocationProviderResult.getStatus());
                    }
                });
            }
        });
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationCallback locationCallback, Looper looper) {
        final LocationRequest locationRequest2 = locationRequest;
        final LocationCallback locationCallback2 = locationCallback;
        final Looper looper2 = looper;
        return googleApiClient.zzb(new zza(this, googleApiClient) {
            final /* synthetic */ zzd zzaFf;

            protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
                com_google_android_gms_location_internal_zzl.zza(LocationRequestInternal.zzb(locationRequest2), locationCallback2, looper2, new com.google.android.gms.location.internal.zzg.zza(this) {
                    final /* synthetic */ AnonymousClass6 zzaFn;

                    {
                        this.zzaFn = r1;
                    }

                    public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
                        this.zzaFn.zzb(fusedLocationProviderResult.getStatus());
                    }
                });
            }
        });
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient googleApiClient, final LocationRequest locationRequest, final LocationListener locationListener) {
        return googleApiClient.zzb(new zza(this, googleApiClient) {
            final /* synthetic */ zzd zzaFf;

            protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
                com_google_android_gms_location_internal_zzl.zza(locationRequest, locationListener, null, new com.google.android.gms.location.internal.zzg.zza(this) {
                    final /* synthetic */ AnonymousClass1 zzaFg;

                    {
                        this.zzaFg = r1;
                    }

                    public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
                        this.zzaFg.zzb(fusedLocationProviderResult.getStatus());
                    }
                });
            }
        });
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener, Looper looper) {
        final LocationRequest locationRequest2 = locationRequest;
        final LocationListener locationListener2 = locationListener;
        final Looper looper2 = looper;
        return googleApiClient.zzb(new zza(this, googleApiClient) {
            final /* synthetic */ zzd zzaFf;

            protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
                com_google_android_gms_location_internal_zzl.zza(locationRequest2, locationListener2, looper2, new com.google.android.gms.location.internal.zzg.zza(this) {
                    final /* synthetic */ AnonymousClass5 zzaFm;

                    {
                        this.zzaFm = r1;
                    }

                    public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
                        this.zzaFm.zzb(fusedLocationProviderResult.getStatus());
                    }
                });
            }
        });
    }

    public PendingResult<Status> setMockLocation(GoogleApiClient googleApiClient, final Location location) {
        return googleApiClient.zzb(new zza(this, googleApiClient) {
            final /* synthetic */ zzd zzaFf;

            protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
                com_google_android_gms_location_internal_zzl.zzc(location);
                zzb(Status.zzabb);
            }
        });
    }

    public PendingResult<Status> setMockMode(GoogleApiClient googleApiClient, final boolean z) {
        return googleApiClient.zzb(new zza(this, googleApiClient) {
            final /* synthetic */ zzd zzaFf;

            protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
                com_google_android_gms_location_internal_zzl.zzah(z);
                zzb(Status.zzabb);
            }
        });
    }
}
