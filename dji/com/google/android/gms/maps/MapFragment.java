package com.google.android.gms.maps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzy;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment {
    private final zzb zzaIN = new zzb(this);
    private GoogleMap zzaIO;

    static class zza implements MapLifecycleDelegate {
        private final IMapFragmentDelegate zzaIP;
        private final Fragment zzapz;

        public zza(Fragment fragment, IMapFragmentDelegate iMapFragmentDelegate) {
            this.zzaIP = (IMapFragmentDelegate) zzx.zzw(iMapFragmentDelegate);
            this.zzapz = (Fragment) zzx.zzw(fragment);
        }

        public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
            try {
                this.zzaIP.getMapAsync(new com.google.android.gms.maps.internal.zzl.zza(this) {
                    final /* synthetic */ zza zzaIR;

                    public void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
                        onMapReadyCallback.onMapReady(new GoogleMap(iGoogleMapDelegate));
                    }
                });
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onCreate(Bundle bundle) {
            if (bundle == null) {
                try {
                    bundle = new Bundle();
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
            Bundle arguments = this.zzapz.getArguments();
            if (arguments != null && arguments.containsKey("MapOptions")) {
                com.google.android.gms.maps.internal.zzx.zza(bundle, "MapOptions", arguments.getParcelable("MapOptions"));
            }
            this.zzaIP.onCreate(bundle);
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                return (View) zze.zzp(this.zzaIP.onCreateView(zze.zzy(layoutInflater), zze.zzy(viewGroup), bundle));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroy() {
            try {
                this.zzaIP.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroyView() {
            try {
                this.zzaIP.onDestroyView();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onEnterAmbient(Bundle bundle) {
            try {
                this.zzaIP.onEnterAmbient(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onExitAmbient() {
            try {
                this.zzaIP.onExitAmbient();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                this.zzaIP.onInflate(zze.zzy(activity), (GoogleMapOptions) bundle.getParcelable("MapOptions"), bundle2);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onLowMemory() {
            try {
                this.zzaIP.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onPause() {
            try {
                this.zzaIP.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onResume() {
            try {
                this.zzaIP.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            try {
                this.zzaIP.onSaveInstanceState(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onStart() {
        }

        public void onStop() {
        }

        public IMapFragmentDelegate zzxK() {
            return this.zzaIP;
        }
    }

    static class zzb extends com.google.android.gms.dynamic.zza<zza> {
        private Activity mActivity;
        protected zzf<zza> zzaIS;
        private final List<OnMapReadyCallback> zzaIT = new ArrayList();
        private final Fragment zzapz;

        zzb(Fragment fragment) {
            this.zzapz = fragment;
        }

        private void setActivity(Activity activity) {
            this.mActivity = activity;
            zzxL();
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

        protected void zza(zzf<zza> com_google_android_gms_dynamic_zzf_com_google_android_gms_maps_MapFragment_zza) {
            this.zzaIS = com_google_android_gms_dynamic_zzf_com_google_android_gms_maps_MapFragment_zza;
            zzxL();
        }

        public void zzxL() {
            if (this.mActivity != null && this.zzaIS != null && zzrZ() == null) {
                try {
                    MapsInitializer.initialize(this.mActivity);
                    IMapFragmentDelegate zzt = zzy.zzaG(this.mActivity).zzt(zze.zzy(this.mActivity));
                    if (zzt != null) {
                        this.zzaIS.zza(new zza(this.zzapz, zzt));
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

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    public static MapFragment newInstance(GoogleMapOptions googleMapOptions) {
        MapFragment mapFragment = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MapOptions", googleMapOptions);
        mapFragment.setArguments(bundle);
        return mapFragment;
    }

    @Deprecated
    public final GoogleMap getMap() {
        IMapFragmentDelegate zzxK = zzxK();
        if (zzxK == null) {
            return null;
        }
        try {
            IGoogleMapDelegate map = zzxK.getMap();
            if (map == null) {
                return null;
            }
            if (this.zzaIO == null || this.zzaIO.zzxy().asBinder() != map.asBinder()) {
                this.zzaIO = new GoogleMap(map);
            }
            return this.zzaIO;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        zzx.zzci("getMapAsync must be called on the main thread.");
        this.zzaIN.getMapAsync(onMapReadyCallback);
    }

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.zzaIN.setActivity(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzaIN.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = this.zzaIN.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.setClickable(true);
        return onCreateView;
    }

    public void onDestroy() {
        this.zzaIN.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.zzaIN.onDestroyView();
        super.onDestroyView();
    }

    public final void onEnterAmbient(Bundle bundle) {
        zzx.zzci("onEnterAmbient must be called on the main thread.");
        this.zzaIN.onEnterAmbient(bundle);
    }

    public final void onExitAmbient() {
        zzx.zzci("onExitAmbient must be called on the main thread.");
        this.zzaIN.onExitAmbient();
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        this.zzaIN.setActivity(activity);
        Parcelable createFromAttributes = GoogleMapOptions.createFromAttributes(activity, attributeSet);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("MapOptions", createFromAttributes);
        this.zzaIN.onInflate(activity, bundle2, bundle);
    }

    public void onLowMemory() {
        this.zzaIN.onLowMemory();
        super.onLowMemory();
    }

    public void onPause() {
        this.zzaIN.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.zzaIN.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzaIN.onSaveInstanceState(bundle);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    protected IMapFragmentDelegate zzxK() {
        this.zzaIN.zzxL();
        return this.zzaIN.zzrZ() == null ? null : ((zza) this.zzaIN.zzrZ()).zzxK();
    }
}
