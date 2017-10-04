package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzy;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class MapView extends FrameLayout {
    private GoogleMap zzaIO;
    private final zzb zzaIU;

    static class zza implements MapLifecycleDelegate {
        private final ViewGroup zzaIV;
        private final IMapViewDelegate zzaIW;
        private View zzaIX;

        public zza(ViewGroup viewGroup, IMapViewDelegate iMapViewDelegate) {
            this.zzaIW = (IMapViewDelegate) zzx.zzw(iMapViewDelegate);
            this.zzaIV = (ViewGroup) zzx.zzw(viewGroup);
        }

        public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
            try {
                this.zzaIW.getMapAsync(new com.google.android.gms.maps.internal.zzl.zza(this) {
                    final /* synthetic */ zza zzaIY;

                    public void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
                        onMapReadyCallback.onMapReady(new GoogleMap(iGoogleMapDelegate));
                    }
                });
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onCreate(Bundle bundle) {
            try {
                this.zzaIW.onCreate(bundle);
                this.zzaIX = (View) zze.zzp(this.zzaIW.getView());
                this.zzaIV.removeAllViews();
                this.zzaIV.addView(this.zzaIX);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
        }

        public void onDestroy() {
            try {
                this.zzaIW.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
        }

        public void onEnterAmbient(Bundle bundle) {
            try {
                this.zzaIW.onEnterAmbient(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onExitAmbient() {
            try {
                this.zzaIW.onExitAmbient();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
        }

        public void onLowMemory() {
            try {
                this.zzaIW.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onPause() {
            try {
                this.zzaIW.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onResume() {
            try {
                this.zzaIW.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            try {
                this.zzaIW.onSaveInstanceState(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onStart() {
        }

        public void onStop() {
        }

        public IMapViewDelegate zzxM() {
            return this.zzaIW;
        }
    }

    static class zzb extends com.google.android.gms.dynamic.zza<zza> {
        private final Context mContext;
        protected zzf<zza> zzaIS;
        private final List<OnMapReadyCallback> zzaIT = new ArrayList();
        private final ViewGroup zzaIZ;
        private final GoogleMapOptions zzaJa;

        zzb(ViewGroup viewGroup, Context context, GoogleMapOptions googleMapOptions) {
            this.zzaIZ = viewGroup;
            this.mContext = context;
            this.zzaJa = googleMapOptions;
        }

        public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
            if (zzrZ() != null) {
                ((zza) zzrZ()).getMapAsync(onMapReadyCallback);
            } else {
                this.zzaIT.add(onMapReadyCallback);
            }
        }

        public void onEnterAmbient(Bundle bundle) {
            if (zzrZ() != null) {
                ((zza) zzrZ()).onEnterAmbient(bundle);
            }
        }

        public void onExitAmbient() {
            if (zzrZ() != null) {
                ((zza) zzrZ()).onExitAmbient();
            }
        }

        protected void zza(zzf<zza> com_google_android_gms_dynamic_zzf_com_google_android_gms_maps_MapView_zza) {
            this.zzaIS = com_google_android_gms_dynamic_zzf_com_google_android_gms_maps_MapView_zza;
            zzxL();
        }

        public void zzxL() {
            if (this.zzaIS != null && zzrZ() == null) {
                try {
                    MapsInitializer.initialize(this.mContext);
                    IMapViewDelegate zza = zzy.zzaG(this.mContext).zza(zze.zzy(this.mContext), this.zzaJa);
                    if (zza != null) {
                        this.zzaIS.zza(new zza(this.zzaIZ, zza));
                        for (OnMapReadyCallback mapAsync : this.zzaIT) {
                            ((zza) zzrZ()).getMapAsync(mapAsync);
                        }
                        this.zzaIT.clear();
                    }
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                } catch (GooglePlayServicesNotAvailableException e2) {
                }
            }
        }
    }

    public MapView(Context context) {
        super(context);
        this.zzaIU = new zzb(this, context, null);
        init();
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzaIU = new zzb(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        init();
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzaIU = new zzb(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        init();
    }

    public MapView(Context context, GoogleMapOptions googleMapOptions) {
        super(context);
        this.zzaIU = new zzb(this, context, googleMapOptions);
        init();
    }

    private void init() {
        setClickable(true);
    }

    @Deprecated
    public final GoogleMap getMap() {
        if (this.zzaIO != null) {
            return this.zzaIO;
        }
        this.zzaIU.zzxL();
        if (this.zzaIU.zzrZ() == null) {
            return null;
        }
        try {
            this.zzaIO = new GoogleMap(((zza) this.zzaIU.zzrZ()).zzxM().getMap());
            return this.zzaIO;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        zzx.zzci("getMapAsync() must be called on the main thread");
        this.zzaIU.getMapAsync(onMapReadyCallback);
    }

    public final void onCreate(Bundle bundle) {
        this.zzaIU.onCreate(bundle);
        if (this.zzaIU.zzrZ() == null) {
            com.google.android.gms.dynamic.zza.zzb((FrameLayout) this);
        }
    }

    public final void onDestroy() {
        this.zzaIU.onDestroy();
    }

    public final void onEnterAmbient(Bundle bundle) {
        zzx.zzci("onEnterAmbient() must be called on the main thread");
        this.zzaIU.onEnterAmbient(bundle);
    }

    public final void onExitAmbient() {
        zzx.zzci("onExitAmbient() must be called on the main thread");
        this.zzaIU.onExitAmbient();
    }

    public final void onLowMemory() {
        this.zzaIU.onLowMemory();
    }

    public final void onPause() {
        this.zzaIU.onPause();
    }

    public final void onResume() {
        this.zzaIU.onResume();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.zzaIU.onSaveInstanceState(bundle);
    }
}
