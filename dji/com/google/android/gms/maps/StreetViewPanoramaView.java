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
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzy;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class StreetViewPanoramaView extends FrameLayout {
    private StreetViewPanorama zzaJj;
    private final zzb zzaJv;

    static class zza implements StreetViewLifecycleDelegate {
        private final ViewGroup zzaIV;
        private final IStreetViewPanoramaViewDelegate zzaJw;
        private View zzaJx;

        public zza(ViewGroup viewGroup, IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate) {
            this.zzaJw = (IStreetViewPanoramaViewDelegate) zzx.zzw(iStreetViewPanoramaViewDelegate);
            this.zzaIV = (ViewGroup) zzx.zzw(viewGroup);
        }

        public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            try {
                this.zzaJw.getStreetViewPanoramaAsync(new com.google.android.gms.maps.internal.zzv.zza(this) {
                    final /* synthetic */ zza zzaJy;

                    public void zza(IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) throws RemoteException {
                        onStreetViewPanoramaReadyCallback.onStreetViewPanoramaReady(new StreetViewPanorama(iStreetViewPanoramaDelegate));
                    }
                });
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onCreate(Bundle bundle) {
            try {
                this.zzaJw.onCreate(bundle);
                this.zzaJx = (View) zze.zzp(this.zzaJw.getView());
                this.zzaIV.removeAllViews();
                this.zzaIV.addView(this.zzaJx);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
        }

        public void onDestroy() {
            try {
                this.zzaJw.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
        }

        public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
        }

        public void onLowMemory() {
            try {
                this.zzaJw.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onPause() {
            try {
                this.zzaJw.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onResume() {
            try {
                this.zzaJw.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            try {
                this.zzaJw.onSaveInstanceState(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onStart() {
        }

        public void onStop() {
        }

        public IStreetViewPanoramaViewDelegate zzxS() {
            return this.zzaJw;
        }
    }

    static class zzb extends com.google.android.gms.dynamic.zza<zza> {
        private final Context mContext;
        protected zzf<zza> zzaIS;
        private final ViewGroup zzaIZ;
        private final List<OnStreetViewPanoramaReadyCallback> zzaJn = new ArrayList();
        private final StreetViewPanoramaOptions zzaJz;

        zzb(ViewGroup viewGroup, Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
            this.zzaIZ = viewGroup;
            this.mContext = context;
            this.zzaJz = streetViewPanoramaOptions;
        }

        public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            if (zzrZ() != null) {
                ((zza) zzrZ()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
            } else {
                this.zzaJn.add(onStreetViewPanoramaReadyCallback);
            }
        }

        protected void zza(zzf<zza> com_google_android_gms_dynamic_zzf_com_google_android_gms_maps_StreetViewPanoramaView_zza) {
            this.zzaIS = com_google_android_gms_dynamic_zzf_com_google_android_gms_maps_StreetViewPanoramaView_zza;
            zzxL();
        }

        public void zzxL() {
            if (this.zzaIS != null && zzrZ() == null) {
                try {
                    this.zzaIS.zza(new zza(this.zzaIZ, zzy.zzaG(this.mContext).zza(zze.zzy(this.mContext), this.zzaJz)));
                    for (OnStreetViewPanoramaReadyCallback streetViewPanoramaAsync : this.zzaJn) {
                        ((zza) zzrZ()).getStreetViewPanoramaAsync(streetViewPanoramaAsync);
                    }
                    this.zzaJn.clear();
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                } catch (GooglePlayServicesNotAvailableException e2) {
                }
            }
        }
    }

    public StreetViewPanoramaView(Context context) {
        super(context);
        this.zzaJv = new zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzaJv = new zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzaJv = new zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
        super(context);
        this.zzaJv = new zzb(this, context, streetViewPanoramaOptions);
    }

    @Deprecated
    public final StreetViewPanorama getStreetViewPanorama() {
        if (this.zzaJj != null) {
            return this.zzaJj;
        }
        this.zzaJv.zzxL();
        if (this.zzaJv.zzrZ() == null) {
            return null;
        }
        try {
            this.zzaJj = new StreetViewPanorama(((zza) this.zzaJv.zzrZ()).zzxS().getStreetViewPanorama());
            return this.zzaJj;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        zzx.zzci("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzaJv.getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
    }

    public final void onCreate(Bundle bundle) {
        this.zzaJv.onCreate(bundle);
        if (this.zzaJv.zzrZ() == null) {
            com.google.android.gms.dynamic.zza.zzb((FrameLayout) this);
        }
    }

    public final void onDestroy() {
        this.zzaJv.onDestroy();
    }

    public final void onLowMemory() {
        this.zzaJv.onLowMemory();
    }

    public final void onPause() {
        this.zzaJv.onPause();
    }

    public final void onResume() {
        this.zzaJv.onResume();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.zzaJv.onSaveInstanceState(bundle);
    }
}
